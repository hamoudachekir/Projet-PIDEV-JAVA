package controller.back.Blog;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Blog.Commentaire;
import service.Blog.CommentaireService;

public class StatisticsController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Label totalCommentsLabel;
    @FXML
    private Label activeCommentsLabel;
    @FXML
    private Label inactiveCommentsLabel;

    CommentaireService cs = new CommentaireService();

    @FXML
    void initialize() throws SQLException {
        // Fetch statistics data and update labels
        int totalComments = cs.getTotalComments();
        int activeComments = cs.getActiveCommentsCount();
        int inactiveComments = totalComments - activeComments;

        totalCommentsLabel.setText("Total Comments: " + totalComments);
        activeCommentsLabel.setText("Active Comments: " + activeComments);
        inactiveCommentsLabel.setText("Inactive Comments: " + inactiveComments);
    }
    public void AfficherCommentaire (ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Back/Blog/AfficherCommentaire.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) totalCommentsLabel.getScene().getWindow();
        stage.getScene().setRoot(root);
    }
}
