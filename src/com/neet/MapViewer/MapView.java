package com.neet.MapViewer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class MapView {
	private int tileSize;
	
	private int MapWidth; // number of tiles across map
	private int MapHeight; // number of tiles along map
	
	private Image tiles;
	private int numTilesAcross;
	private int[][] Map;
	
	
	
	public MapView(int tileSize, String tilesLocation, String mapLocation) {
		this.tileSize = tileSize;
		loadTiles(tilesLocation);
		loadMap(mapLocation);
	}
	
	private void loadTiles(String location) {
		tiles = new Image(location);
		numTilesAcross = (int)tiles.getWidth()/tileSize;
	}

	
	private void loadMap(String location) {
		try {
			InputStream in = getClass().getResourceAsStream(location);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			MapWidth = Integer.parseInt(br.readLine());
			MapHeight = Integer.parseInt(br.readLine());
			
			Map = new int[MapWidth][MapHeight];
			
			for (int i = 0; i < MapHeight; i++) {
				String line = br.readLine();
				String[] tokens = line.split("\\s+");
				for (int j = 0; j < MapWidth; j++) {
					Map[i][j] = Integer.parseInt(tokens[j]);
				}
				System.out.println();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void drawMap(GraphicsContext g) {
		for (int i=0; i<MapHeight; i++) {
			for (int j=0; j<MapWidth; j++) {
				int tile = Map[i][j];
				int row = tile/numTilesAcross;
				int column = tile%numTilesAcross;
				g.drawImage(tiles, 
							column*tileSize, row*tileSize, tileSize, tileSize,
							j*tileSize, i*tileSize, tileSize, tileSize);
			}
		}
	}
}
