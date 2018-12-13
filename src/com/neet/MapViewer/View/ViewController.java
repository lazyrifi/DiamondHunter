package com.neet.MapViewer.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import com.neet.MapViewer.Map;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ViewController {
	
	private Map map;
	private GraphicsContext canvasG;
	
	@FXML private Canvas canvas_map;
	@FXML private ImageView iv_item;
	@FXML private RadioButton radio_axe, radio_boat;
	@FXML private Button button_setLocation, button_saveLocation;
	
	private int tileSize;
	
	
	public ViewController() {
		tileSize = 16;
		map = new Map(tileSize, "/Tilesets/testtileset.gif", "/Sprites/items.gif", "/Maps/testmap.map");
	}
	
	public void initialize() {
		canvasG = canvas_map.getGraphicsContext2D();
		UpdateMap();
		OnSelectItem();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("Resources/locations.txt"));
			String[] tokens = br.readLine().split(" ");
			int x = Integer.parseInt(tokens[0]);
			int y = Integer.parseInt(tokens[1]);
			
			tokens = br.readLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			
			map.setItem(Map.AXE, x, y);
			map.setItem(Map.BOAT, a, b);
			UpdateMap();
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void OnSelectItem() {
		if (radio_axe.isSelected()) {
			iv_item.setImage(map.getItem(Map.AXE));
		}
		else iv_item.setImage(map.getItem(Map.BOAT));
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
		UpdateMap();
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
	
	private void UpdateMap() {
		canvasG.drawImage(map.getMap(), 0, 0);
	}
}
