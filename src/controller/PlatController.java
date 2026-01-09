package controller;

import dao.PlatDAO;
import model.Plat;
import java.util.List;

public class PlatController {
    private final PlatDAO dao = new PlatDAO();

    /* ===================== LISTE DES PLATS ===================== */
    public List<Plat> getPlats() {
        return dao.findAll();
    }

    /* ===================== AJOUT PLAT ===================== */
    public boolean addPlat(Plat plat) {
        return dao.insert(plat);   // ✅ retourne un boolean
    }

    /* ===================== MODIFICATION PLAT ===================== */
    public boolean updatePlat(Plat plat) {
        return dao.update(plat);   // ✅ retourne un boolean
    }

    /* ===================== SUPPRESSION PLAT ===================== */
    public boolean deletePlat(int idPlat) {
        return dao.delete(idPlat); // ✅ retourne un boolean
    }

    /* ===================== RECHERCHE PAR ID ===================== */
    public Plat getPlatById(int idPlat) {
        return dao.findById(idPlat);
    }
}
