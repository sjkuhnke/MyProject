package Object;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Swing.Item;

public class Object {

	public BufferedImage image;
	public Item item;
	public int worldX, worldY;
	
	public Object() {
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/item.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
