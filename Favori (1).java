package com.fridgechef.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Favori — responsable : Eya
 * Gère les recettes favorites d'un utilisateur
 */
public class Favori {

    private int id;
    private Utilisateur utilisateur;
    private List<Recette> recettesFavorites;

    public Favori() {
        this.recettesFavorites = new ArrayList<>();
    }

    public Favori(int id, Utilisateur utilisateur) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.recettesFavorites = new ArrayList<>();
    }

    /**
     * Ajoute une recette aux favoris
     */
    public boolean ajouterAuxFavoris(Recette recette) {
        for (Recette r : recettesFavorites) {
            if (r.getId() == recette.getId()) {
                System.out.println("⚠️ Cette recette est déjà dans vos favoris.");
                return false;
            }
        }
        recettesFavorites.add(recette);
        System.out.println("❤️ Recette ajoutée aux favoris : " + recette.getNom());
        return true;
    }

    /**
     * Supprime une recette des favoris
     */
    public boolean supprimerDesFavoris(int idRecette) {
        for (Recette r : recettesFavorites) {
            if (r.getId() == idRecette) {
                recettesFavorites.remove(r);
                System.out.println("🗑️ Recette retirée des favoris : " + r.getNom());
                return true;
            }
        }
        System.out.println("❌ Recette introuvable dans les favoris.");
        return false;
    }

    /**
     * Affiche tous les favoris de l'utilisateur
     */
    public String afficherFavoris() {
        if (recettesFavorites.isEmpty()) {
            return "💔 Aucune recette en favoris pour le moment.";
        }
        StringBuilder sb = new StringBuilder("❤️ Mes recettes favorites (" + recettesFavorites.size() + ") :\n");
        for (Recette r : recettesFavorites) {
            sb.append("  • ").append(r.getNom()).append(" ⭐").append(r.getNoteMoyenne()).append("\n");
        }
        return sb.toString();
    }

    // ===== Getters & Setters =====
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }

    public List<Recette> getRecettesFavorites() { return recettesFavorites; }
}
