package controller;

import dao.CommandeDAO;
import dao.CommandePlatDAO;
import model.Commande;
import model.CommandePlat;

import java.util.ArrayList;
import java.util.List;

public class CommandeController {
    private final CommandeDAO dao = new CommandeDAO();
    private final CommandePlatDAO daoPlat = new CommandePlatDAO();

    /* ===================== LISTE DES COMMANDES ===================== */
    public List<Commande> getCommandes() {
        return dao.findAll();
    }

    /* ===================== AJOUT COMMANDE ===================== */
    public void addCommande(Commande commande) {
        dao.insert(commande);

        // ajouter les plats liés à la commande
        for (CommandePlat cp : commande.getPlats()) {
            daoPlat.insert(cp, commande.getIdCommande());
        }
    }

    /* ===================== MODIFICATION COMMANDE ===================== */
    public void updateCommande(Commande commande) {
        dao.update(commande);
    }

    /* ===================== SUPPRESSION COMMANDE ===================== */
    public void deleteCommande(int idCommande) {
        dao.delete(idCommande);
    }

    /* ===================== LISTE DES PLATS D’UNE COMMANDE ===================== */
    public List<CommandePlat> getPlatsByCommande(int idCommande) {
        return daoPlat.findByCommande(idCommande);
    }

    /* ===================== SUPPRESSION D’UN PLAT D’UNE COMMANDE ===================== */
    public void removePlatFromCommande(int idCommande, int idPlat) {
        daoPlat.delete(idCommande, idPlat);
    }

    /* ===================== LISTE DE TOUS LES PLATS DE TOUTES LES COMMANDES ===================== */
    public List<CommandePlat> getAllPlats() {
        List<CommandePlat> allPlats = new ArrayList<>();
        for (Commande c : dao.findAll()) {
            allPlats.addAll(daoPlat.findByCommande(c.getIdCommande()));
        }
        return allPlats;
    }
}
