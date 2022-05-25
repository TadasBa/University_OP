// Tadas Baltrūnas
// 2110535
// program 3
// 2022.05

package com.program3;

import com.program3.Controllers.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static MainController mainController;
    public void start(Stage stage) throws IOException {

        try{
            new WindowManager(stage, "Students", "main.fxml");
            mainController = WindowManager.getLoader().getController();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            Platform.exit();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}