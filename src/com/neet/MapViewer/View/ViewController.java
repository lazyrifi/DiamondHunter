package com.neet.MapViewer.View;

import javafx.fxml.FXML;
import javafx.scene.canvas.*;

public class ViewController {
	
	@FXML
	private Canvas canvas_map;
	private GraphicsContext canvasG;
	
	public void initialize() {
		canvasG = canvas_map.getGraphicsContext2D();
		canvasG.fillRect(0, 0, 640, 640);
	}
}
