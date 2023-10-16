package org.mai.obstanovki;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreen extends Application implements Runnable{


    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainScreen.class.getResource("MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 801, 544);
        primaryStage.setScene(scene);
        //String css = this.getClass().getResource("Menu-Item.css").toExternalForm();
        primaryStage.setTitle("obstanovka");
        primaryStage.getIcons().add(new Image("C:\\Users\\vledb\\IdeaProjects\\obstanovki\\src\\main\\resources\\images\\Rocket-PNG-HD.png"));
        primaryStage.show();
    }


    public void run(){
        launch();
    }
}