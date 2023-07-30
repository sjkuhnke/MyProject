package Overworld;

import Entity.Entity;
import Entity.NPC_Clerk;
import Entity.NPC_GymLeader;
import Entity.NPC_Nurse;
import Entity.NPC_PC;
import Entity.NPC_Trainer;

public class AssetSetter {

	GamePanel gp;
	int index;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
		index = 0;
	}
	
	public void setObject() {}
	
	public void setNPC() {
		int mapNum = 0;
		
		gp.npc[mapNum][index] = NPCSetup(4, 72, 48, 0);
		
		gp.npc[mapNum][index] = NPCSetup(5, 18, 18, 1);
		
		gp.npc[mapNum][index] = NPCSetup(6, 23, 19, 2);
		
		gp.npc[mapNum][index] = NPCSetup(6, 23, 27, 3);
		
		gp.npc[mapNum][index] = NPCSetup(5, 21, 31, 4);
		
		
		// Nurses/PCs
		gp.npc[1][index] = NPCSetup(1, 31, 37, -1);
		gp.npc[1][index] = NPCSetup(0, 35, 36, -1);
		gp.npc[5][index] = NPCSetup(1, 31, 37, -1);
		gp.npc[5][index] = NPCSetup(0, 35, 36, -1);
		
		// Clerks
		gp.npc[2][index] = NPCSetup(2, 27, 39, -1);
		gp.npc[6][index] = NPCSetup(2, 27, 39, -1);
		
		mapNum = 4;
		gp.npc[mapNum][index] = NPCSetup(4, 32, 62, 18);
		gp.npc[mapNum][index] = NPCSetup(4, 23, 65, 19);
		gp.npc[mapNum][index] = NPCSetup(4, 32, 68, 20);
		gp.npc[mapNum][index] = NPCSetup(4, 34, 75, 21);
		gp.npc[mapNum][index] = NPCSetup(4, 45, 75, 22);
		gp.npc[mapNum][index] = NPCSetup(4, 16, 70, 23);
		
		mapNum = 7;
		gp.npc[mapNum][index] = NPCSetup(6, 30, 42, 5);
		gp.npc[mapNum][index] = NPCSetup(5, 33, 42, 6);
		gp.npc[mapNum][index] = NPCSetup(3, 36, 39, 7);
		gp.npc[mapNum][index] = NPCSetup(4, 36, 42, 8);
		
		mapNum = 8;
		gp.npc[mapNum][index] = NPCSetup(5, 30, 39, 9);
		gp.npc[mapNum][index] = NPCSetup(4, 28, 41, 10);
		gp.npc[mapNum][index] = NPCSetup(3, 32, 39, 11);
		gp.npc[mapNum][index] = NPCSetup(4, 32, 45, 12);
		gp.npc[mapNum][index] = NPCSetup(8, 35, 41, 13);
		
		mapNum = 9;
		gp.npc[mapNum][index] = NPCSetup(5, 34, 42, 14);
		gp.npc[mapNum][index] = NPCSetup(6, 27, 39, 15);
		gp.npc[mapNum][index] = NPCSetup(5, 42, 34, 16);
		gp.npc[mapNum][index] = NPCSetup(8, 38, 28, 17);
		
		mapNum = 11;
		gp.npc[mapNum][index] = NPCSetup(4, 69, 65, 24);
		gp.npc[mapNum][index] = NPCSetup(4, 50, 72, 25);
		gp.npc[mapNum][index] = NPCSetup(4, 59, 74, 26);
		gp.npc[mapNum][index] = NPCSetup(4, 76, 68, 27);
		gp.npc[mapNum][index] = NPCSetup(3, 53, 59, 28);
		gp.npc[mapNum][index] = NPCSetup(4, 53, 65, 29);
		gp.npc[mapNum][index] = NPCSetup(4, 76, 59, 30);
		
		gp.npc[mapNum][index] = NPCSetup(4, 43, 73, 34);
		
		gp.npc[mapNum][index] = NPCSetup(5, 40, 78, 31);
		gp.npc[mapNum][index] = NPCSetup(5, 36, 79, 32);
		gp.npc[mapNum][index] = NPCSetup(4, 34, 89, 33);
		
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
		
		index++;
		
		return result;
	}
}
