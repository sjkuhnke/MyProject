package Swing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JOptionPane;

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
	private int[] ivs;
	
	public PType type1;
	public PType type2;
	
	public Move[] movebank;
	public Move[] moveset;
	
	private Status status;
	public ArrayList<Status> vStatuses;
	
	public int exp;
	public int expMax;
	
	public int currentHP;
	private boolean fainted;
	
	private int confusionCounter;
	private int sleepCounter;
	private int perishCount;
	private int magCount;
	private Move lastMoveUsed;
	private int moveMultiplier;
	private boolean trainerOwned;
	private int spunCount;
	public boolean impressive;
	public boolean battled;
	private int outCount;
	private int rollCount;
	private double trainer;
	
	public Pokemon(int i, int l, boolean o, boolean t) {
		id = i;
		name = getName();
		
		baseStats = new int[6];
		stats = new int[6];
		ivs = new int[6];
		level = l;
		statStages = new int[7];
		
		setBaseStats();
		for (int j = 0; j < 6; j++) { ivs[j] = (int) (Math.random() * 32); }
		getStats();
		setType();
		
		expMax = level * 2;
		exp = 0;
		
		currentHP = this.getStat(0);
		moveset = new Move[4];
		setMoveBank();
		setMoves();
		moveMultiplier = 1;
		
		status = Status.HEALTHY;
		vStatuses = new ArrayList<Status>();
		
		trainerOwned = o;
		impressive = true;
		trainer = 1;
		if (t) {
			trainer = 1.5;
			ivs = new int[] {31, 31, 31, 31, 31, 31};
		}
		
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
	
	public Move bestMove(Pokemon foe) {
	    ArrayList<Move> validMoves = new ArrayList<>();

	    // Add all non-null moves to the validMoves list
	    for (Move move : moveset) {
	        if (move != null) {
	            validMoves.add(move);
	        }
	    }

	    ArrayList<Move> statusMoves = new ArrayList<>();
	    ArrayList<Move> damagingMoves = new ArrayList<>();

	    // Split the moves into status and damaging moves
	    for (Move move : validMoves) {
	        if (move.cat == 2) {
	            statusMoves.add(move);
	        } else {
	            damagingMoves.add(move);
	        }
	    }

	    // Determine if a status move should be used
	    boolean useStatusMove = false;
	    if (!statusMoves.isEmpty()) {
	        int randomChance = (int) (Math.random() * 4); // 25% chance
	        useStatusMove = randomChance == 0;
	    }

	    if (useStatusMove && !statusMoves.isEmpty()) {
	        // If a status move should be used, randomly select one
	        int randomIndex = (int) (Math.random() * statusMoves.size());
	        return statusMoves.get(randomIndex);
	    } else {
	        // Otherwise, find the move with the highest damage
	        int maxDamage = 0;
	        ArrayList<Move> bestMoves = new ArrayList<>();

	        for (Move move : damagingMoves) {
	            int damage = calcWithTypes(foe, move);
	            if (damage > foe.currentHP) damage = foe.currentHP;
	            if (damage > maxDamage) {
	                maxDamage = damage;
	                bestMoves.clear();
	                bestMoves.add(move);
	            } else if (damage == maxDamage) {
	                bestMoves.add(move);
	            }
	        }

	        // If all valid moves have the same damage, randomly select one of them
	        if (bestMoves.size() > 1) {
	            int randomIndex = (int) (Math.random() * bestMoves.size());
	            return bestMoves.get(randomIndex);
	        }

	        // Otherwise, return the move with the highest damage
	        return bestMoves.get(0);
	    }
	}



	public Pokemon(int i, int l, Move[] set, int[] iv) {
		id = i;
		name = getName();
		
		baseStats = new int[6];
		stats = new int[6];
		level = l;
		statStages = new int[7];
		ivs = iv;
		
		setBaseStats();
		getStats();
		setType();
		
		expMax = level * 2;
		exp = 0;
		
		currentHP = this.getStat(0);
		setMoveBank();
		moveset = set;
		
		status = Status.HEALTHY;
		vStatuses = new ArrayList<Status>();
		
		trainerOwned = true;
		impressive = true;
		trainer = 1;
	}
	
	public boolean isFainted() {
		return this.fainted;
	}
	

	private void setType() {
		if (id == -1) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == -2) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == -3) {
			this.type1 = PType.GRASS;
			this.type2 = PType.ROCK;
		} else if (id == -4) {
			this.type1 = PType.FIRE;
			this.type2 = null;
		} else if (id == -5) {
			this.type1 = PType.FIRE;
			this.type2 = null;
		} else if (id == -6) {
			this.type1 = PType.FIRE;
			this.type2 = PType.ROCK;
		} else if (id == -7) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == -8) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == -9) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == -10) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == -11) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == -12) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == -13) {
			this.type1 = PType.GRASS;
			this.type2 = PType.FIGHTING;
		} else if (id == -14) {
			this.type1 = PType.NORMAL;
			this.type2 = null;
		} else if (id == -15) {
			this.type1 = PType.NORMAL;
			this.type2 = null;
		} else if (id == -16) {
			this.type1 = PType.NORMAL;
			this.type2 = null;
		} else if (id == -17) {
			this.type1 = PType.NORMAL;
			this.type2 = null;
		} else if (id == -18) {
			this.type1 = PType.ROCK;
			this.type2 = PType.GROUND;
		} else if (id == -19) {
			this.type1 = PType.ROCK;
			this.type2 = PType.GROUND;
		} else if (id == -20) {
			this.type1 = PType.ROCK;
			this.type2 = PType.GROUND;
		} else if (id == -21) {
			this.type1 = PType.ROCK;
			this.type2 = null;
		} else if (id == -22) {
			this.type1 = PType.ROCK;
			this.type2 = null;
		} else if (id == -23) {
			this.type1 = PType.ROCK;
			this.type2 = PType.FLYING;
		} else if (id == -24) {
			this.type1 = PType.WATER;
			this.type2 = PType.DARK;
		} else if (id == -25) {
			this.type1 = PType.WATER;
			this.type2 = PType.ELECTRIC;
		} else if (id == -26) {
			this.type1 = PType.WATER;
			this.type2 = PType.FLYING;
		} else if (id == -27) {
			this.type1 = PType.WATER;
			this.type2 = PType.FLYING;
		} else if (id == -28) {
			this.type1 = PType.WATER;
			this.type2 = PType.FLYING;
		} else if (id == -29) {
			this.type1 = PType.NORMAL;
			this.type2 = PType.FLYING;
		} else if (id == -30) {
			this.type1 = PType.NORMAL;
			this.type2 = PType.FLYING;
		} else if (id == -31) {
			this.type1 = PType.WATER;
			this.type2 = PType.FLYING;
		} else if (id == -32) {
			this.type1 = PType.GRASS;
			this.type2 = PType.FLYING;
		} else if (id == -33) {
			this.type1 = PType.BUG;
			this.type2 = PType.POISON;
		} else if (id == -34) {
			this.type1 = PType.BUG;
			this.type2 = PType.POISON;
		} else if (id == -35) {
			this.type1 = PType.BUG;
			this.type2 = null;
		} else if (id == -36) {
			this.type1 = PType.BUG;
			this.type2 = PType.POISON;
		} else if (id == -37) {
			this.type1 = PType.GHOST;
			this.type2 = PType.POISON;
		} else if (id == -38) {
			this.type1 = PType.GHOST;
			this.type2 = null;
		} else if (id == -39) {
			this.type1 = PType.GHOST;
			this.type2 = PType.STEEL;
		} else if (id == -40) {
			this.type1 = PType.GHOST;
			this.type2 = PType.DARK;
		} else if (id == -41) {
			this.type1 = PType.GHOST;
			this.type2 = PType.DARK;
		} else if (id == -42) {
			this.type1 = PType.ELECTRIC;
			this.type2 = null;
		} else if (id == -43) {
			this.type1 = PType.NORMAL;
			this.type2 = PType.ELECTRIC;
		} else if (id == -44) {
			this.type1 = PType.ELECTRIC;
			this.type2 = null;
		} else if (id == -45) {
			this.type1 = PType.ELECTRIC;
			this.type2 = PType.STEEL;;
		} else if (id == -46) {
			this.type1 = PType.ELECTRIC;
			this.type2 = null;
		} else if (id == -47) {
			this.type1 = PType.ELECTRIC;
			this.type2 = null;
		} else if (id == -48) {
			this.type1 = PType.BUG;
			this.type2 = PType.FIGHTING;
		} else if (id == -49) {
			this.type1 = PType.BUG;
			this.type2 = PType.FIGHTING;
		} else if (id == -50) {
			this.type1 = PType.BUG;
			this.type2 = PType.FIGHTING;
		} else if (id == -51) {
			this.type1 = PType.FIGHTING;
			this.type2 = null;
		} else if (id == -52) {
			this.type1 = PType.FIGHTING;
			this.type2 = null;
		} else if (id == -53) {
			this.type1 = PType.FIGHTING;
			this.type2 = null;
		} else if (id == -54) {
			this.type1 = PType.FIGHTING;
			this.type2 = null;
		} else if (id == -55) {
			this.type1 = PType.POISON;
			this.type2 = null;
		} else if (id == -56) {
			this.type1 = PType.POISON;
			this.type2 = null;
		} else if (id == -57) {
			this.type1 = PType.POISON;
			this.type2 = PType.FLYING;
		} else if (id == -58) {
			this.type1 = PType.POISON;
			this.type2 = PType.FLYING;
		} else if (id == -59) {
			this.type1 = PType.POISON;
			this.type2 = PType.GRASS;
		} else if (id == -60) {
			this.type1 = PType.POISON;
			this.type2 = PType.GRASS;
		} else if (id == -61) {
			this.type1 = PType.WATER;
			this.type2 = PType.POISON;
		} else if (id == -62) {
			this.type1 = PType.WATER;
			this.type2 = PType.POISON;
		} else if (id == -63) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == -64) {
			this.type1 = PType.WATER;
			this.type2 = PType.FIGHTING;
		} else if (id == -65) {
			this.type1 = PType.WATER;
			this.type2 = PType.FLYING;
		} else if (id == -66) {
			this.type1 = PType.WATER;
			this.type2 = PType.FLYING;
		} else if (id == -67) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == -68) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == -69) {
			this.type1 = PType.WATER;
			this.type2 = PType.DARK;
		} else if (id == -70) {
			this.type1 = PType.WATER;
			this.type2 = PType.DRAGON;
		} else if (id == -71) {
			this.type1 = PType.GROUND;
			this.type2 = null;
		} else if (id == -72) {
			this.type1 = PType.GROUND;
			this.type2 = null;
		} else if (id == -73) {
			this.type1 = PType.GROUND;
			this.type2 = PType.FIGHTING;
		} else if (id == -74) {
			this.type1 = PType.FIRE;
			this.type2 = null;
		} else if (id == -75) {
			this.type1 = PType.FIRE;
			this.type2 = PType.FIGHTING;
		} else if (id == -76) {
			this.type1 = PType.FIRE;
			this.type2 = PType.FIGHTING;
		} else if (id == -77) {
			this.type1 = PType.FIRE;
			this.type2 = PType.GROUND;
		} else if (id == -78) {
			this.type1 = PType.FIRE;
			this.type2 = PType.FLYING;
		} else if (id == -79) {
			this.type1 = PType.FIRE;
			this.type2 = PType.DRAGON;
		} else if (id == -80) {
			this.type1 = PType.FIRE;
			this.type2 = PType.STEEL;
		} else if (id == -81) {
			this.type1 = PType.FIRE;
			this.type2 = PType.STEEL;
		} else if (id == -82) {
			this.type1 = PType.WATER;
			this.type2 = PType.FLYING;
		} else if (id == -83) {
			this.type1 = PType.ELECTRIC;
			this.type2 = PType.FLYING;
		} else if (id == -84) {
			this.type1 = PType.FIRE;
			this.type2 = null;
		} else if (id == -85) {
			this.type1 = PType.FIRE;
			this.type2 = PType.FLYING;
		} else if (id == -86) {
			this.type1 = PType.DARK;
			this.type2 = PType.BUG;
		} else if (id == -87) {
			this.type1 = PType.DARK;
			this.type2 = PType.BUG;
		} else if (id == -88) {
			this.type1 = PType.BUG;
			this.type2 = PType.STEEL;
		} else if (id == -89) {
			this.type1 = PType.DARK;
			this.type2 = null;
		} else if (id == -90) {
			this.type1 = PType.DARK;
			this.type2 = null;
		} else if (id == -91) {
			this.type1 = PType.DARK;
			this.type2 = PType.MAGIC;
		} else if (id == -92) {
			this.type1 = PType.ELECTRIC;
			this.type2 = null;
		} else if (id == -93) {
			this.type1 = PType.GHOST;
			this.type2 = PType.FIRE;
		} else if (id == -94) {
			this.type1 = PType.GHOST;
			this.type2 = PType.FIRE;
		} else if (id == -95) {
			this.type1 = PType.NORMAL;
			this.type2 = PType.STEEL;
		} else if (id == -96) {
			this.type1 = PType.NORMAL;
			this.type2 = PType.STEEL;
		} else if (id == -97) {
			this.type1 = PType.STEEL;
			this.type2 = null;
		} else if (id == -98) {
			this.type1 = PType.STEEL;
			this.type2 = null;
		} else if (id == -99) {
			this.type1 = PType.STEEL;
			this.type2 = null;
		} else if (id == -100) {
			this.type1 = PType.DRAGON;
			this.type2 = null;
		} else if (id == -101) {
			this.type1 = PType.DRAGON;
			this.type2 = null;
		} else if (id == -102) {
			this.type1 = PType.DRAGON;
			this.type2 = PType.FLYING;
		} else if (id == -103) {
			this.type1 = PType.DRAGON;
			this.type2 = PType.FIGHTING;
		} else if (id == -104) {
			this.type1 = PType.DRAGON;
			this.type2 = PType.FLYING;
		} else if (id == -105) {
			this.type1 = PType.DRAGON;
			this.type2 = PType.FIRE;
		} else if (id == -106) {
			this.type1 = PType.MAGIC;
			this.type2 = null;
		} else if (id == -107) {
			this.type1 = PType.MAGIC;
			this.type2 = null;
		} else if (id == -108) {
			this.type1 = PType.MAGIC;
			this.type2 = PType.FIRE;
		} else if (id == -109) {
			this.type1 = PType.MAGIC;
			this.type2 = null;
		} else if (id == -110) {
			this.type1 = PType.MAGIC;
			this.type2 = null;
		} else if (id == -111) {
			this.type1 = PType.MAGIC;
			this.type2 = null;
		} else if (id == -112) {
			this.type1 = PType.NORMAL;
			this.type2 = null;
		} else if (id == -113) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == -114) {
			this.type1 = PType.FIRE;
			this.type2 = null;
		} else if (id == -115) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == -116) {
			this.type1 = PType.POISON;
			this.type2 = null;
		} else if (id == -117) {
			this.type1 = PType.ELECTRIC;
			this.type2 = null;
		} else if (id == -118) {
			this.type1 = PType.ROCK;
			this.type2 = null;
		} else if (id == -119) {
			this.type1 = PType.DARK;
			this.type2 = null;
		} else if (id == -120) {
			this.type1 = PType.STEEL;
			this.type2 = null;
		} else if (id == -121) {
			this.type1 = PType.FIGHTING;
			this.type2 = null;
		} else if (id == -122) {
			this.type1 = PType.DRAGON;
			this.type2 = null;
		} else if (id == -123) {
			this.type1 = PType.MAGIC;
			this.type2 = null;
		} else if (id == -124) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == -125) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == -126) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == -127) {
			this.type1 = PType.FIRE;
			this.type2 = null;
		} else if (id == -128) {
			this.type1 = PType.FIRE;
			this.type2 = null;
		} else if (id == -129) {
			this.type1 = PType.FIRE;
			this.type2 = null;
		} else if (id == -130) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == -131) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == -132) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == -133) {
			this.type1 = PType.ELECTRIC;
			this.type2 = PType.DRAGON;
		} else if (id == -134) {
			this.type1 = PType.FIRE;
			this.type2 = PType.DRAGON;
		} else if (id == -135) {
			this.type1 = PType.NORMAL;
			this.type2 = PType.MAGIC;
		} else if (id == -136) {
			this.type1 = PType.BUG;
			this.type2 = PType.MAGIC;
		} else if (id == -137) {
			this.type1 = PType.FLYING;
			this.type2 = PType.MAGIC;
		} else if (id == -138) {
			this.type1 = PType.NORMAL;
			this.type2 = PType.MAGIC;
		} else if (id == -139) {
			this.type1 = PType.BUG;
			this.type2 = PType.MAGIC;
		} else if (id == -140) {
			this.type1 = PType.FLYING;
			this.type2 = PType.MAGIC;
		} else if (id == -141) {
			this.type1 = PType.POISON;
			this.type2 = PType.FIRE;
		} else if (id == -142) {
			this.type1 = PType.POISON;
			this.type2 = PType.WATER;
		} else if (id == -143) {
			this.type1 = PType.POISON;
			this.type2 = PType.FIRE;
		} else if (id == -144) {
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
		if (id == 1) { return "Twigle";
		} else if (id == 2) { return "Torgged";
		} else if (id == 3) { return "Tortugis";
		} else if (id == 4) { return "Lagma";
		} else if (id == 5) { return "Maguide";
		} else if (id == 6) { return "Magron";
		} else if (id == 7) { return "Lizish";
		} else if (id == 8) { return "Iguaton";
		} else if (id == 9) { return "Dragave";
		} else if (id == 10) { return "Hummingspark";
		} else if (id == 11) { return "Flashclaw";
		} else if (id == 12) { return "Magestiflash";
		} else if (id == 13) { return "Pigo";
		} else if (id == 14) { return "Pigonat";
		} else if (id == 15) { return "Pigoga";
		} else if (id == 16) { return "Hammo";
		} else if (id == 17) { return "HammyBoy";
		} else if (id == 18) { return "Hamthorno";
		} else if (id == 19) { return "Sheabear";
		} else if (id == 20) { return "Dualbear";
		} else if (id == 21) { return "Spacebear";
		} else if (id == 22) { return "Bealtle";
		} else if (id == 23) { return "Centatle";
		} else if (id == 24) { return "Curlatoral";
		} else if (id == 25) { return "Millistone";
		} else if (id == 26) { return "Sapwin";
		} else if (id == 27) { return "Treewin";
		} else if (id == 28) { return "Winagrow";
		} else if (id == 29) { return "Budew";
		} else if (id == 30) { return "Roselia";
		} else if (id == 31) { return "Roserade";
		} else if (id == 32) { return "Sewaddle";
		} else if (id == 33) { return "Swadloon";
		} else if (id == 34) { return "Leavanny";
		} else if (id == 35) { return "Grubbin";
		} else if (id == 36) { return "Charjabug";
		} else if (id == 37) { return "Vikavolt";
		} else if (id == 38) { return "Busheep";
		} else if (id == 39) { return "Ramant";
		} else if (id == 40) { return "Bushewe";
		} else if (id == 41) { return "Bugik";
		} else if (id == 42) { return "Swordik";
		} else if (id == 43) { return "Ninjakik";
		} else if (id == 44) { return "Lotad";
		} else if (id == 45) { return "Lombre";
		} else if (id == 46) { return "Ludicolo";
		} else if (id == 47) { return "Bluebunn";
		} else if (id == 48) { return "Rocky";
		} else if (id == 49) { return "Boulder";
		} else if (id == 50) { return "Blaster";
		} else if (id == 51) { return "Crystallor";
		} else if (id == 52) { return "Carinx";
		} else if (id == 53) { return "Carinator";
		} else if (id == 54) { return "Cairnasaur";
		} else if (id == 55) { return "Pebblepup";
		} else if (id == 56) { return "Boulderoar";
		} else if (id == 57) { return "Fightorex";
		} else if (id == 58) { return "Raptorex";
		} else if (id == 59) { return "Kleinowl";
		} else if (id == 60) { return "Hootowl";
		} else if (id == 61) { return "Dualmoose";
		} else if (id == 62) { return "Snom";
		} else if (id == 63) { return "Frosmoth";
		} else if (id == 64) { return "Grondor";
		} else if (id == 65) { return "Bipedice";
		} else if (id == 66) { return "Tricerpup";
		} else if (id == 67) { return "Tricercil";
		} else if (id == 68) { return "Spheal";
		} else if (id == 69) { return "Sealeo";
		} else if (id == 70) { return "Walrein";
		} else if (id == 71) { return "Froshrog";
		} else if (id == 72) { return "Bouncerog";
		} else if (id == 73) { return "Bugop";
		} else if (id == 74) { return "Opwing";
		} else if (id == 75) { return "Hatenna";
		} else if (id == 76) { return "Hattrem";
		} else if (id == 77) { return "Hatterene";
		} else if (id == 78) { return "Otterpor";
		} else if (id == 79) { return "Psylotter";
		} else if (id == 80) { return "Florline";
		} else if (id == 81) { return "Florlion";
		} else if (id == 82) { return "Psycorb";
		} else if (id == 83) { return "Psyballs";
		} else if (id == 84) { return "Psycorborator";
		} else if (id == 85) { return "Ralts";
		} else if (id == 86) { return "Kirlia";
		} else if (id == 87) { return "Gardevoir";
		} else if (id == 88) { return "Gallade";
		} else if (id == 89) { return "Tigrette";
		} else if (id == 90) { return "Inkay";
		} else if (id == 91) { return "Malamar";
		} else if (id == 92) { return "Flameruff";
		} else if (id == 93) { return "Barkflare";
		} else if (id == 94) { return "Iglite";
		} else if (id == 95) { return "Blaxer";
		} else if (id == 96) { return "Pyrator";
		} else if (id == 97) { return "Magmaclang";
		} else if (id == 98) { return "Flamehox";
		} else if (id == 99) { return "Fireshard";
		} else if (id == 100) { return "Blastflames";
		} else if (id == 101) { return "Tiowoo";
		} else if (id == 102) { return "Magwoo";
		} else if (id == 103) { return "Lafloo";
		} else if (id == 104) { return "Houndour";
		} else if (id == 105) { return "Houndoom";
		} else if (id == 106) { return "Sparkdust";
		} else if (id == 107) { return "Splame";
		} else if (id == 108) { return "Sparkitten";
		} else if (id == 109) { return "Fireblion";
		} else if (id == 110) { return "Flamebless";
		} else if (id == 111) { return "Shookwat";
		} else if (id == 112) { return "Wattwo";
		} else if (id == 113) { return "Megawatt";
		} else if (id == 114) { return "Elelamb";
		} else if (id == 115) { return "Electroram";
		} else if (id == 116) { return "Superchargo";
		} else if (id == 117) { return "Twigzap";
		} else if (id == 118) { return "Shockbranch";
		} else if (id == 119) { return "Thunderzap";
		} else if (id == 120) { return "Magie";
		} else if (id == 121) { return "Cumin";
		} else if (id == 122) { return "Cinneroph";
		} else if (id == 123) { return "Vupp";
		} else if (id == 124) { return "Vinnie";
		} else if (id == 125) { return "Suvinero";
		} else if (id == 126) { return "Whiskie";
		} else if (id == 127) { return "Whiskers";
		} else if (id == 128) { return "Whiskeroar";
		} else if (id == 129) { return "Nincada";
		} else if (id == 130) { return "Ninjask";
		} else if (id == 131) { return "Shedinja";
		} else if (id == 132) { return "Sheltor";
		} else if (id == 133) { return "Shelnado";
		} else if (id == 134) { return "Lilyray";
		} else if (id == 135) { return "Daray";
		} else if (id == 136) { return "Spinaquata";
		} else if (id == 137) { return "Magikarp";
		} else if (id == 138) { return "Gyarados";
		} else if (id == 139) { return "Staryu";
		} else if (id == 140) { return "Starmie";
		} else if (id == 141) { return "Ali";
		} else if (id == 142) { return "Batorali";
		} else if (id == 143) { return "Posho";
		} else if (id == 144) { return "Shomp";
		} else if (id == 145) { return "Poshorump";
		} else if (id == 146) { return "Binacle";
		} else if (id == 147) { return "Barbaracle";
		} else if (id == 148) { return "Durfish";
		} else if (id == 149) { return "Dompster";
		} else if (id == 150) { return "Kissyfishy";
		} else if (id == 151) { return "Ekans";
		} else if (id == 152) { return "Arbok";
		} else if (id == 153) { return "Zubat";
		} else if (id == 154) { return "Golbat";
		} else if (id == 155) { return "Crobat";
		} else if (id == 156) { return "Poof";
		} else if (id == 157) { return "Hast";
		} else if (id == 158) { return "Poov";
		} else if (id == 159) { return "Grust";
		} else if (id == 160) { return "Cluuz";
		} else if (id == 161) { return "Zurrclu";
		} else if (id == 162) { return "Zurroarator";
		} else if (id == 163) { return "Timburr";
		} else if (id == 164) { return "Gurdurr";
		} else if (id == 165) { return "Conkeldurr";
		} else if (id == 166) { return "Rhypo";
		} else if (id == 167) { return "Rhynee";
		} else if (id == 168) { return "Rhypolar";
		} else if (id == 169) { return "Diggie";
		} else if (id == 170) { return "Drillatron";
		} else if (id == 171) { return "Wormite";
		} else if (id == 172) { return "Wormbot";
		} else if (id == 173) { return "Wormatron";
		} else if (id == 174) { return "Cleffa";
		} else if (id == 175) { return "Clefairy";
		} else if (id == 176) { return "Clefable";
		} else if (id == 177) { return "Minishoo";
		} else if (id == 178) { return "Glittleshoo";
		} else if (id == 179) { return "Zorua";
		} else if (id == 180) { return "Zoroark";
		} else if (id == 181) { return "Droid";
		} else if (id == 182) { return "Armoid";
		} else if (id == 183) { return "Soldrota";
		} else if (id == 184) { return "Tinkie";
		} else if (id == 185) { return "Shawar";
		} else if (id == 186) { return "Shaboom";
		} else if (id == 187) { return "Dragee";
		} else if (id == 188) { return "Draga";
		} else if (id == 189) { return "Drageye";
		} else if (id == 190) { return "Blobmo";
		} else if (id == 191) { return "Nebulimb";
		} else if (id == 192) { return "Galactasoldier";
		} else if (id == 193) { return "Consodust";
		} else if (id == 194) { return "Cosmocrash";
		} else if (id == 195) { return "Rockmite";
		} else if (id == 196) { return "Stellarock";
		} else if (id == 197) { return "Poof-E";
		} else if (id == 198) { return "Hast-E";
		} else if (id == 199) { return "Droid-E";
		} else if (id == 200) { return "Armoid-E";
		} else if (id == 201) { return "Soldrota-E";
		} else if (id == 202) { return "Flamehox-E";
		} else if (id == 203) { return "Fireshard-E";
		} else if (id == 204) { return "Blastflames-E";
		} else if (id == 205) { return "Rocky-E";
		} else if (id == 206) { return "Boulder-E";
		} else if (id == 207) { return "Blaster-E";
		} else if (id == 208) { return "Crystallor-E";
		} else if (id == 209) { return "Magikarp-E";
		} else if (id == 210) { return "Gyarados-E";
		} else if (id == 211) { return "Shockfang";
		} else if (id == 212) { return "Electrocobra";
		} else if (id == 213) { return "Nightrex";
		} else if (id == 214) { return "Shadowsaur";
		} else if (id == 215) { return "Durfish-S";
		} else if (id == 216) { return "Dompster-S";
		} else if (id == 217) { return "Wormite-S";
		} else if (id == 218) { return "Wormbot-S";
		} else if (id == 219) { return "Wormatron-S";
		} else if (id == 220) { return "Cluuz-S";
		} else if (id == 221) { return "Zurrclu-S";
		} else if (id == 222) { return "Zurroarator-S";
		} else if (id == 223) { return "Iglite-S";
		} else if (id == 224) { return "Blaxer-S";
		} else if (id == 225) { return "Pyrator-S";
		} else if (id == 226) { return "Ekans-S";
		} else if (id == 227) { return "Arbok-S";
		} else if (id == 228) { return "Soarwhell";
		} else if (id == 229) { return "Diftery";
		} else if (id == 230) { return "Vorsuitex";
		} else if (id == 231) { return "Kleinyeti";
		} else if (id == 232) { return "Triwandoliz";
		} else if (id == 233) { return "Relomidel";
		} else if (id == 234) { return "Relopamil";
		} else if (id == 235) { return "Dragowrath";
		} else if (id == 236) { return "Solaroxyous";
		} else if (id == 237) { return "Kissyfishy-D";
		
		
		} else if (id == -1) { return "Leafer";
		} else if (id == -2) { return "Sticker";
		} else if (id == -3) { return "Tree-a-tar";
		} else if (id == -4) { return "Fwoochar";
		} else if (id == -5) { return "Charchar";
		} else if (id == -6) { return "Charwoo";
		} else if (id == -7) { return "Poletad";
		} else if (id == -8) { return "Tadwhirl";
		} else if (id == -9) { return "Tadtoad";
		} else if (id == -10) { return "Twigzig";
		} else if (id == -11) { return "Twanzig";
		} else if (id == -12) { return "Sapwin";
		} else if (id == -13) { return "Treewin";
		} else if (id == -14) { return "Hops";
		} else if (id == -15) { return "Bouncey";
		} else if (id == -16) { return "Hammo";
		} else if (id == -17) { return "HammyBoy";
		} else if (id == -18) { return "Rocky";
		} else if (id == -19) { return "Boulder";
		} else if (id == -20) { return "Blaster";
		} else if (id == -21) { return "Spike";
		} else if (id == -22) { return "Spiko";
		} else if (id == -23) { return "Spikoga";
		} else if (id == -24) { return "Chompee";
		} else if (id == -25) { return "Chompo";
		} else if (id == -26) { return "Duckwee";
		} else if (id == -27) { return "Duckwack";
		} else if (id == -28) { return "Duckstrike";
		} else if (id == -29) { return "Chirpus";
		} else if (id == -30) { return "Quackus";
		} else if (id == -31) { return "Crane";
		} else if (id == -32) { return "Plankik";
		} else if (id == -33) { return "Nug";
		} else if (id == -34) { return "Contrug";
		} else if (id == -35) { return "Wasp";
		} else if (id == -36) { return "Mosquitto";
		} else if (id == -37) { return "Cluuz";
		} else if (id == -38) { return "Poof";
		} else if (id == -39) { return "Hast";
		} else if (id == -40) { return "Poov";
		} else if (id == -41) { return "Grust";
		} else if (id == -42) { return "Smartwiz";
		} else if (id == -43) { return "Vinnie";
		} else if (id == -44) { return "Shookwat";
		} else if (id == -45) { return "Wattwo";
		} else if (id == -46) { return "Corry";
		} else if (id == -47) { return "Ssordy";
		} else if (id == -48) { return "Bugik";
		} else if (id == -49) { return "Swordik";
		} else if (id == -50) { return "Ninjakik";
		} else if (id == -51) { return "Karachop";
		} else if (id == -52) { return "Karapunch";
		} else if (id == -53) { return "Karakik";
		} else if (id == -54) { return "Karasword";
		} else if (id == -55) { return "Sludger";
		} else if (id == -56) { return "Gludge";
		} else if (id == -57) { return "Wing";
		} else if (id == -58) { return "Toxing";
		} else if (id == -59) { return "Gelco-G";
		} else if (id == -60) { return "Lizardo-G";
		} else if (id == -61) { return "Jorid";
		} else if (id == -62) { return "Tentarid";
		} else if (id == -63) { return "Hedgehog";
		} else if (id == -64) { return "Bulldozer";
		} else if (id == -65) { return "Daray";
		} else if (id == -66) { return "Spinaquata";
		} else if (id == -67) { return "Toree";
		} else if (id == -68) { return "Turdee";
		} else if (id == -69) { return "Ali";
		} else if (id == -70) { return "Shomp";
		} else if (id == -71) { return "Rhypo";
		} else if (id == -72) { return "Rhynee";
		} else if (id == -73) { return "Rhypolar";
		} else if (id == -74) { return "Ignite";
		} else if (id == -75) { return "Blaze";
		} else if (id == -76) { return "Inferno";
		} else if (id == -77) { return "Flamehead";
		} else if (id == -78) { return "Fireshard";
		} else if (id == -79) { return "Blastflames";
		} else if (id == -80) { return "Heater";
		} else if (id == -81) { return "Froller";
		} else if (id == -82) { return "Cloudorus";
		} else if (id == -83) { return "Stormatus";
		} else if (id == -84) { return "Creeflare";
		} else if (id == -85) { return "Flyflare";
		} else if (id == -86) { return "Tara-Z";
		} else if (id == -87) { return "Tara-X";
		} else if (id == -88) { return "Savahger";
		} else if (id == -89) { return "Show";
		} else if (id == -90) { return "Dark Zombie";
		} else if (id == -91) { return "Diftery";
		} else if (id == -92) { return "Electroball";
		} else if (id == -93) { return "Ghast";
		} else if (id == -94) { return "Flast";
		} else if (id == -95) { return "Magie";
		} else if (id == -96) { return "Cumin";
		} else if (id == -97) { return "Droid";
		} else if (id == -98) { return "Armoid";
		} else if (id == -99) { return "Soldrota";
		} else if (id == -100) { return "Dragee";
		} else if (id == -101) { return "Draga";
		} else if (id == -102) { return "Drageye";
		} else if (id == -103) { return "Soardine-A";
		} else if (id == -104) { return "Soardine-D";
		} else if (id == -105) { return "Soardine-F";
		} else if (id == -106) { return "Wando";
		} else if (id == -107) { return "Sparkdust";
		} else if (id == -108) { return "Splame";
		} else if (id == -109) { return "Tinkie";
		} else if (id == -110) { return "Shawar";
		} else if (id == -111) { return "Shaboom";
		} else if (id == -112) { return "Dogo";
		} else if (id == -113) { return "Doleaf";
		} else if (id == -114) { return "Drame";
		} else if (id == -115) { return "Daqua";
		} else if (id == -116) { return "Doxic";
		} else if (id == -117) { return "Dratt";
		} else if (id == -118) { return "Drock";
		} else if (id == -119) { return "Dokurk";
		} else if (id == -120) { return "Drotal";
		} else if (id == -121) { return "Drunch";
		} else if (id == -122) { return "Draco";
		} else if (id == -123) { return "Drakik";
		} else if (id == -124) { return "Plantro";
		} else if (id == -125) { return "Leafron";
		} else if (id == -126) { return "Planterra";
		} else if (id == -127) { return "Sparky";
		} else if (id == -128) { return "Fireball";
		} else if (id == -129) { return "Meteator";
		} else if (id == -130) { return "Taddy";
		} else if (id == -131) { return "Tarow";
		} else if (id == -132) { return "Tadagater";
		} else if (id == -133) { return "Zaparch";
		} else if (id == -134) { return "Zaflame";
		} else if (id == -135) { return "Holgor";
		} else if (id == -136) { return "Larvangor";
		} else if (id == -137) { return "Fleigor";
		} else if (id == -138) { return "Halgatoria";
		} else if (id == -139) { return "Lavatoria";
		} else if (id == -140) { return "Wingatoria";
		} else if (id == -141) { return "Gelco-F";
		} else if (id == -142) { return "Gelco-W";
		} else if (id == -143) { return "Lizardo-F";
		} else if (id == -144) { return "Lizardo-W";
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
		checkMove();
		Pokemon result = this.checkEvo();
		expMax = this.level * 2;
		stats = this.getStats();
		int nHP = this.getStat(0);
		this.currentHP += nHP - oHP;
		if (this.level == 100) this.exp = 0;
		return result;
		
	}
	
	public void checkMove() {
		Move move = null;
		if (this.level - 1 >= this.movebank.length) return;
		move = this.movebank[this.level - 1];
		if (move == null) return;
		if (this.contains(move)) return;
		for (int i = 0; i < 4; i++) {
			if (this.moveset[i] == null) {
				this.moveset[i] = move;
				System.out.println(this.name + " learned " + move.toString() + "!");
				return;
			}
		}
		
	    int choice = Battle.displayMoveOptions(this, move);
	    if (choice == JOptionPane.CLOSED_OPTION) {
	    	System.out.println(this.name + " did not learn " + move.toString() + ".");
	    	return;
	    }
	    System.out.println(this.name + " has learned " + move.toString() + " and forgot " + this.moveset[choice] + "!");
	    this.moveset[choice] = move;
	}

	private Pokemon checkEvo() {
		Pokemon result = null;
		if (id == -1 && level >= 15) {
			result = new Pokemon(-2, level, this.moveset, this.ivs);
		} else if (id == -2 && level >= 35) {
			result = new Pokemon(-3, level, this.moveset, this.ivs);
		} else if (id == -4 && level >= 16) {
			result = new Pokemon(-5, level, this.moveset, this.ivs);
		} else if (id == -5 && level >= 34) {
			result = new Pokemon(-6, level, this.moveset, this.ivs);
		} else if (id == -7 && level >= 20) {
			result = new Pokemon(-8, level, this.moveset, this.ivs);
		} else if (id == -8 && level >= 31) {
			result = new Pokemon(-9, level, this.moveset, this.ivs);
		} else if (id == -10 && level >= 16) {
			result = new Pokemon(-11, level, this.moveset, this.ivs);
		} else if (id == -12 && level >= 28) {
			result = new Pokemon(-13, level, this.moveset, this.ivs);
		} else if (id == -14 && level >= 16) {
			result = new Pokemon(-15, level, this.moveset, this.ivs);
		} else if (id == -16 && level >= 32 && this.contains(Move.ROLLOUT)) {
			result = new Pokemon(-17, level, this.moveset, this.ivs);
		} else if (id == -18 && level >= 21) {
			result = new Pokemon(-19, level, this.moveset, this.ivs);
		} else if (id == -19 && level >= 36 && this.contains(Move.ROCK_BLAST)) {
			result = new Pokemon(-20, level, this.moveset, this.ivs);
		} else if (id == -21 && level >= 25) {
			result = new Pokemon(-22, level, this.moveset, this.ivs);
		} else if (id == -22 && level >= 40) {
			result = new Pokemon(id - 1-23, level, this.moveset, this.ivs);
		} else if (id == -24 && level >= 21) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -26 && level >= 18) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -27 && level >= 36) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -29 && level >= 18) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -33 && level >= 21) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -38 && level >= 25) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -40 && level >= 21) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -44 && level >= 30) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -46 && level >= 31) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -48 && level >= 15) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -49 && level >= 25) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -51 && level >= 25 && this.contains(Move.MACH_PUNCH)) {
			result = new Pokemon(-52, level, this.moveset, this.ivs);
		} else if (id == -51 && level >= 25 && this.contains(Move.HI_JUMP_KICK)) {
			result = new Pokemon(-53, level, this.moveset, this.ivs);
		} else if (id == -51 && level >= 25 && this.contains(Move.SWORD_SLICE)) {
			result = new Pokemon(-54, level, this.moveset, this.ivs);
		} else if (id == -55 && level >= 25) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -57 && level >= 21) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -59 && level >= 28) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -141 && level >= 28) {
			result = new Pokemon(-143, level, this.moveset, this.ivs);
		} else if (id == -142 && level >= 28) {
			result = new Pokemon(-144, level, this.moveset, this.ivs);
		} else if (id == -61 && level >= 29) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -63 && level >= 25) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -65 && level >= 27) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -67 && level >= 18) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -71 && level >= 30) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -72 && level >= 55) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -74 && level >= 16) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -75 && level >= 36) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -77 && level >= 35) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -78 && level >= 55) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -80 && level >= 28) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -82 && level >= 26) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -84 && level >= 31) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -86 && level >= 36) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -89 && level >= 31) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -93 && level >= 32) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -95 && level >= 30) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -97 && level >= 25) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -98 && level >= 50) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -100 && level >= 35) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -101 && level >= 55) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -105 && level >= 25) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -107 && level >= 30) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -109 && level >= 30) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -110 && level >= 50) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -112 && level >= 25) {
			result = new Pokemon(Battle.dogoEvo(this), level, this.moveset, this.ivs);
		} else if (id == -124 && level >= 16) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -125 && level >= 36) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -127 && level >= 16) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -128 && level >= 36) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -130 && level >= 15) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -131 && level >= 35) {
			result = new Pokemon(id - 1, level, this.moveset, this.ivs);
		} else if (id == -135 && level >= 50) {
			result = new Pokemon(-138, level, this.moveset, this.ivs);
		} else if (id == -136 && level >= 50) {
			result = new Pokemon(-139, level, this.moveset, this.ivs);
		} else if (id == -137 && level >= 55) {
			result = new Pokemon(-140, level, this.moveset, this.ivs);
		}
		if (result != null) {
			boolean shouldEvolve = Battle.displayEvolution(this);
			if (shouldEvolve) {
		        int hpDif = this.getStat(0) - this.currentHP;
		        result.currentHP -= hpDif;
		        result.moveMultiplier = this.moveMultiplier;
		        System.out.println(this.name + " evolved into " + result.name + "!");
		        result.exp = this.exp;
			} else {
				return this;
			}
	    }
		return result;
		
	}

	public int[] getStats() {
		double HPnum = 2 * baseStats[0] + ivs[0] * level;
		stats[0] = (int) (Math.floor(HPnum/100) + level + 10);
		if (id == 131) stats[0] = 1;
		double Atknum = 2 * baseStats[1] + ivs[1] * level;
		stats[1] = (int) (Math.floor(Atknum/100) + 5);
		double Defnum = 2 * baseStats[2] + ivs[2] * level;
		stats[2] = (int) (Math.floor(Defnum/100) + 5);
		double SpAnum = 2 * baseStats[3] + ivs[3] * level;
		stats[3] = (int) (Math.floor(SpAnum/100) + 5);
		double SpDnum = 2 * baseStats[4] + ivs[4] * level;
		stats[4] = (int) (Math.floor(SpDnum/100) + 5);
		double Spenum = 2 * baseStats[5] + ivs[5] * level;
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
		if (this.id == 1) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 2) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 3) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 4) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 5) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 6) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 7) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 8) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 9) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 10) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 11) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 12) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 13) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 14) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 15) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 16) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 17) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 18) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 19) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 20) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 21) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 22) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 23) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 24) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 25) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 26) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 27) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 28) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 29) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 30) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 31) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 32) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 33) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 34) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 35) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 36) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 37) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 38) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 39) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 40) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 41) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 42) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 43) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 44) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 45) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 46) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 47) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 48) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 49) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 50) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 51) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 52) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 53) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 54) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 55) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 56) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 57) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 58) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 59) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 60) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 61) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 62) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 63) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 64) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 65) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 66) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 67) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 68) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 69) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 70) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 71) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 72) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 73) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 74) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 75) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 76) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 77) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 78) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 79) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 80) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 81) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 82) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 83) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 84) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 85) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 86) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 87) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 88) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 89) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 90) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 91) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 92) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 93) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 94) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 95) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 96) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 97) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 98) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 99) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 100) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 101) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 102) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 103) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 104) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 105) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 106) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 107) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 108) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 109) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 110) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 111) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 112) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 113) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 114) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 115) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 116) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 117) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 118) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 119) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 120) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 121) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 122) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 123) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 124) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 125) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 126) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 127) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 128) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 129) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 130) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 131) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 132) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 133) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 134) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 135) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 136) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 137) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 138) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 139) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 140) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 141) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 142) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 143) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 144) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 145) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 146) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 147) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 148) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 149) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 150) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 151) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 152) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 153) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 154) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 155) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 156) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 157) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 158) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 159) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 160) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 161) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 162) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 163) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 164) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 165) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 166) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 167) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 168) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 169) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 170) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 171) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 172) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 173) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 174) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 175) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 176) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 177) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 178) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 179) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 180) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 181) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 182) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 183) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 184) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 185) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 186) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 187) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 188) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 189) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 190) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 191) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 192) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 193) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 194) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 195) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 196) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 197) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 198) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 199) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 200) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 201) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 202) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 203) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 204) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 205) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 206) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 207) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 208) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 209) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 210) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 211) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 212) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 213) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 214) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 215) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 216) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 217) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 218) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 219) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 220) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 221) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 222) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 223) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 224) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 225) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 226) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 227) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 228) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 229) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 230) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 231) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 232) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 233) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 234) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 235) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 236) { this.baseStats = new int[]{35,46,45,67,70,63};
		} else if (this.id == 237) { this.baseStats = new int[]{35,46,45,67,70,63};
		
		} else if (this.id == -1) {
			this.baseStats[0] = 35;
			this.baseStats[1] = 46;
			this.baseStats[2] = 45;
			this.baseStats[3] = 67;
			this.baseStats[4] = 70;
			this.baseStats[5] = 63;
			return this.baseStats;
		} else if (this.id == -2) {
			this.baseStats[0] = 64;
			this.baseStats[1] = 54;
			this.baseStats[2] = 85;
			this.baseStats[3] = 67;
			this.baseStats[4] = 78;
			this.baseStats[5] = 60;
			return this.baseStats;
		} else if (this.id == -3) {
			this.baseStats[0] = 100;
			this.baseStats[1] = 70;
			this.baseStats[2] = 120;
			this.baseStats[3] = 90;
			this.baseStats[4] = 85;
			this.baseStats[5] = 60;
			return this.baseStats;
		} else if (this.id == -4) {
			this.baseStats[0] = 59;
			this.baseStats[1] = 46;
			this.baseStats[2] = 67;
			this.baseStats[3] = 62;
			this.baseStats[4] = 55;
			this.baseStats[5] = 31;
			return this.baseStats;
		} else if (this.id == -5) {
			this.baseStats[0] = 92;
			this.baseStats[1] = 51;
			this.baseStats[2] = 72;
			this.baseStats[3] = 75;
			this.baseStats[4] = 65;
			this.baseStats[5] = 45;
			return this.baseStats;
		} else if (this.id == -6) {
			this.baseStats[0] = 95;
			this.baseStats[1] = 54;
			this.baseStats[2] = 75;
			this.baseStats[3] = 109;
			this.baseStats[4] = 130;
			this.baseStats[5] = 55;
			return this.baseStats;
		} else if (this.id == -7) {
			this.baseStats[0] = 44;
			this.baseStats[1] = 62;
			this.baseStats[2] = 46;
			this.baseStats[3] = 54;
			this.baseStats[4] = 55;
			this.baseStats[5] = 62;
			return this.baseStats;
		} else if (this.id == -8) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 79;
			this.baseStats[2] = 53;
			this.baseStats[3] = 55;
			this.baseStats[4] = 65;
			this.baseStats[5] = 78;
			return this.baseStats;
		} else if (this.id == -9) {
			this.baseStats[0] = 76;
			this.baseStats[1] = 81;
			this.baseStats[2] = 65;
			this.baseStats[3] = 106;
			this.baseStats[4] = 95;
			this.baseStats[5] = 108;
			return this.baseStats;
		} else if (this.id == -10) {
			this.baseStats[0] = 36;
			this.baseStats[1] = 63;
			this.baseStats[2] = 47;
			this.baseStats[3] = 24;
			this.baseStats[4] = 62;
			this.baseStats[5] = 58;
			return this.baseStats;
		} else if (this.id == -11) {
			this.baseStats[0] = 50;
			this.baseStats[1] = 105;
			this.baseStats[2] = 50;
			this.baseStats[3] = 54;
			this.baseStats[4] = 56;
			this.baseStats[5] = 105;
			return this.baseStats;
		} else if (this.id == -12) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 105;
			this.baseStats[2] = 100;
			this.baseStats[3] = 50;
			this.baseStats[4] = 30;
			this.baseStats[5] = 55;
			return this.baseStats;
		} else if (this.id == -13) {
			this.baseStats[0] = 120;
			this.baseStats[1] = 125;
			this.baseStats[2] = 120;
			this.baseStats[3] = 60;
			this.baseStats[4] = 35;
			this.baseStats[5] = 55;
			return this.baseStats;
		} else if (this.id == -14) {
			this.baseStats[0] = 50;
			this.baseStats[1] = 63;
			this.baseStats[2] = 41;
			this.baseStats[3] = 22;
			this.baseStats[4] = 63;
			this.baseStats[5] = 66;
			return this.baseStats;
		} else if (this.id == -15) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 89;
			this.baseStats[2] = 55;
			this.baseStats[3] = 25;
			this.baseStats[4] = 76;
			this.baseStats[5] = 95;
			return this.baseStats;
		} else if (this.id == -16) {
			this.baseStats[0] = 99;
			this.baseStats[1] = 61;
			this.baseStats[2] = 51;
			this.baseStats[3] = 40;
			this.baseStats[4] = 79;
			this.baseStats[5] = 20;
			return this.baseStats;
		} else if (this.id == -17) {
			this.baseStats[0] = 170;
			this.baseStats[1] = 64;
			this.baseStats[2] = 55;
			this.baseStats[3] = 43;
			this.baseStats[4] = 103;
			this.baseStats[5] = 55;
			return this.baseStats;
		} else if (this.id == -18) {
			this.baseStats[0] = 68;
			this.baseStats[1] = 72;
			this.baseStats[2] = 122;
			this.baseStats[3] = 20;
			this.baseStats[4] = 25;
			this.baseStats[5] = 18;
			return this.baseStats;
		} else if (this.id == -19) {
			this.baseStats[0] = 87;
			this.baseStats[1] = 95;
			this.baseStats[2] = 143;
			this.baseStats[3] = 25;
			this.baseStats[4] = 30;
			this.baseStats[5] = 45;
			return this.baseStats;
		} else if (this.id == -20) {
			this.baseStats[0] = 90;
			this.baseStats[1] = 100;
			this.baseStats[2] = 143;
			this.baseStats[3] = 100;
			this.baseStats[4] = 42;
			this.baseStats[5] = 50;
			return this.baseStats;
		} else if (this.id == -21) {
			this.baseStats[0] = 45;
			this.baseStats[1] = 40;
			this.baseStats[2] = 105;
			this.baseStats[3] = 40;
			this.baseStats[4] = 75;
			this.baseStats[5] = 40;
			return this.baseStats;
		} else if (this.id == -22) {
			this.baseStats[0] = 81;
			this.baseStats[1] = 66;
			this.baseStats[2] = 105;
			this.baseStats[3] = 40;
			this.baseStats[4] = 75;
			this.baseStats[5] = 45;
			return this.baseStats;
		} else if (this.id == -23) {
			this.baseStats[0] = 100;
			this.baseStats[1] = 110;
			this.baseStats[2] = 105;
			this.baseStats[3] = 44;
			this.baseStats[4] = 78;
			this.baseStats[5] = 93;
			return this.baseStats;
		} else if (this.id == -24) {
			this.baseStats[0] = 35;
			this.baseStats[1] = 103;
			this.baseStats[2] = 62;
			this.baseStats[3] = 25;
			this.baseStats[4] = 30;
			this.baseStats[5] = 95;
			return this.baseStats;
		} else if (this.id == -25) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 80;
			this.baseStats[2] = 65;
			this.baseStats[3] = 80;
			this.baseStats[4] = 70;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == -26) {
			this.baseStats[0] = 68;
			this.baseStats[1] = 35;
			this.baseStats[2] = 66;
			this.baseStats[3] = 45;
			this.baseStats[4] = 60;
			this.baseStats[5] = 44;
			return this.baseStats;
		} else if (this.id == -27) {
			this.baseStats[0] = 70;
			this.baseStats[1] = 40;
			this.baseStats[2] = 67;
			this.baseStats[3] = 73;
			this.baseStats[4] = 70;
			this.baseStats[5] = 80;
			return this.baseStats;
		} else if (this.id == -28) {
			this.baseStats[0] = 72;
			this.baseStats[1] = 91;
			this.baseStats[2] = 53;
			this.baseStats[3] = 80;
			this.baseStats[4] = 75;
			this.baseStats[5] = 124;
			return this.baseStats;
		} else if (this.id == -29) {
			this.baseStats[0] = 30;
			this.baseStats[1] = 36;
			this.baseStats[2] = 64;
			this.baseStats[3] = 40;
			this.baseStats[4] = 70;
			this.baseStats[5] = 72;
			return this.baseStats;
		} else if (this.id == -30) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 65;
			this.baseStats[2] = 125;
			this.baseStats[3] = 45;
			this.baseStats[4] = 68;
			this.baseStats[5] = 74;
			return this.baseStats;
		} else if (this.id == -31) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 96;
			this.baseStats[2] = 80;
			this.baseStats[3] = 65;
			this.baseStats[4] = 70;
			this.baseStats[5] = 74;
			return this.baseStats;
		} else if (this.id == -32) {
			this.baseStats[0] = 90;
			this.baseStats[1] = 90;
			this.baseStats[2] = 65;
			this.baseStats[3] = 35;
			this.baseStats[4] = 100;
			this.baseStats[5] = 60;
			return this.baseStats;
		} else if (this.id == -33) {
			this.baseStats[0] = 40;
			this.baseStats[1] = 64;
			this.baseStats[2] = 78;
			this.baseStats[3] = 35;
			this.baseStats[4] = 56;
			this.baseStats[5] = 37;
			return this.baseStats;
		} else if (this.id == -34) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 80;
			this.baseStats[2] = 80;
			this.baseStats[3] = 75;
			this.baseStats[4] = 65;
			this.baseStats[5] = 60;
			return this.baseStats;
		} else if (this.id == -35) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 87;
			this.baseStats[2] = 71;
			this.baseStats[3] = 63;
			this.baseStats[4] = 60;
			this.baseStats[5] = 64;
			return this.baseStats;
		} else if (this.id == -36) {
			this.baseStats[0] = 40;
			this.baseStats[1] = 73;
			this.baseStats[2] = 40;
			this.baseStats[3] = 105;
			this.baseStats[4] = 45;
			this.baseStats[5] = 97;
			return this.baseStats;
		} else if (this.id == -37) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 42;
			this.baseStats[2] = 58;
			this.baseStats[3] = 105;
			this.baseStats[4] = 97;
			this.baseStats[5] = 73;
			return this.baseStats;
		} else if (this.id == -38) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 75;
			this.baseStats[2] = 80;
			this.baseStats[3] = 60;
			this.baseStats[4] = 65;
			this.baseStats[5] = 65;
			return this.baseStats;
		} else if (this.id == -39) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 70;
			this.baseStats[2] = 120;
			this.baseStats[3] = 60;
			this.baseStats[4] = 120;
			this.baseStats[5] = 65;
			return this.baseStats;
		} else if (this.id == -40) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 80;
			this.baseStats[2] = 55;
			this.baseStats[3] = 65;
			this.baseStats[4] = 60;
			this.baseStats[5] = 64;
			return this.baseStats;
		} else if (this.id == -41) {
			this.baseStats[0] = 125;
			this.baseStats[1] = 75;
			this.baseStats[2] = 55;
			this.baseStats[3] = 120;
			this.baseStats[4] = 60;
			this.baseStats[5] = 64;
			return this.baseStats;
		} else if (this.id == -42) {
			this.baseStats[0] = 63;
			this.baseStats[1] = 32;
			this.baseStats[2] = 44;
			this.baseStats[3] = 118;
			this.baseStats[4] = 89;
			this.baseStats[5] = 90;
			return this.baseStats;
		} else if (this.id == -43) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 100;
			this.baseStats[2] = 60;
			this.baseStats[3] = 105;
			this.baseStats[4] = 60;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == -44) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 62;
			this.baseStats[2] = 46;
			this.baseStats[3] = 73;
			this.baseStats[4] = 75;
			this.baseStats[5] = 49;
			return this.baseStats;
		} else if (this.id == -45) {
			this.baseStats[0] = 83;
			this.baseStats[1] = 65;
			this.baseStats[2] = 102;
			this.baseStats[3] = 115;
			this.baseStats[4] = 90;
			this.baseStats[5] = 50;
			return this.baseStats;
		} else if (this.id == -46) {
			this.baseStats[0] = 35;
			this.baseStats[1] = 65;
			this.baseStats[2] = 45;
			this.baseStats[3] = 75;
			this.baseStats[4] = 47;
			this.baseStats[5] = 63;
			return this.baseStats;
		} else if (this.id == -47) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 59;
			this.baseStats[2] = 69;
			this.baseStats[3] = 135;
			this.baseStats[4] = 71;
			this.baseStats[5] = 93;
			return this.baseStats;
		} else if (this.id == -48) {
			this.baseStats[0] = 40;
			this.baseStats[1] = 60;
			this.baseStats[2] = 65;
			this.baseStats[3] = 30;
			this.baseStats[4] = 50;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == -49) {
			this.baseStats[0] = 53;
			this.baseStats[1] = 95;
			this.baseStats[2] = 85;
			this.baseStats[3] = 30;
			this.baseStats[4] = 53;
			this.baseStats[5] = 85;
			return this.baseStats;
		} else if (this.id == -50) {
			this.baseStats[0] = 68;
			this.baseStats[1] = 120;
			this.baseStats[2] = 100;
			this.baseStats[3] = 72;
			this.baseStats[4] = 80;
			this.baseStats[5] = 105;
			return this.baseStats;
		} else if (this.id == -51) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 60;
			this.baseStats[2] = 45;
			this.baseStats[3] = 20;
			this.baseStats[4] = 75;
			this.baseStats[5] = 60;
			return this.baseStats;
		} else if (this.id == -52) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 105;
			this.baseStats[2] = 70;
			this.baseStats[3] = 20;
			this.baseStats[4] = 115;
			this.baseStats[5] = 65;
			return this.baseStats;
		} else if (this.id == -53) {
			this.baseStats[0] = 68;
			this.baseStats[1] = 92;
			this.baseStats[2] = 68;
			this.baseStats[3] = 20;
			this.baseStats[4] = 84;
			this.baseStats[5] = 118;
			return this.baseStats;
		} else if (this.id == -54) {
			this.baseStats[0] = 70;
			this.baseStats[1] = 125;
			this.baseStats[2] = 85;
			this.baseStats[3] = 20;
			this.baseStats[4] = 85;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == -55) {
			this.baseStats[0] = 95;
			this.baseStats[1] = 54;
			this.baseStats[2] = 77;
			this.baseStats[3] = 60;
			this.baseStats[4] = 72;
			this.baseStats[5] = 32;
			return this.baseStats;
		} else if (this.id == -56) {
			this.baseStats[0] = 100;
			this.baseStats[1] = 90;
			this.baseStats[2] = 77;
			this.baseStats[3] = 95;
			this.baseStats[4] = 72;
			this.baseStats[5] = 31;
			return this.baseStats;
		} else if (this.id == -57) {
			this.baseStats[0] = 40;
			this.baseStats[1] = 75;
			this.baseStats[2] = 40;
			this.baseStats[3] = 30;
			this.baseStats[4] = 40;
			this.baseStats[5] = 93;
			return this.baseStats;
		} else if (this.id == -58) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 102;
			this.baseStats[2] = 65;
			this.baseStats[3] = 35;
			this.baseStats[4] = 50;
			this.baseStats[5] = 107;
			return this.baseStats;
		} else if (this.id == -59) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 60;
			this.baseStats[2] = 40;
			this.baseStats[3] = 60;
			this.baseStats[4] = 55;
			this.baseStats[5] = 80;
			return this.baseStats;
		} else if (this.id == -60) {
			this.baseStats[0] = 105;
			this.baseStats[1] = 88;
			this.baseStats[2] = 60;
			this.baseStats[3] = 112;
			this.baseStats[4] = 65;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == -61) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 32;
			this.baseStats[2] = 75;
			this.baseStats[3] = 55;
			this.baseStats[4] = 100;
			this.baseStats[5] = 73;
			return this.baseStats;
		} else if (this.id == -62) {
			this.baseStats[0] = 71;
			this.baseStats[1] = 65;
			this.baseStats[2] = 110;
			this.baseStats[3] = 60;
			this.baseStats[4] = 110;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == -63) {
			this.baseStats[0] = 30;
			this.baseStats[1] = 30;
			this.baseStats[2] = 88;
			this.baseStats[3] = 70;
			this.baseStats[4] = 55;
			this.baseStats[5] = 45;
			return this.baseStats;
		} else if (this.id == -64) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 113;
			this.baseStats[2] = 58;
			this.baseStats[3] = 83;
			this.baseStats[4] = 25;
			this.baseStats[5] = 127;
			return this.baseStats;
		} else if (this.id == -65) {
			this.baseStats[0] = 100;
			this.baseStats[1] = 28;
			this.baseStats[2] = 45;
			this.baseStats[3] = 72;
			this.baseStats[4] = 110;
			this.baseStats[5] = 45;
			return this.baseStats;
		} else if (this.id == -66) {
			this.baseStats[0] = 100;
			this.baseStats[1] = 100;
			this.baseStats[2] = 76;
			this.baseStats[3] = 34;
			this.baseStats[4] = 94;
			this.baseStats[5] = 91;
			return this.baseStats;
		} else if (this.id == -67) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 40;
			this.baseStats[2] = 90;
			this.baseStats[3] = 40;
			this.baseStats[4] = 66;
			this.baseStats[5] = 34;
			return this.baseStats;
		} else if (this.id == -68) {
			this.baseStats[0] = 66;
			this.baseStats[1] = 76;
			this.baseStats[2] = 115;
			this.baseStats[3] = 54;
			this.baseStats[4] = 79;
			this.baseStats[5] = 40;
			return this.baseStats;
		} else if (this.id == -69) {
			this.baseStats[0] = 105;
			this.baseStats[1] = 125;
			this.baseStats[2] = 100;
			this.baseStats[3] = 45;
			this.baseStats[4] = 55;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == -70) {
			this.baseStats[0] = 81;
			this.baseStats[1] = 103;
			this.baseStats[2] = 63;
			this.baseStats[3] = 113;
			this.baseStats[4] = 90;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == -71) {
			this.baseStats[0] = 51;
			this.baseStats[1] = 55;
			this.baseStats[2] = 69;
			this.baseStats[3] = 40;
			this.baseStats[4] = 56;
			this.baseStats[5] = 35;
			return this.baseStats;
		} else if (this.id == -72) {
			this.baseStats[0] = 57;
			this.baseStats[1] = 73;
			this.baseStats[2] = 108;
			this.baseStats[3] = 40;
			this.baseStats[4] = 95;
			this.baseStats[5] = 39;
			return this.baseStats;
		} else if (this.id == -73) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 110;
			this.baseStats[2] = 110;
			this.baseStats[3] = 75;
			this.baseStats[4] = 400;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == -74) {
			this.baseStats[0] = 30;
			this.baseStats[1] = 20;
			this.baseStats[2] = 40;
			this.baseStats[3] = 103;
			this.baseStats[4] = 30;
			this.baseStats[5] = 83;
			return this.baseStats;
		} else if (this.id == -75) {
			this.baseStats[0] = 43;
			this.baseStats[1] = 88;
			this.baseStats[2] = 50;
			this.baseStats[3] = 74;
			this.baseStats[4] = 52;
			this.baseStats[5] = 93;
			return this.baseStats;
		} else if (this.id == -76) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 115;
			this.baseStats[2] = 55;
			this.baseStats[3] = 106;
			this.baseStats[4] = 75;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == -77) {
			this.baseStats[0] = 70;
			this.baseStats[1] = 53;
			this.baseStats[2] = 70;
			this.baseStats[3] = 55;
			this.baseStats[4] = 60;
			this.baseStats[5] = 52;
			return this.baseStats;
		} else if (this.id == -78) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 55;
			this.baseStats[2] = 75;
			this.baseStats[3] = 105;
			this.baseStats[4] = 75;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == -79) {
			this.baseStats[0] = 95;
			this.baseStats[1] = 75;
			this.baseStats[2] = 87;
			this.baseStats[3] = 140;
			this.baseStats[4] = 80;
			this.baseStats[5] = 83;
			return this.baseStats;
		} else if (this.id == -80) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 40;
			this.baseStats[2] = 105;
			this.baseStats[3] = 100;
			this.baseStats[4] = 65;
			this.baseStats[5] = 42;
			return this.baseStats;
		} else if (this.id == -81) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 90;
			this.baseStats[2] = 95;
			this.baseStats[3] = 90;
			this.baseStats[4] = 65;
			this.baseStats[5] = 82;
			return this.baseStats;
		} else if (this.id == -82) {
			this.baseStats[0] = 95;
			this.baseStats[1] = 40;
			this.baseStats[2] = 55;
			this.baseStats[3] = 58;
			this.baseStats[4] = 95;
			this.baseStats[5] = 62;
			return this.baseStats;
		} else if (this.id == -83) {
			this.baseStats[0] = 95;
			this.baseStats[1] = 60;
			this.baseStats[2] = 63;
			this.baseStats[3] = 105;
			this.baseStats[4] = 95;
			this.baseStats[5] = 67;
			return this.baseStats;
		} else if (this.id == -84) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 55;
			this.baseStats[2] = 60;
			this.baseStats[3] = 75;
			this.baseStats[4] = 80;
			this.baseStats[5] = 66;
			return this.baseStats;
		} else if (this.id == -85) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 59;
			this.baseStats[2] = 63;
			this.baseStats[3] = 100;
			this.baseStats[4] = 96;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == -86) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 69;
			this.baseStats[2] = 80;
			this.baseStats[3] = 30;
			this.baseStats[4] = 70;
			this.baseStats[5] = 67;
			return this.baseStats;
		} else if (this.id == -87) {
			this.baseStats[0] = 95;
			this.baseStats[1] = 110;
			this.baseStats[2] = 125;
			this.baseStats[3] = 70;
			this.baseStats[4] = 75;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == -88) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 165;
			this.baseStats[2] = 105;
			this.baseStats[3] = 15;
			this.baseStats[4] = 45;
			this.baseStats[5] = 115;
			return this.baseStats;
		} else if (this.id == -89) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 65;
			this.baseStats[2] = 55;
			this.baseStats[3] = 60;
			this.baseStats[4] = 60;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == -90) {
			this.baseStats[0] = 105;
			this.baseStats[1] = 110;
			this.baseStats[2] = 65;
			this.baseStats[3] = 60;
			this.baseStats[4] = 95;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == -91) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 110;
			this.baseStats[2] = 75;
			this.baseStats[3] = 110;
			this.baseStats[4] = 100;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == -92) {
			this.baseStats[0] = 40;
			this.baseStats[1] = 20;
			this.baseStats[2] = 30;
			this.baseStats[3] = 120;
			this.baseStats[4] = 105;
			this.baseStats[5] = 110;
			return this.baseStats;
		} else if (this.id == -93) {
			this.baseStats[0] = 120;
			this.baseStats[1] = 35;
			this.baseStats[2] = 40;
			this.baseStats[3] = 100;
			this.baseStats[4] = 65;
			this.baseStats[5] = 65;
			return this.baseStats;
		} else if (this.id == -94) {
			this.baseStats[0] = 120;
			this.baseStats[1] = 60;
			this.baseStats[2] = 60;
			this.baseStats[3] = 120;
			this.baseStats[4] = 80;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == -95) {
			this.baseStats[0] = 30;
			this.baseStats[1] = 60;
			this.baseStats[2] = 73;
			this.baseStats[3] = 30;
			this.baseStats[4] = 65;
			this.baseStats[5] = 58;
			return this.baseStats;
		} else if (this.id == -96) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 80;
			this.baseStats[2] = 95;
			this.baseStats[3] = 65;
			this.baseStats[4] = 95;
			this.baseStats[5] = 95;
			return this.baseStats;
		} else if (this.id == -97) {
			this.baseStats[0] = 50;
			this.baseStats[1] = 70;
			this.baseStats[2] = 55;
			this.baseStats[3] = 50;
			this.baseStats[4] = 50;
			this.baseStats[5] = 55;
			return this.baseStats;
		} else if (this.id == -98) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 75;
			this.baseStats[2] = 105;
			this.baseStats[3] = 50;
			this.baseStats[4] = 75;
			this.baseStats[5] = 60;
			return this.baseStats;
		} else if (this.id == -99) {
			this.baseStats[0] = 70;
			this.baseStats[1] = 115;
			this.baseStats[2] = 115;
			this.baseStats[3] = 90;
			this.baseStats[4] = 75;
			this.baseStats[5] = 65;
			return this.baseStats;
		} else if (this.id == -100) {
			this.baseStats[0] = 80;
			this.baseStats[1] = 40;
			this.baseStats[2] = 57;
			this.baseStats[3] = 63;
			this.baseStats[4] = 59;
			this.baseStats[5] = 51;
			return this.baseStats;
		} else if (this.id == -101) {
			this.baseStats[0] = 85;
			this.baseStats[1] = 75;
			this.baseStats[2] = 80;
			this.baseStats[3] = 90;
			this.baseStats[4] = 75;
			this.baseStats[5] = 60;
			return this.baseStats;
		} else if (this.id == -102) {
			this.baseStats[0] = 90;
			this.baseStats[1] = 85;
			this.baseStats[2] = 85;
			this.baseStats[3] = 100;
			this.baseStats[4] = 150;
			this.baseStats[5] = 90;
			return this.baseStats;
		} else if (this.id == -103) {
			this.baseStats[0] = 95;
			this.baseStats[1] = 125;
			this.baseStats[2] = 105;
			this.baseStats[3] = 65;
			this.baseStats[4] = 95;
			this.baseStats[5] = 85;
			return this.baseStats;
		} else if (this.id == -104) {
			this.baseStats[0] = 95;
			this.baseStats[1] = 85;
			this.baseStats[2] = 65;
			this.baseStats[3] = 105;
			this.baseStats[4] = 95;
			this.baseStats[5] = 125;
			return this.baseStats;
		} else if (this.id == -105) {
			this.baseStats[0] = 95;
			this.baseStats[1] = 65;
			this.baseStats[2] = 85;
			this.baseStats[3] = 125;
			this.baseStats[4] = 95;
			this.baseStats[5] = 105;
			return this.baseStats;
		} else if (this.id == -106) {
			this.baseStats[0] = 50;
			this.baseStats[1] = 40;
			this.baseStats[2] = 50;
			this.baseStats[3] = 120;
			this.baseStats[4] = 90;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == -107) {
			this.baseStats[0] = 40;
			this.baseStats[1] = 45;
			this.baseStats[2] = 40;
			this.baseStats[3] = 75;
			this.baseStats[4] = 65;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == -108) {
			this.baseStats[0] = 85;
			this.baseStats[1] = 60;
			this.baseStats[2] = 65;
			this.baseStats[3] = 100;
			this.baseStats[4] = 110;
			this.baseStats[5] = 85;
			return this.baseStats;
		} else if (this.id == -109) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 76;
			this.baseStats[2] = 55;
			this.baseStats[3] = 54;
			this.baseStats[4] = 90;
			this.baseStats[5] = 60;
			return this.baseStats;
		} else if (this.id == -110) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 80;
			this.baseStats[2] = 75;
			this.baseStats[3] = 60;
			this.baseStats[4] = 90;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == -111) {
			this.baseStats[0] = 100;
			this.baseStats[1] = 100;
			this.baseStats[2] = 100;
			this.baseStats[3] = 100;
			this.baseStats[4] = 100;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == -112) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 75;
			this.baseStats[2] = 70;
			this.baseStats[3] = 55;
			this.baseStats[4] = 65;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == -113) {
			this.baseStats[0] = 120;
			this.baseStats[1] = 75;
			this.baseStats[2] = 100;
			this.baseStats[3] = 85;
			this.baseStats[4] = 65;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == -114) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 65;
			this.baseStats[2] = 85;
			this.baseStats[3] = 120;
			this.baseStats[4] = 100;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == -115) {
			this.baseStats[0] = 85;
			this.baseStats[1] = 120;
			this.baseStats[2] = 75;
			this.baseStats[3] = 100;
			this.baseStats[4] = 75;
			this.baseStats[5] = 65;
			return this.baseStats;
		} else if (this.id == -116) {
			this.baseStats[0] = 100;
			this.baseStats[1] = 120;
			this.baseStats[2] = 85;
			this.baseStats[3] = 75;
			this.baseStats[4] = 65;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == -117) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 75;
			this.baseStats[2] = 65;
			this.baseStats[3] = 85;
			this.baseStats[4] = 120;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == -118) {
			this.baseStats[0] = 85;
			this.baseStats[1] = 100;
			this.baseStats[2] = 120;
			this.baseStats[3] = 75;
			this.baseStats[4] = 75;
			this.baseStats[5] = 65;
			return this.baseStats;
		} else if (this.id == -119) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 65;
			this.baseStats[2] = 85;
			this.baseStats[3] = 100;
			this.baseStats[4] = 120;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == -120) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 75;
			this.baseStats[2] = 120;
			this.baseStats[3] = 85;
			this.baseStats[4] = 100;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == -121) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 120;
			this.baseStats[2] = 85;
			this.baseStats[3] = 65;
			this.baseStats[4] = 100;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == -122) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 100;
			this.baseStats[2] = 75;
			this.baseStats[3] = 120;
			this.baseStats[4] = 65;
			this.baseStats[5] = 85;
			return this.baseStats;
		} else if (this.id == -123) {
			this.baseStats[0] = 65;
			this.baseStats[1] = 75;
			this.baseStats[2] = 75;
			this.baseStats[3] = 120;
			this.baseStats[4] = 100;
			this.baseStats[5] = 85;
			return this.baseStats;
		} else if (this.id == -124) {
			this.baseStats[0] = 57;
			this.baseStats[1] = 51;
			this.baseStats[2] = 50;
			this.baseStats[3] = 50;
			this.baseStats[4] = 52;
			this.baseStats[5] = 50;
			return this.baseStats;
		} else if (this.id == -125) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 79;
			this.baseStats[2] = 71;
			this.baseStats[3] = 55;
			this.baseStats[4] = 75;
			this.baseStats[5] = 65;
			return this.baseStats;
		} else if (this.id == -126) {
			this.baseStats[0] = 90;
			this.baseStats[1] = 95;
			this.baseStats[2] = 86;
			this.baseStats[3] = 105;
			this.baseStats[4] = 80;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == -127) {
			this.baseStats[0] = 52;
			this.baseStats[1] = 53;
			this.baseStats[2] = 52;
			this.baseStats[3] = 58;
			this.baseStats[4] = 54;
			this.baseStats[5] = 62;
			return this.baseStats;
		} else if (this.id == -128) {
			this.baseStats[0] = 54;
			this.baseStats[1] = 85;
			this.baseStats[2] = 63;
			this.baseStats[3] = 63;
			this.baseStats[4] = 69;
			this.baseStats[5] = 76;
			return this.baseStats;
		} else if (this.id == -129) {
			this.baseStats[0] = 58;
			this.baseStats[1] = 90;
			this.baseStats[2] = 95;
			this.baseStats[3] = 100;
			this.baseStats[4] = 95;
			this.baseStats[5] = 77;
			return this.baseStats;
		} else if (this.id == -130) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 67;
			this.baseStats[2] = 65;
			this.baseStats[3] = 51;
			this.baseStats[4] = 40;
			this.baseStats[5] = 52;
			return this.baseStats;
		} else if (this.id == -131) {
			this.baseStats[0] = 75;
			this.baseStats[1] = 99;
			this.baseStats[2] = 86;
			this.baseStats[3] = 56;
			this.baseStats[4] = 45;
			this.baseStats[5] = 61;
			return this.baseStats;
		} else if (this.id == -132) {
			this.baseStats[0] = 95;
			this.baseStats[1] = 80;
			this.baseStats[2] = 76;
			this.baseStats[3] = 128;
			this.baseStats[4] = 60;
			this.baseStats[5] = 75;
			return this.baseStats;
		} else if (this.id == -133) {
			this.baseStats[0] = 100;
			this.baseStats[1] = 115;
			this.baseStats[2] = 85;
			this.baseStats[3] = 120;
			this.baseStats[4] = 105;
			this.baseStats[5] = 125;
			return this.baseStats;
		} else if (this.id == -134) {
			this.baseStats[0] = 110;
			this.baseStats[1] = 85;
			this.baseStats[2] = 120;
			this.baseStats[3] = 155;
			this.baseStats[4] = 80;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == -135) {
			this.baseStats[0] = 70;
			this.baseStats[1] = 50;
			this.baseStats[2] = 90;
			this.baseStats[3] = 100;
			this.baseStats[4] = 90;
			this.baseStats[5] = 52;
			return this.baseStats;
		} else if (this.id == -136) {
			this.baseStats[0] = 50;
			this.baseStats[1] = 50;
			this.baseStats[2] = 100;
			this.baseStats[3] = 80;
			this.baseStats[4] = 100;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == -137) {
			this.baseStats[0] = 60;
			this.baseStats[1] = 55;
			this.baseStats[2] = 90;
			this.baseStats[3] = 55;
			this.baseStats[4] = 80;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == -138) {
			this.baseStats[0] = 90;
			this.baseStats[1] = 55;
			this.baseStats[2] = 80;
			this.baseStats[3] = 195;
			this.baseStats[4] = 100;
			this.baseStats[5] = 90;
			return this.baseStats;
		} else if (this.id == -139) {
			this.baseStats[0] = 100;
			this.baseStats[1] = 70;
			this.baseStats[2] = 125;
			this.baseStats[3] = 120;
			this.baseStats[4] = 100;
			this.baseStats[5] = 95;
			return this.baseStats;
		} else if (this.id == -140) {
			this.baseStats[0] = 130;
			this.baseStats[1] = 90;
			this.baseStats[2] = 110;
			this.baseStats[3] = 80;
			this.baseStats[4] = 110;
			this.baseStats[5] = 100;
			return this.baseStats;
		} else if (this.id == -141) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 60;
			this.baseStats[2] = 40;
			this.baseStats[3] = 60;
			this.baseStats[4] = 55;
			this.baseStats[5] = 80;
			return this.baseStats;
		} else if (this.id == -143) {
			this.baseStats[0] = 105;
			this.baseStats[1] = 88;
			this.baseStats[2] = 60;
			this.baseStats[3] = 112;
			this.baseStats[4] = 65;
			this.baseStats[5] = 70;
			return this.baseStats;
		} else if (this.id == -142) {
			this.baseStats[0] = 55;
			this.baseStats[1] = 60;
			this.baseStats[2] = 40;
			this.baseStats[3] = 60;
			this.baseStats[4] = 55;
			this.baseStats[5] = 80;
			return this.baseStats;
		} else if (this.id == -144) {
			this.baseStats[0] = 105;
			this.baseStats[1] = 88;
			this.baseStats[2] = 60;
			this.baseStats[3] = 112;
			this.baseStats[4] = 65;
			this.baseStats[5] = 70;
			return this.baseStats;
		}
		return this.baseStats;
	}

	
	public void move(Pokemon foe, Move move, Player player) {
		if (this.fainted || foe.fainted) return;

		double attackStat;
		double defenseStat;
		int damage = 0;
		int bp = move.basePower;
		if (!this.vStatuses.contains(Status.CHARGING) && !this.vStatuses.contains(Status.LOCKED) && move != Move.MAGIC_REFLECT && move != Move.TAKE_OVER && move != Move.DESTINY_BOND) this.lastMoveUsed = move;
		if (move == Move.FAILED_SUCKER) this.lastMoveUsed = Move.SUCKER_PUNCH;
		
		if (this.vStatuses.contains(Status.CONFUSED)) {
			if (this.confusionCounter == 0) {
				this.vStatuses.remove(Status.CONFUSED);
		        System.out.print("\n" + this.name + " snapped out of confusion!");
			} else {
				System.out.print("\n" + this.name + " is confused!");
				if (Math.random() < 1.0/3.0) {
			        // user hits themselves
					System.out.println("\n" + this.name + " hit itself in confusion!");
					attackStat = this.getStat(1);
					defenseStat = this.getStat(2);
					attackStat *= this.asModifier(0);
					defenseStat *= this.asModifier(1);
					damage = calc(attackStat, defenseStat, 40, this.level);
					this.currentHP -= damage;
					if (this.currentHP <= 0) {
						this.faint(true, player);
						foe.awardxp((int) Math.ceil(this.level * trainer), player);
					}
					confusionCounter--;
					this.impressive = false;
					this.vStatuses.remove(Status.LOCKED);
					this.vStatuses.remove(Status.CHARGING);
					return;
				} else {
					confusionCounter--;
				}
			}
		}
		if (this.status == Status.PARALYZED && Math.random() < 0.25) {
			System.out.println("\n" + this.name + " is fully paralyzed!");
			this.moveMultiplier = 0;
			this.impressive = false;
			this.vStatuses.remove(Status.LOCKED);
			this.vStatuses.remove(Status.CHARGING);
			return;
		}
		
		if (this.status == Status.ASLEEP) {
			if (this.sleepCounter > 0) {
				System.out.println("\n" + this.name + " is fast asleep.");
				this.sleepCounter--;
				this.impressive = false;
				this.vStatuses.remove(Status.LOCKED);
				this.vStatuses.remove(Status.CHARGING);
				return;
			} else {
				System.out.println("\n" + this.name + " woke up!");
				this.status = Status.HEALTHY;
			}
		}
		
		if (this.vStatuses.contains(Status.FLINCHED)) {
			System.out.println("\n" + this.name + " flinched!");
			this.vStatuses.remove(Status.FLINCHED);
			this.impressive = false;
			this.vStatuses.remove(Status.LOCKED);
			this.vStatuses.remove(Status.CHARGING);
			return;
		}
		if (this.vStatuses.contains(Status.RECHARGE)) {
			System.out.println("\n" + this.name + " must recharge!");
			this.vStatuses.remove(Status.RECHARGE);
			this.impressive = false;
			this.vStatuses.remove(Status.LOCKED);
			this.vStatuses.remove(Status.CHARGING);
			return;
		}
		if (move == Move.SKULL_BASH || move == Move.SKY_ATTACK || move == Move.SOLAR_BEAM || this.vStatuses.contains(Status.CHARGING)) {
			if (!this.vStatuses.contains(Status.CHARGING)) {
				System.out.println("\n" + this.name + " used " + move + "!");
				System.out.println(this.name + " started charging up!");
				this.vStatuses.add(Status.CHARGING);
				if (move == Move.SKULL_BASH) stat(this, 1, 1);
				this.impressive = false;
				return;
			} else {
				move = this.lastMoveUsed;
				this.vStatuses.remove(Status.CHARGING);
			}
		}
		if (this.vStatuses.contains(Status.LOCKED) && this.lastMoveUsed == Move.OUTRAGE) move = Move.OUTRAGE;
		if (this.vStatuses.contains(Status.LOCKED) && this.lastMoveUsed == Move.ROLLOUT) move = Move.ROLLOUT;
		if (move == Move.OUTRAGE) {
			if (!this.vStatuses.contains(Status.LOCKED)) {
				this.vStatuses.add(Status.LOCKED);
				this.outCount = (int)(Math.random()*2) + 2;
			}
			this.outCount--;
		}
		if (move == Move.ROLLOUT) {
			if (!this.vStatuses.contains(Status.LOCKED)) {
				this.vStatuses.add(Status.LOCKED);
				this.rollCount = 0;
			}
			this.rollCount++;
		}
		if (move == Move.MIRROR_MOVE) {
			System.out.print("\n" + this.name + " used Mirror Move!");
			move = foe.lastMoveUsed;
			if (move == null) {
				System.out.println("\nBut it failed!");
				this.impressive = false;
				return;
			}
		}
		if (foe.vStatuses.contains(Status.REFLECT) && move != Move.BRICK_BREAK) {
			this.move(this, move, player);
			System.out.println(move + " was reflected on itself!");
			foe.vStatuses.remove(Status.REFLECT);
			return;
		}
		if (this.vStatuses.contains(Status.POSESSED)) {
			this.vStatuses.remove(Status.POSESSED);
			this.move(this, move, player);
			System.out.println(move + " was used on itself!");
			return;
		}
		if (move == Move.FAILED_SUCKER) {
			System.out.println("\n" + this.name + " used Sucker Punch!");
			System.out.println("But it failed!");
			this.impressive = false;
			return;
		}
		if (move == Move.EXTRAORDINARY) {
			System.out.print("\n" + this.name + " used " + move + "!");
			Move[] moves = Move.values();
			move = moves[new Random().nextInt(moves.length)];
			bp = move.basePower;
		}
		if (move == Move.FIRST_IMPRESSION && !this.impressive) {
			System.out.print("\n" + this.name + " used " + move + "!");
			System.out.println("\nBut it failed!");
			return;
		}
		int accEv = this.statStages[5] - foe.statStages[6];
		accEv = accEv > 6 ? 6 : accEv;
		accEv = accEv < -6 ? -6 : accEv;
		if (!hit(move.accuracy * (asAccModifier(accEv)))) {
			System.out.println("\n" + this.name + " used " + move + "!");
			System.out.println(this.name + "'s attack missed!");
			if (move == Move.HI_JUMP_KICK) {
				this.currentHP -= this.getStat(0) / 2;
				System.out.println(this.name + " kept going and crashed!");
				if (this.currentHP < 0) {
					this.faint(true, player);
					foe.awardxp((int) Math.ceil(this.level * trainer), player);
				}
			}
			this.impressive = false;
			this.vStatuses.remove(Status.LOCKED);
			return; // Check for miss
		}
		
		if (move.accuracy <= 100 && move.cat != 2 && move != Move.ELECTROEXPLOSION) {
			if (getImmune(foe, move.mtype)) {
				System.out.println("\n" + this.name + " used " + move + "!");
				System.out.println("It doesn't effect " + foe.name + "...");
				this.impressive = false;
				return; // Check for immunity
			}
		} else if (move.accuracy > 100 && move.cat != 2) {
			if (getImmune(foe, move.mtype)) {
				System.out.println("\n" + this.name + " used " + move + "!");
				System.out.println("It doesn't effect " + foe.name + "...");
				this.impressive = false;
				return; // Check for immunity
			}
		}
		if (move.cat != 2 && move.mtype == PType.GROUND && foe.magCount > 0) {
			System.out.println("\n" + this.name + " used " + move + "!");
			System.out.println("It doesn't effect " + foe.name + "...");
			this.impressive = false;
			return; // Check for immunity
		}
		if (foe.magCount > 0) foe.magCount--;
		
		System.out.println("\n" + this.name + " used " + move + "!");
		
		if (move == Move.DREAM_EATER && foe.status != Status.ASLEEP) {
			System.out.println("It doesn't effect " + foe.name + "...");
			this.impressive = false;
			return;
		}
		if (move.cat == 2) {
			statusEffect(foe, move, player);
			this.impressive = false;
			return;
		}
		
		if (move.basePower < 0) {
			bp = determineBasePower(foe, move);
		}
		
		if (this.vStatuses.contains(Status.AUTO)) {
			if (move == Move.BIG_BULLET || move == Move.GUNSHOT || move == Move.ROCKET) {
				bp *= 2;
				System.out.println("Hit 2 times!");
			}
		}
		// Use either physical or special attack/defense
		if (move.isPhysical()) {
			attackStat = this.getStat(1);
			defenseStat = foe.getStat(2);
			attackStat *= this.asModifier(0);
			defenseStat *= foe.asModifier(1);
			if (this.status == Status.BURNED) attackStat /= 2;
		} else {
			attackStat = this.getStat(3);
			defenseStat = foe.getStat(4);
			attackStat *= this.asModifier(2);
			defenseStat *= foe.asModifier(3);
			if (this.status == Status.BLEEDING) attackStat /= 2;
		}
		
		// Crit Check
		if (critCheck(move)) {
			System.out.println("A critical hit!");
			if (move.isPhysical() && attackStat < this.getStat(1)) {
				attackStat = this.getStat(1);
				if (this.status == Status.BURNED) attackStat /= 2;
			}
			if (!move.isPhysical() && attackStat < this.getStat(3)) {
				attackStat = this.getStat(3);
				if (this.status == Status.BLEEDING) attackStat /= 2;
			}
			if (move.isPhysical() && defenseStat > foe.getStat(2)) attackStat = foe.getStat(2);
			if (!move.isPhysical() && defenseStat > foe.getStat(4)) attackStat = foe.getStat(4);
			damage = calc(attackStat, defenseStat, bp, this.level);
			damage *= 1.5;
			if (move == Move.GUNSHOT || move == Move.SWORD_SLICE && foe.status == Status.HEALTHY) {
				foe.status = Status.BLEEDING;
				System.out.println(foe.name + " is bleeding!");
			}
		} else {
			damage = calc(attackStat, defenseStat, bp, this.level);
		}
		
		// Stab
		if (move.mtype == this.type1) damage *= 1.5;
		if (move.mtype == this.type2) damage *= 1.5;
		
		// Charged
		if (move.mtype == PType.ELECTRIC && this.vStatuses.contains(Status.CHARGED)) {
			damage *= 2;
			this.vStatuses.remove(Status.CHARGED);
		}
		
		double multiplier = 1;
		// Check type effectiveness
		PType[] resist = getResistances(move.mtype);
		for (PType type : resist) {
			if (foe.type1 == type) multiplier /= 2;
			if (foe.type2 == type) multiplier /= 2;
		}
		
		// Check type effectiveness
		PType[] weak = getWeaknesses(move.mtype);
		for (PType type : weak) {
			if (foe.type1 == type) multiplier *= 2;
			if (foe.type2 == type) multiplier *= 2;
		}
		
		damage *= multiplier;
		if (multiplier > 1) System.out.println("It's super effective!");
		if (multiplier < 1) System.out.println("It's not very effective...");
		
		if (move == Move.NIGHT_SHADE || move == Move.SEISMIC_TOSS) damage = this.level;
		if (move == Move.FIRE_DASH) damage = this.currentHP;
		if (move == Move.SUPER_FANG) damage = foe.currentHP / 2;
		if (move == Move.DRAGON_RAGE) damage = 40;
		if (move == Move.HORN_DRILL) {
			damage = foe.currentHP;
			System.out.println("It's a one-hit KO!");
		}
		
		if (move == Move.ABSORB || move == Move.DREAM_EATER || move == Move.GIGA_DRAIN || move == Move.MEGA_DRAIN || move == Move.LEECH_LIFE || move == Move.BUG_BITE || move == Move.INJECT) {
			int healAmount;
			if (damage >= foe.currentHP) {
				healAmount = Math.max((int) Math.ceil(foe.currentHP / 2.0), 1);
			} else {
				healAmount = Math.max((int) Math.ceil(damage / 2.0), 1);
			}
			
			this.currentHP += healAmount;
			if (this.currentHP > this.getStat(0)) this.currentHP = this.getStat(0);
			System.out.println(this.name + " sucked HP from " + foe.name + "!");
		}
		
		damage = Math.max(damage, 1);
		
		if (checkSecondary(move.secondary)) {
			secondaryEffect(foe, move);
		}
		int recoil = 0;
		if (move == Move.BRAVE_BIRD || move == Move.DOUBLE_EDGE || move == Move.FLARE_BLITZ || move == Move.HEAD_SMASH || move == Move.LIGHTNING_HEADBUTT || move == Move.SHELL_BASH || move == Move.SUPER_CHARGE || move == Move.TAKE_DOWN || move == Move.VOLT_TACKLE || move == Move.ROCK_WRECKER) {
			recoil = Math.max(Math.floorDiv(damage, 3), 1);
			if (damage >= foe.currentHP) recoil = Math.max(Math.floorDiv(foe.currentHP, 3), 1);
		}
		// Damage foe
		foe.currentHP -= damage;
		if (foe.currentHP <= 0 && move == Move.FALSE_SWIPE) foe.currentHP = 1;
		if (foe.currentHP <= 0) { // Check for kill
			foe.faint(true, player);
			this.awardxp((int) Math.ceil(foe.level * foe.trainer), player);
			if (this.vStatuses.contains(Status.BONDED)) {
				System.out.println(foe.name + " took its attacker down with it!");
				this.faint(true, player);
			}
		}
		if (recoil != 0) {
			System.out.println(this.name + " was damaged by recoil!");
			this.currentHP -= recoil;
			if (this.currentHP <= 0) { // Check for kill
				this.faint(true, player);
				foe.awardxp((int) Math.ceil(this.level * trainer), player);
			}
		}
		if (move == Move.SELF_DESTRUCT || move == Move.EXPLOSION || move == Move.ELECTROEXPLOSION || move == Move.FIRE_DASH) {
			this.faint(true, player);
			foe.awardxp((int) Math.ceil(this.level * trainer), player);
		}
		if (move == Move.HYPER_BEAM || move == Move.BLAST_BURN || move == Move.FRENZY_PLANT || move == Move.GIGA_IMPACT || move == Move.HYDRO_CANNON || move == Move.MAGIC_CRASH) this.vStatuses.add(Status.RECHARGE);
		if (move == Move.MAGIC_BLAST) {
			ArrayList<Move> moves = new ArrayList<>();
			for (Move cand : Move.values()) {
				if (cand.mtype == PType.ROCK || cand.mtype == PType.GRASS || cand.mtype == PType.GROUND) {
					moves.add(cand);
				}
			}
			Move[] validMoves = moves.toArray(new Move[moves.size()]);
			move(foe, validMoves[new Random().nextInt(validMoves.length)], player);
		}
		this.impressive = false;
		return;
	}

	public void awardxp(int amt, Player player) {
	    if (this.fainted) return;
	    if (!this.trainerOwned) return;

	    ArrayList<Pokemon> teamCopy = new ArrayList<>(Arrays.asList(player.getTeam()));
	    int numBattled = player.getBattled();
	    if (numBattled == 0) return;
	    int expPerPokemon = amt / numBattled;
	    int remainingExp = amt % numBattled;

	    // Award experience points to each battled Pokemon
	    for (Pokemon p : teamCopy) {
	        if (p != null && p.battled) {
	            int expAwarded = expPerPokemon;
	            if (remainingExp > 0) {
	                expAwarded++;
	                remainingExp--;
	            }
	            if (p.level < 100) {
	                p.exp += expAwarded;
	                System.out.println(p.name + " gained " + expAwarded + " experience points!");
	            }
	            while (p.exp >= p.expMax) {
	                // Pokemon has leveled up, check for evolution
	                Pokemon evolved = p.levelUp();
	                if (evolved != null) {
	                    // Update the player's team with the evolved Pokemon
	                    int index = Arrays.asList(player.getTeam()).indexOf(p);
	                    player.team[index] = evolved;
	                    if (index == 0) player.current = evolved;
	                    evolved.checkMove();
	                    p = evolved;
	                }
	            }
	        }
	    }
	}



	private void secondaryEffect(Pokemon foe, Move move) {
		if (move == Move.ACID) {
			stat(foe, 3, -1);
		} else if (move == Move.ANCIENT_POWER) {
			for (int i = 0; i < 5; ++i) {
				stat(this, i, 1);
			}
		} else if (move == Move.AIR_SLASH && this.getSpeed() >= foe.getSpeed()) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.ASTONISH && this.getSpeed() >= foe.getSpeed()) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.BEAT_UP) {
			if (foe.status == Status.HEALTHY) {
				foe.status = Status.BLEEDING;
				System.out.println(foe.name + " is bleeding!");
			}
		} else if (move == Move.BIG_BULLET) {
			foe.paralyze(false);
		} else if (move == Move.BITE && this.getSpeed() >= foe.getSpeed()) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.BLACK_HOLE) {
			stat(foe, 5, -1);
		} else if (move == Move.BLAST_FLAME) {
			foe.burn(false);
		} else if (move == Move.BLAZING_SWORD) {
			foe.burn(false);
		} else if (move == Move.BLUE_FLARE) {
			foe.burn(false);
		} else if (move == Move.BODY_SLAM) {
			foe.paralyze(false);
		} else if (move == Move.BOLT_STRIKE) {
			foe.paralyze(false);
		} else if (move == Move.BOULDER_CRUSH && this.getSpeed() >= foe.getSpeed()) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.BOUNCE) {
			foe.paralyze(false);
		} else if (move == Move.BUBBLEBEAM) {
			stat(foe, 4, -1);
		} else if (move == Move.CHOMP) {
			stat(foe, 4, -1);
		} else if (move == Move.CLOSE_COMBAT) {
			stat(this, 1, -1);
			stat(this, 3, -1);
		} else if (move == Move.CONFUSION) {
			if (!foe.vStatuses.contains(Status.CONFUSED)) {
				foe.confuse();
			}
		} else if (move == Move.CONSTRICT) {
			stat(foe, 4, -1);
		} else if (move == Move.CROSS_POISON) {
			foe.poison(false);
		} else if (move == Move.CRUNCH) {
			stat(foe, 1, -1);
		} else if (move == Move.DARK_PULSE && this.getSpeed() >= foe.getSpeed()) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.DISCHARGE) {
			foe.paralyze(false);
		} else if (move == Move.DISCHARGE) {
			foe.confuse();
		} else if (move == Move.DOUBLE_BLAST) {
			if (!foe.vStatuses.contains(Status.CONFUSED)) {
				foe.confuse();
			}
		} else if (move == Move.DOUBLE_SLICE) {
			if (foe.status == Status.HEALTHY) {
				foe.status = Status.BLEEDING;
				System.out.println(foe.name + " is bleeding!");
			}
		} else if (move == Move.DRACO_METEOR) {
			stat(this, 2, -2);
		} else if (move == Move.DRAGON_RUSH && this.getSpeed() >= foe.getSpeed()) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.DRAGON_BREATH) {
			foe.paralyze(false);
		} else if (move == Move.DUAL_STAB) {
			if (foe.status == Status.HEALTHY) {
				foe.status = Status.BLEEDING;
				System.out.println(foe.name + " is bleeding!");
			}
		} else if (move == Move.EARTH_POWER) {
			stat(foe, 3, -1);
		} else if (move == Move.EMBER) {
			foe.burn(false);
		} else if (move == Move.FIREBALL) {
			foe.burn(false);
		} else if (move == Move.FIRE_BLAST) {
			foe.burn(false);
		} else if (move == Move.FIRE_CHARGE) {
			int randomNum = ((int) Math.random() * 3);
			if (randomNum == 0) {
				foe.burn(false);
			} else if (randomNum == 1 && this.getSpeed() >= foe.getSpeed()) {
				foe.vStatuses.add(Status.FLINCHED);
			}
			 else if (randomNum == 2) {
				if (this.getSpeed() >= foe.getSpeed()) foe.vStatuses.add(Status.FLINCHED);
				foe.burn(false);
			}
		} else if (move == Move.FIRE_DASH) {
			foe.burn(false);
		} else if (move == Move.FIRE_FANG) {
			int randomNum = ((int) Math.random() * 3);
			if (randomNum == 0) {
				foe.burn(false);
			} else if (randomNum == 1 && this.getSpeed() >= foe.getSpeed()) {
				foe.vStatuses.add(Status.FLINCHED);
			}
			 else if (randomNum == 2) {
				if (this.getSpeed() >= foe.getSpeed()) foe.vStatuses.add(Status.FLINCHED);
				foe.burn(false);
			}
		} else if (move == Move.FIRE_PUNCH) {
			foe.burn(false);
		} else if (move == Move.FIRE_SPIN) {
			if (!foe.vStatuses.contains(Status.SPUN)) {
				if (foe.type1 != PType.FIRE && foe.type2 != PType.FIRE) {
					foe.vStatuses.add(Status.SPUN);
					foe.spunCount = (((int) (Math.random() * 4)) + 2);
					System.out.println(foe.name + " was trapped in a fiery vortex!");
				}
			}
		} else if (move == Move.WHIRLPOOL) {
			if (!foe.vStatuses.contains(Status.SPUN)) {
				if (foe.type1 != PType.WATER && foe.type2 != PType.WATER) {
					foe.vStatuses.add(Status.SPUN);
					foe.spunCount = (((int) (Math.random() * 4)) + 2);
					System.out.println(foe.name + " was trapped in a whirlpool vortex!");
				}
			}
		} else if (move == Move.WRAP) {
			if (!foe.vStatuses.contains(Status.SPUN)) {
				foe.vStatuses.add(Status.SPUN);
				foe.spunCount = (((int) (Math.random() * 4)) + 2);
				System.out.println(foe.name + " was wrapped by " + this.name + "!");
			}
		} else if (move == Move.FIRE_TAIL) {
			foe.burn(false);
		} else if (move == Move.FLAME_BURST) {
			foe.burn(false);
			this.confuse();
		} else if (move == Move.FLAME_WHEEL) {
			foe.burn(false);
		} else if (move == Move.FLAMETHROWER) {
			foe.burn(false);
		} else if (move == Move.FLARE_BLITZ) {
			foe.burn(false);
		} else if (move == Move.FLASH_CANNON) {
			stat(foe, 3, -1);
		} else if (move == Move.GALAXY_ATTACK) {
			int randomNum = ((int) Math.random() * 5);
			switch (randomNum) {
			case 0:
				foe.burn(false);
				break;
			case 1:
				foe.sleep(false);
				break;
			case 2:
				foe.paralyze(false);
				break;
			case 3:
				foe.poison(false);
				break;
			case 4:
				if (foe.status == Status.HEALTHY) {
					foe.status = Status.BLEEDING;
					System.out.println(foe.name + " is bleeding!");
				}
				break;
			default:
				return;
			}
		} else if (move == Move.GIGA_HIT) {
			foe.paralyze(false);
		} else if (move == Move.GUNK_SHOT) {
			foe.poison(false);
		} else if (move == Move.HAMMER_ARM) {
			stat(this, 4, -1);
		} else if (move == Move.HEADBUTT && this.getSpeed() >= foe.getSpeed()) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.HEAT_WAVE) {
			foe.burn(false);
		} else if (move == Move.HYPER_FANG && this.getSpeed() >= foe.getSpeed()) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.INJECT) {
			foe.poison(false);
		} else if (move == Move.IRON_HEAD && this.getSpeed() >= foe.getSpeed()) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.IRON_TAIL) {
			stat(foe, 1, -1);
		} else if (move == Move.LAVA_PLUME) {
			foe.burn(false);
		} else if (move == Move.LEAF_KOBE) {
			foe.paralyze(false);
		} else if (move == Move.LEAF_PULSE) {
			stat(foe, 5, -1);
			int randomNum = ((int) Math.round(Math.random()));
			if (randomNum == 0) {
				foe.sleep(false);
			}
		} else if (move == Move.LEAF_STORM) {
			stat(this, 2, -2);
		} else if (move == Move.LEAF_TORNADO) {
			stat(foe, 5, -1);
		} else if (move == Move.LICK) {
			foe.paralyze(false);
		} else if (move == Move.LIGHTNING_HEADBUTT) {
			int randomNum = ((int) Math.random() * 3);
			if (randomNum == 0) {
				foe.paralyze(false);
			} else if (randomNum == 1 && this.getSpeed() >= foe.getSpeed()) {
				foe.vStatuses.add(Status.FLINCHED);
			}
			 else if (randomNum == 2) {
				if (this.getSpeed() >= foe.getSpeed()) foe.vStatuses.add(Status.FLINCHED);
				foe.paralyze(false);
			}
		} else if (move == Move.LOW_KICK) {
			stat(foe, 4, -1);
		} else if (move == Move.MACHETE_STAB && foe.status == Status.HEALTHY) {
			foe.status = Status.BLEEDING;
			System.out.println(foe.name + " is bleeding!");
		} else if (move == Move.MAGIC_CRASH) {
			int randomNum = ((int) Math.random() * 5);
			switch (randomNum) {
			case 0:
				foe.burn(false);
				break;
			case 1:
				foe.sleep(false);
				break;
			case 2:
				foe.paralyze(false);
				break;
			case 3:
				foe.poison(false);
				break;
			case 4:
				if (foe.status == Status.HEALTHY) {
					foe.status = Status.BLEEDING;
					System.out.println(foe.name + " is bleeding!");
				}
				break;
			default:
				return;
			}
		} else if (move == Move.MAGIC_FANG && this.getSpeed() >= foe.getSpeed()) {
			double multiplier = 1;
			// Check type effectiveness
			PType[] resist = getResistances(move.mtype);
			for (PType type : resist) {
				if (foe.type1 == type) multiplier /= 2;
				if (foe.type2 == type) multiplier /= 2;
			}
			
			// Check type effectiveness
			PType[] weak = getWeaknesses(move.mtype);
			for (PType type : weak) {
				if (foe.type1 == type) multiplier *= 2;
				if (foe.type2 == type) multiplier *= 2;
			}
			if (multiplier > 1) foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.MEGA_KICK) {
			foe.paralyze(false);
		} else if (move == Move.MEGA_PUNCH) {
			foe.paralyze(false);
		} else if (move == Move.MEGA_SWORD) {
			foe.paralyze(false);
		} else if (move == Move.MUD_BOMB) {
			stat(foe, 5, -1);
		} else if (move == Move.MUD_SLAP) {
			stat(foe, 5, -1);
		} else if (move == Move.NEEDLE_SPRAY) {
			int randomNum = ((int) Math.round(Math.random()));
			if (randomNum == 0) {
				foe.paralyze(false);
			} else {
				foe.poison(false);
			}
		} else if (move == Move.OVERHEAT) {
			stat(this, 2, -2);
		} else if (move == Move.POISON_BALL) {
			foe.poison(false);
		} else if (move == Move.POISON_FANG) {
			int randomNum = ((int) Math.random() * 3);
			if (randomNum == 0) {
				foe.poison(false);
			} else if (randomNum == 1 && this.getSpeed() >= foe.getSpeed()) {
				foe.vStatuses.add(Status.FLINCHED);
			}
			 else if (randomNum == 2) {
				if (this.getSpeed() >= foe.getSpeed()) foe.vStatuses.add(Status.FLINCHED);
				foe.poison(false);
			}
		} else if (move == Move.POISON_JAB) {
			foe.poison(false);
		} else if (move == Move.POISON_PUNCH) {
			foe.poison(false);
		} else if (move == Move.POISON_STING) {
			foe.poison(false);
		} else if (move == Move.POISONOUS_WATER) {
			foe.poison(false);
		} else if (move == Move.RAPID_SPIN) {
			stat(this, 4, 1);
			if (this.vStatuses.contains(Status.SPUN)) {
				this.vStatuses.remove(Status.SPUN);
				System.out.println(this.name + " was freed!");
				this.spunCount = 0;
			}
		} else if (move == Move.ROCK_SMASH) {
			stat(foe, 1, -1);
		} else if (move == Move.ROCK_TOMB) {
			stat(foe, 4, -1);
		} else if (move == Move.ROCKET) {
			foe.paralyze(false);
		} else if (move == Move.ROOT_CRUSH) {
			foe.paralyze(false);
		} else if (move == Move.SHADOW_BALL) {
			stat(foe, 3, -1);
		} else if (move == Move.SHOCK) {
			foe.paralyze(false);
		} else if (move == Move.SHURIKEN && foe.status == Status.HEALTHY) {
			foe.status = Status.BLEEDING;
			System.out.println(foe.name + " is bleeding!");
		} else if (move == Move.SKY_ATTACK && this.getSpeed() >= foe.getSpeed()) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.SLUDGE) {
			foe.poison(false);
		} else if (move == Move.SLUDGE_BOMB) {
			foe.poison(false);
		} else if (move == Move.SMOG) {
			foe.poison(false);
		} else if (move == Move.SPARK) {
			foe.paralyze(false);
		} else if (move == Move.SPIKE_JAB) {
			foe.poison(false);
		} else if (move == Move.STING && foe.status == Status.HEALTHY) {
			foe.status = Status.BLEEDING;
			System.out.println(foe.name + " is bleeding!");
		} else if (move == Move.STOMP && this.getSpeed() >= foe.getSpeed()) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.STRONG_ARM) {
			int randomNum = ((int) Math.random() * 3);
			if (randomNum == 0) {
				foe.paralyze(false);
			} else if (randomNum == 1 && this.getSpeed() >= foe.getSpeed()) {
				foe.vStatuses.add(Status.FLINCHED);
			}
			 else if (randomNum == 2) {
				if (this.getSpeed() >= foe.getSpeed()) foe.vStatuses.add(Status.FLINCHED);
				foe.paralyze(false);
			}
		} else if (move == Move.SUPER_CHARGE && this.getSpeed() >= foe.getSpeed()) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.SUPERPOWER) {
			stat(this, 0, -1);
			stat(this, 1, -1);
		} else if (move == Move.SWEEP_KICK) {
			stat(foe, 0, -1);
		} else if (move == Move.SWORD_SPIN) {
			stat(this, 0, 1);
		} else if (move == Move.SWORD_STAB && foe.status == Status.HEALTHY) {
			foe.status = Status.BLEEDING;
			System.out.println(foe.name + " is bleeding!");
		} else if (move == Move.THUNDER) {
			foe.paralyze(false);
		} else if (move == Move.THUNDERBOLT) {
			foe.paralyze(false);
		} else if (move == Move.THUNDER_FANG) {
			int randomNum = ((int) Math.random() * 3);
			if (randomNum == 0) {
				foe.paralyze(false);
			} else if (randomNum == 1 && this.getSpeed() >= foe.getSpeed()) {
				foe.vStatuses.add(Status.FLINCHED);
			}
			 else if (randomNum == 2) {
				if (this.getSpeed() >= foe.getSpeed()) foe.vStatuses.add(Status.FLINCHED);
				foe.paralyze(false);
			}
		} else if (move == Move.THUNDER_KICK) {
			foe.paralyze(false);
		} else if (move == Move.THUNDER_PUNCH) {
			foe.paralyze(false);
		} else if (move == Move.THUNDERSHOCK) {
			foe.paralyze(false);
		} else if (move == Move.TORNADO_SPIN) {
			stat(this, 4, 1);
			stat(this, 5, 1);
			if (this.vStatuses.contains(Status.SPUN)) {
				this.vStatuses.remove(Status.SPUN);
				System.out.println(this.name + " was freed!");
				this.spunCount = 0;
			}
		} else if (move == Move.TWISTER && this.getSpeed() >= foe.getSpeed()) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.VOLT_TACKLE) {
			foe.paralyze(false);
		} else if (move == Move.WATER_PULSE) {
			foe.confuse();
		} else if (move == Move.WATERFALL && this.getSpeed() >= foe.getSpeed()) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.WOOD_FANG && this.getSpeed() >= foe.getSpeed()) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.ZEN_HEADBUTT && this.getSpeed() >= foe.getSpeed()) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.ROCK_SLIDE && this.getSpeed() >= foe.getSpeed()) {
			foe.vStatuses.add(Status.FLINCHED);
		} 
	}

	private boolean checkSecondary(int secondary) {
		double chance = (int) (Math.random()*100 + 1);
		if (chance <= secondary) {
			return true;
		} else {
			return false;
		}
	}

	private void statusEffect(Pokemon foe, Move move, Player player) {
		if (move == Move.AGILITY) {
			stat(this, 4, 2);
		} else if (move == Move.AQUA_RING) {
			if (!(this.vStatuses.contains(Status.AQUA_RING))) {
			    this.vStatuses.add(Status.AQUA_RING);
			} else {
			    System.out.println("But it failed!");
			}
		} else if (move == Move.AUTOMOTIZE) {
			stat(this, 4, 2);
		} else if (move == Move.AUTO_SHOT) {
			if (!(this.vStatuses.contains(Status.AUTO))) {
				this.vStatuses.add(Status.AUTO);
				System.out.println(this.name + " upgraded its weapon!");
			} else {
				System.out.println("But it failed!");
			}
		} else if (move == Move.BAWL) {
			stat(foe, 0, -2);
		} else if (move == Move.BLACK_DUST) {
			stat(foe, 5, -2);
		} else if (move == Move.BULK_UP) {
			stat(this, 0, 1);
			stat(this, 1, 1);
		} else if (move == Move.BUZZ) {
			foe.confuse();
		} else if (move == Move.CHARGE) {
			System.out.println(this.name + " became charged with power!");
			this.vStatuses.add(Status.CHARGED);
			stat(this, 3, 1);
		} else if (move == Move.CHARM) {
			stat(foe, 0, -2);
		} else if (move == Move.CONFUSE_RAY) {
			if (!foe.vStatuses.contains(Status.CONFUSED)) {
				foe.confuse();
			} else {
				System.out.println("But it failed!");
			}
		} else if (move == Move.CURSE) {
			if (!foe.vStatuses.contains(Status.CURSED)) {
				foe.vStatuses.add(Status.CURSED);
				System.out.println(foe.name + " was afflicted with a curse!");
				this.currentHP -= (this.getStat(0) / 2);
				if (this.currentHP <= 0) {
					this.faint(true, player);
					foe.awardxp((int) Math.ceil(this.level * trainer), player);
				}
			} else {
				System.out.println("But it failed!");
			}
		} else if (move == Move.DARK_VOID) {
			foe.sleep(true);
		} else if (move == Move.DEFENSE_CURL) {
			stat(this, 1, 1);
		} else if (move == Move.DESTINY_BOND) {
			if (this.lastMoveUsed != Move.DESTINY_BOND) {
				foe.vStatuses.add(Status.BONDED);
				System.out.println(this.name + " is ready to take its attacker down with it!");
			} else System.out.println("But it failed!");
			this.impressive = false;
			this.lastMoveUsed = move;
			
		} else if (move == Move.DISAPPEAR) {
			stat(this, 6, 2);
		} else if (move == Move.DOUBLE_TEAM) {
			stat(this, 6, 1);
		} else if (move == Move.DRAGON_DANCE) {
			stat(this, 0, 1);
			stat(this, 4, 1);
		} else if (move == Move.FLASH) {
			stat(foe, 5, -1);
		} else if (move == Move.FORESIGHT) {
			if (foe.type1 == PType.GHOST) foe.type1 = PType.NORMAL;
			if (foe.type2 == PType.GHOST) foe.type2 = PType.NORMAL;
			System.out.println(this.name + " identified " + foe.name + "!");
			stat(this, 5, 1);
		} else if (move == Move.GLARE) {
			foe.paralyze(true);
		} else if (move == Move.GROWL) {
			stat(foe, 0, -1);
		} else if (move == Move.GROWTH) {
			this.statStages[0] += 1;
			stat(this, 0, 1);
			stat(this, 2, 1);
		} else if (move == Move.HARDEN) {
			stat(this, 1, 1);
		} else if (move == Move.HAZE) {
			this.statStages = new int[7];
			foe.statStages = new int[7];
			System.out.println(this.name + "All stat changes were eliminated!");
		} else if (move == Move.HYPNOSIS) {
			foe.sleep(true);
		} else if (move == Move.IGNITE) {
			foe.burn(true);
		} else if (move == Move.IRON_DEFENSE) {
			stat(this, 1, 2);
		} else if (move == Move.LEECH_SEED) {
			if (foe.type1 == PType.GRASS || foe.type2 == PType.GRASS) {
				System.out.println("It doesn't effect " + foe.name + "...");
				return;
			}
			if (!foe.vStatuses.contains(Status.LEECHED)) {
				foe.vStatuses.add(Status.LEECHED);
				System.out.println(foe.name + " was seeded!");
			} else {
				System.out.println("But it failed!");
			}
		} else if (move == Move.LEER) {
			stat(foe, 1, -1);
		} else if (move == Move.LOCK_ON) {
			System.out.println(this.name + " took aim at " + foe.name + "!\n");
			stat(this, 5, 6);
		} else if (move == Move.MAGIC_REFLECT) {
			if (this.lastMoveUsed != Move.MAGIC_REFLECT) {
				this.vStatuses.add(Status.REFLECT);
			} else System.out.println("But it failed!");
			this.impressive = false;
			this.lastMoveUsed = move;
		} else if (move == Move.MAGNET_RISE) {
			if (this.magCount == 0) {
				this.magCount = 5;
				System.out.println(this.name + " floated with electromagnetism!");
			} else {
				System.out.println("But it failed!");
			}
		} else if (move == Move.METAL_SOUND) {
			stat(foe, 3, -2);
		} else if (move == Move.MINIMIZE) {
			stat(this, 6, 2);
		} else if (move == Move.MOONLIGHT) {
			if (this.currentHP == this.getStat(0)) {
				System.out.println(this.name + "'s HP is full!");
			} else {
				this.currentHP += (this.getStat(0) / 2);
				if (this.currentHP > this.getStat(0)) this.currentHP = this.getStat(0);
				System.out.println(this.name + " restored HP.");
			}
		} else if (move == Move.MUD_SPORT) {
			System.out.println(this.name + " electric's power was weakened!");
		} else if (move == Move.NIGHTMARE) {
			if (foe.status == Status.ASLEEP && !foe.vStatuses.contains(Status.NIGHTMARE)) {
				foe.vStatuses.add(Status.NIGHTMARE);
				System.out.println(foe.name + " had a nightmare!");
			} else {
				System.out.println("But it failed!");
			}
		} else if (move == Move.ODOR_SLEUTH) {
			if (foe.type1 == PType.GHOST) foe.type1 = PType.NORMAL;
			if (foe.type2 == PType.GHOST) foe.type2 = PType.NORMAL;
			System.out.println(this.name + " identified " + foe.name + "!");
			stat(foe, 6, -1);
		} else if (move == Move.PERISH_SONG) {
			this.perishCount = (this.perishCount == 0) ? 3 : this.perishCount;
			foe.perishCount = (foe.perishCount == 0) ? 3 : foe.perishCount;
		} else if (move == Move.PHASE_SHIFT) {
			this.type1 = PType.MAGIC;
			if (foe.lastMoveUsed != null) {
				this.type2 = foe.lastMoveUsed.mtype;
				System.out.println(this.name + " became a " + type1.toString() + " / " + type2.toString() + " type!");
			} else {
				System.out.println(this.name + " became a " + type1.toString() + " type!");
			}
		} else if (move == Move.POISON_GAS) {
			foe.poison(true);
		} else if (move == Move.POISON_POWDER) {
			foe.poison(true);
		} else if (move == Move.SHELL_SMASH) {
			stat(this, 1, -1);
			stat(this, 3, -1);
			stat(this, 0, 2);
			stat(this, 2, 2);
			stat(this, 4, 2);
		} else if (move == Move.REBOOT) {
			if (this.status != Status.HEALTHY || !this.vStatuses.isEmpty()) System.out.println(this.name + " became healthy!");
			this.status = Status.HEALTHY;
			removeBad(this.vStatuses);
			stat(this, 4, 1);
		} else if (move == Move.ROOST) {
			if (this.currentHP == this.getStat(0)) {
				System.out.println(this.name + "'s HP is full!");
			} else {
				this.currentHP += (this.getStat(0) / 2);
				if (this.currentHP > this.getStat(0)) this.currentHP = this.getStat(0);
				System.out.println(this.name + " restored HP.");
			}
		} else if (move == Move.SAND_ATTACK) {
			stat(foe, 5, -1);
		} else if (move == Move.SCARY_FACE) {
			stat(foe, 4, -2);
		} else if (move == Move.SCREECH) {
			stat(foe, 1, -2);
		} else if (move == Move.SLEEP_POWDER) {
			foe.sleep(true);
		} else if (move == Move.SMOKESCREEN) {
			stat(foe, 5, -1);
		} else if (move == Move.STARE) {
			stat(foe, 0, 1);
			if (!foe.vStatuses.contains(Status.CONFUSED)) {
				foe.confuse();
			}
		} else if (move == Move.STRING_SHOT) {
			stat(foe, 4, -2);
		} else if (move == Move.SUPERSONIC) {
			if (!foe.vStatuses.contains(Status.CONFUSED)) {
				foe.confuse();
			} else {
				System.out.println("But it failed!");
			}
		} else if (move == Move.SWAGGER) {
			stat(foe, 0, 2);
			if (!foe.vStatuses.contains(Status.CONFUSED)) {
				foe.confuse();
			}
		} else if (move == Move.SYNTHESIS) {
			if (this.currentHP == this.getStat(0)) {
				System.out.println(this.name + "'s HP is full!");
			} else {
				this.currentHP += (this.getStat(0) / 2);
				if (this.currentHP > this.getStat(0)) this.currentHP = this.getStat(0);
				System.out.println(this.name + " restored HP.");
			}
		} else if (move == Move.TAIL_WHIP) {
			stat(foe, 1, -1);
		} else if (move == Move.TAKE_OVER) {
			if (this.lastMoveUsed != Move.TAKE_OVER) {
				foe.vStatuses.add(Status.POSESSED);
				System.out.println(this.name + " posessed " + foe.name + "!");
			} else System.out.println("But it failed!");
			this.impressive = false;
			this.lastMoveUsed = move;
		} else if (move == Move.THUNDER_WAVE) {
			foe.paralyze(true);
		} else if (move == Move.TOXIC) {
			foe.poison(true);
		} else if (move == Move.WILL_O_WISP) {
			foe.burn(true);
		} if (move == Move.ROCK_POLISH) {
			stat(this, 4, 2);
		}
		return;
	}

	private void stat(Pokemon p, int i, int amt) {
		if (amt == 0) throw new IllegalArgumentException("Stat change amount cannot be 0");
		String type = "";
		if (i == 0) type = "Attack";
		if (i == 1) type = "Defense";
		if (i == 2) type = "Special Attack";
		if (i == 3) type = "Special Defense";
		if (i == 4) type = "Speed";
		if (i == 5) type = "Accuracy";
		if (i == 6) type = "Evasion";
		String amount = "";
		if (amt == 1) amount = " rose";
		if (amt == -1) amount = " fell";
		if (amt > 1) amount = " Sharply rose";
		if (amt < -1) amount = " Harshly fell";
		p.statStages[i] += amt;
		if (p.statStages[i] > 6 && amt > 0) {
			p.statStages[i] = 6;
			System.out.println(p.name + "'s " + type + " won't go any higher!");
		} else if (p.statStages[i] < -6 && amt < 0){
			p.statStages[i] = -6;
			System.out.println(p.name + "'s " + type + " won't go any lower!");
		} else {
			System.out.println(p.name + "'s " + type + amount + "!");
		}
		
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
		}
		return modifier;
	}
	
	public double asAccModifier(int value) {
		double modifier = 1;
		switch(value) {
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

	private boolean hit(double d) {
		double hitChance = (int) (Math.random()*100 + 1);
		int acc = (int) Math.round(d);
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
		  } else if (m.critChance == 2) {
		    baseCrit = 25;
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
	    PType[] toReturn = new PType[resistantTypes.size()];
	    return resistantTypes.toArray(toReturn);
	}
	
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
	    PType[] toReturn = new PType[resistantTypes.size()];
	    return resistantTypes.toArray(toReturn);
	}
	
	private void setMoveBank() {
		switch(this.id) {
		case -1:
			movebank = new Move[15];
			movebank[0] = Move.POKE;
			movebank[1] = Move.GROWL;
			movebank[5] = Move.LEECH_LIFE;
			movebank[8] = Move.LEAF_SLAP;
			movebank[11] = Move.DOUBLE_SLAP;
			movebank[14] = Move.LEAF_TORNADO;
			break;
		case -2:
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
		case -3:
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
		case -4:
			movebank = new Move[15];
			movebank[0] = Move.TACKLE;
			movebank[1] = Move.SCREECH;
			movebank[6] = Move.IGNITE;
			movebank[8] = Move.EMBER;
			movebank[11] = Move.SLAM;
			movebank[14] = Move.FIRE_FANG;
			break;
		case -5:
			movebank = new Move[33];
			movebank[0] = Move.TACKLE;
			movebank[1] = Move.SCREECH;
			movebank[6] = Move.IGNITE;
			movebank[8] = Move.EMBER;
			movebank[11] = Move.SLAM;
			movebank[14] = Move.FIRE_FANG;
			movebank[18] = Move.BLACK_DUST;
			movebank[23] = Move.FIRE_SPIN;
			movebank[27] = Move.SELF_DESTRUCT;
			movebank[32] = Move.LAVA_PLUME;
			break;
		case -6:
			movebank = new Move[65];
			movebank[0] = Move.OVERHEAT;
			movebank[1] = Move.SCREECH;
			movebank[6] = Move.IGNITE;
			movebank[8] = Move.EMBER;
			movebank[11] = Move.SLAM;
			movebank[14] = Move.FIRE_FANG;
			movebank[18] = Move.BLACK_DUST;
			movebank[23] = Move.FIRE_SPIN;
			movebank[27] = Move.SELF_DESTRUCT;
			movebank[32] = Move.LAVA_PLUME;
			movebank[38] = Move.EXPLOSION;
			movebank[44] = Move.ERUPTION;
			movebank[54] = Move.FIRE_BLAST;
			movebank[64] = Move.BLAST_BURN;
			break;
		case -7:
			movebank = new Move[18];
			movebank[0] = Move.TACKLE;
			movebank[1] = Move.TAIL_WHIP;
			movebank[5] = Move.SAND_ATTACK;
			movebank[8] = Move.BUBBLE;
			movebank[10] = Move.BITE;
			movebank[13] = Move.BUBBLEBEAM;
			movebank[17] = Move.WATER_GUN;
			break;
		case -8:
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
		case -9:
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
		case -10:
			movebank = new Move[15];
			movebank[0] = Move.POKE;
			movebank[3] = Move.LEAF_SLAP;
			movebank[4] = Move.STARE;
			movebank[7] = Move.PUNCH;
			movebank[10] = Move.WOOD_FANG;
			movebank[14] = Move.LEAF_PULSE;
			break;
		case -11:
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
			movebank[35] = Move.GLARE;
			movebank[44] = Move.FAINT_ATTACK;
			movebank[54] = Move.LEAF_STORM;
			break;
		case -12:
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
		case -13:
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
		case -14:
			movebank = new Move[12];
			movebank[0] = Move.SCRATCH;
			movebank[1] = Move.LEER;
			movebank[3] = Move.TAIL_WHIP;
			movebank[6] = Move.TACKLE;
			movebank[11] = Move.TAIL_WHACK;
			break;
		case -15:
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
		case -16:
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
		case -17:
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
		case -18:
			movebank = new Move[21];
			movebank[0] = Move.TACKLE;
			movebank[2] = Move.DEFENSE_CURL;
			movebank[5] = Move.MUD_SPORT;
			movebank[8] = Move.ROCK_POLISH;
			movebank[11] = Move.ROCK_THROW;
			movebank[15] = Move.ROCK_TOMB;
			movebank[20] = Move.MAGNITUDE;
			break;
		case -19:
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
		case -20:
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
		case -21:
			movebank = new Move[21];
			movebank[0] = Move.TACKLE;
			movebank[1] = Move.DEFENSE_CURL;
			movebank[4] = Move.ROCK_POLISH;
			movebank[10] = Move.SLAM;
			movebank[15] = Move.RAPID_SPIN;
			movebank[20] = Move.ROLLOUT;
			break;
		case -22:
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
		case -23:
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
		case -24:
			movebank = new Move[19];
			movebank[0] = Move.NIBBLE;
			movebank[1] = Move.LEER;
			movebank[3] = Move.BUBBLE;
			movebank[6] = Move.BITE;
			movebank[9] = Move.WATER_GUN;
			movebank[13] = Move.CHOMP;
			movebank[18] = Move.HYPER_FANG;
			break;
		case -25:
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
		case -26:
			movebank = new Move[15];
			movebank[0] = Move.TACKLE;
			movebank[4] = Move.SAND_ATTACK;
			movebank[6] = Move.FORESIGHT;
			movebank[9] = Move.GUST;
			movebank[12] = Move.PECK;
			movebank[14] = Move.WING_ATTACK;
			break;
		case -27:
			movebank = new Move[33];
			movebank[0] = Move.TACKLE;
			movebank[4] = Move.SAND_ATTACK;
			movebank[6] = Move.FORESIGHT;
			movebank[9] = Move.GUST;
			movebank[12] = Move.PECK;
			movebank[14] = Move.WING_ATTACK;
			movebank[17] = Move.QUICK_ATTACK;
			movebank[19] = Move.AIR_CUTTER;
			movebank[23] = Move.TWISTER;
			movebank[27] = Move.AGILITY;
			movebank[32] = Move.ROOST;
			break;
		case -28:
			movebank = new Move[55];
			movebank[0] = Move.TACKLE;
			movebank[4] = Move.SAND_ATTACK;
			movebank[6] = Move.FORESIGHT;
			movebank[9] = Move.GUST;
			movebank[12] = Move.PECK;
			movebank[14] = Move.WING_ATTACK;
			movebank[17] = Move.QUICK_ATTACK;
			movebank[19] = Move.AIR_CUTTER;
			movebank[23] = Move.TWISTER;
			movebank[27] = Move.AGILITY;
			movebank[32] = Move.ROOST;
			movebank[39] = Move.MIRROR_MOVE;
			movebank[44] = Move.DRILL_PECK;
			movebank[49] = Move.AIR_SLASH;
			movebank[54] = Move.BRAVE_BIRD;
			break;
		case -29:
			movebank = new Move[15];
			movebank[0] = Move.SAND_ATTACK;
			movebank[9] = Move.PECK;
			movebank[14] = Move.WING_ATTACK;
			break;
		case -30:
			movebank = new Move[55];
			movebank[0] = Move.SAND_ATTACK;
			movebank[9] = Move.PECK;
			movebank[14] = Move.WING_ATTACK;
			movebank[17] = Move.GUST;
			movebank[20] = Move.TWISTER;
			movebank[25] = Move.FURY_ATTACK;
			movebank[32] = Move.AGILITY;
			movebank[39] = Move.ROOST;
			movebank[49] = Move.MIRROR_MOVE;
			movebank[54] = Move.DRILL_PECK;
			break;
		case -31:
			movebank = new Move[40];
			movebank[0] = Move.TACKLE;
			movebank[2] = Move.LEER;
			movebank[5] = Move.SAND_ATTACK;
			movebank[9] = Move.GUST;
			movebank[12] = Move.PECK;
			movebank[16] = Move.WATER_GUN;
			movebank[20] = Move.HEADBUTT;
			movebank[29] = Move.WING_ATTACK;
			movebank[39] = Move.DRILL_PECK;
			break;
		case -32:
			movebank = new Move[45];
			movebank[0] = Move.SCRATCH;
			movebank[4] = Move.LEAF_SLAP;
			movebank[9] = Move.GUST;
			movebank[15] = Move.LEAF_KOBE;
			movebank[16] = Move.PECK;
			movebank[21] = Move.LEAF_GUST;
			movebank[29] = Move.LEAF_BALL;
			movebank[34] = Move.WING_ATTACK;
			movebank[44] = Move.DRILL_PECK;
			break;
		case -33:
			movebank = new Move[16];
			movebank[0] = Move.POISON_STING;
			movebank[2] = Move.STRING_SHOT;
			movebank[5] = Move.LEECH_LIFE;
			movebank[9] = Move.SUPERSONIC;
			movebank[11] = Move.SMOKESCREEN;
			movebank[15] = Move.SLUDGE;
			break;
		case -34:
			movebank = new Move[55];
			movebank[0] = Move.POISON_STING;
			movebank[2] = Move.STRING_SHOT;
			movebank[5] = Move.LEECH_LIFE;
			movebank[9] = Move.SUPERSONIC;
			movebank[11] = Move.SMOKESCREEN;
			movebank[15] = Move.SLUDGE;
			movebank[20] = Move.POISON_POWDER;
			movebank[25] = Move.FAINT_ATTACK;
			movebank[33] = Move.BUG_BITE;
			movebank[38] = Move.FIRST_IMPRESSION;
			movebank[43] = Move.DARK_PULSE;
			movebank[48] = Move.CRUNCH;
			movebank[54] = Move.POISON_FANG;
			break;
		case -35:
			movebank = new Move[40];
			movebank[0] = Move.POISON_STING;
			movebank[2] = Move.BUZZ;
			movebank[4] = Move.SLAP;
			movebank[11] = Move.DOUBLE_SLAP;
			movebank[24] = Move.WING_ATTACK;
			movebank[27] = Move.STING;
			movebank[39] = Move.BUG_BUZZ;
			break;
		case -36:
			movebank = new Move[40];
			movebank[0] = Move.TACKLE;
			movebank[2] = Move.BUZZ;
			movebank[4] = Move.SLAP;
			movebank[12] = Move.DOUBLE_SLAP;
			movebank[19] = Move.BUG_BITE;
			movebank[24] = Move.DOUBLE_HIT;
			movebank[29] = Move.WING_ATTACK;
			movebank[34] = Move.INJECT;
			movebank[39] = Move.BUG_BUZZ;
			break;
		case -37:
			movebank = new Move[50];
			movebank[0] = Move.LICK;
			movebank[2] = Move.SMOKESCREEN;
			movebank[7] = Move.SMOG;
			movebank[12] = Move.POISON_GAS;
			movebank[18] = Move.SLUDGE;
			movebank[23] = Move.POISON_BALL;
			movebank[28] = Move.SHADOW_BALL;
			movebank[36] = Move.SLUDGE_BOMB;
			movebank[49] = Move.POISONOUS_WATER;
			break;
		case -38:
			movebank = new Move[24];
			movebank[0] = Move.NIGHT_SHADE;
			movebank[2] = Move.SCREECH;
			movebank[6] = Move.SCARY_FACE;
			movebank[8] = Move.CHARM;
			movebank[10] = Move.HYPNOSIS;
			movebank[14] = Move.CURSE;
			movebank[18] = Move.SUCKER_PUNCH;
			movebank[23] = Move.SHADOW_BALL;
			break;
		case -39:
			movebank = new Move[55];
			movebank[0] = Move.NIGHT_SHADE;
			movebank[2] = Move.SCREECH;
			movebank[6] = Move.SCARY_FACE;
			movebank[8] = Move.CHARM;
			movebank[10] = Move.HYPNOSIS;
			movebank[14] = Move.CURSE;
			movebank[18] = Move.SUCKER_PUNCH;
			movebank[23] = Move.SHADOW_BALL;
			movebank[24] = Move.IRON_DEFENSE;
			movebank[28] = Move.SPIKE_SLAM;
			movebank[30] = Move.STRONG_ARM;
			movebank[31] = Move.DOUBLE_HIT;
			movebank[33] = Move.DREAM_EATER;
			movebank[38] = Move.IRON_TAIL;
			movebank[41] = Move.NIGHTMARE;
			movebank[48] = Move.GYRO_BALL;
			movebank[54] = Move.DESTINY_BOND;
			break;
		case -40:
			movebank = new Move[18];
			movebank[0] = Move.NIGHT_SHADE;
			movebank[3] = Move.DISAPPEAR;
			movebank[7] = Move.LICK;
			movebank[11] = Move.BAWL;
			movebank[12] = Move.SCREECH;
			movebank[14] = Move.HYPNOSIS;
			movebank[17] = Move.CURSE;
			break;
		case -41:
			movebank = new Move[55];
			movebank[0] = Move.NIGHT_SHADE;
			movebank[3] = Move.DISAPPEAR;
			movebank[7] = Move.LICK;
			movebank[11] = Move.BAWL;
			movebank[12] = Move.SCREECH;
			movebank[14] = Move.HYPNOSIS;
			movebank[17] = Move.CURSE;
			movebank[21] = Move.SUCKER_PUNCH;
			movebank[24] = Move.SHADOW_BALL;
			movebank[28] = Move.FAINT_ATTACK;
			movebank[32] = Move.MINIMIZE;
			movebank[38] = Move.DARK_PULSE;
			movebank[41] = Move.DREAM_EATER;
			movebank[48] = Move.NIGHTMARE;
			movebank[51] = Move.TAKE_OVER;
			movebank[54] = Move.DESTINY_BOND;
			break;
		case -42:
			movebank = new Move[45];
			movebank[0] = Move.HEADBUTT;
			movebank[9] = Move.BLIND;
			movebank[14] = Move.SPARK;
			movebank[19] = Move.THUNDER_WAVE;
			movebank[29] = Move.LIGHTNING_HEADBUTT;
			movebank[34] = Move.THUNDERBOLT;
			movebank[44] = Move.DISCHARGE;
			break;
		case -43:
			movebank = new Move[45];
			movebank[0] = Move.SCRATCH;
			movebank[1] = Move.TAIL_WHIP;
			movebank[4] = Move.SPARK;
			movebank[7] = Move.CHARGE;
			movebank[12] = Move.THUNDER_WAVE;
			movebank[17] = Move.WHIP_SMASH;
			movebank[24] = Move.LIGHTNING_HEADBUTT;
			movebank[29] = Move.QUICK_ATTACK;
			movebank[37] = Move.DOUBLE_TEAM;
			movebank[44] = Move.THUNDERBOLT;
			break;
		case -44:
			movebank = new Move[23];
			movebank[0] = Move.SHOCK;
			movebank[3] = Move.CHARGE;
			movebank[7] = Move.SPARK;
			movebank[11] = Move.WRAP;
			movebank[18] = Move.THUNDER_WAVE;
			movebank[22] = Move.THUNDERSHOCK;
			break;
		case -45:
			movebank = new Move[55];
			movebank[0] = Move.SHOCK;
			movebank[3] = Move.CHARGE;
			movebank[7] = Move.SPARK;
			movebank[11] = Move.WRAP;
			movebank[18] = Move.THUNDER_WAVE;
			movebank[22] = Move.THUNDERSHOCK;
			movebank[30] = Move.DOUBLE_HIT;
			movebank[39] = Move.GYRO_BALL;
			movebank[47] = Move.DISCHARGE;
			movebank[54] = Move.THUNDER;
			break;
		case -46:
			movebank = new Move[26];
			movebank[0] = Move.ZAP;
			movebank[4] = Move.POUND;
			movebank[7] = Move.CHARGE;
			movebank[10] = Move.SPARK;
			movebank[13] = Move.TAIL_WHIP;
			movebank[18] = Move.SLAM;
			movebank[23] = Move.THUNDER_WAVE;
			movebank[25] = Move.WRAP;
			break;
		case -47:
			movebank = new Move[55];
			movebank[0] = Move.ZAP;
			movebank[4] = Move.POUND;
			movebank[7] = Move.CHARGE;
			movebank[10] = Move.SPARK;
			movebank[13] = Move.TAIL_WHIP;
			movebank[18] = Move.SLAM;
			movebank[23] = Move.THUNDER_WAVE;
			movebank[25] = Move.WRAP;
			movebank[30] = Move.SHOCK_WAVE;
			movebank[34] = Move.GIGA_HIT;
			movebank[35] = Move.THUNDERBOLT;
			movebank[43] = Move.DISCHARGE;
			movebank[54] = Move.HYPER_BEAM;
			break;
		case -48:
			movebank = new Move[12];
			movebank[0] = Move.TACKLE;
			movebank[2] = Move.LEER;
			movebank[4] = Move.LOW_KICK;
			movebank[7] = Move.KARATE_CHOP;
			movebank[11] = Move.TORNADO_SPIN;
			break;
		case -49:
			movebank = new Move[24];
			movebank[0] = Move.TACKLE;
			movebank[2] = Move.LEER;
			movebank[4] = Move.LOW_KICK;
			movebank[7] = Move.KARATE_CHOP;
			movebank[11] = Move.TORNADO_SPIN;
			movebank[14] = Move.SWORD_SPIN;
			movebank[17] = Move.SWORD_STAB;
			movebank[20] = Move.DOUBLE_SLICE;
			movebank[23] = Move.SWORD_SLASH;
			break;
		case -50:
			movebank = new Move[55];
			movebank[0] = Move.TACKLE;
			movebank[2] = Move.LEER;
			movebank[4] = Move.LOW_KICK;
			movebank[7] = Move.KARATE_CHOP;
			movebank[11] = Move.TORNADO_SPIN;
			movebank[14] = Move.SWORD_SPIN;
			movebank[17] = Move.SWORD_STAB;
			movebank[20] = Move.DOUBLE_SLICE;
			movebank[23] = Move.SWORD_SLASH;
			movebank[24] = Move.SHURIKEN;
			movebank[25] = Move.MACHETE_STAB;
			movebank[26] = Move.GUNSHOT;
			movebank[29] = Move.FIRST_IMPRESSION;
			movebank[34] = Move.AGILITY;
			movebank[39] = Move.FIRE_PUNCH;
			movebank[44] = Move.BLAZING_SWORD;
			movebank[49] = Move.AURA_SPHERE;
			movebank[54] = Move.CLOSE_COMBAT;
			break;
		case -51:
			movebank = new Move[25];
			movebank[0] = Move.LOW_KICK;
			movebank[1] = Move.LEER;
			movebank[6] = Move.KARATE_CHOP;
			movebank[14] = Move.SEISMIC_TOSS;
			movebank[16] = Move.REVENGE;
			movebank[21] = Move.VITAL_THROW;
			movebank[22] = Move.MACH_PUNCH;
			movebank[23] = Move.HI_JUMP_KICK;
			movebank[24] = Move.SWORD_SLICE;
			break;
		case -52:
			movebank = new Move[55];
			movebank[0] = Move.LOW_KICK;
			movebank[1] = Move.LEER;
			movebank[6] = Move.KARATE_CHOP;
			movebank[14] = Move.SEISMIC_TOSS;
			movebank[16] = Move.REVENGE;
			movebank[21] = Move.VITAL_THROW;
			movebank[22] = Move.MACH_PUNCH;
			movebank[26] = Move.COMET_PUNCH;
			movebank[33] = Move.THUNDER_PUNCH;
			movebank[34] = Move.FIRE_PUNCH;
			movebank[35] = Move.POISON_PUNCH;
			movebank[37] = Move.SKY_UPPERCUT;
			movebank[44] = Move.MEGA_PUNCH;
			movebank[54] = Move.CLOSE_COMBAT;
			break;
		case -53:
			movebank = new Move[55];
			movebank[0] = Move.LOW_KICK;
			movebank[1] = Move.LEER;
			movebank[6] = Move.KARATE_CHOP;
			movebank[14] = Move.SEISMIC_TOSS;
			movebank[16] = Move.REVENGE;
			movebank[21] = Move.VITAL_THROW;
			movebank[23] = Move.HI_JUMP_KICK;
			movebank[30] = Move.ROOT_KICK;
			movebank[33] = Move.DOUBLE_KICK;
			movebank[38] = Move.MEGA_KICK;
			movebank[45] = Move.SWEEP_KICK;
			movebank[48] = Move.THUNDER_KICK;
			movebank[54] = Move.CLOSE_COMBAT;
			break;
		case -54:
			movebank = new Move[55];
			movebank[0] = Move.LOW_KICK;
			movebank[1] = Move.LEER;
			movebank[6] = Move.KARATE_CHOP;
			movebank[14] = Move.SEISMIC_TOSS;
			movebank[16] = Move.REVENGE;
			movebank[21] = Move.VITAL_THROW;
			movebank[24] = Move.SWORD_SLICE;
			movebank[26] = Move.SWORD_STAB;
			movebank[32] = Move.DOUBLE_SLICE;
			movebank[36] = Move.SWORD_SPIN;
			movebank[39] = Move.MEGA_SWORD;
			movebank[43] = Move.BLAZING_SWORD;
			movebank[54] = Move.CLOSE_COMBAT;
			break;
		case -55:
			movebank = new Move[24];
			movebank[0] = Move.POUND;
			movebank[3] = Move.HARDEN;
			movebank[6] = Move.MUD_SLAP;
			movebank[11] = Move.EMBER;
			movebank[14] = Move.MINIMIZE;
			movebank[15] = Move.SLUDGE;
			movebank[17] = Move.POISON_GAS;
			movebank[20] = Move.MUD_BOMB;
			movebank[23] = Move.ASSURANCE;
			break;
		case -56:
			movebank = new Move[55];
			movebank[0] = Move.POUND;
			movebank[3] = Move.HARDEN;
			movebank[6] = Move.MUD_SLAP;
			movebank[11] = Move.EMBER;
			movebank[14] = Move.MINIMIZE;
			movebank[15] = Move.SLUDGE;
			movebank[17] = Move.POISON_GAS;
			movebank[20] = Move.MUD_BOMB;
			movebank[23] = Move.ASSURANCE;
			movebank[26] = Move.FLASH_CANNON;
			movebank[30] = Move.SLUDGE_BOMB;
			movebank[36] = Move.DOUBLE_HIT;
			movebank[39] = Move.BIG_BULLET;
			movebank[44] = Move.GUNK_SHOT;
			movebank[54] = Move.ROCKET;
			break;
		case -57:
			movebank = new Move[21];
			movebank[0] = Move.LEECH_LIFE;
			movebank[3] = Move.SUPERSONIC;
			movebank[4] = Move.POISON_STING;
			movebank[7] = Move.HAZE;
			movebank[10] = Move.BITE;
			movebank[14] = Move.ASTONISH;
			movebank[17] = Move.WING_ATTACK;
			movebank[20] = Move.CONFUSE_RAY;
			break;
		case -58:
			movebank = new Move[55];
			movebank[0] = Move.LEECH_LIFE;
			movebank[3] = Move.SUPERSONIC;
			movebank[4] = Move.POISON_STING;
			movebank[7] = Move.HAZE;
			movebank[10] = Move.BITE;
			movebank[14] = Move.ASTONISH;
			movebank[17] = Move.WING_ATTACK;
			movebank[20] = Move.CONFUSE_RAY;
			movebank[24] = Move.TOXIC;
			movebank[28] = Move.GUNK_SHOT;
			movebank[32] = Move.SCREECH;
			movebank[38] = Move.AIR_CUTTER;
			movebank[40] = Move.POISON_FANG;
			movebank[44] = Move.CRUNCH;
			movebank[49] = Move.CROSS_POISON;
			movebank[54] = Move.BRAVE_BIRD;
			break;
		case -59:
			movebank = new Move[25];
			movebank[0] = Move.SCRATCH;
			movebank[2] = Move.TAIL_WHIP;
			movebank[6] = Move.POISON_POWDER;
			movebank[8] = Move.VINE_WHIP;
			movebank[11] = Move.SLEEP_POWDER;
			movebank[12] = Move.MEGA_DRAIN;
			movebank[14] = Move.RAZOR_LEAF;
			movebank[18] = Move.SYNTHESIS;
			movebank[21] = Move.FIRE_FANG;
			movebank[22] = Move.THUNDER_FANG;
			movebank[23] = Move.POISON_FANG;
			movebank[24] = Move.FURY_SWIPES;
			break;
		case -60:
			movebank = new Move[55];
			movebank[0] = Move.SCRATCH;
			movebank[2] = Move.TAIL_WHIP;
			movebank[6] = Move.POISON_POWDER;
			movebank[8] = Move.VINE_WHIP;
			movebank[11] = Move.SLEEP_POWDER;
			movebank[12] = Move.MEGA_DRAIN;
			movebank[14] = Move.RAZOR_LEAF;
			movebank[18] = Move.SYNTHESIS;
			movebank[21] = Move.FIRE_FANG;
			movebank[22] = Move.THUNDER_FANG;
			movebank[23] = Move.POISON_FANG;
			movebank[24] = Move.FURY_SWIPES;
			movebank[27] = Move.ROLLOUT;
			movebank[28] = Move.SLASH;
			movebank[29] = Move.TAKE_DOWN;
			movebank[31] = Move.GIGA_DRAIN;
			movebank[36] = Move.ANCIENT_POWER;
			movebank[40] = Move.CRUNCH;
			movebank[45] = Move.SKULL_BASH;
			movebank[49] = Move.GIGA_IMPACT;
			movebank[54] = Move.SOLAR_BEAM;
			break;
		case -61:
			movebank = new Move[28];
			movebank[0] = Move.POISON_STING;
			movebank[2] = Move.SUPERSONIC;
			movebank[6] = Move.CONSTRICT;
			movebank[10] = Move.WATER_GUN;
			movebank[13] = Move.ACID;
			movebank[17] = Move.BUBBLEBEAM;
			movebank[19] = Move.WRAP;
			movebank[24] = Move.WATER_PULSE;
			movebank[27] = Move.WATER_JET;
			break;
		case -62:
			movebank = new Move[55];
			movebank[0] = Move.POISON_STING;
			movebank[2] = Move.SUPERSONIC;
			movebank[6] = Move.CONSTRICT;
			movebank[10] = Move.WATER_GUN;
			movebank[13] = Move.ACID;
			movebank[17] = Move.BUBBLEBEAM;
			movebank[19] = Move.WRAP;
			movebank[24] = Move.WATER_PULSE;
			movebank[27] = Move.WATER_JET;
			movebank[29] = Move.DOUBLE_HIT;
			movebank[32] = Move.POISON_GAS;
			movebank[36] = Move.POISON_JAB;
			movebank[40] = Move.DIVE;
			movebank[44] = Move.SLUDGE_BOMB;
			movebank[54] = Move.HYDRO_PUMP;
			break;
		case -63:
			movebank = new Move[20];
			movebank[0] = Move.TACKLE;
			movebank[2] = Move.LEER;
			movebank[5] = Move.BUBBLE;
			movebank[8] = Move.WATER_GUN;
			movebank[12] = Move.DOUBLE_TEAM;
			movebank[14] = Move.CONFUSE_RAY;
			movebank[19] = Move.WATER_PULSE;
			break;
		case -64:
			movebank = new Move[55];
			movebank[0] = Move.TACKLE;
			movebank[2] = Move.LEER;
			movebank[5] = Move.BUBBLE;
			movebank[8] = Move.WATER_GUN;
			movebank[12] = Move.DOUBLE_TEAM;
			movebank[14] = Move.CONFUSE_RAY;
			movebank[19] = Move.WATER_PULSE;
			movebank[24] = Move.BODY_SLAM;
			movebank[27] = Move.HAMMER_ARM;
			movebank[29] = Move.SPIKE_JAB;
			movebank[35] = Move.GYRO_BALL;
			movebank[38] = Move.BRINE;
			movebank[40] = Move.WATER_JET;
			movebank[44] = Move.DRAGON_PULSE;
			movebank[54] = Move.SKY_UPPERCUT;
			break;
		case -65:
			movebank = new Move[27];
			movebank[0] = Move.TACKLE;
			movebank[2] = Move.GROWL;
			movebank[5] = Move.SLAP;
			movebank[10] = Move.DOUBLE_SLAP;
			movebank[13] = Move.WATER_GUN;
			movebank[14] = Move.SUPERSONIC;
			movebank[17] = Move.HEADBUTT;
			movebank[21] = Move.WING_ATTACK;
			movebank[26] = Move.WATER_PULSE;
			break;
		case -66:
			movebank = new Move[55];
			movebank[0] = Move.TACKLE;
			movebank[2] = Move.GROWL;
			movebank[5] = Move.SLAP;
			movebank[10] = Move.DOUBLE_SLAP;
			movebank[13] = Move.WATER_GUN;
			movebank[14] = Move.SUPERSONIC;
			movebank[17] = Move.HEADBUTT;
			movebank[21] = Move.WING_ATTACK;
			movebank[26] = Move.WATER_PULSE;
			movebank[27] = Move.TWISTER;
			movebank[28] = Move.GUST;
			movebank[31] = Move.DRAGON_RAGE;
			movebank[33] = Move.FLAIL;
			movebank[38] = Move.AQUA_RING;
			movebank[40] = Move.SCREECH;
			movebank[42] = Move.AGILITY;
			movebank[47] = Move.DRAGON_RUSH;
			movebank[54] = Move.OUTRAGE;
			break;
		case -67:
			movebank = new Move[17];
			movebank[0] = Move.TACKLE;
			movebank[2] = Move.LEER;
			movebank[4] = Move.CHARM;
			movebank[7] = Move.BUBBLE;
			movebank[10] = Move.WATER_GUN;
			movebank[13] = Move.RAPID_SPIN;
			movebank[16] = Move.HEADBUTT;
			break;
		case -68:
			movebank = new Move[55];
			movebank[0] = Move.TACKLE;
			movebank[2] = Move.LEER;
			movebank[4] = Move.CHARM;
			movebank[7] = Move.BUBBLE;
			movebank[10] = Move.WATER_GUN;
			movebank[13] = Move.RAPID_SPIN;
			movebank[16] = Move.HEADBUTT;
			movebank[20] = Move.WATER_PULSE;
			movebank[23] = Move.SHELL_SMASH;
			movebank[27] = Move.BRINE;
			movebank[32] = Move.CRUNCH;
			movebank[37] = Move.SKULL_BASH;
			movebank[40] = Move.WATER_JET;
			movebank[49] = Move.DIVE;
			movebank[54] = Move.HYDRO_PUMP;
			break;
		case -69:
			movebank = new Move[45];
			movebank[0] = Move.BITE;
			movebank[1] = Move.TAIL_WHIP;
			movebank[2] = Move.SCRATCH;
			movebank[6] = Move.SCARY_FACE;
			movebank[10] = Move.HEADBUTT;
			movebank[14] = Move.THUNDER_FANG;
			movebank[18] = Move.BEAT_UP;
			movebank[22] = Move.CRUNCH;
			movebank[28] = Move.WATER_JET;
			movebank[44] = Move.SURF;
			break;
		case -70:
			movebank = new Move[55];
			movebank[0] = Move.BITE;
			movebank[4] = Move.TAIL_WHACK;
			movebank[6] = Move.RAGE;
			movebank[9] = Move.SLAM;
			movebank[14] = Move.DRAGON_BREATH;
			movebank[19] = Move.BUBBLEBEAM;
			movebank[29] = Move.CRUNCH;
			movebank[39] = Move.WATER_JET;
			movebank[49] = Move.ROCKET;
			movebank[54] = Move.HYPER_BEAM;
			break;
		case -71:
			movebank = new Move[30];
			movebank[0] = Move.HORN_ATTACK;
			movebank[14] = Move.FURY_ATTACK;
			movebank[24] = Move.ROCK_BLAST;
			movebank[29] = Move.TAKE_DOWN;
			break;
		case -72:
			movebank = new Move[55];
			movebank[0] = Move.HORN_ATTACK;
			movebank[14] = Move.FURY_ATTACK;
			movebank[24] = Move.ROCK_BLAST;
			movebank[29] = Move.TAKE_DOWN;
			movebank[29] = Move.STOMP;
			movebank[32] = Move.HORN_DRILL;
			movebank[34] = Move.MAGNITUDE;
			movebank[42] = Move.STONE_EDGE;
			movebank[46] = Move.DOUBLE_HIT;
			movebank[50] = Move.BODY_SLAM;
			movebank[54] = Move.EARTH_POWER;
			break;
		case -73:
			movebank = new Move[70];
			movebank[0] = Move.HORN_ATTACK;
			movebank[14] = Move.FURY_ATTACK;
			movebank[24] = Move.ROCK_BLAST;
			movebank[29] = Move.TAKE_DOWN;
			movebank[29] = Move.STOMP;
			movebank[32] = Move.HORN_DRILL;
			movebank[34] = Move.MAGNITUDE;
			movebank[42] = Move.STONE_EDGE;
			movebank[46] = Move.DOUBLE_HIT;
			movebank[50] = Move.BODY_SLAM;
			movebank[54] = Move.EARTH_POWER;
			movebank[55] = Move.HAMMER_ARM;
			movebank[60] = Move.EARTHQUAKE;
			movebank[68] = Move.ROCKET;
			movebank[69] = Move.HYPER_BEAM;
			break;
		case -74:
			movebank = new Move[15];
			movebank[0] = Move.IGNITE;
			movebank[14] = Move.EMBER;
			break;
		case -75:
			movebank = new Move[32];
			movebank[0] = Move.IGNITE;
			movebank[14] = Move.EMBER;
			movebank[15] = Move.FLAME_WHEEL;
			movebank[20] = Move.FURY_SWIPES;
			movebank[25] = Move.MACH_PUNCH;
			movebank[28] = Move.KARATE_CHOP;
			movebank[31] = Move.FIREBALL;
			break;
		case -76:
			movebank = new Move[65];
			movebank[0] = Move.IGNITE;
			movebank[14] = Move.EMBER;
			movebank[15] = Move.FLAME_WHEEL;
			movebank[20] = Move.FURY_SWIPES;
			movebank[25] = Move.MACH_PUNCH;
			movebank[28] = Move.KARATE_CHOP;
			movebank[31] = Move.FIREBALL;
			movebank[35] = Move.FIRE_FANG;
			movebank[39] = Move.WAKE_UP_SLAP;
			movebank[47] = Move.CROSS_POISON;
			movebank[49] = Move.CLOSE_COMBAT;
			movebank[54] = Move.ERUPTION;
			movebank[64] = Move.FIRE_BLAST;
			break;
		case -77:
			movebank = new Move[29];
			movebank[0] = Move.TACKLE;
			movebank[2] = Move.TAIL_WHIP;
			movebank[3] = Move.SLAP;
			movebank[7] = Move.DOUBLE_SLAP;
			movebank[9] = Move.EMBER;
			movebank[13] = Move.SLAM;
			movebank[18] = Move.DOUBLE_TEAM;
			movebank[21] = Move.FLAME_WHEEL;
			movebank[28] = Move.FIRE_CHARGE;
			break;
		case -78:
			movebank = new Move[53];
			movebank[0] = Move.TACKLE;
			movebank[2] = Move.TAIL_WHIP;
			movebank[3] = Move.SLAP;
			movebank[7] = Move.DOUBLE_SLAP;
			movebank[9] = Move.EMBER;
			movebank[13] = Move.SLAM;
			movebank[18] = Move.DOUBLE_TEAM;
			movebank[21] = Move.FLAME_WHEEL;
			movebank[28] = Move.FIRE_CHARGE;
			movebank[34] = Move.WING_ATTACK;
			movebank[35] = Move.BITE;
			movebank[36] = Move.FIRE_FANG;
			movebank[41] = Move.AIR_SLASH;
			movebank[44] = Move.DRAGON_BREATH;
			movebank[49] = Move.FLAMETHROWER;
			movebank[52] = Move.CRUNCH;
			break;
		case -79:
			movebank = new Move[75];
			movebank[0] = Move.TACKLE;
			movebank[2] = Move.TAIL_WHIP;
			movebank[3] = Move.SLAP;
			movebank[7] = Move.DOUBLE_SLAP;
			movebank[9] = Move.EMBER;
			movebank[13] = Move.SLAM;
			movebank[18] = Move.DOUBLE_TEAM;
			movebank[21] = Move.FLAME_WHEEL;
			movebank[28] = Move.FIRE_CHARGE;
			movebank[34] = Move.WING_ATTACK;
			movebank[35] = Move.BITE;
			movebank[36] = Move.FIRE_FANG;
			movebank[41] = Move.AIR_SLASH;
			movebank[44] = Move.DRAGON_BREATH;
			movebank[49] = Move.FLAMETHROWER;
			movebank[52] = Move.CRUNCH;
			movebank[55] = Move.IRON_TAIL;
			movebank[59] = Move.DRAGON_RUSH;
			movebank[62] = Move.OUTRAGE;
			movebank[66] = Move.DRACO_METEOR;
			movebank[70] = Move.FIRE_BLAST;
			movebank[74] = Move.HYPER_BEAM;
			break;
		case -80:
			movebank = new Move[25];
			movebank[0] = Move.TACKLE;
			movebank[4] = Move.LOW_KICK;
			movebank[7] = Move.HEADBUTT;
			movebank[8] = Move.IRON_DEFENSE;
			movebank[12] = Move.EMBER;
			movebank[14] = Move.SCREECH;
			movebank[19] = Move.FLAME_WHEEL;
			movebank[24] = Move.HEAT_WAVE;
			break;
		case -81:
			movebank = new Move[55];
			movebank[0] = Move.TACKLE;
			movebank[4] = Move.LOW_KICK;
			movebank[7] = Move.HEADBUTT;
			movebank[8] = Move.IRON_DEFENSE;
			movebank[12] = Move.EMBER;
			movebank[14] = Move.SCREECH;
			movebank[19] = Move.FLAME_WHEEL;
			movebank[24] = Move.HEAT_WAVE;
			movebank[27] = Move.TAKE_DOWN;
			movebank[30] = Move.FIRE_SPIN;
			movebank[36] = Move.MAGNET_RISE;
			movebank[42] = Move.GIGA_IMPACT;
			movebank[44] = Move.SUPER_CHARGE;
			movebank[48] = Move.FLAMETHROWER;
			movebank[50] = Move.GYRO_BALL;
			movebank[54] = Move.BLAST_BURN;
			break;
		case -82:
			movebank = new Move[25];
			movebank[0] = Move.BRINE;
			movebank[4] = Move.DISAPPEAR;
			movebank[14] = Move.WATER_PULSE;
			movebank[24] = Move.SHADOW_SNEAK;
			break;
		case -83:
			movebank = new Move[55];
			movebank[0] = Move.BRINE;
			movebank[4] = Move.DISAPPEAR;
			movebank[14] = Move.WATER_PULSE;
			movebank[24] = Move.SHADOW_SNEAK;
			movebank[25] = Move.CHARGE;
			movebank[26] = Move.DISCHARGE;
			movebank[29] = Move.WING_ATTACK;
			movebank[34] = Move.AEROBLAST;
			movebank[44] = Move.THUNDERBOLT;
			movebank[49] = Move.SHADOW_BALL;
			movebank[54] = Move.THUNDER;
			break;
		case -84:
			movebank = new Move[27];
			movebank[0] = Move.PECK;
			movebank[7] = Move.EMBER;
			movebank[12] = Move.WILL_O_WISP;
			movebank[17] = Move.DRAGON_RAGE;
			movebank[20] = Move.FURY_ATTACK;
			movebank[22] = Move.FLAME_WHEEL;
			movebank[26] = Move.WING_ATTACK;
			break;
		case -85:
			movebank = new Move[55];
			movebank[0] = Move.PECK;
			movebank[7] = Move.EMBER;
			movebank[12] = Move.WILL_O_WISP;
			movebank[17] = Move.DRAGON_RAGE;
			movebank[20] = Move.FURY_ATTACK;
			movebank[22] = Move.FLAME_WHEEL;
			movebank[26] = Move.WING_ATTACK;
			movebank[30] = Move.HI_JUMP_KICK;
			movebank[36] = Move.ROOST;
			movebank[41] = Move.FLAMETHROWER;
			movebank[44] = Move.DRILL_PECK;
			movebank[50] = Move.BRAVE_BIRD;
			movebank[54] = Move.FIRE_BLAST;
			break;
		case -86:
			movebank = new Move[30];
			movebank[0] = Move.POISON_STING;
			movebank[1] = Move.CONSTRICT;
			movebank[2] = Move.STRING_SHOT;
			movebank[3] = Move.SCARY_FACE;
			movebank[9] = Move.BUG_BITE;
			movebank[14] = Move.NIGHT_SHADE;
			movebank[19] = Move.SHADOW_SNEAK;
			movebank[24] = Move.FAINT_ATTACK;
			movebank[29] = Move.BEAT_UP;
			break;
		case -87:
			movebank = new Move[55];
			movebank[0] = Move.POISON_STING;
			movebank[1] = Move.CONSTRICT;
			movebank[2] = Move.STRING_SHOT;
			movebank[3] = Move.SCARY_FACE;
			movebank[9] = Move.BUG_BITE;
			movebank[14] = Move.NIGHT_SHADE;
			movebank[19] = Move.SHADOW_SNEAK;
			movebank[24] = Move.FAINT_ATTACK;
			movebank[29] = Move.BEAT_UP;
			movebank[35] = Move.FIRE_FANG;
			movebank[36] = Move.MAGIC_FANG;
			movebank[37] = Move.THUNDER_FANG;
			movebank[38] = Move.POISON_FANG;
			movebank[40] = Move.SHADOW_BALL;
			movebank[48] = Move.CROSS_POISON;
			movebank[49] = Move.CRUNCH;
			movebank[54] = Move.PERISH_SONG;
			break;
		case -88:
			movebank = new Move[40];
			movebank[0] = Move.CUT;
			movebank[4] = Move.FURY_CUTTER;
			movebank[9] = Move.FALSE_SWIPE;
			movebank[14] = Move.AGILITY;
			movebank[19] = Move.FIRST_IMPRESSION;
			movebank[24] = Move.SLASH;
			movebank[29] = Move.X_SCIZZOR;
			movebank[34] = Move.SUPERPOWER;
			movebank[39] = Move.GYRO_BALL;
			break;
		case -89:
			movebank = new Move[30];
			movebank[0] = Move.NIGHT_SLASH;
			movebank[4] = Move.BEAT_UP;
			movebank[9] = Move.FAINT_ATTACK;
			movebank[14] = Move.BITE;
			movebank[19] = Move.DARK_VOID;
			movebank[24] = Move.DARK_PULSE;
			movebank[29] = Move.CRUNCH;
			break;
		case -90:
			movebank = new Move[56];
			movebank[0] = Move.NIGHT_SLASH;
			movebank[4] = Move.BEAT_UP;
			movebank[9] = Move.FAINT_ATTACK;
			movebank[14] = Move.BITE;
			movebank[19] = Move.DARK_VOID;
			movebank[24] = Move.DARK_PULSE;
			movebank[29] = Move.CRUNCH;
			movebank[30] = Move.STOMP;
			movebank[35] = Move.WILL_O_WISP;
			movebank[40] = Move.SUCKER_PUNCH;
			movebank[45] = Move.ZEN_HEADBUTT;
			movebank[50] = Move.SHADOW_BALL;
			movebank[55] = Move.CLOSE_COMBAT;
			break;
		case -91:
			movebank = new Move[55];
			movebank[0] = Move.SCRATCH;
			movebank[2] = Move.BEAT_UP;
			movebank[8] = Move.SHADOW_SNEAK;
			movebank[9] = Move.EMBER;
			movebank[15] = Move.DIVE;
			movebank[20] = Move.ROCK_BLAST;
			movebank[22] = Move.LEAF_BALL;
			movebank[34] = Move.SHADOW_BALL;
			movebank[44] = Move.VOLT_TACKLE;
			movebank[54] = Move.GALAXY_ATTACK;
			break;
		case -92:
			movebank = new Move[1];
			movebank[0] = Move.ELECTROEXPLOSION;
			break;
		case -93:
			movebank = new Move[30];
			movebank[0] = Move.EMBER;
			movebank[1] = Move.ASTONISH;
			movebank[4] = Move.SMOKESCREEN;
			movebank[9] = Move.FIREBALL;
			movebank[14] = Move.CURSE;
			movebank[19] = Move.FLAMETHROWER;
			movebank[29] = Move.SHADOW_BALL;
			break;
		case -94:
			movebank = new Move[70];
			movebank[0] = Move.EMBER;
			movebank[1] = Move.ASTONISH;
			movebank[4] = Move.SMOKESCREEN;
			movebank[9] = Move.FIREBALL;
			movebank[14] = Move.CURSE;
			movebank[19] = Move.FLAMETHROWER;
			movebank[29] = Move.SHADOW_BALL;
			movebank[31] = Move.FIRE_CHARGE;
			movebank[34] = Move.EXPLOSION;
			movebank[39] = Move.FIRE_BLAST;
			movebank[41] = Move.DESTINY_BOND;
			movebank[44] = Move.DARK_VOID;
			movebank[49] = Move.NIGHTMARE;
			movebank[54] = Move.TAKE_OVER;
			movebank[69] = Move.BLAST_BURN;
			break;
		case -95:
			movebank = new Move[5];
			movebank[0] = Move.DEFENSE_CURL;
			movebank[1] = Move.SCRATCH;
			movebank[2] = Move.GROWL;
			movebank[3] = Move.IRON_DEFENSE;
			movebank[4] = Move.GYRO_BALL;
			break;
		case -96:
			movebank = new Move[50];
			movebank[0] = Move.DEFENSE_CURL;
			movebank[1] = Move.SCRATCH;
			movebank[2] = Move.GROWL;
			movebank[3] = Move.IRON_DEFENSE;
			movebank[4] = Move.GYRO_BALL;
			movebank[9] = Move.IRON_HEAD;
			movebank[19] = Move.FURY_SWIPES;
			movebank[23] = Move.SLAM;
			movebank[26] = Move.ROLLOUT;
			movebank[34] = Move.FLASH_CANNON;
			movebank[41] = Move.GIGA_IMPACT;
			movebank[49] = Move.GYRO_BALL;
			break;
		case -97:
			movebank = new Move[24];
			movebank[0] = Move.GUNSHOT;
			movebank[1] = Move.SCREECH;
			movebank[9] = Move.SHELL_SMASH;
			movebank[14] = Move.HEADBUTT;
			movebank[19] = Move.AUTOMOTIZE;
			movebank[23] = Move.LOCK_ON;
			break;
		case -98:
			movebank = new Move[48];
			movebank[0] = Move.GUNSHOT;
			movebank[1] = Move.SCREECH;
			movebank[9] = Move.SHELL_SMASH;
			movebank[14] = Move.HEADBUTT;
			movebank[19] = Move.AUTOMOTIZE;
			movebank[23] = Move.LOCK_ON;
			movebank[24] = Move.IRON_DEFENSE;
			movebank[31] = Move.TAKE_DOWN;
			movebank[39] = Move.REBOOT;
			movebank[47] = Move.METAL_SOUND;
			break;
		case -99:
			movebank = new Move[60];
			movebank[0] = Move.GUNSHOT;
			movebank[1] = Move.SCREECH;
			movebank[9] = Move.SHELL_SMASH;
			movebank[14] = Move.HEADBUTT;
			movebank[19] = Move.AUTOMOTIZE;
			movebank[23] = Move.LOCK_ON;
			movebank[24] = Move.IRON_DEFENSE;
			movebank[31] = Move.TAKE_DOWN;
			movebank[39] = Move.REBOOT;
			movebank[47] = Move.METAL_SOUND;
			movebank[49] = Move.AUTO_SHOT;
			movebank[50] = Move.FLAMETHROWER;
			movebank[54] = Move.GIGA_IMPACT;
			movebank[59] = Move.GYRO_BALL;
			break;
		case -100:
			movebank = new Move[33];
			movebank[0] = Move.POUND;
			movebank[1] = Move.LEER;
			movebank[4] = Move.THUNDERSHOCK;
			movebank[7] = Move.WRAP;
			movebank[13] = Move.DRAGON_RAGE;
			movebank[19] = Move.WATER_PULSE;
			movebank[22] = Move.SLAM;
			movebank[26] = Move.DRAGON_BREATH;
			movebank[32] = Move.THUNDERBOLT;
			break;
		case -101:
			movebank = new Move[53];
			movebank[0] = Move.POUND;
			movebank[1] = Move.LEER;
			movebank[4] = Move.THUNDERSHOCK;
			movebank[7] = Move.WRAP;
			movebank[13] = Move.DRAGON_RAGE;
			movebank[19] = Move.WATER_PULSE;
			movebank[22] = Move.SLAM;
			movebank[26] = Move.DRAGON_BREATH;
			movebank[32] = Move.THUNDERBOLT;
			movebank[38] = Move.IRON_HEAD;
			movebank[40] = Move.DRAGON_PULSE;
			movebank[45] = Move.DRAGON_RUSH;
			movebank[52] = Move.HYDRO_PUMP;
			break;
		case -102:
			movebank = new Move[80];
			movebank[0] = Move.POUND;
			movebank[1] = Move.LEER;
			movebank[4] = Move.THUNDERSHOCK;
			movebank[7] = Move.WRAP;
			movebank[13] = Move.DRAGON_RAGE;
			movebank[19] = Move.WATER_PULSE;
			movebank[22] = Move.SLAM;
			movebank[26] = Move.DRAGON_BREATH;
			movebank[32] = Move.THUNDERBOLT;
			movebank[38] = Move.IRON_HEAD;
			movebank[40] = Move.DRAGON_PULSE;
			movebank[45] = Move.DRAGON_RUSH;
			movebank[52] = Move.HYDRO_PUMP;
			movebank[57] = Move.GALAXY_ATTACK;
			movebank[60] = Move.SKY_ATTACK;
			movebank[64] = Move.HYPER_BEAM;
			movebank[67] = Move.THUNDER;
			movebank[72] = Move.OUTRAGE;
			movebank[79] = Move.DRACO_METEOR;
			break;
		case -103:
			movebank = new Move[55];
			movebank[0] = Move.ANCIENT_POWER;
			movebank[4] = Move.THUNDERBOLT;
			movebank[7] = Move.DRAGON_RAGE;
			movebank[14] = Move.WING_ATTACK;
			movebank[23] = Move.FLY;
			movebank[35] = Move.FLAMETHROWER;
			movebank[42] = Move.CLOSE_COMBAT;
			movebank[45] = Move.HYPER_BEAM;
			movebank[49] = Move.DRAGON_RUSH;
			movebank[54] = Move.SUPERPOWER;
			break;
		case -104:
			movebank = new Move[55];
			movebank[0] = Move.ANCIENT_POWER;
			movebank[4] = Move.THUNDERBOLT;
			movebank[7] = Move.DRAGON_RAGE;
			movebank[14] = Move.WING_ATTACK;
			movebank[23] = Move.FLY;
			movebank[35] = Move.FLAMETHROWER;
			movebank[42] = Move.AEROBLAST;
			movebank[45] = Move.HYPER_BEAM;
			movebank[49] = Move.DRAGON_RUSH;
			movebank[54] = Move.SKY_ATTACK;
			break;
		case -105:
			movebank = new Move[55];
			movebank[0] = Move.ANCIENT_POWER;
			movebank[4] = Move.THUNDERBOLT;
			movebank[7] = Move.DRAGON_RAGE;
			movebank[14] = Move.WING_ATTACK;
			movebank[23] = Move.FLY;
			movebank[35] = Move.FLAMETHROWER;
			movebank[42] = Move.FIRE_BLAST;
			movebank[45] = Move.HYPER_BEAM;
			movebank[49] = Move.DRAGON_RUSH;
			movebank[54] = Move.OVERHEAT;
			break;
		case -106:
			movebank = new Move[55];
			movebank[0] = Move.SWIFT;
			movebank[9] = Move.MAGIC_BLAST;
			movebank[14] = Move.AQUA_RING;
			movebank[19] = Move.DISAPPEAR;
			movebank[24] = Move.EXTRAORDINARY;
			movebank[39] = Move.MAGIC_REFLECT;
			movebank[44] = Move.MAGIC_TOMB;
			movebank[54] = Move.STAR_STORM;
			break;
		case -107:
			movebank = new Move[24];
			movebank[0] = Move.DOUBLE_TEAM;
			movebank[1] = Move.QUICK_ATTACK;
			movebank[2] = Move.EMBER;
			movebank[3] = Move.IGNITE;
			movebank[4] = Move.SMOKESCREEN;
			movebank[7] = Move.CONFUSION;
			movebank[9] = Move.GLARE;
			movebank[13] = Move.HYPNOSIS;
			movebank[18] = Move.SWIFT;
			movebank[23] = Move.FLAME_WHEEL;
			break;
		case -108:
			movebank = new Move[55];
			movebank[0] = Move.DOUBLE_TEAM;
			movebank[1] = Move.QUICK_ATTACK;
			movebank[2] = Move.EMBER;
			movebank[3] = Move.IGNITE;
			movebank[4] = Move.SMOKESCREEN;
			movebank[7] = Move.CONFUSION;
			movebank[9] = Move.GLARE;
			movebank[13] = Move.HYPNOSIS;
			movebank[18] = Move.SWIFT;
			movebank[23] = Move.FLAME_WHEEL;
			movebank[27] = Move.FIRE_DASH;
			movebank[32] = Move.PSYCHO_BLADE;
			movebank[37] = Move.GIGA_HIT;
			movebank[40] = Move.ERUPTION;
			movebank[48] = Move.COMET_CRASH;
			movebank[54] = Move.BLAST_FLAME;
			break;
		case -109:
			movebank = new Move[30];
			movebank[0] = Move.GUST;
			movebank[4] = Move.SPARK;
			movebank[10] = Move.DISAPPEAR;
			movebank[12] = Move.AIR_CUTTER;
			movebank[14] = Move.SWIFT;
			movebank[17] = Move.HYPNOSIS;
			movebank[22] = Move.DREAM_EATER;
			movebank[24] = Move.FLAME_WHEEL;
			movebank[29] = Move.ROCK_BLAST;
			break;
		case -110:
			movebank = new Move[50];
			movebank[0] = Move.GUST;
			movebank[4] = Move.SPARK;
			movebank[10] = Move.DISAPPEAR;
			movebank[12] = Move.AIR_CUTTER;
			movebank[14] = Move.SWIFT;
			movebank[17] = Move.HYPNOSIS;
			movebank[22] = Move.DREAM_EATER;
			movebank[24] = Move.FLAME_WHEEL;
			movebank[29] = Move.ROCK_BLAST;
			movebank[32] = Move.WATER_JET;
			movebank[36] = Move.MOONLIGHT;
			movebank[41] = Move.PHASE_SHIFT;
			movebank[45] = Move.SYNTHESIS;
			movebank[49] = Move.BLACK_HOLE;
			break;
		case -111:
			movebank = new Move[75];
			movebank[0] = Move.GUST;
			movebank[4] = Move.SPARK;
			movebank[10] = Move.DISAPPEAR;
			movebank[12] = Move.AIR_CUTTER;
			movebank[14] = Move.SWIFT;
			movebank[17] = Move.HYPNOSIS;
			movebank[22] = Move.DREAM_EATER;
			movebank[24] = Move.FLAME_WHEEL;
			movebank[29] = Move.ROCK_BLAST;
			movebank[32] = Move.WATER_JET;
			movebank[36] = Move.MOONLIGHT;
			movebank[41] = Move.PHASE_SHIFT;
			movebank[45] = Move.SYNTHESIS;
			movebank[48] = Move.BLACK_HOLE;
			movebank[49] = Move.COMET_CRASH;
			movebank[55] = Move.STAR_STORM;
			movebank[64] = Move.GALAXY_ATTACK;
			movebank[69] = Move.HYPER_BEAM;
			movebank[74] = Move.MAGIC_CRASH;
			break;
		case -112:
			movebank = new Move[25];
			movebank[0] = Move.LEER;
			movebank[1] = Move.TACKLE;
			movebank[4] = Move.ODOR_SLEUTH;
			movebank[7] = Move.BITE;
			movebank[11] = Move.FURY_SWIPES;
			movebank[14] = Move.TAKE_DOWN;
			movebank[19] = Move.SLAM;
			movebank[23] = Move.CRUNCH;
			movebank[24] = Move.EXTRAORDINARY;
			break;
		case -113:
			movebank = new Move[65];
			movebank[0] = Move.LEER;
			movebank[1] = Move.TACKLE;
			movebank[4] = Move.ODOR_SLEUTH;
			movebank[7] = Move.BITE;
			movebank[11] = Move.FURY_SWIPES;
			movebank[14] = Move.TAKE_DOWN;
			movebank[19] = Move.SLAM;
			movebank[23] = Move.CRUNCH;
			movebank[24] = Move.EXTRAORDINARY;
			movebank[24] = Move.LEECH_SEED;
			movebank[27] = Move.MEGA_DRAIN;
			movebank[32] = Move.LEAF_BLADE;
			movebank[36] = Move.TAKE_DOWN;
			movebank[42] = Move.EARTHQUAKE;
			movebank[48] = Move.GIGA_DRAIN;
			movebank[54] = Move.GRASS_KNOT;
			movebank[64] = Move.LEAF_STORM;
			break;
		case -114:
			movebank = new Move[65];
			movebank[0] = Move.LEER;
			movebank[1] = Move.TACKLE;
			movebank[4] = Move.ODOR_SLEUTH;
			movebank[7] = Move.BITE;
			movebank[11] = Move.FURY_SWIPES;
			movebank[14] = Move.TAKE_DOWN;
			movebank[19] = Move.SLAM;
			movebank[23] = Move.CRUNCH;
			movebank[24] = Move.EXTRAORDINARY;
			movebank[24] = Move.FLAME_WHEEL;
			movebank[29] = Move.FIRE_CHARGE;
			movebank[33] = Move.FIRE_FANG;
			movebank[37] = Move.TAKE_DOWN;
			movebank[42] = Move.FLAMETHROWER;
			movebank[48] = Move.THUNDERBOLT;
			movebank[54] = Move.ERUPTION;
			movebank[60] = Move.STONE_EDGE;
			movebank[64] = Move.FIRE_BLAST;
			break;
		case -115:
			movebank = new Move[65];
			movebank[0] = Move.LEER;
			movebank[1] = Move.TACKLE;
			movebank[4] = Move.ODOR_SLEUTH;
			movebank[7] = Move.BITE;
			movebank[11] = Move.FURY_SWIPES;
			movebank[14] = Move.TAKE_DOWN;
			movebank[19] = Move.SLAM;
			movebank[23] = Move.CRUNCH;
			movebank[24] = Move.EXTRAORDINARY;
			movebank[24] = Move.WATER_JET;
			movebank[30] = Move.WATER_PULSE;
			movebank[34] = Move.TAKE_DOWN;
			movebank[41] = Move.CROSS_POISON;
			movebank[45] = Move.BRINE;
			movebank[48] = Move.EARTHQUAKE;
			movebank[54] = Move.TIDAL_WAVE;
			movebank[64] = Move.HYDRO_PUMP;
			break;
		case -116:
			movebank = new Move[65];
			movebank[0] = Move.LEER;
			movebank[1] = Move.TACKLE;
			movebank[4] = Move.ODOR_SLEUTH;
			movebank[7] = Move.BITE;
			movebank[11] = Move.FURY_SWIPES;
			movebank[14] = Move.TAKE_DOWN;
			movebank[19] = Move.SLAM;
			movebank[23] = Move.CRUNCH;
			movebank[24] = Move.EXTRAORDINARY;
			movebank[24] = Move.CROSS_POISON;
			movebank[29] = Move.POISON_GAS;
			movebank[33] = Move.WATER_JET;
			movebank[38] = Move.POISON_JAB;
			movebank[42] = Move.TAKE_DOWN;
			movebank[47] = Move.DARK_PULSE;
			movebank[54] = Move.TOXIC;
			movebank[60] = Move.LEAF_BLADE;
			movebank[64] = Move.GUNK_SHOT;
			break;
		case -117:
			movebank = new Move[65];
			movebank[0] = Move.LEER;
			movebank[1] = Move.TACKLE;
			movebank[4] = Move.ODOR_SLEUTH;
			movebank[7] = Move.BITE;
			movebank[11] = Move.FURY_SWIPES;
			movebank[14] = Move.TAKE_DOWN;
			movebank[19] = Move.SLAM;
			movebank[23] = Move.CRUNCH;
			movebank[24] = Move.EXTRAORDINARY;
			movebank[24] = Move.CHARGE;
			movebank[25] = Move.DISCHARGE;
			movebank[32] = Move.ELECTROBALL;
			movebank[36] = Move.TAKE_DOWN;
			movebank[44] = Move.THUNDERBOLT;
			movebank[49] = Move.IRON_TAIL;
			movebank[52] = Move.VOLT_TACKLE;
			movebank[56] = Move.BRINE;
			movebank[64] = Move.THUNDER;
			break;
		case -118:
			movebank = new Move[65];
			movebank[0] = Move.LEER;
			movebank[1] = Move.TACKLE;
			movebank[4] = Move.ODOR_SLEUTH;
			movebank[7] = Move.BITE;
			movebank[11] = Move.FURY_SWIPES;
			movebank[14] = Move.TAKE_DOWN;
			movebank[19] = Move.SLAM;
			movebank[23] = Move.CRUNCH;
			movebank[24] = Move.EXTRAORDINARY;
			movebank[24] = Move.ROCK_TOMB;
			movebank[30] = Move.ROCK_BLAST;
			movebank[33] = Move.ROLLOUT;
			movebank[36] = Move.ROCK_SLIDE;
			movebank[41] = Move.TAKE_DOWN;
			movebank[44] = Move.AEROBLAST;
			movebank[49] = Move.STONE_EDGE;
			movebank[53] = Move.HEAD_SMASH;
			movebank[64] = Move.ROCK_WRECKER;
			break;
		case -119:
			movebank = new Move[65];
			movebank[0] = Move.LEER;
			movebank[1] = Move.TACKLE;
			movebank[4] = Move.ODOR_SLEUTH;
			movebank[7] = Move.BITE;
			movebank[11] = Move.FURY_SWIPES;
			movebank[14] = Move.TAKE_DOWN;
			movebank[19] = Move.SLAM;
			movebank[23] = Move.CRUNCH;
			movebank[24] = Move.EXTRAORDINARY;
			movebank[24] = Move.SHADOW_SNEAK;
			movebank[26] = Move.ASSURANCE;
			movebank[32] = Move.BEAT_UP;
			movebank[38] = Move.FAINT_ATTACK;
			movebank[40] = Move.MINIMIZE;
			movebank[41] = Move.STAR_STORM;
			movebank[46] = Move.DARK_PULSE;
			movebank[51] = Move.FLAMETHROWER;
			movebank[64] = Move.DESTINY_BOND;
			break;
		case -120:
			movebank = new Move[65];
			movebank[0] = Move.LEER;
			movebank[1] = Move.TACKLE;
			movebank[4] = Move.ODOR_SLEUTH;
			movebank[7] = Move.BITE;
			movebank[11] = Move.FURY_SWIPES;
			movebank[14] = Move.TAKE_DOWN;
			movebank[19] = Move.SLAM;
			movebank[23] = Move.CRUNCH;
			movebank[24] = Move.EXTRAORDINARY;
			movebank[24] = Move.BULLET_PUNCH;
			movebank[30] = Move.IRON_DEFENSE;
			movebank[33] = Move.MAGNET_RISE;
			movebank[35] = Move.TAKE_DOWN;
			movebank[42] = Move.IRON_TAIL;
			movebank[47] = Move.WATER_PULSE;
			movebank[54] = Move.FLASH_CANNON;
			movebank[64] = Move.GYRO_BALL;
			break;
		case -121:
			movebank = new Move[65];
			movebank[0] = Move.LEER;
			movebank[1] = Move.TACKLE;
			movebank[4] = Move.ODOR_SLEUTH;
			movebank[7] = Move.BITE;
			movebank[11] = Move.FURY_SWIPES;
			movebank[14] = Move.TAKE_DOWN;
			movebank[19] = Move.SLAM;
			movebank[23] = Move.CRUNCH;
			movebank[24] = Move.EXTRAORDINARY;
			movebank[24] = Move.MACH_PUNCH;
			movebank[25] = Move.LOW_KICK;
			movebank[29] = Move.SWAGGER;
			movebank[33] = Move.BRICK_BREAK;
			movebank[38] = Move.TAKE_DOWN;
			movebank[42] = Move.ASSURANCE;
			movebank[44] = Move.HI_JUMP_KICK;
			movebank[48] = Move.ROCK_SLIDE;
			movebank[54] = Move.BULK_UP;
			movebank[64] = Move.CLOSE_COMBAT;
			break;
		case -122:
			movebank = new Move[65];
			movebank[0] = Move.LEER;
			movebank[1] = Move.TACKLE;
			movebank[4] = Move.ODOR_SLEUTH;
			movebank[7] = Move.BITE;
			movebank[11] = Move.FURY_SWIPES;
			movebank[14] = Move.TAKE_DOWN;
			movebank[19] = Move.SLAM;
			movebank[23] = Move.CRUNCH;
			movebank[24] = Move.EXTRAORDINARY;
			movebank[24] = Move.DRAGON_RAGE;
			movebank[28] = Move.DRAGON_BREATH;
			movebank[33] = Move.TAKE_DOWN;
			movebank[36] = Move.DRAGON_DANCE;
			movebank[42] = Move.IRON_TAIL;
			movebank[47] = Move.DRAGON_PULSE;
			movebank[53] = Move.DRAGON_RUSH;
			movebank[59] = Move.OUTRAGE;
			movebank[64] = Move.DRACO_METEOR;
			break;
		case -123:
			movebank = new Move[65];
			movebank[0] = Move.LEER;
			movebank[1] = Move.TACKLE;
			movebank[4] = Move.ODOR_SLEUTH;
			movebank[7] = Move.BITE;
			movebank[11] = Move.FURY_SWIPES;
			movebank[14] = Move.TAKE_DOWN;
			movebank[19] = Move.SLAM;
			movebank[23] = Move.CRUNCH;
			movebank[24] = Move.EXTRAORDINARY;
			movebank[26] = Move.SWIFT;
			movebank[32] = Move.PSYCHO_BLADE;
			movebank[36] = Move.TAKE_DOWN;
			movebank[41] = Move.FLAMETHROWER;
			movebank[44] = Move.AURA_SPHERE;
			movebank[49] = Move.MAGIC_REFLECT;
			movebank[55] = Move.MAGIC_TOMB;
			movebank[60] = Move.STAR_STORM;
			movebank[64] = Move.GALAXY_ATTACK;
			break;
		case -124:
			movebank = new Move[12];
			movebank[0] = Move.TACKLE;
			movebank[1] = Move.LEER;
			movebank[4] = Move.LEAF_SLAP;
			movebank[7] = Move.DEFENSE_CURL;
			movebank[11] = Move.ABSORB;
			break;
		case -125:
			movebank = new Move[32];
			movebank[0] = Move.TACKLE;
			movebank[1] = Move.LEER;
			movebank[4] = Move.LEAF_SLAP;
			movebank[7] = Move.DEFENSE_CURL;
			movebank[11] = Move.ABSORB;
			movebank[16] = Move.HAMMER_ARM;
			movebank[19] = Move.MEGA_DRAIN;
			movebank[21] = Move.DOUBLE_PUNCH;
			movebank[24] = Move.ROCK_TOMB;
			movebank[27] = Move.LEAF_BLADE;
			movebank[31] = Move.SYNTHESIS;
			break;
		case -126:
			movebank = new Move[70];
			movebank[0] = Move.TACKLE;
			movebank[1] = Move.LEER;
			movebank[4] = Move.LEAF_SLAP;
			movebank[7] = Move.DEFENSE_CURL;
			movebank[11] = Move.ABSORB;
			movebank[16] = Move.HAMMER_ARM;
			movebank[19] = Move.MEGA_DRAIN;
			movebank[21] = Move.DOUBLE_PUNCH;
			movebank[24] = Move.ROCK_TOMB;
			movebank[27] = Move.LEAF_BLADE;
			movebank[31] = Move.SYNTHESIS;
			movebank[36] = Move.GIGA_DRAIN;
			movebank[39] = Move.POISON_FANG;
			movebank[43] = Move.WRING_OUT;
			movebank[47] = Move.NEEDLE_SPRAY;
			movebank[49] = Move.DUAL_STAB;
			movebank[52] = Move.DOUBLE_TEAM;
			movebank[57] = Move.LEAF_STORM;
			movebank[62] = Move.CLOSE_COMBAT;
			movebank[69] = Move.FRENZY_PLANT;
			break;
		case -127:
			movebank = new Move[13];
			movebank[0] = Move.SCRATCH;
			movebank[1] = Move.TAIL_WHIP;
			movebank[6] = Move.SPARK;
			movebank[8] = Move.SMOKESCREEN;
			movebank[12] = Move.EMBER;
			break;
		case -128:
			movebank = new Move[28];
			movebank[0] = Move.SCRATCH;
			movebank[1] = Move.TAIL_WHIP;
			movebank[6] = Move.SPARK;
			movebank[8] = Move.SMOKESCREEN;
			movebank[12] = Move.EMBER;
			movebank[16] = Move.QUICK_ATTACK;
			movebank[18] = Move.FLAME_WHEEL;
			movebank[20] = Move.AGILITY;
			movebank[23] = Move.CRUNCH;
			movebank[27] = Move.FIRE_CHARGE;
			break;
		case -129:
			movebank = new Move[70];
			movebank[0] = Move.SCRATCH;
			movebank[1] = Move.TAIL_WHIP;
			movebank[6] = Move.SPARK;
			movebank[8] = Move.SMOKESCREEN;
			movebank[12] = Move.EMBER;
			movebank[16] = Move.QUICK_ATTACK;
			movebank[18] = Move.FLAME_WHEEL;
			movebank[20] = Move.AGILITY;
			movebank[23] = Move.CRUNCH;
			movebank[27] = Move.FIRE_CHARGE;
			movebank[36] = Move.FLAMETHROWER;
			movebank[41] = Move.BOUNCE;
			movebank[44] = Move.AUTOMOTIZE;
			movebank[49] = Move.FLAME_BURST;
			movebank[53] = Move.EXTREME_SPEED;
			movebank[57] = Move.DRILL_RUN;
			movebank[61] = Move.FRUSTERATION;
			movebank[65] = Move.FIRE_BLAST;
			movebank[69] = Move.FLARE_BLITZ;
			break;
		case -130:
			movebank = new Move[16];
			movebank[0] = Move.POUND;
			movebank[1] = Move.LEER;
			movebank[6] = Move.BUBBLE;
			movebank[8] = Move.BUBBLEBEAM;
			movebank[12] = Move.BITE;
			movebank[15] = Move.SLAM;
			break;
		case -131:
			movebank = new Move[34];
			movebank[0] = Move.POUND;
			movebank[1] = Move.LEER;
			movebank[6] = Move.BUBBLE;
			movebank[8] = Move.BUBBLEBEAM;
			movebank[12] = Move.BITE;
			movebank[15] = Move.SLAM;
			movebank[19] = Move.WATER_PULSE;
			movebank[24] = Move.CRUNCH;
			movebank[27] = Move.AQUA_TAIL;
			movebank[30] = Move.HYPER_FANG;
			movebank[33] = Move.TAKE_DOWN;
			break;
		case -132:
			movebank = new Move[70];
			movebank[0] = Move.POUND;
			movebank[1] = Move.LEER;
			movebank[6] = Move.BUBBLE;
			movebank[8] = Move.BUBBLEBEAM;
			movebank[12] = Move.BITE;
			movebank[15] = Move.SLAM;
			movebank[19] = Move.WATER_PULSE;
			movebank[24] = Move.CRUNCH;
			movebank[27] = Move.AQUA_TAIL;
			movebank[30] = Move.HYPER_FANG;
			movebank[33] = Move.TAKE_DOWN;
			movebank[37] = Move.BEAT_UP;
			movebank[41] = Move.AQUA_TAIL;
			movebank[44] = Move.EARTH_POWER;
			movebank[48] = Move.DRAGON_PULSE;
			movebank[52] = Move.DIVE;
			movebank[54] = Move.HYDRO_PUMP;
			movebank[62] = Move.DRAGON_RUSH;
			movebank[69] = Move.TIDAL_WAVE;
			break;
		case -133:
			movebank = new Move[85];
			movebank[0] = Move.THUNDER_FANG;
			movebank[1] = Move.DRAGON_RAGE;
			movebank[7] = Move.THUNDER_WAVE;
			movebank[14] = Move.ANCIENT_POWER;
			movebank[21] = Move.THUNDERBOLT;
			movebank[28] = Move.DRAGON_BREATH;
			movebank[35] = Move.SLASH;
			movebank[42] = Move.STAR_STORM;
			movebank[49] = Move.DRAGON_CLAW;
			movebank[53] = Move.MAGIC_REFLECT;
			movebank[58] = Move.THUNDER;
			movebank[62] = Move.OUTRAGE;
			movebank[70] = Move.HYPER_BEAM;
			movebank[77] = Move.DRACO_METEOR;
			movebank[84] = Move.BOLT_STRIKE;
			break;
		case -134:
			movebank = new Move[85];
			movebank[0] = Move.FIRE_FANG;
			movebank[1] = Move.DRAGON_RAGE;
			movebank[7] = Move.WILL_O_WISP;
			movebank[14] = Move.ANCIENT_POWER;
			movebank[21] = Move.FLAMETHROWER;
			movebank[28] = Move.DRAGON_BREATH;
			movebank[35] = Move.SLASH;
			movebank[42] = Move.STAR_STORM;
			movebank[49] = Move.DRAGON_PULSE;
			movebank[53] = Move.MAGIC_REFLECT;
			movebank[58] = Move.FIRE_BLAST;
			movebank[62] = Move.OUTRAGE;
			movebank[70] = Move.HYPER_BEAM;
			movebank[77] = Move.DRACO_METEOR;
			movebank[84] = Move.BLUE_FLARE;
			break;
		case -135:
			movebank = new Move[100];
			movebank[0] = Move.DISAPPEAR;
			movebank[1] = Move.DOUBLE_BLAST;
			movebank[5] = Move.VINE_WHIP;
			movebank[7] = Move.DARK_VOID;
			movebank[8] = Move.DREAM_EATER;
			movebank[9] = Move.ANCIENT_POWER;
			movebank[16] = Move.BLACK_HOLE;
			movebank[19] = Move.CONFUSION;
			movebank[24] = Move.EXTRAORDINARY;
			movebank[30] = Move.DRAGON_PULSE;
			movebank[36] = Move.GIGA_IMPACT;
			movebank[45] = Move.MAGIC_BLAST;
			movebank[52] = Move.X_SCIZZOR;
			movebank[61] = Move.AEROBLAST;
			movebank[74] = Move.MAGIC_REFLECT;
			movebank[87] = Move.STAR_STORM;
			movebank[94] = Move.ERUPTION;
			movebank[99] = Move.ELECTROEXPLOSION;
			break;
		case -136:
			movebank = new Move[100];
			movebank[0] = Move.FLAMETHROWER;
			movebank[1] = Move.AURA_SPHERE;
			movebank[5] = Move.BUG_BUZZ;
			movebank[7] = Move.CHARGE;
			movebank[8] = Move.DISCHARGE;
			movebank[9] = Move.ANCIENT_POWER;
			movebank[16] = Move.BLACK_HOLE;
			movebank[19] = Move.CONFUSION;
			movebank[24] = Move.EXTRAORDINARY;
			movebank[30] = Move.DARK_PULSE;
			movebank[36] = Move.COMET_CRASH;
			movebank[45] = Move.MAGIC_BLAST;
			movebank[52] = Move.X_SCIZZOR;
			movebank[61] = Move.HYDRO_PUMP;
			movebank[74] = Move.SOLAR_BEAM;
			movebank[87] = Move.MAGIC_CRASH;
			movebank[94] = Move.DRACO_METEOR;
			movebank[99] = Move.BOLT_STRIKE;
			break;
		case -137:
			movebank = new Move[100];
			movebank[0] = Move.THUNDERBOLT;
			movebank[1] = Move.TWISTER;
			movebank[5] = Move.WING_ATTACK;
			movebank[7] = Move.SLEEP_POWDER;
			movebank[8] = Move.POISON_POWDER;
			movebank[9] = Move.AIR_CUTTER;
			movebank[16] = Move.WATER_PULSE;
			movebank[19] = Move.CONFUSION;
			movebank[24] = Move.EXTRAORDINARY;
			movebank[30] = Move.DRAGON_CLAW;
			movebank[36] = Move.THUNDER;
			movebank[45] = Move.POISON_PUNCH;
			movebank[52] = Move.FIRE_BLAST;
			movebank[61] = Move.MAGIC_BLAST;
			movebank[74] = Move.MAGIC_REFLECT;
			movebank[87] = Move.MAGIC_CRASH;
			movebank[94] = Move.BOLT_STRIKE;
			movebank[99] = Move.BLUE_FLARE;
			break;
		case -138:
			movebank = new Move[100];
			movebank[0] = Move.HYPER_BEAM;
			movebank[1] = Move.GALAXY_ATTACK;
			movebank[24] = Move.EXTRAORDINARY;
			movebank[29] = Move.DRAGON_PULSE;
			movebank[34] = Move.GIGA_IMPACT;
			movebank[39] = Move.STAR_STORM;
			movebank[44] = Move.MAGIC_BLAST;
			movebank[49] = Move.X_SCIZZOR;
			movebank[52] = Move.BOULDER_CRUSH;
			movebank[55] = Move.PHASE_SHIFT;
			movebank[58] = Move.ERUPTION;
			movebank[61] = Move.AEROBLAST;
			movebank[64] = Move.HYPER_BEAM;
			movebank[69] = Move.BLAST_FLAME;
			movebank[74] = Move.DESTINY_BOND;
			movebank[79] = Move.ELECTROEXPLOSION;
			movebank[84] = Move.TAKE_OVER;
			movebank[89] = Move.DRACO_METEOR;
			movebank[94] = Move.STAR_STORM;
			movebank[99] = Move.BLUE_FLARE;
			break;
		case -139:
			movebank = new Move[100];
			movebank[0] = Move.HYPER_BEAM;
			movebank[1] = Move.GALAXY_ATTACK;
			movebank[24] = Move.EXTRAORDINARY;
			movebank[29] = Move.DARK_PULSE;
			movebank[34] = Move.COMET_CRASH;
			movebank[39] = Move.ERUPTION;
			movebank[44] = Move.MAGIC_BLAST;
			movebank[49] = Move.X_SCIZZOR;
			movebank[52] = Move.SOLAR_BEAM;
			movebank[55] = Move.FIRE_BLAST;
			movebank[58] = Move.IRON_TAIL;
			movebank[61] = Move.HYDRO_PUMP;
			movebank[64] = Move.STONE_EDGE;
			movebank[69] = Move.EARTH_POWER;
			movebank[74] = Move.MAGIC_REFLECT;
			movebank[79] = Move.DESTINY_BOND;
			movebank[84] = Move.MAGIC_CRASH;
			movebank[89] = Move.DRACO_METEOR;
			movebank[94] = Move.STAR_STORM;
			movebank[99] = Move.BOLT_STRIKE;
			break;
		case -140:
			movebank = new Move[100];
			movebank[0] = Move.HYPER_BEAM;
			movebank[1] = Move.GALAXY_ATTACK;
			movebank[24] = Move.EXTRAORDINARY;
			movebank[29] = Move.DRAGON_CLAW;
			movebank[34] = Move.THUNDER;
			movebank[39] = Move.ERUPTION;
			movebank[44] = Move.IRON_TAIL;
			movebank[49] = Move.BRAVE_BIRD;
			movebank[52] = Move.FIRE_BLAST;
			movebank[55] = Move.SKY_ATTACK;
			movebank[58] = Move.MAGIC_BLAST;
			movebank[61] = Move.MAGIC_REFLECT;
			movebank[64] = Move.MAGIC_CRASH;
			movebank[69] = Move.SUPERPOWER;
			movebank[74] = Move.HYDRO_PUMP;
			movebank[79] = Move.DESTINY_BOND;
			movebank[84] = Move.MAGIC_FANG;
			movebank[89] = Move.DRACO_METEOR;
			movebank[94] = Move.BOLT_STRIKE;
			movebank[99] = Move.BLUE_FLARE;
			break;
		case -141:
			movebank = new Move[25];
			movebank[0] = Move.SCRATCH;
			movebank[2] = Move.TAIL_WHIP;
			movebank[6] = Move.POISON_POWDER;
			movebank[8] = Move.EMBER;
			movebank[11] = Move.WILL_O_WISP;
			movebank[12] = Move.FIRE_SPIN;
			movebank[14] = Move.FLAME_WHEEL;
			movebank[18] = Move.FIRE_TAIL;
			movebank[21] = Move.FIRE_FANG;
			movebank[22] = Move.THUNDER_FANG;
			movebank[23] = Move.POISON_FANG;
			movebank[24] = Move.FURY_SWIPES;
			break;
		case -142:
			movebank = new Move[25];
			movebank[0] = Move.SCRATCH;
			movebank[2] = Move.TAIL_WHIP;
			movebank[6] = Move.POISON_POWDER;
			movebank[8] = Move.BUBBLEBEAM;
			movebank[11] = Move.AQUA_RING;
			movebank[12] = Move.WATER_GUN;
			movebank[14] = Move.WATER_JET;
			movebank[18] = Move.AQUA_TAIL;
			movebank[21] = Move.FIRE_FANG;
			movebank[22] = Move.THUNDER_FANG;
			movebank[23] = Move.POISON_FANG;
			movebank[24] = Move.FURY_SWIPES;
			break;
		case -143:
			movebank = new Move[55];
			movebank[0] = Move.SCRATCH;
			movebank[2] = Move.TAIL_WHIP;
			movebank[6] = Move.POISON_POWDER;
			movebank[8] = Move.EMBER;
			movebank[11] = Move.WILL_O_WISP;
			movebank[12] = Move.FIRE_SPIN;
			movebank[14] = Move.FLAME_WHEEL;
			movebank[18] = Move.FIRE_TAIL;
			movebank[21] = Move.FIRE_FANG;
			movebank[22] = Move.THUNDER_FANG;
			movebank[23] = Move.POISON_FANG;
			movebank[24] = Move.FURY_SWIPES;
			movebank[27] = Move.ROLLOUT;
			movebank[28] = Move.SLASH;
			movebank[29] = Move.TAKE_DOWN;
			movebank[31] = Move.FLAMETHROWER;
			movebank[36] = Move.ANCIENT_POWER;
			movebank[40] = Move.CRUNCH;
			movebank[45] = Move.SKULL_BASH;
			movebank[49] = Move.GIGA_IMPACT;
			movebank[54] = Move.FIRE_BLAST;
			break;
		case -144:
			movebank = new Move[55];
			movebank[0] = Move.SCRATCH;
			movebank[2] = Move.TAIL_WHIP;
			movebank[6] = Move.POISON_POWDER;
			movebank[8] = Move.BUBBLEBEAM;
			movebank[11] = Move.AQUA_RING;
			movebank[12] = Move.WATER_GUN;
			movebank[14] = Move.WATER_JET;
			movebank[18] = Move.AQUA_TAIL;
			movebank[21] = Move.FIRE_FANG;
			movebank[22] = Move.THUNDER_FANG;
			movebank[23] = Move.POISON_FANG;
			movebank[24] = Move.FURY_SWIPES;
			movebank[27] = Move.ROLLOUT;
			movebank[28] = Move.SLASH;
			movebank[29] = Move.TAKE_DOWN;
			movebank[31] = Move.BRINE;
			movebank[36] = Move.ANCIENT_POWER;
			movebank[40] = Move.CRUNCH;
			movebank[45] = Move.SKULL_BASH;
			movebank[49] = Move.GIGA_IMPACT;
			movebank[54] = Move.HYDRO_PUMP;
			break;
		}
		
	}

	public void faint(boolean announce, Player player) {
		this.currentHP = 0;
		this.fainted = true;
		this.battled = false;
		if (player != null && this.trainerOwned) {
			player.setBattled(player.getBattled() - 1);
		}
		if (announce) System.out.println("\n" + this.name + " fainted!");
	}

	public void clearVolatile() {
		confusionCounter = 0;
		magCount = 0;
		this.lastMoveUsed = null;
		this.moveMultiplier = 1;
		this.vStatuses.clear();
		statStages = new int[7];
		this.impressive = true;
		setType();
		
	}

	public Status getStatus() {
		return this.status;
	}
	
	public int calc(double attackStat, double defenseStat, int bp, int level) {
		double num = 2* (double) level / 5 + 2;
		double stat = attackStat / defenseStat / 50;
		double damageDouble = Math.floor(num * bp * stat);
		damageDouble += 2;
		
		Random roll = new Random();
		double rollAmt = roll.nextInt(16);
		rollAmt += 85;
		rollAmt /= 100;
		
		// Roll
		damageDouble *= rollAmt;
		// Convert to integer
		int damage = (int) Math.floor(damageDouble);
		return damage;
	}
	
	public int calcWithTypes(Pokemon foe, Move move) {
		double attackStat;
		double defenseStat;
		int damage = 0;
		int bp = move.basePower;
		
		if (move.accuracy <= 100 && move.cat != 2 && move != Move.ELECTROEXPLOSION) {
			if (getImmune(foe, move.mtype)) return 0; // Check for immunity
		} else if (move.accuracy > 100 && move.cat != 2) {
			if (getImmune(foe, move.mtype)) return 0; // Check for immunity
		}
		if (move.cat != 2 && move.mtype == PType.GROUND && foe.magCount > 0) return 0; // Check for immunity
		
		if (move == Move.DREAM_EATER && foe.status != Status.ASLEEP) return 0;
		
		if (move.basePower < 0) bp = determineBasePower(foe, move);
		
		if (this.vStatuses.contains(Status.AUTO) && (move == Move.BIG_BULLET || move == Move.GUNSHOT || move == Move.ROCKET)) bp *= 2;
		// Use either physical or special attack/defense
		if (move.isPhysical()) {
			attackStat = this.getStat(1);
			defenseStat = foe.getStat(2);
			attackStat *= this.asModifier(0);
			defenseStat *= foe.asModifier(1);
			if (this.status == Status.BURNED) attackStat /= 2;
		} else {
			attackStat = this.getStat(3);
			defenseStat = foe.getStat(4);
			attackStat *= this.asModifier(2);
			defenseStat *= foe.asModifier(3);
			if (this.status == Status.BLEEDING) attackStat /= 2;
		}
		
		damage = calc(attackStat, defenseStat, bp, this.level);
		
		// Stab
		if (move.mtype == this.type1) damage *= 1.5;
		if (move.mtype == this.type2) damage *= 1.5;
		
		// Charged
		if (move.mtype == PType.ELECTRIC && this.vStatuses.contains(Status.CHARGED)) damage *= 2;
		
		double multiplier = 1;
		// Check type effectiveness
		PType[] resist = getResistances(move.mtype);
		for (PType type : resist) {
			if (foe.type1 == type) multiplier /= 2;
			if (foe.type2 == type) multiplier /= 2;
		}
		
		// Check type effectiveness
		PType[] weak = getWeaknesses(move.mtype);
		for (PType type : weak) {
			if (foe.type1 == type) multiplier *= 2;
			if (foe.type2 == type) multiplier *= 2;
		}
		
		damage *= multiplier;
		
		if (move == Move.NIGHT_SHADE || move == Move.SEISMIC_TOSS) damage = this.level;
		if (move == Move.FIRE_DASH) damage = this.currentHP;
		if (move == Move.SUPER_FANG) damage = foe.currentHP / 2;
		if (move == Move.DRAGON_RAGE) damage = 40;
		
		damage = Math.max(damage, 1);
		
		return damage;
	}

	public static void endOfTurn(Pokemon p, Pokemon f, Player player) {
		if (p.isFainted()) return;
		if (p.status == Status.BLEEDING) {
			p.currentHP -= Math.max(p.getStat(0) / 8, 1);
			System.out.println("\n" + p.name + " was hurt by bleeding!");
			if (p.currentHP <= 0) { // Check for kill
				p.faint(true, player);
				f.awardxp((int) Math.ceil(p.level * p.trainer), player);
			}
			
		} else if (p.status == Status.BURNED) {
			p.currentHP -= Math.max(p.getStat(0) / 16, 1);
			System.out.println("\n" + p.name + " was hurt by its burn!");
			if (p.currentHP <= 0) { // Check for kill
				p.faint(true, player);
				f.awardxp((int) Math.ceil(p.level * p.trainer), player);
			}
			
		} else if (p.status == Status.POISONED) {
			p.currentHP -= Math.max(p.getStat(0) / 8, 1);
			System.out.println("\n" + p.name + " was hurt by poison!");
			if (p.currentHP <= 0) { // Check for kill
				p.faint(true, player);
				f.awardxp((int) Math.ceil(p.level * p.trainer), player);
			}
			
		}
		if (p.vStatuses.contains(Status.CURSED)) {
			p.currentHP -= Math.max(p.getStat(0) / 4, 1);
			System.out.println("\n" + p.name + " was hurt by the curse!");
			if (p.currentHP <= 0) { // Check for kill
				p.faint(true, player);
				f.awardxp((int) Math.ceil(p.level * p.trainer), player);
			}
			
		}
		if (p.vStatuses.contains(Status.LEECHED)) {
			int hp = Math.max(p.getStat(0) / 8, 1);
			if (hp >= p.currentHP) hp = p.currentHP;
			if (f.currentHP > f.getStat(0)) f.currentHP = f.getStat(0);
			p.currentHP -= hp;
			System.out.println("\n" + f.name + " sucked health from " + p.name + "!");
			f.currentHP += hp;
			if (p.currentHP <= 0) {
				p.faint(true, player);
				f.awardxp((int) Math.ceil(p.level * p.trainer), player);
			}
			
		}
		if (p.vStatuses.contains(Status.NIGHTMARE)) {
			if (p.status == Status.ASLEEP) {
				p.currentHP -= Math.max(p.getStat(0) / 4, 1);
				System.out.println("\n" + p.name + " had a nightmare!");
				if (p.currentHP <= 0) { // Check for kill
					p.faint(true, player);
					f.awardxp((int) Math.ceil(p.level * p.trainer), player);
				}
			} else {
				p.vStatuses.remove(Status.NIGHTMARE);
			}
		} if (p.vStatuses.contains(Status.AQUA_RING)) {
			if (p.currentHP < p.getStat(0)) {
				p.currentHP += Math.max(p.getStat(0) / 16, 1);
				if (p.currentHP > p.getStat(0)) {
					p.currentHP = p.getStat(0);
				}
				System.out.println("\n" + p.name + " restored HP.");
			}
		} if (p.vStatuses.contains(Status.SPUN)) {
			if (p.spunCount == 0) {
				System.out.println("\n" + p.name + " was freed from wrap!");
				p.vStatuses.remove(Status.SPUN);
			} else {
				p.currentHP -= Math.max(p.getStat(0) / 16, 1);
				System.out.println("\n" + p.name + " was hurt by being wrapped!");
				p.spunCount--;
				if (p.currentHP <= 0) { // Check for kill
					p.faint(true, player);
					f.awardxp((int) Math.ceil(p.level * p.trainer), player);
				}
			}
		}
		if (p.perishCount > 0) {
			p.perishCount--;
			System.out.println("\n" + p.getName() + "'s perish count fell to " + p.perishCount + "!");
			if (p.perishCount == 0) {
				p.faint(true, player);
				f.awardxp((int) Math.ceil(p.level * p.trainer), player);
			}
		}
		if (p.vStatuses.contains(Status.LOCKED) && p.outCount == 0 && p.lastMoveUsed == Move.OUTRAGE) {
			p.confuse();
			p.vStatuses.remove(Status.LOCKED);
		}
		if (p.vStatuses.contains(Status.LOCKED) && p.rollCount == 5) {
			p.vStatuses.remove(Status.LOCKED);
		}
		if (p.vStatuses.contains(Status.BONDED)) {
			p.vStatuses.remove(Status.BONDED);
		}
		p.vStatuses.remove(Status.FLINCHED);
		
	}

	public int getSpeed() {
		double speed = this.getStat(5) * this.asModifier(4);
		if (this.getStatus() == Status.PARALYZED) speed *= 0.5;
		return (int) speed;
	}
	
	public void confuse() {
		this.vStatuses.add(Status.CONFUSED);
		this.confusionCounter = (int)(Math.random() * 4) + 1;
		System.out.println(this.name + " became confused!");
	}
	
	public void sleep(boolean announce) {
		if (this.status == Status.HEALTHY) {
			this.status = Status.ASLEEP;
			this.sleepCounter = (int)(Math.random() * 3) + 1;
			System.out.println(this.name + " fell asleep!");
		} else {
			if (announce) System.out.println("But it failed!");
		}
		
	}
	
	public void paralyze(boolean announce) {
		if (this.type1 == PType.ELECTRIC || this.type2 == PType.ELECTRIC) {
			if (announce) System.out.println("It doesn't effect " + this.name + "...");
			return;
		}
		if (this.status == Status.HEALTHY) {
			this.status = Status.PARALYZED;
			System.out.println(this.name + " was paralyzed!");
		} else {
			if (announce) System.out.println("But it failed!");
		}
	}
	
	public void burn(boolean announce) {
		if (this.type1 == PType.FIRE || this.type2 == PType.FIRE) {
			if (announce) System.out.println("It doesn't effect " + this.name + "...");
			return;
		}
		if (this.status == Status.HEALTHY) {
			this.status = Status.BURNED;
			System.out.println(this.name + " was burned!");
		} else {
			if (announce) System.out.println("But it failed!");
		}
	}
	
	public void poison(boolean announce) {
		if (this.type1 == PType.POISON || this.type2 == PType.POISON) {
			if (announce) System.out.println("It doesn't effect " + this.name + "...");
			return;
		}
		if (this.status == Status.HEALTHY) {
			this.status = Status.POISONED;
			System.out.println(this.name + " was poisoned!");
		} else {
			if (announce) System.out.println("But it failed!");
		}
	}
	
	public boolean contains(Move move) {
	    for (Move m : this.moveset) {
	        if (m != null && m == (move)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	private int determineBasePower(Pokemon foe, Move move) {
		int bp = 0;
		if (move == Move.BRINE) {
			if (foe.currentHP / foe.getStat(0) > 0.5) {
				bp = 65;
			} else {
				bp = 130;
			}
		} else if (move == Move.COMET_CRASH) {
			if (this.currentHP == this.getStat(0)) {
				bp = 160;
			} else {
				bp = 80;
			}
		} else if (move == Move.COMET_PUNCH) {
			int randomNum = (int) (Math.random() * 4) + 2;
	        System.out.println("Hit " + randomNum + " times!");
	        bp = 20 * randomNum;
		} else if (move == Move.DOUBLE_BLAST) {
	        System.out.println("Hit " + 2 + " times!");
	        bp = 80;
		} else if (move == Move.DOUBLE_HIT) {
	        System.out.println("Hit " + 2 + " times!");
	        bp = 70;
		} else if (move == Move.DOUBLE_JET) {
			int randomNum = (int) (Math.random() * 4) + 2;
	        System.out.println("Hit " + randomNum + " times!");
	        bp = 20 * randomNum;
		} else if (move == Move.DOUBLE_KICK) {
	        System.out.println("Hit " + 2 + " times!");
	        bp = 60;
		} else if (move == Move.DOUBLE_PUNCH) {
	        System.out.println("Hit " + 2 + " times!");
	        bp = 60;
		} else if (move == Move.DOUBLE_SLAP) {
			int randomNum = (int) (Math.random() * 4) + 2;
	        System.out.println("Hit " + randomNum + " times!");
	        bp = 15 * randomNum;
		} else if (move == Move.DOUBLE_SLICE) {
	        System.out.println("Hit " + 2 + " times!");
	        bp = 60;
		} else if (move == Move.DUAL_STAB) {
	        System.out.println("Hit " + 2 + " times!");
	        bp = 60;
		} else if (move == Move.ELECTROBALL) {
			double speedRatio = foe.getSpeed() * 1.0 / this.getSpeed();
			if (speedRatio > 1) {
				bp = 40;
			} else if (speedRatio >= 0.501 && speedRatio <= 1) {
				bp = 60;
			} else if (speedRatio >= 0.3334 && speedRatio < 0.501) {
				bp = 80;
			} else if (speedRatio >= 0.2501 && speedRatio < 0.3334) {
				bp = 120;
			} else if (speedRatio < 0.2501) {
				bp = 150;
			}
		} else if (move == Move.ERUPTION) {
			double hpRatio = this.currentHP * 1.0 / this.getStat(0);
			hpRatio *= 150;
			bp = Math.max((int) hpRatio, 1);
		} else if (move == Move.FIREBALL) {
			if (foe.status == Status.BURNED) {
				bp = 120;
			} else {
				bp = 60;
			}
		} else if (move == Move.FLAIL) {
			double hpRatio = this.currentHP / this.getStat(0);
			if (hpRatio >= 0.6875) {
				bp = 20;
			} else if (hpRatio >= 0.3542 && hpRatio < 0.6875) {
				bp = 40;
			} else if (hpRatio >= 0.2083 && hpRatio < 0.3542) {
				bp = 80;
			} else if (hpRatio >= 0.1042 && hpRatio < 0.2083) {
				bp = 100;
			} else if (hpRatio >= 0.0417 && hpRatio < 0.1042) {
				bp = 150;
			} else if (hpRatio < 0.0417) {
				bp = 200;
			}
		} else if (move == Move.FURY_ATTACK) {
			int randomNum = (int) (Math.random() * 4) + 2;
	        System.out.println("Hit " + randomNum + " times!");
	        bp = 15 * randomNum;
		} else if (move == Move.FURY_CUTTER) {
			if (this.lastMoveUsed == Move.FURY_CUTTER) {
				this.moveMultiplier *= 2;
			}
			bp = Math.min(160, 20 * this.moveMultiplier);
		} else if (move == Move.FURY_SWIPES) {
			int randomNum = (int) (Math.random() * 4) + 2;
	        System.out.println("Hit " + randomNum + " times!");
	        bp = 15 * randomNum;
		} else if (move == Move.GYRO_BALL) {
			double speedRatio = foe.getSpeed() * 1.0 / this.getSpeed();
			bp = (int) Math.min(150, (25 * speedRatio) + 1);
		} else if (move == Move.MAGNITUDE) {
			int mag = (int) (Math.random()*100 + 1);
			if (mag <= 5) {
				bp = 10;
				System.out.println("Magnitude 4!");
			} else if (mag <= 15) {
				bp = 30;
				System.out.println("Magnitude 5!");
			} else if (mag <= 35) {
				bp = 50;
				System.out.println("Magnitude 6!");
			} else if (mag <= 65) {
				bp = 70;
				System.out.println("Magnitude 7!");
			} else if (mag <= 85) {
				bp = 90;
				System.out.println("Magnitude 8!");
			} else if (mag <= 95) {
				bp = 110;
				System.out.println("Magnitude 9!");
			} else if (mag <= 100) {
				bp = 150;
				System.out.println("Magnitude 10!");
			}
		} else if (move == Move.RAGE) {
			if (this.lastMoveUsed == Move.RAGE) {
				this.moveMultiplier *= 2;
			}
			bp = Math.min(160, 20 * this.moveMultiplier);
		} else if (move == Move.REVENGE) {
			if (this.getSpeed() > foe.getSpeed()) {
				bp = 60;
			} else {
				bp = 120;
			}
		} else if (move == Move.ROCK_BLAST) {
			int randomNum = (int) (Math.random() * 4) + 2;
	        System.out.println("Hit " + randomNum + " times!");
	        bp = 25 * randomNum;
		} else if (move == Move.ROLLOUT) {
			bp = (int) (30 * Math.pow(2, this.rollCount-1));
		} else if (move == Move.SPIKE_SHOT) {
			int randomNum = (int) (Math.random() * 4) + 2;
	        System.out.println("Hit " + randomNum + " times!");
	        bp = 20 * randomNum;
		} else if (move == Move.TIDAL_WAVE) {
			int wave = (int) (Math.random()*3 + 1);
			if (wave == 1) {
				bp = 90;
				System.out.println("Morning Tide!");
			} else if (wave == 2) {
				bp = 50;
				System.out.println("Day Tide!");
			} else if (wave == 3) {
				bp = 130;
				System.out.println("Evening Tide!");
			}
		} else if (move == Move.WAKE_UP_SLAP) {
			bp = 60;
			if (foe.status == Status.ASLEEP) {
				bp = 120;
				foe.sleepCounter = 0;
			}
		} else if (move == Move.WRING_OUT) {
			double hpRatio = foe.currentHP * 1.0 / foe.getStat(0);
			hpRatio *= 120;
			bp = Math.max((int) hpRatio, 1);
		}
		return bp;
	}
	
	private void removeBad(ArrayList<Status> stati) {
		stati.remove(Status.CONFUSED);
		stati.remove(Status.CURSED);
		stati.remove(Status.LEECHED);
		stati.remove(Status.NIGHTMARE);
		stati.remove(Status.BLEEDING);
		stati.remove(Status.FLINCHED);
		stati.remove(Status.SPUN);
	}

	public boolean knowsMove(Move move) {
		for (Move m : moveset) {
			if (m == move) {
				return true;
			}
		}
		return false;
	}
	
	public boolean trainerOwned() {
		return this.trainer == 1.5;
	}
}
