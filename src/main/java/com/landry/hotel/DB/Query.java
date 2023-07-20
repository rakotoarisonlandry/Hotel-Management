package com.landry.hotel.DB;
import com.landry.hotel.Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


import javax.swing.*;
import java.sql.*;
public class Query {
    Connection conn;
    int i =0;
    PreparedStatement st = null;
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
                Occuper=new Occuper(rs.getInt("idOccuper"),rs.getInt("idReservation"));
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
            String queryTest = "Select numChambre FROM chambre ";
            Statement st = conn.createStatement();
            ResultSet rs =st.executeQuery(queryTest);
            while(rs.next()){
                if (compteRendu.getNumChambre().equals(rs.getString("numChambre"))){
                    i+=1;
                    break;
                }
            }
            /**/
        }catch(Exception e){
            e.printStackTrace();
        }
        if (i> 0){
            JOptionPane.showMessageDialog(null,"Le Chambre est deja crée ,Veuillez entrer un autre");
            i=0;
        }
        else {
            try {
                DBConnection dbConnection = new DBConnection();
                conn = dbConnection.getConnection("hotel", "root", "");
                String query = "Insert into chambre values (?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, compteRendu.getNumChambre());
                ps.setString(2, compteRendu.getDesignation());
                ps.setString(3, compteRendu.getType());
                ps.setInt(4, compteRendu.getPrixNuite());
                ps.execute();
                JOptionPane.showMessageDialog(null,"Ajouter avec succes");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
public void setCompteRenduListSejour(Sejour compteRendu) {
    Connection conn = null;
    PreparedStatement ps = null;
    int i = 0;

    try {
        // Establish database connection
        DBConnection dbConnection = new DBConnection();
        conn = dbConnection.getConnection("hotel", "root", "");

        // Check if the room is already occupied
        String queryCheck = "SELECT COUNT(*) AS count FROM sejour WHERE numChambre = ? AND dateEntreSejour = ?";
        ps = conn.prepareStatement(queryCheck);
        ps.setString(1, compteRendu.getNumChambre());
        ps.setDate(2, compteRendu.getDateEntreSejour());
        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt("count");

        if (count > 0) {
            JOptionPane.showMessageDialog(null, "La chambre est déjà occupée. Veuillez choisir une autre chambre.");
        } else {
            // Insert the new sejour
            String queryInsert = "INSERT INTO sejour (idSejour, numChambre, dateEntreSejour, nombreJours, nomClient, telephone) VALUES (?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(queryInsert);
            ps.setInt(1, compteRendu.getIdSejour());
            ps.setString(2, compteRendu.getNumChambre());
            ps.setDate(3, compteRendu.getDateEntreSejour());
            ps.setInt(4, compteRendu.getNombreJours());
            ps.setString(5, compteRendu.getNomClient());
            ps.setString(6, compteRendu.getTelephone());
            ps.executeUpdate();

            // Update the SoldeActuel in the solde table
            String queryUpdate = "UPDATE solde SET SoldeActuel = SoldeActuel + (SELECT PrixNuite FROM chambre WHERE numChambre = ?) * ?";
            ps = conn.prepareStatement(queryUpdate);
            ps.setString(1, compteRendu.getNumChambre());
            ps.setInt(2, compteRendu.getNombreJours());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Ajouté avec succès !");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Close resources
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public  void sendEmail(String recipient, String subject, String content) {
        // Configuration de la session de messagerie
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Informations d'authentification pour le serveur SMTP
        final String username = "rakotorisonlandry@gmail.com";
        final String password = "qhwpdzughayzdfkt";

        // Création de la session de messagerie avec l'authentification
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            // Création de l'objet Message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(content);

            // Envoi de l'e-mail
            Transport.send(message);

            System.out.println("E-mail envoyé avec succès !");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public void setCompteRenduListReservation(Reservation compteRendu) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Establish database connection
            DBConnection dbConnection = new DBConnection();
            conn = dbConnection.getConnection("hotel", "root", "");
            // Check if the room is already occupied
            String queryCheck = "SELECT COUNT(*) AS count FROM reservation WHERE (dateEntrer BETWEEN ? AND DATE_ADD(?, INTERVAL ? DAY)) AND numChambre = ?";
            ps = conn.prepareStatement(queryCheck);
            ps.setDate(1, compteRendu.getDateEntrer());
            ps.setDate(2, compteRendu.getDateEntrer());
            ps.setInt(3,compteRendu.getNombreJours());
            ps.setString(4, compteRendu.getNumChambre());
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt("count");
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "La chambre est déjà occupée. Veuillez choisir une autre chambre.");
            } else {
                // Insert the new reservation
                String queryInsert = "INSERT INTO reservation (idReservation, numChambre, dateReservation, dateEntrer, nombreJours, nomClient, mail) VALUES (?, ?, ?, ?, ?, ?, ?)";
                ps = conn.prepareStatement(queryInsert);
                ps.setInt(1, compteRendu.getIdReservation());
                ps.setString(2, compteRendu.getNumChambre());
                ps.setDate(3, compteRendu.getDateReservation());
                ps.setDate(4, compteRendu.getDateEntrer());
                ps.setInt(5, compteRendu.getNombreJours());
                ps.setString(6, compteRendu.getNomClient());
                ps.setString(7, compteRendu.getMail());
                ps.executeUpdate();

                // Update the SoldeActuel in the solde table
                String queryUpdate = "UPDATE solde SET SoldeActuel = SoldeActuel + (SELECT PrixNuite FROM chambre WHERE numChambre = ?) * ?";
                ps = conn.prepareStatement(queryUpdate);
                ps.setString(1, compteRendu.getNumChambre());
                ps.setInt(2, compteRendu.getNombreJours());
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Ajouté avec succès !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
