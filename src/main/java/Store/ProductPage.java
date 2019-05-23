package Store;

import Payment.PaymentPage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.MainController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ProductPage extends Pane {

    private Product product;
    private double d1;
    private double price;
    @FXML private BorderPane borderPane;
    @FXML
    private BorderPane borderPaneCenter;
    @FXML
    private Pane pane = new Pane();
    @FXML
    private Pane ProductInfoVBox;
    @FXML
    private ImageView imageView;
    @FXML
    private Label ProductName;
    @FXML
    private Label priceLabel;
    @FXML
    private Label Quantity;
    @FXML
    private ComboBox comboBox;
    @FXML
    private Label info;
    @FXML
    private Button buyNowBtn;
    @FXML
    private HBox hb = new HBox();
    private Label companyNameLabel;
    private File file;
    private List combox1List;

    public ProductPage(MainController mainController, Product product){

        this.product = product;
        this.combox1List = new ArrayList();
        System.out.println(this.product.getPrice());
    }
    @FXML
    private void initialize(){
        borderPane.setTop(hb);
        hb.getStyleClass().add("hb");
        setQuantity();
        ProductName.setText(this.product.getName());
        System.out.println(ProductName.getText());
        setImageView();
        Quantity.setText("Quantity: " + this.product.getQuantity());
        priceLabel.setText("$" + this.product.getPrice());
        comboBoxStuff();

        borderPane.setCenter(borderPaneCenter);
        borderPaneCenter.setCenter(pane);
        pane.getChildren().addAll();
        buyNowBtn.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/PaymentPageLayout.fxml"));
                Stage secondaryStage = new Stage();
                loader.setController(new PaymentPage(this.product, priceLabel.getText()));
                secondaryStage.setTitle(product.getName());
                secondaryStage.setHeight(450);
                secondaryStage.setWidth(600);

                Scene scene = new Scene(loader.load());
                secondaryStage.setScene(scene);
                secondaryStage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    private void comboBoxStuff(){
        for (int i = 1; i <= this.product.getQuantity(); i++){
            comboBox.getItems().add(i);
        }
        comboBox.getSelectionModel().selectFirst();
        ObservableList combox1 = FXCollections.observableList(this.combox1List);
      //  comboBox.setItems(combox1);
        double totalPrice = this.product.getPrice();
        comboBox.getSelectionModel().selectedItemProperty().addListener((ov, t, t1) ->
                priceLabel.setText("$" + totalPrice * (comboBox.getSelectionModel().getSelectedIndex() + 1)));
    }
    public String getPrice(){
        comboBox.getSelectionModel().selectFirst();
        ObservableList combox1 = FXCollections.observableList(this.combox1List);
        //  comboBox.setItems(combox1);
        double totalPrice = this.product.getPrice();
        comboBox.getSelectionModel().selectedItemProperty().addListener((ov, t, t1) ->
                priceLabel.setText("$" + totalPrice * (comboBox.getSelectionModel().getSelectedIndex() + 1)));
        return priceLabel.getText();
    }
    public void setPrice(){
        double totalPrice = this.product.getPrice();
        System.out.println(totalPrice);
        priceLabel.setText("$" + totalPrice * (comboBox.getSelectionModel().getSelectedIndex() + 1));
        System.out.println("kkjjkj" + totalPrice * (comboBox.getSelectionModel().getSelectedIndex() + 1));
    }
    private void setQuantity(){
        Quantity.setText("" + this.product.getQuantity());
    }
    private void setComboBox(){

        for (int i = 1; i <= this.product.getQuantity(); i++){
            comboBox.getItems().add(i);
        }
        comboBox.getSelectionModel().selectFirst();
    }
    private double comboBoxQuantity(){
        comboBox.setOnAction(e -> {
            int value = Integer.valueOf(comboBox.getSelectionModel().getSelectedIndex());
            System.out.println(value);
            d1 = Double.valueOf(value);
            System.out.println(d1);
            System.out.println(d1);
        });
        return d1;
    }
    public String getPriceLabel(){
        return priceLabel.getText();
    }
    private void setImageView(){
        Image image = this.product.getImage();
        imageView.setImage(image);
    }
    private void setProductName(){
        ProductName.setText(this.product.getName());
    }
}
