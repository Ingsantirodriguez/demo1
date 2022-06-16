package com.example.fakemon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class ControladorFightScene extends Controlador implements Initializable {

    public ImageView userFakemon;
    public ImageView botFakemon;
    public Button Batalla;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //selecRandomFakemon();
        URL pathImg = getClass().getResource("images/batalla.png");
        Image battleImg = new Image(String.valueOf(pathImg),152, 50, false, true);
        Batalla.setGraphic(new ImageView(battleImg));

        sonido.stopMusic();
        battle.selecRandomFakemon();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                this.configUsrFakemon();
                this.configBotFakemon();
            }
            private void configUsrFakemon() {
                String usfakemon= battle.getUsrFakemon().getImgPath();
                Path imageFile = Paths.get(usfakemon);
                try {
                    userFakemon.setImage(new Image(imageFile.toUri().toURL().toExternalForm()));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                sonido.playMusic(battle.getUsrFakemon());
                try{
                    TimeUnit.SECONDS.sleep(3);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                sonido.stopMusic();
            }

            private void configBotFakemon(){
                String botfakemon= battle.getBotFakemon().getImgPath();
                Path imgFile = Paths.get(botfakemon);

                try {
                    botFakemon.setImage(new Image(imgFile.toUri().toURL().toExternalForm()));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                sonido.playMusic(battle.getBotFakemon());
                try{
                    TimeUnit.SECONDS.sleep(3);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                sonido.stopMusic();
            }


        });
        t.start();
    }

    public void config(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/fightScene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void irAtras(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/characterSelection.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void irABatalla(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Batalla.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    public void salir(ActionEvent event) {
        System.exit(0);
    }


}
