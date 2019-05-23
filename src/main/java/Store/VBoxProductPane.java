package Store;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import sample.MainController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VBoxProductPane extends StackPane {

    private MainController mainController;
    private String name;
    private Image image;
    private String fileName;
    private double price;
    private int quantity;

    private Product product;
   // private Label label = new Label();

    public VBoxProductPane(Product product){

        setPrefSize(250, 275);
      //  this.product = product;
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.fileName = product.getFileName();
    //    button = new Button();
        System.out.println(fileName + " ???????");
     //   this.image = new Image(this.fileName);
        this.product = new Product(this.name, this.price, this.quantity, this.fileName);
        setImage();
        setButton();
        setLabel();
      //  initialize();
      //  label.setAlignment(Pos.BASELINE_CENTER);
//        setOnMouseEntered(e -> {
//                    button.setVisible(true);
//                });
//        setOnMouseExited(e -> {
//            button.setVisible(false);
//        });
//        button.setText("Explore");
//        getChildren().add(button);
//        button.setVisible(false);
//        button.setOnAction(e -> {
//            button.setVisible(true);
//        });
//        button.setOnAction(e -> {
//            try {
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("/ProductLayout.fxml"));
//                Stage secondaryStage = new Stage();
//                loader.setController(new ProductPage(mainController, product));
//                secondaryStage.setTitle(product.getName());
//                secondaryStage.setHeight(450);
//                secondaryStage.setWidth(600);
//
//                Scene scene = new Scene(loader.load());
//                secondaryStage.setScene(scene);
//                secondaryStage.show();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        });

    }
    private void initialize(){
        setImage();
        setButton();
        setLabel();
      //  label.setLayoutY(275);
    }
    private void setButton(){
        Button button = new Button("Explore");
    //    button.setText("Explore");
        getChildren().add(button);
        button.setAlignment(Pos.CENTER);
        button.setVisible(false);
        button.setOnAction(e -> {
            button.setVisible(true);
        });
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
        setOnMouseEntered(e -> {
            button.setVisible(true);
        });
        setOnMouseExited(e -> {
            button.setVisible(false);
        });
    }
    private void setLabel(){
        Label label = new Label(getLabelText());
        setAlignment(label, Pos.BOTTOM_CENTER);
  //      label.setLayoutY(250);
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.CENTER);
        getChildren().add(label);
        setAlignment(label, Pos.BOTTOM_CENTER);
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
    private void setImage() {
        ImageView imageViews = new ImageView();
        imageViews.setImage(this.product.getImage());
        setAlignment(imageViews, Pos.TOP_RIGHT);
       // System.out.println(getImage().getHeight());
       // setAlignment(imageViews, Pos.TOP_CENTER);
        imageViews.setFitHeight(250);
        imageViews.setFitWidth(250);
       // imageViews.setY(0);
        getChildren().add(imageViews);
    }
}
