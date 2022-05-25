package com.program3.FileManager;


import com.program3.Data.DataSet;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public abstract class FileLoader {

    public File file;

    public FileLoader(Stage stage, String title, String iFileName, boolean save){
        file = showDialog(stage, title, iFileName, save);
    }

    public abstract javafx.stage.FileChooser.ExtensionFilter[] getFileExtensions();

    public File showDialog(Stage stage, String title, String iFileName, boolean save){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.setInitialFileName(iFileName);
        fileChooser.getExtensionFilters().addAll(getFileExtensions());
        File file;
        if(save){
            file = fileChooser.showSaveDialog(stage);
        }else{
            file = fileChooser.showOpenDialog(stage);
        }

        return file;
    }

}
