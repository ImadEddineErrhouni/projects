package com.mycompany.systemebancaireoo;

public class Compte {
    protected String proprietaire;
    protected double solde;

    public Compte(String proprietaire, double solde) {
        this.proprietaire = proprietaire;
        this.solde = solde;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public double getSolde() {
        return solde;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void deposer(double montant) {
        solde += montant;
    }

    public boolean retirer(double montant) {
        if (montant <= solde) {
            solde -= montant;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Propriétaire: " + proprietaire + ", Solde: " + solde + "€";
    }

    public double calculerInteret() {
        return 0;
    }
}
