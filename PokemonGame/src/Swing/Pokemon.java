package Swing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Pokemon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7087373480262097882L;
	
	protected int id;
	public String name;
	
	public int[] baseStats;
	public int[] stats;
	private int level;
	public int[] statStages;
	
	public PType type1;
	public PType type2;
	
	private Move[] movebank;
	public Move[] moveset;
	
	private Status status;
	
	public int exp;
	public int expMax;
	
	public int currentHP;
	private boolean fainted;

	private boolean charged;
	private int confusionCounter;
	@SuppressWarnings("unused") private int sleepCounter;
	@SuppressWarnings("unused") private int perishCount;
	
	public Pokemon(int i, int l) {
		id = i;
		name = getName();
		
		baseStats = new int[6];
		stats = new int[6];
		level = l;
		statStages = new int[7];
		
		setBaseStats();
		getStats();
		setType();
		
		expMax = level * 2;
		exp = 0;
		
		currentHP = this.getStat(0);
		moveset = new Move[4];
		setMoveBank();
		setMoves();
		
		status = Status.HEALTHY;
		
	}
	
	public void setMoves() {
		int index = 0;
		for (int i = 0; i < level && i < movebank.length; i++) {
			if (movebank[i] != null) {
				moveset[index] = movebank[i];
				index++;
			}
			if (index >= 4) index = 0;
		}
	}
	
	public Move randomMove() {
	    ArrayList<Move> validMoves = new ArrayList<>();

	    // Add all non-null moves to the validMoves list
	    for (Move move : moveset) {
	        if (move != null) {
	            validMoves.add(move);
	        }
	    }

	    // Pick a random move from the validMoves list
	    Random rand = new Random();
	    int index = rand.nextInt(validMoves.size());
	    return validMoves.get(index);
	}

	public Pokemon(int i, int l, Move[] set) {
		id = i;
		name = getName();
		
		baseStats = new int[6];
		stats = new int[6];
		level = l;
		
		setBaseStats();
		getStats();
		setType();
		
		expMax = level * 2;
		exp = 0;
		
		currentHP = this.getStat(0);
		setMoveBank();
		moveset = set;
		
		status = Status.HEALTHY;
		
	}
	
	public boolean isFainted() {
		return this.fainted;
	}
	

	private void setType() {
		if (id == 1) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == 2) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == 3) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == 4) {
			this.type1 = PType.FIRE;
			this.type2 = null;
		} else if (id == 5) {
			this.type1 = PType.FIRE;
			this.type2 = null;
		} else if (id == 6) {
			this.type1 = PType.FIRE;
			this.type2 = PType.ROCK;
		} else if (id == 7) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == 8) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == 9) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == 10) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == 11) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == 12) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == 13) {
			this.type1 = PType.GRASS;
			this.type2 = PType.FIGHTING;
		} else if (id == 14) {
			this.type1 = PType.NORMAL;
			this.type2 = null;
		} else if (id == 15) {
			this.type1 = PType.NORMAL;
			this.type2 = null;
		} else if (id == 16) {
			this.type1 = PType.NORMAL;
			this.type2 = null;
		} else if (id == 17) {
			this.type1 = PType.NORMAL;
			this.type2 = null;
		} else if (id == 18) {
			this.type1 = PType.ROCK;
			this.type2 = PType.GROUND;
		} else if (id == 19) {
			this.type1 = PType.ROCK;
			this.type2 = PType.GROUND;
		} else if (id == 20) {
			this.type1 = PType.ROCK;
			this.type2 = PType.GROUND;
		} else if (id == 21) {
			this.type1 = PType.ROCK;
			this.type2 = null;
		} else if (id == 22) {
			this.type1 = PType.ROCK;
			this.type2 = null;
		} else if (id == 23) {
			this.type1 = PType.ROCK;
			this.type2 = PType.FLYING;
		} else if (id == 24) {
			this.type1 = PType.WATER;
			this.type2 = PType.DARK;
		} else if (id == 25) {
			this.type1 = PType.WATER;
			this.type2 = PType.ELECTRIC;
		} else if (id == 26) {
			this.type1 = PType.WATER;
			this.type2 = PType.FLYING;
		} else if (id == 27) {
			this.type1 = PType.WATER;
			this.type2 = PType.FLYING;
		} else if (id == 28) {
			this.type1 = PType.WATER;
			this.type2 = PType.FLYING;
		} else if (id == 29) {
			this.type1 = PType.NORMAL;
			this.type2 = PType.FLYING;
		} else if (id == 30) {
			this.type1 = PType.NORMAL;
			this.type2 = PType.FLYING;
		} else if (id == 31) {
			this.type1 = PType.WATER;
			this.type2 = PType.FLYING;
		} else if (id == 32) {
			this.type1 = PType.GRASS;
			this.type2 = PType.FLYING;
		} else if (id == 33) {
			this.type1 = PType.BUG;
			this.type2 = PType.POISON;
		} else if (id == 34) {
			this.type1 = PType.WATER;
			this.type2 = PType.POISON;
		} else if (id == 35) {
			this.type1 = PType.BUG;
			this.type2 = null;
		} else if (id == 36) {
			this.type1 = PType.BUG;
			this.type2 = PType.POISON;
		} else if (id == 37) {
			this.type1 = PType.GHOST;
			this.type2 = PType.POISON;
		} else if (id == 38) {
			this.type1 = PType.GHOST;
			this.type2 = null;
		} else if (id == 39) {
			this.type1 = PType.GHOST;
			this.type2 = PType.STEEL;
		} else if (id == 40) {
			this.type1 = PType.GHOST;
			this.type2 = PType.DARK;
		} else if (id == 41) {
			this.type1 = PType.GHOST;
			this.type2 = PType.DARK;
		} else if (id == 42) {
			this.type1 = PType.ELECTRIC;
			this.type2 = null;
		} else if (id == 43) {
			this.type1 = PType.NORMAL;
			this.type2 = PType.ELECTRIC;
		} else if (id == 44) {
			this.type1 = PType.ELECTRIC;
			this.type2 = null;
		} else if (id == 45) {
			this.type1 = PType.ELECTRIC;
			this.type2 = PType.STEEL;;
		} else if (id == 46) {
			this.type1 = PType.ELECTRIC;
			this.type2 = null;
		} else if (id == 47) {
			this.type1 = PType.ELECTRIC;
			this.type2 = null;
		} else if (id == 48) {
			this.type1 = PType.BUG;
			this.type2 = PType.FIGHTING;
		} else if (id == 49) {
			this.type1 = PType.BUG;
			this.type2 = PType.FIGHTING;
		} else if (id == 50) {
			this.type1 = PType.BUG;
			this.type2 = PType.FIGHTING;
		} else if (id == 51) {
			this.type1 = PType.FIGHTING;
			this.type2 = null;
		} else if (id == 52) {
			this.type1 = PType.FIGHTING;
			this.type2 = null;
		} else if (id == 53) {
			this.type1 = PType.FIGHTING;
			this.type2 = null;
		} else if (id == 54) {
			this.type1 = PType.FIGHTING;
			this.type2 = null;
		} else if (id == 55) {
			this.type1 = PType.POISON;
			this.type2 = null;
		} else if (id == 56) {
			this.type1 = PType.POISON;
			this.type2 = null;
		} else if (id == 57) {
			this.type1 = PType.POISON;
			this.type2 = PType.FLYING;
		} else if (id == 58) {
			this.type1 = PType.POISON;
			this.type2 = PType.FLYING;
		} else if (id == 59) {
			this.type1 = PType.POISON;
			this.type2 = PType.GRASS;
		} else if (id == 60) {
			this.type1 = PType.POISON;
			this.type2 = PType.GRASS;
		} else if (id == 61) {
			this.type1 = PType.WATER;
			this.type2 = PType.POISON;
		} else if (id == 62) {
			this.type1 = PType.WATER;
			this.type2 = PType.POISON;
		} else if (id == 63) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == 64) {
			this.type1 = PType.WATER;
			this.type2 = PType.FIGHTING;
		} else if (id == 65) {
			this.type1 = PType.WATER;
			this.type2 = PType.FLYING;
		} else if (id == 66) {
			this.type1 = PType.WATER;
			this.type2 = PType.FLYING;
		} else if (id == 67) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == 68) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == 69) {
			this.type1 = PType.WATER;
			this.type2 = PType.DARK;
		} else if (id == 70) {
			this.type1 = PType.WATER;
			this.type2 = PType.DRAGON;
		} else if (id == 71) {
			this.type1 = PType.GROUND;
			this.type2 = null;
		} else if (id == 72) {
			this.type1 = PType.GROUND;
			this.type2 = null;
		} else if (id == 73) {
			this.type1 = PType.GROUND;
			this.type2 = PType.FIGHTING;
		} else if (id == 74) {
			this.type1 = PType.FIRE;
			this.type2 = null;
		} else if (id == 75) {
			this.type1 = PType.FIRE;
			this.type2 = PType.FIGHTING;
		} else if (id == 76) {
			this.type1 = PType.FIRE;
			this.type2 = PType.FIGHTING;
		} else if (id == 77) {
			this.type1 = PType.FIRE;
			this.type2 = PType.GROUND;
		} else if (id == 78) {
			this.type1 = PType.FIRE;
			this.type2 = PType.FLYING;
		} else if (id == 79) {
			this.type1 = PType.FIRE;
			this.type2 = PType.DRAGON;
		} else if (id == 80) {
			this.type1 = PType.FIRE;
			this.type2 = PType.STEEL;
		} else if (id == 81) {
			this.type1 = PType.FIRE;
			this.type2 = PType.STEEL;
		} else if (id == 82) {
			this.type1 = PType.WATER;
			this.type2 = PType.FLYING;
		} else if (id == 83) {
			this.type1 = PType.ELECTRIC;
			this.type2 = PType.FLYING;
		} else if (id == 84) {
			this.type1 = PType.FIRE;
			this.type2 = null;
		} else if (id == 85) {
			this.type1 = PType.FIRE;
			this.type2 = PType.FLYING;
		} else if (id == 86) {
			this.type1 = PType.DARK;
			this.type2 = PType.BUG;
		} else if (id == 87) {
			this.type1 = PType.DARK;
			this.type2 = PType.BUG;
		} else if (id == 88) {
			this.type1 = PType.BUG;
			this.type2 = PType.STEEL;
		} else if (id == 89) {
			this.type1 = PType.DARK;
			this.type2 = null;
		} else if (id == 90) {
			this.type1 = PType.DARK;
			this.type2 = null;
		} else if (id == 91) {
			this.type1 = PType.DARK;
			this.type2 = PType.MAGIC;
		} else if (id == 92) {
			this.type1 = PType.ELECTRIC;
			this.type2 = null;
		} else if (id == 93) {
			this.type1 = PType.GHOST;
			this.type2 = PType.FIRE;
		} else if (id == 94) {
			this.type1 = PType.GHOST;
			this.type2 = PType.FIRE;
		} else if (id == 95) {
			this.type1 = PType.NORMAL;
			this.type2 = PType.STEEL;
		} else if (id == 96) {
			this.type1 = PType.NORMAL;
			this.type2 = PType.STEEL;
		} else if (id == 97) {
			this.type1 = PType.STEEL;
			this.type2 = null;
		} else if (id == 98) {
			this.type1 = PType.STEEL;
			this.type2 = null;
		} else if (id == 99) {
			this.type1 = PType.STEEL;
			this.type2 = null;
		} else if (id == 100) {
			this.type1 = PType.DRAGON;
			this.type2 = null;
		} else if (id == 101) {
			this.type1 = PType.DRAGON;
			this.type2 = null;
		} else if (id == 102) {
			this.type1 = PType.DRAGON;
			this.type2 = PType.FLYING;
		} else if (id == 103) {
			this.type1 = PType.DRAGON;
			this.type2 = PType.FIGHTING;
		} else if (id == 104) {
			this.type1 = PType.DRAGON;
			this.type2 = PType.FLYING;
		} else if (id == 105) {
			this.type1 = PType.DRAGON;
			this.type2 = PType.FIRE;
		} else if (id == 106) {
			this.type1 = PType.MAGIC;
			this.type2 = null;
		} else if (id == 107) {
			this.type1 = PType.MAGIC;
			this.type2 = null;
		} else if (id == 108) {
			this.type1 = PType.MAGIC;
			this.type2 = PType.FIRE;
		} else if (id == 109) {
			this.type1 = PType.MAGIC;
			this.type2 = null;
		} else if (id == 110) {
			this.type1 = PType.MAGIC;
			this.type2 = null;
		} else if (id == 111) {
			this.type1 = PType.MAGIC;
			this.type2 = null;
		} else if (id == 112) {
			this.type1 = PType.NORMAL;
			this.type2 = null;
		} else if (id == 113) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == 114) {
			this.type1 = PType.FIRE;
			this.type2 = null;
		} else if (id == 115) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == 116) {
			this.type1 = PType.POISON;
			this.type2 = null;
		} else if (id == 117) {
			this.type1 = PType.ELECTRIC;
			this.type2 = null;
		} else if (id == 118) {
			this.type1 = PType.ROCK;
			this.type2 = null;
		} else if (id == 119) {
			this.type1 = PType.DARK;
			this.type2 = null;
		} else if (id == 120) {
			this.type1 = PType.STEEL;
			this.type2 = null;
		} else if (id == 121) {
			this.type1 = PType.FIGHTING;
			this.type2 = null;
		} else if (id == 122) {
			this.type1 = PType.DRAGON;
			this.type2 = null;
		} else if (id == 123) {
			this.type1 = PType.MAGIC;
			this.type2 = null;
		} else if (id == 124) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == 125) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == 126) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == 127) {
			this.type1 = PType.FIRE;
			this.type2 = null;
		} else if (id == 128) {
			this.type1 = PType.FIRE;
			this.type2 = null;
		} else if (id == 129) {
			this.type1 = PType.FIRE;
			this.type2 = null;
		} else if (id == 130) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == 131) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == 132) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == 133) {
			this.type1 = PType.ELECTRIC;
			this.type2 = PType.DRAGON;
		} else if (id == 134) {
			this.type1 = PType.FIRE;
			this.type2 = PType.DRAGON;
		} else if (id == 135) {
			this.type1 = PType.NORMAL;
			this.type2 = PType.MAGIC;
		} else if (id == 136) {
			this.type1 = PType.BUG;
			this.type2 = PType.MAGIC;
		} else if (id == 137) {
			this.type1 = PType.FLYING;
			this.type2 = PType.MAGIC;
		} else if (id == 138) {
			this.type1 = PType.NORMAL;
			this.type2 = PType.MAGIC;
		} else if (id == 139) {
			this.type1 = PType.BUG;
			this.type2 = PType.MAGIC;
		} else if (id == 140) {
			this.type1 = PType.FLYING;
			this.type2 = PType.MAGIC;
		} else if (id == 141) {
			this.type1 = PType.POISON;
			this.type2 = PType.FIRE;
		} else if (id == 142) {
			this.type1 = PType.POISON;
			this.type2 = PType.WATER;
		} else if (id == 143) {
			this.type1 = PType.POISON;
			this.type2 = PType.FIRE;
		} else if (id == 144) {
			this.type1 = PType.POISON;
			this.type2 = PType.WATER;
		}
		
	}

	
	@Override // implementation
	public String toString() {
		String typeString = type1.toString();
	    if (type2 != null) {
	        typeString += ", " + type2.toString();
	    }
	    String expBar = "";
	    int expPercent = (int)((double)exp / expMax * 100);
	    for (int i = 0; i < 10; i++) {
	        if (expPercent >= i * 10) {
	            expBar += "|";
	        } else {
	            expBar += ".";
	        }
	    }
	    
	    String moveString = "";
	    
	    for (int i = 0; i < moveset.length; i++) {
	    	if (moveset[i] != null) {
	    		moveString += moveset[i].toString();
	    		if (i != moveset.length - 1) {
	    			moveString += " / ";
	    		}
	    	}
	    }
	    return name + " (" + typeString + ") lv. " + level + " | Stats: " + intArrayToString(stats) 
	           + ", Base Stats: " + intArrayToString(baseStats) + " (TOTAL: " + this.getBST() + ")\n"
	           + "(" + currentHP + "/" + this.getStat(0) + ") [" + status + "] | EXP: " + exp + "/" + expMax + " " + expBar + "\n" + moveString;
	}

	private String intArrayToString(int[] arr) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");
	    for (int i = 0; i < arr.length; i++) {
	        sb.append(arr[i]);
	        if (i != arr.length - 1) {
	            sb.append(", ");
	        }
	    }
	    sb.append("]");
	    return sb.toString();
	}
	
	public String getName() {
		if (id == 1) { return "Leafer";
		} else if (id == 2) { return "Sticker";
		} else if (id == 3) { return "Tree-a-tar";
		} else if (id == 4) { return "Fwoochar";
		} else if (id == 5) { return "Charchar";
		} else if (id == 6) { return "Charwoo";
		} else if (id == 7) { return "Poletad";
		} else if (id == 8) { return "Tadwhirl";
		} else if (id == 9) { return "Tadtoad";
		} else if (id == 10) { return "Twigzig";
		} else if (id == 11) { return "Twanzig";
		} else if (id == 12) { return "Sapwin";
		} else if (id == 13) { return "Treewin";
		} else if (id == 14) { return "Hops";
		} else if (id == 15) { return "Bouncey";
		} else if (id == 16) { return "Hammo";
		} else if (id == 17) { return "HammyBoy";
		} else if (id == 18) { return "Rocky";
		} else if (id == 19) { return "Boulder";
		} else if (id == 20) { return "Blaster";
		} else if (id == 21) { return "Spike";
		} else if (id == 22) { return "Spiko";
		} else if (id == 23) { return "Spikoga";
		} else if (id == 24) { return "Chompee";
		} else if (id == 25) { return "Chompo";
		} else if (id == 26) { return "Duckwee";
		} else if (id == 27) { return "Duckwack";
		} else if (id == 28) { return "Duckstrike";
		} else if (id == 29) { return "Chirpus";
		} else if (id == 30) { return "Quackus";
		} else if (id == 31) { return "Crane";
		} else if (id == 32) { return "Plankik";
		} else if (id == 33) { return "Nug";
		} else if (id == 34) { return "Contrug";
		} else if (id == 35) { return "Wasp";
		} else if (id == 36) { return "Mosquitto";
		} else if (id == 37) { return "Cluuz";
		} else if (id == 38) { return "Poof";
		} else if (id == 39) { return "Hast";
		} else if (id == 40) { return "Poov";
		} else if (id == 41) { return "Grust";
		} else if (id == 42) { return "Smartwiz";
		} else if (id == 43) { return "Vinnie";
		} else if (id == 44) { return "Shookwat";
		} else if (id == 45) { return "Wattwo";
		} else if (id == 46) { return "Corry";
		} else if (id == 47) { return "Ssordy";
		} else if (id == 48) { return "Bugik";
		} else if (id == 49) { return "Swordik";
		} else if (id == 50) { return "Ninjakik";
		} else if (id == 51) { return "Karachop";
		} else if (id == 52) { return "Karapunch";
		} else if (id == 53) { return "Karakik";
		} else if (id == 54) { return "Karasword";
		} else if (id == 55) { return "Sludger";
		} else if (id == 56) { return "Gludge";
		} else if (id == 57) { return "Wing";
		} else if (id == 58) { return "Toxing";
		} else if (id == 59) { return "Gelco-G";
		} else if (id == 60) { return "Lizardo-G";
		} else if (id == 61) { return "Jorid";
		} else if (id == 62) { return "Tentarid";
		} else if (id == 63) { return "Hedgehog";
		} else if (id == 64) { return "Bulldozer";
		} else if (id == 65) { return "Daray";
		} else if (id == 66) { return "Spinaquata";
		} else if (id == 67) { return "Toree";
		} else if (id == 68) { return "Turdee";
		} else if (id == 69) { return "Ali";
		} else if (id == 70) { return "Shomp";
		} else if (id == 71) { return "Rhypo";
		} else if (id == 72) { return "Rhynee";
		} else if (id == 73) { return "Rhypolar";
		} else if (id == 74) { return "Ignite";
		} else if (id == 75) { return "Blaze";
		} else if (id == 76) { return "Inferno";
		} else if (id == 77) { return "Flamehead";
		} else if (id == 78) { return "Fireshard";
		} else if (id == 79) { return "Blastflames";
		} else if (id == 80) { return "Heater";
		} else if (id == 81) { return "Froller";
		} else if (id == 82) { return "Cloudorus";
		} else if (id == 83) { return "Stormatus";
		} else if (id == 84) { return "Creeflare";
		} else if (id == 85) { return "Flyflare";
		} else if (id == 86) { return "Tara-z";
		} else if (id == 87) { return "Tara-x";
		} else if (id == 88) { return "Savahger";
		} else if (id == 89) { return "Show";
		} else if (id == 90) { return "Dark Zombie";
		} else if (id == 91) { return "Diftery";
		} else if (id == 92) { return "Electroball";
		} else if (id == 93) { return "Ghast";
		} else if (id == 94) { return "Flast";
		} else if (id == 95) { return "Magie";
		} else if (id == 96) { return "Cumin";
		} else if (id == 97) { return "Droid";
		} else if (id == 98) { return "Armoid";
		} else if (id == 99) { return "Soldrota";
		} else if (id == 100) { return "Dragee";
		} else if (id == 101) { return "Draga";
		} else if (id == 102) { return "Drageye";
		} else if (id == 103) { return "Soardine-A";
		} else if (id == 104) { return "Soardine-D";
		} else if (id == 105) { return "Soardine-F";
		} else if (id == 106) { return "Wando";
		} else if (id == 107) { return "Sparkdust";
		} else if (id == 108) { return "Splame";
		} else if (id == 109) { return "Tinkie";
		} else if (id == 110) { return "Shawar";
		} else if (id == 111) { return "Shaboom";
		} else if (id == 112) { return "Dogo";
		} else if (id == 113) { return "Doleaf";
		} else if (id == 114) { return "Drame";
		} else if (id == 115) { return "Daqua";
		} else if (id == 116) { return "Doxic";
		} else if (id == 117) { return "Dratt";
		} else if (id == 118) { return "Drock";
		} else if (id == 119) { return "Dokurk";
		} else if (id == 120) { return "Drotal";
		} else if (id == 121) { return "Drunch";
		} else if (id == 122) { return "Draco";
		} else if (id == 123) { return "Drakik";
		} else if (id == 124) { return "Plantro";
		} else if (id == 125) { return "Leafron";
		} else if (id == 126) { return "Planterra";
		} else if (id == 127) { return "Sparky";
		} else if (id == 128) { return "Fireball";
		} else if (id == 129) { return "Meteator";
		} else if (id == 130) { return "Taddy";
		} else if (id == 131) { return "Tarow";
		} else if (id == 132) { return "Tadagater";
		} else if (id == 133) { return "Zaparch";
		} else if (id == 134) { return "Zaflame";
		} else if (id == 135) { return "Holgor";
		} else if (id == 136) { return "Larvangor";
		} else if (id == 137) { return "Fleigor";
		} else if (id == 138) { return "Halgatoria";
		} else if (id == 139) { return "Lavatoria";
		} else if (id == 140) { return "Wingatoria";
		} else if (id == 141) { return "Gelco-F";
		} else if (id == 142) { return "Gelco-W";
		} else if (id == 143) { return "Lizardo-F";
		} else if (id == 144) { return "Lizardo-W";
		} return "";
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getCurrentHP() {
		return this.currentHP;
	}

	public Pokemon levelUp() {
		int oHP = this.getStat(0);
		this.exp -= this.expMax;
		++level;
		System.out.println(this.name + " leveled Up!");
		Pokemon result = this.checkEvo();
		expMax = this.level * 2;
		stats = this.getStats();
		int nHP = this.getStat(0);
		this.currentHP += nHP - oHP;
		return result;
		
	}
	
	private Pokemon checkEvo() {
		if (id == 1 && level >= 15) {
			Pokemon result = new Pokemon(2, level, this.moveset);
			int hpDif = this.getStat(0) - this.currentHP;
			result.currentHP -= hpDif;
			System.out.println(this.name + " evolved into " + result.name + "!");
			return result;
		} else if (id == 2 && level >= 35) {
			Pokemon result = new Pokemon(3, level, this.moveset);
			int hpDif = this.getStat(0) - this.currentHP;
			result.currentHP -= hpDif;
			System.out.println(this.name + " evolved into " + result.name + "!");
			return result;
		} else if (id == 2 && level >= 35) {
			Pokemon result = new Pokemon(3, level, this.moveset);
			int hpDif = this.getStat(0) - this.currentHP;
			result.currentHP -= hpDif;
			System.out.println(this.name + " evolved into " + result.name + "!");
			return result;
		}
		return this;
		
	}

	public int[] getStats() {
		double HPnum = 2 * baseStats[0] * level;
		stats[0] = (int) (Math.floor(HPnum/100) + level + 10);
		double Atknum = 2 * baseStats[1] * level;
		stats[1] = (int) (Math.floor(Atknum/100) + 5);
		double Defnum = 2 * baseStats[2] * level;
		stats[2] = (int) (Math.floor(Defnum/100) + 5);
		double SpAnum = 2 * baseStats[3] * level;
		stats[3] = (int) (Math.floor(SpAnum/100) + 5);
		double SpDnum = 2 * baseStats[4] * level;
		stats[4] = (int) (Math.floor(SpDnum/100) + 5);
		double Spenum = 2 * baseStats[5] * level;
		stats[5] = (int) (Math.floor(Spenum/100) + 5);
		return stats;
	}
	
	public int getStat(int type) {
		if (type == 0) {
			return this.stats[0];
		} else if (type == 1) {
			return this.stats[1];
		} else if (type == 2) {
			return this.stats[2];
		} else if (type == 3) {
			return this.stats[3];
		} else if (type == 4) {
			return this.stats[4];
		} else if (type == 5) {
			return this.stats[5];
		} else {
			return 0;
		}
	}

	public int getBaseStat(int type) {
		if (type == 0) {
			return this.baseStats[0];
		} else if (type == 1) {
			return this.baseStats[1];
		} else if (type == 2) {
			return this.baseStats[2];
		} else if (type == 3) {
			return this.baseStats[3];
		} else if (type == 4) {
			return this.baseStats[4];
		} else if (type == 5) {
			return this.baseStats[5];
		} else if (type == 6) {
			return this.baseStats[6];
		} else {
			return 0;
		}
	}
	
	public int getBST() {
		int BST = 0;
		for (int i = 0; i <= 5; i++) {
			BST += baseStats[i];
		}
		return BST;
	}
	
	public String toString(int[] arr) {
		StringBuilder sb = new StringBuilder();
	    sb.append("[");
	    for (int i = 0; i < arr.length; i++) {
	        sb.append(arr[i]);
	        if (i != arr.length - 1) {
	            sb.append(", ");
	        }
	    }
	    sb.append("]");
	    return sb.toString();
	}
	
	public int[] setBaseStats() {
		if (this.id == 1) {
			this.baseStats[0] = 35;
			this.baseStats[1] = 46;
			this.baseStats[2] = 45;
			this.baseStats[3] = 67;
			this.baseStats[4] = 70;
			this.baseStats[5] = 63;
			return this.baseStats;
		} else if (this.id == 2) {
			this.baseStats[0] = 64;
			this.baseStats[1] = 54;
			this.baseStats[2] = 85;
			this.baseStats[3] = 67;
			this.baseStats[4] = 78;
			this.baseStats[5] = 60;
			return this.baseStats;
		} else if (this.id == 3) {
			this.baseStats[0] = 100;
			this.baseStats[1] = 70;
			this.baseStats[2] = 120;
			this.baseStats[3] = 90;
			this.baseStats[4] = 85;
			this.baseStats[5] = 60;
			return this.baseStats;
		} else if (this.id == 4) {
			this.baseStats[0] = 59;
			this.baseStats[1] = 46;
			this.baseStats[2] = 67;
			this.baseStats[3] = 62;
			this.baseStats[4] = 55;
			this.baseStats[5] = 31;
			return this.baseStats;
		} else if (this.id == 5) {
			this.baseStats[0] = 92;
			this.baseStats[1] = 51;
			this.baseStats[2] = 72;
			this.baseStats[3] = 75;
			this.baseStats[4] = 65;
			this.baseStats[5] = 45;
			return this.baseStats;
		} else if (this.id == 6) {
			this.baseStats[0] = 95;
			this.baseStats[1] = 54;
			this.baseStats[2] = 75;
			this.baseStats[3] = 109;
			this.baseStats[4] = 130;
			this.baseStats[5] = 55;
			return this.baseStats;
		} else if (this.id == 7) {
			this.baseStats[0] = 44;
			this.baseStats[1] = 62;
			this.baseStats[2] = 46;
			this.baseStats[3] = 54;
			this.baseStats[4] = 55;
			this.baseStats[5] = 62;
			return this.baseStats;
		} else if (this.id == 8) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 79;
			this.baseStats[2] = 53;
			this.baseStats[3] = 55;
			this.baseStats[4] = 65;
			this.baseStats[5] = 78;
			return this.baseStats;
		} else if (this.id == 9) {
			this.baseStats[0] = 76;
			this.baseStats[1] = 81;
			this.baseStats[2] = 65;
			this.baseStats[3] = 106;
			this.baseStats[4] = 95;
			this.baseStats[5] = 108;
			return this.baseStats;
		} else if (this.id == 10) {
			this.baseStats[0] = 36;
			this.baseStats[1] = 63;
			this.baseStats[2] = 47;
			this.baseStats[3] = 24;
			this.baseStats[4] = 62;
			this.baseStats[5] = 58;
			return this.baseStats;
		} else if (this.id == 11) {
			this.baseStats[0] = 50;
			this.baseStats[1] = 105;
			this.baseStats[2] = 50;
			this.baseStats[3] = 54;
			this.baseStats[4] = 56;
			this.baseStats[5] = 105;
			return this.baseStats;
		} else if (this.id == 12) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 105;
			this.baseStats[2] = 100;
			this.baseStats[3] = 50;
			this.baseStats[4] = 30;
			this.baseStats[5] = 55;
			return this.baseStats;
		} else if (this.id == 13) {
			this.baseStats[0] = 120;
			this.baseStats[1] = 125;
			this.baseStats[2] = 120;
			this.baseStats[3] = 60;
			this.baseStats[4] = 35;
			this.baseStats[5] = 55;
			return this.baseStats;
		} else if (this.id == 14) {
			this.baseStats[0] = 50;
			this.baseStats[1] = 63;
			this.baseStats[2] = 41;
			this.baseStats[3] = 22;
			this.baseStats[4] = 63;
			this.baseStats[5] = 66;
			return this.baseStats;
		} else if (this.id == 15) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 89;
			this.baseStats[2] = 55;
			this.baseStats[3] = 25;
			this.baseStats[4] = 76;
			this.baseStats[5] = 95;
			return this.baseStats;
		} else if (this.id == 16) {
			this.baseStats[0] = 99;
			this.baseStats[1] = 61;
			this.baseStats[2] = 51;
			this.baseStats[3] = 40;
			this.baseStats[4] = 79;
			this.baseStats[5] = 20;
			return this.baseStats;
		} else if (this.id == 17) {
			this.baseStats[0] = 170;
			this.baseStats[1] = 64;
			this.baseStats[2] = 55;
			this.baseStats[3] = 43;
			this.baseStats[4] = 103;
			this.baseStats[5] = 55;
			return this.baseStats;
		} else if (this.id == 18) {
			this.baseStats[0] = 68;
			this.baseStats[1] = 72;
			this.baseStats[2] = 122;
			this.baseStats[3] = 20;
			this.baseStats[4] = 25;
			this.baseStats[5] = 18;
			return this.baseStats;
		} else if (this.id == 19) {
			this.baseStats[0] = 87;
			this.baseStats[1] = 95;
			this.baseStats[2] = 143;
			this.baseStats[3] = 25;
			this.baseStats[4] = 30;
			this.baseStats[5] = 45;
			return this.baseStats;
		} else if (this.id == 20) {
			this.baseStats[0] = 90;
			this.baseStats[1] = 100;
			this.baseStats[2] = 143;
			this.baseStats[3] = 100;
			this.baseStats[4] = 42;
			this.baseStats[5] = 50;
			return this.baseStats;
		} else if (this.id == 21) {
			this.baseStats[0] = 45;
			this.baseStats[1] = 40;
			this.baseStats[2] = 105;
			this.baseStats[3] = 40;
			this.baseStats[4] = 75;
			this.baseStats[5] = 40;
			return this.baseStats;
		} else if (this.id == 22) {
			this.baseStats[0] = 81;
			this.baseStats[1] = 66;
			this.baseStats[2] = 105;
			this.baseStats[3] = 40;
			this.baseStats[4] = 75;
			this.baseStats[5] = 45;
			return this.baseStats;
		} else if (this.id == 23) {
			this.baseStats[0] = 100;
			this.baseStats[1] = 110;
			this.baseStats[2] = 105;
			this.baseStats[3] = 44;
			this.baseStats[4] = 78;
			this.baseStats[5] = 93;
			return this.baseStats;
		} else if (this.id == 24) {
			this.baseStats[0] = 35;
			this.baseStats[1] = 103;
			this.baseStats[2] = 62;
			this.baseStats[3] = 25;
			this.baseStats[4] = 30;
			this.baseStats[5] = 95;
			return this.baseStats;
		} else if (this.id == 25) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 80;
			this.baseStats[2] = 65;
			this.baseStats[3] = 80;
			this.baseStats[4] = 70;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == 26) {
			this.baseStats[0] = 68;
			this.baseStats[1] = 35;
			this.baseStats[2] = 66;
			this.baseStats[3] = 45;
			this.baseStats[4] = 60;
			this.baseStats[5] = 44;
			return this.baseStats;
		} else if (this.id == 27) {
			this.baseStats[0] = 70;
			this.baseStats[1] = 40;
			this.baseStats[2] = 67;
			this.baseStats[3] = 73;
			this.baseStats[4] = 70;
			this.baseStats[5] = 80;
			return this.baseStats;
		} else if (this.id == 28) {
			this.baseStats[0] = 72;
			this.baseStats[1] = 91;
			this.baseStats[2] = 53;
			this.baseStats[3] = 80;
			this.baseStats[4] = 75;
			this.baseStats[5] = 124;
			return this.baseStats;
		} else if (this.id == 29) {
			this.baseStats[0] = 30;
			this.baseStats[1] = 36;
			this.baseStats[2] = 64;
			this.baseStats[3] = 40;
			this.baseStats[4] = 70;
			this.baseStats[5] = 72;
			return this.baseStats;
		} else if (this.id == 30) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 65;
			this.baseStats[2] = 125;
			this.baseStats[3] = 45;
			this.baseStats[4] = 68;
			this.baseStats[5] = 74;
			return this.baseStats;
		} else if (this.id == 31) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 96;
			this.baseStats[2] = 80;
			this.baseStats[3] = 65;
			this.baseStats[4] = 70;
			this.baseStats[5] = 74;
			return this.baseStats;
		} else if (this.id == 32) {
			this.baseStats[0] = 90;
			this.baseStats[1] = 90;
			this.baseStats[2] = 65;
			this.baseStats[3] = 35;
			this.baseStats[4] = 100;
			this.baseStats[5] = 60;
			return this.baseStats;
		} else if (this.id == 33) {
			this.baseStats[0] = 40;
			this.baseStats[1] = 64;
			this.baseStats[2] = 78;
			this.baseStats[3] = 35;
			this.baseStats[4] = 56;
			this.baseStats[5] = 37;
			return this.baseStats;
		} else if (this.id == 34) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 80;
			this.baseStats[2] = 80;
			this.baseStats[3] = 75;
			this.baseStats[4] = 65;
			this.baseStats[5] = 60;
			return this.baseStats;
		} else if (this.id == 35) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 87;
			this.baseStats[2] = 71;
			this.baseStats[3] = 63;
			this.baseStats[4] = 60;
			this.baseStats[5] = 64;
			return this.baseStats;
		} else if (this.id == 36) {
			this.baseStats[0] = 40;
			this.baseStats[1] = 73;
			this.baseStats[2] = 40;
			this.baseStats[3] = 105;
			this.baseStats[4] = 45;
			this.baseStats[5] = 97;
			return this.baseStats;
		} else if (this.id == 37) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 42;
			this.baseStats[2] = 58;
			this.baseStats[3] = 105;
			this.baseStats[4] = 97;
			this.baseStats[5] = 73;
			return this.baseStats;
		} else if (this.id == 38) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 75;
			this.baseStats[2] = 80;
			this.baseStats[3] = 60;
			this.baseStats[4] = 65;
			this.baseStats[5] = 65;
			return this.baseStats;
		} else if (this.id == 39) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 70;
			this.baseStats[2] = 120;
			this.baseStats[3] = 60;
			this.baseStats[4] = 120;
			this.baseStats[5] = 65;
			return this.baseStats;
		} else if (this.id == 40) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 80;
			this.baseStats[2] = 55;
			this.baseStats[3] = 65;
			this.baseStats[4] = 60;
			this.baseStats[5] = 64;
			return this.baseStats;
		} else if (this.id == 41) {
			this.baseStats[0] = 125;
			this.baseStats[1] = 75;
			this.baseStats[2] = 55;
			this.baseStats[3] = 120;
			this.baseStats[4] = 60;
			this.baseStats[5] = 64;
			return this.baseStats;
		} else if (this.id == 42) {
			this.baseStats[0] = 63;
			this.baseStats[1] = 32;
			this.baseStats[2] = 44;
			this.baseStats[3] = 118;
			this.baseStats[4] = 89;
			this.baseStats[5] = 90;
			return this.baseStats;
		} else if (this.id == 43) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 100;
			this.baseStats[2] = 60;
			this.baseStats[3] = 105;
			this.baseStats[4] = 60;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == 44) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 62;
			this.baseStats[2] = 46;
			this.baseStats[3] = 73;
			this.baseStats[4] = 75;
			this.baseStats[5] = 49;
			return this.baseStats;
		} else if (this.id == 45) {
			this.baseStats[0] = 83;
			this.baseStats[1] = 65;
			this.baseStats[2] = 102;
			this.baseStats[3] = 115;
			this.baseStats[4] = 90;
			this.baseStats[5] = 50;
			return this.baseStats;
		} else if (this.id == 46) {
			this.baseStats[0] = 35;
			this.baseStats[1] = 65;
			this.baseStats[2] = 45;
			this.baseStats[3] = 75;
			this.baseStats[4] = 47;
			this.baseStats[5] = 63;
			return this.baseStats;
		} else if (this.id == 47) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 59;
			this.baseStats[2] = 69;
			this.baseStats[3] = 135;
			this.baseStats[4] = 71;
			this.baseStats[5] = 93;
			return this.baseStats;
		} else if (this.id == 48) {
			this.baseStats[0] = 40;
			this.baseStats[1] = 60;
			this.baseStats[2] = 65;
			this.baseStats[3] = 30;
			this.baseStats[4] = 50;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == 49) {
			this.baseStats[0] = 53;
			this.baseStats[1] = 95;
			this.baseStats[2] = 85;
			this.baseStats[3] = 30;
			this.baseStats[4] = 53;
			this.baseStats[5] = 85;
			return this.baseStats;
		} else if (this.id == 50) {
			this.baseStats[0] = 68;
			this.baseStats[1] = 120;
			this.baseStats[2] = 100;
			this.baseStats[3] = 72;
			this.baseStats[4] = 80;
			this.baseStats[5] = 105;
			return this.baseStats;
		} else if (this.id == 51) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 60;
			this.baseStats[2] = 45;
			this.baseStats[3] = 20;
			this.baseStats[4] = 75;
			this.baseStats[5] = 60;
			return this.baseStats;
		} else if (this.id == 52) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 105;
			this.baseStats[2] = 70;
			this.baseStats[3] = 20;
			this.baseStats[4] = 115;
			this.baseStats[5] = 65;
			return this.baseStats;
		} else if (this.id == 53) {
			this.baseStats[0] = 68;
			this.baseStats[1] = 92;
			this.baseStats[2] = 68;
			this.baseStats[3] = 20;
			this.baseStats[4] = 84;
			this.baseStats[5] = 118;
			return this.baseStats;
		} else if (this.id == 54) {
			this.baseStats[0] = 70;
			this.baseStats[1] = 125;
			this.baseStats[2] = 85;
			this.baseStats[3] = 20;
			this.baseStats[4] = 85;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == 55) {
			this.baseStats[0] = 95;
			this.baseStats[1] = 54;
			this.baseStats[2] = 77;
			this.baseStats[3] = 60;
			this.baseStats[4] = 72;
			this.baseStats[5] = 32;
			return this.baseStats;
		} else if (this.id == 56) {
			this.baseStats[0] = 100;
			this.baseStats[1] = 90;
			this.baseStats[2] = 77;
			this.baseStats[3] = 95;
			this.baseStats[4] = 72;
			this.baseStats[5] = 31;
			return this.baseStats;
		} else if (this.id == 57) {
			this.baseStats[0] = 40;
			this.baseStats[1] = 75;
			this.baseStats[2] = 40;
			this.baseStats[3] = 30;
			this.baseStats[4] = 40;
			this.baseStats[5] = 93;
			return this.baseStats;
		} else if (this.id == 58) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 102;
			this.baseStats[2] = 65;
			this.baseStats[3] = 35;
			this.baseStats[4] = 50;
			this.baseStats[5] = 107;
			return this.baseStats;
		} else if (this.id == 59) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 60;
			this.baseStats[2] = 40;
			this.baseStats[3] = 60;
			this.baseStats[4] = 55;
			this.baseStats[5] = 80;
			return this.baseStats;
		} else if (this.id == 60) {
			this.baseStats[0] = 105;
			this.baseStats[1] = 88;
			this.baseStats[2] = 60;
			this.baseStats[3] = 112;
			this.baseStats[4] = 65;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == 61) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 32;
			this.baseStats[2] = 75;
			this.baseStats[3] = 55;
			this.baseStats[4] = 100;
			this.baseStats[5] = 73;
			return this.baseStats;
		} else if (this.id == 62) {
			this.baseStats[0] = 71;
			this.baseStats[1] = 65;
			this.baseStats[2] = 110;
			this.baseStats[3] = 60;
			this.baseStats[4] = 110;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == 63) {
			this.baseStats[0] = 30;
			this.baseStats[1] = 30;
			this.baseStats[2] = 88;
			this.baseStats[3] = 70;
			this.baseStats[4] = 55;
			this.baseStats[5] = 45;
			return this.baseStats;
		} else if (this.id == 64) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 113;
			this.baseStats[2] = 58;
			this.baseStats[3] = 83;
			this.baseStats[4] = 25;
			this.baseStats[5] = 127;
			return this.baseStats;
		} else if (this.id == 65) {
			this.baseStats[0] = 100;
			this.baseStats[1] = 28;
			this.baseStats[2] = 45;
			this.baseStats[3] = 72;
			this.baseStats[4] = 110;
			this.baseStats[5] = 45;
			return this.baseStats;
		} else if (this.id == 66) {
			this.baseStats[0] = 100;
			this.baseStats[1] = 100;
			this.baseStats[2] = 76;
			this.baseStats[3] = 34;
			this.baseStats[4] = 94;
			this.baseStats[5] = 91;
			return this.baseStats;
		} else if (this.id == 67) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 40;
			this.baseStats[2] = 90;
			this.baseStats[3] = 40;
			this.baseStats[4] = 66;
			this.baseStats[5] = 34;
			return this.baseStats;
		} else if (this.id == 68) {
			this.baseStats[0] = 66;
			this.baseStats[1] = 76;
			this.baseStats[2] = 115;
			this.baseStats[3] = 54;
			this.baseStats[4] = 79;
			this.baseStats[5] = 40;
			return this.baseStats;
		} else if (this.id == 69) {
			this.baseStats[0] = 105;
			this.baseStats[1] = 125;
			this.baseStats[2] = 100;
			this.baseStats[3] = 45;
			this.baseStats[4] = 55;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == 70) {
			this.baseStats[0] = 81;
			this.baseStats[1] = 103;
			this.baseStats[2] = 63;
			this.baseStats[3] = 113;
			this.baseStats[4] = 90;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == 71) {
			this.baseStats[0] = 51;
			this.baseStats[1] = 55;
			this.baseStats[2] = 69;
			this.baseStats[3] = 40;
			this.baseStats[4] = 56;
			this.baseStats[5] = 35;
			return this.baseStats;
		} else if (this.id == 72) {
			this.baseStats[0] = 57;
			this.baseStats[1] = 73;
			this.baseStats[2] = 108;
			this.baseStats[3] = 40;
			this.baseStats[4] = 95;
			this.baseStats[5] = 39;
			return this.baseStats;
		} else if (this.id == 73) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 110;
			this.baseStats[2] = 110;
			this.baseStats[3] = 75;
			this.baseStats[4] = 400;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == 74) {
			this.baseStats[0] = 30;
			this.baseStats[1] = 20;
			this.baseStats[2] = 40;
			this.baseStats[3] = 103;
			this.baseStats[4] = 30;
			this.baseStats[5] = 83;
			return this.baseStats;
		} else if (this.id == 75) {
			this.baseStats[0] = 43;
			this.baseStats[1] = 88;
			this.baseStats[2] = 50;
			this.baseStats[3] = 74;
			this.baseStats[4] = 52;
			this.baseStats[5] = 93;
			return this.baseStats;
		} else if (this.id == 76) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 115;
			this.baseStats[2] = 55;
			this.baseStats[3] = 106;
			this.baseStats[4] = 75;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == 77) {
			this.baseStats[0] = 70;
			this.baseStats[1] = 53;
			this.baseStats[2] = 70;
			this.baseStats[3] = 55;
			this.baseStats[4] = 60;
			this.baseStats[5] = 52;
			return this.baseStats;
		} else if (this.id == 78) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 55;
			this.baseStats[2] = 75;
			this.baseStats[3] = 105;
			this.baseStats[4] = 75;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == 79) {
			this.baseStats[0] = 95;
			this.baseStats[1] = 75;
			this.baseStats[2] = 87;
			this.baseStats[3] = 140;
			this.baseStats[4] = 80;
			this.baseStats[5] = 83;
			return this.baseStats;
		} else if (this.id == 80) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 40;
			this.baseStats[2] = 105;
			this.baseStats[3] = 100;
			this.baseStats[4] = 65;
			this.baseStats[5] = 42;
			return this.baseStats;
		} else if (this.id == 81) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 90;
			this.baseStats[2] = 95;
			this.baseStats[3] = 90;
			this.baseStats[4] = 65;
			this.baseStats[5] = 82;
			return this.baseStats;
		} else if (this.id == 82) {
			this.baseStats[0] = 95;
			this.baseStats[1] = 40;
			this.baseStats[2] = 55;
			this.baseStats[3] = 58;
			this.baseStats[4] = 95;
			this.baseStats[5] = 62;
			return this.baseStats;
		} else if (this.id == 83) {
			this.baseStats[0] = 95;
			this.baseStats[1] = 60;
			this.baseStats[2] = 63;
			this.baseStats[3] = 105;
			this.baseStats[4] = 95;
			this.baseStats[5] = 67;
			return this.baseStats;
		} else if (this.id == 84) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 55;
			this.baseStats[2] = 60;
			this.baseStats[3] = 75;
			this.baseStats[4] = 80;
			this.baseStats[5] = 66;
			return this.baseStats;
		} else if (this.id == 85) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 59;
			this.baseStats[2] = 63;
			this.baseStats[3] = 100;
			this.baseStats[4] = 96;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == 86) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 69;
			this.baseStats[2] = 80;
			this.baseStats[3] = 30;
			this.baseStats[4] = 70;
			this.baseStats[5] = 67;
			return this.baseStats;
		} else if (this.id == 87) {
			this.baseStats[0] = 95;
			this.baseStats[1] = 110;
			this.baseStats[2] = 125;
			this.baseStats[3] = 70;
			this.baseStats[4] = 75;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == 88) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 165;
			this.baseStats[2] = 105;
			this.baseStats[3] = 15;
			this.baseStats[4] = 45;
			this.baseStats[5] = 115;
			return this.baseStats;
		} else if (this.id == 89) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 65;
			this.baseStats[2] = 55;
			this.baseStats[3] = 60;
			this.baseStats[4] = 60;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == 90) {
			this.baseStats[0] = 105;
			this.baseStats[1] = 110;
			this.baseStats[2] = 65;
			this.baseStats[3] = 60;
			this.baseStats[4] = 95;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == 91) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 110;
			this.baseStats[2] = 75;
			this.baseStats[3] = 110;
			this.baseStats[4] = 100;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == 92) {
			this.baseStats[0] = 40;
			this.baseStats[1] = 20;
			this.baseStats[2] = 30;
			this.baseStats[3] = 120;
			this.baseStats[4] = 105;
			this.baseStats[5] = 110;
			return this.baseStats;
		} else if (this.id == 93) {
			this.baseStats[0] = 120;
			this.baseStats[1] = 35;
			this.baseStats[2] = 40;
			this.baseStats[3] = 100;
			this.baseStats[4] = 65;
			this.baseStats[5] = 65;
			return this.baseStats;
		} else if (this.id == 94) {
			this.baseStats[0] = 120;
			this.baseStats[1] = 60;
			this.baseStats[2] = 60;
			this.baseStats[3] = 120;
			this.baseStats[4] = 80;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == 95) {
			this.baseStats[0] = 30;
			this.baseStats[1] = 60;
			this.baseStats[2] = 73;
			this.baseStats[3] = 30;
			this.baseStats[4] = 65;
			this.baseStats[5] = 58;
			return this.baseStats;
		} else if (this.id == 96) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 80;
			this.baseStats[2] = 95;
			this.baseStats[3] = 65;
			this.baseStats[4] = 95;
			this.baseStats[5] = 95;
			return this.baseStats;
		} else if (this.id == 97) {
			this.baseStats[0] = 50;
			this.baseStats[1] = 70;
			this.baseStats[2] = 55;
			this.baseStats[3] = 50;
			this.baseStats[4] = 50;
			this.baseStats[5] = 55;
			return this.baseStats;
		} else if (this.id == 98) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 75;
			this.baseStats[2] = 105;
			this.baseStats[3] = 50;
			this.baseStats[4] = 75;
			this.baseStats[5] = 60;
			return this.baseStats;
		} else if (this.id == 99) {
			this.baseStats[0] = 70;
			this.baseStats[1] = 115;
			this.baseStats[2] = 115;
			this.baseStats[3] = 90;
			this.baseStats[4] = 75;
			this.baseStats[5] = 65;
			return this.baseStats;
		} else if (this.id == 100) {
			this.baseStats[0] = 80;
			this.baseStats[1] = 40;
			this.baseStats[2] = 57;
			this.baseStats[3] = 63;
			this.baseStats[4] = 59;
			this.baseStats[5] = 51;
			return this.baseStats;
		} else if (this.id == 101) {
			this.baseStats[0] = 85;
			this.baseStats[1] = 75;
			this.baseStats[2] = 80;
			this.baseStats[3] = 90;
			this.baseStats[4] = 75;
			this.baseStats[5] = 60;
			return this.baseStats;
		} else if (this.id == 102) {
			this.baseStats[0] = 90;
			this.baseStats[1] = 85;
			this.baseStats[2] = 85;
			this.baseStats[3] = 100;
			this.baseStats[4] = 150;
			this.baseStats[5] = 90;
			return this.baseStats;
		} else if (this.id == 103) {
			this.baseStats[0] = 95;
			this.baseStats[1] = 125;
			this.baseStats[2] = 105;
			this.baseStats[3] = 65;
			this.baseStats[4] = 95;
			this.baseStats[5] = 85;
			return this.baseStats;
		} else if (this.id == 104) {
			this.baseStats[0] = 95;
			this.baseStats[1] = 85;
			this.baseStats[2] = 65;
			this.baseStats[3] = 105;
			this.baseStats[4] = 95;
			this.baseStats[5] = 125;
			return this.baseStats;
		} else if (this.id == 105) {
			this.baseStats[0] = 95;
			this.baseStats[1] = 65;
			this.baseStats[2] = 85;
			this.baseStats[3] = 125;
			this.baseStats[4] = 95;
			this.baseStats[5] = 105;
			return this.baseStats;
		} else if (this.id == 106) {
			this.baseStats[0] = 50;
			this.baseStats[1] = 40;
			this.baseStats[2] = 50;
			this.baseStats[3] = 120;
			this.baseStats[4] = 90;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == 107) {
			this.baseStats[0] = 40;
			this.baseStats[1] = 45;
			this.baseStats[2] = 40;
			this.baseStats[3] = 75;
			this.baseStats[4] = 65;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == 108) {
			this.baseStats[0] = 85;
			this.baseStats[1] = 60;
			this.baseStats[2] = 65;
			this.baseStats[3] = 100;
			this.baseStats[4] = 110;
			this.baseStats[5] = 85;
			return this.baseStats;
		} else if (this.id == 109) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 76;
			this.baseStats[2] = 55;
			this.baseStats[3] = 54;
			this.baseStats[4] = 90;
			this.baseStats[5] = 60;
			return this.baseStats;
		} else if (this.id == 110) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 80;
			this.baseStats[2] = 75;
			this.baseStats[3] = 60;
			this.baseStats[4] = 90;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == 111) {
			this.baseStats[0] = 100;
			this.baseStats[1] = 100;
			this.baseStats[2] = 100;
			this.baseStats[3] = 100;
			this.baseStats[4] = 100;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == 112) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 75;
			this.baseStats[2] = 70;
			this.baseStats[3] = 55;
			this.baseStats[4] = 65;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == 113) {
			this.baseStats[0] = 120;
			this.baseStats[1] = 75;
			this.baseStats[2] = 100;
			this.baseStats[3] = 85;
			this.baseStats[4] = 65;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == 114) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 65;
			this.baseStats[2] = 85;
			this.baseStats[3] = 120;
			this.baseStats[4] = 100;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == 115) {
			this.baseStats[0] = 85;
			this.baseStats[1] = 120;
			this.baseStats[2] = 75;
			this.baseStats[3] = 100;
			this.baseStats[4] = 75;
			this.baseStats[5] = 65;
			return this.baseStats;
		} else if (this.id == 116) {
			this.baseStats[0] = 100;
			this.baseStats[1] = 120;
			this.baseStats[2] = 85;
			this.baseStats[3] = 75;
			this.baseStats[4] = 65;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == 117) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 75;
			this.baseStats[2] = 65;
			this.baseStats[3] = 85;
			this.baseStats[4] = 120;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == 118) {
			this.baseStats[0] = 85;
			this.baseStats[1] = 100;
			this.baseStats[2] = 120;
			this.baseStats[3] = 75;
			this.baseStats[4] = 75;
			this.baseStats[5] = 65;
			return this.baseStats;
		} else if (this.id == 119) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 65;
			this.baseStats[2] = 85;
			this.baseStats[3] = 100;
			this.baseStats[4] = 120;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == 120) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 75;
			this.baseStats[2] = 120;
			this.baseStats[3] = 85;
			this.baseStats[4] = 100;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == 121) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 120;
			this.baseStats[2] = 85;
			this.baseStats[3] = 65;
			this.baseStats[4] = 100;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == 122) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 100;
			this.baseStats[2] = 75;
			this.baseStats[3] = 120;
			this.baseStats[4] = 65;
			this.baseStats[5] = 85;
			return this.baseStats;
		} else if (this.id == 123) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 75;
			this.baseStats[2] = 75;
			this.baseStats[3] = 120;
			this.baseStats[4] = 100;
			this.baseStats[5] = 85;
			return this.baseStats;
		} else if (this.id == 124) {
			this.baseStats[0] = 57;
			this.baseStats[1] = 51;
			this.baseStats[2] = 50;
			this.baseStats[3] = 50;
			this.baseStats[4] = 52;
			this.baseStats[5] = 50;
			return this.baseStats;
		} else if (this.id == 125) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 79;
			this.baseStats[2] = 71;
			this.baseStats[3] = 55;
			this.baseStats[4] = 75;
			this.baseStats[5] = 65;
			return this.baseStats;
		} else if (this.id == 126) {
			this.baseStats[0] = 90;
			this.baseStats[1] = 95;
			this.baseStats[2] = 86;
			this.baseStats[3] = 105;
			this.baseStats[4] = 80;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == 127) {
			this.baseStats[0] = 52;
			this.baseStats[1] = 53;
			this.baseStats[2] = 52;
			this.baseStats[3] = 58;
			this.baseStats[4] = 54;
			this.baseStats[5] = 62;
			return this.baseStats;
		} else if (this.id == 128) {
			this.baseStats[0] = 54;
			this.baseStats[1] = 85;
			this.baseStats[2] = 63;
			this.baseStats[3] = 63;
			this.baseStats[4] = 69;
			this.baseStats[5] = 76;
			return this.baseStats;
		} else if (this.id == 129) {
			this.baseStats[0] = 58;
			this.baseStats[1] = 90;
			this.baseStats[2] = 95;
			this.baseStats[3] = 100;
			this.baseStats[4] = 95;
			this.baseStats[5] = 77;
			return this.baseStats;
		} else if (this.id == 130) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 67;
			this.baseStats[2] = 65;
			this.baseStats[3] = 51;
			this.baseStats[4] = 40;
			this.baseStats[5] = 52;
			return this.baseStats;
		} else if (this.id == 131) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 99;
			this.baseStats[2] = 86;
			this.baseStats[3] = 56;
			this.baseStats[4] = 45;
			this.baseStats[5] = 61;
			return this.baseStats;
		} else if (this.id == 132) {
			this.baseStats[0] = 95;
			this.baseStats[1] = 80;
			this.baseStats[2] = 76;
			this.baseStats[3] = 128;
			this.baseStats[4] = 60;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == 133) {
			this.baseStats[0] = 100;
			this.baseStats[1] = 115;
			this.baseStats[2] = 85;
			this.baseStats[3] = 120;
			this.baseStats[4] = 105;
			this.baseStats[5] = 125;
			return this.baseStats;
		} else if (this.id == 134) {
			this.baseStats[0] = 110;
			this.baseStats[1] = 85;
			this.baseStats[2] = 120;
			this.baseStats[3] = 155;
			this.baseStats[4] = 80;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == 135) {
			this.baseStats[0] = 70;
			this.baseStats[1] = 50;
			this.baseStats[2] = 90;
			this.baseStats[3] = 100;
			this.baseStats[4] = 90;
			this.baseStats[5] = 52;
			return this.baseStats;
		} else if (this.id == 136) {
			this.baseStats[0] = 50;
			this.baseStats[1] = 50;
			this.baseStats[2] = 100;
			this.baseStats[3] = 80;
			this.baseStats[4] = 100;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == 137) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 55;
			this.baseStats[2] = 90;
			this.baseStats[3] = 55;
			this.baseStats[4] = 80;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == 138) {
			this.baseStats[0] = 90;
			this.baseStats[1] = 55;
			this.baseStats[2] = 80;
			this.baseStats[3] = 195;
			this.baseStats[4] = 100;
			this.baseStats[5] = 90;
			return this.baseStats;
		} else if (this.id == 139) {
			this.baseStats[0] = 100;
			this.baseStats[1] = 70;
			this.baseStats[2] = 125;
			this.baseStats[3] = 120;
			this.baseStats[4] = 100;
			this.baseStats[5] = 95;
			return this.baseStats;
		} else if (this.id == 140) {
			this.baseStats[0] = 130;
			this.baseStats[1] = 90;
			this.baseStats[2] = 110;
			this.baseStats[3] = 80;
			this.baseStats[4] = 110;
			this.baseStats[5] = 100;
			return this.baseStats;
		}
		return this.baseStats;
	}

	
	public Pokemon move(Pokemon foe, Move move) {
		if (this.fainted || foe.fainted) return this;

		double attackStat;
		double defenseStat;
		int damage;
		
		if (this.status == Status.CONFUSED) {
			if (this.confusionCounter == 0) {
				this.status = Status.HEALTHY;
		        System.out.println(this.name + " snapped out of confusion!");
			} else {
				System.out.println(this.name + " is confused!");
				if (Math.random() < 1.0/3.0) {
			        // user hits themselves
					System.out.println(this.name + " hit itself in confusion!");
					attackStat = this.getStat(1);
					defenseStat = this.getStat(2);
					attackStat *= this.asModifier(0);
					defenseStat *= this.asModifier(1);
					
					double num = 2* (double) this.level / 5 + 2;
					double stat = (double) attackStat / (double) defenseStat / 50;
					double damageDouble = Math.floor(num * 40 * stat);
					damageDouble += 2;
					
					Random roll = new Random();
					double rollAmt = roll.nextInt(16);
					rollAmt += 85;
					rollAmt /= 100;
					
					// Roll
					damageDouble *= rollAmt;
					// Convert to integer
					damage = (int) Math.floor(damageDouble);
					this.currentHP -= damage;
					
					if (this.currentHP <= 0) {
						this.currentHP = 0;
						this.fainted = true;
						System.out.println(this.name + " fainted!\n");
					}
					confusionCounter--;
					return this;
				} else {
					confusionCounter--;
				}
			}
		}
		
		if (move.accuracy < 100 && !hit(move.accuracy)) {
			System.out.println(this.name + " used " + move + "!");
			System.out.println(this.name + "'s attack missed!\n");
			return this; // Check for miss
		}
		
		if (move.accuracy <= 100 && move.cat != 2) {
			if (getImmune(foe, move.mtype)) {
				System.out.println(this.name + " used " + move + "!");
				System.out.println("It doesn't effect " + foe.name + "...\n");
				return this; // Check for immunity
			}
		}
		
		System.out.println(this.name + " used " + move + "!");
		
		if (move.cat == 2) {
			statusEffect(foe, move);
			return this;
		}
		
		// Use either physical or special attack/defense
		if (move.isPhysical()) {
			attackStat = this.getStat(1);
			defenseStat = foe.getStat(2);
			attackStat *= this.asModifier(0);
			defenseStat *= foe.asModifier(1);
		} else {
			attackStat = this.getStat(3);
			defenseStat = foe.getStat(4);
			attackStat *= this.asModifier(2);
			defenseStat *= foe.asModifier(3);
		}
		
		double num = 2* (double) this.level / 5 + 2;
		double stat = (double) attackStat / (double) defenseStat / 50;
		double damageDouble = Math.floor(num * move.basePower * stat);
		damageDouble += 2;
		
		Random roll = new Random();
		double rollAmt = roll.nextInt(16);
		rollAmt += 85;
		rollAmt /= 100;
		
		// Roll
		damageDouble *= rollAmt;
		
		// Stab
		if (move.mtype == this.type1) damageDouble *= 1.5;
		if (move.mtype == this.type2) damageDouble *= 1.5;
		
		// Charged
		if (move.mtype == PType.ELECTRIC && this.charged) damageDouble *= 2;
		
		// Crit Check
		if (critCheck(move)) {
			System.out.print("Crit! ");
			damageDouble *= 1.5;
		}
		
		// Convert to integer
		damage = (int) Math.floor(damageDouble);
		
		// Check type effectiveness
		PType[] resist = getResistances(move.mtype);
		for (PType type : resist) {
			if (foe.type1 == type) {
				System.out.println("It's not very effective...\n");
				damage *= 0.5;
			}
			if (foe.type2 == type) {
				System.out.println("It's not very effective...\n");
				damage *= 0.5;
			}
		}
		// Check type effectiveness
		PType[] weak = getWeaknesses(move.mtype);
		for (PType type : weak) {
			if (foe.type1 == type) {
				damage *= 2;
				System.out.println("It's super effective!\n");
			}
			if (foe.type2 == type) {
				damage *= 2;
				System.out.println("It's super effective!\n");
			}
		}
		
		System.out.println(damage + " damage\n");
		
		Pokemon user = this;
		
		// Damage foe
		foe.currentHP -= damage;
		if (foe.currentHP <= 0) { // Check for kill
			foe.currentHP = 0;
			this.exp += foe.level;
			if (this.exp >= this.expMax) { // Check for level up
				user = this.levelUp();
			}
			System.out.println(foe.name + " fainted!\n");
			foe.fainted = true;
		}
		
		
		return user;
	}
	
	private void statusEffect(Pokemon foe, Move move) {
		if (move == Move.AGILITY) {
			this.statStages[4] += 2;
			if (this.statStages[4] > 6) {
				this.statStages[4] = 6;
				System.out.println(this.name + "'s Speed won't go any higher!\n");
			} else {
				System.out.println(this.name + "'s Speed sharply rose!\n");
			}
		} else if (move == Move.AQUA_RING) {
//			// TODO
//			System.out.println(this.name + " surrounded itself in a veil of water!");
		} else if (move == Move.AUTOMOTIZE) {
			this.statStages[4] += 2;
			if (this.statStages[4] > 6) {
				this.statStages[4] = 6;
				System.out.println(this.name + "'s Speed won't go any higher!\n");
			} else {
				System.out.println(this.name + "'s Speed sharply rose!\n");
			}
		} else if (move == Move.AUTO_SHOT) {
//			// TODO
//			System.out.println(this.name + " upgraded its weapon!");
		} else if (move == Move.BAWL) {
			foe.statStages[0] -= 2;
			if (foe.statStages[0] < -6) {
			    foe.statStages[0] = -6;
			    System.out.println(foe.name + "'s Attack won't go any lower!\n");
			} else {
				System.out.println(foe.name + "'s Attack harshly fell!\n");
			}
		} else if (move == Move.BLACK_DUST) {
			foe.statStages[5] -= 2;
			if (foe.statStages[5] < -6) {
			    foe.statStages[5] = -6;
			    System.out.println(foe.name + "'s Accuracy won't go any lower!\n");
			} else {
				System.out.println(foe.name + "'s Accuracy harshly fell!\n");
			}
		} else if (move == Move.CHARGE) {
			System.out.println(this.name + " became charged with power!");
			this.charged = true;
			this.statStages[3] += 1;
			if (this.statStages[3] > 6) {
				this.statStages[3] = 6;
				System.out.println(this.name + "'s Special Defense won't go any higher!\n");
			} else {
				System.out.println(this.name + "'s Special Defense rose!\n");
			}
		} else if (move == Move.CHARM) {
			foe.statStages[0] -= 2;
			if (foe.statStages[0] < -6) {
			    foe.statStages[0] = -6;
			    System.out.println(foe.name + "'s Attack won't go any lower!\n");
			} else {
				System.out.println(foe.name + "'s Attack harshly fell!\n");
			}
		} else if (move == Move.CONFUSE_RAY) {
			if (foe.status == Status.HEALTHY) {
				foe.status = Status.CONFUSED;
				foe.confusionCounter = (int)(Math.random() * 4) + 1;
				System.out.println(foe.name + " became confused!\n");
			} else {
				System.out.println("But it failed!\n");
			}
		} else if (move == Move.CURSE) {
			if (foe.status == Status.HEALTHY) {
				foe.status = Status.CURSED;
				System.out.println(foe.name + " was afflicted with a curse!\n");
				this.currentHP -= (this.getStat(0) / 2);
				if (this.currentHP <= 0) {
					this.currentHP = 0;
					this.fainted = true;
					System.out.println(this.name + " fainted!\n");
				}
			} else {
				System.out.println("But it failed!\n");
			}
		} else if (move == Move.DARK_VOID) {
			if (foe.status == Status.HEALTHY) {
				foe.status = Status.ASLEEP;
				foe.sleepCounter = (int)(Math.random() * 3) + 1;
				System.out.println(foe.name + " fell asleep!\n");
			} else {
				System.out.println("But it failed!\n");
			}
		} else if (move == Move.DEFENSE_CURL) {
			this.statStages[1] += 1;
			if (this.statStages[1] > 6) {
				this.statStages[1] = 6;
				System.out.println(this.name + "'s Defense won't go any higher!\n");
			} else {
				System.out.println(this.name + "'s Defense rose!\n");
			}
		} else if (move == Move.DESTINY_BOND) {
//			// TODO
//			System.out.println(this.name + " is ready to take its attacker down with it!");
		} else if (move == Move.DISAPPEAR) {
//			// TODO
//			System.out.println(this.name + " vanished!");
		} else if (move == Move.DOUBLE_TEAM) {
			this.statStages[6] += 1;
			if (this.statStages[6] > 6) {
				this.statStages[6] = 6;
				System.out.println(this.name + "'s Evasion won't go any higher!\n");
			} else {
				System.out.println(this.name + "'s Evasion rose!\n");
			}
		} else if (move == Move.DRAGON_DANCE) {
			this.statStages[0] += 1;
			this.statStages[4] += 1;
			if (this.statStages[0] > 6) {
				this.statStages[0] = 6;
				System.out.println(this.name + "'s Attack won't go any higher!\n");
			} else {
				System.out.println(this.name + "'s Attack rose!\n");
			}
			if (this.statStages[4] > 6) {
				this.statStages[4] = 6;
				System.out.println(this.name + "'s Speed won't go any higher!\n");
			} else {
				System.out.println(this.name + "'s Speed rose!\n");
			}
		} else if (move == Move.FLASH) {
			foe.statStages[5] -= 1;
			if (foe.statStages[5] < -6) {
			    foe.statStages[5] = -6;
			    System.out.println(foe.name + "'s Accuracy won't go any lower!\n");
			} else {
				System.out.println(foe.name + "'s Accuracy fell!\n");
			}
		} else if (move == Move.FORESIGHT) {
			if (foe.type1 == PType.GHOST) foe.type1 = PType.NORMAL;
			if (foe.type2 == PType.GHOST) foe.type2 = PType.NORMAL;
			System.out.println(this.name + " identified " + foe.name + "!\n");
		} else if (move == Move.GLARE) {
			if (foe.type1 == PType.ELECTRIC || foe.type2 == PType.ELECTRIC) {
				System.out.println("It doesn't effect " + foe.name + "...");
			}
			if (foe.status == Status.HEALTHY) {
				foe.status = Status.PARALYZED;
				System.out.println(foe.name + " was paralyzed!\n");
			} else {
				System.out.println("But it failed!\n");
			}
		} else if (move == Move.GROWL) {
			foe.statStages[0] -= 1;
			if (foe.statStages[0] < -6) {
			    foe.statStages[0] = -6;
			    System.out.println(foe.name + "'s Attack won't go any lower!\n");
			} else {
				System.out.println(foe.name + "'s Attack fell!\n");
			}
		} else if (move == Move.GROWTH) {
			this.statStages[0] += 1;
			this.statStages[2] += 1;
			if (this.statStages[0] > 6) {
				this.statStages[0] = 6;
				System.out.println(this.name + "'s Attack won't go any higher!\n");
			} else {
				System.out.println(this.name + "'s Attack rose!\n");
			}
			if (this.statStages[2] > 6) {
				this.statStages[2] = 6;
				System.out.println(this.name + "'s Special Attack won't go any higher!\n");
			} else {
				System.out.println(this.name + "'s Special Attack rose!\n");
			}
		} else if (move == Move.HARDEN) {
			this.statStages[1] += 1;
			if (this.statStages[1] > 6) {
				this.statStages[1] = 6;
				System.out.println(this.name + "'s Defense won't go any higher!\n");
			} else {
				System.out.println(this.name + "'s Defense rose!\n");
			}
		} else if (move == Move.HAZE) {
			this.statStages = new int[7];
			foe.statStages = new int[7];
			System.out.println(this.name + "All stat changes were eliminated!\n");
		} else if (move == Move.HYPNOSIS) {
			if (foe.status == Status.HEALTHY) {
				foe.status = Status.ASLEEP;
				foe.sleepCounter = (int)(Math.random() * 3) + 1;
				System.out.println(foe.name + " fell asleep!\n");
			} else {
				System.out.println("But it failed!\n");
			}
		} else if (move == Move.IGNITE) {
			if (foe.type1 == PType.FIRE || foe.type2 == PType.FIRE) {
				System.out.println("It doesn't effect " + foe.name + "...");
				return;
			}
			if (foe.status == Status.HEALTHY) {
				foe.status = Status.BURNED;
				System.out.println(foe.name + " was burned!\n");
			} else {
				System.out.println("But it failed!\n");
			}
		} else if (move == Move.IRON_DEFENSE) {
			this.statStages[1] += 2;
			if (this.statStages[1] > 6) {
				this.statStages[1] = 6;
				System.out.println(this.name + "'s Defense won't go any higher!\n");
			} else {
				System.out.println(this.name + "'s Defense sharply rose!\n");
			}
		} else if (move == Move.LEECH_SEED) {
			if (foe.status == Status.HEALTHY) {
				foe.status = Status.LEECHED;
				System.out.println(foe.name + " was seeded!\n");
			} else {
				System.out.println("But it failed!\n");
			}
		} else if (move == Move.LEER) {
			foe.statStages[1] -= 1;
			if (foe.statStages[1] < -6) {
			    foe.statStages[1] = -6;
			    System.out.println(foe.name + "'s Defense won't go any lower!\n");
			} else {
				System.out.println(foe.name + "'s Defense fell!\n");
			}
		} else if (move == Move.LOCK_ON) {
//			// TODO
//			System.out.println(this.name + " took aim at " + foe.name + "!\n");
		} else if (move == Move.MAGIC_REFLECT) {
//			// TODO
		} else if (move == Move.MAGNET_RISE) {
//			// TODO
//			System.out.println(this.name + " floated with electromagnetism!\n");
		} else if (move == Move.METAL_SOUND) {
			foe.statStages[3] -= 2;
			if (foe.statStages[3] < -6) {
			    foe.statStages[3] = -6;
			    System.out.println(foe.name + "'s Special Defense won't go any lower!\n");
			} else {
				System.out.println(foe.name + "'s Special Defense harshly fell!\n");
			}
		} else if (move == Move.MINIMIZE) {
			this.statStages[6] += 2;
			if (this.statStages[6] > 6) {
				this.statStages[6] = 6;
				System.out.println(this.name + "'s Evasion won't go any higher!\n");
			} else {
				System.out.println(this.name + "'s Evasion sharply rose!\n");
			}
		} else if (move == Move.MOONLIGHT) {
			if (this.currentHP == this.getStat(0)) {
				System.out.println(this.name + "'s HP is full!\n");
			} else {
				this.currentHP += (this.getStat(0) / 2);
				if (this.currentHP > this.getStat(0)) this.currentHP = this.getStat(0);
				System.out.println(this.name + " restored HP.\n");
			}
		} else if (move == Move.MUD_SPORT) {
			System.out.println(this.name + " electric's power was weakened!\n");
		} else if (move == Move.NIGHTMARE) {
//			if (foe.status == Status.ASLEEP) { TODO
//				foe.status = Status.NIGHTMARE;
//				System.out.println(foe.name + " had a nightmare!\n");
//			} else {
//				System.out.println("But it failed!\n");
//			}
		} else if (move == Move.ODOR_SLEUTH) {
			if (foe.type1 == PType.GHOST) foe.type1 = PType.NORMAL;
			if (foe.type2 == PType.GHOST) foe.type2 = PType.NORMAL;
			System.out.println(this.name + " identified " + foe.name + "!\n");
		} else if (move == Move.PERISH_SONG) {
//			if (foe.status == Status.HEALTHY) { TODO
//			foe.status = Status.PERISH;
//			this.perishCount = 3;
//			foe.perishCount = 3;
//			}
		} else if (move == Move.PHASE_SHIFT) {
//			// TODO
		} else if (move == Move.POISON_GAS) {
			if (foe.type1 == PType.POISON || foe.type2 == PType.POISON) {
				System.out.println("It doesn't effect " + foe.name + "...");
				return;
			}
			if (foe.status == Status.HEALTHY) {
				foe.status = Status.POISONED;
				System.out.println(foe.name + " was poisoned!\n");
			} else {
				System.out.println("But it failed!\n");
			}
		} else if (move == Move.POISON_POWDER) {
			if (foe.type1 == PType.POISON || foe.type2 == PType.POISON) {
				System.out.println("It doesn't effect " + foe.name + "...");
				return;
			}
			if (foe.status == Status.HEALTHY) {
				foe.status = Status.POISONED;
				System.out.println(foe.name + " was poisoned!\n");
			} else {
				System.out.println("But it failed!\n");
			}
		} else if (move == Move.PROTECT) {
//			// TODO
		} else if (move == Move.REBOOT) {
			if (this.status != Status.HEALTHY) System.out.println(this.name + " became healthy!\n");
			this.status = Status.HEALTHY;
			this.statStages[4] += 1;
			if (this.statStages[4] > 6) {
				this.statStages[4] = 6;
				System.out.println(this.name + "'s Speed won't go any higher!\n");
			} else {
				System.out.println(this.name + "'s Speed rose!\n");
			}
		} else if (move == Move.ROOST) {
			if (this.currentHP == this.getStat(0)) {
				System.out.println(this.name + "'s HP is full!\n");
			} else {
				this.currentHP += (this.getStat(0) / 2);
				if (this.currentHP > this.getStat(0)) this.currentHP = this.getStat(0);
				System.out.println(this.name + " restored HP.\n");
			}
		} else if (move == Move.SAND_ATTACK) {
			foe.statStages[5] -= 1;
			if (foe.statStages[5] < -6) {
			    foe.statStages[5] = -6;
			    System.out.println(foe.name + "'s Accuracy won't go any lower!\n");
			} else {
				System.out.println(foe.name + "'s Accuracy fell!\n");
			}
		} else if (move == Move.SCARY_FACE) {
			foe.statStages[4] -= 2;
			if (foe.statStages[4] < -6) {
			    foe.statStages[4] = -6;
			    System.out.println(foe.name + "'s Speed won't go any lower!\n");
			} else {
				System.out.println(foe.name + "'s Speed harshly fell!\n");
			}
		} else if (move == Move.SCREECH) {
			foe.statStages[1] -= 2;
			if (foe.statStages[1] < -6) {
			    foe.statStages[1] = -6;
			    System.out.println(foe.name + "'s Defense won't go any lower!\n");
			} else {
				System.out.println(foe.name + "'s Defense harshly fell!\n");
			}
		} else if (move == Move.SLEEP_POWDER) {
			if (foe.status == Status.HEALTHY) {
				foe.status = Status.ASLEEP;
				foe.sleepCounter = (int)(Math.random() * 3) + 1;
				System.out.println(foe.name + " fell asleep!\n");
			} else {
				System.out.println("But it failed!\n");
			}
		} else if (move == Move.SMOKESCREEN) {
			foe.statStages[5] -= 1;
			if (foe.statStages[5] < -6) {
			    foe.statStages[5] = -6;
			    System.out.println(foe.name + "'s Accuracy won't go any lower!\n");
			} else {
				System.out.println(foe.name + "'s Accuracy fell!\n");
			}
		} else if (move == Move.STARE) {
			foe.statStages[0] += 1;
			if (foe.statStages[0] > 6) {
				foe.statStages[0] = 6;
				System.out.println(foe.name + "'s Attack won't go any higher!\n");
			} else {
				System.out.println(foe.name + "'s Attack rose!\n");
			}
			if (foe.status == Status.HEALTHY) {
				foe.status = Status.CONFUSED;
				foe.confusionCounter = (int)(Math.random() * 4) + 1;
				System.out.println(foe.name + " became confused!\n");
			}
		} else if (move == Move.STRING_SHOT) {
			foe.statStages[4] -= 2;
			if (foe.statStages[4] < -6) {
			    foe.statStages[4] = -6;
			    System.out.println(foe.name + "'s Speed won't go any lower!\n");
			} else {
				System.out.println(foe.name + "'s Speed harshly fell!\n");
			}
		} else if (move == Move.SUNNY_DAY) {
//			// TODO
		} else if (move == Move.SUPERSONIC) {
			if (foe.status == Status.HEALTHY) {
				foe.status = Status.CONFUSED;
				foe.confusionCounter = (int)(Math.random() * 4) + 1;
				System.out.println(foe.name + " became confused!\n");
			} else {
				System.out.println("But it failed!\n");
			}
		} else if (move == Move.SWAGGER) {
			foe.statStages[0] += 2;
			if (foe.statStages[0] > 6) {
				foe.statStages[0] = 6;
				System.out.println(foe.name + "'s Attack won't go any higher!\n");
			} else {
				System.out.println(foe.name + "'s Attack sharply rose!\n");
			}
			if (foe.status == Status.HEALTHY) {
				foe.status = Status.CONFUSED;
				foe.confusionCounter = (int)(Math.random() * 4) + 1;
				System.out.println(foe.name + " became confused!\n");
			}
		} else if (move == Move.SYNTHESIS) {
			if (this.currentHP == this.getStat(0)) {
				System.out.println(this.name + "'s HP is full!\n");
			} else {
				this.currentHP += (this.getStat(0) / 2);
				if (this.currentHP > this.getStat(0)) this.currentHP = this.getStat(0);
				System.out.println(this.name + " restored HP.\n");
			}
		} else if (move == Move.TAIL_WHIP) {
			foe.statStages[1] -= 1;
			if (foe.statStages[1] < -6) {
			    foe.statStages[1] = -6;
			    System.out.println(foe.name + "'s Defense won't go any lower!\n");
			} else {
				System.out.println(foe.name + "'s Defense fell!\n");
			}
		} else if (move == Move.TAILWIND) {
//			// TODO
		} else if (move == Move.TAKE_OVER) {
//			// TODO
		} else if (move == Move.TAUNT) {
//			// TODO
		} else if (move == Move.THUNDER_WAVE) {
			if (foe.type1 == PType.ELECTRIC || foe.type2 == PType.ELECTRIC) {
				System.out.println("It doesn't effect " + foe.name + "...");
				return;
			}
			if (foe.status == Status.HEALTHY) {
				foe.status = Status.PARALYZED;
				System.out.println(foe.name + " was paralyzed!\n");
			} else {
				System.out.println("But it failed!\n");
			}
		} else if (move == Move.TORMENT) {
//			// TODO
		} else if (move == Move.TOXIC) {
			if (foe.type1 == PType.POISON || foe.type2 == PType.POISON) {
				System.out.println("It doesn't effect " + foe.name + "...");
				return;
			}
			if (foe.status == Status.HEALTHY) {
				foe.status = Status.POISONED;
				System.out.println(foe.name + " was poisoned!\n");
			} else {
				System.out.println("But it failed!\n");
			}
		} else if (move == Move.TOXIC_SPIKES) {
//			// TODO
		} else if (move == Move.WHIRLWIND) {
//			// TODO
		} else if (move == Move.WILL_O_WISP) {
			if (foe.type1 == PType.FIRE || foe.type2 == PType.FIRE) {
				System.out.println("It doesn't effect " + foe.name + "...");
				return;
			}
			if (foe.status == Status.HEALTHY) {
				foe.status = Status.BURNED;
				System.out.println(foe.name + " was burned!\n");
			} else {
				System.out.println("But it failed!\n");
			}
		} if (move == Move.ROCK_POLISH) {
			this.statStages[4] += 2;
			if (this.statStages[4] > 6) {
				this.statStages[4] = 6;
				System.out.println(this.name + "'s Speed won't go any higher!\n");
			} else {
				System.out.println(this.name + "'s Speed sharply rose!\n");
			}
		}
		return;
	}

	public double asModifier(int index) {
		double modifier = 1;
		if (index <= 4) {
			switch(this.statStages[index]) {
			case -6:
				modifier = 2.0/8.0;
				break;
			case -5:
				modifier = 2.0/7.0;
				break;
			case -4:
				modifier = 2.0/6.0;
				break;
			case -3:
				modifier = 2.0/5.0;
				break;
			case -2:
				modifier = 2.0/4.0;
				break;
			case -1:
				modifier = 2.0/3.0;
				break;
			case 0:
				modifier = 2.0/2.0;
				break;
			case 1:
				modifier = 3.0/2.0;
				break;
			case 2:
				modifier = 4.0/2.0;
				break;
			case 3:
				modifier = 5.0/2.0;
				break;
			case 4:
				modifier = 6.0/2.0;
				break;
			case 5:
				modifier = 7.0/2.0;
				break;
			case 6:
				modifier = 8.0/2.0;
				break;
			default:
				modifier = 1;
				break;
			}
		} else {
			int accev = statStages[index];
			if (index == 6) accev *= -1;
			switch(accev) {
			case -6:
				modifier = 3.0/9.0;
				break;
			case -5:
				modifier = 3.0/8.0;
				break;
			case -4:
				modifier = 3.0/7.0;
				break;
			case -3:
				modifier = 3.0/6.0;
				break;
			case -2:
				modifier = 3.0/5.0;
				break;
			case -1:
				modifier = 3.0/4.0;
				break;
			case 0:
				modifier = 3.0/3.0;
				break;
			case 1:
				modifier = 4.0/3.0;
				break;
			case 2:
				modifier = 5.0/3.0;
				break;
			case 3:
				modifier = 6.0/3.0;
				break;
			case 4:
				modifier = 7.0/3.0;
				break;
			case 5:
				modifier = 8.0/3.0;
				break;
			case 6:
				modifier = 9.0/3.0;
				break;
			default:
				modifier = 1;
				break;
			}
		}
		return modifier;
	}

	private boolean getImmune(Pokemon p, PType type) {
		switch(type) {
        case NORMAL: 
        	if (p.type1 == PType.GHOST) return true;
        	if (p.type2 == PType.GHOST) return true;
        	return false;
        case ROCK: 
            return false;
		case BUG:
			return false;
		case DARK:
			return false;
		case DRAGON:
			return false;
		case ELECTRIC:
			if (p.type1 == PType.GROUND) return true;
        	if (p.type2 == PType.GROUND) return true;
            return false;
		case FIGHTING:
			if (p.type1 == PType.GHOST) return true;
        	if (p.type2 == PType.GHOST) return true;
            return false;
		case FIRE:
			return false;
		case FLYING:
			return false;
		case GHOST:
			if (p.type1 == PType.NORMAL) return true;
        	if (p.type2 == PType.NORMAL) return true;
            return false;
		case GRASS:
			return false;
		case GROUND:
			if (p.type1 == PType.FLYING) return true;
        	if (p.type2 == PType.FLYING) return true;
			return false;
		case MAGIC:
			if (p.type1 == PType.DARK) return true;
        	if (p.type2 == PType.DARK) return true;
        	return false;
		case POISON:
			if (p.type1 == PType.STEEL) return true;
        	if (p.type2 == PType.STEEL) return true;
			return false;
		case STEEL:
			return false;
		case WATER:
			return false;
		default:
			return false;
    }
		
	}

	private boolean hit(int acc) {
		int hitChance = (int)(Math.random()*100);
		if (hitChance <= acc) {
			return true;
		} else {
			return false;
		}
	}

	private boolean critCheck(Move m) {
		  int critChance = (int)(Math.random()*100);
		  int baseCrit;
		  if (m.critChance == 1) {
		    baseCrit = 13;
		  } else {
		    baseCrit = 5;
		  }
		  if (critChance <= baseCrit) {
		    return true;
		  } else {
		    return false;
		  }
		}
	
	public void heal() {
		this.fainted = false;
		this.clearVolatile();
		this.currentHP = this.getStat(0);
		this.status = Status.HEALTHY;
		System.out.println(this.name + " healed!");
	}
	
	/**
	 * 
	 * @param type - type to check what other types resist it
	 * @return Array of types that resist type
	 */
	public PType[] getResistances(PType type) {
	    ArrayList<PType> resistantTypes = new ArrayList<>();
	    switch(type) {
	        case NORMAL: 
	        	resistantTypes.add(PType.ROCK);
	            resistantTypes.add(PType.STEEL);
	            break;
	        case ROCK: 
	            resistantTypes.add(PType.FIGHTING);
	            resistantTypes.add(PType.GROUND);
	            resistantTypes.add(PType.STEEL);
	            break;
			case BUG:
				resistantTypes.add(PType.FIRE);
	            resistantTypes.add(PType.FIGHTING);
	            resistantTypes.add(PType.POISON);
	            resistantTypes.add(PType.FLYING);
	            resistantTypes.add(PType.GHOST);
	            resistantTypes.add(PType.STEEL);
	            break;
			case DARK:
				resistantTypes.add(PType.FIGHTING);
	            resistantTypes.add(PType.DARK);
	            break;
			case DRAGON:
				resistantTypes.add(PType.STEEL);
	            break;
			case ELECTRIC:
				resistantTypes.add(PType.ELECTRIC);
	            resistantTypes.add(PType.GRASS);
	            resistantTypes.add(PType.DRAGON);
	            break;
			case FIGHTING:
				resistantTypes.add(PType.BUG);
	            resistantTypes.add(PType.POISON);
	            resistantTypes.add(PType.FLYING);
	            resistantTypes.add(PType.MAGIC);
	            break;
			case FIRE:
				resistantTypes.add(PType.FIRE);
	            resistantTypes.add(PType.WATER);
	            resistantTypes.add(PType.ROCK);
	            resistantTypes.add(PType.DRAGON);
	            break;
			case FLYING:
				resistantTypes.add(PType.ELECTRIC);
	            resistantTypes.add(PType.ROCK);
	            resistantTypes.add(PType.STEEL);
	            break;
			case GHOST:
				resistantTypes.add(PType.DARK);
	            break;
			case GRASS:
				resistantTypes.add(PType.FIRE);
	            resistantTypes.add(PType.GRASS);
	            resistantTypes.add(PType.POISON);
	            resistantTypes.add(PType.FLYING);
	            resistantTypes.add(PType.BUG);
	            resistantTypes.add(PType.DRAGON);
	            resistantTypes.add(PType.STEEL);
	            break;
			case GROUND:
				resistantTypes.add(PType.GRASS);
	            resistantTypes.add(PType.BUG);
				break;
			case MAGIC:
				resistantTypes.add(PType.ELECTRIC);
	            resistantTypes.add(PType.MAGIC);
	            resistantTypes.add(PType.STEEL);
				break;
			case POISON:
				resistantTypes.add(PType.POISON);
	            resistantTypes.add(PType.GROUND);
	            resistantTypes.add(PType.ROCK);
	            resistantTypes.add(PType.GHOST);
				break;
			case STEEL:
				resistantTypes.add(PType.FIRE);
	            resistantTypes.add(PType.WATER);
	            resistantTypes.add(PType.ELECTRIC);
	            resistantTypes.add(PType.STEEL);
				break;
			case WATER:
				resistantTypes.add(PType.WATER);
	            resistantTypes.add(PType.GRASS);
	            resistantTypes.add(PType.DRAGON);
				break;
			default:
				break;
	    }
	    PType[] toReturn = new PType[resistantTypes.size()]; // this will probably error for NORMAl since size() returns 0.
	    return resistantTypes.toArray(toReturn);
	}
	
	/**
	 * 
	 * @param type - type to check what other types are weak to it
	 * @return Array of types that are weak to type
	 */
	public PType[] getWeaknesses(PType type) {
	    ArrayList<PType> resistantTypes = new ArrayList<>();
	    switch(type) {
	        case NORMAL:
	            break;
	        case ROCK: 
	            resistantTypes.add(PType.FIRE);
	            resistantTypes.add(PType.FLYING);
	            resistantTypes.add(PType.BUG);
	            break;
			case BUG:
				resistantTypes.add(PType.GRASS);
	            resistantTypes.add(PType.MAGIC);
	            resistantTypes.add(PType.DARK);
	            break;
			case DARK:
				resistantTypes.add(PType.MAGIC);
	            resistantTypes.add(PType.GHOST);
	            break;
			case DRAGON:
				resistantTypes.add(PType.DRAGON);
	            break;
			case ELECTRIC:
				resistantTypes.add(PType.WATER);
	            resistantTypes.add(PType.FLYING);
	            break;
			case FIGHTING:
				resistantTypes.add(PType.NORMAL);
	            resistantTypes.add(PType.ROCK);
	            resistantTypes.add(PType.DRAGON);
	            resistantTypes.add(PType.DARK);
	            resistantTypes.add(PType.STEEL);
	            break;
			case FIRE:
				resistantTypes.add(PType.GRASS);
	            resistantTypes.add(PType.BUG);
	            resistantTypes.add(PType.STEEL);
	            break;
			case FLYING:
				resistantTypes.add(PType.GRASS);
	            resistantTypes.add(PType.BUG);
	            resistantTypes.add(PType.FIGHTING);
	            break;
			case GHOST:
				resistantTypes.add(PType.GHOST);
				resistantTypes.add(PType.MAGIC);
	            break;
			case GRASS:
				resistantTypes.add(PType.WATER);
	            resistantTypes.add(PType.ROCK);
	            resistantTypes.add(PType.GROUND);
	            break;
			case GROUND:
				resistantTypes.add(PType.ELECTRIC);
	            resistantTypes.add(PType.FIRE);
	            resistantTypes.add(PType.POISON);
	            resistantTypes.add(PType.ROCK);
	            resistantTypes.add(PType.STEEL);
				break;
			case MAGIC:
				resistantTypes.add(PType.NORMAL);
	            resistantTypes.add(PType.FIGHTING);
	            resistantTypes.add(PType.POISON);
	            resistantTypes.add(PType.DRAGON);
				break;
			case POISON:
				resistantTypes.add(PType.GRASS);
				break;
			case STEEL:
				resistantTypes.add(PType.ROCK);
	            resistantTypes.add(PType.DRAGON);
				break;
			case WATER:
				resistantTypes.add(PType.FIRE);
	            resistantTypes.add(PType.ROCK);
	            resistantTypes.add(PType.GROUND);
				break;
			default:
				break;
	    }
	    PType[] toReturn = new PType[resistantTypes.size()]; // this will probably error for NORMAl since size() returns 0.
	    return resistantTypes.toArray(toReturn);
	}
	
	private void setMoveBank() {
		switch(this.id) {
		case 1:
			movebank = new Move[15];
			movebank[0] = Move.POKE;
			movebank[1] = Move.GROWL;
			movebank[5] = Move.LEECH_LIFE;
			movebank[8] = Move.LEAF_SLAP;
			movebank[11] = Move.DOUBLE_SLAP;
			movebank[14] = Move.LEAF_TORNADO;
			break;
		case 2:
			movebank = new Move[35];
			movebank[0] = Move.POKE;
			movebank[1] = Move.GROWL;
			movebank[5] = Move.LEECH_LIFE;
			movebank[8] = Move.LEAF_SLAP;
			movebank[11] = Move.DOUBLE_SLAP;
			movebank[14] = Move.LEAF_TORNADO;
			movebank[14] = Move.LEAF_TORNADO;
			movebank[16] = Move.MAGICAL_LEAF;
			movebank[20] = Move.MEGA_DRAIN;
			movebank[22] = Move.BRANCH_WHACK;
			movebank[26] = Move.BODY_SLAM;
			movebank[31] = Move.GYRO_BALL;
			movebank[34] = Move.LEAF_STORM;
			break;
		case 3:
			movebank = new Move[75];
			movebank[0] = Move.ROCK_TOMB;
			movebank[1] = Move.GROWL;
			movebank[5] = Move.LEECH_LIFE;
			movebank[8] = Move.LEAF_SLAP;
			movebank[11] = Move.DOUBLE_SLAP;
			movebank[14] = Move.LEAF_TORNADO;
			movebank[14] = Move.LEAF_TORNADO;
			movebank[16] = Move.MAGICAL_LEAF;
			movebank[20] = Move.MEGA_DRAIN;
			movebank[22] = Move.BRANCH_WHACK;
			movebank[26] = Move.BODY_SLAM;
			movebank[31] = Move.GYRO_BALL;
			movebank[34] = Move.LEAF_STORM;
			movebank[37] = Move.GRASS_PUNCH;
			movebank[39] = Move.ROCK_BLAST;
			movebank[47] = Move.ROOT_CRUSH;
			movebank[54] = Move.BOULDER_CRUSH;
			movebank[64] = Move.FRENZY_PLANT;
			movebank[70] = Move.SUPERPOWER;
			movebank[74] = Move.ROCK_SLIDE;
			break;
		case 4:
			movebank = new Move[15];
			movebank[0] = Move.TACKLE;
			movebank[1] = Move.SCREECH;
			movebank[6] = Move.IGNITE;
			movebank[8] = Move.EMBER;
			movebank[11] = Move.SLAM;
			movebank[14] = Move.FIRE_FANG;
			break;
		case 5:
			movebank = new Move[33];
			movebank[0] = Move.TACKLE;
			movebank[1] = Move.SCREECH;
			movebank[6] = Move.IGNITE;
			movebank[8] = Move.EMBER;
			movebank[11] = Move.SLAM;
			movebank[14] = Move.FIRE_FANG;
			movebank[18] = Move.BLACK_DUST;
			movebank[23] = Move.FIRE_SMASH;
			movebank[27] = Move.SELF_DESTRUCT;
			movebank[32] = Move.LAVA_PLUME;
			break;
		case 6:
			movebank = new Move[65];
			movebank[0] = Move.OVERHEAT;
			movebank[1] = Move.SCREECH;
			movebank[6] = Move.IGNITE;
			movebank[8] = Move.EMBER;
			movebank[11] = Move.SLAM;
			movebank[14] = Move.FIRE_FANG;
			movebank[18] = Move.BLACK_DUST;
			movebank[23] = Move.FIRE_SMASH;
			movebank[27] = Move.SELF_DESTRUCT;
			movebank[32] = Move.LAVA_PLUME;
			movebank[38] = Move.EXPLOSION;
			movebank[44] = Move.ERUPTION;
			movebank[54] = Move.FIRE_BLAST;
			movebank[64] = Move.BLAST_BURN;
			break;
		case 7:
			movebank = new Move[18];
			movebank[0] = Move.TACKLE;
			movebank[1] = Move.TAIL_WHIP;
			movebank[5] = Move.SAND_ATTACK;
			movebank[8] = Move.BUBBLE;
			movebank[10] = Move.BITE;
			movebank[13] = Move.BUBBLEBEAM;
			movebank[17] = Move.WATER_GUN;
			break;
		case 8:
			movebank = new Move[28];
			movebank[0] = Move.TACKLE;
			movebank[1] = Move.TAIL_WHIP;
			movebank[5] = Move.SAND_ATTACK;
			movebank[8] = Move.BUBBLE;
			movebank[10] = Move.BITE;
			movebank[13] = Move.BUBBLEBEAM;
			movebank[17] = Move.WATER_GUN;
			movebank[20] = Move.QUICK_ATTACK;
			movebank[27] = Move.WATER_JET;
			break;
		case 9:
			movebank = new Move[75];
			movebank[0] = Move.WATER_PULSE;
			movebank[1] = Move.TAIL_WHIP;
			movebank[5] = Move.SAND_ATTACK;
			movebank[8] = Move.BUBBLE;
			movebank[10] = Move.BITE;
			movebank[13] = Move.BUBBLEBEAM;
			movebank[17] = Move.WATER_GUN;
			movebank[20] = Move.QUICK_ATTACK;
			movebank[27] = Move.WATER_JET;
			movebank[33] = Move.SWIFT;
			movebank[38] = Move.BRINE;
			movebank[44] = Move.DOUBLE_JET;
			movebank[54] = Move.HYDRO_PUMP;
			movebank[64] = Move.HYDRO_CANNON;
			movebank[70] = Move.GYRO_BALL;
			movebank[74] = Move.HYPER_BEAM;
			break;
		case 10:
			movebank = new Move[15];
			movebank[0] = Move.POKE;
			movebank[3] = Move.LEAF_SLAP;
			movebank[4] = Move.STARE;
			movebank[7] = Move.PUNCH;
			movebank[10] = Move.WOOD_FANG;
			movebank[14] = Move.LEAF_PULSE;
			break;
		case 11:
			movebank = new Move[55];
			movebank[0] = Move.LEAF_BLADE;
			movebank[3] = Move.LEAF_SLAP;
			movebank[4] = Move.STARE;
			movebank[7] = Move.PUNCH;
			movebank[10] = Move.WOOD_FANG;
			movebank[14] = Move.LEAF_PULSE;
			movebank[18] = Move.HEADBUTT;
			movebank[23] = Move.BRANCH_WHACK;
			movebank[28] = Move.LEAF_BALL;
			//movebank[35] = Move.MEAN_LOOK; TODO
			movebank[44] = Move.FAINT_ATTACK;
			movebank[54] = Move.LEAF_STORM;
			break;
		case 12:
			movebank = new Move[27];
			movebank[0] = Move.POUND;
			movebank[2] = Move.LEAF_SLAP;
			movebank[4] = Move.ROOT_KICK;
			movebank[5] = Move.LEER;
			movebank[8] = Move.HEADBUTT;
			movebank[12] = Move.LEAF_TORNADO;
			movebank[16] = Move.ROCK_THROW;
			movebank[21] = Move.ROCK_TOMB;
			movebank[26] = Move.ROCK_BLAST;
			break;
		case 13:
			movebank = new Move[55];
			movebank[0] = Move.POUND;
			movebank[2] = Move.LEAF_SLAP;
			movebank[4] = Move.ROOT_KICK;
			movebank[5] = Move.LEER;
			movebank[8] = Move.HEADBUTT;
			movebank[12] = Move.LEAF_TORNADO;
			movebank[16] = Move.ROCK_THROW;
			movebank[21] = Move.ROCK_TOMB;
			movebank[26] = Move.ROCK_BLAST;
			movebank[30] = Move.LEAF_BALL;
			movebank[34] = Move.STRONG_ARM;
			movebank[35] = Move.SMASH;
			movebank[38] = Move.GROWTH;
			movebank[44] = Move.EARTHQUAKE;
			movebank[48] = Move.ROLLOUT;
			movebank[52] = Move.SUPERPOWER;
			movebank[54] = Move.LEAF_STORM;
			break;
		case 14:
			movebank = new Move[12];
			movebank[0] = Move.SCRATCH;
			movebank[1] = Move.LEER;
			movebank[3] = Move.TAIL_WHIP;
			movebank[6] = Move.TACKLE;
			movebank[11] = Move.TAIL_WHACK;
			break;
		case 15:
			movebank = new Move[45];
			movebank[0] = Move.SCRATCH;
			movebank[1] = Move.LEER;
			movebank[3] = Move.TAIL_WHIP;
			movebank[6] = Move.TACKLE;
			movebank[11] = Move.TAIL_WHACK;
			movebank[16] = Move.BITE;
			movebank[25] = Move.BOUNCE;
			movebank[30] = Move.ZEN_HEADBUTT;
			movebank[37] = Move.CHOMP;
			movebank[44] = Move.HYPER_FANG;
			break;
		case 16:
			movebank = new Move[32];
			movebank[0] = Move.SCRATCH;
			movebank[2] = Move.TAIL_WHIP;
			movebank[5] = Move.NIBBLE;
			movebank[10] = Move.SLAP;
			movebank[14] = Move.DOUBLE_SLAP;
			movebank[18] = Move.PUNCH;
			movebank[22] = Move.BITE;
			movebank[27] = Move.HEADBUTT;
			movebank[31] = Move.ROLLOUT;
			break;
		case 17:
			movebank = new Move[55];
			movebank[0] = Move.SCRATCH;
			movebank[2] = Move.TAIL_WHIP;
			movebank[5] = Move.NIBBLE;
			movebank[10] = Move.SLAP;
			movebank[14] = Move.DOUBLE_SLAP;
			movebank[18] = Move.PUNCH;
			movebank[22] = Move.BITE;
			movebank[27] = Move.HEADBUTT;
			movebank[31] = Move.ROLLOUT;
			movebank[34] = Move.CHOMP;
			movebank[41] = Move.ZEN_HEADBUTT;
			movebank[48] = Move.SUPER_FANG;
			movebank[54] = Move.HYPER_FANG;
			break;
		case 18:
			movebank = new Move[21];
			movebank[0] = Move.TACKLE;
			movebank[2] = Move.DEFENSE_CURL;
			movebank[5] = Move.MUD_SPORT;
			movebank[8] = Move.ROCK_POLISH;
			movebank[11] = Move.ROCK_THROW;
			movebank[15] = Move.ROCK_TOMB;
			movebank[20] = Move.MAGNITUDE;
			break;
		case 19:
			movebank = new Move[36];
			movebank[0] = Move.TACKLE;
			movebank[2] = Move.DEFENSE_CURL;
			movebank[5] = Move.MUD_SPORT;
			movebank[8] = Move.ROCK_POLISH;
			movebank[11] = Move.ROCK_THROW;
			movebank[15] = Move.ROCK_TOMB;
			movebank[20] = Move.MAGNITUDE;
			movebank[25] = Move.ROLLOUT;
			movebank[30] = Move.SLAM;
			movebank[35] = Move.ROCK_BLAST;
			break;
		case 20:
			movebank = new Move[70];
			movebank[0] = Move.TACKLE;
			movebank[2] = Move.DEFENSE_CURL;
			movebank[5] = Move.MUD_SPORT;
			movebank[8] = Move.ROCK_POLISH;
			movebank[11] = Move.ROCK_THROW;
			movebank[15] = Move.ROCK_TOMB;
			movebank[20] = Move.MAGNITUDE;
			movebank[25] = Move.ROLLOUT;
			movebank[30] = Move.SLAM;
			movebank[35] = Move.ROCK_BLAST;
			movebank[38] = Move.STONE_EDGE;
			movebank[42] = Move.EARTHQUAKE;
			movebank[46] = Move.ROCKET;
			movebank[50] = Move.SUPERPOWER;
			movebank[54] = Move.EARTH_POWER;
			movebank[60] = Move.FIRE_BLAST;
			movebank[69] = Move.HYPER_BEAM;
			break;
		case 21:
			movebank = new Move[21];
			movebank[0] = Move.TACKLE;
			movebank[1] = Move.DEFENSE_CURL;
			movebank[4] = Move.ROCK_POLISH;
			movebank[10] = Move.SLAM;
			movebank[15] = Move.RAPID_SPIN;
			movebank[20] = Move.ROLLOUT;
			break;
		case 22:
			movebank = new Move[39];
			movebank[0] = Move.TACKLE;
			movebank[1] = Move.DEFENSE_CURL;
			movebank[4] = Move.ROCK_POLISH;
			movebank[10] = Move.SLAM;
			movebank[15] = Move.RAPID_SPIN;
			movebank[20] = Move.ROLLOUT;
			movebank[27] = Move.STOMP;
			movebank[33] = Move.ROCK_TOMB;
			movebank[38] = Move.ROCK_BLADE;
			break;
		case 23:
			movebank = new Move[60];
			movebank[0] = Move.TACKLE;
			movebank[1] = Move.DEFENSE_CURL;
			movebank[4] = Move.ROCK_POLISH;
			movebank[10] = Move.SLAM;
			movebank[15] = Move.RAPID_SPIN;
			movebank[20] = Move.ROLLOUT;
			movebank[27] = Move.STOMP;
			movebank[33] = Move.ROCK_TOMB;
			movebank[38] = Move.ROCK_BLADE;
			movebank[39] = Move.WING_ATTACK;
			movebank[44] = Move.BOULDER_SLAM;
			movebank[49] = Move.SPIKE_SHOT;
			movebank[54] = Move.DOUBLE_EDGE;
			movebank[59] = Move.SKY_ATTACK;
			break;
		case 24:
			movebank = new Move[19];
			movebank[0] = Move.NIBBLE;
			movebank[1] = Move.LEER;
			movebank[3] = Move.BUBBLE;
			movebank[6] = Move.BITE;
			movebank[9] = Move.WATER_GUN;
			movebank[13] = Move.CHOMP;
			movebank[18] = Move.HYPER_FANG;
			break;
		case 25:
			movebank = new Move[55];
			movebank[0] = Move.THUNDER;
			movebank[1] = Move.LEER;
			movebank[3] = Move.BUBBLE;
			movebank[6] = Move.BITE;
			movebank[9] = Move.WATER_GUN;
			movebank[13] = Move.CHOMP;
			movebank[18] = Move.HYPER_FANG;
			movebank[22] = Move.SPARK;
			movebank[27] = Move.DOUBLE_SLAP;
			movebank[31] = Move.TIDAL_WAVE;
			movebank[36] = Move.DOUBLE_BLAST;
			movebank[45] = Move.THUNDERBOLT;
			movebank[54] = Move.ELECTROBALL;
			break;
		case 26:
			movebank = new Move[15];
			movebank[0] = Move.TACKLE;
			movebank[4] = Move.SAND_ATTACK;
			movebank[6] = Move.FORESIGHT;
			movebank[9] = Move.GUST;
			movebank[12] = Move.PECK;
			movebank[14] = Move.WING_ATTACK;
			break;
		case 27:
			movebank = new Move[33];movebank[0] = Move.TACKLE;
			movebank[4] = Move.SAND_ATTACK;
			movebank[6] = Move.FORESIGHT;
			movebank[9] = Move.GUST;
			movebank[12] = Move.PECK;
			movebank[14] = Move.WING_ATTACK;
			movebank[17] = Move.QUICK_ATTACK;
			movebank[19] = Move.WHIRLWIND;
			movebank[23] = Move.TWISTER;
			movebank[27] = Move.AGILITY;
			movebank[32] = Move.ROOST;
			break;
		case 28:
			movebank = new Move[55];
			movebank[0] = Move.TACKLE;
			movebank[4] = Move.SAND_ATTACK;
			movebank[6] = Move.FORESIGHT;
			movebank[9] = Move.GUST;
			movebank[12] = Move.PECK;
			movebank[14] = Move.WING_ATTACK;
			movebank[17] = Move.QUICK_ATTACK;
			movebank[19] = Move.WHIRLWIND;
			movebank[23] = Move.TWISTER;
			movebank[27] = Move.AGILITY;
			movebank[32] = Move.ROOST;
			movebank[39] = Move.MIRROR_MOVE;
			movebank[44] = Move.DRILL_PECK;
			movebank[49] = Move.AIR_SLASH;
			movebank[54] = Move.BRAVE_BIRD;
			break;
		case 29:
			break;
		case 30:
			break;
		case 31:
			break;
		case 32:
			break;
		case 33:
			break;
		case 34:
			break;
		case 35:
			break;
		case 36:
			break;
		case 37:
			break;
		case 38:
			break;
		case 39:
			break;
		case 40:
			break;
		case 41:
			break;
		case 42:
			break;
		case 43:
			break;
		case 44:
			break;
		case 45:
			break;
		case 46:
			break;
		case 47:
			break;
		case 48:
			break;
		case 49:
			break;
		case 50:
			break;
		case 51:
			break;
		case 52:
			break;
		case 53:
			break;
		case 54:
			break;
		case 55:
			break;
		case 56:
			break;
		case 57:
			break;
		case 58:
			break;
		case 59:
			break;
		case 60:
			break;
		case 61:
			break;
		case 62:
			break;
		case 63:
			break;
		case 64:
			break;
		case 65:
			break;
		case 66:
			break;
		case 67:
			break;
		case 68:
			break;
		case 69:
			break;
		case 70:
			break;
		case 71:
			break;
		case 72:
			break;
		case 73:
			break;
		case 74:
			break;
		case 75:
			break;
		case 76:
			break;
		case 77:
			break;
		case 78:
			break;
		case 79:
			break;
		case 80:
			break;
		case 81:
			break;
		case 82:
			break;
		case 83:
			break;
		case 84:
			break;
		case 85:
			break;
		case 86:
			break;
		case 87:
			break;
		case 88:
			break;
		case 89:
			break;
		case 90:
			break;
		case 91:
			break;
		case 92:
			break;
		case 93:
			break;
		case 94:
			break;
		case 95:
			break;
		case 96:
			break;
		case 97:
			break;
		case 98:
			break;
		case 99:
			break;
		case 100:
			break;
		case 101:
			break;
		case 102:
			break;
		case 103:
			break;
		case 104:
			break;
		case 105:
			break;
		case 106:
			break;
		case 107:
			break;
		case 108:
			break;
		case 109:
			break;
		case 110:
			break;
		case 111:
			break;
		case 112:
			break;
		case 113:
			break;
		case 114:
			break;
		case 115:
			break;
		case 116:
			break;
		case 117:
			break;
		case 118:
			break;
		case 119:
			break;
		case 120:
			break;
		case 121:
			break;
		case 122:
			break;
		case 123:
			break;
		case 124:
			break;
		case 125:
			break;
		case 126:
			break;
		case 127:
			break;
		case 128:
			break;
		case 129:
			break;
		case 130:
			break;
		case 131:
			break;
		case 132:
			break;
		case 133:
			break;
		case 134:
			break;
		case 135:
			break;
		case 136:
			break;
		case 137:
			break;
		case 138:
			break;
		case 139:
			break;
		case 140:
			break;
		}
		
	}

	public void faint() {
		this.fainted = true;
		
	}

	public void clearVolatile() {
		if (this.status == Status.CONFUSED) {
			confusionCounter = 0;
			this.status = Status.HEALTHY;
		}
		if (this.status == Status.CURSED) this.status = Status.HEALTHY;
		statStages = new int[7];
		setType();
		
	}
}