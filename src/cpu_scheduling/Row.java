package cpu_scheduling;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import gui.RowRecord;

public class Row extends RecursiveTreeObject<RowRecord> {

    private String processName;
    private int arrivalTime;
    private int burstTime;
    private int startTime;
    private int finishTime;
    private int priorityLevel;
    private int waitingTime;
    private int turnaroundTime;
    private int Chartarrival;
    private int RemainingTime;

    private Row(String processName, int arrivalTime, int burstTime, int startTime, int finishTime, int priorityLevel, int waitingTime, int turnaroundTime) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.priorityLevel = priorityLevel;
        this.waitingTime = waitingTime;
        this.turnaroundTime = turnaroundTime;
        this.Chartarrival = arrivalTime;
        this.RemainingTime = burstTime;
    }

    public Row(String processName, int arrivalTime, int burstTime, int priorityLevel) {
        this(processName, arrivalTime, burstTime, 0, 0, priorityLevel, 0, 0);
    }

    public Row(String processName, int arrivalTime, int burstTime) {
        this(processName, arrivalTime, burstTime, 0, 0, 0, 0, 0);
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public String getProcessName() {
        return this.processName;
    }

    public int getArrivalTime() {
        return this.arrivalTime;
    }

    public int getBurstTime() {
        return this.burstTime;
    }

    public int getPriorityLevel() {
        return this.priorityLevel;
    }

    public int getWaitingTime() {
        return this.waitingTime;
    }

    public int getTurnaroundTime() {
        return this.turnaroundTime;
    }

    public int getRemainingTime() {
        return RemainingTime;
    }

    public void setRemainingTime(int RemainingTime) {
        this.RemainingTime = RemainingTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getChartarrival() {
        return Chartarrival;
    }

    public void setChartarrival(int Chartarrival) {
        this.Chartarrival = Chartarrival;
    }

    @Override
    public String toString() {
        return "\nRow{" + "\n\tprocessName=" + processName + "\n\tarrivalTime=" + arrivalTime
                + "\n\tburstTime=" + burstTime + "\n\tstartTime=" + startTime
                + "\n\tfinishTime=" + finishTime + "\n\tpriorityLevel=" + priorityLevel
                + "\n\twaitingTime=" + waitingTime + "\n\tturnaroundTime=" + turnaroundTime + "\n}";
    }

    public static void main(String[] args) {
        Row r = new Row("", 0, 0);
        System.out.println(r);
    }
}
