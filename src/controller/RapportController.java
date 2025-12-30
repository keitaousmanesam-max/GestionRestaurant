package controller;

import dao.RapportDAO;
import model.Rapport;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class RapportController {

    private final RapportDAO dao = new RapportDAO();

    /* ===================== LISTE DES RAPPORTS ===================== */
    public List<Rapport> getRapports() {
        return dao.getAll(); // alias de findAll()
    }

    /* ===================== AJOUT RAPPORT ===================== */
    public boolean addRapport(Rapport r) {
        return dao.add(r);
    }

    /* ===================== MODIFICATION RAPPORT ===================== */
    public boolean updateRapport(Rapport r) {
        return dao.update(r);
    }

    /* ===================== SUPPRESSION RAPPORT ===================== */
    public boolean deleteRapport(int idRapport) {
        return dao.delete(idRapport);
    }

    /* ===================== RECHERCHE PAR ID ===================== */
    public Rapport getRapportById(int idRapport) {
        return dao.findById(idRapport);
    }

    /* ===================== RAPPORTS PAR DATE ===================== */
    public List<Rapport> getRapportsByDate(LocalDate date) {
        return getRapports().stream()
                .filter(r -> r.getDate().equals(date))
                .collect(Collectors.toList());
    }

    /* ===================== STATISTIQUES ===================== */

    // Chiffre d’affaires total
    public double getChiffreAffairesTotal() {
        return getRapports().stream()
                .mapToDouble(Rapport::getChiffreAffaires)
                .sum();
    }

    // Nombre total de plats vendus
    public int getTotalPlatsVendus() {
        return getRapports().stream()
                .mapToInt(Rapport::getPlatsVendus)
                .sum();
    }

    // Chiffre d’affaires moyen
    public double getChiffreAffairesMoyen() {
        List<Rapport> rapports = getRapports();
        if (rapports.isEmpty()) return 0;
        return getChiffreAffairesTotal() / rapports.size();
    }

    // Rapport du jour
    public Rapport getRapportDuJour(LocalDate date) {
        return getRapports().stream()
                .filter(r -> r.getDate().equals(date))
                .findFirst()
                .orElse(null);
    }
}
