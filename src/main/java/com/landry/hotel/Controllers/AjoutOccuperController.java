package com.landry.hotel.Controllers;
import com.landry.hotel.DB.Query;
import com.landry.hotel.Models.Occuper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.*;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class AjoutOccuperController implements Initializable {

    @FXML
    public Button annulerOccuperButton;
    @FXML
    public TextField IdreservationTextField;
    @FXML
    public void ActionannulerOccuperButton(ActionEvent actionEvent) throws Exception{
        Stage stage =(Stage) annulerOccuperButton.getScene().getWindow();
        stage.close();
    }
    public void AddNewSejour(ActionEvent event) throws Exception{
        Occuper Rendue =new Occuper(0, Integer.parseInt(IdreservationTextField.getText()));
        Query query =new Query();
        query.setCompteRenduListOccuper(Rendue);
        JOptionPane.showMessageDialog(null,"SuccessFully Added");
        ActionannulerOccuperButton(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
