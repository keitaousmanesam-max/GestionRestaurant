package view;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RapportView extends Stage {

    public RapportView() {

        Label titre = new Label("📊 Rapports & Statistiques");
        titre.setStyle("""
                -fx-font-size:18px;
                -fx-font-weight:bold;
                -fx-text-fill:#2E8B57;
        """);

        Label info = new Label(
                "• Chiffre d'affaires journalier / mensuel\n" +
                        "• Plats les plus vendus\n" +
                        "• Historique des ventes"
        );
        info.setStyle("-fx-font-size:14px;");

        VBox root = new VBox(15, titre, info);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("""
                -fx-background-color: linear-gradient(to bottom, #FDFEFE, #EBF5FB);
                -fx-border-color:#2E8B57;
                -fx-border-width:2px;
                -fx-border-radius:10px;
        """);

        animate(root);

        setTitle("Rapports");
        setScene(new Scene(root, 450, 300));
        setResizable(true);
        setMinWidth(400);
        setMinHeight(280);
    }

    private void animate(VBox root) {
        ScaleTransition st = new ScaleTransition(Duration.millis(150), root);
        st.setFromX(0.95);
        st.setFromY(0.95);
        st.setToX(1);
        st.setToY(1);
        st.play();
    }
}
