/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    private final String correoEmpresa = "correopruebaboda@gmail.com";
    private final String contrasenaCorreoEmpresa = "123456j.";
    private String recoveryCode = "";
    private Connection conexion = SingleConnection.getSingleConnection().getConection();
    
    private boolean checkEmail(String correo){
        if(!this.comprobarCorreo(correo)){
            return false;//correo incorrecto --> tratar posteriormente en la vista
        }
        String SQL = "select * from account where correo=?";
        PreparedStatement statement = null;
        ResultSet resultset = null;
        try {
            statement = conexion.prepareStatement(SQL);
            statement.setString(1,correo);
            resultset = statement.executeQuery();
            
            if(resultset.next()){
                resultset.close();
                return true;//el correo existe
            }
        } catch (SQLException ex) {
            Logger.getLogger(Recover.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;//el correo no existes
    }
    
    public boolean sendRecoverCode(String correoDestinatario) throws MessagingException{
        if(!(checkEmail(correoDestinatario) && this.comprobarCorreo(correoDestinatario))){
            return false;//el correo no existe, no se puede enviar codigo de recuperacion tratar porteriormente en la vista
        }
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");
        Session sesion = Session.getDefaultInstance(propiedad);
        String asunto = "Codigo de verificacion";
        String codigo = this.generateCode();
        this.recoveryCode = codigo;
        MimeMessage mail = new MimeMessage(sesion); 
        try {
            mail.setFrom(new InternetAddress(correoEmpresa));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatario));
            mail.setSubject(asunto);
            mail.setText(codigo);
            Transport transporte = sesion.getTransport("smtp");
            transporte.connect(correoEmpresa,contrasenaCorreoEmpresa);
            transporte.sendMessage(mail,mail.getRecipients(Message.RecipientType.TO));
            transporte.close();
            
        } catch (AddressException ex) {
            Logger.getLogger(Recover.class.getName()).log(Level.SEVERE, null, ex);
        } catch(MessagingException ex){
            Logger.getLogger(Recover.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    private String generateCode(){
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
    
    public String getRecoveryCode(){
        return this.recoveryCode;
    }
    
    public boolean changePassword(String correo,String password){
        if(!this.comprobarCorreo(correo)){
            return false; //comprobar si el correo esta bien escrito 
        }
        String SQL = "UPDATE account SET password=? WHERE correo=?";
        PreparedStatement statement = null;
        ResultSet resultset = null;
        try {
            statement = conexion.prepareStatement(SQL);
            statement.setString(1, password);
            statement.setString(2, correo);
            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Recover.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return true;
    }
    private boolean comprobarCorreo(String correo){
        boolean valido = false;
        Pattern patronEmail = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mEmail = patronEmail.matcher(correo.toLowerCase());
        if(!mEmail.matches()){
           valido = true;
        }
        return valido;
    }
}
