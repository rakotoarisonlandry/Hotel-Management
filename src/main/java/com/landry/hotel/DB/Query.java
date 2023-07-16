package com.landry.hotel.DB;

import com.landry.hotel.Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
public class Query {
    Connection conn;
    public ObservableList<com.landry.hotel.Models.Reservation> getReservationList(){
        ObservableList<com.landry.hotel.Models.Reservation> ReservationList = FXCollections.observableArrayList();
        try{
            DBConnection dbConnection = new DBConnection();
            conn=dbConnection.getConnection("hotel","root","");
            String query="SELECT * FROM `reservation` order by idReservation ASC";
            Statement st = conn.createStatement();
            ResultSet rs =st.executeQuery(query);
            Reservation reservation;
            while(rs.next()){
                reservation=new Reservation(rs.getInt("idReservation"),rs.getString("numChambre"),rs.getDate("dateReservation"),rs.getDate("dateEntrer"),rs.getInt("nombreJours"),rs.getString("nomClient"),rs.getString("mail"));
                ReservationList.add(reservation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ReservationList;
    }


    public ObservableList<com.landry.hotel.Models.Occuper> getOccuperList(){
        ObservableList<com.landry.hotel.Models.Occuper> OccuperList = FXCollections.observableArrayList();
        try{
            DBConnection dbConnection = new DBConnection();
            conn =dbConnection.getConnection("hotel","root","");
            String query="SELECT * FROM `occuper` order by idReservation ASC";
            Statement st = conn.createStatement();
            ResultSet rs =st.executeQuery(query);
            Occuper Occuper;
            while(rs.next()){
                Occuper=new Occuper(rs.getInt("idReservation"),rs.getInt("idOccuper"));
                OccuperList.add(Occuper);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return OccuperList;
    }
    public ObservableList<com.landry.hotel.Models.Solde> getSolde(){
        ObservableList<com.landry.hotel.Models.Solde> SoldeList = FXCollections.observableArrayList();
        try{
            DBConnection dbConnection = new DBConnection();
            conn =dbConnection.getConnection("hotel","root","");
            String query="SELECT SoldeActuel FROM `solde`";
            Statement st = conn.createStatement();
            ResultSet rs =st.executeQuery(query);
            Solde Solde;
            while(rs.next()){
                Solde=new Solde(rs.getInt("idsolde"),rs.getInt("soldeActuel"));
                SoldeList.add(Solde);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return SoldeList;
    }


    public ObservableList<com.landry.hotel.Models.Sejour> getSejour(){
        ObservableList<com.landry.hotel.Models.Sejour> SejourList = FXCollections.observableArrayList();
        try{
            DBConnection dbConnection = new DBConnection();
            conn =dbConnection.getConnection("hotel","root","");
            String query="SELECT * FROM `sejour` order by IdSejour ASC";
            Statement st = conn.createStatement();
            ResultSet rs =st.executeQuery(query);
            Sejour Sejour;
            while(rs.next()){
                Sejour=new Sejour(rs.getInt("IdSejour"),rs.getString("NumChambre") , rs.getDate("DateEntreSejour") , rs.getInt("NombreJours"), rs.getString("nomClient"),rs.getString("telephone"));
                SejourList.add(Sejour);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return SejourList;
    }

    public void  setCompteRenduList(Chambre compteRendu){
        Connection conn=null;
        try{
            DBConnection dbConnection =new DBConnection();
            conn= dbConnection.getConnection("hotel","root","");
            String query ="Insert into chambre values (?,?,?,?)";
            PreparedStatement ps= conn.prepareStatement(query);
            ps.setString(1,compteRendu.getNumChambre());
            ps.setString(2,compteRendu.getDesignation());
            ps.setString(3,compteRendu.getType());
            ps.setInt(4,compteRendu.getPrixNuite());
            ps.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void  setCompteRenduListSejour(Sejour compteRendu){
        Connection conn=null;
        try{
            DBConnection dbConnection =new DBConnection();
            conn= dbConnection.getConnection("hotel","root","");
            String query ="Insert into sejour values (?,?,?,?,?,?)";
            PreparedStatement ps= conn.prepareStatement(query);
            ps.setInt(1,compteRendu.getIdSejour());
            ps.setString(2,compteRendu.getNumChambre());
            ps.setDate(3, (Date) compteRendu.getDateEntreSejour());
            ps.setInt(4,compteRendu.getNombreJours());
            ps.setString(5,compteRendu.getNomClient());
            ps.setString(6, compteRendu.getTelephone());
            ps.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void  setCompteRenduListOccuper(Occuper compteRendu){
        Connection conn=null;
        try{
            DBConnection dbConnection =new DBConnection();
            conn= dbConnection.getConnection("hotel","root","");
            String query ="Insert into occuper values (?,?)";
            PreparedStatement ps= conn.prepareStatement(query);
            ps.setInt(1,compteRendu.getIdOccuper());
            ps.setInt(2,compteRendu.getIdReservation());

            ps.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void  setCompteRenduListReservation(Reservation compteRendu){
        Connection conn=null;
        try{
//            DBConnection dbConnection =new DBConnection();
//            conn= dbConnection.getConnection("hotel","root","");
//            String query ="Insert into reservation (idReservation,numChambre,dateReservation,dateEntrer,nombreJours,nomClient,mail) values (?,?,?,?,?,?,?)";
//
//            PreparedStatement ps= conn.prepareStatement(query);
//            ps.setInt(1,compteRendu.getIdReservation());
//            ps.setString(2,compteRendu.getNumChambre());
//            ps.setDate(3, compteRendu.getDateReservation());
//            ps.setDate(4, (Date) compteRendu.getDateEntrer());
//            ps.setInt(5,compteRendu.getNombreJours());
//            ps.setString(6,compteRendu.getNomClient());
//            ps.setString(7,compteRendu.getMail());
//
//
//            String queryUpdate ="UPDATE `solde` SET" +
//                    "`SoldeActuel`= (`SoldeActuel`+ " +
//                    "(SELECT PrixNuite FROM chambre WHERE numChambre = ?)"+
//                    "*(SELECT NombreJours FROM reservation WHERE idReservation =?))"+
//                    "WHERE idSolde = 1";
//            PreparedStatement statement = conn.prepareStatement(queryUpdate);
//            statement.setString(1, compteRendu.getNumChambre());
//            statement.setInt(2,compteRendu.getIdReservation());
//            statement.execute();
//            ps.execute();

            DBConnection dbConnection = new DBConnection();
            conn = dbConnection.getConnection("hotel", "root", "");

            String queryUpdate = "UPDATE solde SET SoldeActuel = (SoldeActuel + (SELECT PrixNuite FROM chambre WHERE numChambre = ?) * (SELECT NombreJours FROM reservation WHERE idReservation = ?)) WHERE idSolde = 1";
            PreparedStatement statement = conn.prepareStatement(queryUpdate);
            statement.setString(1, compteRendu.getNumChambre());
            statement.setInt(2, compteRendu.getIdReservation());
            statement.execute();

            String query = "INSERT INTO reservation (idReservation, numChambre, dateReservation, dateEntrer, nombreJours, nomClient, mail) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, compteRendu.getIdReservation());
            ps.setString(2, compteRendu.getNumChambre());
            ps.setDate(3, compteRendu.getDateReservation());
            ps.setDate(4, (Date) compteRendu.getDateEntrer());
            ps.setInt(5, compteRendu.getNombreJours());
            ps.setString(6, compteRendu.getNomClient());
            ps.setString(7, compteRendu.getMail());
            ps.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }



    public void deleteChambreList(Chambre Chambre){
        Connection connection=null;
        try{
            DBConnection dbConnection =new DBConnection();
            connection=dbConnection.getConnection("hotel","root","");
            PreparedStatement ps= connection.prepareStatement("Delete from `chambre` where numChambre= ?");
            ps.setString(1,Chambre.getNumChambre());
            ps.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<com.landry.hotel.Models.Chambre> getChambre(){
        ObservableList<com.landry.hotel.Models.Chambre> ChambreList = FXCollections.observableArrayList();
        try{
            DBConnection dbConnection = new DBConnection();
            conn =dbConnection.getConnection("hotel","root","");
            String query="SELECT * FROM `chambre` ";
            Statement st = conn.createStatement();
            ResultSet rs =st.executeQuery(query);
            Chambre Chambre;
            while(rs.next()){
                Chambre=new Chambre(rs.getString("numChambre"),rs.getString("Designation") , rs.getString("Type") , rs.getInt("PrixNuite"));
                ChambreList.add(Chambre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ChambreList;
    }


}
