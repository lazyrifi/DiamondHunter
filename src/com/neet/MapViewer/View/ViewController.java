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
import javafx.scene.input.MouseEvent;

public class ViewController {
	
	private Map map;
	private GraphicsContext canvasG;
	
	@FXML private Canvas canvas_map, canvas_item;
	@FXML private RadioButton radio_axe, radio_boat;
	@FXML private Button button_setLocation, button_saveLocation;
	
	private int tileSize;
	
	
	public ViewController() {
		tileSize = 16;
		map = new Map(tileSize, "/Tilesets/testtileset.gif", "/Sprites/items.gif", "/Maps/testmap.map");
	}
	
	public void initialize() {
		canvasG = canvas_map.getGraphicsContext2D();
		map.drawMap(canvasG);
	}
	
	public void OnSelectItem() {
		
	}
	
	public void OnClickSetLocation(MouseEvent e) {
		int x = (int) e.getX() / tileSize;
		int y = (int) e.getY() / tileSize;
		if(radio_axe.isSelected()) {
			map.setItem(Map.AXE, x, y);
		}
		else {
			map.setItem(Map.BOAT, x, y);
		}
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
