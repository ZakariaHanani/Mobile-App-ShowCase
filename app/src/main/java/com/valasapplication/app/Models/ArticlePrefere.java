package com.valasapplication.app.Models;

public class ArticlePrefere {
    private  int id ;
    private Article article ;
    private String date;

    public ArticlePrefere(Article article, String date) {
        this.article = article;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
