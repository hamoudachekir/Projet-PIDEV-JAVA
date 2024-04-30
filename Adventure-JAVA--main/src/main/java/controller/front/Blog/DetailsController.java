package controller.front.Blog;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import model.Blog.Commentaire;
import model.Blog.Publication;
import service.Blog.CommentaireService;
import service.Blog.PublicationService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DetailsController implements Initializable {
    @FXML
    private Label contenuLabel;

    @FXML
    private Label titre;

    @FXML
    private Label date;

    @FXML
    private Label contenu;

    @FXML
    private ImageView imageView;

    @FXML
    private ListView<String> ListComment;
    @FXML
    private TableColumn<Commentaire, String> comment;

    @FXML
    private TextArea AreaComment;
    public static Publication pub;

    private Publication publication;
    public void setContenu(String contenu) {
        contenuLabel.setText(contenu);
    }
    public void initialize() {
        afficherPublication();
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    private void afficherPublication() {
        if (pub != null) {
            titre.setText(pub.getTitre());
            date.setText(pub.getDatepub().toString());
            contenu.setText(pub.getContenu());

            String imageUrl = pub.getImage();
            if (imageUrl != null && !imageUrl.isEmpty()) {
                Image image = new Image(imageUrl);
                imageView.setImage(image);
            } else {
            }

            remplirListeCommentaires();
        }
    }

    private void remplirListeCommentaires() {
        ArrayList<Commentaire> listCommentaire = new ArrayList<>();
        try {
            for(var comm: new CommentaireService().select()){
                if(comm.getPublication().getId() == pub.getId())
                    listCommentaire.add(comm);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String> ListSrting = new ArrayList<>();
        for(var s: listCommentaire){
            ListSrting.add(s.getDescription());
        }

        ListComment.setItems(FXCollections.observableList(ListSrting));

    }

    @FXML
    void AddCommentaire() {
        CommentaireService comServ = new CommentaireService();
        try {
            comServ.add(new Commentaire(pub, 1, AreaComment.getText()));
            System.out.println("comment added");
            System.out.println(comServ.select());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void userCommentaire() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        afficherPublication();
    }
}
