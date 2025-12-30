package model;

public class Role {
    private int idRole;
    private String nomRole;
    private String description; // optionnel

    public Role() {}

    // ✅ Constructeur complet
    public Role(int idRole, String nomRole, String description) {
        this.idRole = idRole;
        this.nomRole = nomRole;
        this.description = description;
    }

    // ✅ Constructeur simplifié
    public Role(int idRole, String nomRole) {
        this.idRole = idRole;
        this.nomRole = nomRole;
    }

    public int getIdRole() { return idRole; }
    public void setIdRole(int idRole) { this.idRole = idRole; }

    public String getNomRole() { return nomRole; }
    public void setNomRole(String nomRole) { this.nomRole = nomRole; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return nomRole;
    }
}
