package controller;

import model.Utilisateur;

public class MenuPrincipalServeurController {

    private Utilisateur serveur;

    public MenuPrincipalServeurController(Utilisateur serveur) {
        this.serveur = serveur;
    }

    public Utilisateur getServeur() {
        return serveur;
    }
}
