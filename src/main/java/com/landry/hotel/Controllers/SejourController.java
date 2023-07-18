package com.landry.hotel.Controllers;

import com.itextpdf.text.Document;
import com.landry.hotel.DB.DBConnection;
import com.landry.hotel.DB.Query;
import com.landry.hotel.Models.Chambre;
import com.landry.hotel.Models.Occuper;
import com.landry.hotel.Models.Sejour;
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
import javafx.stage.Stage;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import  com.itextpdf.text.pdf.PdfWriter;
import javafx.stage.StageStyle;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

public class SejourController implements Initializable {
    Connection con;
    PreparedStatement st;
    public  Integer idSejour;

    public  Integer NombreJours;


    public String numchambre;
    public TableView<Sejour> SejourView;
    public TableColumn<Sejour,String> ColInumchambredSejour;

    public TableColumn<Sejour,Integer> ColIdSejouner ;
    public TableColumn<Sejour, Date> coldateEntrerSejour;

    public TableColumn<Sejour, Integer> colNombresDejours;

    public TableColumn<Sejour, String> colnomDeClient;

    public TableColumn<Sejour, String> colTelephone;
    @FXML
    public Button AjoutSejour;
    @FXML
    public TextField numChambreTextField;

    @FXML
    public  Button ButtonMettreJours;

    @FXML
    public DatePicker DateEntrerSejour;

    @FXML
    public  TextField NombreJourstextField;

    @FXML
    public  TextField NomclientTextField;

    @FXML
    public  TextField TelephoneTextField;

    @FXML
    public void actionAjoutSejour(ActionEvent actionEvent) throws Exception{
        try {
            Parent root  = FXMLLoader.load(getClass().getResource("/FXML/AjoutSejour.fxml"));
            Stage primarystage = new Stage();
            primarystage.initStyle(StageStyle.UNDECORATED);
            primarystage.setScene(new Scene(root ,548,610));
            primarystage.show();
        }
        catch (Exception e ){
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void GenererPdf(ActionEvent event) throws Exception {
        DBConnection dbConnection = new DBConnection();
        con = dbConnection.getConnection("hotel", "root", "");
        String queryTest = "SELECT  DATE_ADD(dateEntreSejour, INTERVAL nombreJours  DAY) as DateSortie  FROM sejour";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(queryTest);

        String file_name = "C:\\pdf\\"+numChambreTextField.getText() + ".pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph("Recus ");
        document.add(para);
        para = new Paragraph("Sejour Numero " + this.idSejour);
        document.add(para);
        para = new Paragraph("Nom Client :" + this.NomclientTextField.getText());
        document.add(para);
        para = new Paragraph("Designation Chambre : " + this.numchambre);
        document.add(para);
        para = new Paragraph("Nombre de Jour : " + NombreJourstextField.getText());
        document.add(para);
        para = new Paragraph("Date d'entrée : " + DateEntrerSejour.getValue());
        document.add(para);
        while (rs.next()) {
            para = new Paragraph("Date de Sortie : " + rs.getString("DateSortie"));
            document.add(para);
        }
        document.close();
        JOptionPane.showMessageDialog(null, "Generer avec succes");
    }



    @FXML
    public void getData(MouseEvent event) {
        Sejour chambre = SejourView.getSelectionModel().getSelectedItem();
        this.numchambre = chambre.getNumChambre();
        this.idSejour =chambre.getIdSejour();
        this.NombreJours =chambre.getNombreJours();
        numChambreTextField.setText(chambre.getNumChambre());
        DateEntrerSejour.setValue(chambre.getDateEntreSejour().toLocalDate());
        NombreJourstextField.setText(String.valueOf(chambre.getNombreJours()));
        NomclientTextField.setText(chambre.getNomClient());
        TelephoneTextField.setText(chambre.getTelephone());


        numChambreTextField.setDisable(false);
        DateEntrerSejour.setDisable(false);
        NombreJourstextField.setDisable(false);
        NomclientTextField.setDisable(false);
        TelephoneTextField.setDisable(false);
        ButtonMettreJours.setDisable(false);
    }

    public void  ClearANDactive(){

        numChambreTextField.setDisable(true);
        DateEntrerSejour.setDisable(true);
        NombreJourstextField.setDisable(true);
        NomclientTextField.setDisable(true);
        ButtonMettreJours.setDisable(true);
        TelephoneTextField.setDisable(true);
    }
    public  void  OnButtonUpdate(ActionEvent event) throws  Exception{
        DBConnection dbConnection =new DBConnection();
        OccuperController controller =new OccuperController();
        String queryDelete ="Update `sejour`  set numChambre= ?,dateEntreSejour=? ,nombreJours=?,nomClient=? ,telephone=?  where idSejour= ?";
        con = dbConnection.getConnection("hotel","root","");

        if (controller.confirmation("Modifier")){
            try {
                if (this.numchambre != null){
                    st = con.prepareStatement(queryDelete);
                    st.setString(1,numChambreTextField.getText());
                    st.setDate(2, java.sql.Date.valueOf(DateEntrerSejour.getValue()));
                    st.setInt(3, Integer.parseInt(NombreJourstextField.getText()));
                    st.setString(4,this.NomclientTextField.getText());
                    st.setString(5,this.TelephoneTextField.getText());
                    st.setInt(6,this.idSejour);
                    st.executeUpdate();
                    ClearANDactive();
                    showSejourList();
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String queryUpdate2 = "UPDATE solde SET SoldeActuel = SoldeActuel + (SELECT PrixNuite FROM chambre WHERE numChambre = ?) * (? - ?)";
            try (PreparedStatement statement = con.prepareStatement(queryUpdate2)) {
                statement.setString(1, this.numchambre);
                statement.setInt(2, Integer.parseInt(NombreJourstextField.getText()));
                statement.setInt(3, this.NombreJours);
                statement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void OnBtnDeleteSejour(ActionEvent actionEvent) {

        DBConnection dbConnection =new DBConnection();
        OccuperController controller =new OccuperController();
        con = dbConnection.getConnection("hotel","root","");
        if (controller.confirmation("Modifier")) {
            String queryDeleteSejour = "Delete from `sejour` where idSejour= ?";
            con = dbConnection.getConnection("hotel", "root", "");
            try {
                if (this.idSejour != null) {
                    JOptionPane.showMessageDialog(null, "Voulez-Vous Vraiment supprimer?");
                    st = con.prepareStatement(queryDeleteSejour);
                    st.setInt(1, this.idSejour);
                    st.executeUpdate();
                    ClearANDactive();
                    showSejourList();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            String queryUpdate = "UPDATE solde SET SoldeActuel = SoldeActuel - (SELECT PrixNuite FROM chambre WHERE numChambre = ?) * ?";
            try (PreparedStatement statement = con.prepareStatement(queryUpdate)) {
                statement.setString(1, this.numchambre );
                statement.setInt(2, this.NombreJours);
                statement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public  void ActualiserButton() throws  Exception{
        showSejourList();
//        ContentAllController contentAllController = new ContentAllController();
//        Label soldeIdLabel = new Label(); // Créez une instance de Label pour l'utiliser comme argument
//
//        try {
//            contentAllController.Soldeactuel(soldeIdLabel);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//// Utilisez la valeur mise à jour dans soldeIdLabel
//        String soldeText = soldeIdLabel.getText();
//        System.out.println("Solde actuel : " + soldeText);
        ReservationController rs = new ReservationController();
        rs.mettreAJourSoldeLabel();
    }
    public void showSejourList(){
        Query q= new Query();
        ObservableList<Sejour> list = q.getSejour();
        try{
            ColIdSejouner.setCellValueFactory(new PropertyValueFactory<Sejour, Integer>("IdSejour"));
            ColInumchambredSejour.setCellValueFactory(new PropertyValueFactory<Sejour,String >("NumChambre"));
            coldateEntrerSejour.setCellValueFactory(new PropertyValueFactory<Sejour, Date>("DateEntreSejour"));
            colNombresDejours.setCellValueFactory(new PropertyValueFactory<Sejour, Integer>("NombreJours"));
            colnomDeClient.setCellValueFactory(new PropertyValueFactory<Sejour, String>("nomClient"));
            colTelephone.setCellValueFactory(new PropertyValueFactory<Sejour, String>("telephone"));
            SejourView.setItems(list);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showSejourList();
    }
}
