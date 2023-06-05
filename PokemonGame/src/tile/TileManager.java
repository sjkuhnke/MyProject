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
		
		setup(49, true);
		setup(50, true);
		setup(51, true);
		setup(52, true);
		setup(53, true);
		setup(54, true);
		setup(55, true);
		setup(56, true);
		setup(57, true);
		setup(58, true);
		setup(59, false);
		setup(60, true);
		setup(61, true);
		setup(62, true);
		setup(63, true);
		setup(64, true);
		setup(65, true);
		setup(66, true);
		setup(67, true);
		setup(68, true);
		setup(69, true);
		setup(70, true);
		setup(71, true);
		setup(72, true);
		setup(73, true);
		setup(74, true);
		setup(75, true);
		setup(76, true);
		setup(77, true);
		setup(78, true);
		setup(79, false);
		setup(80, true);
		setup(81, true);
		setup(82, true);
		setup(83, true);
		setup(84, true);
		setup(85, true);
		setup(86, true);
		setup(87, true);
		setup(88, true);
		setup(89, true);
		setup(90, true);
		setup(91, true);
		setup(92, false);
		setup(93, true);
//		setup(94, true);
//		setup(95, true);
//		setup(96, true);
//		setup(97, true);
//		setup(98, true);
//		setup(99, true);
//		setup(100, true);
		try {
			tile[7] = new GrassTile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/007.png"));
			
			tile[37] = new Tile();
			tile[37].image = ImageIO.read(getClass().getResourceAsStream("/tiles/037.png"));
			tile[37].collisionDirection = "down";
			tile[37].collision = true;
			
			tile[38] = new Tile();
			tile[38].image = ImageIO.read(getClass().getResourceAsStream("/tiles/038.png"));
			tile[38].collisionDirection = "down";
			tile[38].collision = true;
			
			tile[39] = new Tile();
			tile[39].image = ImageIO.read(getClass().getResourceAsStream("/tiles/039.png"));
			tile[39].collisionDirection = "down";
			tile[39].collision = true;
			
			tile[40] = new Tile();
			tile[40].image = ImageIO.read(getClass().getResourceAsStream("/tiles/040.png"));
			tile[40].collisionDirection = "up";
			tile[40].collision = true;
			
			tile[41] = new Tile();
			tile[41].image = ImageIO.read(getClass().getResourceAsStream("/tiles/041.png"));
			tile[41].collisionDirection = "up";
			tile[41].collision = true;
			
			tile[42] = new Tile();
			tile[42].image = ImageIO.read(getClass().getResourceAsStream("/tiles/042.png"));
			tile[42].collisionDirection = "up";
			tile[42].collision = true;
			
			tile[43] = new Tile();
			tile[43].image = ImageIO.read(getClass().getResourceAsStream("/tiles/043.png"));
			tile[43].collisionDirection = "right";
			tile[43].collision = true;
			
			tile[44] = new Tile();
			tile[44].image = ImageIO.read(getClass().getResourceAsStream("/tiles/044.png"));
			tile[44].collisionDirection = "right";
			tile[44].collision = true;
			
			tile[45] = new Tile();
			tile[45].image = ImageIO.read(getClass().getResourceAsStream("/tiles/045.png"));
			tile[45].collisionDirection = "right";
			tile[45].collision = true;
			
			tile[46] = new Tile();
			tile[46].image = ImageIO.read(getClass().getResourceAsStream("/tiles/046.png"));
			tile[46].collisionDirection = "left";
			tile[46].collision = true;
			
			tile[47] = new Tile();
			tile[47].image = ImageIO.read(getClass().getResourceAsStream("/tiles/047.png"));
			tile[47].collisionDirection = "left";
			tile[47].collision = true;
			
			tile[48] = new Tile();
			tile[48].image = ImageIO.read(getClass().getResourceAsStream("/tiles/048.png"));
			tile[48].collisionDirection = "left";
			tile[48].collision = true;
			
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
			if (collision) {
				tile[index].collisionDirection = "all";
			}
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
