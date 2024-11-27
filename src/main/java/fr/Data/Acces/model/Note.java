package fr.Data.Acces.model;

public class Note {
    private int id;
    private String matiere;
    private double valeur;

    // Constructeur sans argument
    public Note() {
    }

    // Constructeur avec arguments
    public Note(int id, String matiere, double valeur) {
        this.id = id;
        this.matiere = matiere;
        this.valeur = valeur;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    // Méthode toString pour afficher les informations
    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", matiere='" + matiere + '\'' +
                ", valeur=" + valeur +
                '}';
    }
}
