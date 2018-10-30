//package sample.assets;

package sample;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.text.AttributedCharacterIterator;
import java.util.ResourceBundle;

import java.awt.event.*;
import java.awt.image.*;

import com.sun.javafx.iio.ImageLoader;
import computer_vision.CompareFaces;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.*;


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

    private String pathSrc = "";

    private String pathDest = "";




    @FXML
    void start(ActionEvent event) throws Exception {


        if (pathDest == "" || pathSrc == "") {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("FaceHunter");

            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Error! You need upload photos!");

            alert.showAndWait();
        }
        else
        {
            Stage stage = new Stage(); //(Stage)startButton.getScene().getWindow();
            // start code
            if (CompareFaces.compare(pathSrc, pathDest)) {
                try {
                //Image picture = ImageIO.read(new File("resources/out_photo/result.jpg"));

                //Creating a Group object
                Image image = new Image(new FileInputStream("resources/out_photo/result.jpg"));

                //Setting the image view
                ImageView imageView = new ImageView(image);

                //Setting the position of the image
                imageView.setX(50);
                imageView.setY(25);

                //setting the fit height and width of the image view
                imageView.setFitHeight(455);
                imageView.setFitWidth(500);

                //Setting the preserve ratio of the image view
                imageView.setPreserveRatio(true);
                Group root = new Group(imageView);
                    Scene scene = new Scene(root, 600, 500);
                    stage.setScene(scene);
                //Creating a scene object
                /*
                    FXMLLoader fxmlLoader = new
                            FXMLLoader(getClass().getResource("addphoto.fxml"));
                    Parent root1 = fxmlLoader.load();
                    Stage stage1 = new Stage();
                    //stage1.setOpacity(1);

                    stage1.setTitle("FaceHunter");
                    stage1.setScene(new Scene(root1, 788, 589));
                    stage1.setResizable(false);
                    stage1.showAndWait();
                    stage.setOpacity(2);*/
                //Setting title to the Stage
                stage.setTitle("Loading an image");


                //Adding scene to the stage


                //Displaying the contents of the stage
                    stage.setAlwaysOnTop(true);
                    stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

                } catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
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
        if(chosenFile != null) {
            pathSrc = chosenFile.getPath();
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
        if(chosenFile != null) {
            pathDest = chosenFile.getPath();
        }
    }


    @FXML
    void initialize() {


    }
}
