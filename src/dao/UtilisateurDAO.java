package dao;

import database.DBConnection;
import model.Role;
import model.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {

    /* ===================== LISTE DES UTILISATEURS ===================== */
    public List<Utilisateur> findAll() {
        List<Utilisateur> list = new ArrayList<>();
        String sql = "SELECT u.*, r.nom_role, r.description FROM utilisateur u " +
                "JOIN role r ON u.id_role = r.id_role";
        try (Statement st = DBConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Role role = new Role(
                        rs.getInt("id_role"),
                        rs.getString("nom_role"),
                        rs.getString("description")
                );
                list.add(new Utilisateur(
                        rs.getInt("id_utilisateur"),
                        rs.getString("identifiant"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("mot_de_passe"),
                        role,
                        rs.getString("statut")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ===================== AJOUT ===================== */
    public boolean add(Utilisateur u) {
        String sql = "INSERT INTO utilisateur(identifiant, nom, prenom, mot_de_passe, statut, id_role) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, u.getIdentifiant());
            ps.setString(2, u.getNom());
            ps.setString(3, u.getPrenom());
            ps.setString(4, u.getMotDePasse());
            ps.setString(5, u.getStatut());
            ps.setInt(6, u.getRole().getIdRole());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===================== MODIFICATION ===================== */
    public boolean update(Utilisateur u) {
        String sql = "UPDATE utilisateur SET identifiant=?, nom=?, prenom=?, mot_de_passe=?, statut=?, id_role=? WHERE id_utilisateur=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, u.getIdentifiant());
            ps.setString(2, u.getNom());
            ps.setString(3, u.getPrenom());
            ps.setString(4, u.getMotDePasse());
            ps.setString(5, u.getStatut());
            ps.setInt(6, u.getRole().getIdRole());
            ps.setInt(7, u.getIdUtilisateur());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===================== SUPPRESSION ===================== */
    public boolean delete(int idUtilisateur) {
        String sql = "DELETE FROM utilisateur WHERE id_utilisateur=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idUtilisateur);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
