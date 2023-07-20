package com.landry.hotel.Controllers;
import com.landry.hotel.DB.Query;
import com.landry.hotel.Models.Chambre;
import com.landry.hotel.Models.Sejour;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class AjoutSejoutController implements Initializable {
    @FXML
    public Button annulerSejourButton;
    @FXML
    public TextField NumbreTextField;

    @FXML
    public DatePicker DateEntrerSejour;

    @FXML
    public TextField NombreJoursTextField;

    @FXML
    public TextField nomClientTextField;
    @FXML
    public TextField telephoneTextField;
    @FXML
    public void ActionannulerSejourButton(ActionEvent actionEvent) throws Exception{
        Stage stage =(Stage) annulerSejourButton.getScene().getWindow();
        stage.close();
    }
    public void AddNewSejour(ActionEvent event) throws Exception{
        Sejour compteRendu =new Sejour(0,NumbreTextField.getText(), Date.valueOf(DateEntrerSejour.getValue()) ,Integer.parseInt(NombreJoursTextField.getText()),nomClientTextField.getText(),telephoneTextField.getText());
        Query query =new Query();
        query.setCompteRenduListSejour(compteRendu);
        ActionannulerSejourButton(event);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
