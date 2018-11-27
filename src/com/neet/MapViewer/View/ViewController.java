package com.neet.MapViewer.View;

import com.neet.MapViewer.MapView;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

public class ViewController {
	
	private MapView mapView;
	
	@FXML
	private Canvas canvas_map;
	private GraphicsContext canvasG;
	
	@FXML 
	private Canvas canvas_item;
	
	@FXML
	private RadioButton radio_axe, radio_boat;
	
	@FXML
	private Button button_setLocation;
	
	@FXML
	private Button button_saveLocation;
	
	@FXML 
	private Button button_zoomIn;
	
	@FXML 
	private Button button_zoomOut;
	
	public ViewController() {
		mapView = new MapView(16, "/Tilesets/testtileset.gif", "/Maps/testmap.map");
	}
	
	public void initialize() {
		canvasG = canvas_map.getGraphicsContext2D();
		mapView.drawMap(canvasG);
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
