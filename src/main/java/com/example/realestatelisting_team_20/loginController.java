package com.example.realestatelisting_team_20;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button login;
    @FXML
     private Button signup;
    Connection con;


    public void onClickLogin(ActionEvent event){
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection connection = DataBaseConnection.getConnection();
        try {
            pst = connection.prepareStatement("SELECT* FROM user WHERE username = ? AND password_hash = ?");
            pst.setString(1,"username");
            pst.setString(2,"password");
            rs = pst.executeQuery();
            if(rs.next()){
                FXMLLoader loader = new FXMLLoader(loginController.class.getResource("home.fxml"));
                Parent root = FXMLLoader.load(Objects.requireNonNull(loginController.class.getResource("home.fxml")));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Home page...!");
                stage.setScene(new Scene(root,600,400));
                stage.show();
            }
            else{
                Alert error = new Alert(Alert.AlertType.ERROR,"Invalid username/Password...!", ButtonType.OK);
                error.show();
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void onClicks(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(loginController.class.getResource("signup.fxml"));
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(loginController.class.getResource("sighup.fxml")));
        } catch (IOException e) {
            e.fillInStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Sign Up...!");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
             login.setOnAction(this::onClickLogin);
             signup.setOnAction(this::onClicks);
    }
}
