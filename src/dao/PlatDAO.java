package dao;

import database.DBConnection;
import model.Plat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlatDAO {

    /* ===================== LISTE DES PLATS ===================== */
    public List<Plat> findAll() {
        List<Plat> plats = new ArrayList<>();
        String sql = "SELECT * FROM plat";
        try (Statement st = DBConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Plat plat = new Plat(
                        rs.getInt("id_plat"),
                        rs.getString("nom_plat"),
                        rs.getString("categorie"),
                        rs.getDouble("prix"),
                        rs.getString("disponibilite")
                );
                plats.add(plat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plats;
    }

    /* ===================== AJOUT PLAT ===================== */
    public boolean add(Plat p) {
        String sql = "INSERT INTO plat(nom_plat, categorie, prix, disponibilite) VALUES(?,?,?,?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, p.getNomPlat());
            ps.setString(2, p.getCategorie());
            ps.setDouble(3, p.getPrix());
            ps.setString(4, p.getDisponibilite());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===================== ALIAS insert ===================== */
    public boolean insert(Plat p) {
        return add(p);
    }

    /* ===================== MODIFICATION PLAT ===================== */
    public boolean update(Plat p) {
        String sql = "UPDATE plat SET nom_plat=?, categorie=?, prix=?, disponibilite=? WHERE id_plat=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, p.getNomPlat());
            ps.setString(2, p.getCategorie());
            ps.setDouble(3, p.getPrix());
            ps.setString(4, p.getDisponibilite());
            ps.setInt(5, p.getIdPlat());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===================== SUPPRESSION PLAT ===================== */
    public boolean delete(int idPlat) {
        String sql = "DELETE FROM plat WHERE id_plat=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idPlat);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* ===================== RECHERCHE PAR ID ===================== */
    public Plat findById(int idPlat) {
        String sql = "SELECT * FROM plat WHERE id_plat=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idPlat);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Plat(
                        rs.getInt("id_plat"),
                        rs.getString("nom_plat"),
                        rs.getString("categorie"),
                        rs.getDouble("prix"),
                        rs.getString("disponibilite")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
