package Store;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String name;
    private String companyName;
    private double price;
    private int quantity;
    private String description;
    private ImageView imageView;
    private String fileName;
    private String info;
    private Image image;

    public Product(String name, double price, int quantity, String fileNames)  {

        this.name = name;
        this.companyName = companyName;
        this.price = price;
        this.description = description;
        this.imageView = imageView;
        this.quantity = quantity;
        this.fileName = fileNames;
        System.out.println(this.fileName);
        System.out.println(fileNames);
        this.info = info;
        this.image = new Image(fileNames);

    }
    public void setVBoxName(String nameFromTable){
        this.name = nameFromTable;
    }
    public void setVBoxPrice(int priceFromTable){
        this.price = new Double(priceFromTable);
    }
    public void setVBoxQuantity(int quantityFromTable){
        this.quantity = quantityFromTable;
    }
    public void setVBoxFilename(String filenameFromTable){
        this.fileName = filenameFromTable;
    }
    public String getName(){
        return this.name;
    }
    public String getCompanyName(){
        return this.companyName;
    }
    public double getPrice(){
        return this.price;
    }
    public Image getImage(){
        return image;
    }
    public String getDescription(){ return this.description; }
    public ImageView getImageView(){ return this.imageView; }
    public int getQuantity(){return this.quantity;}
    public String getFileName(){return this.fileName;}
    public String getInfo(){return this.info;}
}
