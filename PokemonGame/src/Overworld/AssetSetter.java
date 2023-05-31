package Overworld;

import Entity.Entity;
import Entity.NPC_Clerk;
import Entity.NPC_GymLeader;
import Entity.NPC_Nurse;
import Entity.NPC_PC;
import Entity.NPC_Trainer;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {}
	
	public void setNPC() {
		gp.npc[0] = NPCSetup(1, 21, 21, -1);
		
		gp.npc[1] = NPCSetup(2, 25, 21, -1);
		
		gp.npc[2] = NPCSetup(3, 23, 24, 0);
		
		gp.npc[3] = NPCSetup(8, 23, 19, 14);
		
		gp.npc[4] = NPCSetup(0, 23, 7, -1);
	}
	
	private Entity NPCSetup(int type, int x, int y, int team) {
		Entity result = null;
		switch (type) {
		case 0:
			result = new NPC_PC(gp);
			break;
		case 1:
			result = new NPC_Nurse(gp);
			break;
		case 2:
			result = new NPC_Clerk(gp);
			break;
		case 3:
			result = new NPC_Trainer(gp, "down", team);
			break;
		case 4:
			result = new NPC_Trainer(gp, "up", team);
			break;
		case 5:
			result = new NPC_Trainer(gp, "left", team);
			break;
		case 6:
			result = new NPC_Trainer(gp, "right", team);
			break;
		case 8:
			result = new NPC_GymLeader(gp, "down", team);
		}
		
		result.worldX = gp.tileSize*x;
		result.worldY = gp.tileSize*y;
		
		return result;
	}
}
