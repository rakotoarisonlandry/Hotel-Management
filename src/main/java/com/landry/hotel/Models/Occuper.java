package com.landry.hotel.Models;

public class Occuper {

    private Integer idOccuper;
    private Integer idReservation;

    public Integer getIdOccuper() {
        return idOccuper;
    }

    public void setIdOccuper(Integer idOccuper) {
        this.idOccuper = idOccuper;
    }

    public Integer getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }

    public Occuper(Integer idOccuper , Integer idReservation){
        this.idOccuper=idOccuper;
        this.idReservation=idReservation;
    }
}
