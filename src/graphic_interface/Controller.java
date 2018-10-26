package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import java.io.File;
import javafx.stage.FileChooser;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addPhotoButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button helpButton;

    @FXML
    void addphoto(ActionEvent event) throws Exception{
        Stage stage = (Stage)addPhotoButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("addphoto.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage1 = new Stage();
        stage1.setOpacity(1);

        stage1.setTitle("FaceHunter");
        stage1.setScene(new Scene(root, 788, 589));
        stage1.setResizable(false);
        stage1.showAndWait();

    }



    @FXML
    void closeApp(ActionEvent event) throws Exception{
        Stage stage = (Stage)exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void askHelp(ActionEvent event) throws Exception {
        Stage stage = (Stage)helpButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("instruction.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage1 = new Stage();
        stage1.setOpacity(1);

        stage1.setTitle("FaceHunter");
        stage1.setScene(new Scene(root, 788, 589));
        stage1.setResizable(false);
        stage1.showAndWait();
    }


    @FXML
    void initialize(MouseEvent event) {

    }

    @FXML
    void initialize() {

    }
}
