package com.valasapplication.app.Models;

public class Article {
    private int artId;
    private String titre;
    private String contenu ;
    private String date_publication ;
    private String imageUrl;
    private int isFavorite ;
    private Categorie categorie;

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(String date_publication) {
        this.date_publication = date_publication;
    }

    public Article(int artId, String titre, String contenu, String date_publication,String imageUrl,int isFavorite ) {
        this.artId = artId;
        this.titre = titre;
        this.contenu = contenu;
        this.date_publication = date_publication;
        this.imageUrl =imageUrl;
        this.isFavorite=isFavorite;
    }

    public int getArtId() {
        return artId;
    }

    public void setArtId(int artId) {
        this.artId = artId;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }


    public Categorie getCategorie() {
        return categorie;
    }


    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}
