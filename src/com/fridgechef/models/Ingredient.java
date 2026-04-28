package com.fridgechef.models;

/**
 * Classe Ingredient — responsable : Baya
 * Sprint 2 — V2 : ajout validation et détection de doublons par nom
 *
 * Prompt IA utilisé :
 *   "Ajoute à la classe Ingredient Java : (1) une méthode memeNom(String) qui compare
 *    les noms sans tenir compte de la casse, (2) une méthode valider() qui retourne
 *    un message d'erreur String si l'ingrédient est invalide (nom vide, quantité <= 0,
 *    unité vide), ou null si tout est correct. Java plain, sans framework."
 *
 * Corrections après génération IA :
 *   - trim() ajouté sur nom dans memeNom() pour éviter les espaces parasites
 *   - Message d'erreur reformulé en français cohérent avec le reste du projet
 */
public class Ingredient {

    private int id;
    private String nom;
    private String categorie;
    private double quantite;
    private String unite;

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

    /**
     * Compare le nom de cet ingrédient avec un autre nom (insensible à la casse).
     * Utilisé par FrigoVirtuel pour détecter les doublons.
     */
    public boolean memeNom(String autreNom) {
        return this.nom != null && this.nom.trim().equalsIgnoreCase(autreNom.trim());
    }

    /**
     * Valide l'ingrédient avant ajout au frigo.
     * @return message d'erreur si invalide, null si valide
     */
    public String valider() {
        if (nom == null || nom.trim().isEmpty())
            return "❌ Le nom de l'ingrédient ne peut pas être vide.";
        if (quantite <= 0)
            return "❌ La quantité doit être supérieure à 0 (reçu : " + quantite + ").";
        if (unite == null || unite.trim().isEmpty())
            return "❌ L'unité ne peut pas être vide.";
        return null;
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
