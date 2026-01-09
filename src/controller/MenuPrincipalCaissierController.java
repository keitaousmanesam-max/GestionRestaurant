package controller;

import model.Utilisateur;

public class MenuPrincipalCaissierController {

    private Utilisateur caissier;

    public MenuPrincipalCaissierController(Utilisateur caissier) {
        this.caissier = caissier;
    }

    public Utilisateur getCaissier() {
        return caissier;
    }
}
