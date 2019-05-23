package Store;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import sample.MainController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductPaneHomePage extends StackPane {

    //   private Pane productPaneHomepagePane = new Pane();
    private MainController mainController;
//    @FXML private ImageView quickLookImageView = new ImageView();
//    @FXML private Label quickLookLabel = new Label();
//    @FXML private Button quickLookBtn = new Button("Buy Now!");
    private Product product;
    //  private File file;
    private Image image;
    private String fileName;
    private String name;
    private double price;
    private int quantity;
    private Button button = new Button();

    private Product waterProduct = new Product("Water Rights to the Nation of Angola", 999.00, 2,
            "/0.png");
    private Product pollutedWater = new Product("Russian Water Now With 80% More Industrial Runoff!", 4.99, 25,
            "/1.png");
    private Product rawStreamWater = new Product("raw water", 24.99, 50, "/2.png");
    private Product rawStreamWater2 = new Product("rawer water", 24.99, 50, "/3.png");
    private Product rawStreamWater1 = new Product("even rawer water", 24.99, 50, "/4.png");
    private List<Product> productList = new ArrayList<>();
    private Label label = new Label();

    public ProductPaneHomePage(Product product) {

//        setMinSize(200, 150);
//        setPrefSize(200, 150);
//        setMaxSize(200, 150);
        // was height 225

        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.fileName = product.getFileName();
        this.product = new Product(this.name, this.price, this.quantity, this.fileName);
        productList.add(waterProduct);
        productList.add(pollutedWater);
        productList.add(rawStreamWater);
        productList.add(rawStreamWater1);
        productList.add(rawStreamWater2);
    //    label.setVisible(false);
        button.setVisible(false);
        setImage();
        setButton();
        setLabel();

        setOnMouseEntered(e -> {
            button.setVisible(true);
            label.setVisible(true);
        });
        setOnMouseExited(e -> {
            label.setVisible(false);
            button.setVisible(false);
        });
        setAlignment(button, Pos.CENTER);
        setAlignment(Pos.TOP_CENTER);
    }

    private void setButton(){
        button.setText("Explore");
        getChildren().add(button);
        button.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/ProductLayout.fxml"));
                Stage secondaryStage = new Stage();
                loader.setController(new ProductPage(mainController, product));
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
    private void setLabel(){
        label = new Label(getLabelText());
        label.setVisible(false);
        setAlignment(label, Pos.BOTTOM_CENTER);
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.CENTER);
        getChildren().add(label);
    }
    private String getLabelText(){
        if (this.product.getName() == null){
            System.out.println("name is null");
        }
        return this.product.getName();
    }
    private Image getImage(){
        Image image = this.product.getImage();
        return image;
    }
    private void setImage(){
        ImageView imageViews = new ImageView();
        imageViews.setImage(getImage());
        imageViews.setFitHeight(165);
        imageViews.prefHeight(200);
        imageViews.maxHeight(200);
        imageViews.minHeight(200);
        imageViews.prefWidth(165);
        imageViews.minWidth(165);
        imageViews.maxWidth(165);
        imageViews.setFitWidth(200);
        imageViews.setY(0);
        getChildren().add(imageViews);
    }
}
