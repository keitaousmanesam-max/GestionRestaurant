package view;

import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.input.KeyCombination;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import model.Role;
import model.Utilisateur;
import controller.UtilisateurController;
import controller.RoleController;

public class UtilisateurView extends Stage {

    private final UtilisateurController utilisateurController = new UtilisateurController();
    private final RoleController roleController = new RoleController();

    public UtilisateurView() {

        /* ===================== HEADER ===================== */
        Label lblTitre = new Label("👤 Gestion des Utilisateurs");
        lblTitre.setStyle("""
                -fx-font-size: 24px;
                -fx-font-weight: bold;
                -fx-text-fill: white;
                -fx-padding: 15 0;
        """);

        Label lblSubtitle = new Label("Gérez les comptes utilisateurs et leurs permissions");
        lblSubtitle.setStyle("""
                -fx-font-size: 13px;
                -fx-text-fill: #E8F5F1;
                -fx-opacity: 0.9;
        """);

        VBox header = new VBox(5, lblTitre, lblSubtitle);
        header.setAlignment(Pos.CENTER);
        header.setStyle("""
                -fx-background-color: linear-gradient(to right, #1ABC9C, #16A085);
                -fx-padding: 20;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);
        """);

        /* ===================== TABLE ===================== */
        TableView<Utilisateur> table = new TableView<>();
        table.setStyle("""
                -fx-background-color: white;
                -fx-border-color: #E0E0E0;
                -fx-border-radius: 8;
                -fx-background-radius: 8;
        """);

        TableColumn<Utilisateur, String> colId = new TableColumn<>("ID");
        colId.setPrefWidth(60);
        colId.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(String.valueOf(d.getValue().getIdUtilisateur())));
        styleColumn(colId);

        TableColumn<Utilisateur, String> colIdentifiant = new TableColumn<>("Identifiant");
        colIdentifiant.setPrefWidth(120);
        colIdentifiant.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getIdentifiant()));
        styleColumn(colIdentifiant);

        TableColumn<Utilisateur, String> colNom = new TableColumn<>("Nom");
        colNom.setPrefWidth(120);
        colNom.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getNom()));
        styleColumn(colNom);

        TableColumn<Utilisateur, String> colPrenom = new TableColumn<>("Prénom");
        colPrenom.setPrefWidth(120);
        colPrenom.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getPrenom()));
        styleColumn(colPrenom);

        TableColumn<Utilisateur, String> colRole = new TableColumn<>("Rôle");
        colRole.setPrefWidth(100);
        colRole.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(
                d.getValue().getRole() != null ? d.getValue().getRole().getNomRole() : "N/A"
        ));
        styleColumn(colRole);

        TableColumn<Utilisateur, String> colStatut = new TableColumn<>("Statut");
        colStatut.setPrefWidth(80);
        colStatut.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getStatut()));
        colStatut.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    if ("ACTIF".equals(item)) {
                        setStyle("-fx-text-fill: #27AE60; -fx-font-weight: bold;");
                    } else {
                        setStyle("-fx-text-fill: #E74C3C; -fx-font-weight: bold;");
                    }
                }
            }
        });

        table.getColumns().addAll(colId, colIdentifiant, colNom, colPrenom, colRole, colStatut);
        table.setItems(FXCollections.observableArrayList(utilisateurController.getAllUtilisateurs()));
        VBox.setVgrow(table, Priority.ALWAYS);

        /* ===================== FORM CARD ===================== */
        Label lblForm = new Label("📝 Informations de l'utilisateur");
        lblForm.setStyle("""
                -fx-font-size: 16px;
                -fx-font-weight: bold;
                -fx-text-fill: #2C3E50;
        """);

        TextField txtIdentifiant = createStyledTextField("Identifiant (login)");
        TextField txtNom = createStyledTextField("Nom");
        TextField txtPrenom = createStyledTextField("Prénom");
        PasswordField txtMotDePasse = createStyledPasswordField("Mot de passe");

        ComboBox<Role> cbRole = new ComboBox<>(FXCollections.observableArrayList(roleController.getAllRoles()));
        cbRole.setPromptText("Sélectionner un rôle");
        cbRole.setMaxWidth(Double.MAX_VALUE);
        styleComboBox(cbRole);

        ChoiceBox<String> cbStatut = new ChoiceBox<>(FXCollections.observableArrayList("ACTIF", "INACTIF"));
        cbStatut.setValue("ACTIF");
        cbStatut.setMaxWidth(Double.MAX_VALUE);
        styleChoiceBox(cbStatut);

        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(12);
        formGrid.setPadding(new Insets(15));

        formGrid.add(new Label("Identifiant:"), 0, 0);
        formGrid.add(txtIdentifiant, 1, 0);
        formGrid.add(new Label("Nom:"), 0, 1);
        formGrid.add(txtNom, 1, 1);
        formGrid.add(new Label("Prénom:"), 0, 2);
        formGrid.add(txtPrenom, 1, 2);
        formGrid.add(new Label("Mot de passe:"), 0, 3);
        formGrid.add(txtMotDePasse, 1, 3);
        formGrid.add(new Label("Rôle:"), 0, 4);
        formGrid.add(cbRole, 1, 4);
        formGrid.add(new Label("Statut:"), 0, 5);
        formGrid.add(cbStatut, 1, 5);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMinWidth(100);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        formGrid.getColumnConstraints().addAll(col1, col2);

        VBox formCard = new VBox(15, lblForm, formGrid);
        formCard.setStyle("""
                -fx-background-color: white;
                -fx-background-radius: 10;
                -fx-padding: 20;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 8, 0, 0, 2);
        """);

        /* ===================== MESSAGE ===================== */
        Label lblMessage = new Label();
        lblMessage.setStyle("-fx-font-size: 13px; -fx-padding: 10; -fx-background-radius: 5;");
        lblMessage.setMaxWidth(Double.MAX_VALUE);
        lblMessage.setAlignment(Pos.CENTER);

        /* ===================== BOUTONS ===================== */
        Button btnAjouter = createStyledButton("➕ Ajouter", "#27AE60");
        Button btnModifier = createStyledButton("✏️ Modifier", "#3498DB");
        Button btnSupprimer = createStyledButton("🗑️ Supprimer", "#E74C3C");
        Button btnActualiser = createStyledButton("🔄 Actualiser", "#95A5A6");
        Button btnFermer = createStyledButton("✖ Fermer", "#34495E");

        HBox actionButtons = new HBox(10, btnAjouter, btnModifier, btnSupprimer, btnActualiser, btnFermer);
        actionButtons.setAlignment(Pos.CENTER);
        HBox.setHgrow(btnAjouter, Priority.ALWAYS);
        HBox.setHgrow(btnModifier, Priority.ALWAYS);
        HBox.setHgrow(btnSupprimer, Priority.ALWAYS);
        HBox.setHgrow(btnActualiser, Priority.ALWAYS);
        HBox.setHgrow(btnFermer, Priority.ALWAYS);

        btnAjouter.setMaxWidth(Double.MAX_VALUE);
        btnModifier.setMaxWidth(Double.MAX_VALUE);
        btnSupprimer.setMaxWidth(Double.MAX_VALUE);
        btnActualiser.setMaxWidth(Double.MAX_VALUE);
        btnFermer.setMaxWidth(Double.MAX_VALUE);

        /* ===================== ACTIONS ===================== */
        btnAjouter.setOnAction(e -> {
            if (txtIdentifiant.getText().isEmpty() || txtNom.getText().isEmpty() ||
                    txtPrenom.getText().isEmpty() || txtMotDePasse.getText().isEmpty() ||
                    cbRole.getValue() == null) {
                showMessage(lblMessage, "❌ Veuillez remplir tous les champs obligatoires", "error");
                return;
            }

            Utilisateur u = new Utilisateur(0, txtIdentifiant.getText(), txtNom.getText(),
                    txtPrenom.getText(), txtMotDePasse.getText(), cbRole.getValue(), cbStatut.getValue());

            utilisateurController.addUtilisateur(u);
            showMessage(lblMessage, "✅ Utilisateur ajouté avec succès", "success");
            refreshTable(table);
            clearForm(txtIdentifiant, txtNom, txtPrenom, txtMotDePasse, cbRole, cbStatut);
            animateSuccess(formCard);
        });

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, u) -> {
            if (u != null) {
                txtIdentifiant.setText(u.getIdentifiant());
                txtNom.setText(u.getNom());
                txtPrenom.setText(u.getPrenom());
                txtMotDePasse.setText(u.getMotDePasse());
                cbRole.setValue(u.getRole());
                cbStatut.setValue(u.getStatut());
                animateSelection(formCard);
            }
        });

        btnModifier.setOnAction(e -> {
            Utilisateur u = table.getSelectionModel().getSelectedItem();
            if (u == null) {
                showMessage(lblMessage, "⚠️ Veuillez sélectionner un utilisateur", "warning");
                return;
            }

            u.setIdentifiant(txtIdentifiant.getText());
            u.setNom(txtNom.getText());
            u.setPrenom(txtPrenom.getText());
            u.setMotDePasse(txtMotDePasse.getText());
            u.setRole(cbRole.getValue());
            u.setStatut(cbStatut.getValue());

            utilisateurController.updateUtilisateur(u);
            showMessage(lblMessage, "✏️ Utilisateur modifié avec succès", "success");
            refreshTable(table);
            animateSuccess(formCard);
        });

        btnSupprimer.setOnAction(e -> {
            Utilisateur u = table.getSelectionModel().getSelectedItem();
            if (u == null) {
                showMessage(lblMessage, "⚠️ Veuillez sélectionner un utilisateur", "warning");
                return;
            }

            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirmation");
            confirm.setHeaderText("Supprimer l'utilisateur ?");
            confirm.setContentText("Êtes-vous sûr de vouloir supprimer " + u.getNom() + " " + u.getPrenom() + " ?");

            if (confirm.showAndWait().get() == ButtonType.OK) {
                utilisateurController.deleteUtilisateur(u.getIdUtilisateur());
                showMessage(lblMessage, "🗑️ Utilisateur supprimé avec succès", "success");
                refreshTable(table);
                clearForm(txtIdentifiant, txtNom, txtPrenom, txtMotDePasse, cbRole, cbStatut);
            }
        });

        btnActualiser.setOnAction(e -> {
            refreshTable(table);
            animateRefresh(table);
            showMessage(lblMessage, "🔄 Liste actualisée", "info");
        });

        btnFermer.setOnAction(e -> close());

        /* ===================== LAYOUT ===================== */
        VBox content = new VBox(20, table, formCard, lblMessage, actionButtons);
        content.setPadding(new Insets(20));
        VBox.setVgrow(table, Priority.ALWAYS);

        VBox root = new VBox(header, content);
        root.setStyle("-fx-background-color: #ECF0F1;");

        Scene scene = new Scene(root, 800, 750);

        setTitle("Gestion des Utilisateurs - Système Professionnel");
        setScene(scene);
        setResizable(true);
        setMinWidth(800);
        setMinHeight(750);

        // Animation d'entrée
        root.setOpacity(0);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(600), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();
    }

    /* ===================== STYLING METHODS ===================== */
    private TextField createStyledTextField(String prompt) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.setStyle("""
                -fx-background-color: white;
                -fx-border-color: #BDC3C7;
                -fx-border-radius: 5;
                -fx-background-radius: 5;
                -fx-padding: 8;
                -fx-font-size: 13px;
        """);
        tf.focusedProperty().addListener((obs, old, focused) -> {
            if (focused) {
                tf.setStyle(tf.getStyle() + "-fx-border-color: #3498DB; -fx-border-width: 2;");
            } else {
                tf.setStyle(tf.getStyle().replace("-fx-border-color: #3498DB; -fx-border-width: 2;", ""));
            }
        });
        return tf;
    }

    private PasswordField createStyledPasswordField(String prompt) {
        PasswordField pf = new PasswordField();
        pf.setPromptText(prompt);
        pf.setStyle("""
                -fx-background-color: white;
                -fx-border-color: #BDC3C7;
                -fx-border-radius: 5;
                -fx-background-radius: 5;
                -fx-padding: 8;
                -fx-font-size: 13px;
        """);
        pf.focusedProperty().addListener((obs, old, focused) -> {
            if (focused) {
                pf.setStyle(pf.getStyle() + "-fx-border-color: #3498DB; -fx-border-width: 2;");
            } else {
                pf.setStyle(pf.getStyle().replace("-fx-border-color: #3498DB; -fx-border-width: 2;", ""));
            }
        });
        return pf;
    }

    private void styleComboBox(ComboBox<?> cb) {
        cb.setStyle("""
                -fx-background-color: white;
                -fx-border-color: #BDC3C7;
                -fx-border-radius: 5;
                -fx-background-radius: 5;
                -fx-font-size: 13px;
        """);
    }

    private void styleChoiceBox(ChoiceBox<?> cb) {
        cb.setStyle("""
                -fx-background-color: white;
                -fx-border-color: #BDC3C7;
                -fx-border-radius: 5;
                -fx-background-radius: 5;
                -fx-font-size: 13px;
        """);
    }

    private void styleColumn(TableColumn<?, ?> col) {
        col.setStyle("-fx-alignment: CENTER; -fx-font-size: 12px;");
    }

    private Button createStyledButton(String text, String color) {
        Button btn = new Button(text);
        btn.setStyle(String.format("""
                -fx-background-color: %s;
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-font-size: 13px;
                -fx-background-radius: 6;
                -fx-padding: 10 20;
                -fx-cursor: hand;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 4, 0, 0, 1);
        """, color));

        btn.setOnMouseEntered(e -> {
            btn.setStyle(btn.getStyle() + "-fx-opacity: 0.9;");
            animateButton(btn, 1.05);
        });

        btn.setOnMouseExited(e -> {
            btn.setStyle(btn.getStyle().replace("-fx-opacity: 0.9;", ""));
            animateButton(btn, 1.0);
        });

        return btn;
    }

    /* ===================== ANIMATION METHODS ===================== */
    private void animateButton(Button btn, double scale) {
        ScaleTransition st = new ScaleTransition(Duration.millis(150), btn);
        st.setToX(scale);
        st.setToY(scale);
        st.setInterpolator(Interpolator.EASE_BOTH);
        st.play();
    }

    private void animateSuccess(VBox card) {
        FadeTransition fade = new FadeTransition(Duration.millis(200), card);
        fade.setFromValue(1.0);
        fade.setToValue(0.7);
        fade.setCycleCount(2);
        fade.setAutoReverse(true);
        fade.play();
    }

    private void animateSelection(VBox card) {
        TranslateTransition slide = new TranslateTransition(Duration.millis(200), card);
        slide.setFromX(-10);
        slide.setToX(0);

        FadeTransition fade = new FadeTransition(Duration.millis(200), card);
        fade.setFromValue(0.8);
        fade.setToValue(1.0);

        ParallelTransition parallel = new ParallelTransition(slide, fade);
        parallel.play();
    }

    private void animateRefresh(TableView<?> table) {
        RotateTransition rotate = new RotateTransition(Duration.millis(400), table);
        rotate.setByAngle(360);
        rotate.setAxis(javafx.geometry.Point3D.ZERO);

        ScaleTransition scale = new ScaleTransition(Duration.millis(200), table);
        scale.setFromX(0.95);
        scale.setFromY(0.95);
        scale.setToX(1.0);
        scale.setToY(1.0);
        scale.setCycleCount(2);
        scale.setAutoReverse(true);

        scale.play();
    }

    private void showMessage(Label lbl, String message, String type) {
        lbl.setText(message);

        String color = switch (type) {
            case "success" -> "-fx-background-color: #D5F4E6; -fx-text-fill: #27AE60;";
            case "error" -> "-fx-background-color: #FADBD8; -fx-text-fill: #E74C3C;";
            case "warning" -> "-fx-background-color: #FCF3CF; -fx-text-fill: #F39C12;";
            default -> "-fx-background-color: #D6EAF8; -fx-text-fill: #3498DB;";
        };

        lbl.setStyle(lbl.getStyle() + color);

        FadeTransition fade = new FadeTransition(Duration.millis(300), lbl);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.setOnFinished(e -> {
            FadeTransition fadeOut = new FadeTransition(Duration.millis(500), lbl);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(ev -> lbl.setText(""));
            fadeOut.play();
        });
        pause.play();
    }

    private void refreshTable(TableView<Utilisateur> table) {
        table.setItems(FXCollections.observableArrayList(utilisateurController.getAllUtilisateurs()));
    }

    private void clearForm(TextField txtIdentifiant, TextField txtNom, TextField txtPrenom,
                           PasswordField txtMotDePasse, ComboBox<Role> cbRole, ChoiceBox<String> cbStatut) {
        txtIdentifiant.clear() ;
        txtNom.clear();
        txtPrenom.clear();
        txtMotDePasse.clear();
        cbRole.setValue(null);
        cbStatut.setValue("ACTIF");
    }
}