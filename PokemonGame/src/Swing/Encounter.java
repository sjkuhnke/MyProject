package Swing;

import java.util.ArrayList;

public class Encounter {
	private int id;
	private int minLevel;
	private int maxLevel;
	private double encounterChance;
	
	public Encounter(int id, int minLevel, int maxLevel, double encounterChance) {
		this.id = id;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.encounterChance = encounterChance;
	}

	public double getEncounterChance() {
		return encounterChance;
	}

	public int getId() {
		return id;
	}

	public int getMinLevel() {
		return minLevel;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public static ArrayList<Encounter> getEncounters(int area, int x, int y, String type, String time) {
		ArrayList<Encounter> encounters = new ArrayList<>();
		System.out.println(area + "||" + x + ", " + y);
		if (area == 0 && type.equals("Standard") && y > 38) {
			encounters.add(new Encounter(13, 2, 2, 0.26));
			encounters.add(new Encounter(16, 2, 4, 0.18));
			encounters.add(new Encounter(22, 2, 3, 0.22));
			encounters.add(new Encounter(26, 2, 4, 0.10));
			encounters.add(new Encounter(29, 3, 3, 0.24));
		} else if (area == 0 && type.equals("Standard") && y <= 38) {
			encounters.add(new Encounter(10, 2, 3, 0.18));
			encounters.add(new Encounter(13, 3, 4, 0.24));
			encounters.add(new Encounter(16, 4, 4, 0.24));
			encounters.add(new Encounter(32, 2, 3, 0.20));
			encounters.add(new Encounter(38, 3, 5, 0.06));
			encounters.add(new Encounter(41, 2, 3, 0.08));
		} else if (area == 1 && type.equals("Standard")) {
			
		} else if (area == 2 && type.equals("Standard")) {
			encounters.add(new Encounter(13, 2, 2, 0.3));
			encounters.add(new Encounter(16, 2, 4, 0.3));
			encounters.add(new Encounter(22, 2, 3, 0.04));
			encounters.add(new Encounter(26, 2, 4, 0.35));
			encounters.add(new Encounter(29, 3, 3, 0.01));
		} else if (area == 3 && type.equals("Standard")) {
			encounters.add(new Encounter(13, 2, 2, 0.25));
			encounters.add(new Encounter(22, 2, 3, 0.6));
			encounters.add(new Encounter(26, 2, 4, 0.15));
		} else if (area == 4 && type.equals("Standard")) {
			encounters.add(new Encounter(10, 2, 3, 0.4));
			encounters.add(new Encounter(12, 3, 3, 0.2));
			encounters.add(new Encounter(14, 2, 3, 0.3));
		} else if (area == 5 && type.equals("Standard")) {
			encounters.add(new Encounter(10, 2, 3, 0.05));
			encounters.add(new Encounter(13, 3, 4, 0.36));
			encounters.add(new Encounter(16, 4, 4, 0.40));
			encounters.add(new Encounter(32, 2, 3, 0.03));
			encounters.add(new Encounter(38, 3, 5, 0.15));
			encounters.add(new Encounter(41, 2, 3, 0.01));
		} else if (area == 6 && type.equals("Standard")) {
			encounters.add(new Encounter(10, 2, 3, 0.4));
			encounters.add(new Encounter(12, 3, 3, 0.2));
			encounters.add(new Encounter(33, 2, 3, 0.3));
		}
		
		return encounters;
	}

}
