package model;

public class TableRestaurant {
    private int idTable;
    private String numero;   // numéro ou nom de la table
    private String statut;   // LIBRE / OCCUPEE

    public TableRestaurant() {}

    public TableRestaurant(int idTable, String numero, String statut) {
        this.idTable = idTable;
        this.numero = numero;
        this.statut = statut;
    }

    public int getIdTable() { return idTable; }
    public void setIdTable(int idTable) { this.idTable = idTable; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    @Override
    public String toString() {
        return "Table " + numero + " (" + statut + ")";
    }
}
