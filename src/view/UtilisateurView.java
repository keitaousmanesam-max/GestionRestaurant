package view;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.KeyCombination;
import javafx.util.Duration;
import model.Role;
import model.Utilisateur;
import controller.UtilisateurController;
import controller.RoleController;

public class UtilisateurView extends Stage {

    private final UtilisateurController utilisateurController = new UtilisateurController();
    private final RoleController roleController = new RoleController();

    public UtilisateurView() {

        /* ===================== TITRE ===================== */
        Label lblTitre = new Label("👤 Gestion des utilisateurs");
        lblTitre.setStyle("""
                -fx-font-size:18px;
                -fx-font-weight:bold;
                -fx-text-fill:#2E8B57;
        """);

        /* ===================== TABLE ===================== */
        TableView<Utilisateur> table = new TableView<>();

        TableColumn<Utilisateur, String> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(String.valueOf(d.getValue().getIdUtilisateur())));

        TableColumn<Utilisateur, String> colIdentifiant = new TableColumn<>("Identifiant");
        colIdentifiant.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getIdentifiant()));

        TableColumn<Utilisateur, String> colNom = new TableColumn<>("Nom");
        colNom.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getNom()));

        TableColumn<Utilisateur, String> colPrenom = new TableColumn<>("Prénom");
        colPrenom.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getPrenom()));

        TableColumn<Utilisateur, String> colRole = new TableColumn<>("Rôle");
        colRole.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(
                d.getValue().getRole() != null ? d.getValue().getRole().getNomRole() : "N/A"
        ));

        TableColumn<Utilisateur, String> colStatut = new TableColumn<>("Statut");
        colStatut.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getStatut()));

        table.getColumns().addAll(colId, colIdentifiant, colNom, colPrenom, colRole, colStatut);
        table.setItems(FXCollections.observableArrayList(utilisateurController.getAllUtilisateurs()));

        /* ===================== FORM ===================== */
        TextField txtIdentifiant = new TextField();
        txtIdentifiant.setPromptText("Identifiant (login)");

        TextField txtNom = new TextField();
        txtNom.setPromptText("Nom");

        TextField txtPrenom = new TextField();
        txtPrenom.setPromptText("Prénom");

        PasswordField txtMotDePasse = new PasswordField();
        txtMotDePasse.setPromptText("Mot de passe");

        ComboBox<Role> cbRole = new ComboBox<>(FXCollections.observableArrayList(roleController.getAllRoles()));
        cbRole.setPromptText("Choisir un rôle");

        ChoiceBox<String> cbStatut = new ChoiceBox<>(FXCollections.observableArrayList("ACTIF", "INACTIF"));
        cbStatut.setValue("ACTIF");

        Label lblMessage = new Label();

        /* ===================== BOUTONS ===================== */
        Button btnAjouter = createButton("Ajouter");
        Button btnModifier = createButton("Modifier");
        Button btnSupprimer = createButton("Supprimer");
        Button btnActualiser = createButton("Actualiser");
        Button btnFermer = createButton("Fermer");

        /* ===================== ACTIONS ===================== */
        btnAjouter.setOnAction(e -> {
            if (txtIdentifiant.getText().isEmpty() || txtNom.getText().isEmpty() || txtPrenom.getText().isEmpty() || txtMotDePasse.getText().isEmpty() || cbRole.getValue() == null) {
                lblMessage.setText("❌ Veuillez remplir tous les champs");
                lblMessage.setStyle("-fx-text-fill:red; -fx-font-weight:bold;");
                return;
            }

            Utilisateur u = new Utilisateur(0, txtIdentifiant.getText(), txtNom.getText(), txtPrenom.getText(),
                    txtMotDePasse.getText(), cbRole.getValue(), cbStatut.getValue());

            utilisateurController.addUtilisateur(u);
            lblMessage.setText("✅ Utilisateur ajouté");
            lblMessage.setStyle("-fx-text-fill:green; -fx-font-weight:bold;");
            table.setItems(FXCollections.observableArrayList(utilisateurController.getAllUtilisateurs()));
            clearForm(txtIdentifiant, txtNom, txtPrenom, txtMotDePasse, cbRole, cbStatut);
        });

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, u) -> {
            if (u != null) {
                txtIdentifiant.setText(u.getIdentifiant());
                txtNom.setText(u.getNom());
                txtPrenom.setText(u.getPrenom());
                txtMotDePasse.setText(u.getMotDePasse());
                cbRole.setValue(u.getRole());
                cbStatut.setValue(u.getStatut());
            }
        });

        btnModifier.setOnAction(e -> {
            Utilisateur u = table.getSelectionModel().getSelectedItem();
            if (u == null) return;

            u.setIdentifiant(txtIdentifiant.getText());
            u.setNom(txtNom.getText());
            u.setPrenom(txtPrenom.getText());
            u.setMotDePasse(txtMotDePasse.getText());
            u.setRole(cbRole.getValue());
            u.setStatut(cbStatut.getValue());

            utilisateurController.updateUtilisateur(u);
            lblMessage.setText("✏ Utilisateur modifié");
            lblMessage.setStyle("-fx-text-fill:green; -fx-font-weight:bold;");
            table.setItems(FXCollections.observableArrayList(utilisateurController.getAllUtilisateurs()));
        });

        btnSupprimer.setOnAction(e -> {
            Utilisateur u = table.getSelectionModel().getSelectedItem();
            if (u == null) return;

            utilisateurController.deleteUtilisateur(u.getIdUtilisateur());
            lblMessage.setText("🗑 Utilisateur supprimé");
            lblMessage.setStyle("-fx-text-fill:green; -fx-font-weight:bold;");
            table.setItems(FXCollections.observableArrayList(utilisateurController.getAllUtilisateurs()));
        });

        btnActualiser.setOnAction(e -> table.setItems(FXCollections.observableArrayList(utilisateurController.getAllUtilisateurs())));
        btnFermer.setOnAction(e -> close());

        /* ===================== LAYOUT ===================== */
        VBox root = new VBox(
                12,
                lblTitre,
                table,
                txtIdentifiant, txtNom, txtPrenom, txtMotDePasse,
                cbRole, cbStatut,
                btnAjouter, btnModifier, btnSupprimer, btnActualiser, btnFermer,
                lblMessage
        );

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("""
                -fx-background-color: linear-gradient(to bottom, #FDFEFE, #EBF5FB);
                -fx-border-color:#2E8B57;
                -fx-border-width:2px;
                -fx-border-radius:8px;
        """);

        for (var node : root.getChildren()) {
            if (node instanceof Button b) {
                b.setMaxWidth(Double.MAX_VALUE);
                VBox.setVgrow(b, Priority.ALWAYS);
            }
        }

        Scene scene = new Scene(root, 600, 650);

        setTitle("Gestion des Utilisateurs");
        setScene(scene);
        setResizable(true);
        setMinWidth(600);
        setMinHeight(650);

        setFullScreenExitHint("");
        setFullScreenExitKeyCombination(KeyCombination.valueOf("ESC"));
    }

    /* ===================== BOUTON FACTORY ===================== */
    private Button createButton(String text) {
        Button btn = new Button(text);
        btn.setStyle("""
                -fx-background-color:#2E8B57;
                -fx-text-fill:white;
                -fx-font-weight:bold;
                -fx-font-size:14px;
                -fx-background-radius:8;
                -fx-padding:10 20;
        """);

        btn.setOnMouseEntered(e -> animate(btn, 1.05));
        btn.setOnMouseExited(e -> animate(btn, 1.0));

        return btn;
    }

    private void animate(Button btn, double scale) {
        ScaleTransition st = new ScaleTransition(Duration.millis(120), btn);
        st.setToX(scale);
        st.setToY(scale);
        st.play();
    }

    private void clearForm(TextField txtIdentifiant, TextField txtNom, TextField txtPrenom, PasswordField txtMotDePasse, ComboBox<Role> cbRole, ChoiceBox<String> cbStatut) {
        txtIdentifiant.clear();
        txtNom.clear();
        txtPrenom.clear();
        txtMotDePasse.clear();
        cbRole.setValue(null);
        cbStatut.setValue("ACTIF");
    }
}
