package view;

import controller.TableRestaurantController;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.TableRestaurant;

public class TableRestaurantView extends Stage {

    public TableRestaurantView() {

        TableRestaurantController controller = new TableRestaurantController();

        ListView<TableRestaurant> listView = new ListView<>();
        listView.setItems(FXCollections.observableArrayList(
                controller.getAllTables()
        ));

        VBox root = new VBox(listView);
        root.setStyle("-fx-padding:20;");

        setTitle("Tables du restaurant");
        setScene(new Scene(root, 300, 400));
    }
}
