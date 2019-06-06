<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarycalculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;


public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private MenuItem saveBttn;
    @FXML
    private MenuItem openBttn;
    @FXML
    private MenuItem clearBttn;
    @FXML
    private MenuItem aboutBttn;
    @FXML
    private TextField decTF;
    @FXML
    private TextField binTF;
    @FXML
    private TextField hexTF;
    @FXML
    private TextArea formulaTF;
    @FXML
    private Button equalBttn;
    @FXML
    private Button addBttn;
    @FXML
    private Button subBttn;
    @FXML
    private Button multBttn;
    @FXML
    private Button divBttn;
    
    enum Operation{
        ADD{
            @Override
            public int math(int _one, int _two){
                return _one + _two; 
            }
        }, SUB{
            @Override
            public int math(int _one, int _two){
                return _one - _two; 
            }
        }, MUL{
            @Override
            public int math(int _one, int _two){
                return _one * _two; 
            }
        }, DIV{
            @Override
            public int math(int _one, int _two){
                return _one / _two; 
            }
        };
        
        public abstract int math(int _one, int _two); 
    }
    
    
    
    Operation currentOp; 
    
    int result = 0,
            temp,
            calcCount = 0; //to determine if a calculation has begun
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        decTF.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            String setBin = "", setHex = "";
            try{
                if(!decTF.getText().trim().isEmpty()){
                setBin = Integer.toBinaryString(getDec());
                setHex = Integer.toHexString(getDec());
                }
            }catch(NumberFormatException e){
                binTF.setText(setBin);
                hexTF.setText(setHex);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Somethings not right, check you syntax...");
                alert.showAndWait();
            }
            binTF.setText(setBin);
            hexTF.setText(setHex);
        });
    }
    
    private void clearTxt(){
        decTF.clear();
        formulaTF.clear();
    }

    @FXML
    private void savePressed(ActionEvent event) {
    }

    @FXML
    private void openPressed(ActionEvent event) {
    }

    @FXML
    private void clearPressed(ActionEvent event) {
        result = 0; 
        calcCount = 0; 
        clearTxt();
    }

    @FXML
    private void aboutPressed(ActionEvent event) {
    }

    @FXML
    private void equalPressed(ActionEvent event) {
        switch(currentOp){
            case ADD:
                result = temp + getDec();
                break;
            case SUB:
                result -= temp + getDec();
                break;
            case MUL:
                result *= temp + getDec();
                break;
            case DIV:
                result /= temp + getDec();
                break;
        }
        decTF.setText(Integer.toString(result));
    }

    @FXML
    private void addPressed(ActionEvent event) {
        currentOp = Operation.ADD;
        completeOp(currentOp);
    }

    @FXML
    private void subPressed(ActionEvent event) {
        currentOp = Operation.SUB;
        completeOp(currentOp);
    }

    @FXML
    private void mulPressed(ActionEvent event) {
        currentOp = Operation.MUL;
        completeOp(currentOp);
    }

    @FXML
    private void divPressed(ActionEvent event) {
        currentOp = Operation.DIV;
        completeOp(currentOp);
    }
    
    private void completeOp(Operation _op){
        switch (calcCount) {
            case 0:
                calcCount++;
                temp = getDec();
                decTF.clear();
                break;
            case 1:
                calcCount++;
                result = _op.math(temp, getDec());
                decTF.setText(Integer.toString(result));
                break;
            default:
                calcCount++;
                temp = getDec();
                result = _op.math(result, getDec());
                decTF.setText(Integer.toString(result));
                break;
        }
    }
    
    private int getDec(){
        return Integer.parseInt(decTF.getText());
    }
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarycalculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;


public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private MenuItem saveBttn;
    @FXML
    private MenuItem openBttn;
    @FXML
    private MenuItem clearBttn;
    @FXML
    private MenuItem aboutBttn;
    @FXML
    private TextField decTF;
    @FXML
    private TextField binTF;
    @FXML
    private TextField hexTF;
    @FXML
    private TextArea formulaTF;
    @FXML
    private Button equalBttn;
    @FXML
    private Button addBttn;
    @FXML
    private Button subBttn;
    @FXML
    private Button multBttn;
    @FXML
    private Button divBttn;
    
    enum Operation{
        ADD{
            @Override
            public int math(int _one, int _two){
                return _one + _two; 
            }
        }, SUB{
            @Override
            public int math(int _one, int _two){
                return _one - _two; 
            }
        }, MUL{
            @Override
            public int math(int _one, int _two){
                return _one * _two; 
            }
        }, DIV{
            @Override
            public int math(int _one, int _two){
                return _one / _two; 
            }
        };
        
        public abstract int math(int _one, int _two); 
    }
    
    
    
    Operation currentOp; 
    
    int result = 0,
            temp,
            calcCount = 0; //to determine if a calculation has begun
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        decTF.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            String setBin = "", setHex = "";
            try{
                if(!decTF.getText().trim().isEmpty()){
                setBin = Integer.toBinaryString(getDec());
                setHex = Integer.toHexString(getDec());
                }
            }catch(NumberFormatException e){
                binTF.setText(setBin);
                hexTF.setText(setHex);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Somethings not right, check you syntax...");
                alert.showAndWait();
            }
            binTF.setText(setBin);
            hexTF.setText(setHex);
        });
    }
    
    private void clearTxt(){
        decTF.clear();
        formulaTF.clear();
    }

    @FXML
    private void savePressed(ActionEvent event) {
    }

    @FXML
    private void openPressed(ActionEvent event) {
    }

    @FXML
    private void clearPressed(ActionEvent event) {
        result = 0; 
        calcCount = 0; 
        clearTxt();
    }

    @FXML
    private void aboutPressed(ActionEvent event) {
    }

    @FXML
    private void equalPressed(ActionEvent event) {
        switch(currentOp){
            case ADD:
                result = temp + getDec();
                break;
            case SUB:
                result -= temp + getDec();
                break;
            case MUL:
                result *= temp + getDec();
                break;
            case DIV:
                result /= temp + getDec();
                break;
        }
        decTF.setText(Integer.toString(result));
    }

    @FXML
    private void addPressed(ActionEvent event) {
        currentOp = Operation.ADD;
        completeOp(currentOp);
    }

    @FXML
    private void subPressed(ActionEvent event) {
        currentOp = Operation.SUB;
        completeOp(currentOp);
    }

    @FXML
    private void mulPressed(ActionEvent event) {
        currentOp = Operation.MUL;
        completeOp(currentOp);
    }

    @FXML
    private void divPressed(ActionEvent event) {
        currentOp = Operation.DIV;
        completeOp(currentOp);
    }
    
    private void completeOp(Operation _op){
        switch (calcCount) {
            case 0:
                calcCount++;
                temp = getDec();
                decTF.clear();
                break;
            case 1:
                calcCount++;
                result = _op.math(temp, getDec());
                decTF.setText(Integer.toString(result));
                break;
            default:
                calcCount++;
                temp = getDec();
                result = _op.math(result, getDec());
                decTF.setText(Integer.toString(result));
                break;
        }
    }
    
    private int getDec(){
        return Integer.parseInt(decTF.getText());
    }
}
>>>>>>> 849669344663504afdfec92dbb6312b2e02de94d
