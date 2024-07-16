package com.valasapplication.app.Models;

import java.util.ArrayList;

public class Blog {


    private ArrayList<Article> listArticle ;

    public Blog(ArrayList<Article> listArticle) {
        this.listArticle = listArticle;
    }

    public ArrayList<Article> getListArticle() {
        return listArticle;
    }

    public void setListArticle(ArrayList<Article> listArticle) {
        this.listArticle = listArticle;
    }
}
