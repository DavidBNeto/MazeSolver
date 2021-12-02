package com.iadlpc.mazesolver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication {

    public static void main(String[] args) {
        new Game(true).play("C:\\Users\\david.bertrand\\Documents\\UNICRED\\mazeSolver\\maze");
    }
}