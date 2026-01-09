package controller;

import model.Utilisateur;

public class MenuPrincipalGestionnaireController {

    private Utilisateur gestionnaire;

    public MenuPrincipalGestionnaireController(Utilisateur gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    public Utilisateur getGestionnaire() {
        return gestionnaire;
    }
}
