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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
//-----------------------------------------------------------------------------------------------------
public class TableSceneController {

    public UserData values;
    @FXML
    private TableView<TableData> table;
    @FXML
    private TableColumn <TableData, Integer> monthCol;
    @FXML
    private TableColumn <TableData, Double> paymentAmountCol;
    @FXML
    private TableColumn <TableData, Double> principalCol;
    @FXML
    private TableColumn <TableData, Double>  interestCol;
    @FXML
    private TableColumn <TableData, Double> remainingAmountCol;
//-----------------------------------------------------------------------------------------------------
    public void fillTable(ObservableList<TableData> allPayments){
        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        paymentAmountCol.setCellValueFactory(new PropertyValueFactory<>("paymentAmount"));
        principalCol.setCellValueFactory(new PropertyValueFactory<>("principal"));
        interestCol.setCellValueFactory(new PropertyValueFactory<>("interest"));
        remainingAmountCol.setCellValueFactory(new PropertyValueFactory<>("remainingLoanAmount"));

        table.setItems(allPayments);
    }
//-----------------------------------------------------------------------------------------------------
    public void switchToMainScene(ActionEvent event) throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/com/loancalculator/EnterScene.fxml"));
        Parent root = mainLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();

        EnterSceneController enterController = mainLoader.getController();
        enterController.getValues(this.values);

        stage.show();
    }
    //-----------------------------------------------------------------------------------------------------
}
