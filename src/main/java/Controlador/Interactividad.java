/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JButton;
import modelo.Login;
import modelo.Recover;
import vista.ForgottenPassword;
import vista.Home;
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
    private Home homeFrame = new Home();
    private Login login = new Login();
    private Recover recuperar = new Recover();
    
    //
    private Point LocationPanels = frame.getLoginPanelLocation();
    //
    public Interactividad(){
        crearCuenta.setBounds((int)LocationPanels.getX(),(int)LocationPanels.getY(), 343, 469);
        recuperarContrasenia.setBounds((int)LocationPanels.getX(),(int)LocationPanels.getY(), 343, 469);
        this.loginFrame_Interaction();
        this.recoveryMethod(); 
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
                if(login.log_User(frame.getCampo_Usuario().getText(), String.valueOf(frame.getCampo_contrasenia().getPassword()))){
                    frame.setVisible(false);
                    homeFrame.setVisible(true); 
                }    
            }    
        });
        frame.getBt_recuperar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getLoginPanel().setVisible(false);//panel de login 
                recuperarContrasenia.setVisible(true);//vista
                frame.getCapaBase().add(recuperarContrasenia);//se le añade a la capa base otro panel 
                frame.revalidate();
            }
            
        });
    }
    private void recoveryMethod(){
        recuperarContrasenia.getBt_Enviar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(!recuperar.sendRecoverCode(recuperarContrasenia.getCampo_Correo().getText())){
                        System.out.println("No se pudo enviar el codigo de recuperacion, ingrese un correo valido");
                    }
                } catch (MessagingException ex) {
                    Logger.getLogger(Interactividad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        recuperarContrasenia.getBt_verficarCodigo().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(recuperarContrasenia.getCampo_Codigo().equals(recuperar.getRecoveryCode())){
                    recuperarContrasenia.getCampo_nuevaContrasenia1().setEditable(true);
                    recuperarContrasenia.getCampo_nuevaContrasenia2().setEditable(true);
                }
            }
            
        });
        
    }
}
