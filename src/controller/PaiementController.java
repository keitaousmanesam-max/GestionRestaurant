package controller;

import dao.PaiementDAO;
import model.Paiement;

import java.util.List;

public class PaiementController {

    private final PaiementDAO dao = new PaiementDAO();

    public List<Paiement> getPaiements() {
        return dao.getAll();
    }

    public boolean addPaiement(Paiement p) {
        return dao.add(p);
    }
}
