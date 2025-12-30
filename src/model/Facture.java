package model;

import java.time.LocalDateTime;

public class Facture {
    private int idFacture;
    private Commande commande;
    private LocalDateTime dateFacture;
    private double montantTotal;
    private String modePaiement; // Espèces, Carte, etc.

    public Facture() {}

    // ✅ Constructeur complet correspondant à ton appel
    public Facture(int idFacture, Commande commande, LocalDateTime dateFacture, double montantTotal, String modePaiement) {
        this.idFacture = idFacture;
        this.commande = commande;
        this.dateFacture = dateFacture;
        this.montantTotal = montantTotal;
        this.modePaiement = modePaiement;
    }

    /* ===================== GETTERS ===================== */
    public int getIdFacture() { return idFacture; }
    public Commande getCommande() { return commande; }
    public LocalDateTime getDateFacture() { return dateFacture; }
    public double getMontantTotal() { return montantTotal; }
    public String getModePaiement() { return modePaiement; }

    /* ===================== SETTERS ===================== */
    public void setIdFacture(int idFacture) { this.idFacture = idFacture; }
    public void setCommande(Commande commande) { this.commande = commande; }
    public void setDateFacture(LocalDateTime dateFacture) { this.dateFacture = dateFacture; }
    public void setMontantTotal(double montantTotal) { this.montantTotal = montantTotal; }
    public void setModePaiement(String modePaiement) { this.modePaiement = modePaiement; }

    @Override
    public String toString() {
        return "Facture #" + idFacture +
                " | Commande: " + (commande != null ? commande.getIdCommande() : "N/A") +
                " | Date: " + dateFacture +
                " | Montant: " + montantTotal +
                " | Paiement: " + modePaiement;
    }
}
