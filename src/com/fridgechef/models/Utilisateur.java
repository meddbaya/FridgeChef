package com.fridgechef.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Utilisateur — classe commune à tous les membres
 * Sprint 2 — V2 : vraie logique métier
 *
 * Prompt IA utilisé :
 *   "Ma classe Utilisateur Java a seConnecter() et sInscrire() basiques.
 *    Pour le Sprint 2 : (1) sInscrire() doit valider que nom/email/motDePasse ne sont
 *    pas vides, que l'email contient '@' et un '.' après le '@', et que l'email n'est
 *    pas déjà dans une liste statique d'emails inscrits ; (2) seConnecter() doit
 *    verrouiller le compte après 3 tentatives échouées consécutives.
 *    Java plain, sans framework."
 *
 * Corrections après génération IA :
 *   - Reset du compteur tentativesEchouees après connexion réussie (oubli de l'IA)
 *   - emailsInscrits déclaré static pour être partagé entre toutes les instances
 *     (simuler une vraie base de données)
 *   - Ajout de reinitialiserEmails() pour permettre les tests propres dans Main
 */
public class Utilisateur {

    private int id;
    private String nom;
    private String email;
    private String motDePasse;
    private int tentativesEchouees;
    private boolean verrouille;

    /** Simule la base d'emails déjà inscrits (partagée entre instances) */
    private static List<String> emailsInscrits = new ArrayList<>();

    public Utilisateur() {
        this.tentativesEchouees = 0;
        this.verrouille = false;
    }

    public Utilisateur(int id, String nom, String email, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.tentativesEchouees = 0;
        this.verrouille = false;
    }

    // ===== Méthodes métier =====

    /**
     * Inscrit l'utilisateur avec validation complète.
     * Vérifie : champs non vides, mot de passe >= 4 chars, format email, unicité email.
     */
    public boolean sInscrire() {
        if (nom == null || nom.trim().isEmpty()) {
            System.out.println("❌ Inscription échouée : le nom est obligatoire.");
            return false;
        }
        if (email == null || email.trim().isEmpty()) {
            System.out.println("❌ Inscription échouée : l'email est obligatoire.");
            return false;
        }
        if (motDePasse == null || motDePasse.length() < 4) {
            System.out.println("❌ Inscription échouée : mot de passe trop court (minimum 4 caractères).");
            return false;
        }
        // Validation format email
        int arrobase = email.indexOf('@');
        if (arrobase < 1 || email.indexOf('.', arrobase) == -1) {
            System.out.println("❌ Inscription échouée : format d'email invalide (" + email + ").");
            return false;
        }
        // Unicité email
        if (emailsInscrits.contains(email.toLowerCase())) {
            System.out.println("❌ Inscription échouée : l'email " + email + " est déjà utilisé.");
            return false;
        }
        emailsInscrits.add(email.toLowerCase());
        System.out.println("✅ Compte créé pour : " + nom + " (" + email + ")");
        return true;
    }

    /**
     * Connecte l'utilisateur.
     * Verrouille le compte après 3 tentatives échouées consécutives.
     */
    public boolean seConnecter(String email, String motDePasse) {
        if (verrouille) {
            System.out.println("🔒 Compte verrouillé — trop de tentatives échouées. Contactez le support.");
            return false;
        }
        if (this.email.equals(email) && this.motDePasse.equals(motDePasse)) {
            tentativesEchouees = 0; // reset compteur
            System.out.println("✅ Connexion réussie ! Bienvenue " + this.nom + " 👋");
            return true;
        }
        tentativesEchouees++;
        if (tentativesEchouees >= 3) {
            verrouille = true;
            System.out.println("❌ Mot de passe incorrect. Compte verrouillé après 3 tentatives.");
        } else {
            System.out.println("❌ Email ou mot de passe incorrect. "
                    + (3 - tentativesEchouees) + " tentative(s) restante(s).");
        }
        return false;
    }

    public String afficherProfil() {
        return "👤 Profil : " + nom + " | Email : " + email
                + (verrouille ? " | 🔒 Verrouillé" : " | ✅ Actif");
    }

    /** Réinitialise la base d'emails — à appeler en début de test */
    public static void reinitialiserEmails() {
        emailsInscrits.clear();
    }

    // ===== Getters & Setters =====
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String mp) { this.motDePasse = mp; }

    public boolean isVerrouille() { return verrouille; }

    @Override
    public String toString() {
        return "Utilisateur{id=" + id + ", nom='" + nom + "', email='" + email + "'}";
    }
}
