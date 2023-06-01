package Overworld;

import java.awt.Rectangle;

import Entity.Entity;
import Entity.NPC_Trainer;
import tile.GrassTile;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
	    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
	    if (gp.tileM.tile[tileNum1] instanceof GrassTile || gp.tileM.tile[tileNum2] instanceof GrassTile) {
	        entity.inTallGrass = true;
	    } else {
	        entity.inTallGrass = false;
	    }
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) entity.collisionOn = true;
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) entity.collisionOn = true;
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) entity.collisionOn = true;
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) entity.collisionOn = true;
			break;
		}
	}
	
	public int checkEntity(Entity entity, Entity[] target) {
	    int index = 999;

	    for (int i = 0; i < target.length; i++) {
	        if (target[i] != null) {
	            Rectangle entityRange = new Rectangle(entity.worldX + entity.solidArea.x, entity.worldY + entity.solidArea.y, entity.solidArea.width, entity.solidArea.height);
	            Rectangle targetRange = new Rectangle(target[i].worldX + target[i].solidArea.x, target[i].worldY + target[i].solidArea.y, target[i].solidArea.width, target[i].solidArea.height);

	            switch (entity.direction) {
	                case "up":
	                    entityRange.y -= entity.speed;
	                    break;
	                case "down":
	                    entityRange.y += entity.speed;
	                    break;
	                case "left":
	                    entityRange.x -= entity.speed;
	                    break;
	                case "right":
	                    entityRange.x += entity.speed;
	                    break;
	            }

	            if (entityRange.intersects(targetRange)) {
	                entity.collisionOn = true;
	                index = i;
	            }
	        }
	    }
	    return index;
	}

	public boolean checkTrainer(Entity entity, Entity target) {
	    int visionRange = 4 * gp.tileSize;
	    boolean result = false;

	    if (target instanceof NPC_Trainer && target != null) {
	        Rectangle entityRange = new Rectangle(entity.worldX + entity.solidArea.x, entity.worldY + entity.solidArea.y, entity.solidArea.width, entity.solidArea.height);
	        Rectangle trainerRange = new Rectangle(target.worldX + target.solidArea.x, target.worldY + target.solidArea.y, target.solidArea.width, target.solidArea.height);

	        switch (target.direction) {
	            case "up":
	                trainerRange.y -= visionRange;
	                trainerRange.height += visionRange;
	                break;
	            case "down":
	                trainerRange.height += visionRange;
	                break;
	            case "left":
	                trainerRange.x -= visionRange;
	                trainerRange.width += visionRange;
	                break;
	            case "right":
	                trainerRange.width += visionRange;
	                break;
	        }

	        if (entityRange.intersects(trainerRange)) {
	            result = true;
	        }
	    }
	    return result;
	}



}
