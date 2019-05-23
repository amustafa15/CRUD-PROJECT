package sample;

import Store.StoreHomePageController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import javafx.stage.Window;

import javax.mail.MessagingException;
import java.io.*;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RegisterController {

    @FXML
    private Hyperlink goBack;
    @FXML
    private Hyperlink signIn;
    @FXML
    private Label incorrectLabels;
    @FXML
    private Label passwordsDontMatch;
    @FXML
    private Button register;
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private PasswordField passwordConfirm;
    @FXML private TextField name;
    @FXML private TextField email;
    private boolean loggedIn = false;

    private ConnectToDb db = new ConnectToDb();

    private File file = new File("/WelcomeMessage.txt");
    private List<String> welcomeTextFromEmail;

    private MainController mainController;

    public RegisterController(MainController mainController){

        this.mainController = mainController;
        System.out.println("h");
        initialize();
    }

    @FXML
    private void initialize(){
        goBack.setFocusTraversable(false);
        register.setFocusTraversable(false);
        username.setFocusTraversable(false);
        password.setFocusTraversable(false);
        passwordConfirm.setFocusTraversable(false);
        name.setFocusTraversable(false);
        email.setFocusTraversable(false);
        signIn.setFocusTraversable(false);


        goBack.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginLayout.fxml"));
                loader.setController(new LoginController(mainController));

                // Set our RegisterLayout as the new content for our MainLayout window
                mainController.getContentPane().getChildren().clear();
                mainController.getContentPane().getChildren().add(loader.load());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        register.setOnMouseClicked(e -> {

            System.out.println(password.getText());
            System.out.println(passwordConfirm.getText());
            try {
                if (isPasswordsCheck() == true) {
                    System.out.println("yay");
                    System.out.println("everything is full");
                    try {
                        if (createUser() == true) {

                            AutomatedEmails automatedEmails = new AutomatedEmails();

                      //      automatedEmails.getText();
                            automatedEmails.sendWelcomeEmail(RegisterController.this);
                            Window window = register.getScene().getWindow();
                            window.setHeight(800);
                            window.setWidth(800);
                            window.centerOnScreen();

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/StoreHomePageLayout.fxml"));
                            loader.setController(new StoreHomePageController(mainController));
                            mainController.getContentPane().getChildren().clear();
                            mainController.getContentPane().getChildren().add(loader.load());

                            System.out.println("homepage screen");
                            loggedIn = true;
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (IOException | MessagingException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                } else if (isPasswordsCheck() == false) {
                    incorrectLabels.setText("Passwords don't match");
                    incorrectLabels.setVisible(true);
                    System.out.println("Passwords don't match2");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
    public String getUsername(){
        return username.getText();
    }
    public String getEmail(){
        return email.getText();
    }
    public String getPassword(){
        return password.getText();
    }
    public String getName(){
        return name.getText();
    }

    public boolean createUser() throws SQLException, IOException, MessagingException, URISyntaxException {

     //   AutomatedEmails automatedEmails = new AutomatedEmails();
//        String query = " INSERT INTO users(username, password, name, email)"
//                + " VALUES(?, ?, ?, ?)";
        PreparedStatement statement = db.getConn().prepareStatement(" INSERT INTO users(username, password, name, email)"
                + " VALUES(?, ?, ?, ?)");
        statement.setString(1, username.getText());
        statement.setString(2, password.getText());
        statement.setString(3, name.getText());
        statement.setString(4, email.getText());

            if (stackOverFlowCheckUserName() ){
                statement.executeUpdate();
                statement.close();
                System.out.println("user created");

                return true;
            }
            return false;
    }

    public boolean isPasswordsCheck(){

            if (!password.getText().isEmpty() && !passwordConfirm.getText().isEmpty()
                    && password.getLength() == passwordConfirm.getLength()
                    && password.getText().trim().equals(passwordConfirm.getText().trim())){
                System.out.println("yea");
                incorrectLabels.setVisible(false);
                return true;
            }
            return false;
    }

    public boolean stackOverFlowCheckUserName() throws SQLException {
        String suppliedUserName = getUsername();
        String dbUserName = "";
        String SQL = "SELECT username FROM users WHERE username=?";

        PreparedStatement preparedStatement = db.getConn().prepareStatement(SQL);
        preparedStatement.setString(1, suppliedUserName);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            dbUserName = rs.getString("username");
        }
        rs.close();
        preparedStatement.close();

/* Below we use the equalsIgnoreCase() method. You
   don't want a supplied User Name to be that close
   or that similar to another User Name already in
   Database. If you do then just use equals() method.  */
        if (dbUserName.equalsIgnoreCase(suppliedUserName)) {
            System.out.println("The User name (" + suppliedUserName +
                    ") is already in use. Try another User Name.");
            incorrectLabels.setText("That username is already taken");
            incorrectLabels.setVisible(true);
            return false;
        }
        else {
            System.out.println("The User name (" + suppliedUserName + ") is Unique.");
            return true;
        }
    }
}
