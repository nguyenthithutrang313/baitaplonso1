module com.example.dictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires freetts.jsapi10;
    requires freetts;
    requires java.sql;


    opens com.example.dictionary to javafx.fxml;
    exports com.example.dictionary;
}