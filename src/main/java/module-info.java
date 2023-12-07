module com.example.realestatelisting_team_20 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    opens com.example.realestatelisting_team_20 to javafx.fxml;
    exports com.example.realestatelisting_team_20;
}