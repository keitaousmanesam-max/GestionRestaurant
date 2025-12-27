package controller;

import dao.RoleDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Role;

public class RoleController {

    private RoleDAO dao = new RoleDAO();

    public ObservableList<Role> charger() {
        return FXCollections.observableArrayList(dao.getAllRoles());
    }

    public void ajouter(Role r) {
        dao.ajouter(r);
    }

    public void modifier(Role r) {
        dao.modifier(r);
    }

    public void supprimer(int id) {
        dao.supprimer(id);
    }
}
