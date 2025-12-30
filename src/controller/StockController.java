package controller;

import dao.StockDAO;
import model.Stock;

import java.util.List;

public class StockController {

    private final StockDAO dao = new StockDAO();

    public List<Stock> getAllStock() {
        return dao.getAll();
    }

    public boolean addStock(Stock s) {
        return dao.add(s);
    }

    public boolean updateStock(Stock s) {
        return dao.update(s);
    }

    public boolean deleteStock(int idStock) {
        return dao.delete(idStock);
    }
}
