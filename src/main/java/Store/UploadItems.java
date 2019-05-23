package Store;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.ConnectToDb;
import sample.MainController;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UploadItems {

    @FXML private TextField productName;
    @FXML private TextField quantityForSale;
    @FXML private TextField prices;
    @FXML private Image image;
    @FXML private Button imageUploadBtn;
    @FXML private Button uploadBtn;
    private MainController mainController;
    private File file;
    private ConnectToDb connectToDb = new ConnectToDb();
   // private ImageView imageView;
    private StoreHomePageController storeHomePageController;
    private final String fileLocation = "/Users/ameenmustafa/Desktop/Test";
 //   private final String fileLocation = "/Users/ameenmustafa/IdeaProjects/AmeenazonOnlineStore/src/main/Images";
 //   private final String otherFileLocation = "/Users/ameenmustafa/IdeaProjects/AmeenazonOnlineStore/src/main/resources/UploadedImages/";
  //  private final String otherFileLocation = "/UploadedImages/";

    public UploadItems(){
        mainController = new MainController();
        storeHomePageController = new StoreHomePageController(mainController);

    }
    @FXML
    private void initialize(){
//        String fileLocation = "/Users/ameenmustafa/IdeaProjects/AmeenazonOnlineStore/src/main/resources/";
//        String otherFileLocation = "/Users/ameenmustafa/IdeaProjects/AmeenazonOnlineStore/src/main/resources/UploadedImages/";

//        Stage stage = new Stage();
        //   image = new Image("get the filename somehow");

        imageUploadBtn.setOnAction(e -> {
            uploadImageBtn();
        });
        uploadBtn.setOnAction(e -> {
            try {
                uploadBtn();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
    private void uploadBtn() throws SQLException {
        String name = productName.getText();
        String otherFileLocation = "/Users/ameenmustafa/IdeaProjects/AmeenazonOnlineStore/src/main/resources/";
        int quantity = Integer.parseInt(quantityForSale.getText());
        int price = Integer.parseInt(prices.getText());
        System.out.println(otherFileLocation + file.getName());
   //     String filename = file.toURI().toString();
        String filename = file.getName();
        if (filename.contains("/")){
            filename.substring(1);
        }
     //   String fileGetName = filename.substring(32);
     //   System.out.println(imageUploadBtn.getProperties().get(otherFileLocation).toString() + 988 + " k");
    //    System.out.println(filename);
        if (!name.isEmpty() && quantity > 0 && price > 0){
            System.out.println("upload button set action");
            String query = " INSERT INTO products(name, price, quantity, filename)"
                    + " VALUES(?, ?, ?, ?)";

                PreparedStatement preparedStatement = connectToDb.getConn().prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, price);
                preparedStatement.setInt(3, quantity);
                preparedStatement.setString(4, filename);

                preparedStatement.executeUpdate();

                if (checkTableForProduct()){
                    System.out.println(name);
                    System.out.println(price);
                    System.out.println(filename);
                //    storeHomePageController.addToVBoxList(new Product(name, price, quantity, filename));

//                    preparedStatement.executeUpdate();
//                    preparedStatement.close();
                    System.out.println("product added... hopefully ");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Item Successfully Uploaded!");
                    alert.setHeaderText("You can now sell your stuff. We can't wait for our cut");
                    alert.setContentText("You better not stiff us or we'll send our goons out to get you!");
                    alert.showAndWait();
                }
                //      preparedStatement.close();

            } try {

                Window window = uploadBtn.getScene().getWindow();
                window.hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/StoreHomePageLayout.fxml"));
                loader.setController(new StoreHomePageController(mainController));
                Stage secondaryStage = new Stage();
                secondaryStage.setTitle("Ameenazon");
                secondaryStage.setHeight(800);
                secondaryStage.setWidth(800);
                Scene scene = new Scene(loader.load());
                secondaryStage.setScene(scene);
                secondaryStage.show();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // close the upload window
            // close the store home page
            // reopen it
        }

    private void uploadImageBtn(){
        String otherFileLocation = "/Users/ameenmustafa/IdeaProjects/AmeenazonOnlineStore/src/main/resources/";
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");

        file = fileChooser.showOpenDialog(stage);
        if (file != null){
            Path movefrom = FileSystems.getDefault().getPath(file.getPath());
            Path target = FileSystems.getDefault().getPath(otherFileLocation  +  file.getName());
            imageUploadBtn.getProperties().put(otherFileLocation, file.getAbsolutePath());
            System.out.println("image upload button ");
            try{
                Files.move(movefrom,target, StandardCopyOption.ATOMIC_MOVE);
                System.out.println("image moved");
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
//        String otherFileLocation = "/Users/ameenmustafa/Desktop/IdeaProjects/AmeenazonOnlineStore/src/main/resources/";
//        Stage stage = new Stage();
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Select Image");
//
//        file = fileChooser.showOpenDialog(stage);
//        if (file != null){
//            //   fileLoaction.setText(file.getAbsolutePath());
//            //    image = new Image(file.toURI().toString());
//            //  imageView.setImage(image);
//            Path movefrom = FileSystems.getDefault().getPath(file.getPath());
//            System.out.println(movefrom);
//            System.out.println(file.getPath());
//            Path target = FileSystems.getDefault().getPath(otherFileLocation  +  file.getName());
//            System.out.println(otherFileLocation  + file.getName());
//            //   image = new Image(file.toURI().toString());
//            //    Path targetDir = FileSystems.getDefault().getPath("Users/ameenmustafa/IdeaProjects/AmeenazonOnlineStore/src/main/resources");
//            imageUploadBtn.getProperties().put(otherFileLocation, file.getAbsolutePath());
//            System.out.println("image upload button ");
//            try{
//                Files.move(movefrom,target, StandardCopyOption.ATOMIC_MOVE);
//                System.out.println("image moved");
//            } catch (IOException ex){
//                ex.printStackTrace();
//            }
//        }
    }
    public boolean checkTableForProduct() throws SQLException {
        String suppliedProductName = productName.getText();
        String dbProductName = "";
        String SQL = "SELECT name FROM products WHERE name =?";

        PreparedStatement preparedStatement = connectToDb.getConn().prepareStatement(SQL);
        preparedStatement.setString(1, suppliedProductName);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            dbProductName = rs.getString("name");
        }
        rs.close();
        preparedStatement.close();

/* Below we use the equalsIgnoreCase() method. You
   don't want a supplied User Name to be that close
   or that similar to another User Name already in
   Database. If you do then just use equals() method.  */
        if (dbProductName.equalsIgnoreCase(suppliedProductName)) {
            System.out.println("The product name (" + suppliedProductName +
                    ") is already in use. Try another product Name.");
//        incorrectLabels.setText("That username is already taken");
//        incorrectLabels.setVisible(true);
            return false;
        } else {
            System.out.println("The product name (" + suppliedProductName + ") is Unique.");
            return true;
        }
    }
    private void createProductPane(Product product) throws SQLException {

       // String sql = "SELECT * FROM products";
        PreparedStatement preparedStatement = connectToDb.getConn().prepareStatement("SELECT * FROM products");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            String name = rs.getString("name");
           // int prices = rs.getInt("price");
            double price = Double.valueOf(rs.getInt("price"));
            int quantity = rs.getInt("quantity");
            String filename = rs.getString("filename");
            product.setVBoxName(name);
          //  product.setVBoxPrice(price);
            product.setVBoxQuantity(quantity);
            product.setVBoxFilename(filename);
            VBoxProductPane vBoxProductPane = new VBoxProductPane(new Product(name, price, quantity, filename));
        }
        rs.close();
        preparedStatement.close();
    }
}
