    package com.landry.hotel.Controllers;

    import com.landry.hotel.DB.DBConnection;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import javafx.scene.Parent;
    import javafx.scene.control.Label;
    import javafx.scene.layout.StackPane;
    import java.io.IOException;
    import java.net.URL;
    import java.sql.*;
    import java.util.ResourceBundle;
    import java.util.logging.Level;
    import java.util.logging.Logger;

    public class ContentAllController implements Initializable {
        Connection con;
        PreparedStatement st = null;
        @FXML
        public StackPane ContentArea;
        @FXML
        public Label soldeIdLabel;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            try {
                Soldeactuel();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                Parent fxml  = FXMLLoader.load(getClass().getResource("/FXML/Chambre.fxml"));
                ContentArea.getChildren().removeAll();
                ContentArea.getChildren().setAll(fxml);
            }catch (IOException ex){
                Logger.getLogger(ContentAllController.class.getName()).log(Level.SEVERE ,null,ex);
            }
        }

        public  void Chambre (javafx.event.ActionEvent actionEvent) throws  IOException{
            Parent fxml =FXMLLoader.load(getClass().getResource("/FXML/Chambre.fxml"));
            ContentArea.getChildren().removeAll();
            ContentArea.getChildren().setAll(fxml);
        }

        public  void Reservation (javafx.event.ActionEvent actionEvent) throws  IOException{
            Parent fxml =FXMLLoader.load(getClass().getResource("/FXMl/Reservation.fxml"));
            ContentArea.getChildren().removeAll();
            ContentArea.getChildren().setAll(fxml);
        }

        public  void Sejouner (javafx.event.ActionEvent actionEvent) throws  IOException{
            Parent fxml =FXMLLoader.load(getClass().getResource("/FXML/Sejour.fxml"));
            ContentArea.getChildren().removeAll();
            ContentArea.getChildren().setAll(fxml);
        }

        public  void Occuper (javafx.event.ActionEvent actionEvent) throws  IOException{
            Parent fxml =FXMLLoader.load(getClass().getResource("/FXML/Occuper.fxml"));
            ContentArea.getChildren().removeAll();
            ContentArea.getChildren().setAll(fxml);
        }

        public double Soldeactuel() throws SQLException {
            Connection conn = null;
            try {
                DBConnection dbConnection = new DBConnection();
                conn = dbConnection.getConnection("hotel", "root", "");

                ResultSet rs = conn.createStatement().executeQuery("SELECT `SoldeActuel` FROM solde WHERE idSolde = 1");

                if (rs.next()) {
                    return rs.getDouble("SoldeActuel");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
            return 0.0; // Ajout d'un retour par défaut au cas où aucun solde n'est trouvé
        }

    }

