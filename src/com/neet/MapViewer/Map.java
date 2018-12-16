package com.neet.MapViewer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;


public class Map {
	private int tileSize;
	
	private int MapWidth; // number of tiles across map
	private int MapHeight; // number of tiles along map
	
	private BufferedImage[] tiles;
	private BufferedImage axe;
	private BufferedImage boat;
	private int[][] map;
	
	private BufferedImage mapImage;
	private Graphics mapG;
	
	public static final int AXE = 0;
	public static final int BOAT = 1;
	
	private int AxeX;
	private int AxeY;
	private int BoatX;
	private int BoatY;
	
	
	
	public Map(int tileSize, String tilesLocation, String itemsLocation, String mapLocation) {
		this.tileSize = tileSize;
		loadTiles(tilesLocation);
		loadItems(itemsLocation);
		loadMap(mapLocation);
		
		mapImage = new BufferedImage(MapWidth*tileSize, MapHeight*tileSize, BufferedImage.TYPE_INT_ARGB);
		drawMap();
		
		AxeX = 20;
		AxeY = 20;
		BoatX = 19;
		BoatY = 19;
	}
	
	
	private void loadTiles(String location) {
		BufferedImage temp;
		try {
			temp = ImageIO.read(getClass().getResourceAsStream(location));
			
			int numTilesAcross = temp.getWidth() / tileSize;
			int numTilesAlong = temp.getHeight() / tileSize;
			tiles = new BufferedImage[numTilesAcross * numTilesAlong];
			
			int tilenum = 0;
			for (int i = 0; i < numTilesAlong; i++) {
				for (int j = 0; j < numTilesAcross; j++) {
					tiles[tilenum] = temp.getSubimage(j*tileSize, i*tileSize, tileSize, tileSize);
					tilenum++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void loadItems(String location) {
		BufferedImage items;
		try {
			items = ImageIO.read(getClass().getResourceAsStream(location));
			
			axe = items.getSubimage(16, 16, tileSize, tileSize);
			boat = items.getSubimage(0, 16, tileSize, tileSize);
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
			
			map = new int[MapWidth][MapHeight];
			
			for (int i = 0; i < MapHeight; i++) {
				String line = br.readLine();
				String[] tokens = line.split("\\s+");
				for (int j = 0; j < MapWidth; j++) {
					map[i][j] = Integer.parseInt(tokens[j]);
				}
				System.out.println();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void drawMap() {
		mapG = mapImage.getGraphics();
		for (int i=0; i<MapWidth; i++) {
			for (int j=0; j<MapHeight; j++) {
				mapG.drawImage(tiles[map[j][i]], i*tileSize, j*tileSize, null);
			}
		}
	}
	
	
	public Image getMap() {
		return SwingFXUtils.toFXImage(mapImage, null);
	}
	
	
	public Image getItem(int item) {
		switch(item) {
			case AXE:
				return SwingFXUtils.toFXImage(axe, null);
			case BOAT:
				return SwingFXUtils.toFXImage(boat, null);
		}
		return null; 
	}
	
	
	public String getAxeLocation() {
		return AxeX + " " + AxeY;
	}
	
	
	public String getBoatLocation() {
		return BoatX + " " + BoatY;
	}
	
	
	public void setItem(int item, int x, int y) {
		switch(item) {
			case AXE:
				mapG.drawImage(tiles[map[AxeY][AxeX]], AxeX*tileSize, AxeY*tileSize, null);
				AxeX = x;
				AxeY = y;
				mapG.drawImage(axe, AxeX*tileSize, AxeY*tileSize, null);
				break;
			case BOAT:
				mapG.drawImage(tiles[map[BoatY][BoatX]], BoatX*tileSize, BoatY*tileSize, null);
				BoatX = x;
				BoatY = y;
				mapG.drawImage(boat, BoatX*tileSize, BoatY*tileSize, null);
				break;
			default:
				break;
		}
	}
	
	
	public boolean validTile(int x, int y) {
		return map[y][x] < 20;
	}
}
