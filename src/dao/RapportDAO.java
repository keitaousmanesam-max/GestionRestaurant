package dao;

import database.DBConnection;
import model.Rapport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RapportDAO {

    /* ===================== LISTE DES RAPPORTS ===================== */
    public List<Rapport> findAll() {
        List<Rapport> rapports = new ArrayList<>();
        String sql = "SELECT * FROM rapport";
        try (Statement st = DBConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Rapport r = new Rapport(
                        rs.getInt("id_rapport"),
                        rs.getDate("date").toLocalDate(),
                        rs.getDouble("chiffre_affaires"),
                        rs.getInt("plats_vendus")
                );
                rapports.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rapports;
    }

    /* ✅ ALIAS pour compatibilité */
    public List<Rapport> getAll() {
        return findAll();
    }

    /* ===================== AJOUT RAPPORT ===================== */
    public boolean add(Rapport r) {
        String sql = "INSERT INTO rapport(date, chiffre_affaires, plats_vendus) VALUES(?,?,?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(r.getDate()));
            ps.setDouble(2, r.getChiffreAffaires());
            ps.setInt(3, r.getPlatsVendus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===================== MODIFICATION RAPPORT ===================== */
    public boolean update(Rapport r) {
        String sql = "UPDATE rapport SET date=?, chiffre_affaires=?, plats_vendus=? WHERE id_rapport=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(r.getDate()));
            ps.setDouble(2, r.getChiffreAffaires());
            ps.setInt(3, r.getPlatsVendus());
            ps.setInt(4, r.getIdRapport());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===================== SUPPRESSION RAPPORT ===================== */
    public boolean delete(int idRapport) {
        String sql = "DELETE FROM rapport WHERE id_rapport=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idRapport);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===================== RECHERCHE PAR ID ===================== */
    public Rapport findById(int idRapport) {
        String sql = "SELECT * FROM rapport WHERE id_rapport=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idRapport);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Rapport(
                        rs.getInt("id_rapport"),
                        rs.getDate("date").toLocalDate(),
                        rs.getDouble("chiffre_affaires"),
                        rs.getInt("plats_vendus")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
