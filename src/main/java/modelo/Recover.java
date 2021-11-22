/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author alexa
 */
public class Recover {
    private String correoEmpresa = "correopruebaboda@gmail.com";
    private String contraseñaCorreoEmpresa = "123456j.";
    
    public void sendRecoverCode(String correo){
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");
        Session sesion = Session.getDefaultInstance(propiedad);
        String asunto = "Codigo de verificacion";
        String codigo = this.generateCode();
        MimeMessage mail = new MimeMessage(sesion); 
        
    }
    
    private String generateCode(){
        //generar un condigo de 9 numeros
        return null;
    }
}
