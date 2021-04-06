package mailUtility;

import servlets.RunPageServlet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Liam
 */
public class JavaMailUtility {
    /*
    
        query db to check if reports need to be sent
        
        if true, ftp to this app and send mail
    
        necessary fields are email, reportID?
    
    
    */
    
    public static void sendMail(String recepient) throws MessagingException {
        
        System.out.println("Preparing to send Message");
    
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
       
        final String emailAccount = "trigdatamobilizer@gmail.com";
        final String emailPassword = "Capstone_2021";
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
            
                return new PasswordAuthentication(emailAccount, emailPassword);
            }
        });
        
        Message message = prepareMessage(session, emailAccount, recepient);
        
        Transport.send(message);
        System.out.println("Message Sent Successfully");
    }
    
    private static Message prepareMessage(Session session, String emailAccount, String recepient) {
    
        Message message = new MimeMessage(session);
        
        try {
            
            message.setFrom(new InternetAddress(emailAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Sample TRIG Reports");
            
            BodyPart messageBody = new MimeBodyPart();
            
            messageBody.setText("Respective reports in PDF and Excel have been attached");
            
            Multipart multipart = new MimeMultipart();
            
            multipart.addBodyPart(messageBody);
            
            messageBody = new MimeBodyPart();
            
            String pdfReport = "D:/Software_Development/Semester4/Capstone/TRIGReportMailer/res/plc_reportPDF.pdf";
            
            //String pdfReport = "http://backendowner-env.eba-mhuzfgmk.us-east-2.elasticbeanstalk.com/report/filter?carId=XZX_SAIT000000&startDate=2019-12-19&finishDate=2022-01-16";
            
            DataSource source = new FileDataSource(pdfReport);
            
            messageBody.setDataHandler(new DataHandler(source));
            
            messageBody.setFileName("PDF_Report.pdf");
            
            multipart.addBodyPart(messageBody);
            
            messageBody = new MimeBodyPart();
            
            //String excelReport = "http://backendowner-env.eba-mhuzfgmk.us-east-2.elasticbeanstalk.com/report/export?carId=XZX&startDate=2019-12-19&finishDate=2022-01-16";
            
            String excelReport = "D:/Software_Development/Semester4/Capstone/TRIGReportMailer/res/plc_reportEXCEL.xlsx";
            
            DataSource source2 = new FileDataSource(excelReport);
            
            messageBody.setDataHandler(new DataHandler(source2));
            
            messageBody.setFileName("Excel_Report.xlsx");
            
            multipart.addBodyPart(messageBody);
            
            message.setContent(multipart);
        
            return message;
            
        } catch (MessagingException ex) {
            
            Logger.getLogger(RunPageServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
