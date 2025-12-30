package view.menu;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.KeyCombination;
import javafx.util.Duration;
import model.Utilisateur;
import view.StockView;
import view.RapportView;
import view.LoginView;

public class MenuPrincipalGestionnaire extends Stage {

    public MenuPrincipalGestionnaire(Utilisateur u) {

        /* ===================== BOUTONS ===================== */
        Button btnStock = createButton("📦 Gestion du stock");
        Button btnRapports = createButton("📊 Rapports & Statistiques");
        Button btnDeconnexion = createButton("🚪 Déconnexion");

        /* ===================== ACTIONS ===================== */
        btnStock.setOnAction(e -> new StockView().show());
        btnRapports.setOnAction(e -> new RapportView().show());
        btnDeconnexion.setOnAction(e -> {
            close();
            new LoginView().show();
        });

        /* ===================== LAYOUT ===================== */
        VBox root = new VBox(12, btnStock, btnRapports, btnDeconnexion);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
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
            if (newVal.doubleValue() > 700) {
                root.setPadding(new Insets(40));
            } else {
                root.setPadding(new Insets(25));
            }
        });

        /* ===================== SCENE ===================== */
        Scene scene = new Scene(root, 400, 350);

        /* ===================== FENÊTRE ===================== */
        setTitle("Menu Gestionnaire - " + u.getNom());
        setScene(scene);
        setResizable(true);
        setMinWidth(400);
        setMinHeight(350);

        /* ===================== PLEIN ÉCRAN OPTIONNEL ===================== */
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
}
