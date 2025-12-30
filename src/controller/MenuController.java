package controller;

import dao.MenuDAO;
import model.Menu;

import java.util.List;

public class MenuController {

    private final MenuDAO dao = new MenuDAO();

    // Récupérer tous les menus
    public List<Menu> getMenus() {
        return dao.getAll();
    }

    // Ajouter un menu
    public boolean addMenu(Menu m) {
        return dao.add(m);
    }

    // Modifier un menu
    public boolean updateMenu(Menu m) {
        return dao.update(m);
    }

    // Supprimer un menu
    public boolean deleteMenu(int id) {
        return dao.delete(id);
    }
}
