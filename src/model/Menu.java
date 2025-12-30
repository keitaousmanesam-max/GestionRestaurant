package model;

public class Menu {

    private int idMenu;
    private String nomMenu;
    private String categorie;

    // Constructeur vide
    public Menu() {}

    // Constructeur complet
    public Menu(int idMenu, String nomMenu, String categorie) {
        this.idMenu = idMenu;
        this.nomMenu = nomMenu;
        this.categorie = categorie;
    }

    // Getter & Setter
    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getNomMenu() {
        return nomMenu;
    }

    public void setNomMenu(String nomMenu) {
        this.nomMenu = nomMenu;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return nomMenu + " (" + categorie + ")";
    }
}
