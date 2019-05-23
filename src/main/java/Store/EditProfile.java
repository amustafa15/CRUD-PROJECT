package Store;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.ConnectToDb;
import sample.MainController;

import java.sql.SQLException;

public class EditProfile {

    @FXML private Button updatePasswordBtn;
    @FXML private PasswordField oldPassword;
    @FXML private PasswordField newPassword;
    @FXML private PasswordField confirmNewPassword;
    @FXML private Button deleteBtn;
    @FXML private TextField paymentUsername;
    private MainController mainController;
    private ConnectToDb connectToDb = new ConnectToDb();

    public EditProfile(MainController mainController){

    }
    @FXML
    private void initialize(){
        updatePasswordBtn.setOnAction(e -> {
            try {
                if (connectToDb.checkIfPasswordExists(oldPassword.getText()) &&
                    newPassword.getText().equals(confirmNewPassword.getText())){
                        connectToDb.changePassword(oldPassword.getText(), newPassword.getText());
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        deleteBtn.setOnAction(e -> {
            try {
                connectToDb.deleteCard(paymentUsername.getText());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Oh no you deleted your card info");
                alert.setHeaderText("We are so sad to lose your personal data");
                alert.setContentText("We promise it is not saved anywhere else");
                alert.showAndWait();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

}
