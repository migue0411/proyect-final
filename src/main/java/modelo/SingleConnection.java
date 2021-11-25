/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexa
 */
public class SingleConnection{
    private static SingleConnection Single_DB_connection = null;
    private Connection conexion;

    private SingleConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql:///proyectofinal","root","");
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
    }
    
    public static SingleConnection getSingleConnection() {
        if(Single_DB_connection == null){
            Single_DB_connection = new SingleConnection();
        }
        return Single_DB_connection;
    }
    
    public Connection getConection(){
        return this.conexion;
    }
}
