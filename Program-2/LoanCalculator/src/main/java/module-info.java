module com.loancalculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;


    opens com.loancalculator.Control to javafx.fxml;
    opens com.loancalculator.Action to javafx.base;

    exports com.loancalculator;
}