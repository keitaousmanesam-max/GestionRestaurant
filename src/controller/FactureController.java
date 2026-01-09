package controller;

import dao.FactureDAO;
import model.Facture;

import java.util.List;

public class FactureController {

    private final FactureDAO dao = new FactureDAO();

    public List<Facture> getFactures() {
        return dao.getAll();
    }

    public boolean addFacture(Facture f) {
        return dao.add(f);
    }
}
