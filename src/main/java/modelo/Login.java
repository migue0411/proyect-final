/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexa
 */

public class Login {
    private String correoEmpresa = "correopruebaboda@gmail.com";
    private String contrasenaCorreoEmpresa = "123456j.";

    //metodos publicos
    public boolean log_User(String User,String password){//lanzará un booleano que indicara si el login se ha hecho correctamente
       //se establece la conexion 
       Connection conexion = SingleConnection.getSingleConnection().getConection();
       String SQL = "select * from account where username=? and password=?";
        try {
            PreparedStatement statement = conexion.prepareStatement(SQL);
            statement.setString(1,User);
            statement.setString(2,password);
            ResultSet resultset = statement.executeQuery();
            
            while(resultset.next()){
                
            }

            
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
         
          
    
    
    //metodos internenos
    private boolean checkData(){//verificar datos 
        
        return false; 
    }
    
}