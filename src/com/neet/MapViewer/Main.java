package com.neet.MapViewer;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{
	
	private Stage primaryStage;
	private Scene primaryScene;
	
	@Override
	public void start(Stage args) throws Exception {
		primaryStage = args;
		primaryStage.setTitle("MapViewer");
		
		initMainScene();
		
		primaryStage.setScene(primaryScene);
		primaryStage.show();
	}
	
	
	private void initMainScene() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("View/view_main.fxml"));
			BorderPane pane = (BorderPane) loader.load();
			primaryScene = new Scene(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
