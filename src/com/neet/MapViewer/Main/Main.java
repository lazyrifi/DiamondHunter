package com.neet.MapViewer.Main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{
	
	private Stage primaryStage;
	
	@Override
	public void start(Stage args) throws Exception {
		primaryStage = args;
		primaryStage.setTitle("MapViewer");
		
		Scene scene = new Scene(new BorderPane());
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
