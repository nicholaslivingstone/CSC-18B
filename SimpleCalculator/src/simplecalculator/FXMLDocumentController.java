//Nicholas Livingstone 2019
package simplecalculator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.Stack;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextInputDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class FXMLDocumentController implements Initializable {
    
    //FXML Items
    @FXML
    private Button clearBttn;
    @FXML
    private Button signBttn;
    @FXML
    private Button percentBttn;
    @FXML
    private Button divBttn;
    @FXML
    private Button seven;
    @FXML
    private Button eight;
    @FXML
    private Button nine;
    @FXML
    private Button multBttn;
    @FXML
    private Button four;
    @FXML
    private Button five;
    @FXML
    private Button six;
    @FXML
    private Button subBttn;
    @FXML
    private Button one;
    @FXML
    private Button two;
    @FXML
    private Button three;
    @FXML
    private Button addBttn;
    @FXML
    private Button zero;
    @FXML
    private Button decimalPnt;
    @FXML
    private Button equalBttn;
    @FXML
    private TextField display;
    @FXML
    private AnchorPane anchor; 
    @FXML
    private MenuItem binaryMI;
    @FXML
    private MenuItem hexMI;
    @FXML
    private MenuItem formulaMI;
    @FXML
    private MenuItem saveBttn;
    @FXML
    private MenuItem openBttn;
    
    //class variables
    double temp, result; 
    String currentNum = "",
            formula = "";
    Operation currentOp;
    boolean opPressed;
    private static ObjectOutputStream output;
    private static ObjectInputStream input; 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anchor.setOnKeyPressed((KeyEvent ev) -> {
            //listerner for all relevant keys
            switch(ev.getCode()){
                case DIGIT0:
                    zero.fire();
                    break;
                case DIGIT1:
                    one.fire();
                    break;
                case DIGIT2:
                    two.fire();
                    break;
                case DIGIT3:
                    three.fire();
                    break;
                case DIGIT4:
                    four.fire();
                    break;
                case DIGIT5:
                    five.fire();
                    break;
                case DIGIT6:
                    six.fire();
                    break;
                case DIGIT7:
                    seven.fire();
                    break;
                case DIGIT8:
                    eight.fire();
                    break;
                case DIGIT9:
                    nine.fire();
                    break;
                case EQUALS:
                case ENTER:
                    equalBttn.fire();
                    break;
                case MINUS:
                    subBttn.fire();
                    break;
                case PLUS:
                    addBttn.fire();
                    break;
                case SLASH:
                    divBttn.fire();
                    break;
                case ASTERISK:
                    multBttn.fire();
                    break;
                case PERIOD:
                    decimalPnt.fire();
            }
        });
    }
    
    //NUMBER BUTTONS
    @FXML
    private void press7(ActionEvent event) {
        if(opPressed){
            currentNum = "";
            opPressed = false;
        }
        currentNum += '7';
        updateDisplay(currentNum);
    }

    @FXML
    private void press8(ActionEvent event){
        if(opPressed){
            currentNum = "";
            opPressed = false;
        }
        currentNum += '8';
        updateDisplay(currentNum);
    }

    @FXML
    private void press9(ActionEvent event) {
        if(opPressed){
            currentNum = "";
            opPressed = false;
        }
        currentNum += '9';
        updateDisplay(currentNum);
    }


    @FXML
    private void press4(ActionEvent event) {
        if(opPressed){
            currentNum = "";
            opPressed = false;
        }
        currentNum += '4';
        updateDisplay(currentNum);
    }

    @FXML
    private void press5(ActionEvent event) {
        if(opPressed){
            currentNum = "";
            opPressed = false;
        }
        currentNum += '5';
        updateDisplay(currentNum);
    }

    @FXML
    private void press6(ActionEvent event) {
        if(opPressed){
            currentNum = "";
            opPressed = false;
        }
        currentNum += '6';
        updateDisplay(currentNum);
    }

   
    @FXML
    private void press1(ActionEvent event) {
        if(opPressed){
            currentNum = "";
            opPressed = false;
        }
        currentNum += '1';
        updateDisplay(currentNum);
    }

    @FXML
    private void press2(ActionEvent event) {
        if(opPressed){
            currentNum = "";
            opPressed = false;
        }
        currentNum += '2';
        updateDisplay(currentNum);
    }

    @FXML
    private void press3(ActionEvent event) {
        if(opPressed){
            currentNum = "";
            opPressed = false;
        }
        currentNum += '3';
        updateDisplay(currentNum);
    }

    

    @FXML
    private void press0(ActionEvent event) {
        if(opPressed){
            currentNum = "";
            opPressed = false;
        }
        currentNum += '0';
        updateDisplay(currentNum);
    }
    
    
    //OPERATIONAL BUTTONS
    
    //Clear calculator
    @FXML
    private void clearPress(ActionEvent event) {
        currentNum = "";
        updateDisplay(currentNum);
        temp = 0; 
        result = 0; 
    }

    
    
    //alternate sign button
    @FXML
    private void signPress(ActionEvent event) {
        if(getNum() == 0)
            return; 
        try{
        currentNum = Double.toString(getNum() * -1);
        updateDisplay(currentNum);
        }catch(NumberFormatException e){
            numberError();
        }
    }

    //create percent out of number
    @FXML
    private void percentPress(ActionEvent event) {
        try{
            currentNum = Double.toString(getNum() * .01);
            updateDisplay(currentNum);
        }catch(NumberFormatException e){
            numberError();
        }
    }

    
    //period button
    @FXML
    private void decPress(ActionEvent event) {
        if(display.getText().trim().isEmpty()){
            currentNum = "0.";
            updateDisplay(currentNum);
        }
        else{
            currentNum += ".";
            updateDisplay(currentNum);
        }
    }
    
    
    //MATH BUTTONS
    
    //MULTIPLY
    @FXML
    private void multPress(ActionEvent event) {
        currentOp = Operation.MUL;
        opCheck();
    }
    
    //DIVIDE
    @FXML
    private void divPress(ActionEvent event) {
        currentOp = Operation.DIV;
        opCheck();
    }
    
    //SUBTRACT
    @FXML
    private void subPress(ActionEvent event) {
        currentOp = Operation.SUB;
        opCheck(); 
    }
    
    //ADD
    @FXML
    private void addPress(ActionEvent event) {
        currentOp = Operation.ADD;
        opCheck();
    }
    
    //EQUALS
    @FXML
    private void equalPress(ActionEvent event) {
        try{
            result = currentOp.math(temp, getNum());
            currentNum = "";
            temp = 0;
            updateDisplay(result);
        }
        catch(NumberFormatException e){
            numberError();
        }
    }
    
    
    //INTERNAL FUNCTIONS
    
    //checks if it's currently in an operation state
    private void opCheck(){
        if(!opPressed){
            opPressed = true;
            temp = getNum();
        }
        else{
            opPressed = false; 
            equalBttn.fire();
        }
    }
    
    //complete a math operations
    private void completeOp(Operation _op){
        try{
            result = currentOp.math(result, getNum());
            currentNum = "";
            temp = 0;
            updateDisplay(result);
        }catch(NumberFormatException e){
            numberError();
        }
    }
    
    //get the current number from the display
    private double getNum(){
        return Double.parseDouble(display.getText());
    }

    //OVERLOADED update display using string
    private void updateDisplay(String _num){
        display.setText(_num);
    }
    
    //OVERLOADED UPDATE DISPLAY USING DOUBLE
    private void updateDisplay(Double _num){
        updateDisplay(Double.toString(_num));
    }
    
    //function to call if a number occurs, resets the calculator and present an error
    private void numberError(){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Error parsing number input, calculator has been reset.");
        alert.showAndWait();
        currentNum = "";
        updateDisplay(currentNum);
        temp = 0; 
        result = 0;
    }

    @FXML
    private void binPressed(ActionEvent event) {
        if(display.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nothing to convert.");
            alert.showAndWait();
            return; 
        }
        currentNum = "";
        temp = 0; 
        result = 0; 
        opPressed = true;
        int bin = (int)getNum();
        TextField text = new TextField(Integer.toBinaryString(bin));
        Stage stage = new Stage();
        stage.setTitle("Binary Value");
        stage.setScene(new Scene(text, 250, 25));
        text.setEditable(false);
        stage.setResizable(false);
        stage.show();
    }

    
    @FXML
    private void hexPressed(ActionEvent event) {
        if(display.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nothing to convert.");
            alert.showAndWait();
            return; 
        }
        currentNum = "";
        temp = 0; 
        result = 0; 
        opPressed = true;
        TextField text = new TextField(Double.toHexString(getNum()));
        Stage stage = new Stage();
        stage.setTitle("Hexedecimal Value");
        stage.setScene(new Scene(text, 250, 25));
        text.setEditable(false);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void formulaPressed(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/simplecalculator/FormulaInputFXML.fxml"));
            Stage formStage = new Stage(); 
            Parent root1 = (Parent) fxmlLoader.load();
            formStage.setTitle("Formula Input");
            formStage.setScene(new Scene(root1));
            Consumer<String> onComplete = _formula -> {   //consumer to activate when user enters formula in popup dialog
                formula = _formula; 
                updateDisplay(calcFormula(formula));
            };
            FormulaInputFXMLController controller = fxmlLoader.<FormulaInputFXMLController>getController();
            controller.initData(onComplete);
            formStage.show();
    }
    
    @SuppressWarnings("CallToPrintStackTrace")
    private String calcFormula(String formula){
        List<String> formulaElements = new ArrayList<>();   //formula elements located by regex
        List<String> RPN = new ArrayList<>();               //list of converted polish notation        
        Stack<String> S = new Stack<>();                    //Stack for conversion and calculations
        String resultStr;                                   //string to store result
        
        //obtain each element of formula by regex
        try{
            Pattern regex = Pattern.compile("(\\(\\-\\d*\\))|-|\\*|\\)|\\/|\\(|\\+|\\d*?\\S");  //regex pattern
            Matcher m = regex.matcher(formula); //match pattern against the formula
            while(m.find()){
                formulaElements.add(m.group());
            }
        }catch(java.util.regex.PatternSyntaxException e){  //catch syntax error of regex expression
            e.printStackTrace();
        }
        
        // convert the elements to reverse polish notation
        try{
            //Prescendence of Operations
            Map<String, Integer> prededence = new HashMap<>();
            prededence.put("/", 5);
            prededence.put("*", 5);
            prededence.put("+", 4);
            prededence.put("-", 4);
            prededence.put("(", 0);
                    
            for(String item : formulaElements){
                if("(".equals(item)){
                    S.push(item);
                    continue; 
                }
                if(")".equals(item)){
                    while(!"(".equals(S.peek()))
                        RPN.add(S.pop());
                    S.pop();
                    continue; 
                }
                if (prededence.containsKey(item)) {
                    while (!S.empty() && prededence.get(item) <= prededence.get(S.peek())) {
                        RPN.add(S.pop());
                    }
                    S.push(item);
                    continue;
                }
                if(isNumber(item)){
                    RPN.add(item);
                    continue; 
                }
                throw new IllegalArgumentException("Invalid input");
            }
            while (!S.isEmpty()) {
                RPN.add(S.pop());
            }
        }catch(IllegalArgumentException e){
            e.printStackTrace();
            return "Syntax Error";
        }
        
        //Complete calculation
        String ops = "+-*/";    //stores operator symboles
        try{
            for(String item : RPN){
                if(!ops.contains(item)) //if it's a number, push it onto the stack
                    S.push(item);
                else{
                    double a = Double.valueOf(S.pop());
                    double b = Double.valueOf(S.pop());
                    switch(item){
                        case "+":
                            S.push(String.valueOf(a + b));
                            break;
                        case "-":
                            S.push(String.valueOf(b - a));
                            break;
                        case "*":
                            S.push(String.valueOf(a * b));
                            break;
                        case "/":
                            S.push(String.valueOf(b / a));
                    }
                }
            }
            
        resultStr = S.pop(); 
        result = Double.valueOf(resultStr);
        
        }catch(Exception e){
            e.printStackTrace();
            return "Syntax Error";
        }
        return resultStr; 
    }
    
    private static boolean isNumber(String str) {
        try{
            Double.valueOf(str);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    @FXML
    private void savePressed(ActionEvent event) {
        try {
//            TextInputDialog dialog = new TextInputDialog("Enter the file name to save as");
//            dialog.showAndWait();
              JFileChooser saver = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
              int r = saver.showSaveDialog(null); 
              if(r == JFileChooser.APPROVE_OPTION){
                    output = new ObjectOutputStream(Files.newOutputStream(Paths.get(saver.getSelectedFile().getAbsolutePath())));
                    output.writeObject(formula);
                    output.close();
              }
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error opening file");
            alert.showAndWait();
        }
    }

    @FXML
    private void openPressed(ActionEvent event) {
        try {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int choice = fileChooser.showOpenDialog(null);
            if(choice == JFileChooser.APPROVE_OPTION){
                input = new ObjectInputStream(Files.newInputStream(Paths.get(fileChooser.getSelectedFile().getAbsolutePath())));
                formula = input.readObject().toString();
                updateDisplay(calcFormula(formula));
                input.close();
            }
        }catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error opening file");
            alert.showAndWait();
        }catch (ClassNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    
    
