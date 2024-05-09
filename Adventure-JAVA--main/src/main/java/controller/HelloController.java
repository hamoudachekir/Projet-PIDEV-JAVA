package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloController implements Initializable {

    @FXML
    private Button activities;

    @FXML
    private Button blogs;

    @FXML
    private StackPane contentArea;

    @FXML
    private Button home;

    @FXML
    private Button shop;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/home.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(ModuleLayer.Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        home.setStyle("-fx-background-color: #2A332D;-fx-text-fill:  #ffffff;");

    }

    public void home(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/home.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        home.setStyle("-fx-background-color: #2A332D;-fx-text-fill:  #ffffff;");
        activities.setStyle("-fx-background-color: #1D231F;-fx-text-fill:  #ffffff;");
        blogs.setStyle("-fx-background-color: #1D231F;-fx-text-fill:  #ffffff;");
        shop.setStyle("-fx-background-color: #1D231F;-fx-text-fill:  #ffffff;");
    }

    public void activities(javafx.event.ActionEvent actionEvent) throws IOException {
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/activities.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/activitiesDashboard.fxml"));
        Parent fxml = loader.load();
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        activities.setStyle("-fx-background-color: #2A332D;-fx-text-fill:  #ffffff;");
        home.setStyle("-fx-background-color: #1D231F;-fx-text-fill:  #ffffff;");
        blogs.setStyle("-fx-background-color: #1D231F;-fx-text-fill:  #ffffff;");
        shop.setStyle("-fx-background-color: #1D231F;-fx-text-fill:  #ffffff;");
    }


}