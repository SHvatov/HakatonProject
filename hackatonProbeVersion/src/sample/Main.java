package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            primaryStage.setTitle("FaceHunter");
            primaryStage.setScene(new Scene(root, 788, 589));
            primaryStage.setResizable(false);
            primaryStage.show();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
