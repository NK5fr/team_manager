package dto;

import util.Position;

public class Joueur {
    
    private int jno;
    private String nom;
    private String prenom;
    private int age;
    private Position position;
    private String situation;
    private int valeur;
    private String description;

    public Joueur(int jno, String nom, String prenom, int age, Position position, String situation, int valeur, String description) {
        this.jno = jno;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.position = position;
        this.situation = situation;
        this.valeur = valeur;
        this.description = description;
    }

    public int getJno() {
        return jno;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getAge() {
        return age;
    }

    public Position getPosition() {
        return position;
    }

    public String getSituation() {
        return situation;
    }

    public int getValeur() {
        return valeur;
    }

    public String getDescription() {
        return description;
    }

}
