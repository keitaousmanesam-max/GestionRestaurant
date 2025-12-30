package controller;

import model.Utilisateur;

public class LoginController {

    private final AuthController authController = new AuthController();

    /**
     * Délègue l'authentification à AuthController
     * @param identifiant identifiant de connexion
     * @param mdp mot de passe
     * @return l'utilisateur si trouvé, sinon null
     */
    public Utilisateur login(String identifiant, String mdp) {
        return authController.login(identifiant, mdp); // ✅ correction
    }
}
