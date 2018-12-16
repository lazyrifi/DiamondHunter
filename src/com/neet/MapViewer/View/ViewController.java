package com.neet.MapViewer.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import com.neet.MapViewer.Map;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * This class represents the controller of the GUI elements
 * and contain methods that run when the user interacts with
 * the GUI.
 */
public class ViewController {
	
	/**
	 * Size of a tile in Diamond Hunter in pixels
	 */
	private int tileSize;
	
	/**
	 * Holds reference to a Map object 
	 * which is the model class for the application
	 */
	private Map map;
	
	/**
	 * Graphics object of the Canvas, used to draw map
	 */
	private GraphicsContext canvasG;
	
	/**
	 * Currently selected item;
	 */
	private int selectedItem;
	
	
	// Variables for the GUI elements, automatically injected by the FXMLLoader
	@FXML private Canvas canvas_map;
	@FXML private ImageView iv_item;
	@FXML private RadioButton radio_axe, radio_boat;
	
	
	
	
	
	/**
	 * Creates and initializes the Map model object
	 * by passing the locations of resources to it
	 */
	public ViewController() {
		tileSize = 16;
		map = new Map(tileSize, "/Tilesets/testtileset.gif", "/Sprites/items.gif", "/Maps/testmap.map");
	}
	
	
	/**
	 * This method sets up GUI elements after they have been injected.
	 * The Graphics object of the canvas is extracted and stored in 
	 * {@link ViewController#canvasG}. The canvas is then updated to show 
	 * the map. The Axe item is set as the default selected tool.
	 * The application tries to automatically read the current locations of
	 * the items stored in the locations file and update the map accordingly.
	 */
	public void initialize() {
		canvasG = canvas_map.getGraphicsContext2D();
		OnSelectItem();
		
		try {
			File file = new File("itemLocations.txt");
			if (file.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String[] tokens = br.readLine().split(" ");
				int x = Integer.parseInt(tokens[0]);
				int y = Integer.parseInt(tokens[1]);
				
				tokens = br.readLine().split(" ");
				int a = Integer.parseInt(tokens[0]);
				int b = Integer.parseInt(tokens[1]);
				
				br.close();
				
				map.setItem(Map.AXE, x, y);
				map.setItem(Map.BOAT, a, b);
				UpdateMap();
			}
			else {
				file.createNewFile();
				OnClickDefault();
				OnClickSaveLocation();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void UpdateMap() {
		canvasG.drawImage(map.getMap(), 0, 0);
	}
	
	
	
	/**
	 * This method is called when the user clicks on an item radio button.
	 */
	public void OnSelectItem() {
		if (radio_axe.isSelected()) {
			iv_item.setImage(map.getItem(Map.AXE));		// sets image of currently selected item to Axe
			selectedItem = Map.AXE;						// sets current item to axe
		}
		else {
			iv_item.setImage(map.getItem(Map.BOAT));	// same for boat
			selectedItem = Map.BOAT;					//
		}
	}


	/**
	 * This method sets the location of the currently selected item
	 * to where the user clicks on the canvas
	 * @param e
	 */
	public void OnClickSetLocation(MouseEvent e) {
		int x = (int) e.getX() / tileSize;	// get X and Y coordinates of where user clicked
		int y = (int) e.getY() / tileSize;	// in terms of tile number
		map.setItem(selectedItem, x, y);
		UpdateMap();
	}
	
	
	/**
	 * This method saves the location of the items set by the user.
	 * If the user has not changed the location, the application
	 * will save the locations as they were loaded. 
	 */
	public void OnClickSaveLocation() {
		File file = new File("itemLocations.txt");
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			
			PrintWriter writer;
			writer = new PrintWriter(file);
			writer.println(map.getAxeLocation());
			writer.println(map.getBoatLocation());
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  
  
	public void OnClickDefault() {
		map.setItem(Map.AXE, 37, 26);
		map.setItem(Map.BOAT, 4, 12);
		UpdateMap();

  
	public void OnClickStartGame() {
		
	}
	
	
	/**
	 * This method refreshes the canvas.
	 */
	private void UpdateMap() {
	  canvasG.drawImage(map.getMap(), 0, 0);
	}	
}