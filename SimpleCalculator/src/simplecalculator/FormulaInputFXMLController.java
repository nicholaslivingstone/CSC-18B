/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplecalculator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author freez
 */
public class FormulaInputFXMLController implements Initializable {

    @FXML
    private TextField formulaField;
    @FXML
    private Button calcBttn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void calcuatePressed(ActionEvent event) {
        try{
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/simplecalculator/FXMLDocument.fxml"));
        fxmlLoader.load();
        FXMLDocumentController controller = fxmlLoader.getController();
        controller.returnFormula(formulaField.getText());
        Stage stage = (Stage)calcBttn.getScene().getWindow();
        stage.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
