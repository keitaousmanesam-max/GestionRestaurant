package view.menu;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.KeyCombination;
import javafx.util.Duration;
import model.Utilisateur;
import view.*;

public class MenuPrincipalAdministrateur extends Stage {

    public MenuPrincipalAdministrateur(Utilisateur u) {

        /* ===================== TITRE ===================== */
        Label lblTitre = new Label("Bienvenue Admin : " + u.getNom() + " " + u.getPrenom());
        lblTitre.setStyle("""
                -fx-font-size:18px;
                -fx-font-weight:bold;
                -fx-text-fill:#2E8B57;
        """);

        /* ===================== BOUTONS ===================== */
        Button btnUtilisateurs = createButton("👤 Gestion des utilisateurs");
        Button btnMenu = createButton("📋 Gestion des menus");
        Button btnPlat = createButton("🍽 Gestion des plats");
        Button btnCommandes = createButton("🧾 Gestion des commandes");
        Button btnFacturePaiement = createButton("💳 Facturation & Paiement");
        Button btnTables = createButton("🪑 Tables du restaurant");
        Button btnStock = createButton("📦 Gestion des stocks");
        Button btnRapports = createButton("📊 Rapports & Statistiques");
        Button btnDeconnexion = createButton("🚪 Déconnexion");

        /* ===================== ACTIONS ===================== */
        btnUtilisateurs.setOnAction(e -> new UtilisateurView().show());
        btnMenu.setOnAction(e -> new MenuView().show());
        btnPlat.setOnAction(e -> new PlatView().show());
        btnCommandes.setOnAction(e -> new CommandeView().show());
        btnFacturePaiement.setOnAction(e -> showFacturationPaiementChoix());
        btnTables.setOnAction(e -> new TableRestaurantView().show());
        btnStock.setOnAction(e -> new StockView().show());
        btnRapports.setOnAction(e -> new RapportView().show());

        btnDeconnexion.setOnAction(e -> {
            close();
            new LoginView().show();
        });

        /* ===================== LAYOUT ===================== */
        VBox root = new VBox(
                12,
                lblTitre,
                btnUtilisateurs, btnMenu, btnPlat,
                btnCommandes, btnFacturePaiement,
                btnTables, btnStock, btnRapports, btnDeconnexion
        );

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("""
                -fx-background-color: linear-gradient(to bottom, #FDFEFE, #EBF5FB);
                -fx-border-color:#2E8B57;
                -fx-border-width:2px;
                -fx-border-radius:8px;
        """);

        /* ===================== RESPONSIVE ===================== */
        for (var node : root.getChildren()) {
            if (node instanceof Button b) {
                b.setMaxWidth(Double.MAX_VALUE);
                VBox.setVgrow(b, Priority.ALWAYS);
            }
        }

        /* ===================== ADAPTATION TAILLE ===================== */
        widthProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() > 800) {
                root.setPadding(new Insets(40));
            } else {
                root.setPadding(new Insets(20));
            }
        });

        /* ===================== SCENE ===================== */
        Scene scene = new Scene(root, 450, 650);

        /* ===================== FENÊTRE ===================== */
        setTitle("Menu Administrateur");
        setScene(scene);
        setResizable(true);
        setMinWidth(450);
        setMinHeight(600);

        /* ===================== PLEIN ÉCRAN ===================== */
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

        /* ===== Animation Hover ===== */
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

    /* ===================== FENÊTRE CHOIX FACTURATION / PAIEMENT ===================== */
    private void showFacturationPaiementChoix() {
        Stage choixStage = new Stage();
        choixStage.initOwner(this);
        choixStage.setTitle("Choisir une option");

        Button btnFacturation = new Button("Facturation");
        Button btnPaiement = new Button("Paiement");

        btnFacturation.setMaxWidth(Double.MAX_VALUE);
        btnPaiement.setMaxWidth(Double.MAX_VALUE);

        btnFacturation.setOnAction(ev -> {
            new FactureView().show();
            choixStage.close();
        });

        btnPaiement.setOnAction(ev -> {
            new PaiementView().show();
            choixStage.close();
        });

        VBox rootChoix = new VBox(15, btnFacturation, btnPaiement);
        rootChoix.setPadding(new Insets(20));
        rootChoix.setAlignment(Pos.CENTER);
        rootChoix.setStyle("""
            -fx-background-color:#F4F6F9;
            -fx-border-color:#2E8B57;
            -fx-border-width:2px;
            -fx-border-radius:8px;
        """);

        Scene scene = new Scene(rootChoix, 250, 150);
        choixStage.setScene(scene);
        choixStage.setResizable(false);

        // Fenêtre modale bloquante
        choixStage.showAndWait();
    }
}
