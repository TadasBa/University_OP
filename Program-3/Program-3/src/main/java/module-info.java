module com.program3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.apache.poi.ooxml;
    requires itextpdf;

    opens com.program3 to javafx.fxml;
    exports com.program3;
    exports com.program3.Data;
    opens com.program3.Data to javafx.fxml;
    exports com.program3.Controllers;
    opens com.program3.Controllers to javafx.fxml;
    exports com.program3.FileManager;
    opens com.program3.FileManager to javafx.fxml;
}