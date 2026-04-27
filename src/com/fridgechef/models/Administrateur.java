package com.fridgechef.models;

/**
 * Classe Administrateur — hérite de Utilisateur
 * Sprint 2 — V2 : statistiques dynamiques basées sur les données réelles
 *
 * Prompt IA utilisé :
 *   "Ma classe Administrateur hérite de Utilisateur. Pour le Sprint 2, améliore
 *    consulterStatistiques() pour qu'elle accepte en paramètre le nombre d'utilisateurs,
 *    de recettes et de recherches (entiers) et retourne un affichage formaté avec
 *    des emojis. Ajoute aussi gererRecettes() qui affiche un message d'action admin."
 *
 * Corrections après génération IA :
 *   - Conservé la surcharge sans paramètres pour compatibilité avec Main simplifié
 *   - Formatage aligné avec le style du reste du projet (emojis, tirets)
 */
public class Administrateur extends Utilisateur {

    private String niveau;

    public Administrateur() {}

    public Administrateur(int id, String nom, String email, String motDePasse, String niveau) {
        super(id, nom, email, motDePasse);
        this.niveau = niveau;
    }

    /**
     * Affiche des statistiques dynamiques passées en paramètre.
     */
    public String consulterStatistiques(int nbUtilisateurs, int nbRecettes, int nbRecherches) {
        return "📊 Tableau de bord Admin :\n"
             + "   👥 Utilisateurs inscrits : " + nbUtilisateurs + "\n"
             + "   🍽️  Recettes disponibles : " + nbRecettes + "\n"
             + "   🔍 Recherches aujourd'hui : " + nbRecherches;
    }

    /** Surcharge sans paramètre — données en dur (compatibilité Sprint 1) */
    public String consulterStatistiques() {
        return consulterStatistiques(5, 12, 30);
    }

    public void gererUtilisateurs() {
        System.out.println("🔧 [Admin] Accès à la gestion des utilisateurs — niveau : " + niveau);
    }

    public void gererRecettes() {
        System.out.println("📝 [Admin] Accès à la gestion des recettes — niveau : " + niveau);
    }

    public String getNiveau() { return niveau; }
    public void setNiveau(String niveau) { this.niveau = niveau; }

    @Override
    public String toString() {
        return "Administrateur{nom='" + getNom() + "', niveau='" + niveau + "'}";
    }
}
