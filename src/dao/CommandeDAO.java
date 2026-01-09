package dao;

import database.DBConnection;
import model.Commande;
import model.TableRestaurant;
import model.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO {

    /* ===================== LISTE DES COMMANDES ===================== */
    public List<Commande> findAll() {
        List<Commande> commandes = new ArrayList<>();
        String sql = "SELECT c.*, t.id_table, t.numero, t.etat, u.id_utilisateur, u.nom, u.prenom " +
                "FROM commande c " +
                "JOIN table_restaurant t ON c.id_table = t.id_table " +
                "JOIN utilisateur u ON c.id_serveur = u.id_utilisateur";
        try (Statement st = DBConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                TableRestaurant table = new TableRestaurant(
                        rs.getInt("id_table"),
                        rs.getString("numero"),
                        rs.getString("etat")
                );
                Utilisateur serveur = new Utilisateur(
                        rs.getInt("id_utilisateur"),
                        rs.getString("nom"),
                        rs.getString("prenom")
                );
                Commande commande = new Commande(
                        rs.getInt("id_commande"),
                        table,
                        serveur,
                        rs.getTimestamp("date_commande").toLocalDateTime(),
                        rs.getString("statut")
                );
                commandes.add(commande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;
    }

    /* ===================== AJOUT COMMANDE ===================== */
    public boolean add(Commande c) {
        String sql = "INSERT INTO commande(id_table, id_serveur, date_commande, statut) VALUES(?,?,?,?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, c.getTable().getIdTable());
            ps.setInt(2, c.getServeur().getIdUtilisateur());
            ps.setTimestamp(3, Timestamp.valueOf(c.getDateCommande()));
            ps.setString(4, c.getStatut());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===================== MODIFICATION COMMANDE ===================== */
    public boolean update(Commande c) {
        String sql = "UPDATE commande SET id_table=?, id_serveur=?, date_commande=?, statut=? WHERE id_commande=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, c.getTable().getIdTable());
            ps.setInt(2, c.getServeur().getIdUtilisateur());
            ps.setTimestamp(3, Timestamp.valueOf(c.getDateCommande()));
            ps.setString(4, c.getStatut());
            ps.setInt(5, c.getIdCommande());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===================== SUPPRESSION COMMANDE ===================== */
    public boolean delete(int idCommande) {
        String sql = "DELETE FROM commande WHERE id_commande=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idCommande);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===================== ALIAS insert ===================== */
    public boolean insert(Commande c) {
        return add(c);
    }
}
