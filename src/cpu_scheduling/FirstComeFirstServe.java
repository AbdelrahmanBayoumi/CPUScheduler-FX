package cpu_scheduling;

import java.util.ArrayList;
import java.util.List;

public class FirstComeFirstServe {

    public static Output Calc(List<Row> process) {
        List<Row> processes = Utility.deepCopy(process);
        Utility.sortArrival(processes);
        int timeline = 0;
        double avg_waiting = 0;
        double avg_turnaround = 0;
        for (Row i : processes) {
            if (timeline < i.getArrivalTime()) {
                timeline = i.getArrivalTime();
            }
            i.setStartTime(timeline);
            timeline += i.getBurstTime();
            i.setFinishTime(timeline);
            i.setWaitingTime(i.getStartTime() - i.getArrivalTime());
            avg_waiting += i.getWaitingTime();
            i.setTurnaroundTime(i.getBurstTime() + i.getWaitingTime());
            avg_turnaround += i.getTurnaroundTime();
        }
        return new Output(processes, Utility.FormatDouble(avg_waiting / processes.size()),
                Utility.FormatDouble(avg_turnaround / processes.size()));
    }

}
