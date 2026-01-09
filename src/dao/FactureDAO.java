package dao;

import database.DBConnection;
import model.Commande;
import model.Facture;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FactureDAO {

    /* ===================== LISTE DES FACTURES ===================== */
    public List<Facture> getAll() {
        List<Facture> factures = new ArrayList<>();
        String sql = "SELECT * FROM facture";
        try (Statement st = DBConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                // ⚠️ Ici, il faut récupérer la commande associée
                Commande commande = new Commande(rs.getInt("id_commande")); // simplifié

                Facture f = new Facture(
                        rs.getInt("id_facture"),
                        commande,
                        rs.getTimestamp("date_facture").toLocalDateTime(),
                        rs.getDouble("montant_total"),
                        rs.getString("mode_paiement")
                );
                factures.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return factures;
    }

    /* ===================== AJOUT FACTURE ===================== */
    public boolean add(Facture f) {
        String sql = "INSERT INTO facture(id_commande, date_facture, montant_total, mode_paiement) VALUES(?,?,?,?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, f.getCommande().getIdCommande());
            ps.setTimestamp(2, Timestamp.valueOf(f.getDateFacture()));
            ps.setDouble(3, f.getMontantTotal());
            ps.setString(4, f.getModePaiement());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===================== SUPPRESSION ===================== */
    public boolean delete(int idFacture) {
        String sql = "DELETE FROM facture WHERE id_facture=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idFacture);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
