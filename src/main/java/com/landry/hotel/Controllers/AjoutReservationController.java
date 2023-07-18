package com.landry.hotel.Controllers;
import com.landry.hotel.DB.Query;
import com.landry.hotel.Models.Chambre;
import com.landry.hotel.Models.Occuper;
import com.landry.hotel.Models.Reservation;
import com.mysql.cj.util.TestUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AjoutReservationController implements Initializable {

    @FXML
    public TextField NumChambreTextfield;

    @FXML
    public DatePicker DateEntrerReservation;

    @FXML
    public DatePicker DateEntrer;

    @FXML
    public  TextField NombreJours;
    @FXML
    public TextField NomClient;
    @FXML
    public TextField mail;
    @FXML
    public Button back;
    @FXML
        public void BackButton(ActionEvent actionEvent) throws Exception{
        Stage stage =(Stage) back.getScene().getWindow();
        stage.close();
    }
    
    public void AddNewReservation(ActionEvent event) throws Exception {
        Reservation compteRendu =new Reservation(0, NumChambreTextfield.getText(), Date.valueOf(DateEntrerReservation.getValue()), Date.valueOf(DateEntrer.getValue()),Integer.parseInt(NombreJours.getText()),NomClient.getText(),mail.getText());
        Query query =new Query();
        String recipient = mail.getText();
        String subject = "Bienvenue sur Hotel_Luxe";
        String content = "Informarions :\n" +
                "Chambre Occupé :" +NumChambreTextfield.getText() +"\n"+ "Date Entrer : "
                + DateEntrerReservation.getValue()+ "\n"+ "Nombre de jours : " + NombreJours.getText() +"\n" + "Merci de Choisir le Luxe" ;

        query.setCompteRenduListReservation(compteRendu);
//        query.sendEmail(recipient , subject ,content);
        BackButton(event);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
