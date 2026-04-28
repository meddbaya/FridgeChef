package com.fridgechef.models;

public class EtapePreparation {

    private int numero;
    private String description;
    private int duree; // en minutes

    public EtapePreparation() {}

    public EtapePreparation(int numero, String description, int duree) {
        this.numero = numero;
        this.description = description;
        this.duree = duree;
    }

    public String afficherEtape() {
        return "Étape " + numero + " (" + duree + " min) : " + description;
    }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getDuree() { return duree; }
    public void setDuree(int duree) { this.duree = duree; }

    @Override
    public String toString() {
        return afficherEtape();
    }
}
