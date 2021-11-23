/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.ForgottenPassword;
import vista.LoginFrame;
import vista.SingUp;

/**
 *
 * @author alexa
 */
public class Interactividad {
    //vista
    private LoginFrame frame = new LoginFrame();
    private SingUp crearCuenta = new SingUp();
    private ForgottenPassword recuperarContrasenia = new ForgottenPassword();
    
    //
    private Point LocationPanels = frame.getLoginPanelLocation();
    //
    public Interactividad(){
        crearCuenta.setBounds((int)LocationPanels.getX(),(int)LocationPanels.getY(), 340, 465);
        recuperarContrasenia.setBounds((int)LocationPanels.getX(),(int)LocationPanels.getY(), 340, 465);
        this.loginFrame_Interaction();
        
       
        
    }
    
    private void loginFrame_Interaction(){
        
        frame.setVisible(true);
        frame.getBt_crear().addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.getLoginPanel().setVisible(false);
               crearCuenta.setVisible(true);
               frame.getCapaBase().add(crearCuenta);
               frame.revalidate();
            }    
        });
        frame.getBt_ingresar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Posteriormente se implementara el frame de ingreso");
            }    
        });
        frame.getBt_recuperar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getLoginPanel().setVisible(false);
                recuperarContrasenia.setVisible(true);
                frame.getCapaBase().add(recuperarContrasenia);
               frame.revalidate();
               
                
            }
            
        });
        
        
        
    }
    
}
