package com.landry.hotel.Controllers;

import com.landry.hotel.DB.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContentAllController implements Initializable {
    Connection con;
    PreparedStatement st = null;
    @FXML
    public StackPane ContentArea;
    @FXML
    public Label soldeIdLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Soldeactuel();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Parent fxml  = FXMLLoader.load(getClass().getResource("/FXML/Chambre.fxml"));
            ContentArea.getChildren().removeAll();
            ContentArea.getChildren().setAll(fxml);
        }catch (IOException ex){
            Logger.getLogger(ContentAllController.class.getName()).log(Level.SEVERE ,null,ex);
        }
    }

    public  void Chambre (javafx.event.ActionEvent actionEvent) throws  IOException{
        Parent fxml =FXMLLoader.load(getClass().getResource("/FXML/Chambre.fxml"));
        ContentArea.getChildren().removeAll();
        ContentArea.getChildren().setAll(fxml);
    }

    public  void Reservation (javafx.event.ActionEvent actionEvent) throws  IOException{
        Parent fxml =FXMLLoader.load(getClass().getResource("/FXMl/Reservation.fxml"));
        ContentArea.getChildren().removeAll();
        ContentArea.getChildren().setAll(fxml);
    }

    public  void Sejouner (javafx.event.ActionEvent actionEvent) throws  IOException{
        Parent fxml =FXMLLoader.load(getClass().getResource("/FXML/Sejour.fxml"));
        ContentArea.getChildren().removeAll();
        ContentArea.getChildren().setAll(fxml);
    }

    public  void Occuper (javafx.event.ActionEvent actionEvent) throws  IOException{
        Parent fxml =FXMLLoader.load(getClass().getResource("/FXML/Occuper.fxml"));
        ContentArea.getChildren().removeAll();
        ContentArea.getChildren().setAll(fxml);
    }

    public void Soldeactuel() throws SQLException {
        try {
            DBConnection dbConnection = new DBConnection();
            con = dbConnection.getConnection("hotel", "root", "");
            ResultSet rs = con.createStatement().executeQuery("select SoldeActuel from solde");
            ObservableList data = FXCollections.observableArrayList();
            while (rs.next()) {

                data.add(new String(rs.getString(1)));
            }
            soldeIdLabel.setText(String.valueOf(data));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

