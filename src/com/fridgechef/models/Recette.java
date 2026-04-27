package com.fridgechef.models;

import java.util.ArrayList;
import java.util.List;

public class Recette {

    private int id;
    private String nom;
    private String description;
    private int dureePreparation;
    private int nbPersonnes;
    private String regime;
    private double noteMoyenne;
    private List<Integer> toutesLesNotes; // pour calculer la vraie moyenne
    private List<Ingredient> ingredientsNecessaires;
    private List<EtapePreparation> etapes;

    public Recette() {
        this.ingredientsNecessaires = new ArrayList<>();
        this.etapes = new ArrayList<>();
        this.toutesLesNotes = new ArrayList<>();
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
        this.toutesLesNotes = new ArrayList<>();
    }

    // ===== Méthodes métier =====

    public void ajouterNote(int valeur) {
        if (valeur < 1 || valeur > 5) {
            System.out.println("❌ Note ignorée : doit être entre 1 et 5 (reçu : " + valeur + ").");
            return;
        }
        toutesLesNotes.add(valeur);
        double somme = 0;
        for (int n : toutesLesNotes) somme += n;
        noteMoyenne = somme / toutesLesNotes.size();
    }

    public String afficherDetails() {
        String moyenneStr = toutesLesNotes.isEmpty() ? "Pas encore notée"
                : String.format("%.1f/5 (%d avis)", noteMoyenne, toutesLesNotes.size());
        StringBuilder sb = new StringBuilder();
        sb.append("📖 ").append(nom).append("\n");
        sb.append("   Description : ").append(description).append("\n");
        sb.append("   Durée : ").append(dureePreparation).append(" min | Personnes : ").append(nbPersonnes).append("\n");
        sb.append("   Régime : ").append(regime).append(" | Note : ").append(moyenneStr).append("\n");
        sb.append("   Ingrédients nécessaires :\n");
        for (Ingredient ing : ingredientsNecessaires) {
            sb.append("     - ").append(ing.afficherDetails()).append("\n");
        }
        return sb.toString();
    }

  
    public List<Ingredient> calculerIngredientsManquants(FrigoVirtuel frigo) {
        List<Ingredient> manquants = new ArrayList<>();
        for (Ingredient necessaire : ingredientsNecessaires) {
            boolean trouve = false;
            for (Ingredient dispo : frigo.consulterFrigo()) {
                if (dispo.getNom().equalsIgnoreCase(necessaire.getNom())) {
                    trouve = true;
                    break;
                }
            }
            if (!trouve) manquants.add(necessaire);
        }
        return manquants;
    }

    public void ajouterIngredient(Ingredient ingredient) { ingredientsNecessaires.add(ingredient); }
    public void ajouterEtape(EtapePreparation etape) { etapes.add(etape); }

    // ===== Getters & Setters =====
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String d) { this.description = d; }
    public int getDureePreparation() { return dureePreparation; }
    public void setDureePreparation(int d) { this.dureePreparation = d; }
    public int getNbPersonnes() { return nbPersonnes; }
    public void setNbPersonnes(int n) { this.nbPersonnes = n; }
    public String getRegime() { return regime; }
    public void setRegime(String r) { this.regime = r; }
    public double getNoteMoyenne() { return noteMoyenne; }
    public void setNoteMoyenne(double n) { this.noteMoyenne = n; }
    public List<Ingredient> getIngredientsNecessaires() { return ingredientsNecessaires; }
    public List<EtapePreparation> getEtapes() { return etapes; }

    @Override
    public String toString() {
        return "Recette{id=" + id + ", nom='" + nom + "', note=" + String.format("%.1f", noteMoyenne) + "}";
    }
}
