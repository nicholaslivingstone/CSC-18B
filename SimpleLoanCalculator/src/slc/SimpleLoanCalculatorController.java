/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slc;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 *
 * @author freez
 */
public class SimpleLoanCalculatorController implements Initializable {
    
    private Label label;
    @FXML
    private Label amount_label;
    @FXML
    private Label interest_label;
    @FXML
    private Label terms_label;
    @FXML
    private Label payments_label;
    @FXML
    private TextField amount_text_field;
    @FXML
    private Slider interest_slider;
    @FXML
    private ChoiceBox<?> terms_selection;
    @FXML
    private TextField Payments_Text_Field;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        interest_label.setText(String.format("Interest %.2f", interest_slider.getValue()));
        
    }    
    
}
