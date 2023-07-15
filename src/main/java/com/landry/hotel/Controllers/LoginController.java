package com.landry.hotel.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    public TextField userTextFiled;

    @FXML
    public PasswordField passwordField;

    @FXML
    public Button login;

    @FXML
    public  Label errorMessage;

    @FXML
    public  Button Cancelbutton;

    public  void annuler(ActionEvent event){
        Stage stage =(Stage) Cancelbutton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void ActionBoutton(ActionEvent event){
    if (userTextFiled.getText().isBlank()==false && passwordField.getText().isBlank()==false){
        try {
            Parent root  = FXMLLoader.load(getClass().getResource("/FXML/ContentAll.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root ,1375,710));
            stage.show();
            annuler(event);
        }
        catch (Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }
    else {
        errorMessage.setText("Erreur de validation!!! ");
    }
    }
}
