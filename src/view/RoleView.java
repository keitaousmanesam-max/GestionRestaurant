package view;

import controller.RoleController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Role;

public class RoleView extends Application {

    private RoleController controller = new RoleController();
    private TableView<Role> table = new TableView<>();

    @Override
    public void start(Stage stage) {

        TableColumn<Role, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("idRole"));

        TableColumn<Role, String> colNom = new TableColumn<>("Nom");
        colNom.setCellValueFactory(new PropertyValueFactory<>("nomRole"));

        TableColumn<Role, String> colDesc = new TableColumn<>("Description");
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));

        table.getColumns().addAll(colId, colNom, colDesc);
        table.setItems(controller.charger());

        TextField txtNom = new TextField();
        txtNom.setPromptText("Nom du rôle");

        TextField txtDesc = new TextField();
        txtDesc.setPromptText("Description");

        Button btnAdd = new Button("Ajouter");
        Button btnUpdate = new Button("Modifier");
        Button btnDelete = new Button("Supprimer");

        btnAdd.setOnAction(e -> {
            controller.ajouter(new Role(0, txtNom.getText(), txtDesc.getText()));
            table.setItems(controller.charger());
            txtNom.clear();
            txtDesc.clear();
        });

        btnUpdate.setOnAction(e -> {
            Role r = table.getSelectionModel().getSelectedItem();
            if (r != null) {
                r.setNomRole(txtNom.getText());
                r.setDescription(txtDesc.getText());
                controller.modifier(r);
                table.setItems(controller.charger());
            }
        });

        btnDelete.setOnAction(e -> {
            Role r = table.getSelectionModel().getSelectedItem();
            if (r != null) {
                controller.supprimer(r.getIdRole());
                table.setItems(controller.charger());
            }
        });

        table.setOnMouseClicked(e -> {
            Role r = table.getSelectionModel().getSelectedItem();
            if (r != null) {
                txtNom.setText(r.getNomRole());
                txtDesc.setText(r.getDescription());
            }
        });

        VBox root = new VBox(10,
                table,
                new HBox(10, txtNom, txtDesc),
                new HBox(10, btnAdd, btnUpdate, btnDelete)
        );

        stage.setScene(new Scene(root, 700, 450));
        stage.setTitle("Gestion des rôles");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
