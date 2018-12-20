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
	
	private int MapWidth; 	// number of tiles across map
	private int MapHeight;	// number of tiles along map
	
	private BufferedImage[] tiles;	// array of individual tiles
	private BufferedImage axe;		// sprite for axe
	private BufferedImage boat;		// sprite for boat
	private int[][] map;			// 2-dimensional array of integers representing the tile number in the map
	
	private BufferedImage mapImage;	// The map in image format
	private Graphics mapG;			// Graphics object to modify the map image
	
	public static final int AXE = 0;
	public static final int BOAT = 1;
	
	private int AxeX;	// x-location of axe
	private int AxeY;	// y-location of axe
	private int BoatX;	// x-location of boat
	private int BoatY;	// y-location of boat
	
	
	
	/**
	 * Constructor that sets up the map.
	 * Loads the tiles, map and item sprites and initializes the map image.
	 * @param tileSize
	 * @param tilesLocation
	 * @param itemsLocation
	 * @param mapLocation
	 */
	public Map(int tileSize, String tilesLocation, String itemsLocation, String mapLocation) {
		this.tileSize = tileSize;
		loadTiles(tilesLocation);
		loadItems(itemsLocation);
		loadMap(mapLocation);
		
		mapImage = new BufferedImage(MapWidth*tileSize, MapHeight*tileSize, BufferedImage.TYPE_INT_ARGB);
		drawMap();
	}
	
	
	
	/**
	 * This method loads the tile sprites.
	 * @param location Location of sprite image.
	 */
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
	
	
	
	/**
	 * This method loads the item sprites.
	 * @param location Location of item sprites image.
	 */
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

	
	
	/**
	 * This method loads the map numbers.
	 * @param location Location of the map file.
	 */
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
	
	
	
	/**
	 * This method draws the base map to an image.
	 */
	public void drawMap() {
		mapG = mapImage.getGraphics();
		for (int i=0; i<MapWidth; i++) {
			for (int j=0; j<MapHeight; j++) {
				mapG.drawImage(tiles[map[j][i]], i*tileSize, j*tileSize, null);
			}
		}
	}
	
	
	
	/**
	 * This method returns the rendered image of the map.
	 * @return Returns an image of the map.
	 */
	public Image getMap() {
		return SwingFXUtils.toFXImage(mapImage, null);
	}
	
	
	
	/**
	 * This method returns an image of the axe or boat, depending on user selection.
	 * @param item The item for which the sprite is wanted.
	 * @return The sprite image for the item.
	 */
	public Image getItem(int item) {
		switch(item) {
			case AXE:
				return SwingFXUtils.toFXImage(axe, null);
			case BOAT:
				return SwingFXUtils.toFXImage(boat, null);
		}
		return null; 
	}
	
	
	
	/**
	 * This method returns the location of the axe in string format.
	 * @return String containing locations of axe.
	 */
	public String getAxeLocation() {
		return AxeX + " " + AxeY;
	}
	
	
	
	/**
	 * This method returns the location of the boat in string format.
	 * @return String containing locations of boat.
	 */
	public String getBoatLocation() {
		return BoatX + " " + BoatY;
	}
	
	
	
	/**
	 * This method draws the item specified in a new location.
	 * @param item The item to be drawn.
	 * @param x The x-location of the item.
	 * @param y The y-location of the item.
	 */
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
	
	
	
	/**
	 * This method checks if the specified tile is a normal or blocked tile.
	 * @param x The x-location of the tile.
	 * @param y	The y-location of the tile.
	 * @return Boolean returns true if tile is normal.
	 */
	public boolean validTile(int x, int y) {
		return map[y][x] < 20;
	}
}
