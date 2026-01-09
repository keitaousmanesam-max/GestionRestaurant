package controller;

import dao.CommandePlatDAO;
import model.CommandePlat;

import java.util.List;

public class CommandePlatController {

    private CommandePlatDAO dao = new CommandePlatDAO();

    public List<CommandePlat> getPlatsParCommande(int idCommande) {
        return dao.findByCommande(idCommande);
    }
}
