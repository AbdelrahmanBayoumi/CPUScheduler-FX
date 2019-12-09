package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import cpu_scheduling.FirstComeFirstServe;
import cpu_scheduling.Output;
import cpu_scheduling.RoundRobin;
import cpu_scheduling.Row;
import cpu_scheduling.ShortestJobFirst;
import cpu_scheduling.ShortestRemainingTime;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abdelrahman Bayoumi
 */
public class FXMLController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private TextField processName;
    @FXML
    private TextField ArrivalTime;
    @FXML
    private TextField BurstTime;
    @FXML
    private JFXTreeTableView<RowRecord> Table;
    @FXML
    private TreeTableColumn<RowRecord, String> processNameCol;
    @FXML
    private TreeTableColumn<RowRecord, Integer> ArrivalTimeCol;
    @FXML
    private TreeTableColumn<RowRecord, Integer> BurstTimeCol;
    @FXML
    private TreeTableColumn<RowRecord, JFXButton> deleteBTN;
    @FXML
    private ToggleGroup Algorithm;
    @FXML
    private Label AvgWaitingTimeLabel;
    @FXML
    private Label AvgTurnaroundTimeLabel;

    ObservableList<RowRecord> data = FXCollections.observableArrayList();
    @FXML
    private TextField QuantumTimeTextField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("FXMLController -> initialize() ...");
        processNameCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("processName"));
        ArrivalTimeCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("arrivalTime"));
        BurstTimeCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("burstTime"));
        deleteBTN.setCellValueFactory(new TreeItemPropertyValueFactory<>("delBTN"));
        processNameCol.setStyle("-fx-alignment: center;");
        ArrivalTimeCol.setStyle("-fx-alignment: center;");
        BurstTimeCol.setStyle("-fx-alignment: center;");
        deleteBTN.setStyle("-fx-alignment: center;");
        TreeItem<RowRecord> root = new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);
        Table.setRoot(root);
        Table.setShowRoot(false);

        QuantumTimeTextField.setDisable(true);
    }

    private List<Row> change(ObservableList<RowRecord> input) {
        List<Row> r = new ArrayList<>();
        for (RowRecord i : input) {
            r.add(new Row(i.getProcessName(), i.getArrivalTime(), i.getBurstTime()));
        }
        return r;
    }

    @FXML
    private void AddProcessAction(ActionEvent event) {
        System.out.println("FXMLController -> AddProcessAction() ...");
        try {
            RowRecord r = new RowRecord(processName.getText(), Integer.parseInt(ArrivalTime.getText()),
                    Integer.parseInt(BurstTime.getText()));
            r.delBTN.setOnAction((e) -> {
                data.remove(r);
            });
            data.add(r);
            processName.setText("");
            ArrivalTime.setText("");
            BurstTime.setText("");
            TreeItem<RowRecord> root = new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);
            Table.setRoot(root);
            Table.setShowRoot(false);
        } catch (Exception e) {
            System.out.println("Exception in FXMLController -> AddProcessAction() : " + e);
        }
    }

    @FXML
    private void ComputeAction(ActionEvent event) {
        System.out.println("FXMLController -> ComputeAction() ...");
        String AlgorithmValue = ((RadioButton) Algorithm.getSelectedToggle()).getText();
        System.out.println("AlgorithmValue : " + AlgorithmValue);
        System.out.println("data : " + data);
        if (data.isEmpty()) {
            return;
        }
        if (null == AlgorithmValue) {
            System.out.println("ERROR");
        } else {
            switch (AlgorithmValue) {
                case "FCFS":
                    Output fcfs = FirstComeFirstServe.Calc(change(data));
                    AvgWaitingTimeLabel.setText(fcfs.getAvg_waiting() + "");
                    AvgTurnaroundTimeLabel.setText(fcfs.getAvg_turnaround() + "");
                    System.out.println("FirstComeFirstServe.Calc(data) : " + fcfs);
                    break;
                case "SJF":
                    Output sjf = ShortestJobFirst.Calc(change(data));
                    AvgWaitingTimeLabel.setText(sjf.getAvg_waiting() + "");
                    AvgTurnaroundTimeLabel.setText(sjf.getAvg_turnaround() + "");
                    System.out.println("ShortestJobFirst.Calc(data) : " + sjf);
                    break;
                case "STRF":
                    Output srtf = ShortestRemainingTime.Calc(change(data));
                    AvgWaitingTimeLabel.setText(srtf.getAvg_waiting() + "");
                    AvgTurnaroundTimeLabel.setText(srtf.getAvg_turnaround() + "");
                    System.out.println("ShortestRemainingTime.Calc(data) : " + srtf);
                    break;
                case "RR":
                    int q;
                    try {
                        q = Integer.parseInt(QuantumTimeTextField.getText());
                    } catch (Exception e) {
                        break;
                    }
                    Output rr = RoundRobin.Calc(change(data), q);
                    AvgWaitingTimeLabel.setText(rr.getAvg_waiting() + "");
                    AvgTurnaroundTimeLabel.setText(rr.getAvg_turnaround() + "");
                    System.out.println("RoundRobin.Calc(data) : " + rr);
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }
        }

    }

    @FXML
    private void CloseAction(Event event) {
        System.out.println("FXMLController -> CloseAction() ...");
        ((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
    }

    @FXML
    private void RootMousePressed(Event event) {
        MouseEvent e = (MouseEvent) event;
        xOffset = e.getSceneX();
        yOffset = e.getSceneY();
    }

    @FXML
    private void RootMouseDragged(Event event) {
        MouseEvent e = (MouseEvent) event;
        ((Stage) (((Node) (event.getSource())).getScene().getWindow())).setX(e.getScreenX() - xOffset);
        ((Stage) (((Node) (event.getSource())).getScene().getWindow())).setY(e.getScreenY() - yOffset);
    }

    @FXML
    private void radioHandle(Event event) {
        if (((RadioButton) Algorithm.getSelectedToggle()).getText().equals("RR")) {
            QuantumTimeTextField.setDisable(false);
        } else {
            QuantumTimeTextField.setDisable(true);
        }

    }
}
