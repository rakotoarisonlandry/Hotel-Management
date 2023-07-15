package com.landry.hotel.Models;

import java.util.Date;

public class Chambre {
    private String numChambre;
    private String Designation;
    private String Type;
    private Integer PrixNuite;


    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Integer getPrixNuite() {
        return PrixNuite;
    }

    public void setPrixNuite(Integer prixNuite) {
        PrixNuite = prixNuite;
    }

    public String getNumChambre() {
        return numChambre;
    }

    public void setNumChambre(String numChambre) {
        this.numChambre = numChambre;
    }

    public Chambre(String numChambre, String Designation, String Type, Integer PrixNuite){
        this.numChambre=numChambre;
        this.Designation=Designation;
        this.Type=Type;
        this.PrixNuite=PrixNuite;
    }
}
