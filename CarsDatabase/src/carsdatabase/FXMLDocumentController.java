/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsdatabase;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;
import javafx.scene.control.ButtonType;
import java.lang.String;

/**
 *
 * @author freez
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button prevBttn;
    @FXML
    private TextField currentIndexTF;
    @FXML
    private TextField totalIndexTF;
    @FXML
    private Button nextBttn;
    @FXML
    private TextField IDTF;
    @FXML
    private TextField makeTF;
    @FXML
    private TextField modelTF;
    @FXML
    private TextField yearTF;
    @FXML
    private TextField mlgTF;
    @FXML
    private TextField modelSearchTF;
    @FXML
    private Button findBttn;
    @FXML
    private Button browseBttn;
    @FXML
    private Button insertBttn;
    @FXML
    private Button deleteBttn;
    
    CarQueries carQ = new CarQueries();    //databse query object
    List<Car> carList = new ArrayList<Car>();
    int carCount = 0,
            currentIndex = 1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carCount = carQ.getRowCount(); 
        totalIndexTF.setText(Integer.toString(carCount));
        if(carCount == 0){
            currentIndexTF.setText("0"); 
            Alert alert = new Alert(AlertType.INFORMATION, "The database is empty, you should add something!", ButtonType.CLOSE);
            alert.showAndWait();
            return; 
        }
        currentIndexTF.setText(Integer.toString(currentIndex)); 
        for(int i = 1; i <= carCount; i++){
            carList.add(carQ.getCar(i));
        }
        fillFields(carList.get(currentIndex - 1));
    }    

    @FXML
    private void prevPress(ActionEvent event) {
        if (carCount == 0)
            return; 
        currentIndex--;
         if(currentIndex < 1)
            currentIndex = carCount;
        fillFields(carList.get(currentIndex - 1));
        currentIndexTF.setText(Integer.toString(currentIndex));
    }

    @FXML
    private void nextPress(ActionEvent event) {
        if (carCount == 0)
            return;
        currentIndex++;
        if(currentIndex > carCount)
            currentIndex = 1;
        fillFields(carList.get(currentIndex - 1));
        currentIndexTF.setText(Integer.toString(currentIndex));
    }

    @FXML
    private void findPress(ActionEvent event) {
        String _model = modelSearchTF.getText();
        if(_model.isEmpty()){
            Alert alert = new Alert(AlertType.ERROR, "Uh Oh, somethings missing! Try typing in a model to search for.", ButtonType.CLOSE);
            alert.showAndWait();
            return; 
        }
        int foundIndex = 0; 
        boolean found = false; 
        for(int i = 0; i < carCount; i++){
            if(carList.get(i).getCarModel().equalsIgnoreCase(_model)){
                found = true;
                foundIndex = i;
            }
        }
        if(found){
            updateFields(foundIndex + 1);
        }
        else{
            Alert alert = new Alert(AlertType.INFORMATION, "No cars found with that model.", ButtonType.CLOSE);
            alert.showAndWait();
        }
    }

    @FXML
    private void browsePress(ActionEvent event) {
    }

    @FXML
    private void insertPress(ActionEvent event) {
        //get data from fields
        String make = makeTF.getText().trim();
        String model = modelTF.getText().trim();
        String year = yearTF.getText().trim();
        int mileage = Integer.valueOf(mlgTF.getText());
        
        int ID = carQ.addCar(make, model, year, mileage);   //add car to the database, returns newly created car ID
        carList.add(new Car(ID, make, model, year, mileage));   //add car to vector array
        carCount++;     //increase the number of cars
        updateFields(carCount); //update fields with the new car
    }

    @FXML
    private void deletePress(ActionEvent event) {
        if(carCount == 0){
            Alert issue = new Alert(AlertType.ERROR, "There are no entries to delete!", ButtonType.CLOSE);
            issue.showAndWait();
            return; 
        }
        Alert confirm = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this entry?", ButtonType.YES, ButtonType.CANCEL);
        confirm.setTitle("Confirm Delete");
        Optional<ButtonType> yes = confirm.showAndWait();
        if(yes.isPresent() && (yes.get() == ButtonType.YES)){
            confirm.close();
            if(carQ.deleteCar(currentIndex));
                Alert success = new Alert(AlertType.INFORMATION, "Entry successfully deleted.", ButtonType.CLOSE);
                success.setTitle("Success!");
                success.showAndWait();
                carList.remove(currentIndex - 1);
                carCount--;
                if(carCount == 0){
                    IDTF.clear();
                    makeTF.clear();
                    modelTF.clear(); 
                    yearTF.clear(); 
                    mlgTF.clear(); 
                    currentIndexTF.setText("0");
                    totalIndexTF.setText("0");
                }
                else
                    updateFields();
        }
    }
    
    private void fillFields(Car _car){
        IDTF.setText(Integer.toString(_car.getCarID()));
        makeTF.setText(_car.getCarMake());
        modelTF.setText(_car.getCarModel());
        yearTF.setText(_car.getCarYear());
        mlgTF.setText(Integer.toString(_car.getCarMileage()));
    }
    
    private void updateFields(){
        currentIndex = 1;
        currentIndexTF.setText(Integer.toString(currentIndex));
        totalIndexTF.setText(Integer.toString(carCount));
        fillFields(carList.get(currentIndex - 1));
        modelSearchTF.clear();
        prevBttn.requestFocus();
    }
    
    private void updateFields(int _index){
        currentIndex = _index;
        currentIndexTF.setText(Integer.toString(currentIndex));
        totalIndexTF.setText(Integer.toString(carCount));
        fillFields(carList.get(currentIndex - 1));
        modelSearchTF.clear();
        prevBttn.requestFocus();
    }
}
