package com.valasapplication.app.Models;

public class Preferences {
    private int id;
    private int idarticle ;
    private int idservice ;

    public Preferences(int id, int idarticle ,int idservice ) {
        this.id = id;
        this.idarticle = idarticle;
    }
    public Preferences(int id,int idservice){
        this.id=id;
        this.idservice =idservice;
    }


}
