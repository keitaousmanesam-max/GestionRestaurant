package view.menu;

import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Utilisateur;
import view.*;

public class MenuPrincipalAdministrateur extends Stage {

    public MenuPrincipalAdministrateur(Utilisateur u) {

        /* ===================== HEADER SECTION ===================== */
        Label lblWelcome = new Label("Bienvenue Admin");
        lblWelcome.setStyle("""
                -fx-font-size: 22px;
                -fx-font-weight: bold;
                -fx-text-fill: white;
        """);

        Label lblUser = new Label(u.getNom() + " " + u.getPrenom());
        lblUser.setStyle("""
                -fx-font-size: 14px;
                -fx-text-fill: #E8F5F1;
                -fx-opacity: 0.95;
        """);

        Label lblRole = new Label("🔐 Administrateur Système");
        lblRole.setStyle("""
                -fx-font-size: 11px;
                -fx-text-fill: #D5F4E6;
                -fx-opacity: 0.9;
                -fx-padding: 3 0 0 0;
        """);

        VBox headerContent = new VBox(5, lblWelcome, lblUser, lblRole);
        headerContent.setAlignment(Pos.CENTER);

        VBox header = new VBox(headerContent);
        header.setAlignment(Pos.CENTER);
        header.setStyle("""
                -fx-background-color: linear-gradient(to right, #1ABC9C, #16A085);
                -fx-padding: 20;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0, 0, 3);
        """);

        /* ===================== DASHBOARD STATS ===================== */
        VBox statsCard = createCompactStatsCard();

        /* ===================== MENU GRID ===================== */
        GridPane menuGrid = new GridPane();
        menuGrid.setHgap(10);
        menuGrid.setVgap(10);
        menuGrid.setPadding(new Insets(15));
        menuGrid.setAlignment(Pos.CENTER);

        // Ligne 1
        Button btnUtilisateurs = createCompactMenuButton("👤", "Utilisateurs", "#3498DB");
        Button btnMenu = createCompactMenuButton("📋", "Menus", "#9B59B6");

        // Ligne 2
        Button btnPlat = createCompactMenuButton("🍽", "Plats", "#E67E22");
        Button btnCommandes = createCompactMenuButton("🧾", "Commandes", "#F39C12");

        // Ligne 3
        Button btnFacturePaiement = createCompactMenuButton("💳", "Facturation", "#1ABC9C");
        Button btnTables = createCompactMenuButton("🪑", "Tables", "#34495E");

        // Ligne 4
        Button btnStock = createCompactMenuButton("📦", "Stocks", "#E74C3C");
        Button btnRapports = createCompactMenuButton("📊", "Rapports", "#27AE60");

        menuGrid.add(btnUtilisateurs, 0, 0);
        menuGrid.add(btnMenu, 1, 0);
        menuGrid.add(btnPlat, 0, 1);
        menuGrid.add(btnCommandes, 1, 1);
        menuGrid.add(btnFacturePaiement, 0, 2);
        menuGrid.add(btnTables, 1, 2);
        menuGrid.add(btnStock, 0, 3);
        menuGrid.add(btnRapports, 1, 3);

        // Contraintes de colonnes
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        menuGrid.getColumnConstraints().addAll(col1, col2);

        /* ===================== DÉCONNEXION ===================== */
        Button btnDeconnexion = createLogoutButton();

        /* ===================== ACTIONS ===================== */
        btnUtilisateurs.setOnAction(e -> openWindow(new UtilisateurView(), btnUtilisateurs));
        btnMenu.setOnAction(e -> openWindow(new MenuView(), btnMenu));
        btnPlat.setOnAction(e -> openWindow(new PlatView(), btnPlat));
        btnCommandes.setOnAction(e -> openWindow(new CommandeView(), btnCommandes));
        btnFacturePaiement.setOnAction(e -> showFacturationPaiementChoix(btnFacturePaiement));
        btnTables.setOnAction(e -> openWindow(new TableRestaurantView(), btnTables));
        btnStock.setOnAction(e -> openWindow(new StockView(), btnStock));
        btnRapports.setOnAction(e -> openWindow(new RapportView(), btnRapports));

        btnDeconnexion.setOnAction(e -> {
            animateLogout(() -> {
                close();
                new LoginView().show();
            });
        });

        /* ===================== CONTENT SECTION ===================== */
        VBox contentBox = new VBox(15, statsCard, menuGrid, btnDeconnexion);
        contentBox.setPadding(new Insets(15));
        contentBox.setAlignment(Pos.TOP_CENTER);

        /* ===================== SCROLLPANE ===================== */
        ScrollPane scrollPane = new ScrollPane(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #ECF0F1; -fx-background-color: #ECF0F1;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        /* ===================== MAIN LAYOUT ===================== */
        VBox root = new VBox(header, scrollPane);
        root.setStyle("-fx-background-color: #ECF0F1;");

        /* ===================== SCENE ===================== */
        Scene scene = new Scene(root, 550, 700);

        /* ===================== FENÊTRE ===================== */
        setTitle("Tableau de Bord Administrateur");
        setScene(scene);
        setResizable(true);
        setMinWidth(550);
        setMinHeight(600);

        setFullScreenExitHint("");
        setFullScreenExitKeyCombination(KeyCombination.valueOf("ESC"));

        /* ===================== ANIMATIONS D'ENTRÉE ===================== */
        animateEntrance(root, menuGrid);
    }

    /* ===================== COMPACT STATS CARD ===================== */
    private VBox createCompactStatsCard() {
        HBox statsRow = new HBox(8);
        statsRow.setAlignment(Pos.CENTER);

        VBox stat1 = createCompactStatItem("👥", "24", "Users");
        VBox stat2 = createCompactStatItem("🧾", "156", "Orders");
        VBox stat3 = createCompactStatItem("💰", "45K€", "Rev.");

        statsRow.getChildren().addAll(stat1, stat2, stat3);
        HBox.setHgrow(stat1, Priority.ALWAYS);
        HBox.setHgrow(stat2, Priority.ALWAYS);
        HBox.setHgrow(stat3, Priority.ALWAYS);

        VBox statsCard = new VBox(statsRow);
        statsCard.setStyle("""
                -fx-background-color: white;
                -fx-background-radius: 8;
                -fx-padding: 10;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 6, 0, 0, 2);
        """);
        statsCard.setMaxWidth(500);

        return statsCard;
    }

    private VBox createCompactStatItem(String icon, String value, String label) {
        Label lblIcon = new Label(icon);
        lblIcon.setStyle("-fx-font-size: 18px;");

        Label lblValue = new Label(value);
        lblValue.setStyle("""
                -fx-font-size: 15px;
                -fx-font-weight: bold;
                -fx-text-fill: #2C3E50;
        """);

        Label lblLabel = new Label(label);
        lblLabel.setStyle("""
                -fx-font-size: 9px;
                -fx-text-fill: #7F8C8D;
        """);

        VBox box = new VBox(3, lblIcon, lblValue, lblLabel);
        box.setAlignment(Pos.CENTER);
        box.setStyle("""
                -fx-background-color: #F8F9FA;
                -fx-background-radius: 6;
                -fx-padding: 8;
        """);
        box.setMaxWidth(Double.MAX_VALUE);

        return box;
    }

    /* ===================== COMPACT MENU BUTTON ===================== */
    private Button createCompactMenuButton(String icon, String text, String color) {
        Label lblIcon = new Label(icon);
        lblIcon.setStyle("-fx-font-size: 28px;");

        Label lblText = new Label(text);
        lblText.setStyle("""
                -fx-font-size: 12px;
                -fx-font-weight: bold;
                -fx-text-fill: white;
        """);

        VBox content = new VBox(6, lblIcon, lblText);
        content.setAlignment(Pos.CENTER);
        content.setMouseTransparent(true);

        Button btn = new Button();
        btn.setGraphic(content);
        btn.setPrefSize(200, 90);
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn.setStyle(String.format("""
                -fx-background-color: %s;
                -fx-background-radius: 10;
                -fx-cursor: hand;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 6, 0, 0, 2);
        """, color));

        btn.setOnMouseEntered(e -> animateMenuButton(btn, 1.05, color, true));
        btn.setOnMouseExited(e -> animateMenuButton(btn, 1.0, color, false));

        return btn;
    }

    /* ===================== LOGOUT BUTTON ===================== */
    private Button createLogoutButton() {
        Button btn = new Button("🚪  Déconnexion");
        btn.setMaxWidth(500);
        btn.setStyle("""
                -fx-background-color: #E74C3C;
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-font-size: 13px;
                -fx-background-radius: 8;
                -fx-padding: 10 20;
                -fx-cursor: hand;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 2);
        """);

        btn.setOnMouseEntered(e -> {
            btn.setStyle(btn.getStyle() + "-fx-background-color: #C0392B;");
            animateScale(btn, 1.02);
        });

        btn.setOnMouseExited(e -> {
            btn.setStyle(btn.getStyle().replace("-fx-background-color: #C0392B;", ""));
            animateScale(btn, 1.0);
        });

        return btn;
    }

    /* ===================== ANIMATIONS ===================== */
    private void animateEntrance(VBox root, GridPane grid) {
        // Fade in global
        root.setOpacity(0);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        // Animation cascade des boutons
        int delay = 0;
        for (var node : grid.getChildren()) {
            node.setOpacity(0);
            node.setTranslateY(20);

            PauseTransition pause = new PauseTransition(Duration.millis(delay));
            pause.setOnFinished(e -> {
                FadeTransition fade = new FadeTransition(Duration.millis(400), node);
                fade.setFromValue(0);
                fade.setToValue(1);

                TranslateTransition translate = new TranslateTransition(Duration.millis(400), node);
                translate.setFromY(20);
                translate.setToY(0);

                ParallelTransition parallel = new ParallelTransition(fade, translate);
                parallel.setInterpolator(Interpolator.EASE_OUT);
                parallel.play();
            });
            pause.play();

            delay += 80;
        }
    }

    private void animateMenuButton(Button btn, double scale, String color, boolean hover) {
        ScaleTransition st = new ScaleTransition(Duration.millis(200), btn);
        st.setToX(scale);
        st.setToY(scale);
        st.setInterpolator(Interpolator.EASE_BOTH);
        st.play();

        if (hover) {
            btn.setStyle(btn.getStyle() + "-fx-opacity: 0.95;");
        } else {
            btn.setStyle(btn.getStyle().replace("-fx-opacity: 0.95;", ""));
        }
    }

    private void animateScale(Button btn, double scale) {
        ScaleTransition st = new ScaleTransition(Duration.millis(150), btn);
        st.setToX(scale);
        st.setToY(scale);
        st.setInterpolator(Interpolator.EASE_BOTH);
        st.play();
    }

    private void animateButtonClick(Button btn) {
        ScaleTransition shrink = new ScaleTransition(Duration.millis(100), btn);
        shrink.setToX(0.95);
        shrink.setToY(0.95);

        ScaleTransition grow = new ScaleTransition(Duration.millis(100), btn);
        grow.setToX(1.0);
        grow.setToY(1.0);

        SequentialTransition seq = new SequentialTransition(shrink, grow);
        seq.play();
    }

    private void animateLogout(Runnable onFinished) {
        Scene scene = getScene();
        FadeTransition fade = new FadeTransition(Duration.millis(400), scene.getRoot());
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setOnFinished(e -> onFinished.run());
        fade.play();
    }

    private void openWindow(Stage window, Button sourceButton) {
        animateButtonClick(sourceButton);

        PauseTransition pause = new PauseTransition(Duration.millis(150));
        pause.setOnFinished(e -> window.show());
        pause.play();
    }

    /* ===================== FENÊTRE CHOIX FACTURATION / PAIEMENT ===================== */
    private void showFacturationPaiementChoix(Button sourceButton) {
        animateButtonClick(sourceButton);

        Stage choixStage = new Stage();
        choixStage.initOwner(this);
        choixStage.initModality(Modality.APPLICATION_MODAL);
        choixStage.initStyle(StageStyle.UNDECORATED);
        choixStage.setTitle("Choisir une option");

        // Titre
        Label lblTitle = new Label("💳 Facturation & Paiement");
        lblTitle.setStyle("""
                -fx-font-size: 18px;
                -fx-font-weight: bold;
                -fx-text-fill: #2C3E50;
                -fx-padding: 0 0 15 0;
        """);

        Label lblSubtitle = new Label("Sélectionnez une action");
        lblSubtitle.setStyle("""
                -fx-font-size: 12px;
                -fx-text-fill: #7F8C8D;
        """);

        VBox titleBox = new VBox(5, lblTitle, lblSubtitle);
        titleBox.setAlignment(Pos.CENTER);

        // Boutons stylisés
        Button btnFacturation = createChoiceButton("📄", "Facturation", "#3498DB");
        Button btnPaiement = createChoiceButton("💰", "Paiement", "#27AE60");
        Button btnAnnuler = createChoiceButton("✖", "Annuler", "#95A5A6");

        btnFacturation.setOnAction(ev -> {
            animateButtonClick(btnFacturation);
            new FactureView().show();
            fadeOutAndClose(choixStage);
        });

        btnPaiement.setOnAction(ev -> {
            animateButtonClick(btnPaiement);
            new PaiementView().show();
            fadeOutAndClose(choixStage);
        });

        btnAnnuler.setOnAction(ev -> fadeOutAndClose(choixStage));

        VBox buttonsBox = new VBox(12, btnFacturation, btnPaiement, btnAnnuler);
        buttonsBox.setAlignment(Pos.CENTER);

        VBox rootChoix = new VBox(20, titleBox, buttonsBox);
        rootChoix.setPadding(new Insets(30));
        rootChoix.setAlignment(Pos.CENTER);
        rootChoix.setStyle("""
                -fx-background-color: white;
                -fx-background-radius: 15;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 20, 0, 0, 5);
        """);

        // Background avec blur
        StackPane overlay = new StackPane(rootChoix);
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.3);");
        overlay.setAlignment(Pos.CENTER);

        Scene scene = new Scene(overlay, 380, 320);
        scene.setFill(Color.TRANSPARENT);
        choixStage.setScene(scene);

        // Animation d'entrée
        rootChoix.setOpacity(0);
        rootChoix.setScaleX(0.8);
        rootChoix.setScaleY(0.8);

        FadeTransition fade = new FadeTransition(Duration.millis(300), rootChoix);
        fade.setFromValue(0);
        fade.setToValue(1);

        ScaleTransition scale = new ScaleTransition(Duration.millis(300), rootChoix);
        scale.setFromX(0.8);
        scale.setFromY(0.8);
        scale.setToX(1.0);
        scale.setToY(1.0);

        ParallelTransition entrance = new ParallelTransition(fade, scale);
        entrance.setInterpolator(Interpolator.EASE_OUT);
        entrance.play();

        choixStage.showAndWait();
    }

    private Button createChoiceButton(String icon, String text, String color) {
        Label lblIcon = new Label(icon);
        lblIcon.setStyle("-fx-font-size: 24px;");

        Label lblText = new Label(text);
        lblText.setStyle("""
                -fx-font-size: 14px;
                -fx-font-weight: bold;
                -fx-text-fill: white;
        """);

        HBox content = new HBox(12, lblIcon, lblText);
        content.setAlignment(Pos.CENTER_LEFT);
        content.setMouseTransparent(true);

        Button btn = new Button();
        btn.setGraphic(content);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setPrefHeight(50);
        btn.setStyle(String.format("""
                -fx-background-color: %s;
                -fx-background-radius: 8;
                -fx-cursor: hand;
                -fx-padding: 0 20;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 4, 0, 0, 2);
        """, color));

        btn.setOnMouseEntered(e -> {
            btn.setStyle(btn.getStyle() + "-fx-opacity: 0.9;");
            animateScale(btn, 1.03);
        });

        btn.setOnMouseExited(e -> {
            btn.setStyle(btn.getStyle().replace("-fx-opacity: 0.9;", ""));
            animateScale(btn, 1.0);
        });

        return btn;
    }

    private void fadeOutAndClose(Stage stage) {
        FadeTransition fade = new FadeTransition(Duration.millis(200), stage.getScene().getRoot());
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setOnFinished(e -> stage.close());
        fade.play();
    }
}