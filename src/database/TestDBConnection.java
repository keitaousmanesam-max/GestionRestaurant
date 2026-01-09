package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDBConnection {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/gestion_restaurant?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "RTD-TAMBA";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion réussie ✅");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DATABASE()");

            if (rs.next()) {
                System.out.println("Base utilisée : " + rs.getString(1));
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
