package controller.back.Blog;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Blog.Commentaire;
import model.Blog.Publication;
import service.Blog.CommentaireService;

public class AfficherCommentaire {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Commentaire> table1view;

    @FXML
    private TableColumn<Commentaire, Date> dateCCol;

    @FXML
    private TableColumn<Commentaire, Boolean> ActionCol;

    @FXML
    private Label Welcome;

    @FXML
    private TableColumn<Commentaire, Publication> pubCol;

    @FXML
    private TableColumn<Commentaire, Integer> userCol;

    @FXML
    private TableColumn<Commentaire, String> descriptionCol;
    CommentaireService cs = new CommentaireService();
    ObservableList<Commentaire> obsC;
    @FXML
    void cd107b(ActionEvent event) {

    }

    @FXML
    void initialize() {
        try {
            List<Commentaire> list = cs.select();
            obsC= FXCollections.observableArrayList(list);
            table1view.setItems(obsC);
            dateCCol.setCellValueFactory(new PropertyValueFactory<>("datecomnt"));
            ActionCol.setCellValueFactory(new PropertyValueFactory<>("active"));
            pubCol.setCellValueFactory(new PropertyValueFactory<>("publication"));
            userCol.setCellValueFactory(new PropertyValueFactory<>("user_id"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));


        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }
    public void setData(String msg){
        Welcome.setText("Welcome" + msg);
    }
}
