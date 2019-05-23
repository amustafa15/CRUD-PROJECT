package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ForgotPasswordController {

    private MainController mainController;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSendLostPassword;
    @FXML
    private TextField inputedUserName;
    @FXML
    private Label hiddenLabel;
    private ConnectToDb connectToDb = new ConnectToDb();

    public ForgotPasswordController(MainController mainController){
        this.mainController = mainController;
    }
    @FXML
    public void initialize(){
        hiddenLabel.setVisible(false);
        btnCancel.setOnAction(event -> {
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
        btnSendLostPassword.setOnAction(e ->{
            System.out.println("butt");
            try {
                hiddenLabel.setText(connectToDb.getLostPassword(inputedUserName.getText()));
                hiddenLabel.setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            // check for username
            // if true {
            // send forgot password email
            // print label saying "email has been sent to the email address associated with this account"
            // send to home page
            // else {
            // display some label saying we can't find that username
        });
    }
    public void findUsernameInDatabase(){

    }
}
