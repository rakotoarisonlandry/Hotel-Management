    package com.landry.hotel.Controllers;

    import com.landry.hotel.DB.DBConnection;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
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
                Soldeactuel(soldeIdLabel);
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
    //    public void Soldeactuel(Label soldeIdLabel) throws SQLException {
    //        Connection conn = null;
    //        try {
    //            DBConnection dbConnection = new DBConnection();
    //            conn = dbConnection.getConnection("hotel", "root", "");
    //
    //            ResultSet rs = conn.createStatement().executeQuery("SELECT `SoldeActuel` FROM solde WHERE idSolde = 1");
    //
    //            if (rs.next()) {
    //                double soldeActuel = rs.getDouble(1); // Récupérer le résultat en tant que double
    //                System.out.println(soldeActuel);
    //                soldeIdLabel.setText(String.valueOf(soldeActuel) + " Ariary"); // Convertir le double en chaîne de caractères pour l'affichage
    //                System.out.println(soldeIdLabel);
    //            }
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        } finally {
    //            // Fermer la connexion
    //            if (conn != null) {
    //                conn.close();
    //            }
    //        }
    //    }

        public String Soldeactuel(Label soldeIdLabel) throws SQLException {
            Connection conn = null;
            try {
                DBConnection dbConnection = new DBConnection();
                conn = dbConnection.getConnection("hotel", "root", "");

                ResultSet rs = conn.createStatement().executeQuery("SELECT `SoldeActuel` FROM solde WHERE idSolde = 1");

                if (rs.next()) {
                    double soldeActuel = rs.getDouble("SoldeActuel");
                    String soldeText = String.valueOf(soldeActuel) + " Ariary";
                     soldeIdLabel.setText(soldeText);
                    // System.out.println(soldeText);
                    return soldeText;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
            return null; // Ajout d'un retour par défaut au cas où aucun solde n'est trouvé
        }


//        public void Soldeactuel(Label soldeIdLabel) throws SQLException {
//            Connection conn = null;
//            try {
//                DBConnection dbConnection = new DBConnection();
//                conn = dbConnection.getConnection("hotel", "root", "");
//
//                ResultSet rs = conn.createStatement().executeQuery("SELECT `SoldeActuel` FROM solde WHERE idSolde = 1");
//
//                if (rs.next()) {
//                    double soldeActuel = rs.getDouble(1); // Récupérer le résultat en tant que double
//                    System.out.println(soldeActuel);
//                    soldeIdLabel.textProperty().set(String.valueOf(soldeActuel) + " Ariary"); // Modifier la propriété text de soldeIdLabel
//                    System.out.println(soldeIdLabel);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                // Fermer la connexion
//                if (conn != null) {
//                    conn.close();
//                }
//            }
//        }

    }

