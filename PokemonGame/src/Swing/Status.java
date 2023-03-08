package Swing;

import java.awt.Color;

public enum Status {
	BURNED("BRN", new Color(230, 9, 15), Color.BLACK),
	PARALYZED("PRZ", new Color(219, 216, 15), Color.BLACK),
	ASLEEP("SLP", new Color(105, 105, 102), Color.WHITE),
	POISONED("PSN", new Color(68, 2, 161), Color.WHITE),
	HEALTHY("", new Color(255, 255, 255), Color.BLACK),
	BLEEDING("BLD", new Color(64, 0, 0), Color.WHITE),
	CONFUSED("CNF", new Color(32, 37, 61), Color.WHITE),
	CURSED("CRS", new Color(30, 32, 41), Color.WHITE),
	LEECHED("LCH", new Color(21, 143, 40), Color.BLACK),
	NIGHTMARE("NGT", new Color(0, 0, 0), Color.WHITE), 
	FLINCHED("FLN", new Color(32, 37, 61), Color.WHITE),
	AQUA_RING("AQR", new Color(84, 117, 247), Color.BLACK), 
	CHARGED("CRG", new Color(219, 216, 15), Color.BLACK),
	SPUN("SPN", new Color(32, 37, 61), Color.WHITE);
	
	Status(String name, Color color, Color textColor) {
		this.name = name;
		this.color = color;
		this.textColor = textColor;
	}
	
	private String name;
	private Color color;
	private Color textColor;
	
	String getName() {
		return name;
	}
	
	Color getColor() {
		return color;
	}
	
	Color getTextColor() {
		return textColor;
	}
}
