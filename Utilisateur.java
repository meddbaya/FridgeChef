package com.fridgechef.models;

/**
 * Classe Utilisateur — classe commune à tous les membres
 * Représente un utilisateur inscrit sur FridgeChef
 */
public class Utilisateur {

    // ===== Champs privés =====
    private int id;
    private String nom;
    private String email;
    private String motDePasse;

    // ===== Constructeurs =====
    public Utilisateur() {}

    public Utilisateur(int id, String nom, String email, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    // ===== Méthodes métier =====

    /**
     * Vérifie les identifiants de connexion
     */
    public boolean seConnecter(String email, String motDePasse) {
        if (this.email.equals(email) && this.motDePasse.equals(motDePasse)) {
            System.out.println("✅ Connexion réussie ! Bienvenue " + this.nom);
            return true;
        }
        System.out.println("❌ Email ou mot de passe incorrect.");
        return false;
    }

    /**
     * Inscription d'un nouvel utilisateur
     */
    public boolean sInscrire() {
        if (this.nom != null && this.email != null && this.motDePasse != null) {
            System.out.println("✅ Compte créé pour : " + this.nom + " (" + this.email + ")");
            return true;
        }
        System.out.println("❌ Informations manquantes.");
        return false;
    }

    public String afficherProfil() {
        return "Profil : " + nom + " | Email : " + email;
    }

    // ===== Getters & Setters =====
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    @Override
    public String toString() {
        return "Utilisateur{id=" + id + ", nom='" + nom + "', email='" + email + "'}";
    }
}
