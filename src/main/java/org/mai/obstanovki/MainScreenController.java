package org.mai.obstanovki;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainScreenController {

    @FXML
    public void initialize(){
        new ObstanovkaData().start();
    }

}