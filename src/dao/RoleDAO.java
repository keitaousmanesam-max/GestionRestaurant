package dao;

import database.DBConnection;
import model.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {

    // READ
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM role";

        try (Connection c = DBConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                roles.add(new Role(
                        rs.getInt("id_role"),
                        rs.getString("nom_role"),
                        rs.getString("description")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roles;
    }

    // CREATE
    public void ajouter(Role role) {
        String sql = "INSERT INTO role (nom_role, description) VALUES (?, ?)";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, role.getNomRole());
            ps.setString(2, role.getDescription());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void modifier(Role role) {
        String sql = "UPDATE role SET nom_role=?, description=? WHERE id_role=?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, role.getNomRole());
            ps.setString(2, role.getDescription());
            ps.setInt(3, role.getIdRole());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void supprimer(int idRole) {
        String sql = "DELETE FROM role WHERE id_role=?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, idRole);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
