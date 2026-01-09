package model;

public class Plat {
    private int idPlat;          // identifiant unique du plat
    private String nomPlat;      // nom du plat
    private String categorie;    // catégorie (entrée, plat principal, dessert…)
    private double prix;         // prix du plat
    private String disponibilite; // disponible / indisponible

    public Plat() {}

    // ✅ Constructeur complet
    public Plat(int idPlat, String nomPlat, String categorie, double prix, String disponibilite) {
        this.idPlat = idPlat;
        this.nomPlat = nomPlat;
        this.categorie = categorie;
        this.prix = prix;
        this.disponibilite = disponibilite;
    }

    // ✅ Constructeur simplifié (souvent utilisé pour créer rapidement un plat)
    public Plat(String nomPlat, String categorie, double prix, String disponibilite) {
        this.nomPlat = nomPlat;
        this.categorie = categorie;
        this.prix = prix;
        this.disponibilite = disponibilite;
    }

    /* ===================== GETTERS ===================== */
    public int getIdPlat() { return idPlat; }
    public String getNomPlat() { return nomPlat; }
    public String getCategorie() { return categorie; }
    public double getPrix() { return prix; }
    public String getDisponibilite() { return disponibilite; }

    /* ===================== SETTERS ===================== */
    public void setIdPlat(int idPlat) { this.idPlat = idPlat; }
    public void setNomPlat(String nomPlat) { this.nomPlat = nomPlat; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public void setPrix(double prix) { this.prix = prix; }
    public void setDisponibilite(String disponibilite) { this.disponibilite = disponibilite; }

    @Override
    public String toString() {
        return nomPlat + " (" + categorie + ") - " + prix + " [" + disponibilite + "]";
    }
}
