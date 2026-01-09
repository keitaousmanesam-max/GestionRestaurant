package view;

import controller.PlatController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Plat;

public class PlatView extends Stage {

    private final PlatController controller = new PlatController();

    public PlatView() {
        // TableView pour afficher les plats
        TableView<Plat> tablePlats = new TableView<>();

        TableColumn<Plat, Number> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getIdPlat()));

        TableColumn<Plat, String> colNom = new TableColumn<>("Nom");
        colNom.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNomPlat()));

        TableColumn<Plat, String> colCategorie = new TableColumn<>("Catégorie");
        colCategorie.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategorie()));

        TableColumn<Plat, Number> colPrix = new TableColumn<>("Prix");
        colPrix.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrix()));

        TableColumn<Plat, String> colDispo = new TableColumn<>("Disponibilité");
        colDispo.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDisponibilite()));

        tablePlats.getColumns().addAll(colId, colNom, colCategorie, colPrix, colDispo);
        tablePlats.setItems(FXCollections.observableArrayList(controller.getPlats())); // ✅ correction

        // Champs de saisie
        TextField txtNom = new TextField();
        txtNom.setPromptText("Nom du plat");

        TextField txtCategorie = new TextField();
        txtCategorie.setPromptText("Catégorie");

        TextField txtPrix = new TextField();
        txtPrix.setPromptText("Prix");

        ChoiceBox<String> choiceDispo = new ChoiceBox<>(FXCollections.observableArrayList("Disponible", "Indisponible"));
        choiceDispo.setValue("Disponible");

        // Boutons
        Button btnAjouter = new Button("Ajouter");
        btnAjouter.setOnAction(e -> {
            try {
                double prix = Double.parseDouble(txtPrix.getText());
                Plat p = new Plat(txtNom.getText(), txtCategorie.getText(), prix, choiceDispo.getValue());
                if (controller.addPlat(p)) {
                    tablePlats.setItems(FXCollections.observableArrayList(controller.getPlats()));
                    showAlert(Alert.AlertType.INFORMATION, "Succès", "Plat ajouté avec succès !");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible d'ajouter le plat.");
                }
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Le prix doit être un nombre valide.");
            }
        });

        Button btnModifier = new Button("Modifier");
        btnModifier.setOnAction(e -> {
            Plat p = tablePlats.getSelectionModel().getSelectedItem();
            if (p != null) {
                try {
                    double prix = Double.parseDouble(txtPrix.getText());
                    p.setNomPlat(txtNom.getText());
                    p.setCategorie(txtCategorie.getText());
                    p.setPrix(prix);
                    p.setDisponibilite(choiceDispo.getValue());
                    if (controller.updatePlat(p)) {
                        tablePlats.setItems(FXCollections.observableArrayList(controller.getPlats()));
                        showAlert(Alert.AlertType.INFORMATION, "Succès", "Plat modifié avec succès !");
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de modifier le plat.");
                    }
                } catch (NumberFormatException ex) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Le prix doit être un nombre valide.");
                }
            }
        });

        Button btnSupprimer = new Button("Supprimer");
        btnSupprimer.setOnAction(e -> {
            Plat p = tablePlats.getSelectionModel().getSelectedItem();
            if (p != null) {
                if (controller.deletePlat(p.getIdPlat())) {
                    tablePlats.setItems(FXCollections.observableArrayList(controller.getPlats()));
                    showAlert(Alert.AlertType.INFORMATION, "Succès", "Plat supprimé avec succès !");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de supprimer le plat.");
                }
            }
        });

        // Sélection dans la table pour remplir les champs
        tablePlats.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtNom.setText(newSel.getNomPlat());
                txtCategorie.setText(newSel.getCategorie());
                txtPrix.setText(String.valueOf(newSel.getPrix()));
                choiceDispo.setValue(newSel.getDisponibilite());
            }
        });

        // Layout
        HBox hBoxForm = new HBox(10, txtNom, txtCategorie, txtPrix, choiceDispo, btnAjouter, btnModifier, btnSupprimer);
        VBox root = new VBox(15, tablePlats, hBoxForm);
        root.setPadding(new Insets(20));

        setTitle("Gestion des Plats");
        setScene(new Scene(root, 800, 400));
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
