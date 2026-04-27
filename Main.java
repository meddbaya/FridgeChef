package com.fridgechef.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Main.java V1 — FridgeChef
 * Démonstration du prototype Sprint 1
 * Compile avec : javac -encoding UTF-8 -d out src/com/fridgechef/models/*.java
 * Exécute avec  : java -cp out com.fridgechef.models.Main
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║       🍳 FridgeChef — Sprint 1           ║");
        System.out.println("║  What's in my fridge? Find your recipe!  ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        // ============================================================
        // --- Authentification (Baya) ---
        // ============================================================
        System.out.println("═══════════════════════════════════════════");
        System.out.println("👩‍💻 Fonctionnalité 1 : Gestion Ingrédients (Baya)");
        System.out.println("═══════════════════════════════════════════");

        Utilisateur baya = new Utilisateur(1, "Baya Ben Ali", "baya@fridgechef.tn", "pass123");
        baya.sInscrire();
        baya.seConnecter("baya@fridgechef.tn", "pass123");

        // Frigo de Baya
        FrigoVirtuel frigoB = new FrigoVirtuel(1, baya);
        Ingredient tomate  = new Ingredient(1, "Tomate",  "Légume",    3,   "unités");
        Ingredient pates   = new Ingredient(2, "Pâtes",   "Féculents", 200, "g");
        Ingredient fromage = new Ingredient(3, "Fromage", "Produit laitier", 100, "g");
        Ingredient oeuf    = new Ingredient(4, "Oeuf",    "Protéine",  2,   "unités");

        frigoB.ajouterIngredient(tomate);
        frigoB.ajouterIngredient(pates);
        frigoB.ajouterIngredient(fromage);
        frigoB.ajouterIngredient(oeuf);

        System.out.println(frigoB.afficherContenu());

        // Test suppression
        frigoB.supprimerIngredient(99); // id inexistant
        System.out.println();

        // ============================================================
        // --- Recherche Recettes (Ines) ---
        // ============================================================
        System.out.println("═══════════════════════════════════════════");
        System.out.println("🔍 Fonctionnalité 2 : Recherche de Recettes (Ines)");
        System.out.println("═══════════════════════════════════════════");

        // Création de recettes dans la base
        List<Recette> base = new ArrayList<>();

        Recette carbonara = new Recette(1, "Pâtes Carbonara", "Pâtes crémeuses à l'oeuf et fromage", 20, 2, "normal");
        carbonara.ajouterIngredient(new Ingredient(2, "Pâtes",   "Féculents", 200, "g"));
        carbonara.ajouterIngredient(new Ingredient(3, "Fromage", "Produit laitier", 50, "g"));
        carbonara.ajouterIngredient(new Ingredient(4, "Oeuf",    "Protéine",  2,   "unités"));
        carbonara.ajouterIngredient(new Ingredient(5, "Lardons", "Viande",    100, "g")); // manquant

        Recette salade = new Recette(2, "Salade Tomate Mozzarella", "Salade fraîche et simple", 10, 2, "vegetarien");
        salade.ajouterIngredient(new Ingredient(1, "Tomate",      "Légume",         3,  "unités"));
        salade.ajouterIngredient(new Ingredient(6, "Mozzarella",  "Produit laitier", 1, "boule"));
        salade.ajouterIngredient(new Ingredient(7, "Basilic",     "Herbe",           5, "feuilles"));

        Recette omelette = new Recette(3, "Omelette au Fromage", "Omelette rapide et nourrissante", 15, 1, "vegetarien");
        omelette.ajouterIngredient(new Ingredient(4, "Oeuf",    "Protéine",        2,  "unités"));
        omelette.ajouterIngredient(new Ingredient(3, "Fromage", "Produit laitier", 50, "g"));

        base.add(carbonara);
        base.add(salade);
        base.add(omelette);

        RechercheRecette recherche = new RechercheRecette("tous", 60);
        List<Recette> resultats = recherche.rechercherParIngredients(frigoB, base);
        System.out.println(recherche.afficherResultats(resultats));

        // Filtre végétarien
        List<Recette> vegetariennes = recherche.filtrerParRegime(resultats, "vegetarien");
        System.out.println("🥗 Filtre végétarien : " + vegetariennes.size() + " recette(s)");
        System.out.println();

        // ============================================================
        // --- Détails Recette (Chahinez) ---
        // ============================================================
        System.out.println("═══════════════════════════════════════════");
        System.out.println("📖 Fonctionnalité 3 : Détails Recette (Chahinez)");
        System.out.println("═══════════════════════════════════════════");

        // Ajout des étapes de préparation
        omelette.ajouterEtape(new EtapePreparation(1, "Battre les oeufs avec du sel", 2));
        omelette.ajouterEtape(new EtapePreparation(2, "Faire chauffer la poêle avec un peu de beurre", 3));
        omelette.ajouterEtape(new EtapePreparation(3, "Verser les oeufs, ajouter le fromage râpé", 5));
        omelette.ajouterEtape(new EtapePreparation(4, "Plier l'omelette et servir", 2));

        System.out.println(omelette.afficherDetails());
        System.out.println("📋 Étapes de préparation :");
        for (EtapePreparation e : omelette.getEtapes()) {
            System.out.println("  " + e.afficherEtape());
        }

        List<Ingredient> manquants = carbonara.calculerIngredientsManquants(frigoB);
        System.out.println("\n🛒 Ingrédients manquants pour Carbonara :");
        for (Ingredient m : manquants) {
            System.out.println("  ❌ " + m.getNom());
        }
        System.out.println();

        // ============================================================
        // --- Favoris & Notation (Eya) ---
        // ============================================================
        System.out.println("═══════════════════════════════════════════");
        System.out.println("❤️  Fonctionnalité 4 : Favoris & Notation (Eya)");
        System.out.println("═══════════════════════════════════════════");

        Favori favorisB = new Favori(1, baya);
        favorisB.ajouterAuxFavoris(omelette);
        favorisB.ajouterAuxFavoris(carbonara);
        favorisB.ajouterAuxFavoris(omelette); // test doublon

        System.out.println(favorisB.afficherFavoris());

        Note note1 = new Note(1, 0, "", baya, omelette);
        note1.noterRecette(5, "Super rapide et délicieux !");

        Note note2 = new Note(2, 0, "", baya, carbonara);
        note2.noterRecette(4, "Très bonne recette, manque juste les lardons 😅");

        System.out.println("\n📝 Avis laissés :");
        System.out.println("  " + note1.afficherNote());
        System.out.println("  " + note2.afficherNote());

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║  ✅ Sprint 1 terminé — Prototype fonctionnel ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }
}
