package com.landry.hotel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application{
    @Override
    public void start(Stage primarystage) throws Exception {
        FXMLLoader root=new FXMLLoader(getClass().getResource("/FXMl/Login.fxml"));
        primarystage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root.load());
        primarystage.setScene( scene);
        primarystage.show();
    }
    public static  void  main(String[] args){
        launch(args);
    }
}
