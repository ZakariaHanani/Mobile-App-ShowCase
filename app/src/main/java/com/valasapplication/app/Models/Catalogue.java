package com.valasapplication.app.Models;

import java.util.List;

public class Catalogue {

    private List<Services> servicesList ;

    public Catalogue(List<Services> servicesList) {
        this.servicesList = servicesList;
    }

    public List<Services> getServicesList() {
        return servicesList;
    }

    public void setServicesListt(List<Services> servicesList) {
        this.servicesList = servicesList;
    }

}