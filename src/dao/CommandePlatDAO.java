package dao;

import database.DBConnection;
import model.CommandePlat;
import model.Plat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandePlatDAO {

    /* ===================== LISTE DES PLATS D’UNE COMMANDE ===================== */
    public List<CommandePlat> findByCommande(int idCommande) {
        List<CommandePlat> list = new ArrayList<>();
        String sql = "SELECT cp.*, p.nom_plat, p.categorie, p.prix, p.disponibilite " +
                "FROM commande_plat cp " +
                "JOIN plat p ON cp.id_plat = p.id_plat " +
                "WHERE cp.id_commande = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idCommande);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Plat plat = new Plat(
                        rs.getInt("id_plat"),
                        rs.getString("nom_plat"),
                        rs.getString("categorie"),
                        rs.getDouble("prix"),
                        rs.getString("disponibilite")
                );
                CommandePlat cp = new CommandePlat(
                        rs.getInt("id_commande_plat"),
                        plat,
                        rs.getInt("quantite")
                );
                list.add(cp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /* ===================== AJOUT PLAT À UNE COMMANDE ===================== */
    public boolean add(CommandePlat cp, int idCommande) {
        String sql = "INSERT INTO commande_plat(id_commande, id_plat, quantite) VALUES(?,?,?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idCommande);
            ps.setInt(2, cp.getIdPlat()); // ✅ utilise getIdPlat()
            ps.setInt(3, cp.getQuantite());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===================== SUPPRESSION PAR idCommandePlat ===================== */
    public boolean delete(int idCommandePlat) {
        String sql = "DELETE FROM commande_plat WHERE id_commande_plat=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idCommandePlat);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===================== SUPPRESSION PAR (idCommande, idPlat) ===================== */
    public boolean delete(int idCommande, int idPlat) {
        String sql = "DELETE FROM commande_plat WHERE id_commande=? AND id_plat=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idCommande);
            ps.setInt(2, idPlat);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===================== ALIAS insert ===================== */
    public boolean insert(CommandePlat cp, int idCommande) {
        return add(cp, idCommande);
    }
}
