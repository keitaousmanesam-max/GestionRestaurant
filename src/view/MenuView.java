package view;

import controller.MenuController;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Menu;

public class MenuView extends Stage {

    private final MenuController controller = new MenuController();

    public MenuView() {

        /* ===================== TABLE ===================== */
        TableView<Menu> table = new TableView<>();
        table.setStyle("-fx-background-color:#ffffff; -fx-border-color:#2E8B57; -fx-border-radius:8;");

        TableColumn<Menu, String> colNom = new TableColumn<>("Nom du menu");
        colNom.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getNomMenu()));

        TableColumn<Menu, String> colCategorie = new TableColumn<>("Catégorie");
        colCategorie.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getCategorie()));

        table.getColumns().addAll(colNom, colCategorie);
        table.setItems(FXCollections.observableArrayList(controller.getMenus()));

        /* ===================== FORMULAIRE ===================== */
        TextField txtNom = new TextField();
        txtNom.setPromptText("Nom du menu");
        styleField(txtNom);

        TextField txtCategorie = new TextField();
        txtCategorie.setPromptText("Catégorie");
        styleField(txtCategorie);

        Label lblMessage = new Label();

        /* ===================== BOUTONS ===================== */
        Button btnAjouter = new Button("Ajouter");
        Button btnModifier = new Button("Modifier");
        Button btnSupprimer = new Button("Supprimer");

        styleButton(btnAjouter);
        styleButton(btnModifier);
        styleButton(btnSupprimer);

        /* ===================== AJOUTER ===================== */
        btnAjouter.setOnAction(e -> {
            if (txtNom.getText().isEmpty() || txtCategorie.getText().isEmpty()) {
                showMessage(lblMessage, "❌ Veuillez remplir tous les champs", false);
                return;
            }

            Menu m = new Menu();
            m.setNomMenu(txtNom.getText());
            m.setCategorie(txtCategorie.getText());

            if (!controller.addMenu(m)) {
                showMessage(lblMessage, "❌ Menu déjà existant", false);
                return;
            }

            showMessage(lblMessage, "✅ Menu ajouté", true);
            table.setItems(FXCollections.observableArrayList(controller.getMenus()));
            clearForm(txtNom, txtCategorie);
        });

        /* ===================== SELECTION TABLE ===================== */
        table.getSelectionModel().selectedItemProperty().addListener((obs, o, m) -> {
            if (m != null) {
                txtNom.setText(m.getNomMenu());
                txtCategorie.setText(m.getCategorie());
            }
        });

        /* ===================== MODIFIER ===================== */
        btnModifier.setOnAction(e -> {
            Menu m = table.getSelectionModel().getSelectedItem();
            if (m == null) return;

            m.setNomMenu(txtNom.getText());
            m.setCategorie(txtCategorie.getText());

            controller.updateMenu(m);
            showMessage(lblMessage, "✏ Menu modifié", true);
            table.setItems(FXCollections.observableArrayList(controller.getMenus()));
            clearForm(txtNom, txtCategorie);
        });

        /* ===================== SUPPRIMER ===================== */
        btnSupprimer.setOnAction(e -> {
            Menu m = table.getSelectionModel().getSelectedItem();
            if (m == null) return;

            controller.deleteMenu(m.getIdMenu());
            showMessage(lblMessage, "🗑 Menu supprimé", true);
            table.setItems(FXCollections.observableArrayList(controller.getMenus()));
            clearForm(txtNom, txtCategorie);
        });

        /* ===================== LAYOUT ===================== */
        VBox root = new VBox(12, table, txtNom, txtCategorie, btnAjouter, btnModifier, btnSupprimer, lblMessage);
        root.setStyle("-fx-padding:20; -fx-background-color:#F4F6F9; -fx-spacing:10;");

        setTitle("📋 Gestion des Menus");
        setScene(new Scene(root, 500, 550));

        /* Animation d’apparition */
        FadeTransition ft = new FadeTransition(Duration.seconds(1.5), root);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    /* ===================== OUTILS ===================== */
    private void showMessage(Label label, String text, boolean success) {
        label.setText(text);
        label.setStyle(success
                ? "-fx-text-fill:green; -fx-font-weight:bold;"
                : "-fx-text-fill:red; -fx-font-weight:bold;");

        FadeTransition ft = new FadeTransition(Duration.seconds(1.2), label);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    private void styleButton(Button b) {
        b.setStyle("""
                -fx-background-color:#2E8B57;
                -fx-text-fill:white;
                -fx-font-weight:bold;
                -fx-background-radius:8;
                -fx-padding:8 16;
        """);

        /* Animation hover */
        b.setOnMouseEntered(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), b);
            st.setToX(1.1);
            st.setToY(1.1);
            st.play();
        });
        b.setOnMouseExited(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), b);
            st.setToX(1.0);
            st.setToY(1.0);
            st.play();
        });
    }

    private void styleField(TextField f) {
        f.setStyle("""
                -fx-background-color:#ffffff;
                -fx-border-color:#2E8B57;
                -fx-border-radius:6;
                -fx-padding:6;
        """);
    }

    private void clearForm(TextField txtNom, TextField txtCategorie) {
        txtNom.clear();
        txtCategorie.clear();
    }
}
