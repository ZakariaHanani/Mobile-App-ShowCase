package com.valasapplication.app.Models;

public class Categorie {
    private int id;
    private  String nom ;
    private  String imageUrl ;
    public Categorie( int id,String nom,String imageUrl) {
        this.id=id;
        this.nom = nom;
        this.imageUrl =imageUrl ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categorie(String nom) {
        this.nom = nom;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


}
