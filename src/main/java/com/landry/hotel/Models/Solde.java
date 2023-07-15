package com.landry.hotel.Models;

public class Solde {

    private  Integer Idsolde;
    private Integer SoldeActuel;

    public Integer getIdsolde() {
        return Idsolde;
    }

    public void setIdsolde(Integer idsolde) {
        Idsolde = idsolde;
    }

    public Integer getSoldeActuel() {
        return SoldeActuel;
    }

    public void setSoldeActuel(Integer soldeActuel) {
        SoldeActuel = soldeActuel;
    }

    public  Solde(Integer idsolde, Integer soldeActuel){
        this.Idsolde =idsolde;
        this.SoldeActuel =soldeActuel;
    }
}
