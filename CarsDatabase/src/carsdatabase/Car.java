/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsdatabase;

public class Car {

    
    private int CarID;
    private String CarMake; 
    private String CarModel; 
    private String CarYear;
    private int CarMileage;
    
    public Car(int ID, String make, String model, String year, int mileage){
        this.CarID = ID;
        this.CarMake = make; 
        this.CarModel = model; 
        this.CarYear = year; 
        this.CarMileage = mileage; 
    }
    
    public int getCarID() {
        return CarID;
    }
    
    public void setCarID(int CarID) {
        this.CarID = CarID;
    }
    
    public String getCarMake() {
        return CarMake;
    }
    
    public void setCarMake(String CarMake) {
        this.CarMake = CarMake;
    }
    
    public String getCarModel() {
        return CarModel;
    }
    
    public void setCarModel(String CarModel) {
        this.CarModel = CarModel;
    }
    
    public int getCarMileage() {
        return CarMileage;
    }

    public void setCarMileage(int CarMileage) {
        this.CarMileage = CarMileage;
    }
    
    public String getCarYear() {
        return CarYear;
    }
    
    public void setCarYear(String CarYear) {
        this.CarYear = CarYear;
    }
}
