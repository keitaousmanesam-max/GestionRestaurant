package view;

import controller.CommandePlatController;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CommandePlat;

public class CommandePlatView extends Stage {

    public CommandePlatView(int idCommande) {

        CommandePlatController controller = new CommandePlatController();

        ListView<CommandePlat> listView = new ListView<>();
        listView.setItems(FXCollections.observableArrayList(
                controller.getPlatsParCommande(idCommande)
        ));

        VBox root = new VBox(listView);
        root.setStyle("-fx-padding:20;");

        setTitle("Plats de la commande");
        setScene(new Scene(root, 400, 450));
    }
}
