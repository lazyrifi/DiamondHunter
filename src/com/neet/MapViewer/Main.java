package com.neet.MapViewer;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
* The MapViewer program is a JavaFX application that
* allows a user a view the full map in the game Diamond Hunter
*/
public class Main extends Application{
	
	private Stage mainStage;
	private Scene mainScene;
	
	/**
	 * This method initializes the GUI window of the application, sets
	 * a title and calls the method to initialize the GUI elements 
	 * and their controller
	 */
	@Override
	public void start(Stage args) throws Exception {
		mainStage = args;
		mainStage.setTitle("MapViewer");	// sets title
		
		initMainScene();
		
		mainStage.setScene(mainScene);
		mainStage.show();					// show window on-screen
	}
	
	
	/**
	 * This method loads the FXML file containing the GUI elements of the app
	 * and sets it to the main scene that is displayed in the main stage
	 */
	private void initMainScene() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("View/view_main.fxml"));
			BorderPane pane = (BorderPane) loader.load();
			mainScene = new Scene(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Main method that launches the JavaFX application
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
