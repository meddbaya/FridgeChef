package com.fridgechef.models;

/**
 * Classe Ingredient — responsable : Baya
 * Représente un ingrédient dans le frigo virtuel de l'utilisateur
 */
public class Ingredient {

    // ===== Champs privés =====
    private int id;
    private String nom;
    private String categorie;
    private double quantite;
    private String unite;

    // ===== Constructeurs =====
    public Ingredient() {}

    public Ingredient(int id, String nom, String categorie, double quantite, String unite) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.quantite = quantite;
        this.unite = unite;
    }

    // ===== Méthodes métier =====

    public String afficherDetails() {
        return nom + " — " + quantite + " " + unite + " [" + categorie + "]";
    }

    // ===== Getters & Setters =====
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }

    public double getQuantite() { return quantite; }
    public void setQuantite(double quantite) { this.quantite = quantite; }

    public String getUnite() { return unite; }
    public void setUnite(String unite) { this.unite = unite; }

    @Override
    public String toString() {
        return nom + " (" + quantite + " " + unite + ")";
    }
}
