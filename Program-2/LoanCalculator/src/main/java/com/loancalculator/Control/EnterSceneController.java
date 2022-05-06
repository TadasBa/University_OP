package com.loancalculator.Control;
//-----------------------------------------------------------------------------------------------------
import com.loancalculator.Action.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//-----------------------------------------------------------------------------------------------------
public class EnterSceneController {
    @FXML
    public TextField TFamount, TFinterest, TFyears, TFmonths, TFstart, TFend, TFfrom, TFtill, TFdeferInterest;
    @FXML
    private CheckBox CBlinear, CBannuity;

    public double amount, interest;
    public int term;

    public int endMonth;
    public int startMonth;

    public int fromMonth;
    public int tillMonth;
    public double deferInterest;

    private UserData values;
    private Mortgage mortgage = new Type_Linear(0, 0, 0);
//-----------------------------------------------------------------------------------------------------
    public void setValues() {
        try {
            amount = Double.parseDouble(TFamount.getText());
            interest = Double.parseDouble(TFinterest.getText()) / (12 * 100);
            term = Integer.parseInt(TFyears.getText()) * 12 + Integer.parseInt(TFmonths.getText());
            startMonth = Integer.parseInt(TFstart.getText());
            endMonth = Integer.parseInt(TFend.getText());
            fromMonth = Integer.parseInt(TFfrom.getText());
            tillMonth = Integer.parseInt(TFtill.getText());
            deferInterest = Double.parseDouble(TFdeferInterest.getText()) / (12*100);

        } catch (Exception e) {
            System.out.println();
        }

        setMortgageType();
        this.values = new UserData(
                endMonth, startMonth,
                fromMonth, tillMonth, deferInterest * (12 * 100),
                amount, interest * (12 * 100), term / 12, term % 12, mortgage.getClass().getSimpleName());
    }
//-----------------------------------------------------------------------------------------------------
    public void getValues(UserData values) {
        TFamount.setText(String.valueOf(values.getAmount()));
        TFinterest.setText(String.valueOf(values.getInterest()));
        TFyears.setText(String.valueOf(values.getTermYears()));
        TFmonths.setText(String.valueOf(values.getTermMonths()));
        TFstart.setText(String.valueOf(values.getStartMonth()));
        TFend.setText(String.valueOf(values.getEndMonth()));
        TFfrom.setText(String.valueOf(values.getFromMonth()));
        TFtill.setText(String.valueOf(values.getTillMonth()));
        TFdeferInterest.setText(String.valueOf(values.getDeferInterest()));

        getMortgageType(values.getType());
    }
//-----------------------------------------------------------------------------------------------------
    public void getMortgageType(String type) {
        if (type.equals("Annuity")) {
            CBannuity.setSelected(true);
        } else if (type.equals("Linear")) {
            CBlinear.setSelected(true);
        }
    }
//-----------------------------------------------------------------------------------------------------
    public void setMortgageType() {
        if (CBlinear.isSelected()) {
            mortgage = new Type_Linear(amount, term, interest);

        } else if (CBannuity.isSelected()) {
            mortgage = new Type_Annuity(amount, term, interest);
        }

        mortgage.calculate(fromMonth, tillMonth, deferInterest);
    }
//-----------------------------------------------------------------------------------------------------
    public void switchToTableScene(ActionEvent event) throws IOException {
        setValues();

        FXMLLoader tableLoader = new FXMLLoader(getClass().getResource("/com/loancalculator/TableScene.fxml"));
        Parent root = tableLoader.load();

        TableSceneController tableController = tableLoader.getController();
        tableController.values = this.values;
        tableController.fillTable(mortgage.getAllPayments());

        if(startMonth != 0 || endMonth != 0){
            tableController.fillTable(mortgage.getAllPayments(startMonth-1, endMonth));
        }else{
            tableController.fillTable(mortgage.getAllPayments());
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
//-----------------------------------------------------------------------------------------------------
    public void switchToGraphScene(ActionEvent event) throws IOException {
       setValues();

        FXMLLoader currentLoader = new FXMLLoader(getClass().getResource("/com/loancalculator/GraphScene.fxml"));
        Parent root = currentLoader.load();

        GraphSceneController graphSceneController = currentLoader.getController();
        graphSceneController.userData = this.values;
        graphSceneController.setGraph(mortgage.getAllPayments(), startMonth, endMonth);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();

        stage.show();
    }
//-----------------------------------------------------------------------------------------------------
        public void exportToFile(ActionEvent event){
            setValues();

                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text documents (*.txt)", "*.txt"));

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                File file = fileChooser.showSaveDialog(stage);

                try {
                    FileWriter fileWriter = new FileWriter(file);

                    String type;
                    if(values.getType().equals("Type_Annuity")){
                        type = "Annuity";}
                    else type = "Linear";

                    fileWriter.write(String.format("""
                                    Your loan information:
                                    --------------------------
                                    Loan amount:    %.2f €
                                    Loan interest:  %.2f %%
                                    Loan type:      %s
                                    Loan term:      %d months
                                    --------------------------
                                    
                                    """,
                            values.getAmount(),
                            values.getInterest(),
                            type,
                            values.getTermYears()*12 + values.getTermMonths()));

                    for (int i = 0; i < mortgage.getAllPayments().size(); i++) {
                        TableData tableData = mortgage.getAllPayments().get(i);

                        fileWriter.write(String.format("""
                                        Month: %d,       Balance:   %.3f €,       Interest:  %.3f €,       Principal: %.3f €,       Payment:   %.3f €
                                        ----------------------------------------------------------------------------------------------------------------------------
                                        """,
                                tableData.getMonth(),
                                tableData.getRemainingLoanAmount(),
                                tableData.getInterest(),
                                tableData.getPrincipal(),
                                tableData.getPaymentAmount()
                                ));
                    }
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    //-----------------------------------------------------------------------------------------------------
    public void switchToStartScene(ActionEvent event) throws IOException {
        FXMLLoader currentLoader = new FXMLLoader(getClass().getResource("/com/loancalculator/StartScene.fxml"));
        Parent root = currentLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();

        stage.show();
    }
    //-----------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------
        }