package com.loancalculator.Control;
//-----------------------------------------------------------------------------------------------------
import com.loancalculator.Action.TableData;
import com.loancalculator.Action.UserData;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.io.IOException;
//-----------------------------------------------------------------------------------------------------
public class GraphSceneController {

@FXML
private LineChart<Integer, Double> lineChart;

@FXML
private NumberAxis XAxis;

@FXML
private NumberAxis YAxis;

public UserData userData;
    //-----------------------------------------------------------------------------------------------------
    public void setGraph(ObservableList<TableData> tableData, int startMonth, int endMonth){
        XYChart.Series<Integer, Double> series = new XYChart.Series<>();

        for(TableData data : tableData){
            series.getData().add(new XYChart.Data(data.getMonth(), data.getPaymentAmount()));
        }

        if(startMonth == 0 && endMonth == 0){
            setXAxisRange(0, tableData.get(tableData.size()-1).getMonth());
        }else{
            setXAxisRange(startMonth, endMonth);
        }

        XAxis.setLowerBound(0);
        XAxis.setUpperBound(tableData.get(tableData.size()-1).getMonth());
        YAxis.setLowerBound(0);
        YAxis.setUpperBound(tableData.get(0).getPaymentAmount() * 2);
        lineChart.getData().clear();
        lineChart.getData().add(series);
    }
    //-----------------------------------------------------------------------------------------------------
    private void setXAxisRange(int from, int to){
        XAxis.setLowerBound(from);
        XAxis.setUpperBound(to);
    }
    //-----------------------------------------------------------------------------------------------------
    public void switchToEnterScene(ActionEvent event) throws IOException {
        FXMLLoader currentLoader = new FXMLLoader(getClass().getResource("/com/loancalculator/EnterScene.fxml"));
        Parent root = currentLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();

        stage.show();
    }
//-----------------------------------------------------------------------------------------------------
}
