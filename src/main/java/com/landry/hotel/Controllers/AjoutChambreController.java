package com.landry.hotel.Controllers;



import com.landry.hotel.DB.Query;
import com.landry.hotel.Models.Chambre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//import javax.swing.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AjoutChambreController  implements Initializable {
    @FXML
    public Pane contenuChambre;

    @FXML
    public TextField numChambreTextfield;

    @FXML
    public  TextField PriNuiteTextField;

    @FXML
    public TextField DesignationTextfield;
    @FXML
    public Button AnnulerPageChambreButton;
    @FXML
    public  Button CreateChambre;

    @FXML
    public  TextField TypeTextFiel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void ActionAnnulerPageChambreButton(ActionEvent event) {
        Stage stage =(Stage) AnnulerPageChambreButton.getScene().getWindow();
        stage.close();
    }
    public void AddNewChambre(ActionEvent event) throws SQLException ,IOException{
        Chambre compteRendu =new Chambre(numChambreTextfield.getText(),DesignationTextfield.getText(),TypeTextFiel.getText(),Integer.parseInt(PriNuiteTextField.getText()));
        Query query =new Query();
        query.setCompteRenduList(compteRendu);
        JOptionPane.showMessageDialog(null,"SuccessFully Added");
        ActionAnnulerPageChambreButton(event);
        ChambreController chambre = new ChambreController();
        chambre.showChambreList();
    }
}
