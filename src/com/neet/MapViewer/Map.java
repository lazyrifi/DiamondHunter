package com.neet.MapViewer;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Map {
	private int tileSize;
	
	private int MapWidth; // number of tiles across map
	private int MapHeight; // number of tiles along map
	
	private Image[] tiles;
	private int[][] Map;
	
	
	
	public Map(int tileSize, String tilesLocation, String mapLocation) {
		this.tileSize = tileSize;
		loadTiles(tilesLocation);
		loadMap(mapLocation);
	}
	
	private void loadTiles(String location) {
		BufferedImage temp;
		try {
			temp = ImageIO.read(getClass().getResourceAsStream(location));
			
			int numTilesAcross = temp.getWidth() / tileSize;
			int numTilesAlong = temp.getHeight() / tileSize;
			tiles = new Image[numTilesAcross * numTilesAlong];
			
			int tilenum = 0;
			for (int i = 0; i < numTilesAlong; i++) {
				for (int j = 0; j < numTilesAcross; j++) {
					Image tile = SwingFXUtils.toFXImage(temp.getSubimage(j*tileSize, i*tileSize, tileSize, tileSize), null);
					tiles[tilenum] = tile;
					tilenum++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
				Image tile = tiles[Map[i][j]];
				g.drawImage(tile, j*tileSize, i*tileSize);
			}
		}
	}
}
