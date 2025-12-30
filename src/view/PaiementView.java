package view;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PaiementView extends Stage {

    public PaiementView() {

        Label titre = new Label("💰 Paiements");
        titre.setStyle("""
                -fx-font-size:18px;
                -fx-font-weight:bold;
                -fx-text-fill:#2E8B57;
        """);

        Label info = new Label(
                "• Paiement en espèces\n" +
                        "• Paiement par carte\n" +
                        "• Validation des paiements"
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

        setTitle("Paiement");
        setScene(new Scene(root, 420, 300));
        setMinWidth(400);
        setMinHeight(280);
        setResizable(true);
    }

    private void animate(VBox root) {
        ScaleTransition st = new ScaleTransition(Duration.millis(200), root);
        st.setFromX(0.95);
        st.setFromY(0.95);
        st.setToX(1);
        st.setToY(1);
        st.play();
    }
}
