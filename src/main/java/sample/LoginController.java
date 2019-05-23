package sample;

import Store.StoreHomePageController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Button btnRegister;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField usernameLogin;
    @FXML
    private PasswordField passwordFieldLogin;
    @FXML
    private Hyperlink forgotPasswordHyperlink;
    @FXML
    private Label incorrectLabel;
    // Reference to our main controller so we can access its content
    private MainController mainController;
    private ConnectToDb connectToDb = new ConnectToDb();
    private Stage stage = new Stage();

    public LoginController(MainController mainController) {
        this.mainController = mainController;


    }

    @FXML
    private void initialize() {
        usernameLogin.setFocusTraversable(false);
        passwordFieldLogin.setFocusTraversable(false);
        forgotPasswordHyperlink.setFocusTraversable(false);
        btnLogin.setFocusTraversable(false);
        btnRegister.setFocusTraversable(false);
        // Set our Register button to change the content of the main pane
        btnLogin.setOnAction(ei -> {
            try {
                if (Login() == true) {

                    Window window = btnLogin.getScene().getWindow();
                    window.setHeight(800);
                    window.setWidth(800);
                    window.centerOnScreen();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/StoreHomePageLayout.fxml"));
                    loader.setController(new StoreHomePageController(mainController));
                    mainController.getContentPane().getChildren().clear();
                    mainController.getContentPane().getChildren().add(loader.load());

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnRegister.setOnAction(event -> {
            try {
                Window window = btnRegister.getScene().getWindow();
                window.setHeight(400);
                window.setWidth(300);
                window.centerOnScreen();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegisterLayout.fxml"));

                loader.setController(new RegisterController(mainController));

                // Set our RegisterLayout as the new content for our MainLayout window
                mainController.getContentPane().getChildren().clear();
                mainController.getContentPane().getChildren().add(loader.load());

            } catch (IOException f) {
                f.printStackTrace();
            }
        });
        forgotPasswordHyperlink.setOnAction(e ->{
            try{
                // this does the resizing and centering
                Window window = forgotPasswordHyperlink.getScene().getWindow();
                window.setHeight(250);
                window.setWidth(350);
                window.centerOnScreen();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ForgotPasswordLayout.fxml"));
                loader.setController(new ForgotPasswordController(mainController));


                mainController.getContentPane().getChildren().clear();
                mainController.getContentPane().getChildren().add(loader.load());
            } catch (IOException d){
                d.printStackTrace();
            }
        });
    }
    @FXML
    private boolean Login() throws SQLException {

        if (connectToDb.checkIfUsernameExists(usernameLogin.getText(), passwordFieldLogin.getText()) == true){
            System.out.println("hurray");
            return true;
        } else if (connectToDb.checkIfUsernameExists(usernameLogin.getText(), passwordFieldLogin.getText()) == false) {
            incorrectLabel.setTextFill(Color.RED);
            incorrectLabel.setVisible(true);
            return false;
        }
        return false;
    }

    public String getUsername(){
        return usernameLogin.getText();
    }
    public String getPassword(){
        return passwordFieldLogin.getText();
    }
}
