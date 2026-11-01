package com.mycompany.systemebancaireoo;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class SystemebancaireOO {

    public static void main(String[] args) {

        // Force UTF-8 output (fix ????? issue)
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        afficherTitre();

        // Création des comptes
        CompteCourant cc1 = new CompteCourant(500, "Karim", 3000);
        CompteEpargne ce1 = new CompteEpargne(2.5, "Sara", 10000);
        CompteCourant cc2 = new CompteCourant(1000, "Ali", 5000);

        // 1. COMPTES CRÉÉS
        section("1. COMPTES CRÉÉS");
        afficherCompteCourant(cc1);
        afficherCompteEpargne(ce1);
        afficherCompteCourant(cc2);

        // 2. OPÉRATIONS BANCAIRES
        section("2. OPÉRATIONS BANCAIRES");

        System.out.println("Dépôt de 1000€ sur le compte de " + cc1.getProprietaire());
        cc1.deposer(1000);
        System.out.println("→ Nouveau solde : " + cc1.getSolde() + "€");

        System.out.println("\nRetrait de 4500€ sur le compte de " + cc1.getProprietaire());
        if (cc1.retirer(4500)) {
            System.out.println("→ Retrait réussi");
            System.out.println("→ Solde final : " + cc1.getSolde() + "€");
            System.out.println("→ Découvert utilisé : " + (cc1.getSolde() < 0 ? "OUI" : "NON"));
        } else {
            System.out.println("→ Retrait échoué : solde insuffisant");
        }

        // 3. CALCUL DES INTÉRÊTS
        section("3. CALCUL DES INTÉRÊTS");

        afficherInterets(cc1);
        afficherInterets(ce1);
        afficherInterets(cc2);

        // 4. POLYMORPHISME
        section("4. POLYMORPHISME");

        Compte[] comptes = { cc1, ce1, cc2 };
        for (Compte c : comptes) {
            System.out.println(c);
            System.out.println("→ Intérêts calculés : " + c.calculerInteret() + "€\n");
        }

        // 5. CONCLUSION
        section("5. CONCLUSION");
        System.out.println("✓ Compte Courant :");
        System.out.println("  - Découvert autorisé");
        System.out.println("  - Intérêts seulement si solde positif");

        System.out.println("\n✓ Compte Épargne :");
        System.out.println("  - Pas de découvert");
        System.out.println("  - Intérêts toujours calculés");

        System.out.println("\n════════════════════════════════════════");
        System.out.println("        PROJET RÉUSSI 🎉");
        System.out.println("════════════════════════════════════════");
    }

    // ================== MÉTHODES UTILES ==================

    private static void afficherTitre() {
        System.out.println("════════════════════════════════════════");
        System.out.println("  SYSTÈME BANCAIRE – CALCUL DES INTÉRÊTS");
        System.out.println("════════════════════════════════════════");
    }

    private static void section(String titre) {
        System.out.println("\n" + titre);
        System.out.println("────────────────────────────────────────");
    }

    private static void afficherCompteCourant(CompteCourant cc) {
        System.out.println("• " + cc.getProprietaire() + " : Compte Courant");
        System.out.println("  Solde : " + cc.getSolde() + "€ | Découvert autorisé : "
                + cc.getDecouvertAutorise() + "€\n");
    }

    private static void afficherCompteEpargne(CompteEpargne ce) {
        System.out.println("• " + ce.getProprietaire() + " : Compte Épargne");
        System.out.println("  Solde : " + ce.getSolde() + "€ | Taux : "
                + ce.getTauxInteret() + "%\n");
    }

    private static void afficherInterets(Compte c) {
        System.out.println("Compte de " + c.getProprietaire());
        System.out.println("Solde : " + c.getSolde() + "€");
        System.out.println("Intérêts : " + c.calculerInteret() + "€\n");
    }
}