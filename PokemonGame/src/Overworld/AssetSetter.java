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
		gp.npc[0] = NPCSetup(4, 72, 48, 0);
		
		gp.npc[1] = NPCSetup(5, 18, 18, 1);
		
		gp.npc[2] = NPCSetup(6, 23, 19, 2);
		
		gp.npc[3] = NPCSetup(6, 23, 27, 3);
		
		gp.npc[4] = NPCSetup(5, 21, 31, 4);
		
		gp.npc[5] = NPCSetup(1, 35, 45, -1);
		gp.npc[6] = NPCSetup(0, 36, 45, -1);
		gp.npc[7] = NPCSetup(1, 21, 11, -1);
		gp.npc[8] = NPCSetup(0, 22, 11, -1);
		
		gp.npc[9] = NPCSetup(2, 27, 54, -1);
		gp.npc[10] = NPCSetup(2, 20, 11, -1);
		
		gp.npc[11] = NPCSetup(6, 18, 48, 5);
		gp.npc[12] = NPCSetup(5, 6, 11, 6);
		gp.npc[13] = NPCSetup(6, 4, 10, 7);
		gp.npc[14] = NPCSetup(5, 6, 9, 8);
		gp.npc[15] = NPCSetup(6, 4, 8, 9);
		gp.npc[16] = NPCSetup(5, 6, 7, 10);
		gp.npc[17] = NPCSetup(6, 4, 6, 11);
		gp.npc[18] = NPCSetup(5, 6, 5, 12);
		gp.npc[19] = NPCSetup(8, 5, 3, 13);
		
		gp.npc[20] = NPCSetup(6, 38, 7, 14);
		gp.npc[21] = NPCSetup(5, 40, 6, 15);
		gp.npc[22] = NPCSetup(6, 38, 5, 16);
		gp.npc[23] = NPCSetup(8, 39, 2, 17);
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
