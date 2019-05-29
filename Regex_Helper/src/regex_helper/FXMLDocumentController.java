/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex_helper;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import java.util.regex.*;
import javafx.beans.binding.BooleanBinding;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.scene.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author freez
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label regEx_label;
    @FXML
    private Label string_label;
    @FXML
    private Label match_label;
    @FXML
    private Label newString_label;
    @FXML
    private TextField regEx_text;
    @FXML
    private TextArea string_text;
    @FXML
    private TextField match_text;
    @FXML
    private TextArea newString_text;
    @FXML
    private Button filter_button;
    @FXML
    private Button reset_button;
    @FXML
    private Menu help_button;
    @FXML
    private MenuItem cheatSheet_button;
    @FXML
    private Label matchCount;
    @FXML
    private MenuItem about_button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //boolean binding to filter button, requires all text fields to be filled to enable the filter button
        BooleanBinding bb = new BooleanBinding(){
            {
              super.bind(regEx_text.textProperty(), string_text.textProperty(),match_text.textProperty());  //join text fields to binding
            }
            @Override
            protected boolean computeValue(){
                return (regEx_text.getText().isEmpty() || string_text.getText().isEmpty() || match_text.getText().isEmpty());   //check state of texts fields
            }
        };
        filter_button.disableProperty().bind(bb);
    }

    

    @FXML
    private void filterButtonPressed(ActionEvent event) {
        //get text
        String text = string_text.getText();    //string storing the text to be changed
        StringBuffer sb = new StringBuffer();   //string buffer to hold characters for replacement
        
        try{
            Pattern regex = Pattern.compile(regEx_text.getText());  //regex pattern
            Matcher m = regex.matcher(string_text.getText());       //what to replace matches with
            String replacement = match_text.getText();              //string storing replaced string
            
            int count = 0; //initalize match count to zero
        
            if(m.find()){   //complete if there is a match
                m.appendReplacement(sb, replacement);       //append asjusted string 
                count++;                                    //increase match count
                while (m.find()){ //continue while a match is found
                    m.appendReplacement(sb, replacement);   //setString buffer to replacement
                    count++;                                //increase match count
                }
                matchCount.setText(Integer.toString(count));    //set number of matches
                newString_text.setText(sb.toString()); 
            }
            else{   //no match found
                matchCount.setText("None found");   //state no matches foudn
                newString_text.setText(text);   //set replaced text field to original text
            }
        } catch(java.util.regex.PatternSyntaxException e){  //catch syntax error of regex expression
            Alert alert = new Alert(Alert.AlertType.ERROR, "Uh oh, something happened!\n"
                    + "Check your regex syntax.");
            alert.showAndWait();    //display error message
        }
    }
   
    
    @FXML   //clear text fields and set focus to first text field
    private void resetPressed(ActionEvent event) {
        regEx_text.requestFocus(); 
        regEx_text.clear(); 
        string_text.clear(); 
        match_text.clear(); 
        newString_text.clear(); 
        matchCount.setText("NA");
    }
    
    @FXML   
    private void cheatSheetPressed(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://rexegg.com/regex-quickstart.html"));   //opens link in native browser to a regex cheat sheet
        
    }

    @FXML
    private void aboutButtonPressed(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/regex_helper/AboutWindow.fxml"));
            Stage aboutStage = new Stage();
            Parent root1 = (Parent) fxmlLoader.load();
            aboutStage.setTitle("About");
            aboutStage.setScene(new Scene(root1));
            aboutStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
