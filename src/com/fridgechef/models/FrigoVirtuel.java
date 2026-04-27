package com.fridgechef.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe FrigoVirtuel — responsable : Baya
 * Sprint 2 — V2 : vraie logique métier
 *
 * Prompt IA utilisé :
 *   "Ma classe FrigoVirtuel a ajouterIngredient() et supprimerIngredient() basiques.
 *    Pour le Sprint 2, améliore : (1) ajouterIngredient() doit valider l'ingrédient
 *    via ingredient.valider() et refuser si invalide, (2) si un ingrédient du même nom
 *    existe déjà (via memeNom()), cumuler la quantité au lieu de créer un doublon,
 *    (3) ajouter modifierQuantite(id, double) avec validation quantité > 0,
 *    (4) ajouter rechercherParNom(String) qui retourne l'Ingredient ou null.
 *    Java plain, sans framework, retourner boolean sur ajouterIngredient."
 *
 * Corrections après génération IA :
 *   - ajouterIngredient() retourne maintenant boolean (l'IA avait gardé void)
 *   - Message de cumul enrichi avec ancienne et nouvelle quantité pour la lisibilité
 *   - afficherContenu() numéroté (1. 2. 3.) au lieu de bullets, plus lisible en démo
 */
public class FrigoVirtuel {

    private int id;
    private List<Ingredient> listeIngredients;
    private Utilisateur utilisateur;

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
     * Ajoute un ingrédient au frigo.
     * - Valide l'ingrédient (nom, quantité, unité)
     * - Si le même ingrédient existe déjà, cumule la quantité (pas de doublon)
     */
    public boolean ajouterIngredient(Ingredient ingredient) {
        // 1. Validation
        String erreur = ingredient.valider();
        if (erreur != null) {
            System.out.println(erreur);
            return false;
        }
        // 2. Détection doublon → cumul quantité
        Ingredient existant = rechercherParNom(ingredient.getNom());
        if (existant != null) {
            double avant = existant.getQuantite();
            existant.setQuantite(avant + ingredient.getQuantite());
            System.out.println("🔄 Doublon détecté — quantité mise à jour : "
                    + existant.getNom() + " | " + avant + " + " + ingredient.getQuantite()
                    + " = " + existant.getQuantite() + " " + existant.getUnite());
            return true;
        }
        // 3. Ajout normal
        listeIngredients.add(ingredient);
        System.out.println("✅ Ingrédient ajouté : " + ingredient.afficherDetails());
        return true;
    }

    /**
     * Supprime un ingrédient par son id.
     */
    public boolean supprimerIngredient(int id) {
        for (Ingredient ing : listeIngredients) {
            if (ing.getId() == id) {
                listeIngredients.remove(ing);
                System.out.println("🗑️  Ingrédient supprimé : " + ing.getNom());
                return true;
            }
        }
        System.out.println("❌ Ingrédient introuvable (id=" + id + ")");
        return false;
    }

    /**
     * Modifie la quantité d'un ingrédient existant.
     * Refuse les valeurs <= 0.
     */
    public boolean modifierQuantite(int id, double nouvelleQuantite) {
        if (nouvelleQuantite <= 0) {
            System.out.println("❌ La nouvelle quantité doit être > 0.");
            return false;
        }
        for (Ingredient ing : listeIngredients) {
            if (ing.getId() == id) {
                double avant = ing.getQuantite();
                ing.setQuantite(nouvelleQuantite);
                System.out.println("✏️  Quantité modifiée : " + ing.getNom()
                        + " | " + avant + " → " + nouvelleQuantite + " " + ing.getUnite());
                return true;
            }
        }
        System.out.println("❌ Ingrédient introuvable (id=" + id + ")");
        return false;
    }

    /**
     * Recherche un ingrédient par nom (insensible à la casse).
     * Retourne l'ingrédient ou null s'il n'existe pas.
     */
    public Ingredient rechercherParNom(String nom) {
        for (Ingredient ing : listeIngredients) {
            if (ing.memeNom(nom)) return ing;
        }
        return null;
    }

    /**
     * Retourne la liste des ingrédients.
     */
    public List<Ingredient> consulterFrigo() {
        return listeIngredients;
    }

    /**
     * Affiche le contenu du frigo (numéroté, lisible en console).
     */
    public String afficherContenu() {
        if (listeIngredients.isEmpty()) return "🧊 Votre frigo est vide !";
        StringBuilder sb = new StringBuilder(
                "🧊 Contenu du frigo (" + listeIngredients.size() + " ingrédient(s)) :\n");
        for (int i = 0; i < listeIngredients.size(); i++) {
            sb.append("  ").append(i + 1).append(". ")
              .append(listeIngredients.get(i).afficherDetails()).append("\n");
        }
        return sb.toString();
    }

    public void vider() {
        int nb = listeIngredients.size();
        listeIngredients.clear();
        System.out.println("🧹 Frigo vidé (" + nb + " ingrédient(s) supprimé(s)).");
    }

    // ===== Getters & Setters =====
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public List<Ingredient> getListeIngredients() { return listeIngredients; }
    public void setListeIngredients(List<Ingredient> l) { this.listeIngredients = l; }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur u) { this.utilisateur = u; }
}
