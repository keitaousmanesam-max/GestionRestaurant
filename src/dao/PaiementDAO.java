package dao;

import database.DBConnection;
import model.Paiement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaiementDAO {

    // 🔹 Ajouter un paiement
    public boolean add(Paiement paiement) {
        String sql = "INSERT INTO paiement(id_facture, type_paiement, montant, date_paiement) VALUES(?,?,?,?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, paiement.getIdFacture());
            ps.setString(2, paiement.getTypePaiement());
            ps.setDouble(3, paiement.getMontant());
            ps.setTimestamp(4, Timestamp.valueOf(paiement.getDatePaiement()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Récupérer tous les paiements
    public List<Paiement> getAll() {
        List<Paiement> list = new ArrayList<>();
        String sql = "SELECT * FROM paiement";
        try (Statement st = DBConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Paiement p = new Paiement();
                p.setIdPaiement(rs.getInt("id_paiement"));
                p.setIdFacture(rs.getInt("id_facture"));
                p.setTypePaiement(rs.getString("type_paiement"));
                p.setMontant(rs.getDouble("montant"));
                Timestamp ts = rs.getTimestamp("date_paiement");
                if(ts != null) p.setDatePaiement(ts.toLocalDateTime());
                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 🔹 Récupérer un paiement par ID
    public Paiement getById(int idPaiement) {
        String sql = "SELECT * FROM paiement WHERE id_paiement=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idPaiement);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Paiement p = new Paiement();
                p.setIdPaiement(rs.getInt("id_paiement"));
                p.setIdFacture(rs.getInt("id_facture"));
                p.setTypePaiement(rs.getString("type_paiement"));
                p.setMontant(rs.getDouble("montant"));
                Timestamp ts = rs.getTimestamp("date_paiement");
                if(ts != null) p.setDatePaiement(ts.toLocalDateTime());
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 🔹 Supprimer un paiement
    public boolean delete(int idPaiement) {
        String sql = "DELETE FROM paiement WHERE id_paiement=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idPaiement);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Mettre à jour un paiement
    public boolean update(Paiement paiement) {
        String sql = "UPDATE paiement SET id_facture=?, type_paiement=?, montant=?, date_paiement=? WHERE id_paiement=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, paiement.getIdFacture());
            ps.setString(2, paiement.getTypePaiement());
            ps.setDouble(3, paiement.getMontant());
            ps.setTimestamp(4, Timestamp.valueOf(paiement.getDatePaiement()));
            ps.setInt(5, paiement.getIdPaiement());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
