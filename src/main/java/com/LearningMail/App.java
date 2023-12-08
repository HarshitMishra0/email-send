package com.LearningMail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Preparing to send message" );
        String message = "This is a message for security check";
        String subject = "CodersArea: Confirmation";
        String to = "harshitmishra.inbox@gmail.com";
        String from = "harshit4India@gmail.com";
        //
        // 
        // sendEmail(message, subject, to, from);
        
        //For Attachment
        
        sendAttachment(message, subject, to, from);
    }

    //For attachment waali mail
    private static void sendAttachment(String message, String subject, String to, String from) {
        String host = "smtp.gmail.com";

        //get the system properties
        Properties properties = System.getProperties();
        System.out.println("Properties" + properties);

        //host set

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //Step1: to get the session object...
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("harshit4India@gmail.com", "abc");
            }
        });

        session.setDebug(true);

        //Step 2: compose the message[text, multi media]
        MimeMessage m = new MimeMessage(session);

        try {
            //from email id
            m.setFrom(from);

            //adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //adding subject to message
            m.setSubject(subject);

            //attachment
            //file path
            String path = "C:\\Users\\user\\Desktop\\hello.pdf";

            MimeMultipart mimeMultipart = new MimeMultipart();

            //text
            //file

            MimeBodyPart textMime = new MimeBodyPart();
            MimeBodyPart fileMime = new MimeBodyPart();

            try{

                textMime.setText(message);
                File file = new File(path);
                fileMime.attachFile(file);

                mimeMultipart.addBodyPart(textMime);
                mimeMultipart.addBodyPart(fileMime);

            } catch(Exception e){
                e.printStackTrace();
            }



            m.setContent(mimeMultipart);



            //send

            //Step 3: send the message using Transport Class
            Transport.send(m);


        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

    private static void sendEmail(String message, String subject, String to, String from) {
        String host = "smtp.gmail.com";

        //get the system properties
        Properties properties = System.getProperties();
        System.out.println("Properties" + properties);

        //host set

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //Step1: to get the session object...
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("harshit4India@gmail.com", "abc");
            }
        });

        session.setDebug(true);

        //Step 2: compose the message[text, multi media]
        MimeMessage m = new MimeMessage(session);

        try {
            //from email id
            m.setFrom(from);

            //adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //adding subject to message
            m.setSubject(subject);

            //adding text to message
            m.setText(message);

            //send

            //Step 3: send the message using Transport Class
            Transport.send(m);


        }
        catch(Exception e){
            e.printStackTrace();
        }




    }





}
