package model;

public class Utilisateur {
    private int idUtilisateur;
    private String identifiant;
    private String nom;
    private String prenom;
    private String motDePasse;
    private Role role;
    private String statut;

    public Utilisateur() {}

    // ✅ Constructeur complet
    public Utilisateur(int idUtilisateur, String identifiant, String nom, String prenom, String motDePasse, Role role, String statut) {
        this.idUtilisateur = idUtilisateur;
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.role = role;
        this.statut = statut;
    }

    // ✅ Constructeur simplifié (utilisé dans CommandeDAO)
    public Utilisateur(int idUtilisateur, String nom, String prenom) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
    }

    /* ===================== GETTERS ===================== */
    public int getIdUtilisateur() { return idUtilisateur; }
    public String getIdentifiant() { return identifiant; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getMotDePasse() { return motDePasse; }
    public Role getRole() { return role; }
    public String getStatut() { return statut; }

    /* ===================== SETTERS ===================== */
    public void setIdUtilisateur(int idUtilisateur) { this.idUtilisateur = idUtilisateur; }
    public void setIdentifiant(String identifiant) { this.identifiant = identifiant; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
    public void setRole(Role role) { this.role = role; }
    public void setStatut(String statut) { this.statut = statut; }

    @Override
    public String toString() {
        return nom + " " + prenom + " (ID: " + idUtilisateur + ")";
    }
}
