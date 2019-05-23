package sample;

import Payment.PaymentPage;
import Payment.ProcessCreditCard;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.TimeZone;

import static java.lang.Math.toIntExact;

public class ConnectToDb {

    private Connection conn = null;

//    @FXML
//    javafx.scene.control.TextField username;
    @FXML
    javafx.scene.control.TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField password;
    @FXML private PasswordField passwordConfirm;
    private String dbURL = "jdbc:mysql://localhost:3306/Test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//    // the dburl uses the schema name
//    private String username1 = "root";
//    private String password1 = "B@tman15";

    public ConnectToDb(){
       // String dbURL = "jdbc:mysql://localhost:3306/Test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        // the dburl uses the schema name
        String username = "root";
        String password = "b@tman15";

        {
            Connection conn = null;
            try {
             //   timeZone = TimeZone.getTimeZone(timeZone);
                this.conn = DriverManager.getConnection(dbURL, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (this.conn != null) {
                System.out.println("Connected");
            }
        }
    }
    // delete saved card row
    public void deleteCard(String username) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM PaymentInfo WHERE username='" + username
            + "'");
        preparedStatement.executeUpdate();
        System.out.println("deleted");
    }

    // for edit password for profile
    public boolean checkIfPasswordExists(String oldPassword) throws SQLException {
        String otherPassword;
        String SQL = "SELECT password FROM users WHERE password='" + oldPassword + "'";
        PreparedStatement preparedStatement = conn.prepareStatement(SQL);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()){
            otherPassword = rs.getString("password");
            if (oldPassword.equals(otherPassword)){
                return true;
            }
        }
        preparedStatement.close();
        rs.close();
        return false;
    }
    // saving credit card info
    public boolean saveCreditCardInfo(long cardNumbered, String cardholderName, String username) throws SQLException {

        String dbUsername = username;
        String cardnumber = Long.toString(cardNumbered);
        String SQL = "INSERT INTO PaymentInfo(cardholder_name, cardnumber, username)" +
                "VALUES(?, ?, ?)";
        PreparedStatement preparedStatement = getConn().prepareStatement(SQL);
        preparedStatement.setString(1, cardholderName);
        preparedStatement.setString(2, cardnumber);
        preparedStatement.setString(3, dbUsername);

        int rs = preparedStatement.executeUpdate();
        if (rs > 0){
            return true;
        }
        preparedStatement.close();

        return false;
    }

    // for changing their password
    public boolean changePassword(String oldPassword, String newPassword) throws SQLException {
        // get the username here??
        String something;
        String SQL = "UPDATE users SET password ='" + newPassword
                + "' WHERE password= '" + oldPassword + "'";
        PreparedStatement preparedStatement = conn.prepareStatement(SQL);
        int x = preparedStatement.executeUpdate();
        if (x > 0){
       // if (rs.next()){
            System.out.println("password updated");
            return true;
        }
        preparedStatement.close();
        //rs.close();
        return false;
    }

    public Connection getConn(){
        return conn;
    }
    public String getDbURL(){
        return dbURL;
    }

    // for registering users
    public boolean checkIfUsernameExists(String username, String Passwordes) throws SQLException {
        String usernameCounter;
        String passwordCounter;
        String SQL  = "SELECT * FROM users WHERE username='" + username +  "' and password='"
          + Passwordes + "'";
        PreparedStatement preparedStatement = conn.prepareStatement(SQL);
        ResultSet rs = preparedStatement.executeQuery();
        System.out.println(SQL);
        if (rs.next()){
            usernameCounter = rs.getString("username");
            passwordCounter = rs.getString("password");
            if (usernameCounter.equals(username) && passwordCounter.equals(Passwordes)) {
                System.out.println("username is here");
                System.out.println("password is here");
//                preparedStatement.close();
//                rs.close();
                return true;
            }
        } else if (username.isEmpty() || Passwordes.isEmpty()) {
            System.out.println("username is empty");
            return false;
        } else if (!rs.next()){
            System.out.println(rs.next());
            System.out.println("!rs.next");
        }
        preparedStatement.close();
    //    rs.close();
        System.out.println("returned false here because it can't be found");
        return false;
    }
    // for the lost password
    public String getLostPassword(String searchedUsername) throws SQLException{
        String userNameChecker;
        String passWordChecker;
        String SQL = "SELECT password FROM users WHERE username='" + searchedUsername + "'";
        PreparedStatement preparedStatement = conn.prepareStatement(SQL);
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()){
                passWordChecker = rs.getString("password");
                System.out.println("username is here");
                return passWordChecker + " we could email this to you but we didn't";
        }
        System.out.println("returned false because username is not here");
        return "Your password is not here";
    }
    // for registering the seller account
    public boolean checkIfBusinessNameExists(String username, String password1) throws SQLException{
        String usernameCounter;
        String passwordCounter;
        String SQL  = "SELECT * FROM sellers WHERE businessName='" + username +  "' and password='"
                + password1 + "'";
        PreparedStatement preparedStatement = conn.prepareStatement(SQL);
        ResultSet rs = preparedStatement.executeQuery();
        System.out.println(SQL);
        if (rs.next()){
            usernameCounter = rs.getString("businessName");
            passwordCounter = rs.getString("password");
            if (usernameCounter.equals(username) && passwordCounter.equals(password1)) {
                System.out.println("username is here");
                System.out.println("password is here");
                return true;
            }
        } else if (username.isEmpty() || password1.isEmpty()) {
            System.out.println("username is empty");
            return false;
        } else if (!rs.next()){
            System.out.println(rs.next());
            System.out.println("!rs.next");
        }
        System.out.println("returned false here because it can't be found");
        return false;
    }
}