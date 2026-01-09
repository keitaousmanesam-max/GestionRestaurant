package dao;

import database.DBConnection;
import model.Menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {

    // Lire tous les menus
    public List<Menu> getAll() {
        List<Menu> list = new ArrayList<>();
        String sql = "SELECT * FROM menu";
        try (Statement st = DBConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Menu m = new Menu(
                        rs.getInt("id_menu"),
                        rs.getString("nom_menu"),
                        rs.getString("categorie")
                );
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Ajouter un menu
    public boolean add(Menu m) {
        String sql = "INSERT INTO menu (nom_menu, categorie) VALUES (?, ?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, m.getNomMenu());
            ps.setString(2, m.getCategorie());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Modifier un menu
    public boolean update(Menu m) {
        String sql = "UPDATE menu SET nom_menu=?, categorie=? WHERE id_menu=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, m.getNomMenu());
            ps.setString(2, m.getCategorie());
            ps.setInt(3, m.getIdMenu());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Supprimer un menu
    public boolean delete(int id) {
        String sql = "DELETE FROM menu WHERE id_menu=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
