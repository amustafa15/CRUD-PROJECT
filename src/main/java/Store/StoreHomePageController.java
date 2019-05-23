package Store;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.io.FileUtils;
import sample.ConnectToDb;
import sample.LoginController;
import sample.MainController;
import sample.MessagingSupportController;

import javax.servlet.RequestDispatcher;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class StoreHomePageController {

    private MainController mainController;

    @FXML private ScrollPane salesScrollPane;
    @FXML private ImageView quickLookImageView;
    @FXML private BorderPane borderPane;
    @FXML private Button messageSupports;
    @FXML private MenuBar menuBarProfile;
    @FXML private Menu menuProfile;
    @FXML private BorderPane borderPaneHomePage;
    @FXML private ScrollPane scrollPane;
    @FXML private Hyperlink hyperlink;
    @FXML private MenuItem editProfile;
    @FXML private MenuItem yourOrders;
    @FXML private MenuItem switchToSeller;
    @FXML private MenuBar menuBarProfileSeller;
    @FXML private Menu menuProfileSeller;
    @FXML private MenuItem editProfileSeller;
    @FXML private MenuItem yourOrdersSeller;
    @FXML private MenuItem switchToShopper;
    @FXML private MenuItem uploadItem;
    @FXML private ScrollPane verticalScrollPane;
    @FXML private HBox scrollableHBox;
    private String fileName;
    private Product waterProduct = new Product("The Water Rights to the Nation of Angola", 999.00, 2,
            "/0.png");
    private Product pollutedWater = new Product("Polluted Water From Various Russian Streams", 4.99, 25,
            "/1.png");
    private Product rawStreamWater= new Product("Raw Water", 24.99, 50, "/2.png");
    private Product rawStreamWater2= new Product("Extra Raw Water", 24.99, 50, "/3.png");
    private Product rawStreamWater1= new Product("Diet Raw Water", 24.99, 50, "/4.png");

    private ObservableList<Product> forSaleHBoxProducts = FXCollections.observableArrayList();
    private List<Product> forSaleVBoxProducts = FXCollections.observableArrayList();

    private Stage secondaryStage = new Stage();
    @FXML private VBox vbox;
    @FXML private GridPane gridPane;
    @FXML HBox hb;
    public StoreHomePageController(MainController mainController) {

        this.mainController = mainController;
        this.fileName = waterProduct.getFileName();

    }
    public void setMenu(){
        switchToSeller.setVisible(false);
        switchToShopper.setVisible(true);
        uploadItem.setVisible(true);
    }
    public void addToVBoxList(Product product){
        forSaleVBoxProducts.add(product);
        System.out.println(forSaleVBoxProducts.size());
    }
    private void addToForSalesPane(){

        scrollableHBox.setPadding(new Insets(0, 5, 0, 5));
        for (int i =0; i < forSaleHBoxProducts.size(); i++){
            ProductPaneHomePage stackedPane = new ProductPaneHomePage(forSaleHBoxProducts.get(i));
            stackedPane.setMaxSize(200, 200);
            stackedPane.setPrefSize(200, 200);
            stackedPane.setMinSize(200, 200);
            scrollableHBox.getChildren().add(stackedPane);
            scrollableHBox.setSpacing(10);
        }
    }
    public void addToVBox() throws SQLException {
        System.out.println(forSaleVBoxProducts.size() + " addtovbox");
        String location = "/Users/ameenmustafa/IdeaProjects/AmeenazonOnlineStore/main/resources/";

        ConnectToDb connectToDb = new ConnectToDb();
        PreparedStatement preparedStatement = connectToDb.getConn().prepareStatement("SELECT * FROM products");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            String name = rs.getString("name");

            double price = Double.valueOf(rs.getInt("price"));
            int quantity = rs.getInt("quantity");
            String filename = rs.getString("filename");
            System.out.println(name + " " + price + " " + quantity + " " + filename);

            addToVBoxList(new Product(name, price, quantity, filename));

            System.out.println("vbox pane ");
        }

        rs.close();
        preparedStatement.close();
    }
    private void reNameFile(String filename){
        File root = new File("/Users/ameenmustafa/Desktop/IdeaProjects/AmeenazonOnlineStore/");
        String fileName = "a.txt";
        try {
            boolean recursive = true;

            Collection files = FileUtils.listFiles(root, null, recursive);

            for (Iterator iterator = files.iterator(); iterator.hasNext();) {
                File file = (File) iterator.next();
                if (file.getName().equals(fileName))
                    System.out.println(file.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void populateGridPane(){
        int x = 0;
        int y = 0;
        int i = 0;
        vbox.setPadding(new Insets(15, 15, 0, 15));

        while (x < forSaleVBoxProducts.size() && i < 4 && y < 3){
            Product newProduct = forSaleVBoxProducts.get(x);
            VBoxProductPane vBoxProductPane = new VBoxProductPane(newProduct);
            System.out.println(vBoxProductPane.getPrefHeight() + " here it is" + vBoxProductPane.getPrefWidth());
            gridPane.add(vBoxProductPane, y, i);

            System.out.println(i + " " + y);
            x++;
            if (y == 2){
                i++;
                y = 0;
            } else {
                y++;
            }
//            i++;
        }

    }

    @FXML
    private void initialize() throws SQLException {

      //  HBox hb = new HBox();
        borderPane.setTop(hb);
        hb.getStyleClass().add("hb");

        forSaleHBoxProducts.add(waterProduct);
        forSaleHBoxProducts.add(pollutedWater);
        forSaleHBoxProducts.add(rawStreamWater);
        forSaleHBoxProducts.add(rawStreamWater1);
        forSaleHBoxProducts.add(rawStreamWater2);

        salesScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        salesScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        verticalScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        verticalScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        addToForSalesPane();
        addToVBox();
        populateGridPane();
      //  setMenu();

            messageSupports.setOnMouseClicked(e -> {
//                try {
//                    System.out.println("opening server side chat");
//                    FXMLLoader loader = new FXMLLoader();
//                    loader.setLocation(getClass().getResource("/ServerSideMessagingSupport.fxml"));
//                    Stage tertiaryStage = new Stage();
//                    loader.setController(new SupportSideMessagingSupport(mainController));
//
//                    tertiaryStage.setTitle("Server side support");
//                    tertiaryStage.setHeight(500);
//                    tertiaryStage.setWidth(350);
//
//                    Scene scene = new Scene(loader.load());
//
//                    tertiaryStage.setScene(scene);
//                    tertiaryStage.show();
//                } catch (Exception ex){
//                    ex.printStackTrace();
//                }
                try {
                    System.out.println("opening client side chat");
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/MessagingSupportLayout.fxml"));
                 //   Parent root = (Parent) loader.load();
                    Stage secondaryStage = new Stage();
                    loader.setController(new MessagingSupportController(mainController));

                   // mainController.getContentPane().getChildren().add(loader.load());
                    secondaryStage.setTitle("Support");
                    secondaryStage.setHeight(500);
                    secondaryStage.setWidth(350);

                    Scene scene = new Scene(loader.load());

                    secondaryStage.setScene(scene);
                    secondaryStage.show();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            });
            editProfile.setOnAction(e -> {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/EditProfileLayout.fxml"));
                    loader.setController(new EditProfile(mainController));
                    mainController.getContentPane().getChildren().clear();
                    mainController.getContentPane().getChildren().add(loader.load());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            yourOrders.setOnAction(e -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/YourORders.fxml"));
                    loader.setController(mainController);
                    mainController.getContentPane().getChildren().clear();
                    mainController.getContentPane().getChildren().add(loader.load());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            switchToSeller.setOnAction(e -> {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/SwitchToSellerProfile.fxml"));
                    Stage secondaryStage = new Stage();
                    loader.setController(new SwitchToSellerProfile(mainController));
                    secondaryStage.setTitle("Seller Profile Login");
                    secondaryStage.setHeight(400);
                    secondaryStage.setWidth(550);
                    Scene scene = new Scene(loader.load());
                    secondaryStage.setScene(scene);
                    secondaryStage.show();
                    setMenu();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            menuBarProfile.setOnMousePressed(e->{
            });
            menuProfile.setOnAction(e -> {
            });
            uploadItem.setOnAction(e -> {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/UploadItemLayout.fxml"));
                    Stage secondaryStage = new Stage();
                    loader.setController(new UploadItems());
                    secondaryStage.setTitle("List an Item For Sale");
                    secondaryStage.setHeight(400);
                    secondaryStage.setWidth(600);
                    Scene scene = new Scene(loader.load());
                    secondaryStage.setScene(scene);
                    secondaryStage.show();
                    Window window = vbox.getScene().getWindow();
                    window.hide();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }


