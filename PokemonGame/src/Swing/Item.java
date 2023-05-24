package Swing;

import java.io.Serializable;

public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int cost;
	
	public Item(int i) {
		id = i;
		cost = setCost();
	}
	
	private int setCost() {
		if (id == 0) return 50;
		else if (id == 1) return 25;
		else if (id == 2) return 50;
		else if (id == 3) return 100;
		else if (id == 4) return 50;
		else if (id == 5) return 150;
		else if (id == 6) return 300;
		else if (id == 7) return 500;
		else if (id == 8) return 750;
		else if (id == 9) return 25;
		else if (id == 10) return 25;
		else if (id == 11) return 25;
		else if (id == 12) return 25;
		else if (id == 13) return 25;
		else if (id == 14) return 150;
		else if (id == 15) return 100;
		else if (id == 16) return 500;
		else if (id == 17) return 1500;
		else if (id == 18) return 500;
		else if (id == 19) return 500;
		else if (id == 20) return 500;
		else if (id == 21) return 500;
		else if (id == 112) return 600;
		else if (id == 113) return 500;
		else if (id == 115) return 900;
		else if (id == 116) return 1100;
		else if (id == 123) return 800;
		else if (id == 124) return 900;
		else if (id == 126) return 250;
		else if (id == 138) return 1500;
		else if (id == 141) return 1000;
		else if (id == 142) return 1000;
		else if (id == 143) return 1000;
		else if (id == 144) return 1000;
		else if (id == 145) return 750;
		else if (id == 146) return 1500;
		else if (id == 149) return 500;
		else if (id == 150) return 750;
		else if (id == 151) return 400;
		else if (id == 154) return 750;
		else if (id == 163) return 1500;
		else if (id == 164) return 1500;
		else if (id == 166) return 2000;
		else if (id == 167) return 2000;
		else if (id == 170) return 1500;
		else if (id == 174) return 900;
		else if (id == 175) return 900;
		else if (id == 176) return 700;
		else if (id == 177) return 700;
		else if (id == 178) return 800;
		else if (id == 179) return 300;
		else if (id == 180) return 300;
		else if (id == 181) return 300;
		else if (id == 182) return 300;
		else if (id == 191) return 1000;
		else if (id == 195) return 500;
		else return 0;
	}
	
	public int getCost() { return cost; }

	public int getID() { return id; }
	
	@Override
	public String toString() {
		if (id == 0) return "Repel";
		else if (id == 1) return "Pokeball";
		else if (id == 2) return "Great Ball";
		else if (id == 3) return "Ultra Ball";
		else if (id == 4) return "Potion";
		else if (id == 5) return "Super Potion";
		else if (id == 6) return "Hyper Potion";
		else if (id == 7) return "Max Potion";
		else if (id == 8) return "Full Restore";
		else if (id == 9) return "Antidote";
		else if (id == 10) return "Awakening";
		else if (id == 11) return "Burn Heal";
		else if (id == 12) return "Paralyze Heal";
		else if (id == 13) return "Freeze Heal";
		else if (id == 14) return "Full Heal";
		else if (id == 15) return "Rage Candy Bar";
		else if (id == 16) return "Revive";
		else if (id == 17) return "Max Revive";
		else if (id == 18) return "Leaf Stone";
		else if (id == 19) return "Dusk Stone";
		else if (id == 20) return "Dawn Stone";
		else if (id == 21) return "Ice Stone";
		else if (id == 93) return "HM01";
		else if (id == 94) return "HM02";
		else if (id == 95) return "HM03";
		else if (id == 96) return "HM04";
		else if (id == 97) return "HM05";
		else if (id == 98) return "HM06";
		else if (id == 99) return "HM07";
		else if (id == 100) return "HM08";
		else if (id == 101) return "TM01";
		else if (id == 102) return "TM02";
		else if (id == 103) return "TM03";
		else if (id == 104) return "TM04";
		else if (id == 105) return "TM05";
		else if (id == 106) return "TM06";
		else if (id == 107) return "TM07";
		else if (id == 108) return "TM08";
		else if (id == 109) return "TM09";
		else if (id == 110) return "TM10";
		else if (id == 111) return "TM11";
		else if (id == 112) return "TM12";
		else if (id == 113) return "TM13";
		else if (id == 114) return "TM14";
		else if (id == 115) return "TM15";
		else if (id == 116) return "TM16";
		else if (id == 117) return "TM17";
		else if (id == 118) return "TM18";
		else if (id == 119) return "TM19";
		else if (id == 120) return "TM20";
		else if (id == 121) return "TM21";
		else if (id == 122) return "TM22";
		else if (id == 123) return "TM23";
		else if (id == 124) return "TM24";
		else if (id == 125) return "TM25";
		else if (id == 126) return "TM26";
		else if (id == 127) return "TM27";
		else if (id == 128) return "TM28";
		else if (id == 129) return "TM29";
		else if (id == 130) return "TM30";
		else if (id == 131) return "TM31";
		else if (id == 132) return "TM32";
		else if (id == 133) return "TM33";
		else if (id == 134) return "TM34";
		else if (id == 135) return "TM35";
		else if (id == 136) return "TM36";
		else if (id == 137) return "TM37";
		else if (id == 138) return "TM38";
		else if (id == 139) return "TM39";
		else if (id == 140) return "TM40";
		else if (id == 141) return "TM41";
		else if (id == 142) return "TM42";
		else if (id == 143) return "TM43";
		else if (id == 144) return "TM44";
		else if (id == 145) return "TM45";
		else if (id == 146) return "TM46";
		else if (id == 147) return "TM47";
		else if (id == 148) return "TM48";
		else if (id == 149) return "TM49";
		else if (id == 150) return "TM50";
		else if (id == 151) return "TM51";
		else if (id == 152) return "TM52";
		else if (id == 153) return "TM53";
		else if (id == 154) return "TM54";
		else if (id == 155) return "TM55";
		else if (id == 156) return "TM56";
		else if (id == 157) return "TM57";
		else if (id == 158) return "TM58";
		else if (id == 159) return "TM59";
		else if (id == 160) return "TM60";
		else if (id == 161) return "TM61";
		else if (id == 162) return "TM62";
		else if (id == 163) return "TM63";
		else if (id == 164) return "TM64";
		else if (id == 165) return "TM65";
		else if (id == 166) return "TM66";
		else if (id == 167) return "TM67";
		else if (id == 168) return "TM68";
		else if (id == 169) return "TM69";
		else if (id == 170) return "TM70";
		else if (id == 171) return "TM71";
		else if (id == 172) return "TM72";
		else if (id == 173) return "TM73";
		else if (id == 174) return "TM74";
		else if (id == 175) return "TM75";
		else if (id == 176) return "TM76";
		else if (id == 177) return "TM77";
		else if (id == 178) return "TM78";
		else if (id == 179) return "TM79";
		else if (id == 180) return "TM80";
		else if (id == 181) return "TM81";
		else if (id == 182) return "TM82";
		else if (id == 183) return "TM83";
		else if (id == 184) return "TM84";
		else if (id == 185) return "TM85";
		else if (id == 186) return "TM86";
		else if (id == 187) return "TM87";
		else if (id == 188) return "TM88";
		else if (id == 189) return "TM89";
		else if (id == 190) return "TM90";
		else if (id == 191) return "TM91";
		else if (id == 192) return "TM92";
		else if (id == 193) return "TM93";
		else if (id == 194) return "TM94";
		else if (id == 195) return "TM95";
		else if (id == 196) return "TM96";
		else if (id == 197) return "TM97";
		else if (id == 198) return "TM98";
		else if (id == 199) return "TM99";
		return "";
	}
}
