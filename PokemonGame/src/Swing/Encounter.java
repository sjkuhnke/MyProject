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

	public static ArrayList<Encounter> getEncounters(String routeName, String type, String time) {
		ArrayList<Encounter> encounters = new ArrayList<>();
		if (routeName.equals("New Pheonix Town") && type.equals("Fishing")) {
			encounters.add(new Encounter(-24, 3, 5, 0.4));
			encounters.add(new Encounter(-63, 5, 6, 0.3));
		} else if (routeName.equals("New Pheonix Town") && type.equals("Surfing")) {
			encounters.add(new Encounter(-26, 10, 14, 0.4));
		} else if (routeName.equals("New Pheonix Town") && type.equals("Headbutt") && time.equals("M")) {
			encounters.add(new Encounter(-10, 2, 3, 0.4));
		} else if (routeName.equals("New Pheonix Town") && type.equals("Headbutt") && time.equals("D")) {
			encounters.add(new Encounter(-10, 2, 3, 0.4));
		} else if (routeName.equals("New Pheonix Town") && type.equals("Headbutt") && time.equals("N")) {
			encounters.add(new Encounter(-10, 2, 3, 0.4));
			encounters.add(new Encounter(-33, 3, 4, 0.3));
		} else if (routeName.equals("Route 1")&& type.equals("Standard") && time.equals("M")) {
			encounters.add(new Encounter(-10, 2, 3, 0.4));
			encounters.add(new Encounter(-12, 3, 3, 0.2));
			encounters.add(new Encounter(-14, 2, 3, 0.3));
		} else if (routeName.equals("Route 1")&& type.equals("Standard") && time.equals("D")) {
			encounters.add(new Encounter(-10, 2, 3, 0.4));
			encounters.add(new Encounter(-12, 3, 3, 0.2));
			encounters.add(new Encounter(-14, 2, 3, 0.3));
		} else if (routeName.equals("Route 1")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-10, 2, 3, 0.4));
			encounters.add(new Encounter(-12, 3, 3, 0.2));
			encounters.add(new Encounter(-33, 2, 3, 0.3));
		} else if (routeName.equals("Blueberry Grove")&& type.equals("Standard")) {
			encounters.add(new Encounter(-10, 15, 25, 0.3));
			encounters.add(new Encounter(-11, 15, 25, 0.2));
			encounters.add(new Encounter(-12, 15, 25, 0.3));
			encounters.add(new Encounter(-14, 15, 25, 0.3));
			encounters.add(new Encounter(-15, 15, 25, 0.2));
			encounters.add(new Encounter(-16, 15, 25, 0.3));
			encounters.add(new Encounter(-18, 15, 25, 0.3));
			encounters.add(new Encounter(-19, 15, 25, 0.2));
			encounters.add(new Encounter(-21, 15, 25, 0.3));
			encounters.add(new Encounter(-22, 15, 25, 0.2));
			encounters.add(new Encounter(-24, 15, 25, 0.3));
			encounters.add(new Encounter(-25, 15, 25, 0.2));
			encounters.add(new Encounter(-26, 15, 25, 0.3));
			encounters.add(new Encounter(-27, 15, 25, 0.2));
			encounters.add(new Encounter(-29, 15, 25, 0.3));
			encounters.add(new Encounter(-30, 15, 25, 0.2));
			encounters.add(new Encounter(-31, 15, 25, 0.3));
			encounters.add(new Encounter(-32, 15, 25, 0.3));
			encounters.add(new Encounter(-33, 15, 25, 0.3));
			encounters.add(new Encounter(-34, 15, 25, 0.2));
			encounters.add(new Encounter(-35, 15, 25, 0.3));
			encounters.add(new Encounter(-36, 15, 25, 0.3));
			encounters.add(new Encounter(-37, 15, 25, 0.3));
			encounters.add(new Encounter(-38, 15, 25, 0.3));
			encounters.add(new Encounter(-39, 15, 25, 0.2));
			encounters.add(new Encounter(-40, 15, 25, 0.3));
			encounters.add(new Encounter(-41, 15, 25, 0.2));
			encounters.add(new Encounter(-42, 15, 25, 0.4));
			encounters.add(new Encounter(-43, 15, 25, 0.3));
			encounters.add(new Encounter(-44, 15, 25, 0.2));
			encounters.add(new Encounter(-46, 15, 25, 0.3));
			encounters.add(new Encounter(-48, 15, 25, 0.3));
			encounters.add(new Encounter(-49, 15, 25, 0.2));
			encounters.add(new Encounter(-50, 15, 25, 0.1));
			encounters.add(new Encounter(-51, 15, 25, 0.3));
			encounters.add(new Encounter(-52, 15, 25, 0.2));
			encounters.add(new Encounter(-53, 15, 25, 0.2));
			encounters.add(new Encounter(-54, 15, 25, 0.2));
			encounters.add(new Encounter(-55, 15, 25, 0.3));
			encounters.add(new Encounter(-56, 15, 25, 0.2));
			encounters.add(new Encounter(-57, 15, 25, 0.3));
			encounters.add(new Encounter(-58, 15, 25, 0.2));
			encounters.add(new Encounter(-59, 15, 25, 0.1));
			encounters.add(new Encounter(-141, 15, 25, 0.1));
			encounters.add(new Encounter(-142, 15, 25, 0.1));
			encounters.add(new Encounter(-61, 15, 25, 0.3));
			encounters.add(new Encounter(-63, 15, 25, 0.3));
			encounters.add(new Encounter(-64, 15, 25, 0.2));
			encounters.add(new Encounter(-65, 15, 25, 0.3));
			encounters.add(new Encounter(-67, 15, 25, 0.3));
			encounters.add(new Encounter(-68, 15, 25, 0.2));
			encounters.add(new Encounter(-69, 15, 25, 0.3));
			encounters.add(new Encounter(-70, 15, 25, 0.3));
			encounters.add(new Encounter(-71, 15, 25, 0.3));
			encounters.add(new Encounter(-74, 15, 25, 0.3));
			encounters.add(new Encounter(-75, 15, 25, 0.2));
			encounters.add(new Encounter(-77, 15, 25, 0.3));
			encounters.add(new Encounter(-80, 15, 25, 0.3));
			encounters.add(new Encounter(-82, 15, 25, 0.3));
			encounters.add(new Encounter(-84, 15, 25, 0.3));
			encounters.add(new Encounter(-86, 15, 25, 0.3));
			encounters.add(new Encounter(-88, 15, 25, 0.3));
			encounters.add(new Encounter(-89, 15, 25, 0.3));
			encounters.add(new Encounter(-92, 15, 25, 0.3));
			encounters.add(new Encounter(-93, 15, 25, 0.3));
			encounters.add(new Encounter(-95, 15, 25, 0.3));
			encounters.add(new Encounter(-97, 15, 25, 0.3));
			encounters.add(new Encounter(-98, 15, 25, 0.2));
			encounters.add(new Encounter(-100, 15, 25, 0.3));
			encounters.add(new Encounter(-104, 15, 25, 0.3));
			encounters.add(new Encounter(-105, 15, 25, 0.3));
			encounters.add(new Encounter(-106, 15, 25, 0.2));
			encounters.add(new Encounter(-107, 15, 25, 0.3));
			encounters.add(new Encounter(-110, 15, 25, 0.3));
		} else if (routeName.equals("Blueberry Grove")&& type.equals("Headbutt") && time.equals("M")) {
			encounters.add(new Encounter(-10, 3, 4, 0.4));
			encounters.add(new Encounter(-35, 3, 5, 0.3));
			encounters.add(new Encounter(-14, 3, 5, 0.2));
		} else if (routeName.equals("Blueberry Grove")&& type.equals("Headbutt") && time.equals("D")) {
			encounters.add(new Encounter(-10, 3, 4, 0.4));
			encounters.add(new Encounter(-35, 3, 5, 0.3));
			encounters.add(new Encounter(-14, 3, 5, 0.2));
		} else if (routeName.equals("Blueberry Grove")&& type.equals("Headbutt") && time.equals("N")) {
			encounters.add(new Encounter(-10, 3, 4, 0.4));
			encounters.add(new Encounter(-35, 3, 5, 0.3));
			encounters.add(new Encounter(-33, 4, 6, 0.4));
		} else if (routeName.equals("Route 2")&& type.equals("Standard") && time.equals("M")) {
			encounters.add(new Encounter(-10, 3, 4, 0.4));
			encounters.add(new Encounter(-16, 4, 5, 0.3));
			encounters.add(new Encounter(-18, 3, 5, 0.2));
			encounters.add(new Encounter(-29, 2, 4, 0.3));
		} else if (routeName.equals("Route 2")&& type.equals("Standard") && time.equals("D")) {
			encounters.add(new Encounter(-10, 3, 4, 0.4));
			encounters.add(new Encounter(-16, 4, 5, 0.4));
			encounters.add(new Encounter(-18, 3, 5, 0.2));
			encounters.add(new Encounter(-29, 2, 4, 0.3));
		} else if (routeName.equals("Route 2")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-16, 4, 5, 0.3));
			encounters.add(new Encounter(-18, 3, 5, 0.2));
			encounters.add(new Encounter(-29, 2, 4, 0.2));
		} else if (routeName.equals("Route 3")&& type.equals("Standard") && time.equals("M")) {
			encounters.add(new Encounter(-35, 5, 8, 0.3));
			encounters.add(new Encounter(-21, 6, 9, 0.1));
			encounters.add(new Encounter(-10, 4, 8, 0.4));
		} else if (routeName.equals("Route 3")&& type.equals("Standard") && time.equals("D")) {
			encounters.add(new Encounter(-35, 5, 8, 0.4));
			encounters.add(new Encounter(-21, 6, 9, 0.1));
			encounters.add(new Encounter(-10, 4, 8, 0.4));
		} else if (routeName.equals("Route 3")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-35, 5, 8, 0.2));
			encounters.add(new Encounter(-21, 6, 9, 0.1));
			encounters.add(new Encounter(-10, 4, 8, 0.4));
		} else if (routeName.equals("Chupi Forest")&& type.equals("Standard") && time.equals("M")) {
			encounters.add(new Encounter(-10, 6, 9, 0.4));
			encounters.add(new Encounter(-11, 10, 14, 0.2));
			encounters.add(new Encounter(-12, 9, 12, 0.4));
		} else if (routeName.equals("Chupi Forest")&& type.equals("Standard") && time.equals("D")) {
			encounters.add(new Encounter(-10, 6, 9, 0.4));
			encounters.add(new Encounter(-11, 10, 14, 0.2));
			encounters.add(new Encounter(-12, 9, 12, 0.4));
		} else if (routeName.equals("Chupi Forest")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-10, 6, 9, 0.4));
			encounters.add(new Encounter(-11, 10, 14, 0.2));
			encounters.add(new Encounter(-12, 9, 12, 0.3));
			encounters.add(new Encounter(-37, 9, 12, 0.1));
		} else if (routeName.equals("Chupi Forest")&& type.equals("Headbutt") && (time.equals("M") || time.equals("D"))) {
			encounters.add(new Encounter(-48, 7, 9, 0.2));
			encounters.add(new Encounter(-35, 5, 8, 0.4));
		} else if (routeName.equals("Chupi Forest")&& type.equals("Headbutt") && time.equals("N")) {
			encounters.add(new Encounter(-33, 6, 8, 0.4));
			encounters.add(new Encounter(-48, 7, 9, 0.2));
			encounters.add(new Encounter(-35, 5, 8, 0.3));
		} else if (routeName.equals("Chupi Forest")&& type.equals("Fishing")) {
			encounters.add(new Encounter(-69, 8, 10, 0.1));
			encounters.add(new Encounter(-24, 4, 7, 0.4));
		} else if (routeName.equals("Chupi Forest")&& type.equals("Surfing")) {
			encounters.add(new Encounter(-63, 10, 12, 0.3));
			encounters.add(new Encounter(-69, 11, 14, 0.2));
			encounters.add(new Encounter(-26, 8, 14, 0.4));
		} else if (routeName.equals("Route 5")&& type.equals("Standard") && time.equals("M")) {
			encounters.add(new Encounter(-16, 9, 11, 0.2));
			encounters.add(new Encounter(-35, 6, 8, 0.3));
			encounters.add(new Encounter(-31, 7, 10, 0.3));
			encounters.add(new Encounter(-29, 6, 8, 0.3));
			encounters.add(new Encounter(-32, 8, 9, 0.2));
		} else if (routeName.equals("Route 5")&& type.equals("Standard") && time.equals("D")) {
			encounters.add(new Encounter(-16, 9, 11, 0.3));
			encounters.add(new Encounter(-35, 6, 8, 0.3));
			encounters.add(new Encounter(-31, 7, 10, 0.4));
			encounters.add(new Encounter(-29, 6, 8, 0.3));
			encounters.add(new Encounter(-32, 8, 9, 0.4));
		} else if (routeName.equals("Route 5")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-16, 9, 11, 0.2));
			encounters.add(new Encounter(-35, 6, 8, 0.2));
			encounters.add(new Encounter(-31, 7, 10, 0.1));
			encounters.add(new Encounter(-29, 6, 8, 0.4));
			encounters.add(new Encounter(-32, 8, 9, 0.3));
		} else if (routeName.equals("Route 6")&& type.equals("Standard") && time.equals("M")) {
			encounters.add(new Encounter(-10, 4, 8, 0.4));
			encounters.add(new Encounter(-107, 8, 10, 0.1));
			encounters.add(new Encounter(-71, 8, 10, 0.1));
		} else if (routeName.equals("Route 6")&& type.equals("Standard") && time.equals("D")) {
			encounters.add(new Encounter(-10, 4, 8, 0.4));
			encounters.add(new Encounter(-71, 8, 10, 0.1));
		} else if (routeName.equals("Route 6")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-10, 4, 8, 0.3));
			encounters.add(new Encounter(-33, 6, 9, 0.3));
			encounters.add(new Encounter(-71, 8, 10, 0.1));
		} else if (routeName.equals("Shorki Cavern")&& type.equals("Standard") && (time.equals("M") || time.equals("D"))) {
			encounters.add(new Encounter(-38, 8, 10, 0.0));
			encounters.add(new Encounter(-18, 7, 13, 0.4));
			encounters.add(new Encounter(-21, 9, 15, 0.2));
			encounters.add(new Encounter(-74, 9, 15, 0.2));
			encounters.add(new Encounter(-77, 10, 16, 0.1));
			encounters.add(new Encounter(-57, 5, 11, 0.3));
			encounters.add(new Encounter(-42, 8, 10, 0.2));
			encounters.add(new Encounter(-33, 5, 8, 0.3));
			encounters.add(new Encounter(-40, 8, 10, 0.0));
		} else if (routeName.equals("Shorki Cavern")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-38, 8, 10, 0.2));
			encounters.add(new Encounter(-18, 7, 13, 0.3));
			encounters.add(new Encounter(-21, 9, 15, 0.2));
			encounters.add(new Encounter(-74, 9, 15, 0.2));
			encounters.add(new Encounter(-77, 10, 16, 0.1));
			encounters.add(new Encounter(-57, 5, 11, 0.4));
			encounters.add(new Encounter(-42, 8, 10, 0.3));
			encounters.add(new Encounter(-33, 5, 8, 0.3));
			encounters.add(new Encounter(-40, 8, 10, 0.2));
		} else if (routeName.equals("Shorki Cavern")&& type.equals("Fishing")) {
			encounters.add(new Encounter(-70, 12, 16, 0.1));
			encounters.add(new Encounter(-24, 8, 10, 0.4));
			encounters.add(new Encounter(-25, 14, 16, 0.2));
		} else if (routeName.equals("Shorki Cavern")&& type.equals("Surfing")) {
			encounters.add(new Encounter(-67, 10, 11, 0.4));
			encounters.add(new Encounter(-61, 9, 11, 0.2));
		} else if (routeName.equals("Route 7")&& type.equals("Standard") && (time.equals("M") || time.equals("D"))) {
			encounters.add(new Encounter(-36, 9, 14, 0.3));
			encounters.add(new Encounter(-43, 12, 15, 0.1));
			encounters.add(new Encounter(-44, 13, 15, 0.2));
		} else if (routeName.equals("Route 7")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-36, 9, 14, 0.4));
			encounters.add(new Encounter(-43, 12, 15, 0.1));
		} else if (routeName.equals("Route 9")&& type.equals("Standard") && (time.equals("M") || time.equals("D") || time.equals("N"))) {
			encounters.add(new Encounter(-10, 10, 12, 0.4));
			encounters.add(new Encounter(-59, 14, 15, 0.06));
			encounters.add(new Encounter(-141, 14, 15, 0.06));
			encounters.add(new Encounter(-142, 14, 15, 0.06));
			encounters.add(new Encounter(-95, 10, 15, 0.1));
		} else if (routeName.equals("Cornline City")&& type.equals("Fishing")) {
			encounters.add(new Encounter(-24, 12, 16, 0.4));
		} else if (routeName.equals("Cornline City")&& type.equals("Surfing")) {
			encounters.add(new Encounter(-65, 10, 14, 0.2));
			encounters.add(new Encounter(-24, 8, 12, 0.4));
		} else if (routeName.equals("Cornline City")&& type.equals("Standard")) {
			encounters.add(new Encounter(-42, 15, 15, 0.4));
			encounters.add(new Encounter(-44, 15, 15, 0.2));
			encounters.add(new Encounter(-46, 15, 15, 0.3));
		} else if (routeName.equals("Cornline City")&& type.equals("Headbutt")) {
			encounters.add(new Encounter(-33, 12, 12, 0.4));
			encounters.add(new Encounter(-34, 16, 17, 0.2));
			encounters.add(new Encounter(-88, 13, 16, 0.1));
		} else if (routeName.equals("Brocline City")&& type.equals("Fishing")) {
			encounters.add(new Encounter(-24, 10, 15, 0.4));
			encounters.add(new Encounter(-25, 13, 17, 0.2));
			encounters.add(new Encounter(-65, 12, 14, 0.3));
			encounters.add(new Encounter(-70, 16, 18, 0.1));
		} else if (routeName.equals("Brocline City")&& type.equals("Surfing")) {
			encounters.add(new Encounter(-65, 11, 11, 0.4));
		} else if (routeName.equals("Route 14")&& type.equals("Standard") && time.equals("M")) {
			encounters.add(new Encounter(-36, 12, 14, 0.4));
			encounters.add(new Encounter(-82, 14, 16, 0.2));
			encounters.add(new Encounter(-30, 18, 18, 0.2));
		} else if (routeName.equals("Route 14")&& type.equals("Standard") && time.equals("D")) {
			encounters.add(new Encounter(-36, 12, 14, 0.4));
			encounters.add(new Encounter(-82, 14, 16, 0.3));
			encounters.add(new Encounter(-30, 18, 18, 0.2));
		} else if (routeName.equals("Route 14")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-36, 12, 14, 0.4));
			encounters.add(new Encounter(-82, 14, 16, 0.1));
			encounters.add(new Encounter(-30, 18, 18, 0.2));
		} else if (routeName.equals("Route 15")&& type.equals("Standard") && time.equals("M")) {
			encounters.add(new Encounter(-48, 12, 14, 0.2));
			encounters.add(new Encounter(-49, 16, 20, 0.1));
			encounters.add(new Encounter(-46, 17, 17, 0.2));
			encounters.add(new Encounter(-26, 14, 16, 0.4));
			encounters.add(new Encounter(-27, 19, 19, 0.3));
		} else if (routeName.equals("Route 15")&& type.equals("Standard") && (time.equals("D") || time.equals("N"))) {
			encounters.add(new Encounter(-48, 12, 14, 0.3));
			encounters.add(new Encounter(-49, 16, 20, 0.2));
			encounters.add(new Encounter(-46, 17, 17, 0.2));
			encounters.add(new Encounter(-26, 14, 16, 0.4));
			encounters.add(new Encounter(-27, 19, 19, 0.3));
		} else if (routeName.equals("Route 11")&& type.equals("Fishing")) {
			encounters.add(new Encounter(-24, 16, 18, 0.4));
			encounters.add(new Encounter(-25, 21, 21, 0.2));
		} else if (routeName.equals("Route 11")&& type.equals("Surfing")) {
			encounters.add(new Encounter(-68, 18, 18, 0.4));
			encounters.add(new Encounter(-69, 18, 18, 0.2));
		} else if (routeName.equals("Route 13")&& type.equals("Standard")) {
			encounters.add(new Encounter(-59, 15, 17, 0.13));
			encounters.add(new Encounter(-141, 15, 17, 0.13));
			encounters.add(new Encounter(-142, 15, 17, 0.13));
			encounters.add(new Encounter(-80, 20, 20, 0.2));
			encounters.add(new Encounter(-84, 16, 19, 0.3));
			encounters.add(new Encounter(-55, 15, 18, 0.2));
		} else if (routeName.equals("Route 12")&& type.equals("Standard")) {
			encounters.add(new Encounter(-18, 18, 19, 0.4));
			encounters.add(new Encounter(-97, 16, 20, 0.2));
			encounters.add(new Encounter(-51, 15, 18, 0.3));
		} else if (routeName.equals("Night Scope Town")&& type.equals("Headbutt") && time.equals("M")) {
			encounters.add(new Encounter(-33, 16, 18, 0.3));
			encounters.add(new Encounter(-34, 20, 20, 0.2));
			encounters.add(new Encounter(-48, 18, 18, 0.3));
			encounters.add(new Encounter(-10, 12, 15, 0.4));
			encounters.add(new Encounter(-11, 18, 20, 0.3));
		} else if (routeName.equals("Night Scope Town")&& type.equals("Headbutt") && time.equals("D")) {
			encounters.add(new Encounter(-33, 16, 18, 0.3));
			encounters.add(new Encounter(-34, 20, 20, 0.2));
			encounters.add(new Encounter(-48, 18, 18, 0.2));
			encounters.add(new Encounter(-10, 12, 15, 0.4));
			encounters.add(new Encounter(-11, 18, 20, 0.2));
		} else if (routeName.equals("Night Scope Town")&& type.equals("Headbutt") && time.equals("N")) {
			encounters.add(new Encounter(-33, 16, 18, 0.4));
			encounters.add(new Encounter(-34, 20, 20, 0.3));
			encounters.add(new Encounter(-48, 18, 18, 0.1));
			encounters.add(new Encounter(-10, 12, 15, 0.4));
			encounters.add(new Encounter(-11, 18, 20, 0.2));
		} else if (routeName.equals("Mt. Wakauki 1A")&& type.equals("Standard")) {
			encounters.add(new Encounter(-18, 6, 8, 0.4));
			encounters.add(new Encounter(-21, 7, 9, 0.2));
		} else if (routeName.equals("Mt. Wakauki 1A")&& type.equals("Fishing")) {
			encounters.add(new Encounter(-24, 8, 10, 0.4));
			encounters.add(new Encounter(-63, 9, 12, 0.2));
		} else if (routeName.equals("Mt. Wakauki 1A")&& type.equals("Surfing")) {
			encounters.add(new Encounter(-24, 5, 7, 0.4));
			encounters.add(new Encounter(-65, 6, 8, 0.2));
			encounters.add(new Encounter(-63, 5, 8, 0.3));
		} else if (routeName.equals("Mt. Wakauki 2F")&& type.equals("Standard") && (time.equals("M") || time.equals("D"))) {
			encounters.add(new Encounter(-18, 15, 20, 0.4));
			encounters.add(new Encounter(-19, 25, 25, 0.3));
			encounters.add(new Encounter(-33, 16, 18, 0.2));
			encounters.add(new Encounter(-57, 15, 20, 0.3));
			encounters.add(new Encounter(-40, 20, 22, 0.2));
		} else if (routeName.equals("Mt. Wakauki 2F")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-18, 15, 20, 0.4));
			encounters.add(new Encounter(-19, 25, 25, 0.2));
			encounters.add(new Encounter(-33, 16, 18, 0.3));
			encounters.add(new Encounter(-57, 15, 20, 0.4));
			encounters.add(new Encounter(-40, 20, 22, 0.2));
		} else if (routeName.equals("Mt. Wakauki 1F")&& type.equals("Standard")) {
			encounters.add(new Encounter(-106, 18, 22, 0.2));
			encounters.add(new Encounter(-21, 18, 22, 0.4));
		} else if (routeName.equals("Mt. Wakauki 1F")&& type.equals("Fishing")) {
			encounters.add(new Encounter(-24, 20, 20, 0.4));
			encounters.add(new Encounter(-25, 22, 22, 0.2));
		} else if (routeName.equals("Mt. Wakauki 1F")&& type.equals("Surfing")) {
			encounters.add(new Encounter(-65, 15, 20, 0.4));
			encounters.add(new Encounter(-100, 20, 20, 0.2));
		} else if (routeName.equals("Mt. Wakauki 3B")&& type.equals("Standard")) {
			encounters.add(new Encounter(-18, 16, 19, 0.4));
			encounters.add(new Encounter(-19, 21, 24, 0.2));
			encounters.add(new Encounter(-21, 19, 23, 0.3));
			encounters.add(new Encounter(-51, 17, 22, 0.1));
		} else if (routeName.equals("Mt. Wakauki 3B")&& type.equals("Fishing")) {
			encounters.add(new Encounter(-24, 15, 20, 0.4));
			encounters.add(new Encounter(-25, 21, 23, 0.3));
			encounters.add(new Encounter(-68, 23, 23, 0.3));
		} else if (routeName.equals("Mt. Wakauki 3B")&& type.equals("Surfing")) {
			encounters.add(new Encounter(-63, 15, 18, 0.4));
			encounters.add(new Encounter(-68, 19, 19, 0.2));
		} else if (routeName.equals("Mt. Wakauki 4A")&& type.equals("Standard") && (time.equals("M") || time.equals("D"))) {
			encounters.add(new Encounter(-18, 16, 19, 0.4));
			encounters.add(new Encounter(-19, 21, 24, 0.2));
			encounters.add(new Encounter(-21, 17, 20, 0.3));
			encounters.add(new Encounter(-33, 15, 18, 0.2));
			encounters.add(new Encounter(-86, 18, 20, 0.1));
			encounters.add(new Encounter(-38, 18, 20, 0.2));
		} else if (routeName.equals("Mt. Wakauki 4A")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-18, 16, 19, 0.4));
			encounters.add(new Encounter(-19, 21, 24, 0.2));
			encounters.add(new Encounter(-21, 17, 20, 0.3));
			encounters.add(new Encounter(-33, 15, 18, 0.4));
			encounters.add(new Encounter(-86, 18, 20, 0.2));
			encounters.add(new Encounter(-38, 18, 20, 0.2));
		} else if (routeName.equals("Mt. Wakauki 4A")&& type.equals("Fishing")) {
			encounters.add(new Encounter(-24, 16, 19, 0.4));
			encounters.add(new Encounter(-25, 18, 21, 0.2));
		} else if (routeName.equals("Mt. Wakauki 4A")&& type.equals("Surfing")) {
			encounters.add(new Encounter(-63, 15, 15, 0.4));
			encounters.add(new Encounter(-65, 15, 15, 0.2));
		} else if (routeName.equals("Mt. Wakauki 5F")&& type.equals("Standard")) {
			encounters.add(new Encounter(-18, 20, 20, 0.4));
			encounters.add(new Encounter(-19, 22, 22, 0.3));
			encounters.add(new Encounter(-106, 21, 21, 0.1));
		} else if (routeName.equals("Mt. Wakauki 5A")&& type.equals("Standard")) {
			encounters.add(new Encounter(-18, 18, 21, 0.4));
			encounters.add(new Encounter(-19, 20, 23, 0.3));
			encounters.add(new Encounter(-21, 19, 22, 0.3));
			encounters.add(new Encounter(-75, 18, 21, 0.2));
		} else if (routeName.equals("Mt. Wakauki 5B")&& type.equals("Standard") && (time.equals("M") || time.equals("D"))) {
			encounters.add(new Encounter(-35, 23, 24, 0.4));
			encounters.add(new Encounter(-36, 23, 24, 0.4));
			encounters.add(new Encounter(-21, 21, 23, 0.3));
			encounters.add(new Encounter(-19, 24, 24, 0.2));
			encounters.add(new Encounter(-86, 20, 24, 0.0));
			encounters.add(new Encounter(-33, 18, 21, 0.0));
			encounters.add(new Encounter(-97, 20, 24, 0.2));
			encounters.add(new Encounter(-49, 19, 22, 0.2));
			encounters.add(new Encounter(-109, 18, 23, 0.1));
			encounters.add(new Encounter(-82, 19, 22, 0.3));
		} else if (routeName.equals("Mt. Wakauki 5B")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-35, 23, 24, 0.4));
			encounters.add(new Encounter(-36, 23, 24, 0.4));
			encounters.add(new Encounter(-21, 21, 23, 0.3));
			encounters.add(new Encounter(-19, 24, 24, 0.2));
			encounters.add(new Encounter(-86, 20, 24, 0.2));
			encounters.add(new Encounter(-33, 18, 21, 0.4));
			encounters.add(new Encounter(-97, 20, 24, 0.2));
			encounters.add(new Encounter(-49, 19, 22, 0.2));
			encounters.add(new Encounter(-109, 18, 23, 0.1));
			encounters.add(new Encounter(-82, 19, 22, 0.3));
		} else if (routeName.equals("Mt. Wakauki 6A")&& type.equals("Standard")) {
			encounters.add(new Encounter(-103, 40, 40, 0.1));
			encounters.add(new Encounter(-104, 40, 40, 0.1));
			encounters.add(new Encounter(-105, 40, 40, 0.1));
		} else if (routeName.equals("Route 4")&& type.equals("Standard") && time.equals("M")) {
			encounters.add(new Encounter(-59, 4, 6, 0.06));
			encounters.add(new Encounter(-141, 4, 6, 0.06));
			encounters.add(new Encounter(-142, 4, 6, 0.06));
			encounters.add(new Encounter(-14, 5, 6, 0.4));
			encounters.add(new Encounter(-16, 6, 7, 0.4));
			encounters.add(new Encounter(-18, 5, 7, 0.2));
			encounters.add(new Encounter(-21, 6, 8, 0.1));
		} else if (routeName.equals("Route 4")&& type.equals("Standard") && time.equals("D")) {
			encounters.add(new Encounter(-59, 4, 6, 0.03));
			encounters.add(new Encounter(-141, 4, 6, 0.03));
			encounters.add(new Encounter(-142, 4, 6, 0.03));
			encounters.add(new Encounter(-14, 5, 6, 0.4));
			encounters.add(new Encounter(-16, 6, 7, 0.3));
			encounters.add(new Encounter(-18, 5, 7, 0.2));
			encounters.add(new Encounter(-21, 6, 8, 0.1));
		} else if (routeName.equals("Route 4")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-59, 4, 6, 0.06));
			encounters.add(new Encounter(-141, 4, 6, 0.06));
			encounters.add(new Encounter(-142, 4, 6, 0.06));
			encounters.add(new Encounter(-14, 5, 6, 0.0));
			encounters.add(new Encounter(-16, 6, 7, 0.2));
			encounters.add(new Encounter(-18, 5, 7, 0.2));
			encounters.add(new Encounter(-21, 6, 8, 0.1));
		} else if (routeName.equals("Route 10")&& type.equals("Standard")) {
			encounters.add(new Encounter(-82, 21, 24, 0.2));
			encounters.add(new Encounter(-71, 20, 24, 0.2));
			encounters.add(new Encounter(-84, 20, 23, 0.3));
			encounters.add(new Encounter(-12, 19, 22, 0.4));
			encounters.add(new Encounter(-26, 16, 18, 0.4));
			encounters.add(new Encounter(-27, 20, 22, 0.3));
			encounters.add(new Encounter(-43, 21, 21, 0.1));
		} else if (routeName.equals("Route 10")&& type.equals("Fishing")) {
			encounters.add(new Encounter(-24, 15, 22, 0.4));
			encounters.add(new Encounter(-25, 20, 25, 0.3));
			encounters.add(new Encounter(-69, 20, 25, 0.1));
		} else if (routeName.equals("Route 10")&& type.equals("Surfing")) {
			encounters.add(new Encounter(-67, 12, 16, 0.1));
			encounters.add(new Encounter(-65, 11, 15, 0.2));
			encounters.add(new Encounter(-63, 12, 16, 0.3));
			encounters.add(new Encounter(-26, 10, 15, 0.4));
			encounters.add(new Encounter(-27, 14, 16, 0.2));
		} else if (routeName.equals("Route 16")&& type.equals("Standard")) {
			encounters.add(new Encounter(-106, 26, 28, 0.2));
			encounters.add(new Encounter(-107, 25, 26, 0.2));
			encounters.add(new Encounter(-82, 25, 25, 0.3));
			encounters.add(new Encounter(-84, 26, 26, 0.3));
			encounters.add(new Encounter(-112, 20, 20, 0.1));
			encounters.add(new Encounter(-18, 20, 23, 0.4));
			encounters.add(new Encounter(-19, 25, 27, 0.2));
		} else if (routeName.equals("Breezeline Village")&& type.equals("Fishing")) {
			encounters.add(new Encounter(-61, 15, 20, 0.2));
			encounters.add(new Encounter(-24, 14, 17, 0.4));
			encounters.add(new Encounter(-25, 18, 20, 0.3));
		} else if (routeName.equals("Breezeline Village")&& type.equals("Surfing")) {
			encounters.add(new Encounter(-65, 13, 16, 0.2));
			encounters.add(new Encounter(-63, 13, 16, 0.4));
		} else if (routeName.equals("Blantoisa Path 1F")&& type.equals("Standard") && (time.equals("M") || time.equals("D"))) {
			encounters.add(new Encounter(-18, 20, 20, 0.4));
			encounters.add(new Encounter(-19, 25, 27, 0.2));
			encounters.add(new Encounter(-21, 21, 24, 0.3));
			encounters.add(new Encounter(-22, 27, 29, 0.1));
			encounters.add(new Encounter(-57, 20, 21, 0.2));
			encounters.add(new Encounter(-58, 25, 27, 0.1));
			encounters.add(new Encounter(-33, 18, 20, 0.3));
			encounters.add(new Encounter(-34, 21, 25, 0.1));
		} else if (routeName.equals("Blantoisa Path 1F")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-18, 20, 20, 0.4));
			encounters.add(new Encounter(-19, 25, 27, 0.2));
			encounters.add(new Encounter(-21, 21, 24, 0.3));
			encounters.add(new Encounter(-22, 27, 29, 0.1));
			encounters.add(new Encounter(-57, 20, 21, 0.4));
			encounters.add(new Encounter(-58, 25, 27, 0.3));
			encounters.add(new Encounter(-33, 18, 20, 0.4));
			encounters.add(new Encounter(-34, 21, 25, 0.2));
		} else if (routeName.equals("Blantoisa Path B1F")&& type.equals("Standard")) {
			encounters.add(new Encounter(-18, 20, 20, 0.4));
			encounters.add(new Encounter(-19, 25, 27, 0.2));
			encounters.add(new Encounter(-21, 21, 24, 0.3));
			encounters.add(new Encounter(-22, 27, 29, 0.1));
			encounters.add(new Encounter(-74, 14, 15, 0.4));
			encounters.add(new Encounter(-75, 22, 24, 0.3));
			encounters.add(new Encounter(-77, 20, 25, 0.4));
			encounters.add(new Encounter(-97, 23, 23, 0.2));
		} else if (routeName.equals("Route 17")&& type.equals("Standard") && (time.equals("D") || time.equals("N"))) {
			encounters.add(new Encounter(-109, 24, 24, 0.0));
			encounters.add(new Encounter(-71, 22, 24, 0.2));
			encounters.add(new Encounter(-82, 24, 24, 0.3));
			encounters.add(new Encounter(-84, 24, 24, 0.3));
			encounters.add(new Encounter(-31, 21, 23, 0.4));
			encounters.add(new Encounter(-32, 20, 22, 0.4));
			encounters.add(new Encounter(-29, 20, 20, 0.4));
			encounters.add(new Encounter(-30, 24, 24, 0.2));
		} else if (routeName.equals("Route 17")&& type.equals("Standard") && time.equals("M")) {
			encounters.add(new Encounter(-109, 24, 24, 0.1));
			encounters.add(new Encounter(-71, 22, 24, 0.2));
			encounters.add(new Encounter(-82, 24, 24, 0.3));
			encounters.add(new Encounter(-84, 24, 24, 0.3));
			encounters.add(new Encounter(-31, 21, 23, 0.4));
			encounters.add(new Encounter(-32, 20, 22, 0.4));
			encounters.add(new Encounter(-29, 20, 20, 0.4));
			encounters.add(new Encounter(-30, 24, 24, 0.2));
		} else if (routeName.equals("Route 17")&& type.equals("Fishing")) {
			encounters.add(new Encounter(-61, 21, 25, 0.2));
			encounters.add(new Encounter(-24, 22, 24, 0.4));
			encounters.add(new Encounter(-25, 25, 25, 0.2));
		} else if (routeName.equals("Route 17")&& type.equals("Surfing")) {
			encounters.add(new Encounter(-65, 15, 15, 0.4));
			encounters.add(new Encounter(-61, 15, 15, 0.2));
			encounters.add(new Encounter(-26, 15, 15, 0.4));
			encounters.add(new Encounter(-27, 20, 20, 0.3));
		} else if (routeName.equals("Elite Plateau")&& type.equals("Fishing")) {
			encounters.add(new Encounter(-61, 21, 25, 0.2));
			encounters.add(new Encounter(-24, 22, 24, 0.4));
			encounters.add(new Encounter(-25, 25, 25, 0.2));
		} else if (routeName.equals("Elite Plateau")&& type.equals("Surfing")) {
			encounters.add(new Encounter(-65, 15, 15, 0.4));
			encounters.add(new Encounter(-61, 15, 15, 0.2));
			encounters.add(new Encounter(-26, 15, 15, 0.4));
			encounters.add(new Encounter(-27, 20, 20, 0.3));
		} else if (routeName.equals("Route 8")&& type.equals("Standard") && time.equals("M")) {
			encounters.add(new Encounter(-29, 5, 6, 0.4));
			encounters.add(new Encounter(-14, 5, 6, 0.3));
			encounters.add(new Encounter(-16, 6, 8, 0.2));
			encounters.add(new Encounter(-31, 6, 8, 0.2));
			encounters.add(new Encounter(-32, 5, 7, 0.2));
		} else if (routeName.equals("Route 8")&& type.equals("Standard") && time.equals("D")) {
			encounters.add(new Encounter(-29, 5, 6, 0.4));
			encounters.add(new Encounter(-14, 5, 6, 0.3));
			encounters.add(new Encounter(-16, 6, 8, 0.3));
			encounters.add(new Encounter(-31, 6, 8, 0.2));
			encounters.add(new Encounter(-32, 5, 7, 0.2));
		} else if (routeName.equals("Route 8")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-29, 5, 6, 0.4));
			encounters.add(new Encounter(-14, 5, 6, 0.0));
			encounters.add(new Encounter(-16, 6, 8, 0.2));
			encounters.add(new Encounter(-31, 6, 8, 0.0));
			encounters.add(new Encounter(-32, 5, 7, 0.2));
		} else if (routeName.equals("Chomp Lake")&& type.equals("Fishing")) {
			encounters.add(new Encounter(-24, 15, 20, 0.4));
			encounters.add(new Encounter(-25, 21, 21, 0.3));
			encounters.add(new Encounter(-69, 20, 20, 0.1));
			encounters.add(new Encounter(-61, 20, 20, 0.2));
		} else if (routeName.equals("Chomp Lake")&& type.equals("Surfing")) {
			encounters.add(new Encounter(-65, 8, 10, 0.4));
			encounters.add(new Encounter(-63, 8, 10, 0.4));
			encounters.add(new Encounter(-67, 10, 10, 0.3));
			encounters.add(new Encounter(-69, 10, 10, 0.2));
		} else if (routeName.equals("Thunder Tower 1T")&& type.equals("Standard") && (time.equals("M") || time.equals("D"))) {
			encounters.add(new Encounter(-33, 15, 20, 0.4));
			encounters.add(new Encounter(-37, 20, 20, 0.1));
			encounters.add(new Encounter(-38, 20, 20, 0.0));
			encounters.add(new Encounter(-40, 20, 20, 0.0));
			encounters.add(new Encounter(-86, 15, 20, 0.2));
			encounters.add(new Encounter(-89, 20, 20, 0.0));
		} else if (routeName.equals("Thunder Tower 1T")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-33, 15, 20, 0.2));
			encounters.add(new Encounter(-37, 20, 20, 0.2));
			encounters.add(new Encounter(-38, 20, 20, 0.3));
			encounters.add(new Encounter(-40, 20, 20, 0.3));
			encounters.add(new Encounter(-86, 15, 20, 0.2));
			encounters.add(new Encounter(-89, 20, 20, 0.1));
		} else if (routeName.equals("Thunder Tower 2T")&& type.equals("Standard") && (time.equals("M") || time.equals("D"))) {
			encounters.add(new Encounter(-33, 20, 20, 0.3));
			encounters.add(new Encounter(-37, 20, 20, 0.0));
			encounters.add(new Encounter(-38, 20, 20, 0.0));
			encounters.add(new Encounter(-89, 20, 20, 0.0));
			encounters.add(new Encounter(-86, 20, 20, 0.0));
			encounters.add(new Encounter(-92, 30, 30, 0.3));
		} else if (routeName.equals("Thunder Tower 2T")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-33, 20, 20, 0.1));
			encounters.add(new Encounter(-37, 20, 20, 0.3));
			encounters.add(new Encounter(-38, 20, 20, 0.3));
			encounters.add(new Encounter(-89, 20, 20, 0.2));
			encounters.add(new Encounter(-86, 20, 20, 0.4));
			encounters.add(new Encounter(-92, 30, 30, 0.1));
		} else if (routeName.equals("Thunder Tower 3T")&& type.equals("Standard")) {
			encounters.add(new Encounter(-92, 60, 60, 0.1));
		} else if (routeName.equals("Thunder Tower R")&& type.equals("Standard")) {
			encounters.add(new Encounter(-133, 70, 70, 0.1));
		} else if (routeName.equals("Thunder Tower 1TB")&& type.equals("Standard") && (time.equals("M") || time.equals("D"))) {
			encounters.add(new Encounter(-93, 25, 25, 0.2));
			encounters.add(new Encounter(-37, 25, 25, 0.0));
			encounters.add(new Encounter(-74, 15, 15, 0.4));
		} else if (routeName.equals("Thunder Tower 1TB")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-93, 25, 25, 0.2));
			encounters.add(new Encounter(-74, 15, 15, 0.4));
		} else if (routeName.equals("Thunder Tower 2TB")&& type.equals("Standard") && (time.equals("M") || time.equals("D"))) {
			encounters.add(new Encounter(-93, 25, 25, 0.2));
			encounters.add(new Encounter(-74, 15, 15, 0.4));
		} else if (routeName.equals("Thunder Tower 2TB")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-93, 25, 25, 0.4));
			encounters.add(new Encounter(-74, 15, 15, 0.3));
		} else if (routeName.equals("Thunder Tower 3TB")&& type.equals("Standard")) {
			encounters.add(new Encounter(-94, 75, 75, 0.1));
		} else if (routeName.equals("Thunder Tower R6")&& type.equals("Standard")) {
			encounters.add(new Encounter(-91, 30, 30, 0.1));
		} else if (routeName.equals("Thunder Tower RB2")&& type.equals("Standard")) {
			encounters.add(new Encounter(-91, 70, 70, 0.1));
		} else if (routeName.equals("Route 18")&& type.equals("Standard") && (time.equals("M") || time.equals("D"))) {
			encounters.add(new Encounter(-72, 30, 35, 0.4));
			encounters.add(new Encounter(-35, 30, 30, 0.2));
			encounters.add(new Encounter(-36, 30, 30, 0.0));
		} else if (routeName.equals("Route 18")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-72, 30, 35, 0.4));
			encounters.add(new Encounter(-35, 30, 30, 0.2));
			encounters.add(new Encounter(-36, 30, 30, 0.4));
		} else if (routeName.equals("Route 19")&& type.equals("Standard") && (time.equals("M") || time.equals("D"))) {
			encounters.add(new Encounter(-56, 30, 35, 0.4));
			encounters.add(new Encounter(-34, 30, 35, 0.0));
			encounters.add(new Encounter(-36, 30, 30, 0.3));
			encounters.add(new Encounter(-37, 35, 35, 0.0));
			encounters.add(new Encounter(-58, 35, 35, 0.0));
			encounters.add(new Encounter(-60, 30, 35, 0.1));
			encounters.add(new Encounter(-143, 30, 35, 0.1));
			encounters.add(new Encounter(-144, 30, 35, 0.1));
		} else if (routeName.equals("Route 19")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-56, 30, 35, 0.2));
			encounters.add(new Encounter(-34, 30, 35, 0.2));
			encounters.add(new Encounter(-36, 30, 30, 0.3));
			encounters.add(new Encounter(-37, 35, 35, 0.3));
			encounters.add(new Encounter(-58, 35, 35, 0.4));
		} else if (routeName.equals("Route 20")&& type.equals("Standard") && time.equals("M")) {
			encounters.add(new Encounter(-88, 30, 35, 0.4));
			encounters.add(new Encounter(-96, 30, 35, 0.1));
			encounters.add(new Encounter(-98, 30, 35, 0.3));
			encounters.add(new Encounter(-39, 30, 30, 0.0));
		} else if (routeName.equals("Route 20")&& type.equals("Standard") && time.equals("D")) {
			encounters.add(new Encounter(-88, 30, 35, 0.2));
			encounters.add(new Encounter(-96, 30, 35, 0.2));
			encounters.add(new Encounter(-98, 30, 35, 0.3));
			encounters.add(new Encounter(-39, 30, 30, 0.0));
		} else if (routeName.equals("Route 20")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-88, 30, 35, 0.2));
			encounters.add(new Encounter(-96, 30, 35, 0.1));
			encounters.add(new Encounter(-98, 30, 35, 0.2));
			encounters.add(new Encounter(-39, 30, 30, 0.3));
		} else if (routeName.equals("Route 21")&& type.equals("Standard")) {
			encounters.add(new Encounter(-75, 30, 35, 0.2));
			encounters.add(new Encounter(-72, 30, 35, 0.3));
		} else if (routeName.equals("Orcai Cavern 1A")&& type.equals("Surfing")) {
			encounters.add(new Encounter(-25, 25, 25, 0.2));
			encounters.add(new Encounter(-65, 25, 25, 0.3));
		} else if (routeName.equals("Orcai Cavern 1A")&& type.equals("Fishing")) {
			encounters.add(new Encounter(-25, 30, 30, 0.4));
		} else if (routeName.equals("Orcai Cavern 0 N")&& type.equals("Standard") && time.equals("M")) {
			encounters.add(new Encounter(-15, 25, 25, 0.4));
			encounters.add(new Encounter(-19, 25, 25, 0.3));
			encounters.add(new Encounter(-30, 25, 25, 0.3));
		} else if (routeName.equals("Orcai Cavern 0 N")&& type.equals("Standard") && time.equals("D")) {
			encounters.add(new Encounter(-15, 25, 25, 0.2));
			encounters.add(new Encounter(-19, 25, 25, 0.4));
			encounters.add(new Encounter(-30, 25, 25, 0.2));
		} else if (routeName.equals("Orcai Cavern 0 N")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-19, 25, 25, 0.2));
		} else if (routeName.equals("Orcai Cavern 0 N")&& type.equals("Surfing")) {
			encounters.add(new Encounter(-65, 25, 25, 0.4));
			encounters.add(new Encounter(-100, 25, 25, 0.2));
		} else if (routeName.equals("Orcai Cavern 0 N")&& type.equals("Fishing")) {
			encounters.add(new Encounter(-25, 30, 30, 0.4));
			encounters.add(new Encounter(-69, 35, 35, 0.2));
			encounters.add(new Encounter(-70, 35, 35, 0.1));
		} else if (routeName.equals("Orcai Cavern 0 SE")&& type.equals("Standard") && time.equals("M")) {
			encounters.add(new Encounter(-58, 25, 25, 0.0));
			encounters.add(new Encounter(-19, 25, 25, 0.3));
			encounters.add(new Encounter(-84, 25, 25, 0.3));
			encounters.add(new Encounter(-30, 25, 25, 0.3));
		} else if (routeName.equals("Orcai Cavern 0 SE")&& type.equals("Standard") && time.equals("D")) {
			encounters.add(new Encounter(-58, 25, 25, 0.0));
			encounters.add(new Encounter(-19, 25, 25, 0.4));
			encounters.add(new Encounter(-84, 25, 25, 0.0));
			encounters.add(new Encounter(-30, 25, 25, 0.2));
		} else if (routeName.equals("Orcai Cavern 0 SE")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-58, 25, 25, 0.4));
			encounters.add(new Encounter(-19, 25, 25, 0.2));
			encounters.add(new Encounter(-84, 25, 25, 0.0));
		} else if (routeName.equals("Orcai Cavern 0 SE")&& type.equals("Surfing")) {
			encounters.add(new Encounter(-65, 25, 25, 0.4));
			encounters.add(new Encounter(-100, 25, 25, 0.2));
		} else if (routeName.equals("Orcai Cavern 0 SE")&& type.equals("Fishing")) {
			encounters.add(new Encounter(-25, 30, 30, 0.4));
			encounters.add(new Encounter(-69, 35, 35, 0.2));
			encounters.add(new Encounter(-70, 35, 35, 0.1));
		} else if (routeName.equals("Orcai Cavern 0 SW")&& type.equals("Standard") && time.equals("M")) {
			encounters.add(new Encounter(-86, 25, 25, 0.0));
			encounters.add(new Encounter(-19, 25, 25, 0.3));
			encounters.add(new Encounter(-34, 25, 25, 0.1));
			encounters.add(new Encounter(-30, 25, 25, 0.3));
		} else if (routeName.equals("Orcai Cavern 0 SW")&& type.equals("Standard") && time.equals("D")) {
			encounters.add(new Encounter(-86, 25, 25, 0.0));
			encounters.add(new Encounter(-19, 25, 25, 0.4));
			encounters.add(new Encounter(-34, 25, 25, 0.2));
			encounters.add(new Encounter(-30, 25, 25, 0.2));
		} else if (routeName.equals("Orcai Cavern 0 SW")&& type.equals("Standard") && time.equals("N")) {
			encounters.add(new Encounter(-86, 25, 25, 0.2));
			encounters.add(new Encounter(-19, 25, 25, 0.2));
			encounters.add(new Encounter(-34, 25, 25, 0.3));
		} else if (routeName.equals("Orcai Cavern 0 SW")&& type.equals("Surfing")) {
			encounters.add(new Encounter(-65, 25, 25, 0.4));
			encounters.add(new Encounter(-100, 25, 25, 0.2));
		} else if (routeName.equals("Orcai Cavern 0 SW")&& type.equals("Fishing")) {
			encounters.add(new Encounter(-25, 30, 30, 0.4));
			encounters.add(new Encounter(-69, 35, 35, 0.2));
			encounters.add(new Encounter(-70, 35, 35, 0.1));
		}
		
		return encounters;
	}

}
