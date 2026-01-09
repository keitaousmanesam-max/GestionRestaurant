package model;

import java.time.LocalDateTime;

public class Paiement {

    private int idPaiement;
    private int idFacture;
    private String typePaiement;
    private double montant;
    private LocalDateTime datePaiement;

    // 🔹 Constructeur vide
    public Paiement() {}

    // 🔹 Constructeur complet
    public Paiement(int idPaiement, int idFacture, String typePaiement, double montant, LocalDateTime datePaiement) {
        this.idPaiement = idPaiement;
        this.idFacture = idFacture;
        this.typePaiement = typePaiement;
        this.montant = montant;
        this.datePaiement = datePaiement;
    }

    // 🔹 Getters & Setters
    public int getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(int idPaiement) {
        this.idPaiement = idPaiement;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public String getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(String typePaiement) {
        this.typePaiement = typePaiement;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDateTime getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDateTime datePaiement) {
        this.datePaiement = datePaiement;
    }
}
