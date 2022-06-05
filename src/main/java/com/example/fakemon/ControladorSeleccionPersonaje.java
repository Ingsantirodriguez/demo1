package com.example.fakemon;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorSeleccionPersonaje implements Initializable {
    public ImageView Bulbasaur;
    public ImageView Pikachu;
    public ImageView Charmander;
    public ImageView Pidgey;
    public ImageView Squirtle;
    public ImageView Jigglypuff;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void config(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fightScene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void irAtras(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("inicio.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    //Seleccionaste Bulbasaur
    public void Bulbasaur(ActionEvent event) throws IOException {
    System.out.println("Bulbasaur");
    config( event);
    }
    //Seleccionaste Pikachu
    public void Pikachu(ActionEvent event) throws IOException {
        System.out.println("Pikachu");
        config(event);
    }
    //Seleccionaste Charmander
    public void Charmander(ActionEvent event) throws IOException {
        System.out.println("Charmander");
        config(event);
    }
    //Seleccionaste Pidgey
    public void Pidgey(ActionEvent event) throws IOException {
        System.out.println("Pidgey");
        config(event);
    }
    //Seleccionaste Squirtle
    public void Squirtle(ActionEvent event) throws IOException {
        System.out.println("Squirtle");
        config(event);
    }
    //Seleccionaste Jigglypuff
    public void Jigglypuff(ActionEvent event) throws IOException {
        System.out.println("Jigglypuff");
        config(event);
    }





}