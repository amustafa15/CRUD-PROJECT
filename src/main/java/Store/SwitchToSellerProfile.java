package Store;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.ConnectToDb;
import sample.MainController;

import java.io.IOException;
import java.sql.SQLException;

public class SwitchToSellerProfile {

    @FXML private BorderPane borderPaneSeller;
    @FXML private TextField usernameLogin;
    @FXML private PasswordField passwordFieldLogin;
  //  @FXML private StoreMainController mainController;
    @FXML private MainController mainController;
    @FXML private Hyperlink sellerProfileRegisterHyperLink;
    @FXML private Button btnLogin;
    @FXML private Label incorrectLabel;
    private ConnectToDb connectToDb = new ConnectToDb();
    private StoreHomePageController storeHomePageController;
    private boolean sellerLoggedIn = false;
    private SellerProfileRegisterController sellerProfileRegisterController;
    private Stage secondaryStage = new Stage();

    public SwitchToSellerProfile(MainController mainController){
        this.mainController = mainController;
        storeHomePageController = new StoreHomePageController(mainController);
        sellerProfileRegisterController = new SellerProfileRegisterController(mainController);
        //SellerProfileRegisterController sellerProfileRegisterController = new SellerProfileRegisterController(mainController);
      //  initialize();
    }
    public boolean isSellerLoggedIn(){
        return sellerLoggedIn;
    }
    @FXML
    private void initialize(){
        sellerProfileRegisterHyperLink.setOnAction(e -> {
            try {
//                Window window = sellerProfileRegisterHyperLink.getScene().getWindow();
//                window.setHeight(400);
//                window.setWidth(300);
//                window.centerOnScreen();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/SellerProfileRegisterLayout.fxml"));
//             //   loader.setLocation(getClass().getResource("/SellerProfileRegisterLayout.fxml"));
                loader.setController(sellerProfileRegisterController);
                secondaryStage.setTitle("Seller Profile Login");
                secondaryStage.setHeight(400);
                secondaryStage.setWidth(550);
                Scene scene = new Scene(loader.load());
                secondaryStage.setScene(scene);
                secondaryStage.show();
                sellerProfileRegisterHyperLink.getScene().getWindow().hide();
//                if (loader.load() != null){
//                    System.out.println("loader is not null bitch");
//                }
//                this.mainController.getContentPane().getChildren().clear();
//                this.mainController.getContentPane().getChildren().add(loader.load());

//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/SellerProfileRegisterLayout.fxml"));
//                loader.setController(new SellerProfileRegisterController(mainController));
//
//                // Set our RegisterLayout as the new content for our MainLayout window
//                mainController.getContentPane().getChildren().clear();
//                mainController.getContentPane().getChildren().add(loader.load());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        btnLogin.setOnAction(e -> {
            try {
                if (Login() == true){
                    System.out.println("logged in seller");
                    sellerLoggedIn = true;
//                    Window window = btnLogin.getScene().getWindow();
//                    window.setHeight(800);
//                    window.setWidth(800);
//                    window.centerOnScreen();
//
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/StoreHomePageLayout.fxml"));
//                    loader.setController(new StoreHomePageController(mainController));
//                    mainController.getContentPane().getChildren().clear();
//                    mainController.getContentPane().getChildren().add(loader.load());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Successfully logged into your seller account!");
                    alert.setHeaderText("You have successfully logged into your account");
                    alert.setContentText("You can now sell items");
                    alert.showAndWait();
                    btnLogin.getScene().getWindow().hide();
                 //   storeHomePageController.setMenu();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
    @FXML
    private boolean Login() throws SQLException {

        if (connectToDb.checkIfBusinessNameExists(usernameLogin.getText(), passwordFieldLogin.getText()) == true){
            System.out.println("hurray");
            return true;
        } else if (connectToDb.checkIfBusinessNameExists(usernameLogin.getText(), passwordFieldLogin.getText()) == false) {
            incorrectLabel.setTextFill(Color.RED);
            incorrectLabel.setVisible(true);
            return false;
        }
        return false;
    }

}
