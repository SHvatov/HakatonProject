//package sample.assets;

package sample;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


import javafx.event.ActionEvent;



public class AddController {

    @FXML
    private ResourceBundle resources;



    @FXML
    private URL location;





    @FXML
    private Button addphotocommonButton;

    @FXML
    private Button startButton;

    @FXML
    private ImageView jpeg1Button;

    @FXML
    private ImageView jpeg2Button;

    @FXML
    private Button backButton;

    @FXML
    void addingphoto(ActionEvent event)  {


    }


    @FXML
    void backMenue(ActionEvent event) throws Exception{
        Stage stage = (Stage)backButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("menu.fxml"));

        Parent root = fxmlLoader.load();
        Stage stage1 = new Stage();
        stage1.setOpacity(1);

        stage1.setTitle("FaceHunter");
        stage1.setScene(new Scene(root, 788, 589));
        stage1.setResizable(false);
        stage1.showAndWait();

    }

    @FXML
    void addphoto1(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("addphoto.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage1 = new Stage();
        stage1.setOpacity(1);

        stage1.setTitle("FaceHunter");
        stage1.setScene(new Scene(root, 788, 589));
        stage1.setResizable(false);
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Pictures");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        File chosenFile = fileChooser.showOpenDialog(null);
        String path;
        if(chosenFile != null) {
            path = chosenFile.getPath();
        } else {
//default return value
            path = null;
        }

    }

    @FXML
    void addphoto2(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("addphoto.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage1 = new Stage();
        stage1.setOpacity(1);

        stage1.setTitle("FaceHunter");
        stage1.setScene(new Scene(root, 788, 589));
        stage1.setResizable(false);
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Pictures");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        File chosenFile = fileChooser.showOpenDialog(null);
        String path;
        if(chosenFile != null) {
            path = chosenFile.getPath();
        } else {
//default return value
            path = null;
        }

    }

    @FXML
    void start(ActionEvent event) throws Exception {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("FaceHunter");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Invalid format!The photo must be in JPEG format!");

        alert.showAndWait();

    }


    @FXML
    void initialize() {


    }
}
