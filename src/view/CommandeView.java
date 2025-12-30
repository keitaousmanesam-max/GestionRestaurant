package view;

import controller.CommandeController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Commande;
import model.CommandePlat;

public class CommandeView extends Stage {

    private final CommandeController controller = new CommandeController();

    public CommandeView() {
        // TableView pour afficher les commandes
        TableView<Commande> tableCommandes = new TableView<>();

        TableColumn<Commande, Number> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getIdCommande()));

        TableColumn<Commande, String> colDate = new TableColumn<>("Date");
        colDate.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDateCommande().toString()));

        TableColumn<Commande, String> colStatut = new TableColumn<>("Statut");
        colStatut.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatut()));

        tableCommandes.getColumns().addAll(colId, colDate, colStatut);
        tableCommandes.setItems(FXCollections.observableArrayList(controller.getCommandes()));

        // TableView pour afficher les plats liés à une commande
        TableView<CommandePlat> tablePlats = new TableView<>();

        TableColumn<CommandePlat, String> colPlatNom = new TableColumn<>("Plat");
        colPlatNom.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPlat().getNomPlat()));

        TableColumn<CommandePlat, Number> colQuantite = new TableColumn<>("Quantité");
        colQuantite.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getQuantite()));

        tablePlats.getColumns().addAll(colPlatNom, colQuantite);

        // Champs de saisie
        TextField txtStatut = new TextField();
        txtStatut.setPromptText("Statut (EN COURS / SERVIE)");

        // Boutons
        Button btnAjouter = new Button("Ajouter");
        btnAjouter.setOnAction(e -> {
            Commande c = new Commande();
            c.setStatut(txtStatut.getText());
            controller.addCommande(c);
            tableCommandes.setItems(FXCollections.observableArrayList(controller.getCommandes()));
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Commande ajoutée !");
        });

        Button btnModifier = new Button("Modifier");
        btnModifier.setOnAction(e -> {
            Commande c = tableCommandes.getSelectionModel().getSelectedItem();
            if (c != null) {
                c.setStatut(txtStatut.getText());
                controller.updateCommande(c);
                tableCommandes.setItems(FXCollections.observableArrayList(controller.getCommandes()));
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Commande modifiée !");
            }
        });

        Button btnSupprimer = new Button("Supprimer");
        btnSupprimer.setOnAction(e -> {
            Commande c = tableCommandes.getSelectionModel().getSelectedItem();
            if (c != null) {
                controller.deleteCommande(c.getIdCommande());
                tableCommandes.setItems(FXCollections.observableArrayList(controller.getCommandes()));
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Commande supprimée !");
            }
        });

        // Sélection d’une commande → affiche ses plats
        tableCommandes.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                tablePlats.setItems(FXCollections.observableArrayList(controller.getPlatsByCommande(newSel.getIdCommande())));
                txtStatut.setText(newSel.getStatut());
            }
        });

        // Layout
        HBox hBoxForm = new HBox(10, txtStatut, btnAjouter, btnModifier, btnSupprimer);
        VBox root = new VBox(15, tableCommandes, tablePlats, hBoxForm);
        root.setPadding(new Insets(20));

        setTitle("Gestion des Commandes");
        setScene(new Scene(root, 800, 500));
    }

    // ✅ Méthode utilitaire pour afficher des messages
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
