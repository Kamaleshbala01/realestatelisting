package com.example.realestatelisting_team_20;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
     private Button signup;
    @FXML
     private AnchorPane loginAnchor;


    public void onClickLogin(ActionEvent event){
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection connection = DataBaseConnection.getConnection();
        try {
            pst = connection.prepareStatement("SELECT * FROM user WHERE userName = ? AND password_hash = ?");
            pst.setString(1,username.getText());
            pst.setString(2,password.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                    Parent root = (Parent) loader.load();
                    Stage stage= (Stage) login.getScene().getWindow();
                    stage.setScene(new Scene(root));
                }
                catch (IOException e){
                    System.out.println(e);
                }
            }
            else{
                Alert error = new Alert(Alert.AlertType.ERROR,"Invalid username/Password...!", ButtonType.OK);
                error.show();
            }
        } catch (SQLException e) {
               System.out.println(e);
        }
    }
    public void onClicks(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage= (Stage) signup.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
        catch (IOException e){
            System.out.println(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
             login.setOnAction(this::onClickLogin);
             signup.setOnAction(this::onClicks);
    }
}
