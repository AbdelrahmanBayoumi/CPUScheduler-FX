package cpu_scheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CPU_Scheduling
 *
 * @author Abdelrahman Bayoumi
 */
public class CPU_Scheduling {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        List<Row> list = new ArrayList();
        System.out.println("Enter number of processes : ");
        int n = sc.nextInt();
        if (n == 0) {
            System.out.println("No process !");
            return;
        }
        for (int i = 1; i <= n; i++) {
            System.out.println("( " + i + " )  Enter ProcessName, ArrialTime and BurstTime: ");
            list.add(new Row(sc.next(), sc.nextInt(), sc.nextInt()));
        }
        int x;
        int flag = 1;
        while (flag == 1) {
            System.out.println("Choose Algorithm: ");
            System.out.println("1- FCFS.");
            System.out.println("2- SJF.");
            System.out.println("3- SRJF.");
            System.out.println("4- RR.");
            System.out.println("Enter (0) to Exit.");
            x = sc.nextInt();
            switch (x) {
                case 1:
                    System.out.println(FirstComeFirstServe.Calc(list));
                    break;
                case 2:
                    System.out.println(ShortestJobFirst.Calc(list));
                    break;
                case 3:
                    System.out.println(ShortestRemainingTime.Calc(list));
                    break;
                case 4:
                    System.out.println("Enter Quantum Time: ");
                    System.out.println(RoundRobin.Calc(list, sc.nextInt()));
                    break;
                case 0:
                    return;
                default:
                    System.out.println("ERROR !");
            }
        }
    }

}
