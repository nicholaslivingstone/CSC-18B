/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


public final class CarQueries {
    
    //member variables
    private final static String URL = "jdbc:derby://localhost:1527/Cars";
    private final static String USER = "root";
    private final static String PASS = "alpine";
    private static Connection conn = null; 
    private static PreparedStatement pstmt = null; 
    private static Statement stmt = null; 
    private static ResultSet rs = null; 
    
    //default constructor
    public CarQueries(){
        connectDB(); 
    }
    
    //creates connection to database
    public void connectDB(){
        try{
            conn = DriverManager.getConnection(URL, USER, PASS);
        }
        catch(SQLException e){
            System.err.println("Error: Cannot Connect to Database");
            System.exit(400);
        }
    }
   
    //gets total number of rows/cars from table
    public int getRowCount(){
        int rows = 0; 
        
        try{
            pstmt = conn.prepareStatement("select count(*) from Cars");
            rs = pstmt.executeQuery();
            rs.next();
            rows = rs.getInt(1);
        }
        catch(SQLException e){
            e.printStackTrace();
            return 0;
        }
        
        return rows; 
    }
    
    //returns Car object type of a specified row 
    public Car getCar(int row){
        int ID, mileage; 
        String make, model, year; 
        
        try{
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select * from Cars");
            rs.absolute(row);
            ID = rs.getInt(1);
            make = rs.getString(2);
            model = rs.getString(3);
            year = rs.getString(4);
            mileage = rs.getInt(5);
            return new Car(ID, make, model, year, mileage);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        return null; 
    }
    
    
    
    //deletes car from a specific row
    public boolean deleteCar(int row){
        try{
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("select * from Cars");
            rs.absolute(row);
            rs.deleteRow();
            return true; 
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false; 
    }
    
    
    //adds a new car to the database, returns generated carID
    public int addCar(String _make, String _model, String _year, int _mileage){
        int id = 0;
        try{
            pstmt = conn.prepareStatement("insert into Cars (carmake, carmodel, caryear, carmileage) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, _make);
            pstmt.setString(2, _model);
            pstmt.setString(3, _year);
            pstmt.setInt(4, _mileage);
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        return id; 
    }
}
