package controller.front.Blog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.Blog.Publication;
import service.Blog.PublicationService;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AfficherPublicationFrontController {

    @FXML
    private FlowPane publicationFlowPane;

    @FXML
    private TextField searchTextField;

    private PublicationService ps = new PublicationService();

    @FXML
    public void initialize() {
        try {
            List<Publication> publicationsList = ps.select();

            for (Publication publication : publicationsList) {
                AnchorPane card = createPublicationCard(publication);
                publicationFlowPane.getChildren().add(card);
            }
        } catch (SQLException e) {
            System.err.println("Error loading products: " + e.getMessage());
        }

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterPublicationList(newValue);
        });
    }

    private AnchorPane createPublicationCard(Publication publication) {
        AnchorPane card = new AnchorPane();
        card.setPrefSize(180, 300);

        card.setStyle("-fx-border-color: #CCCCCC; " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 5px; " +
                "-fx-padding: 5px;");

        Label titreLabel = new Label(publication.getTitre());
        titreLabel.setLayoutX(15);
        titreLabel.setLayoutY(15);
        titreLabel.getStyleClass().add("publication-titre");

        Label dateLabel = new Label("Date: " + publication.getDatepub());
        dateLabel.setLayoutX(15);
        dateLabel.setLayoutY(50);
        dateLabel.getStyleClass().add("publication-date");

        Label contenuLabel = new Label("Contenu: " + publication.getContenu());
        contenuLabel.setLayoutX(15);
        contenuLabel.setLayoutY(85);
        contenuLabel.getStyleClass().add("publication-contenu");

        File file = new File(publication.getImage());
        if (file.exists()) {
            Image image = new Image(file.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);
            imageView.setLayoutX(15);
            imageView.setLayoutY(120);
            imageView.getStyleClass().add("publication-image");
            card.getChildren().add(imageView);
        }

        Button detailsButton = new Button("Détails");
        detailsButton.setLayoutX(15);
        detailsButton.setLayoutY(275);
        detailsButton.setOnAction((event) -> {
            try {
                DetailsController.pub = publication;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Front/Blog/DetailsPublicationF.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = new Stage();
                stage.setScene(scene);

                stage.show();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });

        card.getChildren().addAll(titreLabel, dateLabel, contenuLabel, detailsButton);
        return card;
    }

    private void filterPublicationList(String searchText) {
        for (Node node : publicationFlowPane.getChildren()) {
            if (node instanceof AnchorPane) {
                AnchorPane card = (AnchorPane) node;
                boolean isVisible = false;

                for (Node child : card.getChildren()) {
                    if (child instanceof Label) {
                        Label label = (Label) child;
                        if (label.getStyleClass().contains("publication-titre")) {
                            isVisible = label.getText().toLowerCase().contains(searchText.toLowerCase());
                            break;
                        }
                    }
                }

                card.setVisible(isVisible);
                card.setManaged(isVisible);
            }
        }
    }
}
