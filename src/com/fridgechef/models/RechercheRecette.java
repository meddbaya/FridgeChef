package com.fridgechef.models;

import java.util.ArrayList;
import java.util.List;


public class RechercheRecette {

    private String filtreRegime;
    private int filtreDureeMax; 
    private int filtreNbPersonnes;

    public RechercheRecette() {
        this.filtreRegime = "tous";
        this.filtreDureeMax = 120;
    }

    public RechercheRecette(String filtreRegime, int filtreDureeMax) {
        this.filtreRegime = filtreRegime;
        this.filtreDureeMax = filtreDureeMax;
    }

    public List<Recette> rechercherParIngredients(FrigoVirtuel frigo, List<Recette> toutesLesRecettes) {
        List<Recette> recettesTrouvees = new ArrayList<>();
        List<Ingredient> ingredientsDispo = frigo.consulterFrigo();

        for (Recette recette : toutesLesRecettes) {
            List<Ingredient> necessaires = recette.getIngredientsNecessaires();
            if (necessaires.isEmpty()) continue;

            int nbPresents = 0;
            for (Ingredient necessaire : necessaires) {
                for (Ingredient dispo : ingredientsDispo) {
                    if (dispo.getNom().equalsIgnoreCase(necessaire.getNom())) {
                        nbPresents++;
                        break;
                    }
                }
            }

            double score = (double) nbPresents / necessaires.size() * 100;
            if (score >= 50) {
                recettesTrouvees.add(recette);
            }
        }
        return recettesTrouvees;
    }

    public List<Recette> filtrerParRegime(List<Recette> recettes, String regime) {
        if (regime == null || regime.equals("tous")) return recettes;
        List<Recette> filtrees = new ArrayList<>();
        for (Recette r : recettes) {
            if (r.getRegime().equalsIgnoreCase(regime)) {
                filtrees.add(r);
            }
        }
        return filtrees;
    }

  
    public String afficherResultats(List<Recette> recettes) {
        if (recettes.isEmpty()) {
            return "❌ Aucune recette trouvée avec vos ingrédients.";
        }
        StringBuilder sb = new StringBuilder("🔍 " + recettes.size() + " recette(s) trouvée(s) :\n");
        for (Recette r : recettes) {
            sb.append("  🍽️ ").append(r.getNom())
              .append(" (").append(r.getDureePreparation()).append(" min, ")
              .append(r.getNbPersonnes()).append(" pers.) ⭐").append(r.getNoteMoyenne()).append("\n");
        }
        return sb.toString();
    }

    public String getFiltreRegime() { return filtreRegime; }
    public void setFiltreRegime(String filtreRegime) { this.filtreRegime = filtreRegime; }

    public int getFiltreDureeMax() { return filtreDureeMax; }
    public void setFiltreDureeMax(int filtreDureeMax) { this.filtreDureeMax = filtreDureeMax; }

    public int getFiltreNbPersonnes() { return filtreNbPersonnes; }
    public void setFiltreNbPersonnes(int filtreNbPersonnes) { this.filtreNbPersonnes = filtreNbPersonnes; }
    
}
