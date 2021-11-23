/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.*;

/**
 *
 * @author alexa
 */
public class DB_connection{
    private static DB_connection Single_DB_connection = null;
    private Connection conexion;
    private Statement declaracion; 

    private DB_connection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql:///finalprotect","root","");
            declaracion = conexion.createStatement();
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
    }
    
    public static DB_connection openDB() {
        if(Single_DB_connection == null){
            Single_DB_connection = new DB_connection();
        }
        return Single_DB_connection;
    }

    public static boolean closeDB() {
       if( Single_DB_connection == null){
           return false;
       }else{
            Single_DB_connection = null;
            return true;
       }
    }
    
}
