package com.neet.MapViewer.View;

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
	@FXML private Button button_setLocation, button_saveLocation, button_zoomIn, button_zoomOut;
	
	
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
		
	}
	
	public void OnClickZoomIn() {
		
	}
	
	public void OnClickZoomOut() {
		
	}
	
	}
