package com.landry.hotel.Models;

import java.util.Date;

public class Sejour {


    private Integer IdSejour;
    private String NumChambre;
    private Date DateEntreSejour;
    private Integer NombreJours    ;
    private String nomClient;
    private  String telephone;

    public Integer getIdSejour() {
        return IdSejour;
    }

    public void setIdSejour(Integer idSejour) {
        IdSejour = idSejour;
    }

    public String getNumChambre() {
        return NumChambre;
    }

    public void setNumbChambre(String numbChambre) {
        NumChambre = numbChambre;
    }

    public java.sql.Date getDateEntreSejour() {
        return (java.sql.Date) DateEntreSejour;
    }

    public void setDateEntreSejour(Date dateEntreSejour) {
        DateEntreSejour = dateEntreSejour;
    }

    public Integer getNombreJours() {
        return  NombreJours;
    }

    public void setNombreJours(Integer nombreJours) {
        NombreJours = nombreJours;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Sejour(Integer IdSejour, String NumChambre, Date DateEntreSejour, Integer NombreJours, String nomClient,
                  String telephone){
        this.IdSejour=IdSejour;
        this.NumChambre=NumChambre;
        this.DateEntreSejour=DateEntreSejour;
        this.NombreJours=NombreJours;
        this.nomClient=nomClient;
        this.telephone=telephone;
    }

}
