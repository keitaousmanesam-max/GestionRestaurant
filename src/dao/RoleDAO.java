package dao;

import database.DBConnection;
import model.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {

    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM role"; // table 'role' en BDD
        try (Statement st = DBConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                roles.add(new Role(
                        rs.getInt("id_role"),
                        rs.getString("nom_role"),
                        rs.getString("description") // si tu as une colonne description
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public void insert(Role role) {
        String sql = "INSERT INTO role(nom_role, description) VALUES(?, ?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, role.getNomRole());
            ps.setString(2, role.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Role role) {
        String sql = "UPDATE role SET nom_role=?, description=? WHERE id_role=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, role.getNomRole());
            ps.setString(2, role.getDescription());
            ps.setInt(3, role.getIdRole());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int idRole) {
        String sql = "DELETE FROM role WHERE id_role=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idRole);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
