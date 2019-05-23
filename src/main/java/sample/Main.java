package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;

public class Main extends Application {
    private ConnectToDb db;

  //  private AutomatedEmails automatedEmails;
    @Override
    public void start(Stage primaryStage) throws IOException {
        db = new ConnectToDb();
        BasicConfigurator.configure();
        try {
         //   FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/MainLayout.fxml"));
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/MainLayout.fxml"));
            loader.setController(new MainController());

            primaryStage.setTitle("Ameenazon");
            primaryStage.setWidth(300);
            primaryStage.setHeight(300);

            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add("/StoreHomePageCSS.css");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
