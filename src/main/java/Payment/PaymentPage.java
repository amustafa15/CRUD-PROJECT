package Payment;

import Store.Product;
import Store.ProductPage;
import com.sun.tools.javac.util.Convert;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import sample.ConnectToDb;

import java.math.BigDecimal;
import java.sql.SQLException;

public class PaymentPage {

    @FXML private TextField fullNameTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField cityTextField;
    @FXML private TextField zipCode;
    @FXML private TextField country;
    @FXML private TextField phoneNumber;

    @FXML private TextField cardNumberField;
    @FXML private TextField cardHolderName;
    @FXML private Button useThisButton;
    @FXML private ComboBox comboBoxDate;
    @FXML private ComboBox comboBoxYear;

    @FXML private Label nameLabel;
    @FXML private Label addressLabel;
    @FXML private Label addressTwoLabel;
    @FXML private CheckBox checkBox;
    @FXML private Button placeOrderButton;
    @FXML private Label errorMessageCard;

    @FXML private ImageView imageView;
    @FXML private Label productName;
    @FXML private Label price;
    @FXML private TitledPane confirmTitledPane;
    private Boolean cardAccepted = false;

    private Product product;
    private ProductPage productPage;
    private double totalPrice;
    private ConnectToDb connectToDb = new ConnectToDb();
    private String username = "ni";

    public PaymentPage(Product product, String total){
        this.product = product;
        String newTotal = total.replace("$", "");
        totalPrice = Double.valueOf(newTotal);
    }
    @FXML
    private void initialize(){

        errorMessageCard.setVisible(false);
        fillComboBoxDate();
        fillComboBoxYear();
        confirmTitledPane.setOnMouseClicked(e -> {
            setConfrimName();
            setAddressFields();
            setProductStuff();
            setImageView();
        });
        useThisButton.setOnAction(e -> {
            long cardNumber = Long.valueOf(cardNumberField.getText());
            ProcessCreditCard processCreditCard = new ProcessCreditCard(cardNumber);
            if (processCreditCard.isValid(cardNumber) && !comboBoxDate.getSelectionModel().isEmpty()
                && !comboBoxYear.getSelectionModel().isEmpty() && !cardHolderName.getText().isEmpty()){
                cardAccepted = true;
                errorMessageCard.setVisible(false);
                Long cardNumbered = Long.parseLong(cardNumberField.getText());
                String name = cardHolderName.getText();
                try {
                    connectToDb.saveCreditCardInfo(cardNumbered, name, username);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Credit card info saved");
                    alert.setHeaderText("We took the liberty of saving your credit card info for you.");
                    alert.setContentText("I mean you could delete it if you want");
                    alert.showAndWait();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            } else {
                errorMessageCard.setText("INVALID CARD DEADBEAT!");
                errorMessageCard.setVisible(true);
            }
        });
        placeOrderButton.setOnAction(e -> {
            if (checkBox.isSelected() && cardAccepted == true){
                checkBox.getScene().getWindow().hide();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Order Successfully Placed!");
                alert.setHeaderText("Your order has been successfully placed");
                alert.setContentText("Check your email for a receipt");
                alert.showAndWait();
            }
        });
    }
    private void setConfrimName(){
        nameLabel.setText(fullNameTextField.getText());
    }
    private void setAddressFields(){
        addressLabel.setText(addressTextField.getText());
        addressTwoLabel.setText(cityTextField.getText() + ", " + country.getText() + ", " + zipCode.getText());
    }
    private void setProductStuff(){
        productName.setText(this.product.getName());
        price.setText(totalPrice + "");
      //  System.out.println(productPage.getPrice());
    }
    private void setImageView(){
        imageView.setImage(this.product.getImage());
    }
    private void fillComboBoxDate(){
        for (int i = 1; i < 13; i++){
            if (i < 10){
                comboBoxDate.getItems().add("0" + i);
            } else {
                comboBoxDate.getItems().add(i);
            }
        }
    }
    public long getCardnumber(){
        long cardnumber = Long.valueOf(cardNumberField.getText());
        return cardnumber;
    }
    public String fullNameText(){
        return fullNameTextField.getText();
    }
    private void fillComboBoxYear(){
        for (int i = 0; i < 15; i++){
            int sum = 2019 + i;
            comboBoxYear.getItems().add(sum);
        }
    }
}
