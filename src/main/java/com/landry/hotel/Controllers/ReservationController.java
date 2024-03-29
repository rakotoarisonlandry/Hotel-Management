

package com.landry.hotel.Controllers;

    import  com.landry.hotel.DB.DBConnection;
    import com.landry.hotel.DB.Query;
    import com.landry.hotel.Models.Reservation;
    import javafx.collections.ObservableList;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.control.Label;


    import javafx.fxml.Initializable;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
    import javafx.scene.control.cell.PropertyValueFactory;
    import javafx.scene.input.MouseEvent;
    import javafx.stage.Stage;
    import javafx.stage.StageStyle;
    import java.net.URL;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.SQLException;
    import java.util.Date;
    import java.util.ResourceBundle;

    public class ReservationController implements Initializable {
        PreparedStatement st ;
        Connection con;
        Integer idReservation;

        Date dateReservation ;
        String numChambre;
        Integer NombreJours;
        public TableView <Reservation> ReservationView;
        public TableColumn <Reservation,Integer> ColIdReservation;
        public TableColumn <Reservation,String>colNumChambre;
        public TableColumn <Reservation, Date> colDateR;
        public TableColumn <Reservation,Date> colDateEntre;
        public TableColumn <Reservation,Integer> colNbJour;
        public TableColumn <Reservation,String>colNom;
        public TableColumn <Reservation,String> colMail;

        @FXML
        public Button ajout;

        @FXML
        public TextField NumChambreTextField;

        @FXML
        public DatePicker DateReservation;

        @FXML
        public DatePicker DateEntrer;

        @FXML
        public TextField NombreJoursTextField;
        public TextField NomClientTextField;
        @FXML
        public TextField MailClientTextField;





        @FXML
        public void ajoutbutton(ActionEvent actionEvent) throws Exception{
            try {
                Parent root  = FXMLLoader.load(getClass().getResource("/FXML/AjoutReservation.fxml"));
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

        public void showReservationList(){
            Query q= new Query();
            ObservableList<Reservation> list = q.getReservationList();
            try{
                ColIdReservation.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("idReservation"));
                colNumChambre.setCellValueFactory(new PropertyValueFactory<Reservation, String>("numChambre"));
                colDateR.setCellValueFactory(new PropertyValueFactory<Reservation,Date>("dateReservation"));
                colDateEntre.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("dateEntrer"));
                colNbJour.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("nombreJours"));
                colNom.setCellValueFactory(new PropertyValueFactory<Reservation, String>("nomClient"));
                colMail.setCellValueFactory(new PropertyValueFactory<Reservation,String>("mail"));
                ReservationView.setItems(list);
            }catch(Exception e){
                e.printStackTrace();
            }

        }

        @FXML
        public  Button ButtonMiseAJours;
        @FXML
        public void getData(MouseEvent event) {
            Reservation reservation = ReservationView.getSelectionModel().getSelectedItem();


            this.idReservation = reservation.getIdReservation();
            this.numChambre = reservation.getNumChambre();
            this.dateReservation = reservation.getDateReservation();
            this.NombreJours = reservation.getNombreJours();
            NumChambreTextField.setText(reservation.getNumChambre());
            DateReservation.setValue(reservation.getDateReservation().toLocalDate());
            DateEntrer.setValue(reservation.getDateEntrer().toLocalDate());
            NombreJoursTextField.setText(String.valueOf(reservation.getNombreJours()));
            NomClientTextField.setText(reservation.getNomClient());
            MailClientTextField.setText(reservation.getMail());

            NumChambreTextField.setDisable(false);
            DateReservation.setDisable(false);
            DateEntrer.setDisable(false);
            NombreJoursTextField.setDisable(false);
            NomClientTextField.setDisable(false);
            MailClientTextField.setDisable(false);
            ButtonMiseAJours.setDisable(false);
        }

        public void  ClearANDactive(){

            NumChambreTextField.setDisable(true);
            DateReservation.setDisable(true);
            DateEntrer.setDisable(true);
            NombreJoursTextField.setDisable(true);
            NomClientTextField.setDisable(true);
            MailClientTextField.setDisable(true);
            ButtonMiseAJours.setDisable(true);
        }
        public void OnUpdateButton(ActionEvent event){
            DBConnection dbConnection =new DBConnection();
            OccuperController controller = new OccuperController();
            String queryUpdate ="Update `reservation` set numChambre=?, dateReservation= ?, dateEntrer=?,nombreJours=?,nomClient=?,mail=? where idReservation= ?";
            con = dbConnection.getConnection("hotel","root","");
            if (controller.confirmation("Modifier")){
            try {
                if (this.idReservation != null){
                    st= con.prepareStatement(queryUpdate);
                    st.setString(1,NumChambreTextField.getText());
                    st.setDate(2, java.sql.Date.valueOf(DateReservation.getValue()));
                    st.setDate(3, java.sql.Date.valueOf(DateEntrer.getValue()));
                    st.setInt(4, Integer.parseInt(NombreJoursTextField.getText()));
                    st.setString(5,NomClientTextField.getText());
                    st.setString(6,MailClientTextField.getText());
                    st.setInt(7,this.idReservation);
                    ClearANDactive();
                    st.executeUpdate();
                    showReservationList();
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }

                String queryUpdate2 = "UPDATE solde SET SoldeActuel = SoldeActuel + (SELECT PrixNuite FROM chambre WHERE numChambre = ?) * (? - ?)";
                try (PreparedStatement statement = con.prepareStatement(queryUpdate2)) {
                    statement.setString(1, this.numChambre);
                    statement.setInt(2,Integer.parseInt(NombreJoursTextField.getText()));
                    statement.setInt(3, this.NombreJours);
                    statement.execute();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    @FXML
    public void ActuliserButtonChambre(ActionEvent event) throws Exception {
        showReservationList();
        mettreAJourSoldeLabel();
    }


        public void mettreAJourSoldeLabel() {
            ContentAllController soldeManager = new ContentAllController();
            Label soldeLabel = new Label();

            try {
                double soldeActuel = soldeManager.Soldeactuel();
                String soldeText = String.valueOf(soldeActuel) + " Ariary";
                soldeLabel.setText(soldeText);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void OnBtnDeletereservation(ActionEvent actionEvent) {
            DBConnection dbConnection = new DBConnection();
            OccuperController controller = new OccuperController();
            String queryDelete = "DELETE FROM `reservation` WHERE numChambre = ? AND dateReservation =?";
            con = dbConnection.getConnection("hotel", "root", "");
            if (controller.confirmation("Modifier")) {
                try {
                    if (this.idReservation != null) {
                        st = con.prepareStatement(queryDelete);
                        st.setString(1, this.numChambre);
                        st.setDate(2, (java.sql.Date) this.dateReservation);
                        st.executeUpdate();
                        showReservationList();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String queryUpdate = "UPDATE solde SET SoldeActuel = SoldeActuel - (SELECT PrixNuite FROM chambre WHERE numChambre = ?) * ?";
                try (PreparedStatement statement = con.prepareStatement(queryUpdate)) {
                    statement.setString(1, this.numChambre);
                    statement.setInt(2, this.NombreJours);
                    statement.execute();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            showReservationList();
        }
    }
