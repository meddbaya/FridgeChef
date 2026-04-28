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
    System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║       🍳 FridgeChef — Sprint 2 (V2)          ║");
        System.out.println("║   What's in my fridge? Find your recipe!     ║");
        System.out.println("╚═══════════════════════════════════════════════╝\n");

        // ============================================================
        // SCÉNARIO 1 — Inscription avec validations (Baya)
        // ============================================================
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("📌 Scénario 1 : Inscription et connexion");
        System.out.println("══════════════════════════════════════════════════");

        Utilisateur.reinitialiserEmails();

        Utilisateur baya = new Utilisateur(1, "Baya Ben Ali", "baya@fridgechef.tn", "pass123");
        baya.sInscrire();                                          // ✅ OK

        Utilisateur doublon = new Utilisateur(2, "Autre", "baya@fridgechef.tn", "abc123");
        doublon.sInscrire();                                       // ❌ email déjà utilisé

        Utilisateur emailInvalide = new Utilisateur(3, "Test", "pasunemail", "abc123");
        emailInvalide.sInscrire();                                 // ❌ format invalide

        Utilisateur mdpCourt = new Utilisateur(4, "Slim", "slim@mail.tn", "ab");
        mdpCourt.sInscrire();                                      // ❌ mdp trop court

        System.out.println();
        baya.seConnecter("baya@fridgechef.tn", "pass123");        // ✅

        // Verrouillage après 3 échecs
        Utilisateur ines = new Utilisateur(2, "Ines Chaabane", "ines@fridgechef.tn", "secure99");
        ines.sInscrire();
        ines.seConnecter("ines@fridgechef.tn", "mauvais");        // tentative 1
        ines.seConnecter("ines@fridgechef.tn", "mauvais");        // tentative 2
        ines.seConnecter("ines@fridgechef.tn", "mauvais");        // tentative 3 → verrouillé
        ines.seConnecter("ines@fridgechef.tn", "secure99");       // 🔒 bloqué

        System.out.println();

        // ============================================================
        // SCÉNARIO 2 — Gestion du frigo virtuel (Baya)
        // ============================================================
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("📌 Scénario 2 : Gestion du frigo virtuel");
        System.out.println("══════════════════════════════════════════════════");

        FrigoVirtuel frigo = new FrigoVirtuel(1, baya);

        frigo.ajouterIngredient(new Ingredient(1, "Tomate",  "Légume",          3,   "unités"));
        frigo.ajouterIngredient(new Ingredient(2, "Pâtes",   "Féculents",       200, "g"));
        frigo.ajouterIngredient(new Ingredient(3, "Fromage", "Produit laitier", 100, "g"));
        frigo.ajouterIngredient(new Ingredient(4, "Oeuf",    "Protéine",        2,   "unités"));

        // Doublon → cumul de quantité
        frigo.ajouterIngredient(new Ingredient(5, "Tomate", "Légume", 2, "unités"));  // → 5 unités

        // Invalides → refusés
        frigo.ajouterIngredient(new Ingredient(6, "",      "Légume",        1,  "g"));   // nom vide
        frigo.ajouterIngredient(new Ingredient(7, "Huile", "Matière grasse", -1, "ml")); // qté négative

        System.out.println();
        System.out.println(frigo.afficherContenu());

        frigo.modifierQuantite(2, 300);   // Pâtes 200g → 300g ✅
        frigo.modifierQuantite(99, 50);   // id inexistant ❌

        frigo.supprimerIngredient(4);     // supprime Oeuf ✅
        frigo.supprimerIngredient(99);    // id inexistant ❌

        System.out.println();
        System.out.println(frigo.afficherContenu());

        // ============================================================
        // SCÉNARIO 3 — Recherche de recettes (Ines)
        // ============================================================
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("📌 Scénario 3 : Recherche de recettes");
        System.out.println("══════════════════════════════════════════════════");

        List<Recette> base = new ArrayList<>();

        Recette carbonara = new Recette(1, "Pâtes Carbonara",
                "Pâtes crémeuses à l'oeuf et fromage", 20, 2, "normal");
        carbonara.ajouterIngredient(new Ingredient(2, "Pâtes",   "Féculents",       200, "g"));
        carbonara.ajouterIngredient(new Ingredient(3, "Fromage", "Produit laitier", 50,  "g"));
        carbonara.ajouterIngredient(new Ingredient(4, "Oeuf",    "Protéine",        2,   "unités"));
        carbonara.ajouterIngredient(new Ingredient(5, "Lardons", "Viande",          100, "g")); // manquant

        Recette salade = new Recette(2, "Salade Tomate Mozzarella",
                "Salade fraîche et simple", 10, 2, "vegetarien");
        salade.ajouterIngredient(new Ingredient(1, "Tomate",     "Légume",          3, "unités"));
        salade.ajouterIngredient(new Ingredient(6, "Mozzarella", "Produit laitier", 1, "boule"));   // manquant
        salade.ajouterIngredient(new Ingredient(7, "Basilic",    "Herbe",           5, "feuilles")); // manquant

        Recette pastaSauce = new Recette(3, "Pasta Sauce Tomate",
                "Pâtes simples à la sauce tomate", 15, 2, "vegetarien");
        pastaSauce.ajouterIngredient(new Ingredient(2, "Pâtes",  "Féculents", 200, "g"));
        pastaSauce.ajouterIngredient(new Ingredient(1, "Tomate", "Légume",    3,   "unités"));

        base.add(carbonara);
        base.add(salade);
        base.add(pastaSauce);

        RechercheRecette recherche = new RechercheRecette("tous", 60);
        List<Recette> resultats = recherche.rechercherParIngredients(frigo, base);
        System.out.println();
        System.out.println(recherche.afficherResultats(resultats));

        List<Recette> vegetariennes = recherche.filtrerParRegime(resultats, "vegetarien");
        System.out.println("🥗 Filtre végétarien → " + vegetariennes.size() + " recette(s) :");
        for (Recette r : vegetariennes) System.out.println("   • " + r.getNom());
        System.out.println();

        // ============================================================
        // SCÉNARIO 4 — Détails + étapes de préparation (Chahinez)
        // ============================================================
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("📌 Scénario 4 : Détails et étapes de préparation");
        System.out.println("══════════════════════════════════════════════════");

        pastaSauce.ajouterEtape(new EtapePreparation(1, "Faire bouillir l'eau salée", 5));
        pastaSauce.ajouterEtape(new EtapePreparation(2, "Cuire les pâtes al dente", 8));
        pastaSauce.ajouterEtape(new EtapePreparation(3, "Écraser les tomates et faire revenir", 5));
        pastaSauce.ajouterEtape(new EtapePreparation(4, "Mélanger pâtes et sauce, servir chaud", 2));

        System.out.println(pastaSauce.afficherDetails());
        System.out.println("📋 Étapes de préparation :");
        for (EtapePreparation e : pastaSauce.getEtapes()) {
            System.out.println("  " + e.afficherEtape());
        }

        // Ingrédients manquants
        List<Ingredient> manquants = carbonara.calculerIngredientsManquants(frigo);
        System.out.println("\n🛒 Ingrédients manquants pour \"" + carbonara.getNom() + "\" :");
        if (manquants.isEmpty()) {
            System.out.println("  ✅ Vous avez tout ce qu'il faut !");
        } else {
            for (Ingredient m : manquants) System.out.println("  ❌ " + m.getNom());
        }
        System.out.println();

        // ============================================================
        // SCÉNARIO 5 — Favoris & Notation avec vraie moyenne (Eya)
        // ============================================================
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("📌 Scénario 5 : Favoris et notation");
        System.out.println("══════════════════════════════════════════════════");

        Favori favoris = new Favori(1, baya);
        favoris.ajouterAuxFavoris(pastaSauce);
        favoris.ajouterAuxFavoris(carbonara);
        favoris.ajouterAuxFavoris(pastaSauce);  // doublon → refusé

        System.out.println();
        System.out.println(favoris.afficherFavoris());

        // Notes → calcul de vraie moyenne
        Note n1 = new Note(1, 0, "", baya, pastaSauce);
        n1.noterRecette(5, "Simple, rapide et délicieux !");

        Note n2 = new Note(2, 0, "", baya, pastaSauce);
        n2.noterRecette(4, "Très bon, on en redemande !");     // moyenne pastaSauce → 4.5

        Note n3 = new Note(3, 0, "", baya, carbonara);
        n3.noterRecette(4, "Très bon mais il manque les lardons 😅");

        Note invalide = new Note(4, 0, "", baya, salade);
        invalide.noterRecette(6, "");                          // ❌ hors plage

        System.out.println("\n📝 Récapitulatif des avis de " + baya.getNom() + " :");
        System.out.println("  " + n1.afficherNote());
        System.out.println("  " + n2.afficherNote());
        System.out.println("  " + n3.afficherNote());

        // Favoris mis à jour avec les nouvelles moyennes
        System.out.println();
        System.out.println(favoris.afficherFavoris());

        // ============================================================
        // SCÉNARIO 6 — Tableau de bord Admin
        // ============================================================
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("📌 Scénario 6 : Tableau de bord Admin");
        System.out.println("══════════════════════════════════════════════════");

        Administrateur admin = new Administrateur(99, "Admin FridgeChef",
                "admin@fridgechef.tn", "admin2026", "super");
        admin.sInscrire();
        admin.seConnecter("admin@fridgechef.tn", "admin2026");
        System.out.println(admin.consulterStatistiques(2, 3, 8));
        admin.gererUtilisateurs();
        admin.gererRecettes();

        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║   ✅ Sprint 2 terminé — Prototype V2 complet  ║");
        System.out.println("╚═══════════════════════════════════════════════╝");

    }
}
