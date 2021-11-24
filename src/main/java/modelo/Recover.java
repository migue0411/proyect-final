/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author alexa
 */
public class Recover {
    private String correoEmpresa = "correopruebaboda@gmail.com";
    private String contrasenaCorreoEmpresa = "123456j.";
    
    public void sendRecoverCode(String correoDestinatario) throws MessagingException{
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");
        Session sesion = Session.getDefaultInstance(propiedad);
        String asunto = "Codigo de verificacion";
        String codigo = this.generateCode();
        MimeMessage mail = new MimeMessage(sesion); 
        try {
            mail.setFrom(new InternetAddress(correoEmpresa));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatario));
            mail.setSubject(asunto);
            mail.setText(codigo);
            Transport transporte = sesion.getTransport();
            transporte.connect(correoEmpresa,contrasenaCorreoEmpresa);
            transporte.sendMessage(mail,mail.getRecipients(Message.RecipientType.TO));
            transporte.close();
            
        } catch (AddressException ex) {
            Logger.getLogger(Recover.class.getName()).log(Level.SEVERE, null, ex);
        } catch(MessagingException ex){
            Logger.getLogger(Recover.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static String generateCode(){
        //generar un codigo de 9 numeros
        int codigo = (int)(Math.random()*1000000000);
        if(codigo <= 1000000000){
            while(true){
                codigo = (int)(Math.random()*1000000000);
                if(codigo > 999000000 && codigo <= 1000000000){
                    return String.valueOf(codigo);
                }
            }
        }
        return String.valueOf(codigo);
    }
}
