package model;

import java.time.LocalDateTime;
import java.util.List;

public class Commande {
    private int idCommande;
    private TableRestaurant table;   // table associée
    private Utilisateur serveur;     // serveur associé
    private LocalDateTime dateCommande;
    private String statut;           // EN_COURS, SERVIE, etc.
    private List<CommandePlat> plats; // ✅ liste des plats de la commande

    public Commande() {}

    // ✅ Constructeur complet
    public Commande(int idCommande, TableRestaurant table, Utilisateur serveur, LocalDateTime dateCommande, String statut, List<CommandePlat> plats) {
        this.idCommande = idCommande;
        this.table = table;
        this.serveur = serveur;
        this.dateCommande = dateCommande;
        this.statut = statut;
        this.plats = plats;
    }

    // ✅ Constructeur simplifié (sans liste de plats)
    public Commande(int idCommande, TableRestaurant table, Utilisateur serveur, LocalDateTime dateCommande, String statut) {
        this.idCommande = idCommande;
        this.table = table;
        this.serveur = serveur;
        this.dateCommande = dateCommande;
        this.statut = statut;
    }

    // ✅ Constructeur minimal (juste ID)
    public Commande(int idCommande) {
        this.idCommande = idCommande;
    }

    /* ===================== GETTERS ===================== */
    public int getIdCommande() { return idCommande; }
    public TableRestaurant getTable() { return table; }
    public Utilisateur getServeur() { return serveur; }
    public LocalDateTime getDateCommande() { return dateCommande; }
    public String getStatut() { return statut; }
    public List<CommandePlat> getPlats() { return plats; }   // ✅ méthode manquante

    /* ===================== SETTERS ===================== */
    public void setIdCommande(int idCommande) { this.idCommande = idCommande; }
    public void setTable(TableRestaurant table) { this.table = table; }
    public void setServeur(Utilisateur serveur) { this.serveur = serveur; }
    public void setDateCommande(LocalDateTime dateCommande) { this.dateCommande = dateCommande; }
    public void setStatut(String statut) { this.statut = statut; }
    public void setPlats(List<CommandePlat> plats) { this.plats = plats; } // ✅ setter

    @Override
    public String toString() {
        return "Commande #" + idCommande +
                " | Table: " + (table != null ? table.getNumero() : "N/A") +
                " | Serveur: " + (serveur != null ? serveur.getNom() : "N/A") +
                " | Statut: " + statut +
                " | Nb Plats: " + (plats != null ? plats.size() : 0);
    }
}
