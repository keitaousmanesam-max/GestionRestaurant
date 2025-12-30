package model;

public class CommandePlat {
    private int idCommandePlat;   // identifiant unique de la ligne commande-plat
    private Plat plat;            // plat associé
    private int quantite;         // quantité commandée

    public CommandePlat() {}

    // ✅ Constructeur complet (avec objet Plat)
    public CommandePlat(int idCommandePlat, Plat plat, int quantite) {
        this.idCommandePlat = idCommandePlat;
        this.plat = plat;
        this.quantite = quantite;
    }

    // ✅ Constructeur simplifié (souvent utilisé lors de l’ajout)
    public CommandePlat(Plat plat, int quantite) {
        this.plat = plat;
        this.quantite = quantite;
    }

    // ✅ Nouveau constructeur (avec trois int : idCommandePlat, idPlat, quantite)
    public CommandePlat(int idCommandePlat, int idPlat, int quantite) {
        this.idCommandePlat = idCommandePlat;
        this.plat = new Plat();       // on crée un Plat vide
        this.plat.setIdPlat(idPlat);  // on affecte juste l’ID
        this.quantite = quantite;
    }

    /* ===================== GETTERS ===================== */
    public int getIdCommandePlat() { return idCommandePlat; }
    public Plat getPlat() { return plat; }
    public int getQuantite() { return quantite; }

    // ✅ Getter délégué pour idPlat
    public int getIdPlat() {
        return plat != null ? plat.getIdPlat() : -1;
    }

    /* ===================== SETTERS ===================== */
    public void setIdCommandePlat(int idCommandePlat) { this.idCommandePlat = idCommandePlat; }
    public void setPlat(Plat plat) { this.plat = plat; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    @Override
    public String toString() {
        return (plat != null ? plat.getNomPlat() : "Plat inconnu") + " x " + quantite;
    }
}
