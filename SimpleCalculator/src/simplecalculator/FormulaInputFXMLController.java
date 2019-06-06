/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplecalculator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    private Consumer<String> onComplete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    void initData(Consumer<String> _onComplete){
        this.onComplete = _onComplete;
    }

    @FXML
    private void calcuatePressed(ActionEvent event) throws IOException {
        onComplete.accept(formulaField.getText().trim());
        Stage stage = (Stage)calcBttn.getScene().getWindow();
        stage.close();
    }
    
}
