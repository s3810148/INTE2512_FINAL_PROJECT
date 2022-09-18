package com.example.inte2512finalproject;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SwitchToScene {

	public void switchToAnotherScene(Event event, String urlFile) {
		Parent root;
		Scene scene;
		Stage stage;
		try {
			root = FXMLLoader.load(getClass().getResource(urlFile));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
