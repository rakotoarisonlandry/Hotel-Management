package com.landry.hotel.Controllers;
import com.landry.hotel.DB.DBConnection;
import com.landry.hotel.DB.Query;
import com.landry.hotel.Models.Chambre;
import com.landry.hotel.Models.Sejour;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class ChambreController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showChambreList();
    }
    Connection con =null;
    PreparedStatement st = null;
    public TableView<Chambre> ChambreView;
    public TableColumn<Chambre,String> ColInumchambre;
    public TableColumn<Chambre,String> colDesignation;
    public TableColumn<Chambre,String> coltype;
    public TableColumn<Chambre,Integer> colPrixnuite;
    @FXML
    public StackPane ContentChambre;

    @FXML
    public TextField DesignationTextField;
    @FXML
    public TextField TypeTextField;
    @FXML
    public TextField PrixNuiteTextField;
//    @FXML
//    public TextField DesignationTextField;
    @FXML
    public Button AjouterChambreButton;

    @FXML
    public  Button ButtonMiseAJours;
    @FXML
    public  Button Actualiserbutton;
    public AnchorPane HBoxChambreList;
    public String numChambre;
    @FXML
    public void ActionAjouterChambreButton(ActionEvent actionEvent) throws Exception{
        try {
            Parent root  = FXMLLoader.load(getClass().getResource("/FXML/AjoutChambre.fxml"));
            Stage primarystage = new Stage();
            primarystage.initStyle(StageStyle.UNDECORATED);
            primarystage.setScene(new Scene(root ,490,477));
            primarystage.show();
        }
        catch (Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public  void  ActuliserButtonChambre(ActionEvent event ) throws  Exception{
        showChambreList();
    }


    public void showChambreList(){
        Query q= new Query();
        ObservableList<Chambre> list = q.getChambre();
        try{
            ColInumchambre.setCellValueFactory(new PropertyValueFactory<Chambre, String>("numChambre"));
            colDesignation.setCellValueFactory(new PropertyValueFactory<Chambre,String >("Designation"));
            coltype.setCellValueFactory(new PropertyValueFactory<Chambre, String>("Type"));
            colPrixnuite.setCellValueFactory(new PropertyValueFactory<Chambre, Integer>("PrixNuite"));
            ChambreView.setItems(list);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void getData(MouseEvent event) {
        Chambre chambre = ChambreView.getSelectionModel().getSelectedItem();
        this.numChambre = chambre.getNumChambre();
        DesignationTextField.setText(chambre.getDesignation());
        TypeTextField.setText(chambre.getType());
        PrixNuiteTextField.setText(String.valueOf(chambre.getPrixNuite()));

        DesignationTextField.setDisable(false);
        TypeTextField.setDisable(false);
        PrixNuiteTextField.setDisable(false);
        ButtonMiseAJours.setDisable(false);
    }
    public void  ClearANDactive(){

        DesignationTextField.setDisable(true);
        TypeTextField.setDisable(true);
        PrixNuiteTextField.setDisable(true);
        ButtonMiseAJours.setDisable(true);
    }
    public void OnBtnDeleteClient(ActionEvent actionEvent) {
        String queryDelete ="Delete from `chambre` where numChambre= ? ";
        DBConnection dbConnection =new DBConnection();
        OccuperController controller = new OccuperController();
        con = dbConnection.getConnection("hotel","root","");
        if (controller.confirmation("Supprimer")){
        try {
                if (this.numChambre != null ){
                    JOptionPane.showMessageDialog(null,"Voulez-Vous Vraiment supprimer?");
                    st = con.prepareStatement(queryDelete);
                    st.setString(1,this.numChambre);
                    st.executeUpdate();
                    ClearANDactive();
                    showChambreList();
                }
            }
            catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }
    }
    public  void  ActionUpdateChambreButton(ActionEvent event) throws  Exception{

        DBConnection dbConnection =new DBConnection();
        String Query = "Update `chambre` set Designation= ?, Type = ?, PrixNuite=? where numChambre = ? ";
        OccuperController controller = new OccuperController();
        con = dbConnection.getConnection("hotel","root","");

        if (controller.confirmation("Modifier")){
        try {
            st = con.prepareStatement(Query);
            st.setString(1,DesignationTextField.getText());
            st.setString(2,TypeTextField.getText());
            st.setInt(3,Integer.parseInt(PrixNuiteTextField.getText()));
            st.setString(4,numChambre);
            st.executeUpdate();
            ClearANDactive();
            showChambreList();
        }catch (Exception e){
            e.printStackTrace();
        }
        }
    }
}