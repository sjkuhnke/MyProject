package Overworld;

import Entity.NPC_Clerk;
import Entity.NPC_Nurse;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {}
	
	public void setNPC() {
		gp.npc[0] = new NPC_Nurse(gp);
		gp.npc[0].worldX = gp.tileSize*21;
		gp.npc[0].worldY = gp.tileSize*21;
		
		gp.npc[1] = new NPC_Clerk(gp);
		gp.npc[1].worldX = gp.tileSize*25;
		gp.npc[1].worldY = gp.tileSize*21;
	}
}
