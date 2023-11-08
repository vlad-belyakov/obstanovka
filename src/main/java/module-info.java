module org.mai.obstanovki {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires spring.context;
    requires spring.beans;


    opens org.mai.obstanovki to javafx.fxml;
    exports org.mai.obstanovki;
}