package com.example.realestatelisting_team_20;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class signupController implements Initializable {

    @FXML
    private PasswordField confirm;

    @FXML
    private TextField contact;

    @FXML
    private TextField email;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private Button signup;

    @FXML
    private AnchorPane signupAnchor;

    @FXML
    private TextField username;

    public void onClickSignup(ActionEvent event){
        if(username.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Enter username ...!");
            alert.show();
        }
        else if(email.getText() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Enter email ...!");
            alert.show();
        }
        else if(password.getText() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Enter password ...!");
            alert.show();
        }

        else if(contact.getText() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Enter contact number ...!");
            alert.show();
        }
        else if(!(password.getText()).equals(confirm.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Enter Confirm password as same as password ...!");
            alert.show();
        }
        else {
            PreparedStatement pst = null;
            ResultSet rs = null;
            Connection connection = DataBaseConnection.getConnection();
            try {
                pst = connection.prepareStatement("INSERT INTO USER (userName,EmailID,password_hash,contact_no) values(?,?,?,?)");
                pst.setString(1, username.getText());
                pst.setString(2, email.getText());
                pst.setString(3, password.getText());
                pst.setString(4, contact.getText());
                pst.executeUpdate();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                Stage stage = (Stage) signup.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));
            } catch (SQLException | IOException e) {
                System.out.println(e);
            }
        }

    }
    public void onClicks(ActionEvent Event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = (Stage) login.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
        catch (IOException e){
            System.out.println(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
             signup.setOnAction(this::onClickSignup);
             login.setOnAction(this::onClicks);
    }
}
