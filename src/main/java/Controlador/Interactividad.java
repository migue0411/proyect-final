/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
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
    private ForgottenPassword recoveryView = new ForgottenPassword();
    private Home homeFrame = new Home();
    private Login login = new Login();
    private Recover recuperar = new Recover();
    
    //
    private Point LocationPanels = frame.getLoginPanelLocation();
    //
    public Interactividad(){
        crearCuenta.setBounds((int)LocationPanels.getX(),(int)LocationPanels.getY(), 343, 469);
        recoveryView .setBounds((int)LocationPanels.getX(),(int)LocationPanels.getY(), 343, 469);
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
                recoveryView.setVisible(true);//vista
                frame.getCapaBase().add(recoveryView);//se le añade a la capa base otro panel 
                frame.revalidate();
            }
            
        });
    }
    private void recoveryMethod(){
        Color redError = new Color(136,145,145);
        recoveryView.getBt_Enviar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(!recuperar.sendRecoverCode(recoveryView.getCampo_Correo().getText())){
                        System.out.println("No se pudo enviar el codigo de recuperacion, ingrese un correo valido");
                    }
                    recoveryView.getCampo_Codigo().setEnabled(true);
                    recoveryView.getBt_verficarCodigo().setEnabled(true);
                } catch (MessagingException ex) {
                    Logger.getLogger(Interactividad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        recoveryView .getBt_verficarCodigo().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(recoveryView.getCampo_Codigo().getText() + " " + recuperar.getRecoveryCode());
                if(recoveryView.getCampo_Codigo().getText().equals(recuperar.getRecoveryCode())){
                    recoveryView.getCampo_nuevaContrasenia1().setEnabled(true);
                    recoveryView.getCampo_nuevaContrasenia2().setEnabled(true);
                    recoveryView.getBt_Confirmar().setEnabled(true);
                }
            }
            
        });
        recoveryView.getBt_Confirmar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }        
        });
        recoveryView.getBt_Volver().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                recoveryView.setVisible(false);//vista
                frame.getLoginPanel().setVisible(true);//panel de login 
                frame.revalidate();
            }    
        });
        
        recoveryView.getBt_Confirmar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(String.valueOf(recoveryView.getCampo_nuevaContrasenia1().getPassword()).equals(String.valueOf(recoveryView.getCampo_nuevaContrasenia2().getPassword()))){
                    
                    //se debe implementar un cambio de contraseña en la base de datos
                }else{
                    recoveryView.getCampo_nuevaContrasenia1().setBackground(redError);
                    recoveryView.getCampo_nuevaContrasenia2().setBackground(redError);
                }
            }
            
        });
        Document documentoPassword1 = recoveryView.getCampo_nuevaContrasenia1().getDocument();
        Document documentoPassword2 = recoveryView.getCampo_nuevaContrasenia2().getDocument();
        documentoPassword1.addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(recoveryView.getCampo_nuevaContrasenia1().getBackground().equals(redError)){
                    recoveryView.getCampo_nuevaContrasenia1().setBackground(Color.WHITE);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
            
        });
        documentoPassword2.addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(recoveryView.getCampo_nuevaContrasenia2().getBackground().equals(redError)){
                    recoveryView.getCampo_nuevaContrasenia2().setBackground(Color.WHITE);
                }    
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
            
        });
    }
}
