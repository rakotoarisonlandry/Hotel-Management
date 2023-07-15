package com.landry.hotel.Models;

import java.util.Date;

public class Reservation {
   private Integer idReservation;
    private String numChambre;
    private  Date dateReservation;
    private Date dateEntrer;
    private  Integer nombreJours;

    private  String mail;
    private String nomClient;

    public Integer getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }

    public String getNumChambre() {
        return numChambre;
    }

    public void setNumChambre(String numChambre) {
        this.numChambre = numChambre;
    }

    public java.sql.Date getDateReservation() {
        return (java.sql.Date) dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public java.sql.Date getDateEntrer() {
        return (java.sql.Date) dateEntrer;
    }

    public void setDateEntrer(Date dateEntrer) {
        this.dateEntrer = dateEntrer;
    }


    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }


    public Integer getNombreJours() {
        return nombreJours;
    }

    public void setNombreJours(Integer nombreJours) {
        this.nombreJours = nombreJours;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Reservation(Integer idReservation,String numChambre, Date dateReservation, Date dateEntrer, Integer nombreJours,
                       String nomClient, String mail){
        this.idReservation=idReservation;
        this.numChambre=numChambre;
        this.dateReservation=dateReservation;
        this.dateEntrer=dateEntrer;
        this.nombreJours=nombreJours;
        this.nomClient=nomClient;
        this.mail=mail;
  }
}
