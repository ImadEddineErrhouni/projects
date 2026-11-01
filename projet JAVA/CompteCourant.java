package com.mycompany.systemebancaireoo;

public class CompteCourant extends Compte {
    private double decouvertAutorise;

    // Constructeur sans découvert explicite (découvert = 0)
    public CompteCourant(String proprietaire, double solde) {
        super(proprietaire, solde);
        this.decouvertAutorise = 0.0;
    }

    // Constructeur avec découvert
    public CompteCourant(double decouvertAutorise, String proprietaire, double solde) {
        super(proprietaire, solde);
        this.decouvertAutorise = decouvertAutorise;
    }

    @Override
    public boolean retirer(double montant) {
        if (montant <= solde + decouvertAutorise) {
            solde -= montant;
            return true;
        }
        return false;
    }

    @Override
    public double calculerInteret() {
        if (solde > 0) {
            double interet = solde * 0.01;
            System.out.println("Calcul intérêts CC (" + proprietaire + "): " +
                    solde + " × 1% = " + interet + "€");
            return interet;
        }
        System.out.println("Calcul intérêts CC (" + proprietaire + "): Pas d'intérêts (solde négatif)");
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() + ", Type: Compte Courant, Découvert: " + decouvertAutorise + "€";
    }

    public String utiliserCarte() {
        return proprietaire + " utilise sa carte bancaire";
    }

    public double getDecouvertAutorise() {
        return decouvertAutorise;
    }

    public void setDecouvertAutorise(double decouvertAutorise) {
        this.decouvertAutorise = decouvertAutorise;
    }
}
