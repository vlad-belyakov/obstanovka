module org.mai.obstanovki {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;


    opens org.mai.obstanovki to javafx.fxml;
    exports org.mai.obstanovki;
}