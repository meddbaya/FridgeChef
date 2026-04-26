package com.fridgechef.models;

import java.util.ArrayList;
import java.util.List;

public class Recette {

    // ===== Champs privés =====
    private int id;
    private String nom;
    private String description;
    private int dureePreparation;
    private int nbPersonnes;
    private String regime;
    private double noteMoyenne;
    private List<Ingredient> ingredientsNecessaires;
    private List<EtapePreparation> etapes;
    private boolean active;  // Ajout pour Sprint 2

    // ===== Constructeurs =====
    public Recette() {
        this.ingredientsNecessaires = new ArrayList<>();
        this.etapes = new ArrayList<>();
        this.active = true;
    }

    public Recette(int id, String nom, String description, int dureePreparation, int nbPersonnes, String regime) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dureePreparation = dureePreparation;
        this.nbPersonnes = nbPersonnes;
        this.regime = regime;
        this.noteMoyenne = 0.0;
        this.ingredientsNecessaires = new ArrayList<>();
        this.etapes = new ArrayList<>();
        this.active = true;
    }

    // ===== Méthodes métier =====
    public String afficherDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("📖 ").append(nom).append("\n");
        sb.append("   Description : ").append(description).append("\n");
        sb.append("   Durée : ").append(dureePreparation).append(" min | Personnes : ").append(nbPersonnes).append("\n");
        sb.append("   Régime : ").append(regime).append(" | Note : ").append(noteMoyenne).append("/5\n");
        sb.append("   Ingrédients nécessaires :\n");
        for (Ingredient ing : ingredientsNecessaires) {
            sb.append("     - ").append(ing.afficherDetails()).append("\n");
        }
        return sb.toString();
    }

    public List<Ingredient> calculerIngredientsManquants(FrigoVirtuel frigo) {
        List<Ingredient> manquants = new ArrayList<>();
        List<Ingredient> frigoIngredients = frigo.consulterFrigo();

        for (Ingredient necessaire : ingredientsNecessaires) {
            boolean trouve = false;
            for (Ingredient dansLeFrigo : frigoIngredients) {
                if (dansLeFrigo.getNom().equalsIgnoreCase(necessaire.getNom())) {
                    trouve = true;
                    break;
                }
            }
            if (!trouve) {
                manquants.add(necessaire);
            }
        }
        return manquants;
    }

    public void ajouterIngredient(Ingredient ingredient) {
        ingredientsNecessaires.add(ingredient);
    }

    public void ajouterEtape(EtapePreparation etape) {
        etapes.add(etape);
    }

    // ===== Nouvelle méthode Sprint 2 =====
    public int getTempsPreparation() {
        int totalDuree = this.dureePreparation;
        for (EtapePreparation etape : this.etapes) {
            totalDuree += etape.getDuree();
        }
        return totalDuree;
    }

    public String suggererRemplacementIngredient(Ingredient ingredientManquant) {
        switch (ingredientManquant.getNom().toLowerCase()) {
            case "lardons":
                return "Vous pouvez remplacer les lardons par des champignons ou des allumettes de jambon";
            case "mozzarella":
                return "Vous pouvez remplacer la mozzarella par de la feta ou du fromage à pâte dure";
            default:
                return "Pas de suggestion pour '" + ingredientManquant.getNom() + "'";
        }
    }

    // ===== Getters & Setters =====
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getDureePreparation() { return dureePreparation; }
    public void setDureePreparation(int dureePreparation) { this.dureePreparation = dureePreparation; }

    public int getNbPersonnes() { return nbPersonnes; }
    public void setNbPersonnes(int nbPersonnes) { this.nbPersonnes = nbPersonnes; }

    public String getRegime() { return regime; }
    public void setRegime(String regime) { this.regime = regime; }

    public double getNoteMoyenne() { return noteMoyenne; }
    public void setNoteMoyenne(double noteMoyenne) { this.noteMoyenne = noteMoyenne; }

    public List<Ingredient> getIngredientsNecessaires() { return ingredientsNecessaires; }
    public List<EtapePreparation> getEtapes() { return etapes; }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String toString() {
        return "Recette{id=" + id + ", nom='" + nom + "', note=" + noteMoyenne + "}";
    }
}
