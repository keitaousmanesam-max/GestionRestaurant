package controller;

import dao.UtilisateurDAO;
import database.DBConnection;
import model.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthController {

    private final UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    /**
     * Vérifie si un utilisateur peut se connecter avec identifiant + mot de passe
     * @param identifiant identifiant de connexion
     * @param motDePasse mot de passe
     * @return l'objet Utilisateur si authentifié, sinon null
     */
    public Utilisateur login(String identifiant, String motDePasse) {
        String sql = "SELECT u.*, r.nom_role, r.description FROM utilisateur u " +
                "JOIN role r ON u.id_role = r.id_role " +
                "WHERE u.identifiant=? AND u.mot_de_passe=? AND u.statut='ACTIF'";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, identifiant);
            ps.setString(2, motDePasse);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Utilisateur(
                        rs.getInt("id_utilisateur"),
                        rs.getString("identifiant"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("mot_de_passe"),
                        new model.Role(
                                rs.getInt("id_role"),
                                rs.getString("nom_role"),
                                rs.getString("description")
                        ),
                        rs.getString("statut")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // si échec
    }

    /**
     * Vérifie si un utilisateur est administrateur
     * @param utilisateur utilisateur connecté
     * @return true si son rôle est ADMIN
     */
    public boolean isAdmin(Utilisateur utilisateur) {
        return utilisateur != null && utilisateur.getRole().getNomRole().equalsIgnoreCase("ADMIN");
    }

    /**
     * Vérifie si un utilisateur est caissier
     */
    public boolean isCaissier(Utilisateur utilisateur) {
        return utilisateur != null && utilisateur.getRole().getNomRole().equalsIgnoreCase("CAISSIER");
    }

    /**
     * Vérifie si un utilisateur est serveur
     */
    public boolean isServeur(Utilisateur utilisateur) {
        return utilisateur != null && utilisateur.getRole().getNomRole().equalsIgnoreCase("SERVEUR");
    }

    /**
     * Vérifie si un utilisateur est gestionnaire
     */
    public boolean isGestionnaire(Utilisateur utilisateur) {
        return utilisateur != null && utilisateur.getRole().getNomRole().equalsIgnoreCase("GESTIONNAIRE");
    }
}
