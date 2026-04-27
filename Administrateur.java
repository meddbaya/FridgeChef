// Sprint 1 - Baya - gestion administrateur
package com.fridgechef.models;

/**
 * Classe Administrateur — hérite de Utilisateur
 */
public class Administrateur extends Utilisateur {

    private String niveau;

    public Administrateur() {}

    public Administrateur(int id, String nom, String email, String motDePasse, String niveau) {
        super(id, nom, email, motDePasse);
        this.niveau = niveau;
    }

    public void gererUtilisateurs() {
        System.out.println("🔧 [Admin] Gestion des utilisateurs activée.");
    }

    public String consulterStatistiques() {
        return "[Admin] Statistiques : 5 utilisateurs inscrits | 12 recettes | 30 recherches aujourd'hui";
    }

    public String getNiveau() { return niveau; }
    public void setNiveau(String niveau) { this.niveau = niveau; }

    @Override
    public String toString() {
        return "Administrateur{nom='" + getNom() + "', niveau='" + niveau + "'}";
    }
}
