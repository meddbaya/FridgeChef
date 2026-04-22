package com.fridgechef.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Note — responsable : Eya
 * Gère les notes et commentaires sur les recettes
 */
public class Note {

    private int id;
    private int valeur; // entre 1 et 5
    private String commentaire;
    private Utilisateur utilisateur;
    private Recette recette;

    public Note() {}

    public Note(int id, int valeur, String commentaire, Utilisateur utilisateur, Recette recette) {
        this.id = id;
        this.valeur = valeur;
        this.commentaire = commentaire;
        this.utilisateur = utilisateur;
        this.recette = recette;
    }

    /**
     * Valide et enregistre une note sur une recette (1-5 étoiles)
     */
    public boolean noterRecette(int valeur, String commentaire) {
        if (valeur < 1 || valeur > 5) {
            System.out.println("❌ La note doit être entre 1 et 5.");
            return false;
        }
        this.valeur = valeur;
        this.commentaire = commentaire;
        System.out.println("⭐ Note ajoutée : " + valeur + "/5 pour \"" + recette.getNom() + "\"");
        System.out.println("   Commentaire : " + commentaire);

        // Mise à jour de la note moyenne de la recette (logique simplifiée Sprint 1)
        recette.setNoteMoyenne(valeur);
        return true;
    }

    public String afficherNote() {
        return "⭐ " + valeur + "/5 par " + utilisateur.getNom() + " — \"" + commentaire + "\"";
    }

    // ===== Getters & Setters =====
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getValeur() { return valeur; }
    public void setValeur(int valeur) { this.valeur = valeur; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }

    public Recette getRecette() { return recette; }
    public void setRecette(Recette recette) { this.recette = recette; }

    @Override
    public String toString() {
        return afficherNote();
    }
}
