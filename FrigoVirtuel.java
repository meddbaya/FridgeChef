// Sprint 1 - Baya - gestion frigo virtuel
package com.fridgechef.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe FrigoVirtuel — responsable : Baya
 * Représente le frigo virtuel d'un utilisateur (liste de ses ingrédients)
 */
public class FrigoVirtuel {

    // ===== Champs privés =====
    private int id;
    private List<Ingredient> listeIngredients;
    private Utilisateur utilisateur;

    // ===== Constructeurs =====
    public FrigoVirtuel() {
        this.listeIngredients = new ArrayList<>();
    }

    public FrigoVirtuel(int id, Utilisateur utilisateur) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.listeIngredients = new ArrayList<>();
    }

    // ===== Méthodes métier =====

    /**
     * Ajoute un ingrédient dans le frigo
     */
    public void ajouterIngredient(Ingredient ingredient) {
        listeIngredients.add(ingredient);
        System.out.println("✅ Ingrédient ajouté : " + ingredient.getNom());
    }

    /**
     * Supprime un ingrédient par son id
     */
    public boolean supprimerIngredient(int id) {
        for (Ingredient ing : listeIngredients) {
            if (ing.getId() == id) {
                listeIngredients.remove(ing);
                System.out.println("🗑️ Ingrédient supprimé : " + ing.getNom());
                return true;
            }
        }
        System.out.println("❌ Ingrédient introuvable (id=" + id + ")");
        return false;
    }

    /**
     * Retourne la liste des ingrédients
     */
    public List<Ingredient> consulterFrigo() {
        return listeIngredients;
    }

    /**
     * Affiche le contenu du frigo en console
     */
    public String afficherContenu() {
        if (listeIngredients.isEmpty()) {
            return "🧊 Votre frigo est vide !";
        }
        StringBuilder sb = new StringBuilder("🧊 Contenu du frigo :\n");
        for (Ingredient ing : listeIngredients) {
            sb.append("  • ").append(ing.afficherDetails()).append("\n");
        }
        return sb.toString();
    }

    public void vider() {
        listeIngredients.clear();
        System.out.println("🧹 Frigo vidé.");
    }

    // ===== Getters & Setters =====
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public List<Ingredient> getListeIngredients() { return listeIngredients; }
    public void setListeIngredients(List<Ingredient> listeIngredients) { this.listeIngredients = listeIngredients; }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
}
