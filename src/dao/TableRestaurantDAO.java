package dao;

import database.DBConnection;
import model.TableRestaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableRestaurantDAO {

    public List<TableRestaurant> findAll() {
        List<TableRestaurant> list = new ArrayList<>();
        String sql = "SELECT * FROM table_restaurant";

        try (Statement st = DBConnection.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new TableRestaurant(
                        rs.getInt("id_table"),
                        rs.getString("numero"),
                        rs.getString("etat")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
