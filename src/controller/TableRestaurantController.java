package controller;

import dao.TableRestaurantDAO;
import model.TableRestaurant;

import java.util.List;

public class TableRestaurantController {

    private TableRestaurantDAO dao = new TableRestaurantDAO();

    public List<TableRestaurant> getAllTables() {
        return dao.findAll();
    }
}
