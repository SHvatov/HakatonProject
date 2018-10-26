package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

public class HelpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    void backMenue(ActionEvent event) throws Exception {
        Stage stage = (Stage)backButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage1 = new Stage();
        stage1.setOpacity(1);

        stage1.setTitle("FaceHunter");
        stage1.setScene(new Scene(root, 788, 589));
        stage1.setResizable(false);
        stage1.showAndWait();


    }


    @FXML
    void initialize() {


    }
}
