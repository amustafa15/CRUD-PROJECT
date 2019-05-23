//package Store;
//
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.VBox;
//
//import java.io.IOException;
//
//public class StoreMainController {
//
//    @FXML
//    private VBox contentPane;
//
//    @FXML
//    private void initialize() {
//
//        // Initially start with the login layout
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SwitchToSellerProfile.fxml"));
//
//            // Set the LoginController and pass a reference to the MainController. This allows the LoginController
//            // to access our contentPane.
//            loader.setController(new SwitchToSellerProfile(this));
//
//            // Now, load the login layout into our contentPane
//            BorderPane gridPane = loader.load();
//            contentPane.getChildren().add(gridPane);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    }
