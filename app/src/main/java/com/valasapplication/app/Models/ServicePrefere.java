package com.valasapplication.app.Models;

public class ServicePrefere {

    private int id;
    private Services service;
    private String date;

    public ServicePrefere(Services service) {
        this.service = service;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
