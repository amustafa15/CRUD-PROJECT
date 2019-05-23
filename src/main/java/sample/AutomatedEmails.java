package sample;

import javafx.fxml.FXML;

import java.awt.*;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class  AutomatedEmails {

    @FXML
    private TextField email;
    @FXML
    private TextField name;
    private RegisterController registerController = new RegisterController(new MainController());
    private String host = "smtp.gmail.com";
    private String user = "Ameenazon@gmail.com";
    private String emailPassword = "B@tman15";
    private String TO = "";
    private String FROM = "Ameenazon@gmail.com";
    private String subject = "Ameenazon";
    private String messageText = "";
    private String nameToInsert = registerController.getName();

    private File file = new File("/WelcomeMessage.txt");
    private Properties props = System.getProperties();

    public AutomatedEmails() throws IOException, MessagingException {
        System.out.println(nameToInsert + "jillkkkk");
    }

    public void sendWelcomeEmail(RegisterController registerController) throws MessagingException, IOException, URISyntaxException {

        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        TO = registerController.getEmail();
        System.out.println(registerController.getName());

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, emailPassword);
            }
        });

        System.out.println(registerController.getName() + " repeat in sendWelcomeEmail");
        Message message = prepareWelcomeMessage(session, FROM, TO);

        Transport tr = session.getTransport("smtps");
        tr.connect(host, FROM, emailPassword);
        Transport.send(message);
        tr.close();
    }

    public Message prepareWelcomeMessage(Session session, String FROM, String TO) throws IOException, URISyntaxException {
        messageText = String.valueOf(getText());
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
            message.setSubject(subject);
            message.setText(messageText);
            return message;
        } catch (Exception e){
            Logger.getLogger(AutomatedEmails.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    public void automatedWelcomeEmail() throws IOException, URISyntaxException {

    }
    public List<String> getText() throws IOException, URISyntaxException {
        automatedWelcomeEmail();
        RegisterController registerController = new RegisterController(new MainController());
        String nameToReplace = registerController.getName();
        System.out.println(nameToReplace + " jo");
        Path path = Paths.get(AutomatedEmails.class.getResource("/WelcomeMessage.txt").toURI());
        Stream<String> lines = Files.lines(path);
        List<String> replaced = lines.map(line -> line.replaceAll("FULLNAME", nameToReplace)).collect(Collectors.toList());
        Files.write(path, replaced);
        lines.close();
        System.out.println("Find and replace done!!");
        System.out.println(nameToReplace);
        System.out.println(replaced);
        return replaced;
    }
}



