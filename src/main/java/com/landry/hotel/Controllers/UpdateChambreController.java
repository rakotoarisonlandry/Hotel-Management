package com.landry.hotel.Controllers;

import com.landry.hotel.DB.DBConnection;
import com.landry.hotel.Models.Chambre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateChambreController implements Initializable {

    Connection con =null;
    PreparedStatement st = null;
        String numChambre;
        @FXML
        public TextField  updateNumChambre;

//        @FXML
//        public Button updateAnnuler;

        @FXML
        public TextField  updateDesignation;

        @FXML
        public TextField  updateType;

        @FXML
        public TextField  updatPrixnuite;

//        @FXML
//        public void  GetData(MouseEvent event){
//            ChambreController ctrl = new ChambreController();
//            Chambre chambre =ctrl.ChambreView.getSelectionModel().getSelectedItem();
//            numChambre =chambre.getNumChambre() ;
//            updateNumChambre.setText(chambre.getNumChambre());
//            updateDesignation.setText(chambre.getDesignation());
//            updateType.setText(chambre.getType());
//            updatPrixnuite.setText(String.valueOf(chambre.getPrixNuite()));
//        }
//
        public void ActionUpdatechambreButton(ActionEvent event) throws  Exception{
//            String Query ="Update chambre set Designation = ? , Type =? , PrixNuite =? where numChambre =?";
//            DBConnection dbConnection =new DBConnection();
//            con = dbConnection.getConnection("hotel","root","");
//
//            try {
//                st= con.prepareStatement(Query);
//                st.setString(1,updateDesignation.getText());
//                st.setString(2,updateType.getText());
//                st.setInt(3, Integer.parseInt(updateDesignation.getText()));
//                st.setString(4,updateNumChambre.getText());
//                st.executeUpdate();
//             }catch (SQLException e){
//                throw new RuntimeException(e);
//            }
            }
    public void ActionAnnulerUpdatePageChambreButton(ActionEvent event) {
        JOptionPane.showMessageDialog(null,"Annuler le mise a jours?");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
