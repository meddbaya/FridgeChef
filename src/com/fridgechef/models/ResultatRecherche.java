package com.fridgechef.models;

import java.util.List;


public class ResultatRecherche {

    private List<Recette> recettesTrouvees;
    private double scoreCompatibilite;

    public ResultatRecherche() {}

    public ResultatRecherche(List<Recette> recettesTrouvees, double scoreCompatibilite) {
        this.recettesTrouvees = recettesTrouvees;
        this.scoreCompatibilite = scoreCompatibilite;
    }

    public String afficherResultats() {
        StringBuilder sb = new StringBuilder();
        sb.append("📊 Score de compatibilité : ").append(scoreCompatibilite).append("%\n");
        sb.append("📋 Recettes disponibles : ").append(recettesTrouvees.size()).append("\n");
        for (Recette r : recettesTrouvees) {
            sb.append("  → ").append(r.getNom()).append("\n");
        }
        return sb.toString();
    }

    public List<Recette> getRecettesTrouvees() { return recettesTrouvees; }
    public void setRecettesTrouvees(List<Recette> recettesTrouvees) { this.recettesTrouvees = recettesTrouvees; }

    public double getScoreCompatibilite() { return scoreCompatibilite; }
    public void setScoreCompatibilite(double scoreCompatibilite) { this.scoreCompatibilite = scoreCompatibilite; }
}
