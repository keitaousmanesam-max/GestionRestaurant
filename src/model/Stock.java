package model;

public class Stock {

    private int idStock;
    private String nomProduit;
    private int quantite;
    private int seuilMin;

    public Stock() {}

    public Stock(int idStock, String nomProduit, int quantite, int seuilMin) {
        this.idStock = idStock;
        this.nomProduit = nomProduit;
        this.quantite = quantite;
        this.seuilMin = seuilMin;
    }

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getSeuilMin() {
        return seuilMin;
    }

    public void setSeuilMin(int seuilMin) {
        this.seuilMin = seuilMin;
    }

    @Override
    public String toString() {
        return nomProduit + " (" + quantite + ")";
    }
}
