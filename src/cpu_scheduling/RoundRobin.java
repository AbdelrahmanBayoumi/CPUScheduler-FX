package cpu_scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoundRobin {

    private static List<Row> filter(List<Row> process, int arrival_time) {
        List<Row> processes_out = new ArrayList<>();
        process.stream().filter((i) -> (i.getArrivalTime() <= arrival_time && i.getRemainingTime() != 0)).forEachOrdered((i) -> {
            processes_out.add(i);
        });
        Utility.sortBrust(processes_out);
        return processes_out;
    }

    private static void sortchartarrivale(List<Row> L) {
        Collections.sort(L, (Object o1, Object o2) -> {
            if (((Row) o1).getChartarrival() == ((Row) o2).getChartarrival()) {
                return 0;
            } else if (((Row) o1).getChartarrival() < ((Row) o2).getChartarrival()) {
                return -1;
            } else {
                return 1;
            }
        });
    }

    public static Output Calc(List<Row> input_process, int QuantumTime) {
        int time = input_process.get(0).getArrivalTime();
        List<Row> tempList1 = new ArrayList<>();
        while (true) {
            List<Row> tempList2 = filter(input_process, time);
            if (tempList1.size() == input_process.size()) {
                break;
            }
            if (tempList2.isEmpty()) {
                time++;
            } else {
                sortchartarrivale(tempList2);
                if (tempList2.get(0).getRemainingTime() >= QuantumTime) {
                    time += QuantumTime;
                    tempList2.get(0).setRemainingTime(tempList2.get(0).getRemainingTime() - QuantumTime);
                    tempList2.get(0).setChartarrival(time);
                    if (tempList2.get(0).getRemainingTime() == 0) {
                        tempList2.get(0).setFinishTime(time);
                        tempList1.add(tempList2.get(0));
                    }
                } else {
                    time += tempList2.get(0).getRemainingTime();
                    tempList2.get(0).setRemainingTime(0);
                    if (tempList2.get(0).getRemainingTime() == 0) {
                        tempList2.get(0).setFinishTime(time);
                        tempList1.add(tempList2.get(0));
                    }
                }
            }
        }
        for (int i = 0; i < tempList1.size(); i++) {
            tempList1.get(i).setTurnaroundTime(tempList1.get(i).getFinishTime() - tempList1.get(i).getArrivalTime());
            tempList1.get(i).setWaitingTime(tempList1.get(i).getTurnaroundTime() - tempList1.get(i).getBurstTime());
        }
        Utility.sortArrival(tempList1);
        double avg_w = 0;
        double avg_t = 0;
        for (Row i : tempList1) {
            avg_w += i.getWaitingTime();
            avg_t += i.getTurnaroundTime();
        }
        return new Output(tempList1, Utility.FormatDouble(avg_w / tempList1.size()), Utility.FormatDouble(avg_t / tempList1.size()));
    }

    public static void main(String[] args) {
        List<Row> LR = new ArrayList();
        LR.add(new Row("P1", 0, 3));
        LR.add(new Row("P2", 3, 7));
        LR.add(new Row("P3", 4, 2));
        LR.add(new Row("P4", 8, 5));
        LR.add(new Row("P5", 12, 6));
        LR.add(new Row("P6", 15, 1));
        LR.add(new Row("P7", 17, 8));
        System.out.println(LR);
        System.out.println("===================================");
        System.out.println(Calc(LR, 3));
        System.out.println("===================================");

    }
}
