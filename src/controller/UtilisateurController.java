package controller;

import dao.UtilisateurDAO;
import model.Utilisateur;

import java.util.List;

public class UtilisateurController {
    private final UtilisateurDAO dao = new UtilisateurDAO();

    public List<Utilisateur> getAllUtilisateurs() {
        return dao.findAll();
    }

    public boolean addUtilisateur(Utilisateur u) {
        return dao.add(u);
    }

    public boolean updateUtilisateur(Utilisateur u) {
        return dao.update(u);
    }

    public boolean deleteUtilisateur(int idUtilisateur) {
        return dao.delete(idUtilisateur);
    }
}
