package gui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Cursor;

public class RowRecord extends cpu_scheduling.Row {

    public JFXButton delBTN = new JFXButton("Delete");

    public RowRecord(String processName, int arrivalTime, int burstTime) {
        super(processName, arrivalTime, burstTime);
        btnDecoration();
    }

    public JFXButton getDelBTN() {
        return delBTN;
    }

    public void setDelBTN(JFXButton delBTN) {
        this.delBTN = delBTN;
    }

    public final void btnDecoration() {
        delBTN.setStyle("-fx-background-color:#95ccff");
        delBTN.setCursor(Cursor.HAND);
    }
}
