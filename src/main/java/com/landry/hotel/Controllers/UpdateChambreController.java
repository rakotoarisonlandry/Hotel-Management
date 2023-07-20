package com.landry.hotel.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class UpdateChambreController implements Initializable {

    Connection con =null;
    PreparedStatement st = null;
        String numChambre;
        @FXML
        public TextField  updateNumChambre;


        @FXML
        public TextField  updateDesignation;

        @FXML
        public TextField  updateType;

        @FXML
        public TextField  updatPrixnuite;


        public void ActionUpdatechambreButton(ActionEvent event) throws  Exception{
            }
    public void ActionAnnulerUpdatePageChambreButton(ActionEvent event) {
        JOptionPane.showMessageDialog(null,"Annuler le mise a jours?");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
