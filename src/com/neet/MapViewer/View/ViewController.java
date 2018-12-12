package com.neet.MapViewer.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import com.neet.MapViewer.Map;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

public class ViewController {
	
	private Map map;
	private GraphicsContext canvasG;
	
	@FXML private Canvas canvas_map, canvas_item;
	@FXML private RadioButton radio_axe, radio_boat;
	@FXML private Button button_setLocation, button_saveLocation;
	
	
	public ViewController() {
		map = new Map(16, "/Tilesets/testtileset.gif", "/Sprites/items.gif", "/Maps/testmap.map");
	}
	
	public void initialize() {
		canvasG = canvas_map.getGraphicsContext2D();
		map.drawMap(canvasG);
	}
	
	public void OnSelectItem() {
		
	}
	
	public void OnClickSetLocation() {
		
	}
	
	public void OnClickSaveLocation() {
		File file = new File("Resources/locations.txt");
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
}
