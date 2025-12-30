package model;

import java.time.LocalDate;

public class Rapport {

    private int idRapport;
    private LocalDate date;
    private double chiffreAffaires;
    private int platsVendus;

    public Rapport() {}

    public Rapport(int idRapport, LocalDate date, double chiffreAffaires, int platsVendus) {
        this.idRapport = idRapport;
        this.date = date;
        this.chiffreAffaires = chiffreAffaires;
        this.platsVendus = platsVendus;
    }

    public int getIdRapport() {
        return idRapport;
    }

    public void setIdRapport(int idRapport) {
        this.idRapport = idRapport;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getChiffreAffaires() {
        return chiffreAffaires;
    }

    public void setChiffreAffaires(double chiffreAffaires) {
        this.chiffreAffaires = chiffreAffaires;
    }

    public int getPlatsVendus() {
        return platsVendus;
    }

    public void setPlatsVendus(int platsVendus) {
        this.platsVendus = platsVendus;
    }

    @Override
    public String toString() {
        return date + " - CA: " + chiffreAffaires + " - Plats vendus: " + platsVendus;
    }
}
