package model;

public class Role {
    private int idRole;
    private String nomRole;
    private String description;

    public Role() {}

    public Role(int idRole, String nomRole, String description) {
        this.idRole = idRole;
        this.nomRole = nomRole;
        this.description = description;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
