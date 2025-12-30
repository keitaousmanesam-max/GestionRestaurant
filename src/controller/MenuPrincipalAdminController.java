package controller;

import model.Utilisateur;

public class MenuPrincipalAdminController {

    private Utilisateur admin;

    public MenuPrincipalAdminController(Utilisateur admin) {
        this.admin = admin;
    }

    public Utilisateur getAdmin() {
        return admin;
    }
}
