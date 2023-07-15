package com.landry.hotel.Controllers;

import com.landry.hotel.DB.DBConnection;
import com.landry.hotel.DB.Query;
import com.landry.hotel.Models.Chambre;
import com.landry.hotel.Models.Occuper;
import com.landry.hotel.Models.Reservation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class OccuperController implements Initializable {

    Connection con;
    PreparedStatement st;
    public TableView<Occuper> OccuperView;
    public TableColumn<Occuper,Integer> ColIdOccuper;
    public TableColumn<Occuper, Integer> colIdReservation;
    public Integer idReservation;
    public  Integer idOccuper;
    @FXML
    public Button ajoutOccuperButton;
    @FXML
    public TextField idReservationTextfield;
    @FXML
    public void ActionajoutOccuperButton(ActionEvent actionEvent) throws Exception{
        try {
            Parent root  = FXMLLoader.load(getClass().getResource("/FXML/AjoutOccuper.fxml"));
            Stage primarystage = new Stage();
            primarystage.initStyle(StageStyle.UNDECORATED);
            primarystage.setScene(new Scene(root ,568,264));
            primarystage.show();
        }
        catch (Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }
    @FXML
    public void  ActionActualiser(ActionEvent event) throws  Exception{
        showOccuperList();
    }
    public void showOccuperList(){
        Query q= new Query();
        ObservableList<Occuper> list = q.getOccuperList();
        try{
            colIdReservation.setCellValueFactory(new PropertyValueFactory<Occuper, Integer>("idReservation"));
            ColIdOccuper.setCellValueFactory(new PropertyValueFactory<Occuper, Integer>("idOccuper"));
            OccuperView.setItems(list);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void getData(MouseEvent event) {
        Occuper Selecte = OccuperView.getSelectionModel().getSelectedItem();
        this.idReservation = Selecte.getIdReservation();
        this.idOccuper = Selecte.getIdOccuper();
        idReservationTextfield.setText(String.valueOf(Selecte.getIdReservation()));
    }

    public  void OnUpdateoccuper(ActionEvent event) throws  Exception{
        DBConnection dbConnection =new DBConnection();

        String queryDeleteChmabre ="Update`occuper` set idReservation=? where  idOccuper= ?";
        con = dbConnection.getConnection("hotel","root","");
        try {
            if (this.idReservation != null){

                st= con.prepareStatement(queryDeleteChmabre);
                st.setInt(1, Integer.parseInt(idReservationTextfield.getText()));
                st.setInt(2,this.idOccuper);
                st.executeUpdate();
                showOccuperList();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void OnBtnDeleteOccuper(ActionEvent actionEvent) {


        DBConnection dbConnection =new DBConnection();

        String queryDeleteChmabre ="Delete from `chambre` where = ?";
        con = dbConnection.getConnection("hotel","root","");
        try {
            if (this.idReservation != null){

                st= con.prepareStatement(queryDeleteChmabre);
                st.setInt(1,this.idReservation);
                st.executeUpdate();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String queryDelete ="Delete from `reservation` where idReservation= ?";
        con = dbConnection.getConnection("hotel","root","");
        try {
            if (this.idReservation != null){

                st= con.prepareStatement(queryDelete);
                st.setInt(1,this.idReservation);
                st.executeUpdate();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String queryDeleteOccuper ="Delete from `occuper`  where idOccuper= ? AND idReservation=?";
        con = dbConnection.getConnection("hotel","root","");
        try {
            if (this.idOccuper != null && confirmation("Supprimer")){
                st = con.prepareStatement(queryDeleteOccuper);
                st.setInt(1,this.idOccuper);
                st.setInt(2,this.idReservation);
                st.executeUpdate();
                showOccuperList();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean confirmation(String traitement){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Veuillez confirmer");
        dialog.setHeaderText("vous voulez "+traitement+ " ces informations ?");
        dialog.initModality(Modality.APPLICATION_MODAL);
        ButtonType okBtn = new ButtonType("Confirmer",ButtonBar.ButtonData.OK_DONE);
        ButtonType retourBtn = new ButtonType("Annuler",ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okBtn,retourBtn);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == okBtn){
            return true;
        }else {return false;}
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showOccuperList();
    }
}
