package com.valasapplication.app.Models;

import java.util.ArrayList;

public class Services {
    private int id ;
    private String nom ;
    private String categorie ;
    private String description ;
    private ArrayList<String> fonctionalites ;
    private Double prix;
    private Boolean isFavorite;
    private String imageUrl ;

    public Services(int id,String nom, String description, ArrayList<String> fonctionalites, Double prix,String categorie,String imageUrl,Boolean isFavorite) {
        this.id=id;
        this.nom = nom;
        this.description = description;
        this.fonctionalites = fonctionalites;
        this.prix = prix;
        this.categorie =categorie ;
        this.imageUrl =imageUrl;
        this.isFavorite=isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getFonctionalites() {
        return fonctionalites;
    }

    public void setFonctionalites(ArrayList<String> fonctionalites) {
        this.fonctionalites = fonctionalites;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }
}
