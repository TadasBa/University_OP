package com.loancalculator.Control;
//-----------------------------------------------------------------------------------------------------
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
//-----------------------------------------------------------------------------------------------------
public class StartSceneController {
    @FXML
    private Label label;
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
    public void exit(){
        System.exit(0);
    }
    //-----------------------------------------------------------------------------------------------------
    @FXML
    protected void help(){
        label.setFont(new Font(13));
        label.setText("""
                You are currently using mortgage calculator
                -  If you would like to start press Enter
                -  If you would like to exit press Exit""");
    }
    //-----------------------------------------------------------------------------------------------------
}