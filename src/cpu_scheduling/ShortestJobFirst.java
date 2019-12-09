package cpu_scheduling;

import java.util.ArrayList;
import java.util.List;

public class ShortestJobFirst {

    private static List<Row> filter(List<Row> process, int arrival_time) {
        List<Row> processes_out = new ArrayList<>();
        for (Row i : process) {
            if (i.getArrivalTime() <= arrival_time) {
                processes_out.add(i);
            }
        }
        Utility.sortBrust(processes_out);
        return processes_out;
    }

    public static Output Calc(List<Row> input_process) {
        List<Row> process = new ArrayList<>();
        List<Row> L = Utility.deepCopy(input_process);
        int sum_burst = Utility.sumBurst(L);
        int timeline = 0;
        double avg_waiting = 0;
        double avg_turnaround = 0;
        List<Row> x;
        for (int j = 0; j < sum_burst; j++) {
            x = filter(L, timeline);
            for (Row i : x) {
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
                L.remove(i);
                process.add(i);
            }
        }

        return new Output(process, Utility.FormatDouble(avg_waiting / input_process.size()),
                Utility.FormatDouble(avg_turnaround / input_process.size()));
    }

    public static void main(String[] args) {
        List<Row> LR = new ArrayList();
        //{'p2': [0, 12], 'p3': [3, 8], 'p4': [5, 4], 'p1': [10, 10], 'p5': [12, 6]}
//        LR.add(new Row("P2", 0, 12));
//        LR.add(new Row("P3", 3, 8));
//        LR.add(new Row("P4", 5, 4));
//        LR.add(new Row("P1", 10, 10));
//        LR.add(new Row("P5", 12, 6));

        LR.add(new Row("P1", 0, 3));
        LR.add(new Row("P2", 3, 7));
        LR.add(new Row("P3", 4, 2));
        LR.add(new Row("P4", 8, 5));
        LR.add(new Row("P5", 12, 6));
        LR.add(new Row("P6", 15, 1));
        LR.add(new Row("P7", 17, 8));
        System.out.println(LR);
        Output Calc = ShortestJobFirst.Calc(LR);
        System.out.println("===================================");
        System.out.println(Calc);
        System.out.println("===================================");

    }
}
