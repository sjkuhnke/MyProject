package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Overworld.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[500];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/map01.txt");
	}
	
	public void getTileImage() {
		setup(0, true);
		setup(1, false);
		setup(2, true);
		setup(3, true);
		setup(4, false);
		setup(5, true);
		setup(6, false);
		setup(8, false);
		setup(9, false);
		setup(10, false);
		setup(11, false);
		setup(12, false);
		setup(13, false);
		setup(14, false);
		setup(15, false);
		setup(16, false);
		setup(17, false);
		setup(18, false);
		setup(19, false);
		setup(20, false);
		setup(21, false);
		setup(22, true);
		setup(23, true);
		setup(24, true);
		setup(25, true);
		setup(26, true);
		setup(27, true);
		setup(28, true);
		setup(29, true);
		setup(30, true);
		setup(31, true);
		setup(32, true);
		setup(33, true);
		setup(34, true);
		setup(35, true);
		setup(36, true);
		try {
			tile[7] = new GrassTile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/007.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setup(int index, boolean collision) {
		tile[index] = new Tile();
		String imageName = index + "";
		while (imageName.length() < 3) imageName = "0" + imageName;
		try {
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
			tile[index].collision = collision;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
					worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
					worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
					worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			
			worldCol++;
			
			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
