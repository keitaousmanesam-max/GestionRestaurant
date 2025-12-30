package dao;

import database.DBConnection;
import model.Stock;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {

    // Récupérer tous les produits
    public List<Stock> getAll() {
        List<Stock> list = new ArrayList<>();
        String sql = "SELECT * FROM stock";

        try (Statement st = DBConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Stock s = new Stock();
                s.setIdStock(rs.getInt("id_stock"));
                s.setNomProduit(rs.getString("nom_produit"));
                s.setQuantite(rs.getInt("quantite"));
                s.setSeuilMin(rs.getInt("seuil_min"));
                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Ajouter un produit
    public boolean add(Stock s) {
        String sql = "INSERT INTO stock(nom_produit, quantite, seuil_min) VALUES(?,?,?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, s.getNomProduit());
            ps.setInt(2, s.getQuantite());
            ps.setInt(3, s.getSeuilMin());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Modifier un produit
    public boolean update(Stock s) {
        String sql = "UPDATE stock SET nom_produit=?, quantite=?, seuil_min=? WHERE id_stock=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, s.getNomProduit());
            ps.setInt(2, s.getQuantite());
            ps.setInt(3, s.getSeuilMin());
            ps.setInt(4, s.getIdStock());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Supprimer un produit
    public boolean delete(int idStock) {
        String sql = "DELETE FROM stock WHERE id_stock=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idStock);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
