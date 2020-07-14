package cpu_scheduling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class ShortestRemainingTime {

    private static List<Row> filter(List<Row> process, int arrival_time) {
        List<Row> processes_out = new ArrayList<>();
        for (Row i : process) {
            if (i.getArrivalTime() <= arrival_time) {
                processes_out.add(i);
                if (i.getBurstTime() == 0) {
                    processes_out.remove(i);
                }
            }
        }
        Utility.sortBrust(processes_out);
        return processes_out;
    }

    private static int getBrustByName(String name, List<Row> input_process) {
        for (Row i : input_process) {
            if (i.getProcessName().equals(name)) {
                return i.getBurstTime();
            }
        }
        return 0;
    }

    private static List<Row> RemoveDuplicateList(List<Row> input_process) {
        Collection<Row> hs = new HashSet<>(input_process);
        List<Row> process = new ArrayList<>(hs);
        Utility.sortName(process);
        return process;
    }

    public static Output Calc(List<Row> input_process) {
        List<Row> process = new ArrayList<>();
        List<Row> L = Utility.deepCopy(input_process);
        int sum_burst = Utility.sumBurst(L);
        int timeline = 0;
        double avg_waiting = 0;
        double avg_turnaround = 0;
        List<Row> x;
        //Turn Around time = Exit time – Arrival time
        //Waiting time = Turn Around time – Burst time
        for (int j = 0; j < sum_burst; j++) {
            x = filter(L, j);
            Row i = x.get(0);
            i.setStartTime(timeline);
            i.setBurstTime(i.getBurstTime() - 1);
            timeline++;
            i.setFinishTime(timeline);
            i.setTurnaroundTime(i.getFinishTime() - i.getArrivalTime());
            i.setWaitingTime(i.getTurnaroundTime() - getBrustByName(i.getProcessName(), input_process));
            process.add(i);
        }
        //for timeline process
        //return process;
        process = RemoveDuplicateList(process);
        for (Row i : process) {
            avg_waiting += i.getWaitingTime();
            avg_turnaround += i.getTurnaroundTime();
        }

        return new Output(process, Utility.FormatDouble(avg_waiting / input_process.size()),
                Utility.FormatDouble(avg_turnaround / input_process.size()));
    }

}
