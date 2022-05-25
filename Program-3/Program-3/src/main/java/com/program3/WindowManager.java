package com.program3;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public final class WindowManager {

    private static Stage stage;
    private static Scene scene;
    private static FXMLLoader loader;

    public WindowManager(Stage stage, String name, String fxmlFile) throws IOException {
        this.stage = stage;

        scene = new Scene(loadFxmlFile(fxmlFile));
        stage.setTitle(name);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFxmlFile(String fxmlFile) throws IOException {

        URL path = Main.class.getResource(fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader(path);
        loader = fxmlLoader;

        try{
            return fxmlLoader.load();
        }catch (Exception e){
            System.out.println("Failed to load " + path);
            throw e;
        }

    }

    public static void newWindow(String name, String fxmlFile) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle(name);
        newStage.setScene(new Scene(loadFxmlFile(fxmlFile)));
       // stage.setOnHidden(e -> newStage.hide());
        newStage.show();

    }

    public static FXMLLoader getLoader() {
        return loader;
    }
}
