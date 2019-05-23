package Store;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import sample.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SellerProfileRegisterController {

    private StoreHomePageController storeHomePageController;
    @FXML private MainController mainController;
    @FXML
    private TextField businessName = new TextField();
    @FXML private BorderPane sellerBorderPane;
    @FXML private TextField businessEmail = new TextField();
    @FXML private Button registerBtn = new Button();
    @FXML private Hyperlink goBackLink = new Hyperlink();
    @FXML
    private Label incorrectLabels = new Label();
    @FXML private PasswordField password;
    @FXML private PasswordField confirmPassword;
    private ConnectToDb db = new ConnectToDb();
    private RegisterController registerController;

    public SellerProfileRegisterController(MainController mainController){
        this.mainController = mainController;
      //  registerController = new RegisterController(mainController);
        storeHomePageController = new StoreHomePageController(this.mainController);
    }
    @FXML private void initialize(){

        businessName.setFocusTraversable(false);
        businessEmail.setFocusTraversable(false);
        registerBtn.setFocusTraversable(false);
        password.setFocusTraversable(false);
        confirmPassword.setFocusTraversable(false);
        goBackLink.setFocusTraversable(false);
        registerBtn.setText("button text");

        goBackLink.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/SwitchToSellerProfile.fxml"));
                loader.setController(new SellerProfileRegisterController(mainController));

                // Set our RegisterLayout as the new content for our MainLayout window
                mainController.getContentPane().getChildren().clear();
                mainController.getContentPane().getChildren().add(loader.load());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        registerBtn.setOnMouseClicked(e -> {

            try {
                if (isPasswordsCheck() == true) {
                    System.out.println("yay");
                    System.out.println("everything is full");
                    try {
                        if (createSeller() == true) {

                            AutomatedEmails automatedEmails = new AutomatedEmails();

                            //      automatedEmails.getText();
                         //   automatedEmails.sendWelcomeEmail(RegisterController.this);
                            Window window = registerBtn.getScene().getWindow();
                            window.setHeight(800);
                            window.setWidth(800);
                            window.centerOnScreen();

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/StoreHomePageLayout.fxml"));
                            loader.setController(new StoreHomePageController(mainController));
                            mainController.getContentPane().getChildren().clear();
                            mainController.getContentPane().getChildren().add(loader.load());
                         //   storeHomePageController.setMenu();

                        //    System.out.println("homepage screen");
                        //    loggedIn = true;
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (IOException | MessagingException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                } else if (isPasswordsCheck() == false) {
                    incorrectLabels.setText("Passwords don't match");
                    incorrectLabels.setVisible(true);
                    System.out.println("Passwords don't match");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public boolean createSeller() throws SQLException, IOException, MessagingException, URISyntaxException {

//        String sql = "INSERT INTO sellers(" + "businessName," + "businessEmail,"
//                + "password)" + "VALUES(?, ?, ?)";
        Statement statement = db.getConn().createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        PreparedStatement pstmt = db.getConn().prepareStatement("INSERT INTO sellers(" + "businessName," + "businessEmail,"
                + "password)" + "VALUES(?, ?, ?)");

        ResultSet rs = statement.executeQuery("SELECT * FROM users");
        while(rs.next()){
        //    String nm = rs.getString(3);
          //  System.out.println(nm);
         //   String userName = rs.getString(1);
         //   System.out.println(userName);
//            pstmt.setString(1, nm);
//            pstmt.setString(2, userName);
            pstmt.setString(1, getBusinessName());
            pstmt.setString(2, businessEmail.getText());
            pstmt.setString(3, password.getText());
        }
        if (stackOverFlowCheckUserName() ){
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Business created");
            return true;
        }
        return false;


    }
    public boolean isPasswordsCheck(){

        if (!password.getText().isEmpty() && !confirmPassword.getText().isEmpty()
                && password.getLength() == confirmPassword.getLength()
                && password.getText().trim().equals(confirmPassword.getText().trim())){
            System.out.println("yea");
            incorrectLabels.setVisible(false);
            return true;
        }
        return false;
    }
    public String getBusinessName(){
        return businessName.getText();
    }

    public boolean stackOverFlowCheckUserName() throws SQLException {
        System.out.println(getBusinessName() + " 1");
        System.out.println(registerController.getUsername() + " 2");
    //    String suppliedUserName = registerController.getUsername();
        String suppliedUserName = getBusinessName();
        String dbUserName = "";
      //  String SQL = "SELECT businessName FROM sellers WHERE businessName=?";

        PreparedStatement preparedStatement = db.getConn().prepareStatement("SELECT businessName FROM sellers WHERE businessName=?");
        preparedStatement.setString(1, suppliedUserName);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            dbUserName = rs.getString("businessName");
        }
        rs.close();
        preparedStatement.close();

/* Below we use the equalsIgnoreCase() method. You
   don't want a supplied User Name to be that close
   or that similar to another User Name already in
   Database. If you do then just use equals() method.  */
        if (dbUserName.equalsIgnoreCase(suppliedUserName)) {
            System.out.println("The User name (" + suppliedUserName +
                    ") is already in use. Try another Business Name.");
            incorrectLabels.setText("That business name is already taken");
            incorrectLabels.setVisible(true);
            return false;
        }
        else {
            System.out.println("The Business name (" + suppliedUserName + ") is Unique.");
            return true;
        }
    }
}

