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

/**
 *
 * @author alexa
 */

public class Login {
      private String correoEmpresa = "correopruebaboda@gmail.com";
        private String contrasenaCorreoEmpresa = "123456j.";

    //metodos publicos
    public void log_User(String User,String password){
        
         
         
    
         JFrame uno=new JFrame();
          
         
         String user1="123";


if (user1.equals(password)){


System.out.println("usuario valido");
}else{

JOptionPane.showMessageDialog(uno,"USUARIO NO VALIDO","ERROR",JOptionPane.ERROR_MESSAGE);
System.out.println("usuario no valido");
}
}
         
          
    
    
    //metodos internenos
    private boolean checkData(){//verificar datos 
        
        return false; 
    }
    


     
  	public static void main(String[] args) {
              Login Login=new Login();
	}
        
}