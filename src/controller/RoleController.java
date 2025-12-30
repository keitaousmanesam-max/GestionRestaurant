package controller;

import dao.RoleDAO;
import model.Role;

import java.util.List;

public class RoleController {
    private final RoleDAO dao = new RoleDAO();

    public List<Role> getAllRoles() {
        return dao.findAll();
    }

    public void addRole(Role role) {
        dao.insert(role);
    }

    public void updateRole(Role role) {
        dao.update(role);
    }

    public void deleteRole(int idRole) {
        dao.delete(idRole);
    }
}
