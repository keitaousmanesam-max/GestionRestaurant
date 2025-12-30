package view;

import controller.AuthController;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Utilisateur;
import view.menu.*;

public class LoginView extends Stage {

    public LoginView() {

        Label lblTitre = new Label("Connexion au Système");
        lblTitre.setStyle("-fx-font-size:22px; -fx-font-weight:bold; -fx-text-fill:#2E8B57;");

        TextField txtIdentifiant = new TextField();
        txtIdentifiant.setPromptText("Identifiant");
        txtIdentifiant.setMaxWidth(250);
        txtIdentifiant.setStyle("-fx-border-color:#ccc; -fx-border-radius:5; -fx-padding:5;");

        PasswordField txtPassword = new PasswordField();
        txtPassword.setPromptText("Mot de passe");
        txtPassword.setMaxWidth(250);
        txtPassword.setStyle("-fx-border-color:#ccc; -fx-border-radius:5; -fx-padding:5;");

        Button btnLogin = new Button("Connexion");
        btnLogin.setStyle("-fx-background-color:#2E8B57; -fx-text-fill:white; -fx-font-weight:bold; -fx-background-radius:5;");
        btnLogin.setMaxWidth(250);

        Label lblMessage = new Label();
        lblMessage.setStyle("-fx-text-fill:red; -fx-font-weight:bold;");

        VBox root = new VBox(15, lblTitre, txtIdentifiant, txtPassword, btnLogin, lblMessage);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #F0F8FF, #E0FFFF); -fx-border-color:#2E8B57; -fx-border-width:2px; -fx-border-radius:10px;");

        setTitle("Authentification");
        setScene(new Scene(root, 400, 380));

        AuthController controller = new AuthController();

        // 🎬 Animation d’apparition du formulaire
        FadeTransition fade = new FadeTransition(Duration.seconds(1.5), root);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        // 🎬 Animation hover sur le bouton
        btnLogin.setOnMouseEntered(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), btnLogin);
            st.setToX(1.1);
            st.setToY(1.1);
            st.play();
        });
        btnLogin.setOnMouseExited(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), btnLogin);
            st.setToX(1.0);
            st.setToY(1.0);
            st.play();
        });

        btnLogin.setOnAction(e -> {
            String identifiant = txtIdentifiant.getText().trim();
            String motDePasse = txtPassword.getText().trim();

            if (identifiant.isEmpty() || motDePasse.isEmpty()) {
                lblMessage.setText("Veuillez remplir tous les champs !");
                shake(lblMessage); // 🎬 animation erreur
                return;
            }

            Utilisateur u = controller.login(identifiant, motDePasse);

            if (u == null) {
                lblMessage.setText("Identifiant ou mot de passe incorrect");
                shake(lblMessage); // 🎬 animation erreur
                return;
            }

            if (!u.getStatut().equalsIgnoreCase("ACTIF")) {
                lblMessage.setText("Compte inactif, connexion impossible");
                shake(lblMessage);
                return;
            }

            switch (u.getRole().getNomRole().toUpperCase()) {
                case "ADMIN":
                    new MenuPrincipalAdministrateur(u).show();
                    break;
                case "CAISSIER":
                    new MenuPrincipalCaissier(u).show();
                    break;
                case "SERVEUR":
                    new MenuPrincipalServeur(u).show();
                    break;
                case "GESTIONNAIRE":
                    new MenuPrincipalGestionnaire(u).show();
                    break;
                default:
                    lblMessage.setText("Rôle inconnu");
                    shake(lblMessage);
                    return;
            }

            Stage current = (Stage) btnLogin.getScene().getWindow();
            current.close();
        });
    }

    // 🎬 Animation de vibration pour les erreurs
    private void shake(Label label) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(100), label);
        tt.setFromX(0);
        tt.setByX(10);
        tt.setCycleCount(4);
        tt.setAutoReverse(true);
        tt.play();
    }
}
