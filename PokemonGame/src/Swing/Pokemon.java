package Swing;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Swing.Battle.JGradientButton;
import Swing.Field.Effect;

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
	public double[] nature;
	
	public PType type1;
	public PType type2;
	
	public Ability ability;
	
	public Node[] movebank;
	public Move[] moveset;
	
	public Status status;
	public ArrayList<Status> vStatuses;
	
	public int exp;
	public int expMax;
	
	public int currentHP;
	public boolean fainted;
	
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
	public int slot;
	public int abilitySlot;
	public boolean success;
	
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
		setNature();
		getStats();
		setType();
		if (t) {
			setAbility(0);
		} else {
			abilitySlot = (int)Math.round(Math.random());
			setAbility(abilitySlot);
		}
		
		
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
			Node node = movebank[i];
	        while (node != null) {
	            moveset[index] = node.data;
	            index++;
	            if (index >= 4) {
	                index = 0;
	            }
	            node = node.next;
	        }
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



	public Pokemon(int i, Pokemon pokemon) {
		id = i;
		name = getName();
		
		baseStats = new int[6];
		stats = new int[6];
		level = pokemon.level;
		statStages = new int[7];
		ivs = pokemon.ivs;
		nature = pokemon.nature;
		
		setBaseStats();
		getStats();
		setType();
		setAbility(pokemon.abilitySlot);
		
		expMax = level * 2;
		exp = 0;
		
		currentHP = this.getStat(0);
		setMoveBank();
		moveset = pokemon.moveset;
		
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
		if (id == 1) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == 2) {
			this.type1 = PType.GRASS;
			this.type2 = PType.ROCK;
		} else if (id == 3) {
			this.type1 = PType.GRASS;
			this.type2 = PType.ROCK;
		} else if (id == 4) {
			this.type1 = PType.FIRE;
			this.type2 = null;
		} else if (id == 5) {
			this.type1 = PType.FIRE;
			this.type2 = null;
		} else if (id == 6) {
			this.type1 = PType.FIRE;
			this.type2 = PType.STEEL;
		} else if (id == 7) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == 8) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == 9) {
			this.type1 = PType.WATER;
			this.type2 = PType.FIGHTING;
		} else if (id == 10) {
			this.type1 = PType.LIGHT;
			this.type2 = PType.FLYING;
		} else if (id == 11) {
			this.type1 = PType.LIGHT;
			this.type2 = PType.FLYING;
		} else if (id == 12) {
			this.type1 = PType.LIGHT;
			this.type2 = PType.FLYING;
		} else if (id == 13) {
			this.type1 = PType.NORMAL;
			this.type2 = PType.FLYING;
		} else if (id == 14) {
			this.type1 = PType.NORMAL;
			this.type2 = PType.FLYING;
		} else if (id == 15) {
			this.type1 = PType.STEEL;
			this.type2 = PType.FLYING;
		} else if (id == 16) {
			this.type1 = PType.NORMAL;
			this.type2 = null;
		} else if (id == 17) {
			this.type1 = PType.NORMAL;
			this.type2 = null;
		} else if (id == 18) {
			this.type1 = PType.NORMAL;
			this.type2 = PType.ROCK;
		} else if (id == 19) {
			this.type1 = PType.NORMAL;
			this.type2 = null;
		} else if (id == 20) {
			this.type1 = PType.NORMAL;
			this.type2 = PType.MAGIC;
		} else if (id == 21) {
			this.type1 = PType.NORMAL;
			this.type2 = PType.GALACTIC;
		} else if (id == 22) {
			this.type1 = PType.BUG;
			this.type2 = null;
		} else if (id == 23) {
			this.type1 = PType.BUG;
			this.type2 = null;
		} else if (id == 24) {
			this.type1 = PType.BUG;
			this.type2 = null;
		} else if (id == 25) {
			this.type1 = PType.BUG;
			this.type2 = PType.ROCK;
		} else if (id == 26) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == 27) {
			this.type1 = PType.GRASS;
			this.type2 = PType.FIGHTING;
		} else if (id == 28) {
			this.type1 = PType.GRASS;
			this.type2 = PType.DRAGON;
		} else if (id == 29) {
			this.type1 = PType.GRASS;
			this.type2 = PType.POISON;
		} else if (id == 30) {
			this.type1 = PType.GRASS;
			this.type2 = PType.POISON;
		} else if (id == 31) {
			this.type1 = PType.GRASS;
			this.type2 = PType.POISON;
		} else if (id == 32) {
			this.type1 = PType.BUG;
			this.type2 = PType.GRASS;
		} else if (id == 33) {
			this.type1 = PType.BUG;
			this.type2 = PType.GRASS;
		} else if (id == 34) {
			this.type1 = PType.BUG;
			this.type2 = PType.GRASS;
		} else if (id == 35) {
			this.type1 = PType.BUG;
			this.type2 = null;
		} else if (id == 36) {
			this.type1 = PType.BUG;
			this.type2 = PType.ELECTRIC;
		} else if (id == 37) {
			this.type1 = PType.BUG;
			this.type2 = PType.ELECTRIC;
		} else if (id == 38) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == 39) {
			this.type1 = PType.GRASS;
			this.type2 = null;
		} else if (id == 40) {
			this.type1 = PType.GRASS;
			this.type2 = PType.LIGHT;
		} else if (id == 41) {
			this.type1 = PType.BUG;
			this.type2 = PType.FIGHTING;
		} else if (id == 42) {
			this.type1 = PType.BUG;
			this.type2 = PType.FIGHTING;
		} else if (id == 43) {
			this.type1 = PType.BUG;
			this.type2 = PType.FIGHTING;
		} else if (id == 44) {
			this.type1 = PType.WATER;
			this.type2 = PType.GRASS;
		} else if (id == 45) {
			this.type1 = PType.WATER;
			this.type2 = PType.GRASS;
		} else if (id == 46) {
			this.type1 = PType.WATER;
			this.type2 = PType.GRASS;
		} else if (id == 47) {
			this.type1 = PType.MAGIC;
			this.type2 = PType.LIGHT;
		} else if (id == 48) {
			this.type1 = PType.ROCK;
			this.type2 = PType.GROUND;
		} else if (id == 49) {
			this.type1 = PType.ROCK;
			this.type2 = PType.GROUND;
		} else if (id == 50) {
			this.type1 = PType.ROCK;
			this.type2 = PType.GROUND;
		} else if (id == 51) {
			this.type1 = PType.ROCK;
			this.type2 = PType.LIGHT;
		} else if (id == 52) {
			this.type1 = PType.ROCK;
			this.type2 = null;
		} else if (id == 53) {
			this.type1 = PType.ROCK;
			this.type2 = PType.PSYCHIC;
		} else if (id == 54) {
			this.type1 = PType.ROCK;
			this.type2 = PType.DRAGON;
		} else if (id == 55) {
			this.type1 = PType.ROCK;
			this.type2 = null;
		} else if (id == 56) {
			this.type1 = PType.ROCK;
			this.type2 = PType.DARK;
		} else if (id == 57) {
			this.type1 = PType.FIGHTING;
			this.type2 = PType.GROUND;
		} else if (id == 58) {
			this.type1 = PType.FIGHTING;
			this.type2 = PType.FLYING;
		} else if (id == 59) {
			this.type1 = PType.ICE;
			this.type2 = PType.PSYCHIC;
		} else if (id == 60) {
			this.type1 = PType.ICE;
			this.type2 = PType.PSYCHIC;
		} else if (id == 61) {
			this.type1 = PType.ICE;
			this.type2 = PType.MAGIC;
		} else if (id == 62) {
			this.type1 = PType.ICE;
			this.type2 = PType.BUG;
		} else if (id == 63) {
			this.type1 = PType.ICE;
			this.type2 = PType.BUG;
		} else if (id == 64) {
			this.type1 = PType.GROUND;
			this.type2 = PType.ICE;
		} else if (id == 65) {
			this.type1 = PType.FIGHTING;
			this.type2 = PType.ICE;
		} else if (id == 66) {
			this.type1 = PType.ROCK;
			this.type2 = PType.LIGHT;
		} else if (id == 67) {
			this.type1 = PType.ROCK;
			this.type2 = PType.FIGHTING;
		} else if (id == 68) {
			this.type1 = PType.ICE;
			this.type2 = PType.WATER;
		} else if (id == 69) {
			this.type1 = PType.ICE;
			this.type2 = PType.WATER;
		} else if (id == 70) {
			this.type1 = PType.ICE;
			this.type2 = PType.WATER;
		} else if (id == 71) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == 72) {
			this.type1 = PType.WATER;
			this.type2 = PType.LIGHT;
		} else if (id == 73) {
			this.type1 = PType.BUG;
			this.type2 = PType.DARK;
		} else if (id == 74) {
			this.type1 = PType.BUG;
			this.type2 = PType.DARK;
		} else if (id == 75) {
			this.type1 = PType.PSYCHIC;
			this.type2 = PType.MAGIC;
		} else if (id == 76) {
			this.type1 = PType.PSYCHIC;
			this.type2 = PType.MAGIC;
		} else if (id == 77) {
			this.type1 = PType.PSYCHIC;
			this.type2 = PType.MAGIC;
		} else if (id == 78) {
			this.type1 = PType.WATER;
			this.type2 = PType.PSYCHIC;
		} else if (id == 79) {
			this.type1 = PType.WATER;
			this.type2 = PType.PSYCHIC;
		} else if (id == 80) {
			this.type1 = PType.GRASS;
			this.type2 = PType.PSYCHIC;
		} else if (id == 81) {
			this.type1 = PType.GRASS;
			this.type2 = PType.PSYCHIC;
		} else if (id == 82) {
			this.type1 = PType.PSYCHIC;
			this.type2 = null;
		} else if (id == 83) {
			this.type1 = PType.PSYCHIC;
			this.type2 = PType.STEEL;
		} else if (id == 84) {
			this.type1 = PType.PSYCHIC;
			this.type2 = PType.STEEL;
		} else if (id == 85) {
			this.type1 = PType.PSYCHIC;
			this.type2 = PType.LIGHT;
		} else if (id == 86) {
			this.type1 = PType.PSYCHIC;
			this.type2 = PType.LIGHT;
		} else if (id == 87) {
			this.type1 = PType.PSYCHIC;
			this.type2 = PType.LIGHT;
		} else if (id == 88) {
			this.type1 = PType.PSYCHIC;
			this.type2 = PType.FIGHTING;
		} else if (id == 89) {
			this.type1 = PType.LIGHT;
			this.type2 = PType.NORMAL;
		} else if (id == 90) {
			this.type1 = PType.PSYCHIC;
			this.type2 = PType.DARK;
		} else if (id == 91) {
			this.type1 = PType.PSYCHIC;
			this.type2 = PType.DARK;
		} else if (id == 92) {
			this.type1 = PType.FIRE;
			this.type2 = PType.NORMAL;
		} else if (id == 93) {
			this.type1 = PType.FIRE;
			this.type2 = PType.NORMAL;
		} else if (id == 94) {
			this.type1 = PType.FIRE;
			this.type2 = null;
		} else if (id == 95) {
			this.type1 = PType.FIRE;
			this.type2 = PType.FIGHTING;
		} else if (id == 96) {
			this.type1 = PType.FIRE;
			this.type2 = PType.FIGHTING;
		} else if (id == 97) {
			this.type1 = PType.FIRE;
			this.type2 = PType.GHOST;
		} else if (id == 98) {
			this.type1 = PType.FIRE;
			this.type2 = PType.GROUND;
		} else if (id == 99) {
			this.type1 = PType.FIRE;
			this.type2 = PType.FLYING;
		} else if (id == 100) {
			this.type1 = PType.FIRE;
			this.type2 = PType.DRAGON;
		} else if (id == 101) {
			this.type1 = PType.FIRE;
			this.type2 = PType.ROCK;
		} else if (id == 102) {
			this.type1 = PType.FIRE;
			this.type2 = PType.ROCK;
		} else if (id == 103) {
			this.type1 = PType.FIRE;
			this.type2 = PType.ROCK;
		} else if (id == 104) {
			this.type1 = PType.FIRE;
			this.type2 = PType.DARK;
		} else if (id == 105) {
			this.type1 = PType.FIRE;
			this.type2 = PType.DARK;
		} else if (id == 106) {
			this.type1 = PType.MAGIC;
			this.type2 = null;
		} else if (id == 107) {
			this.type1 = PType.MAGIC;
			this.type2 = PType.FIRE;
		} else if (id == 108) {
			this.type1 = PType.FIRE;
			this.type2 = PType.LIGHT;
		} else if (id == 109) {
			this.type1 = PType.FIRE;
			this.type2 = null;
		} else if (id == 110) {
			this.type1 = PType.FIRE;
			this.type2 = PType.LIGHT;
		} else if (id == 111) {
			this.type1 = PType.ELECTRIC;
			this.type2 = null;
		} else if (id == 112) {
			this.type1 = PType.ELECTRIC;
			this.type2 = PType.STEEL;
		} else if (id == 113) {
			this.type1 = PType.ELECTRIC;
			this.type2 = PType.STEEL;
		} else if (id == 114) {
			this.type1 = PType.ELECTRIC;
			this.type2 = PType.GROUND;
		} else if (id == 115) {
			this.type1 = PType.ELECTRIC;
			this.type2 = PType.GROUND;
		} else if (id == 116) {
			this.type1 = PType.ELECTRIC;
			this.type2 = PType.GROUND;
		} else if (id == 117) {
			this.type1 = PType.GRASS;
			this.type2 = PType.ELECTRIC;
		} else if (id == 118) {
			this.type1 = PType.GRASS;
			this.type2 = PType.ELECTRIC;
		} else if (id == 119) {
			this.type1 = PType.GRASS;
			this.type2 = PType.ELECTRIC;
		} else if (id == 120) {
			this.type1 = PType.STEEL;
			this.type2 = PType.NORMAL;
		} else if (id == 121) {
			this.type1 = PType.STEEL;
			this.type2 = PType.LIGHT;
		} else if (id == 122) {
			this.type1 = PType.STEEL;
			this.type2 = PType.MAGIC;
		} else if (id == 123) {
			this.type1 = PType.ELECTRIC;
			this.type2 = PType.NORMAL;
		} else if (id == 124) {
			this.type1 = PType.ELECTRIC;
			this.type2 = PType.LIGHT;
		} else if (id == 125) {
			this.type1 = PType.ELECTRIC;
			this.type2 = PType.MAGIC;
		} else if (id == 126) {
			this.type1 = PType.FIGHTING;
			this.type2 = PType.NORMAL;
		} else if (id == 127) {
			this.type1 = PType.FIGHTING;
			this.type2 = PType.LIGHT;
		} else if (id == 128) {
			this.type1 = PType.FIGHTING;
			this.type2 = PType.MAGIC;
		} else if (id == 129) {
			this.type1 = PType.BUG;
			this.type2 = PType.GROUND;;
		} else if (id == 130) {
			this.type1 = PType.BUG;
			this.type2 = PType.FLYING;;
		} else if (id == 131) {
			this.type1 = PType.BUG;
			this.type2 = PType.GHOST;;
		} else if (id == 132) {
			this.type1 = PType.WATER;
			this.type2 = PType.GHOST;
		} else if (id == 133) {
			this.type1 = PType.WATER;
			this.type2 = PType.GHOST;
		} else if (id == 134) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == 135) {
			this.type1 = PType.WATER;
			this.type2 = PType.FLYING;
		} else if (id == 136) {
			this.type1 = PType.WATER;
			this.type2 = PType.DRAGON;
		} else if (id == 137) {
			this.type1 = PType.WATER;
			this.type2 = null;
		} else if (id == 138) {
			this.type1 = PType.WATER;
			this.type2 = PType.FLYING;
		} else if (id == 139) {
			this.type1 = PType.WATER;
			this.type2 = PType.GALACTIC;
		} else if (id == 140) {
			this.type1 = PType.WATER;
			this.type2 = PType.GALACTIC;
		} else if (id == 141) {
			this.type1 = PType.DARK;
			this.type2 = PType.WATER;
		} else if (id == 142) {
			this.type1 = PType.DARK;
			this.type2 = PType.FLYING;
		} else if (id == 143) {
			this.type1 = PType.MAGIC;
			this.type2 = PType.DRAGON;
		} else if (id == 144) {
			this.type1 = PType.WATER;
			this.type2 = PType.DRAGON;
		} else if (id == 145) {
			this.type1 = PType.MAGIC;
			this.type2 = PType.DRAGON;
		} else if (id == 146) {
			this.type1 = PType.WATER;
			this.type2 = PType.ROCK;
		} else if (id == 147) {
			this.type1 = PType.WATER;
			this.type2 = PType.ROCK;
		} else if (id == 148) {
			this.type1 = PType.WATER;
			this.type2 = PType.GROUND;
		} else if (id == 149) {
			this.type1 = PType.WATER;
			this.type2 = PType.GROUND;
		} else if (id == 150) {
			this.type1 = PType.WATER;
			this.type2 = PType.MAGIC;
		} else if (id == 151) {
			this.type1 = PType.POISON;
			this.type2 = null;
		} else if (id == 152) {
			this.type1 = PType.POISON;
			this.type2 = null;
		} else if (id == 153) {
			this.type1 = PType.POISON;
			this.type2 = PType.FLYING;
		} else if (id == 154) {
			this.type1 = PType.POISON;
			this.type2 = PType.FLYING;
		} else if (id == 155) {
			this.type1 = PType.POISON;
			this.type2 = PType.FLYING;
		} else if (id == 156) {
			this.type1 = PType.GHOST;
			this.type2 = null;
		} else if (id == 157) {
			this.type1 = PType.GHOST;
			this.type2 = PType.STEEL;
		} else if (id == 158) {
			this.type1 = PType.GHOST;
			this.type2 = PType.DARK;
		} else if (id == 159) {
			this.type1 = PType.GHOST;
			this.type2 = PType.DARK;
		} else if (id == 160) {
			this.type1 = PType.GHOST;
			this.type2 = PType.POISON;
		} else if (id == 161) {
			this.type1 = PType.GHOST;
			this.type2 = PType.ROCK;
		} else if (id == 162) {
			this.type1 = PType.GHOST;
			this.type2 = PType.ROCK;
		} else if (id == 163) {
			this.type1 = PType.FIGHTING;
			this.type2 = null;
		} else if (id == 164) {
			this.type1 = PType.FIGHTING;
			this.type2 = null;
		} else if (id == 165) {
			this.type1 = PType.FIGHTING;
			this.type2 = null;
		} else if (id == 166) {
			this.type1 = PType.GROUND;
			this.type2 = null;
		} else if (id == 167) {
			this.type1 = PType.GROUND;
			this.type2 = null;
		} else if (id == 168) {
			this.type1 = PType.GROUND;
			this.type2 = PType.FIGHTING;
		} else if (id == 169) {
			this.type1 = PType.GROUND;
			this.type2 = null;
		} else if (id == 170) {
			this.type1 = PType.GROUND;
			this.type2 = PType.LIGHT;
		} else if (id == 171) {
			this.type1 = PType.GROUND;
			this.type2 = null;
		} else if (id == 172) {
			this.type1 = PType.GROUND;
			this.type2 = PType.STEEL;
		} else if (id == 173) {
			this.type1 = PType.GROUND;
			this.type2 = PType.DRAGON;
		} else if (id == 174) {
			this.type1 = PType.GALACTIC;
			this.type2 = PType.LIGHT;
		} else if (id == 175) {
			this.type1 = PType.GALACTIC;
			this.type2 = PType.LIGHT;
		} else if (id == 176) {
			this.type1 = PType.GALACTIC;
			this.type2 = PType.LIGHT;
		} else if (id == 177) {
			this.type1 = PType.MAGIC;
			this.type2 = PType.FLYING;
		} else if (id == 178) {
			this.type1 = PType.MAGIC;
			this.type2 = PType.FLYING;
		} else if (id == 179) {
			this.type1 = PType.DARK;
			this.type2 = null;
		} else if (id == 180) {
			this.type1 = PType.DARK;
			this.type2 = null;
		} else if (id == 181) {
			this.type1 = PType.STEEL;
			this.type2 = null;
		} else if (id == 182) {
			this.type1 = PType.STEEL;
			this.type2 = null;
		} else if (id == 183) {
			this.type1 = PType.STEEL;
			this.type2 = null;
		} else if (id == 184) {
			this.type1 = PType.MAGIC;
			this.type2 = PType.GALACTIC;
		} else if (id == 185) {
			this.type1 = PType.MAGIC;
			this.type2 = PType.GALACTIC;
		} else if (id == 186) {
			this.type1 = PType.MAGIC;
			this.type2 = PType.GALACTIC;
		} else if (id == 187) {
			this.type1 = PType.DRAGON;
			this.type2 = PType.LIGHT;
		} else if (id == 188) {
			this.type1 = PType.DRAGON;
			this.type2 = PType.LIGHT;
		} else if (id == 189) {
			this.type1 = PType.DRAGON;
			this.type2 = PType.LIGHT;
		} else if (id == 190) {
			this.type1 = PType.GALACTIC;
			this.type2 = null;
		} else if (id == 191) {
			this.type1 = PType.GALACTIC;
			this.type2 = null;
		} else if (id == 192) {
			this.type1 = PType.GALACTIC;
			this.type2 = PType.FIGHTING;
		} else if (id == 193) {
			this.type1 = PType.GALACTIC;
			this.type2 = PType.ICE;
		} else if (id == 194) {
			this.type1 = PType.GALACTIC;
			this.type2 = PType.ICE;
		} else if (id == 195) {
			this.type1 = PType.GALACTIC;
			this.type2 = PType.ROCK;
		} else if (id == 196) {
			this.type1 = PType.GALACTIC;
			this.type2 = PType.ROCK;
		} else if (id == 197) {
			this.type1 = PType.GHOST;
			this.type2 = PType.ELECTRIC;
		} else if (id == 198) {
			this.type1 = PType.GHOST;
			this.type2 = PType.ELECTRIC;
		} else if (id == 199) {
			this.type1 = PType.FIGHTING;
			this.type2 = PType.ELECTRIC;
		} else if (id == 200) {
			this.type1 = PType.FIGHTING;
			this.type2 = PType.ELECTRIC;
		} else if (id == 201) {
			this.type1 = PType.FIGHTING;
			this.type2 = PType.ELECTRIC;
		} else if (id == 202) {
			this.type1 = PType.FIRE;
			this.type2 = PType.ELECTRIC;
		} else if (id == 203) {
			this.type1 = PType.FIRE;
			this.type2 = PType.ELECTRIC;
		} else if (id == 204) {
			this.type1 = PType.FIRE;
			this.type2 = PType.ELECTRIC;
		} else if (id == 205) {
			this.type1 = PType.ROCK;
			this.type2 = PType.ELECTRIC;
		} else if (id == 206) {
			this.type1 = PType.ROCK;
			this.type2 = PType.ELECTRIC;
		} else if (id == 207) {
			this.type1 = PType.ROCK;
			this.type2 = PType.ELECTRIC;
		} else if (id == 208) {
			this.type1 = PType.LIGHT;
			this.type2 = PType.ELECTRIC;
		} else if (id == 209) {
			this.type1 = PType.WATER;
			this.type2 = PType.ELECTRIC;
		} else if (id == 210) {
			this.type1 = PType.WATER;
			this.type2 = PType.ELECTRIC;
		} else if (id == 211) {
			this.type1 = PType.DRAGON;
			this.type2 = PType.ELECTRIC;
		} else if (id == 212) {
			this.type1 = PType.DRAGON;
			this.type2 = PType.ELECTRIC;
		} else if (id == 213) {
			this.type1 = PType.DRAGON;
			this.type2 = PType.DARK;
		} else if (id == 214) {
			this.type1 = PType.DRAGON;
			this.type2 = PType.DARK;
		} else if (id == 215) {
			this.type1 = PType.GROUND;
			this.type2 = PType.GHOST;
		} else if (id == 216) {
			this.type1 = PType.DRAGON;
			this.type2 = PType.GHOST;
		} else if (id == 217) {
			this.type1 = PType.GROUND;
			this.type2 = PType.DARK;
		} else if (id == 218) {
			this.type1 = PType.GROUND;
			this.type2 = PType.DARK;
		} else if (id == 219) {
			this.type1 = PType.GROUND;
			this.type2 = PType.DARK;
		} else if (id == 220) {
			this.type1 = PType.POISON;
			this.type2 = PType.DARK;
		} else if (id == 221) {
			this.type1 = PType.POISON;
			this.type2 = PType.DARK;
		} else if (id == 222) {
			this.type1 = PType.POISON;
			this.type2 = PType.DARK;
		} else if (id == 223) {
			this.type1 = PType.FIRE;
			this.type2 = PType.DARK;
		} else if (id == 224) {
			this.type1 = PType.FIGHTING;
			this.type2 = PType.DARK;
		} else if (id == 225) {
			this.type1 = PType.FIGHTING;
			this.type2 = PType.DARK;
		} else if (id == 226) {
			this.type1 = PType.FLYING;
			this.type2 = PType.GHOST;
		} else if (id == 227) {
			this.type1 = PType.FLYING;
			this.type2 = PType.GHOST;
		} else if (id == 228) {
			this.type1 = PType.WATER;
			this.type2 = PType.FLYING;
		} else if (id == 229) {
			this.type1 = PType.DARK;
			this.type2 = PType.MAGIC;
		} else if (id == 230) {
			this.type1 = PType.ICE;
			this.type2 = PType.LIGHT;
		} else if (id == 231) {
			this.type1 = PType.ICE;
			this.type2 = PType.MAGIC;
		} else if (id == 232) {
			this.type1 = PType.POISON;
			this.type2 = PType.MAGIC;
		} else if (id == 233) {
			this.type1 = PType.ICE;
			this.type2 = PType.DRAGON;
		} else if (id == 234) {
			this.type1 = PType.PSYCHIC;
			this.type2 = PType.DRAGON;
		} else if (id == 235) {
			this.type1 = PType.GALACTIC;
			this.type2 = PType.DRAGON;
		} else if (id == 236) {
			this.type1 = PType.FIRE;
			this.type2 = PType.GALACTIC;
		} else if (id == -1) {
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
	
	private void setAbility(int i) {
		Ability[] abilities;
		
		if (id == 1) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 2) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 3) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 4) { abilities = new Ability[] {Ability.BLAZE, Ability.SOLAR_POWER};
		} else if (id == 5) { abilities = new Ability[] {Ability.BLAZE, Ability.SOLAR_POWER};
		} else if (id == 6) { abilities = new Ability[] {Ability.BLAZE, Ability.SOLAR_POWER};
		} else if (id == 7) { abilities = new Ability[] {Ability.TORRENT, Ability.PROTEAN};
		} else if (id == 8) { abilities = new Ability[] {Ability.TORRENT, Ability.PROTEAN};
		} else if (id == 9) { abilities = new Ability[] {Ability.TORRENT, Ability.PROTEAN};
		} else if (id == 10) { abilities = new Ability[] {Ability.KEEN_EYE, Ability.INSECT_FEEDER};
		} else if (id == 11) { abilities = new Ability[] {Ability.KEEN_EYE, Ability.INSECT_FEEDER};
		} else if (id == 12) { abilities = new Ability[] {Ability.MAGIC_GUARD, Ability.INSECT_FEEDER};
		} else if (id == 13) { abilities = new Ability[] {Ability.INSOMNIA, Ability.INSECT_FEEDER};
		} else if (id == 14) { abilities = new Ability[] {Ability.INSOMNIA, Ability.INSECT_FEEDER};
		} else if (id == 15) { abilities = new Ability[] {Ability.MIRROR_ARMOR, Ability.TOUGH_CLAWS};
		} else if (id == 16) { abilities = new Ability[] {Ability.MOUTHWATER, Ability.THICK_FAT};
		} else if (id == 17) { abilities = new Ability[] {Ability.MOUTHWATER, Ability.THICK_FAT};
		} else if (id == 18) { abilities = new Ability[] {Ability.ROUGH_SKIN, Ability.ROCK_HEAD};
		} else if (id == 19) { abilities = new Ability[] {Ability.ADAPTABILITY, Ability.ANGER_POINT};
		} else if (id == 20) { abilities = new Ability[] {Ability.ADAPTABILITY, Ability.SHIELD_DUST};
		} else if (id == 21) { abilities = new Ability[] {Ability.ADAPTABILITY, Ability.LEVITATE};
		} else if (id == 22) { abilities = new Ability[] {Ability.SWARM, Ability.COMPOUND_EYES};
		} else if (id == 23) { abilities = new Ability[] {Ability.SWARM, Ability.COMPOUND_EYES};
		} else if (id == 24) { abilities = new Ability[] {Ability.SWARM, Ability.COMPOUND_EYES};
		} else if (id == 25) { abilities = new Ability[] {Ability.ROUGH_SKIN, Ability.COMPOUND_EYES};
		} else if (id == 26) { abilities = new Ability[] {Ability.ANGER_POINT, Ability.CHLOROPHYL};
		} else if (id == 27) { abilities = new Ability[] {Ability.ANGER_POINT, Ability.CHLOROPHYL};
		} else if (id == 28) { abilities = new Ability[] {Ability.BERZERK, Ability.CHLOROPHYL};
		} else if (id == 29) { abilities = new Ability[] {Ability.NATURAL_CURE, Ability.POISON_POINT};
		} else if (id == 30) { abilities = new Ability[] {Ability.NATURAL_CURE, Ability.POISON_POINT};
		} else if (id == 31) { abilities = new Ability[] {Ability.NATURAL_CURE, Ability.TECHNICIAN};
		} else if (id == 32) { abilities = new Ability[] {Ability.SWARM, Ability.CHLOROPHYL};
		} else if (id == 33) { abilities = new Ability[] {Ability.SWARM, Ability.CHLOROPHYL};
		} else if (id == 34) { abilities = new Ability[] {Ability.SWARM, Ability.CHLOROPHYL};
		} else if (id == 35) { abilities = new Ability[] {Ability.SWARM, Ability.SWARM};
		} else if (id == 36) { abilities = new Ability[] {Ability.STATIC, Ability.STATIC};
		} else if (id == 37) { abilities = new Ability[] {Ability.LEVITATE, Ability.LEVITATE};
		} else if (id == 38) { abilities = new Ability[] {Ability.LEAF_GUARD, Ability.FLUFFY};
		} else if (id == 39) { abilities = new Ability[] {Ability.MOXIE, Ability.DEFIANT};
		} else if (id == 40) { abilities = new Ability[] {Ability.SERENE_GRACE, Ability.COMPETITIVE};
		} else if (id == 41) { abilities = new Ability[] {Ability.HYPER_CUTTER, Ability.SWARM};
		} else if (id == 42) { abilities = new Ability[] {Ability.HYPER_CUTTER, Ability.JUSTIFIED};
		} else if (id == 43) { abilities = new Ability[] {Ability.SPEED_BOOST, Ability.JUSTIFIED};
		} else if (id == 44) { abilities = new Ability[] {Ability.SWIFT_SWIM, Ability.RAIN_DISH};
		} else if (id == 45) { abilities = new Ability[] {Ability.SWIFT_SWIM, Ability.RAIN_DISH};
		} else if (id == 46) { abilities = new Ability[] {Ability.SWIFT_SWIM, Ability.RAIN_DISH};
		} else if (id == 47) { abilities = new Ability[] {Ability.MAGIC_GUARD, Ability.FLASH_FIRE};
		} else if (id == 48) { abilities = new Ability[] {Ability.STURDY, Ability.UNERODIBLE};
		} else if (id == 49) { abilities = new Ability[] {Ability.STURDY, Ability.UNERODIBLE};
		} else if (id == 50) { abilities = new Ability[] {Ability.STURDY, Ability.UNERODIBLE};
		} else if (id == 51) { abilities = new Ability[] {Ability.STURDY, Ability.FILTER};
		} else if (id == 52) { abilities = new Ability[] {Ability.SHED_SKIN, Ability.REGENERATOR};
		} else if (id == 53) { abilities = new Ability[] {Ability.SYNCHRONIZE, Ability.REGENERATOR};
		} else if (id == 54) { abilities = new Ability[] {Ability.PSYCHIC_SURGE, Ability.REGENERATOR};
		} else if (id == 55) { abilities = new Ability[] {Ability.INTIMIDATE, Ability.TECHNICIAN};
		} else if (id == 56) { abilities = new Ability[] {Ability.INTIMIDATE, Ability.TECHNICIAN};
		} else if (id == 57) { abilities = new Ability[] {Ability.GUTS, Ability.NO_GUARD};
		} else if (id == 58) { abilities = new Ability[] {Ability.GUTS, Ability.NO_GUARD};
		} else if (id == 59) { abilities = new Ability[] {Ability.SNOW_CLOAK, Ability.MOUTHWATER};
		} else if (id == 60) { abilities = new Ability[] {Ability.SNOW_CLOAK, Ability.ICE_BODY};
		} else if (id == 61) { abilities = new Ability[] {Ability.SNOW_CLOAK, Ability.SPARKLY_SURGE};
		} else if (id == 62) { abilities = new Ability[] {Ability.ICY_SCALES, Ability.SWARM};
		} else if (id == 63) { abilities = new Ability[] {Ability.ICY_SCALES, Ability.SWARM};
		} else if (id == 64) { abilities = new Ability[] {Ability.STURDY, Ability.TECHNICIAN};
		} else if (id == 65) { abilities = new Ability[] {Ability.GUTS, Ability.SLUSH_RUSH};
		} else if (id == 66) { abilities = new Ability[] {Ability.ROCK_HEAD, Ability.SAND_FORCE};
		} else if (id == 67) { abilities = new Ability[] {Ability.ROCK_HEAD, Ability.SAND_FORCE};
		} else if (id == 68) { abilities = new Ability[] {Ability.THICK_FAT, Ability.ICE_BODY};
		} else if (id == 69) { abilities = new Ability[] {Ability.THICK_FAT, Ability.ICE_BODY};
		} else if (id == 70) { abilities = new Ability[] {Ability.THICK_FAT, Ability.ICE_BODY};
		} else if (id == 71) { abilities = new Ability[] {Ability.TORRENT, Ability.SLUSH_RUSH};
		} else if (id == 72) { abilities = new Ability[] {Ability.TORRENT, Ability.SLUSH_RUSH};
		} else if (id == 73) { abilities = new Ability[] {Ability.TECHNICIAN, Ability.REGENERATOR};
		} else if (id == 74) { abilities = new Ability[] {Ability.TECHNICIAN, Ability.REGENERATOR};
		} else if (id == 75) { abilities = new Ability[] {Ability.ANTICIPATION, Ability.NATURAL_CURE};
		} else if (id == 76) { abilities = new Ability[] {Ability.ANTICIPATION, Ability.NATURAL_CURE};
		} else if (id == 77) { abilities = new Ability[] {Ability.ANTICIPATION, Ability.NATURAL_CURE};
		} else if (id == 78) { abilities = new Ability[] {Ability.WATER_VEIL, Ability.ANTICIPATION};
		} else if (id == 79) { abilities = new Ability[] {Ability.WATER_VEIL, Ability.SWIFT_SWIM};
		} else if (id == 80) { abilities = new Ability[] {Ability.GRASSY_SURGE, Ability.GRASSY_SURGE};
		} else if (id == 81) { abilities = new Ability[] {Ability.GRASSY_SURGE, Ability.GRASSY_SURGE};
		} else if (id == 82) { abilities = new Ability[] {Ability.PSYCHIC_SURGE, Ability.LEVITATE};
		} else if (id == 83) { abilities = new Ability[] {Ability.PSYCHIC_SURGE, Ability.LEVITATE};
		} else if (id == 84) { abilities = new Ability[] {Ability.PSYCHIC_SURGE, Ability.LEVITATE};
		} else if (id == 85) { abilities = new Ability[] {Ability.SYNCHRONIZE, Ability.NATURAL_CURE};
		} else if (id == 86) { abilities = new Ability[] {Ability.SYNCHRONIZE, Ability.NATURAL_CURE};
		} else if (id == 87) { abilities = new Ability[] {Ability.SYNCHRONIZE, Ability.NATURAL_CURE};
		} else if (id == 88) { abilities = new Ability[] {Ability.JUSTIFIED, Ability.NO_GUARD};
		} else if (id == 89) { abilities = new Ability[] {Ability.SIMPLE, Ability.NORMALIZE};
		} else if (id == 90) { abilities = new Ability[] {Ability.CONTRARY, Ability.CONTRARY};
		} else if (id == 91) { abilities = new Ability[] {Ability.CONTRARY, Ability.CONTRARY};
		} else if (id == 92) { abilities = new Ability[] {Ability.FLASH_FIRE, Ability.INTIMIDATE};
		} else if (id == 93) { abilities = new Ability[] {Ability.FLASH_FIRE, Ability.INTIMIDATE};
		} else if (id == 94) { abilities = new Ability[] {Ability.FLAME_BODY, Ability.FLAME_BODY};
		} else if (id == 95) { abilities = new Ability[] {Ability.FLAME_BODY, Ability.FLAME_BODY};
		} else if (id == 96) { abilities = new Ability[] {Ability.FLAME_BODY, Ability.FLAME_BODY};
		} else if (id == 97) { abilities = new Ability[] {Ability.STEELWORKER, Ability.STEELWORKER};
		} else if (id == 98) { abilities = new Ability[] {Ability.FLAME_BODY, Ability.FLAME_BODY};
		} else if (id == 99) { abilities = new Ability[] {Ability.INTIMIDATE, Ability.INTIMIDATE};
		} else if (id == 100) { abilities = new Ability[] {Ability.LEVITATE, Ability.LEVITATE};
		} else if (id == 101) { abilities = new Ability[] {Ability.SOLID_ROCK, Ability.SOLID_ROCK};
		} else if (id == 102) { abilities = new Ability[] {Ability.SOLID_ROCK, Ability.SOLID_ROCK};
		} else if (id == 103) { abilities = new Ability[] {Ability.SOLID_ROCK, Ability.SOLID_ROCK};
		} else if (id == 104) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 105) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 106) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 107) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 108) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 109) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 110) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 111) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 112) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 113) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 114) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 115) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 116) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 117) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 118) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 119) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 120) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 121) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 122) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 123) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 124) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 125) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 126) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 127) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 128) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 129) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 130) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 131) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 132) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 133) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 134) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 135) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 136) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 137) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 138) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 139) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 140) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 141) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 142) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 143) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 144) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 145) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 146) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 147) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 148) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 149) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 150) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 151) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 152) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 153) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 154) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 155) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 156) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 157) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 158) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 159) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 160) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 161) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 162) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 163) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 164) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 165) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 166) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 167) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 168) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 169) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 170) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 171) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 172) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 173) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 174) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 175) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 176) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 177) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 178) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 179) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 180) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 181) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 182) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 183) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 184) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 185) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 186) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 187) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 188) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 189) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 190) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 191) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 192) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 193) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 194) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 195) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 196) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 197) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 198) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 199) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 200) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 201) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 202) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 203) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 204) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 205) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 206) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 207) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 208) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 209) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 210) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 211) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 212) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 213) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 214) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 215) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 216) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 217) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 218) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 219) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 220) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 221) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 222) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 223) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 224) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 225) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 226) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 227) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 228) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 229) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 230) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 231) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 232) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 233) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 234) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 235) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 236) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else if (id == 237) { abilities = new Ability[] {Ability.OVERGROW, Ability.ROUGH_SKIN};
		} else {
			abilities = new Ability[] {Ability.SAND_VEIL, Ability.SNOW_CLOAK};
		}
		
		ability = abilities[i];
		
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
	    if (this.level - 1 >= this.movebank.length) return;
	    Node node = this.movebank[this.level - 1];
	    while (node != null) {
	        Move move = node.data;
	        if (move != null && !this.contains(move)) {
	            boolean learnedMove = false;
	            for (int i = 0; i < 4; i++) {
	                if (this.moveset[i] == null) {
	                    this.moveset[i] = move;
	                    System.out.println(this.name + " learned " + move.toString() + "!");
	                    learnedMove = true;
	                    break;
	                }
	            }
	            if (!learnedMove) {
	                int choice = Battle.displayMoveOptions(this, move);
	                if (choice == JOptionPane.CLOSED_OPTION) {
	                    System.out.println(this.name + " did not learn " + move.toString() + ".");
	                } else {
		                System.out.println(this.name + " has learned " + move.toString() + " and forgot " + this.moveset[choice] + "!");
		                this.moveset[choice] = move;
	                }
	            }
	        }
	        node = node.next;
	    }
	}


	private Pokemon checkEvo() {
		Pokemon result = null;
		if (id == 1 && level >= 18) {
			result = new Pokemon(2, this);
		} else if (id == 2 && level >= 36) {
			result = new Pokemon(3, this);
		} else if (id == 4 && level >= 16) {
			result = new Pokemon(5, this);
		} else if (id == 5 && level >= 36) {
			result = new Pokemon(6, this);
		} else if (id == 7 && level >= 16) {
			result = new Pokemon(8, this);
		} else if (id == 8 && level >= 36) {
			result = new Pokemon(9, this);
		} else if (id == 10 && level >= 16) {
			result = new Pokemon(11, this);
			
		} else if (id == -1 && level >= 15) {
			result = new Pokemon(-2, this);
		} else if (id == -2 && level >= 35) {
			result = new Pokemon(-3, this);
		} else if (id == -4 && level >= 16) {
			result = new Pokemon(-5, this);
		} else if (id == -5 && level >= 34) {
			result = new Pokemon(-6, this);
		} else if (id == -7 && level >= 20) {
			result = new Pokemon(-8, this);
		} else if (id == -8 && level >= 31) {
			result = new Pokemon(-9, this);
		} else if (id == -10 && level >= 16) {
			result = new Pokemon(-11, this);
		} else if (id == -12 && level >= 28) {
			result = new Pokemon(-13, this);
		} else if (id == -14 && level >= 16) {
			result = new Pokemon(-15, this);
		} else if (id == -16 && level >= 32 && this.contains(Move.ROLLOUT)) {
			result = new Pokemon(-17, this);
		} else if (id == -18 && level >= 21) {
			result = new Pokemon(-19, this);
		} else if (id == -19 && level >= 36 && this.contains(Move.ROCK_BLAST)) {
			result = new Pokemon(-20, this);
		} else if (id == -21 && level >= 25) {
			result = new Pokemon(-22, this);
		} else if (id == -22 && level >= 40) {
			result = new Pokemon(id - 1-23, this);
		} else if (id == -24 && level >= 21) {
			result = new Pokemon(id - 1, this);
		} else if (id == -26 && level >= 18) {
			result = new Pokemon(id - 1, this);
		} else if (id == -27 && level >= 36) {
			result = new Pokemon(id - 1, this);
		} else if (id == -29 && level >= 18) {
			result = new Pokemon(id - 1, this);
		} else if (id == -33 && level >= 21) {
			result = new Pokemon(id - 1, this);
		} else if (id == -38 && level >= 25) {
			result = new Pokemon(id - 1, this);
		} else if (id == -40 && level >= 21) {
			result = new Pokemon(id - 1, this);
		} else if (id == -44 && level >= 30) {
			result = new Pokemon(id - 1, this);
		} else if (id == -46 && level >= 31) {
			result = new Pokemon(id - 1, this);
		} else if (id == -48 && level >= 15) {
			result = new Pokemon(id - 1, this);
		} else if (id == -49 && level >= 25) {
			result = new Pokemon(id - 1, this);
		} else if (id == -51 && level >= 25 && this.contains(Move.MACH_PUNCH)) {
			result = new Pokemon(-52, this);
		} else if (id == -51 && level >= 25 && this.contains(Move.HI_JUMP_KICK)) {
			result = new Pokemon(-53, this);
		} else if (id == -51 && level >= 25) {
			result = new Pokemon(-54, this);
		} else if (id == -55 && level >= 25) {
			result = new Pokemon(id - 1, this);
		} else if (id == -57 && level >= 21) {
			result = new Pokemon(id - 1, this);
		} else if (id == -59 && level >= 28) {
			result = new Pokemon(id - 1, this);
		} else if (id == -141 && level >= 28) {
			result = new Pokemon(-143, this);
		} else if (id == -142 && level >= 28) {
			result = new Pokemon(-144, this);
		} else if (id == -61 && level >= 29) {
			result = new Pokemon(id - 1, this);
		} else if (id == -63 && level >= 25) {
			result = new Pokemon(id - 1, this);
		} else if (id == -65 && level >= 27) {
			result = new Pokemon(id - 1, this);
		} else if (id == -67 && level >= 18) {
			result = new Pokemon(id - 1, this);
		} else if (id == -71 && level >= 30) {
			result = new Pokemon(id - 1, this);
		} else if (id == -72 && level >= 55) {
			result = new Pokemon(id - 1, this);
		} else if (id == -74 && level >= 16) {
			result = new Pokemon(id - 1, this);
		} else if (id == -75 && level >= 36) {
			result = new Pokemon(id - 1, this);
		} else if (id == -77 && level >= 35) {
			result = new Pokemon(id - 1, this);
		} else if (id == -78 && level >= 55) {
			result = new Pokemon(id - 1, this);
		} else if (id == -80 && level >= 28) {
			result = new Pokemon(id - 1, this);
		} else if (id == -82 && level >= 26) {
			result = new Pokemon(id - 1, this);
		} else if (id == -84 && level >= 31) {
			result = new Pokemon(id - 1, this);
		} else if (id == -86 && level >= 36) {
			result = new Pokemon(id - 1, this);
		} else if (id == -89 && level >= 31) {
			result = new Pokemon(id - 1, this);
		} else if (id == -93 && level >= 32) {
			result = new Pokemon(id - 1, this);
		} else if (id == -95 && level >= 30) {
			result = new Pokemon(id - 1, this);
		} else if (id == -97 && level >= 25) {
			result = new Pokemon(id - 1, this);
		} else if (id == -98 && level >= 50) {
			result = new Pokemon(id - 1, this);
		} else if (id == -100 && level >= 35) {
			result = new Pokemon(id - 1, this);
		} else if (id == -101 && level >= 55) {
			result = new Pokemon(id - 1, this);
		} else if (id == -105 && level >= 25) {
			result = new Pokemon(id - 1, this);
		} else if (id == -107 && level >= 30) {
			result = new Pokemon(id - 1, this);
		} else if (id == -109 && level >= 30) {
			result = new Pokemon(id - 1, this);
		} else if (id == -110 && level >= 50) {
			result = new Pokemon(id - 1, this);
		} else if (id == -112 && level >= 25) {
			result = new Pokemon(Battle.dogoEvo(this), this);
		} else if (id == -124 && level >= 16) {
			result = new Pokemon(id - 1, this);
		} else if (id == -125 && level >= 36) {
			result = new Pokemon(id - 1, this);
		} else if (id == -127 && level >= 16) {
			result = new Pokemon(id - 1, this);
		} else if (id == -128 && level >= 36) {
			result = new Pokemon(id - 1, this);
		} else if (id == -130 && level >= 15) {
			result = new Pokemon(id - 1, this);
		} else if (id == -131 && level >= 35) {
			result = new Pokemon(id - 1, this);
		} else if (id == -135 && level >= 50) {
			result = new Pokemon(-138, this);
		} else if (id == -136 && level >= 50) {
			result = new Pokemon(-139, this);
		} else if (id == -137 && level >= 55) {
			result = new Pokemon(-140, this);
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
		double HPnum = (2 * baseStats[0] + ivs[0]) * level;
		stats[0] = (int) (Math.floor(HPnum/100) + level + 10);
		if (id == 131) stats[0] = 1;
		double Atknum = (2 * baseStats[1] + ivs[1]) * level;
		stats[1] = (int) Math.floor((Math.floor(Atknum/100) + 5) * nature[0]);
		double Defnum = (2 * baseStats[2] + ivs[2]) * level;
		stats[2] = (int) Math.floor((Math.floor(Defnum/100) + 5) * nature[1]);
		double SpAnum = (2 * baseStats[3] + ivs[3]) * level;
		stats[3] = (int) Math.floor((Math.floor(SpAnum/100) + 5) * nature[2]);
		double SpDnum = (2 * baseStats[4] + ivs[4]) * level;
		stats[4] = (int) Math.floor((Math.floor(SpDnum/100) + 5) * nature[3]);
		double Spenum = (2 * baseStats[5] + ivs[5]) * level;
		stats[5] = (int) Math.floor((Math.floor(Spenum/100) + 5) * nature[4]);
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
		if (this.id == 1) { this.baseStats = new int[]{58,58,69,46,69,35};
		} else if (this.id == 2) { this.baseStats = new int[]{73,75,80,48,89,37};
		} else if (this.id == 3) { this.baseStats = new int[]{95,87,91,95,110,39};
		} else if (this.id == 4) { this.baseStats = new int[]{60,49,54,74,58,34};
		} else if (this.id == 5) { this.baseStats = new int[]{85,53,69,90,66,36};
		} else if (this.id == 6) { this.baseStats = new int[]{89,59,81,115,75,102};
		} else if (this.id == 7) { this.baseStats = new int[]{45,70,56,46,45,63};
		} else if (this.id == 8) { this.baseStats = new int[]{60,90,76,49,55,70};
		} else if (this.id == 9) { this.baseStats = new int[]{85,115,110,50,85,85};
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

	
	public void move(Pokemon foe, Move move, Player player, Field field, Pokemon[] team, boolean first) {
		if (this.fainted || foe.fainted) return;

		double attackStat;
		double defenseStat;
		int damage = 0;
		int bp = move.basePower;
		PType moveType = move.mtype;
		
		if (!this.vStatuses.contains(Status.CHARGING) && !this.vStatuses.contains(Status.LOCKED) && move != Move.MAGIC_REFLECT && move != Move.TAKE_OVER && move != Move.ABDUCT && move != Move.DESTINY_BOND) this.lastMoveUsed = move;
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
					endMove();
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
		if (this.vStatuses.contains(Status.LOCKED) && this.lastMoveUsed == Move.PETAL_DANCE) move = Move.PETAL_DANCE;
		if (this.vStatuses.contains(Status.LOCKED) && this.lastMoveUsed == Move.ROLLOUT) move = Move.ROLLOUT;
		if (move == Move.OUTRAGE || move == Move.PETAL_DANCE) {
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
//		if (move == Move.MIRROR_MOVE) {
//			System.out.print("\n" + this.name + " used Mirror Move!");
//			move = foe.lastMoveUsed;
//			if (move == null) {
//				System.out.println("\nBut it failed!");
//				this.impressive = false;
//				return;
//			}
//		}
		if (foe.vStatuses.contains(Status.REFLECT) && move != Move.BRICK_BREAK) {
			this.move(this, move, player, field, team, false);
			System.out.println(move + " was reflected on itself!");
			foe.vStatuses.remove(Status.REFLECT);
			return;
		}
		if (this.vStatuses.contains(Status.POSESSED)) {
			this.vStatuses.remove(Status.POSESSED);
			this.move(this, move, player, field, team, false);
			System.out.println(move + " was used on itself!");
			return;
		}
		if (move == Move.FAILED_SUCKER) {
			System.out.println("\n" + this.name + " used Sucker Punch!");
			fail();
			this.impressive = false;
			return;
		}
		if (move == Move.METRONOME) {
			System.out.print("\n" + this.name + " used " + move + "!");
			Move[] moves = Move.values();
			move = moves[new Random().nextInt(moves.length)];
			bp = move.basePower;
		}
		if ((move == Move.FIRST_IMPRESSION || move == Move.BELCH) && !this.impressive) {
			System.out.print("\n" + this.name + " used " + move + "!");
			fail();
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
			endMove();
			this.vStatuses.remove(Status.LOCKED);
			return; // Check for miss
		}
		
		if (move.accuracy <= 100 && move.cat != 2) {
			if (getImmune(foe, moveType)) {
				System.out.println("\n" + this.name + " used " + move + "!");
				System.out.println("It doesn't effect " + foe.name + "...");
				endMove();
				return; // Check for immunity
			}
		} else if (move.accuracy > 100 && move.cat != 2) {
			if (getImmune(foe, moveType)) {
				System.out.println("\n" + this.name + " used " + move + "!");
				System.out.println("It doesn't effect " + foe.name + "...");
				endMove();
				return; // Check for immunity
			}
		}
		if (move.cat != 2 && moveType == PType.GROUND && foe.magCount > 0) {
			System.out.println("\n" + this.name + " used " + move + "!");
			System.out.println("It doesn't effect " + foe.name + "...");
			endMove();
			return; // Check for immunity
		}
		if (foe.magCount > 0) foe.magCount--;
		
		System.out.println("\n" + this.name + " used " + move + "!");
		
		if (move == Move.DREAM_EATER && foe.status != Status.ASLEEP) {
			System.out.println("It doesn't effect " + foe.name + "...");
			endMove();
			return;
		}
		if (move.cat == 2) {
			statusEffect(foe, move, player, field, team);
			endMove();
			return;
		}
		
		if (move.basePower < 0) {
			bp = determineBasePower(foe, move);
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
			if (move == Move.PSYSHOCK) defenseStat = foe.getStat(2) * foe.asModifier(1);
			if (this.status == Status.FROSTBITE) attackStat /= 2;
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
				if (this.status == Status.FROSTBITE) attackStat /= 2;
			}
			if (move.isPhysical() && defenseStat > foe.getStat(2)) attackStat = foe.getStat(2);
			if (!move.isPhysical() && defenseStat > foe.getStat(4)) attackStat = foe.getStat(4);
			damage = calc(attackStat, defenseStat, bp, this.level);
			damage *= 1.5;
			if (foe.ability == Ability.ANGER_POINT) System.out.print("[" + this.name + "'s Anger Point]"); stat(foe, 0, 12);
		} else {
			damage = calc(attackStat, defenseStat, bp, this.level);
		}
		
		// Stab
		if (moveType == this.type1 || moveType == this.type2) {
			damage *= 1.5;
			if (ability == Ability.ADAPTABILITY) damage *= 2;
		}
		
		// Charged
		if (moveType == PType.ELECTRIC && this.vStatuses.contains(Status.CHARGED)) {
			damage *= 2;
			this.vStatuses.remove(Status.CHARGED);
		}
		
		double multiplier = 1;
		// Check type effectiveness
		PType[] resist = getResistances(moveType);
		for (PType type : resist) {
			if (foe.type1 == type) multiplier /= 2;
			if (foe.type2 == type) multiplier /= 2;
		}
		
		// Check type effectiveness
		PType[] weak = getWeaknesses(moveType);
		for (PType type : weak) {
			if (foe.type1 == type) multiplier *= 2;
			if (foe.type2 == type) multiplier *= 2;
		}
		
		damage *= multiplier;
		if (multiplier > 1) System.out.println("It's super effective!");
		if (multiplier < 1) System.out.println("It's not very effective...");
		
		if (move == Move.NIGHT_SHADE || move == Move.SEISMIC_TOSS) damage = this.level;
		if (move == Move.SUPER_FANG) damage = foe.currentHP / 2;
		if (move == Move.DRAGON_RAGE) damage = 40;
		if (move == Move.HORN_DRILL || move == Move.SHEER_COLD || move == Move.GUILLOTINE || move == Move.FISSURE) {
			if (move == Move.SHEER_COLD && (foe.type1 == PType.ICE || foe.type2 == PType.ICE)) {
				System.out.println("It doesn't effect " + foe.name + "...");
				endMove();
				return;
			}
			damage = foe.currentHP;
			System.out.println("It's a one-hit KO!");
		}
		
		if (move == Move.ABSORB || move == Move.DREAM_EATER || move == Move.GIGA_DRAIN || move == Move.MEGA_DRAIN || move == Move.LEECH_LIFE) {
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
			secondaryEffect(foe, move, field, first);
		}
		int recoil = 0;
		if ((move == Move.BRAVE_BIRD || move == Move.FLARE_BLITZ || move == Move.HEAD_SMASH || move == Move.TAKE_DOWN || move == Move.VOLT_TACKLE || move == Move.ROCK_WRECKER) && ability != Ability.ROCK_HEAD) {
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
		if (move == Move.SELF_DESTRUCT || move == Move.EXPLOSION || move == Move.SUPERNOVA_EXPLOSION) {
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
			move(foe, validMoves[new Random().nextInt(validMoves.length)], player, field, team, first);
		}
		endMove();
		return;
	}

	private void endMove() {
		impressive = false;
		success = true;
	}

	private void fail() {
		System.out.println("But it failed!");
		success = false;
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



	private void secondaryEffect(Pokemon foe, Move move, Field field, boolean first) {
		if (move == Move.ACID) {
			stat(foe, 3, -1);
		} else if (move == Move.ACID_SPRAY) {
			stat(foe, 3, -2);
		} else if (move == Move.ANCIENT_POWER) {
			for (int i = 0; i < 5; ++i) {
				stat(this, i, 1);
			}
		} else if (move == Move.AIR_SLASH && first) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.ASTONISH && first) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.BEAT_UP) {
			if (foe.status == Status.HEALTHY) {
				foe.status = Status.FROSTBITE;
				System.out.println(foe.name + " is bleeding!");
			}
//		} else if (move == Move.BIG_BULLET) {
//			foe.paralyze(false);
		} else if (move == Move.BITE && first) {
			foe.vStatuses.add(Status.FLINCHED);
//		} else if (move == Move.BLACK_HOLE) {
//			stat(foe, 5, -1);
//		} else if (move == Move.BLAST_FLAME) {
//			foe.burn(false);
//		} else if (move == Move.BLAZING_SWORD) {
//			foe.burn(false);
		} else if (move == Move.BLUE_FLARE) {
			foe.burn(false);
		} else if (move == Move.BODY_SLAM) {
			foe.paralyze(false);
		} else if (move == Move.BOLT_STRIKE) {
			foe.paralyze(false);
//		} else if (move == Move.BOULDER_CRUSH && first) {
//			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.BOUNCE) {
			foe.paralyze(false);
		} else if (move == Move.BUBBLEBEAM) {
			stat(foe, 4, -1);
//		} else if (move == Move.CHOMP) {
//			stat(foe, 4, -1);
		} else if (move == Move.CLOSE_COMBAT) {
			stat(this, 1, -1);
			stat(this, 3, -1);
		} else if (move == Move.CONFUSION) {
			if (!foe.vStatuses.contains(Status.CONFUSED)) {
				foe.confuse();
			}
//		} else if (move == Move.CONSTRICT) {
//			stat(foe, 4, -1);
		} else if (move == Move.CROSS_POISON) {
			foe.poison(false);
		} else if (move == Move.CRUNCH) {
			stat(foe, 1, -1);
		} else if (move == Move.DARK_PULSE && first) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.DISCHARGE) {
			foe.paralyze(false);
		} else if (move == Move.DISCHARGE) {
			foe.confuse();
//		} else if (move == Move.DOUBLE_BLAST) {
//			if (!foe.vStatuses.contains(Status.CONFUSED)) {
//				foe.confuse();
//			}
//		} else if (move == Move.DOUBLE_SLICE) {
//			if (foe.status == Status.HEALTHY) {
//				foe.status = Status.BLEEDING;
//				System.out.println(foe.name + " is bleeding!");
//			}
		} else if (move == Move.DRACO_METEOR) {
			stat(this, 2, -2);
		} else if (move == Move.DRAGON_RUSH && first) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.DRAGON_BREATH) {
			foe.paralyze(false);
//		} else if (move == Move.DUAL_STAB) {
//			if (foe.status == Status.HEALTHY) {
//				foe.status = Status.FROSTBITE;
//				System.out.println(foe.name + " is bleeding!");
//			}
		} else if (move == Move.EARTH_POWER) {
			stat(foe, 3, -1);
		} else if (move == Move.EMBER) {
			foe.burn(false);
//		} else if (move == Move.FIREBALL) {
//			foe.burn(false);
		} else if (move == Move.FIRE_BLAST) {
			foe.burn(false);
//		} else if (move == Move.FIRE_CHARGE) {
//			int randomNum = ((int) Math.random() * 3);
//			if (randomNum == 0) {
//				foe.burn(false);
//			} else if (randomNum == 1 && first) {
//				foe.vStatuses.add(Status.FLINCHED);
//			}
//			 else if (randomNum == 2) {
//				if (first) foe.vStatuses.add(Status.FLINCHED);
//				foe.burn(false);
//			}
//		} else if (move == Move.FIRE_DASH) {
//			foe.burn(false);
		} else if (move == Move.FIRE_FANG) {
			int randomNum = ((int) Math.random() * 3);
			if (randomNum == 0) {
				foe.burn(false);
			} else if (randomNum == 1 && first) {
				foe.vStatuses.add(Status.FLINCHED);
			}
			 else if (randomNum == 2) {
				if (first) foe.vStatuses.add(Status.FLINCHED);
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
//		} else if (move == Move.FIRE_TAIL) {
//			foe.burn(false);
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
//		} else if (move == Move.GIGA_HIT) {
//			foe.paralyze(false);
		} else if (move == Move.GUNK_SHOT) {
			foe.poison(false);
		} else if (move == Move.HAMMER_ARM) {
			stat(this, 4, -1);
		} else if (move == Move.HEADBUTT && first) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.HEAT_WAVE) {
			foe.burn(false);
		} else if (move == Move.HYPER_FANG && first) {
			foe.vStatuses.add(Status.FLINCHED);
//		} else if (move == Move.INJECT) {
//			foe.poison(false);
		} else if (move == Move.IRON_HEAD && first) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.IRON_TAIL) {
			stat(foe, 1, -1);
		} else if (move == Move.LAVA_PLUME) {
			foe.burn(false);
//		} else if (move == Move.LEAF_KOBE) {
//			foe.paralyze(false);
//		} else if (move == Move.LEAF_PULSE) {
//			stat(foe, 5, -1);
//			int randomNum = ((int) Math.round(Math.random()));
//			if (randomNum == 0) {
//				foe.sleep(false);
//			}
		} else if (move == Move.LEAF_STORM) {
			stat(this, 2, -2);
		} else if (move == Move.LEAF_TORNADO) {
			stat(foe, 5, -1);
		} else if (move == Move.LICK) {
			foe.paralyze(false);
//		} else if (move == Move.LIGHTNING_HEADBUTT) {
//			int randomNum = ((int) Math.random() * 3);
//			if (randomNum == 0) {
//				foe.paralyze(false);
//			} else if (randomNum == 1 && first) {
//				foe.vStatuses.add(Status.FLINCHED);
//			}
//			 else if (randomNum == 2) {
//				if (first) foe.vStatuses.add(Status.FLINCHED);
//				foe.paralyze(false);
//			}
		} else if (move == Move.LOW_KICK) {
			stat(foe, 4, -1);
//		} else if (move == Move.MACHETE_STAB && foe.status == Status.HEALTHY) {
//			foe.status = Status.BLEEDING;
//			System.out.println(foe.name + " is bleeding!");
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
					foe.status = Status.FROSTBITE;
					System.out.println(foe.name + " is Frostbitten!");
				}
				break;
			default:
				return;
			}
		} else if (move == Move.MAGIC_FANG && first) {
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
//		} else if (move == Move.MEGA_KICK) {
//			foe.paralyze(false);
//		} else if (move == Move.MEGA_PUNCH) {
//			foe.paralyze(false);
//		} else if (move == Move.MEGA_SWORD) {
//			foe.paralyze(false);
		} else if (move == Move.MUD_BOMB) {
			stat(foe, 5, -1);
		} else if (move == Move.MUD_SLAP) {
			stat(foe, 5, -1);
//		} else if (move == Move.NEEDLE_SPRAY) {
//			int randomNum = ((int) Math.round(Math.random()));
//			if (randomNum == 0) {
//				foe.paralyze(false);
//			} else {
//				foe.poison(false);
//			}
		} else if (move == Move.OVERHEAT) {
			stat(this, 2, -2);
//		} else if (move == Move.POISON_BALL) {
//			foe.poison(false);
		} else if (move == Move.POISON_FANG) {
			int randomNum = ((int) Math.random() * 3);
			if (randomNum == 0) {
				foe.poison(false);
			} else if (randomNum == 1 && first) {
				foe.vStatuses.add(Status.FLINCHED);
			}
			 else if (randomNum == 2) {
				if (first) foe.vStatuses.add(Status.FLINCHED);
				foe.poison(false);
			}
		} else if (move == Move.POISON_JAB) {
			foe.poison(false);
//		} else if (move == Move.POISON_PUNCH) {
//			foe.poison(false);
		} else if (move == Move.POISON_STING) {
			foe.poison(false);
//		} else if (move == Move.POISONOUS_WATER) {
//			foe.poison(false);
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
//		} else if (move == Move.ROCKET) {
//			foe.paralyze(false);
//		} else if (move == Move.ROOT_CRUSH) {
//			foe.paralyze(false);
		} else if (move == Move.SHADOW_BALL) {
			stat(foe, 3, -1);
//		} else if (move == Move.SHOCK) {
//			foe.paralyze(false);
//		} else if (move == Move.SHURIKEN && foe.status == Status.HEALTHY) {
//			foe.status = Status.BLEEDING;
//			System.out.println(foe.name + " is bleeding!");
		} else if (move == Move.SKY_ATTACK && first) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.SLUDGE) {
			foe.poison(false);
		} else if (move == Move.SLUDGE_BOMB) {
			foe.poison(false);
		} else if (move == Move.SMOG) {
			foe.poison(false);
		} else if (move == Move.SPARK) {
			foe.paralyze(false);
//		} else if (move == Move.SPIKE_JAB) {
//			foe.poison(false);
//		} else if (move == Move.STING && foe.status == Status.HEALTHY) {
//			foe.status = Status.BLEEDING;
//			System.out.println(foe.name + " is bleeding!");
		} else if (move == Move.STOMP && first) {
			foe.vStatuses.add(Status.FLINCHED);
//		} else if (move == Move.STRONG_ARM) {
//			int randomNum = ((int) Math.random() * 3);
//			if (randomNum == 0) {
//				foe.paralyze(false);
//			} else if (randomNum == 1 && first) {
//				foe.vStatuses.add(Status.FLINCHED);
//			}
//			 else if (randomNum == 2) {
//				if (first) foe.vStatuses.add(Status.FLINCHED);
//				foe.paralyze(false);
//			}
//		} else if (move == Move.SUPER_CHARGE && first) {
//			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.SUPERPOWER) {
			stat(this, 0, -1);
			stat(this, 1, -1);
//		} else if (move == Move.SWEEP_KICK) {
//			stat(foe, 0, -1);
		} else if (move == Move.SWORD_SPIN) {
			stat(this, 0, 1);
//		} else if (move == Move.SWORD_STAB && foe.status == Status.HEALTHY) {
//			foe.status = Status.BLEEDING;
//			System.out.println(foe.name + " is bleeding!");
		} else if (move == Move.THUNDER) {
			foe.paralyze(false);
		} else if (move == Move.THUNDERBOLT) {
			foe.paralyze(false);
		} else if (move == Move.THUNDER_FANG) {
			int randomNum = ((int) Math.random() * 3);
			if (randomNum == 0) {
				foe.paralyze(false);
			} else if (randomNum == 1 && first) {
				foe.vStatuses.add(Status.FLINCHED);
			}
			 else if (randomNum == 2) {
				if (first) foe.vStatuses.add(Status.FLINCHED);
				foe.paralyze(false);
			}
//		} else if (move == Move.THUNDER_KICK) {
//			foe.paralyze(false);
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
		} else if (move == Move.TWISTER && first) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.VOLT_TACKLE) {
			foe.paralyze(false);
		} else if (move == Move.WATER_PULSE) {
			foe.confuse();
		} else if (move == Move.WATERFALL && first) {
			foe.vStatuses.add(Status.FLINCHED);
//		} else if (move == Move.WOOD_FANG && first) {
//			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.ZEN_HEADBUTT && first) {
			foe.vStatuses.add(Status.FLINCHED);
		} else if (move == Move.ROCK_SLIDE && first) {
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

	private void statusEffect(Pokemon foe, Move move, Player player, Field field, Pokemon[] team) {
		if (move == Move.ABDUCT) {
			if (this.lastMoveUsed != Move.ABDUCT) {
				foe.vStatuses.add(Status.POSESSED);
				System.out.println(this.name + " posessed " + foe.name + "!");
			} else fail();
			this.impressive = false;
			this.lastMoveUsed = move;
		} else if (move == Move.ACID_ARMOR) {
			stat(this, 1, 2);
		} else if (move == Move.AGILITY) {
			stat(this, 4, 2);
		} else if (move == Move.AMNESIA) {
			stat(this, 3, 2);
		} else if (move == Move.AROMATHERAPY) {
			System.out.println("A soothing aroma wafted through the air!");
			if (team == null) {
				if (this.status != Status.HEALTHY) {
					System.out.println(this.name + " was cured of its " + this.status.getName() + "!");
					this.status = Status.HEALTHY;
				}
			} else {
				for (Pokemon p : team) {
					if (p != null && p.status != Status.HEALTHY) {
						System.out.println(p.name + " was cured of its " + p.status.getName() + "!");
						p.status = Status.HEALTHY;
					}
				}
			}
		} else if (move == Move.AQUA_RING) {
			if (!(this.vStatuses.contains(Status.AQUA_RING))) {
			    this.vStatuses.add(Status.AQUA_RING);
			} else {
			    fail();
			}
		} else if (move == Move.AUTOMOTIZE) {
			stat(this, 4, 2);
//		} else if (move == Move.AUTO_SHOT) {
//			if (!(this.vStatuses.contains(Status.AUTO))) {
//				this.vStatuses.add(Status.AUTO);
//				System.out.println(this.name + " upgraded its weapon!");
//			} else {
//				System.out.println("But it failed!");
//			}
//		} else if (move == Move.BAWL) {
//			stat(foe, 0, -2);
//		} else if (move == Move.BLACK_DUST) {
//			stat(foe, 5, -2);
		} else if (move == Move.BULK_UP) {
			stat(this, 0, 1);
			stat(this, 1, 1);
//		} else if (move == Move.BUZZ) {
//			foe.confuse();
		} else if (move == Move.CHARGE) {
			System.out.println(this.name + " became charged with power!");
			this.vStatuses.add(Status.CHARGED);
			stat(this, 3, 1);
		} else if (move == Move.CHARM) {
			stat(foe, 0, -2);
		} else if (move == Move.COIL) {
			stat(this, 0, 1);
			stat(this, 1, 1);
			stat(this, 5, 1);
		} else if (move == Move.CONFUSE_RAY) {
			if (!foe.vStatuses.contains(Status.CONFUSED)) {
				foe.confuse();
			} else {
				fail();
			}
		} else if (move == Move.COSMIC_POWER) {
			stat(this, 1, 1);
			stat(this, 3, 1);
		} else if (move == Move.COTTON_GUARD) {
			stat(this, 1, 3);
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
				fail();
			}
//		} else if (move == Move.DARK_VOID) {
//			foe.sleep(true);
		} else if (move == Move.DEFENSE_CURL) {
			stat(this, 1, 1);
		} else if (move == Move.DEFOG) {
			stat(foe, 6, -1);
			// TODO: remove hazards
		} else if (move == Move.DESTINY_BOND) {
			if (this.lastMoveUsed != Move.DESTINY_BOND) {
				foe.vStatuses.add(Status.BONDED);
				System.out.println(this.name + " is ready to take its attacker down with it!");
			} else fail();
			this.impressive = false;
			this.lastMoveUsed = move;
			
//		} else if (move == Move.DISAPPEAR) {
//			stat(this, 6, 2);
		} else if (move == Move.DOUBLE_TEAM) {
			stat(this, 6, 1);
		} else if (move == Move.DRAGON_DANCE) {
			stat(this, 0, 1);
			stat(this, 4, 1);
		} else if (move == Move.ELECTRIC_TERRAIN) {
			field.setTerrain(field.new FieldEffect(Effect.ELECTRIC));
		} else if (move == Move.FLASH) {
			stat(foe, 5, -1);
			stat(this, 2, 1);
		} else if (move == Move.ENTRAINMENT) {
			foe.ability = this.ability;
			System.out.println(foe.name + "'s ability became " + foe.ability.toString() + "!");
		} else if (move == Move.FAKE_TEARS) {
			stat(foe, 3, -2);
		} else if (move == Move.FEATHER_DANCE) {
			stat(foe, 1, -2);
		} else if (move == Move.FLATTER) {
			stat(foe, 2, 2);
			if (!foe.vStatuses.contains(Status.CONFUSED)) {
				foe.confuse();
			}
		} else if (move == Move.FORESIGHT) {
			if (foe.type1 == PType.GHOST) foe.type1 = PType.NORMAL;
			if (foe.type2 == PType.GHOST) foe.type2 = PType.NORMAL;
			System.out.println(this.name + " identified " + foe.name + "!");
			stat(this, 5, 1);
		} else if (move == Move.GASTRO_ACID) {
			foe.ability = Ability.NULL;
			System.out.println(foe.name + "'s ability was supressed!");
		} else if (move == Move.GLARE) {
			foe.paralyze(true);
		} else if (move == Move.GLITTER_DANCE) {
			stat(this, 2, 1);
			stat(this, 3, 1);
		} else if (move == Move.GRASS_WHISTLE) {
			foe.sleep(true);
		} else if (move == Move.GRASSY_TERRAIN) {
			field.setTerrain(field.new FieldEffect(Effect.GRASSY));
		} else if (move == Move.GRAVITY) {
			field.setEffect(field.new FieldEffect(Effect.GRAVITY));
		} else if (move == Move.GROWL) {
			stat(foe, 0, -1);
		} else if (move == Move.GROWTH) {
			this.statStages[0] += 1;
			stat(this, 0, 1);
			stat(this, 2, 1);
		} else if (move == Move.SNOWSCAPE) {
			field.setWeather(field.new FieldEffect(Effect.SNOW));
		} else if (move == Move.HARDEN) {
			stat(this, 1, 1);
		} else if (move == Move.HAZE) {
			this.statStages = new int[7];
			foe.statStages = new int[7];
			System.out.println(this.name + "All stat changes were eliminated!");
		} else if (move == Move.HEAL_PULSE) {
			if (foe.currentHP == foe.getStat(0)) {
				System.out.println(foe.name + "'s HP is full!");
			} else {
				foe.currentHP += (foe.getStat(0) / 2);
				if (foe.currentHP > foe.getStat(0)) foe.currentHP = foe.getStat(0);
				System.out.println(foe.name + " restored HP.");
			}
		} else if (move == Move.HONE_CLAWS) {
			stat(this, 0, 1);
			stat(this, 5, 1);
		} else if (move == Move.HOWL) {
			stat(this, 0, 1);
		} else if (move == Move.HYPNOSIS) {
			foe.sleep(true);
//		} else if (move == Move.IGNITE) {
//			foe.burn(true);
		} else if (move == Move.INGRAIN) {
			if (!(this.vStatuses.contains(Status.AQUA_RING))) {
			    this.vStatuses.add(Status.AQUA_RING);
			} else {
			    fail();
			}
			this.vStatuses.add(Status.TRAPPED);
		} else if (move == Move.IRON_DEFENSE) {
			stat(this, 1, 2);
		} else if (move == Move.LIFE_DEW) {
			if (this.currentHP == this.getStat(0)) {
				System.out.println(this.name + "'s HP is full!");
			} else {
				this.currentHP += (this.getStat(0) / 4);
				if (this.currentHP > this.getStat(0)) this.currentHP = this.getStat(0);
				System.out.println(this.name + " restored HP.");
			}
		} else if (move == Move.LEECH_SEED) {
			if (foe.type1 == PType.GRASS || foe.type2 == PType.GRASS) {
				System.out.println("It doesn't effect " + foe.name + "...");
				return;
			}
			if (!foe.vStatuses.contains(Status.LEECHED)) {
				foe.vStatuses.add(Status.LEECHED);
				System.out.println(foe.name + " was seeded!");
			} else {
				fail();
			}
		} else if (move == Move.LEER) {
			stat(foe, 1, -1);
		} else if (move == Move.LIGHT_SCREEN) {
			if (this.trainerOwned) {
				if (!(field.contains(field.playerSide, Effect.LIGHT_SCREEN))) {
					field.playerSide.add(field.new FieldEffect(Effect.LIGHT_SCREEN));
				} else {
					fail();
				}
			} else {
				if (!(field.contains(field.foeSide, Effect.LIGHT_SCREEN))) {
					field.foeSide.add(field.new FieldEffect(Effect.LIGHT_SCREEN));
				} else {
					fail();
				}
			}
		} else if (move == Move.LOCK_ON) {
			System.out.println(this.name + " took aim at " + foe.name + "!\n");
			stat(this, 5, 6);
		} else if (move == Move.MAGIC_POWDER) {
			foe.type1 = PType.MAGIC;
			foe.type2 = null;
			System.out.println(foe.name + "'s type changed to MAGIC!");
		} else if (move == Move.MEAN_LOOK) {
			foe.vStatuses.add(Status.TRAPPED);
			System.out.println(foe.name + " can no longer escape!");
		} else if (move == Move.MAGIC_REFLECT) {
			if (this.lastMoveUsed != Move.MAGIC_REFLECT) {
				this.vStatuses.add(Status.REFLECT);
			} else fail();
			this.impressive = false;
			this.lastMoveUsed = move;
		} else if (move == Move.MAGNET_RISE) {
			if (this.magCount == 0) {
				this.magCount = 5;
				System.out.println(this.name + " floated with electromagnetism!");
			} else {
				fail();
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
				fail();
			}
		} else if (move == Move.ODOR_SLEUTH) {
			if (foe.type1 == PType.GHOST) foe.type1 = PType.NORMAL;
			if (foe.type2 == PType.GHOST) foe.type2 = PType.NORMAL;
			System.out.println(this.name + " identified " + foe.name + "!");
			stat(foe, 6, -1);
		} else if (move == Move.PERISH_SONG) {
			this.perishCount = (this.perishCount == 0) ? 3 : this.perishCount;
			foe.perishCount = (foe.perishCount == 0) ? 3 : foe.perishCount;
//		} else if (move == Move.PHASE_SHIFT) {
//			this.type1 = PType.MAGIC;
//			if (foe.lastMoveUsed != null) {
//				this.type2 = foe.lastMoveUsed.mtype;
//				System.out.println(this.name + " became a " + type1.toString() + " / " + type2.toString() + " type!");
//			} else {
//				System.out.println(this.name + " became a " + type1.toString() + " type!");
//			}
		} else if (move == Move.POISON_GAS) {
			foe.poison(true);
//		} else if (move == Move.POISON_POWDER) {
//			foe.poison(true);
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
		} else if (move == Move.REFLECT) {
			if (this.trainerOwned) {
				if (!(field.contains(field.playerSide, Effect.REFLECT))) {
					field.playerSide.add(field.new FieldEffect(Effect.REFLECT));
				} else {
					fail();
				}
			} else {
				if (!(field.contains(field.foeSide, Effect.REFLECT))) {
					field.foeSide.add(field.new FieldEffect(Effect.REFLECT));
				} else {
					fail();
				}
			}
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
//		} else if (move == Move.STARE) {
//			stat(foe, 0, 1);
//			if (!foe.vStatuses.contains(Status.CONFUSED)) {
//				foe.confuse();
//			}
		} else if (move == Move.STRING_SHOT) {
			stat(foe, 4, -2);
		} else if (move == Move.SUPERSONIC) {
			if (!foe.vStatuses.contains(Status.CONFUSED)) {
				foe.confuse();
			} else {
				fail();
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
			} else fail();
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
	
	public void verifyHP() {
		if (currentHP > this.getStat(0)) currentHP = this.getStat(0);
	}

	private void stat(Pokemon p, int i, int amt) throws IllegalArgumentException {
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
		if (amt == 2) amount = " Sharply rose";
		if (amt == -2) amount = " Harshly fell";
		if (amt > 2) amount = " Drastically rose";
		if (amt < -2) amount = " Dramatically fell";
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
			if (p.type1 == PType.MAGIC) return true;
        	if (p.type2 == PType.MAGIC) return true;
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
			if (p.type1 == PType.GALACTIC) return true;
        	if (p.type2 == PType.GALACTIC) return true;
			return false;
		case FLYING:
			return false;
		case GHOST:
			if (p.type1 == PType.NORMAL) return true;
        	if (p.type2 == PType.NORMAL) return true;
            return false;
		case GRASS:
			if (p.type1 == PType.LIGHT) return true;
        	if (p.type2 == PType.LIGHT) return true;
			return false;
		case GROUND:
			if (p.type1 == PType.FLYING) return true;
        	if (p.type2 == PType.FLYING) return true;
			return false;
		case MAGIC:
        	return false;
		case POISON:
			if (p.type1 == PType.STEEL) return true;
        	if (p.type2 == PType.STEEL) return true;
			return false;
		case STEEL:
			return false;
		case WATER:
			return false;
		case LIGHT:
			if (p.type1 == PType.GRASS) return true;
        	if (p.type2 == PType.GRASS) return true;
			return false;
		case PSYCHIC:
			if (p.type1 == PType.DARK) return true;
        	if (p.type2 == PType.DARK) return true;
			return false;
		case ICE:
			return false;
		case GALACTIC:
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
		  } else if (m.critChance == 3) {
			  return true;
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
	            resistantTypes.add(PType.POISON);
	            resistantTypes.add(PType.FLYING);
	            resistantTypes.add(PType.STEEL);
	            resistantTypes.add(PType.GALACTIC);
	            break;
			case DARK:
				resistantTypes.add(PType.FIGHTING);
	            resistantTypes.add(PType.DARK);
	            resistantTypes.add(PType.GALACTIC);
	            break;
			case DRAGON:
				resistantTypes.add(PType.STEEL);
	            break;
			case ELECTRIC:
				resistantTypes.add(PType.ELECTRIC);
	            resistantTypes.add(PType.GRASS);
	            resistantTypes.add(PType.DRAGON);
	            resistantTypes.add(PType.GHOST);
	            resistantTypes.add(PType.LIGHT);
	            break;
			case FIGHTING:
				resistantTypes.add(PType.GALACTIC);
	            resistantTypes.add(PType.POISON);
	            resistantTypes.add(PType.FLYING);
	            resistantTypes.add(PType.MAGIC);
	            resistantTypes.add(PType.PSYCHIC);
	            break;
			case FIRE:
				resistantTypes.add(PType.FIRE);
	            resistantTypes.add(PType.WATER);
	            resistantTypes.add(PType.ROCK);
	            resistantTypes.add(PType.DRAGON);
	            resistantTypes.add(PType.MAGIC);
	            break;
			case FLYING:
				resistantTypes.add(PType.ELECTRIC);
	            resistantTypes.add(PType.ROCK);
	            resistantTypes.add(PType.STEEL);
	            resistantTypes.add(PType.ICE);
	            break;
			case GHOST:
				resistantTypes.add(PType.DARK);
				resistantTypes.add(PType.LIGHT);
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
	            resistantTypes.add(PType.GALACTIC);
				break;
			case MAGIC:
				resistantTypes.add(PType.POISON);
	            resistantTypes.add(PType.FIGHTING);
	            resistantTypes.add(PType.MAGIC);
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
	            resistantTypes.add(PType.MAGIC);
				break;
			case WATER:
				resistantTypes.add(PType.WATER);
	            resistantTypes.add(PType.GRASS);
	            resistantTypes.add(PType.DRAGON);
				break;
			case LIGHT:
				resistantTypes.add(PType.FIRE);
				resistantTypes.add(PType.WATER);
				resistantTypes.add(PType.STEEL);
				resistantTypes.add(PType.LIGHT);
				resistantTypes.add(PType.BUG);
				break;
			case PSYCHIC:
				resistantTypes.add(PType.PSYCHIC);
				resistantTypes.add(PType.MAGIC);
				break;
			case ICE:
				resistantTypes.add(PType.FIRE);
				resistantTypes.add(PType.WATER);
				resistantTypes.add(PType.ICE);
				resistantTypes.add(PType.STEEL);
				break;
			case GALACTIC:
				resistantTypes.add(PType.ICE);
				resistantTypes.add(PType.ROCK);
				resistantTypes.add(PType.STEEL);
				resistantTypes.add(PType.LIGHT);
				break;
			default:
				break;
	    }
	    PType[] toReturn = new PType[resistantTypes.size()];
	    return resistantTypes.toArray(toReturn);
	}
	
	public PType[] getWeaknesses(PType type) {
	    ArrayList<PType> weakTypes = new ArrayList<>();
	    switch(type) {
	        case NORMAL:
	        	weakTypes.add(PType.MAGIC);
	            break;
	        case ROCK: 
	            weakTypes.add(PType.FIRE);
	            weakTypes.add(PType.ICE);
	            weakTypes.add(PType.FLYING);
	            weakTypes.add(PType.BUG);
	            weakTypes.add(PType.GALACTIC);
	            break;
			case BUG:
				weakTypes.add(PType.GRASS);
	            weakTypes.add(PType.PSYCHIC);
	            weakTypes.add(PType.DARK);
	            weakTypes.add(PType.LIGHT);
	            break;
			case DARK:
				weakTypes.add(PType.MAGIC);
				weakTypes.add(PType.PSYCHIC);
	            weakTypes.add(PType.GHOST);
	            break;
			case DRAGON:
				weakTypes.add(PType.DRAGON);
	            break;
			case ELECTRIC:
				weakTypes.add(PType.WATER);
				weakTypes.add(PType.STEEL);
	            weakTypes.add(PType.FLYING);
	            break;
			case FIGHTING:
				weakTypes.add(PType.NORMAL);
	            weakTypes.add(PType.ROCK);
	            weakTypes.add(PType.ICE);
	            weakTypes.add(PType.DARK);
	            weakTypes.add(PType.STEEL);
	            break;
			case FIRE:
				weakTypes.add(PType.GRASS);
				weakTypes.add(PType.ICE);
	            weakTypes.add(PType.BUG);
	            weakTypes.add(PType.STEEL);
	            break;
			case FLYING:
				weakTypes.add(PType.GRASS);
				weakTypes.add(PType.FIGHTING);
	            weakTypes.add(PType.BUG);
	            weakTypes.add(PType.GALACTIC);
	            break;
			case GHOST:
				weakTypes.add(PType.ELECTRIC);
				weakTypes.add(PType.GHOST);
				weakTypes.add(PType.PSYCHIC);
	            break;
			case GRASS:
				weakTypes.add(PType.WATER);
	            weakTypes.add(PType.ROCK);
	            weakTypes.add(PType.GROUND);
	            weakTypes.add(PType.LIGHT);
	            break;
			case GROUND:
				weakTypes.add(PType.ELECTRIC);
	            weakTypes.add(PType.FIRE);
	            weakTypes.add(PType.POISON);
	            weakTypes.add(PType.ROCK);
	            weakTypes.add(PType.STEEL);
				break;
			case MAGIC:
				weakTypes.add(PType.NORMAL);
	            weakTypes.add(PType.STEEL);
	            weakTypes.add(PType.DRAGON);
				break;
			case POISON:
				weakTypes.add(PType.GRASS);
				weakTypes.add(PType.WATER);
				weakTypes.add(PType.POISON);
				break;
			case STEEL:
				weakTypes.add(PType.ICE);
				weakTypes.add(PType.ROCK);
	            weakTypes.add(PType.LIGHT);
				break;
			case WATER:
				weakTypes.add(PType.FIRE);
	            weakTypes.add(PType.ROCK);
	            weakTypes.add(PType.GROUND);
				break;
			case LIGHT:
				weakTypes.add(PType.ICE);
				weakTypes.add(PType.GHOST);
				weakTypes.add(PType.DARK);
				weakTypes.add(PType.GALACTIC);
				break;
			case PSYCHIC:
				weakTypes.add(PType.FIGHTING);
				weakTypes.add(PType.POISON);
				break;
			case ICE:
				weakTypes.add(PType.GRASS);
				weakTypes.add(PType.GROUND);
				weakTypes.add(PType.FLYING);
				weakTypes.add(PType.DRAGON);
				break;
			case GALACTIC:
				weakTypes.add(PType.FIRE);
				weakTypes.add(PType.FIGHTING);
				weakTypes.add(PType.GROUND);
				break;
			default:
				break;
	    }
	    PType[] toReturn = new PType[weakTypes.size()];
	    return weakTypes.toArray(toReturn);
	}
	
	private void setMoveBank() {
		switch(this.id) {
		case 1:
			movebank = new Node[18];
			movebank[0] = new Node(Move.POUND);
			movebank[0].next = new Node(Move.WITHDRAW);
			movebank[0].next.next = new Node(Move.AROMATHERAPY);
			movebank[6] = new Node(Move.ABSORB);
			movebank[10] = new Node(Move.RAZOR_LEAF);
			movebank[14] = new Node(Move.SAND_ATTACK);
			movebank[17] = new Node(Move.HEADBUTT);
			break;
		case 2:
			movebank = new Node[31];
			movebank[0] = new Node(Move.POUND);
			movebank[0].next = new Node(Move.WITHDRAW);
			movebank[6] = new Node(Move.ABSORB);
			movebank[10] = new Node(Move.RAZOR_LEAF);
			movebank[14] = new Node(Move.SAND_ATTACK);
			movebank[17] = new Node(Move.HEADBUTT);
			movebank[20] = new Node(Move.SLEEP_POWDER);
			movebank[24] = new Node(Move.MEGA_DRAIN);
			movebank[27] = new Node(Move.MAGNITUDE);
			movebank[30] = new Node(Move.ROCK_TOMB);
			break;
		case 3:
		    movebank = new Node[75];
		    movebank[0] = new Node(Move.POUND);
			movebank[0].next = new Node(Move.WITHDRAW);
			movebank[6] = new Node(Move.ABSORB);
			movebank[10] = new Node(Move.RAZOR_LEAF);
			movebank[14] = new Node(Move.SAND_ATTACK);
			movebank[17] = new Node(Move.HEADBUTT);
			movebank[20] = new Node(Move.SLEEP_POWDER);
			movebank[24] = new Node(Move.MEGA_DRAIN);
			movebank[27] = new Node(Move.MAGNITUDE);
			movebank[30] = new Node(Move.ROCK_TOMB);
			movebank[35] = new Node(Move.ROCKFALL_FRENZY);
			movebank[39] = new Node(Move.EARTHQUAKE);
			movebank[41] = new Node(Move.POWER_GEM);
			movebank[44] = new Node(Move.DRAGON_TAIL);
			movebank[49] = new Node(Move.LEAF_STORM);
			movebank[52] = new Node(Move.EARTH_POWER);
			movebank[56] = new Node(Move.SPIKY_SHIELD);
			movebank[64] = new Node(Move.HEAD_SMASH);
			movebank[74] = new Node(Move.FRENZY_PLANT);
		    break;
		case 4:
		    movebank = new Node[14];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[0].next = new Node(Move.LEER);
		    movebank[6] = new Node(Move.EMBER);
		    movebank[10] = new Node(Move.SMOKESCREEN);
		    movebank[13] = new Node(Move.SLAM);
		    break;
		case 5:
		    movebank = new Node[36];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[0].next = new Node(Move.LEER);
		    movebank[6] = new Node(Move.EMBER);
		    movebank[10] = new Node(Move.SMOKESCREEN);
		    movebank[13] = new Node(Move.SLAM);
		    movebank[16] = new Node(Move.HARDEN);
		    movebank[19] = new Node(Move.FLAME_WHEEL);
		    movebank[23] = new Node(Move.HEADBUTT);
		    movebank[26] = new Node(Move.MUD_BOMB);
		    movebank[31] = new Node(Move.FLAMETHROWER);
		    movebank[35] = new Node(Move.IRON_DEFENSE);
		    break;
		case 6:
		    movebank = new Node[75];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[0].next = new Node(Move.LEER);
		    movebank[6] = new Node(Move.EMBER);
		    movebank[10] = new Node(Move.SMOKESCREEN);
		    movebank[13] = new Node(Move.SLAM);
		    movebank[16] = new Node(Move.HARDEN);
		    movebank[19] = new Node(Move.FLAME_WHEEL);
		    movebank[23] = new Node(Move.HEADBUTT);
		    movebank[26] = new Node(Move.MUD_BOMB);
		    movebank[31] = new Node(Move.FLAMETHROWER);
		    movebank[35] = new Node(Move.IRON_DEFENSE);
		    movebank[35].next = new Node(Move.MOLTEN_STEELSPIKE);
		    movebank[40] = new Node(Move.LAVA_PLUME);
		    movebank[45] = new Node(Move.PSYBEAM);
		    movebank[50] = new Node(Move.AURA_SPHERE);
		    movebank[54] = new Node(Move.ERUPTION);
		    movebank[59] = new Node(Move.EARTH_POWER);
		    movebank[69] = new Node(Move.HYPER_BEAM);
		    movebank[74] = new Node(Move.STEEL_BEAM);
		    break;
		case 7:
			movebank = new Node[15];
		    movebank[0] = new Node(Move.SCRATCH);
		    movebank[0].next = new Node(Move.GROWL);
		    movebank[6] = new Node(Move.WATER_GUN);
		    movebank[10] = new Node(Move.BUBBLEBEAM);
		    movebank[14] = new Node(Move.MUD_SPORT);
		    break;
		case 8:
		    movebank = new Node[32];
		    movebank[0] = new Node(Move.SCRATCH);
		    movebank[0].next = new Node(Move.GROWL);
		    movebank[6] = new Node(Move.WATER_GUN);
		    movebank[10] = new Node(Move.BUBBLEBEAM);
		    movebank[14] = new Node(Move.MUD_SPORT);
		    movebank[15] = new Node(Move.BITE);
		    movebank[18] = new Node(Move.MEAN_LOOK);
		    movebank[22] = new Node(Move.SLAM);
		    movebank[25] = new Node(Move.AQUA_JET);
		    movebank[31] = new Node(Move.DRAGON_TAIL);
		    break;
		case 9:
			movebank = new Node[80];
		    movebank[0] = new Node(Move.SCRATCH);
		    movebank[0].next = new Node(Move.GROWL);
		    movebank[6] = new Node(Move.WATER_GUN);
		    movebank[10] = new Node(Move.BUBBLEBEAM);
		    movebank[14] = new Node(Move.MUD_SPORT);
		    movebank[15] = new Node(Move.BITE);
		    movebank[18] = new Node(Move.MEAN_LOOK);
		    movebank[22] = new Node(Move.SLAM);
		    movebank[25] = new Node(Move.AQUA_JET);
		    movebank[31] = new Node(Move.DRAGON_TAIL);
		    movebank[35] = new Node(Move.CHANNELING_BLOW);
		    movebank[37] = new Node(Move.LIQUIDATION);
		    movebank[40] = new Node(Move.HAMMER_ARM);
		    movebank[45] = new Node(Move.DRAGON_RUSH);
		    movebank[49] = new Node(Move.BULK_UP);
		    movebank[53] = new Node(Move.CLOSE_COMBAT);
		    movebank[58] = new Node(Move.GIGA_IMPACT);
		    movebank[65] = new Node(Move.WAVE_CRASH);
		    movebank[71] = new Node(Move.OUTRAGE);
		    movebank[79] = new Node(Move.HYDRO_CANNON);
		    break;
		case 10:
		    movebank = new Node[15];
		    movebank[0] = new Node(Move.POUND);
		    movebank[3] = new Node(Move.BRANCH_POKE);
		    //movebank[4] = new Node(Move.STARE);
		    //movebank[7] = new Node(Move.PUNCH);
		    //movebank[10] = new Node(Move.WOOD_FANG);
		    //movebank[14] = new Node(Move.LEAF_PULSE);
		    break;
		case 11:
		    movebank = new Node[55];
		    movebank[0] = new Node(Move.LEAF_BLADE);
		    //movebank[3] = new Node(Move.LEAF_SLAP);
		    //movebank[4] = new Node(Move.STARE);
		    //movebank[7] = new Node(Move.PUNCH);
		    //movebank[10] = new Node(Move.WOOD_FANG);
		    //movebank[14] = new Node(Move.LEAF_PULSE);
		    movebank[18] = new Node(Move.HEADBUTT);
		    //movebank[23] = new Node(Move.BRANCH_WHACK);
		    //movebank[28] = new Node(Move.LEAF_BALL);
		    movebank[35] = new Node(Move.GLARE);
		    //movebank[44] = new Node(Move.FAINT_ATTACK);
		    movebank[54] = new Node(Move.LEAF_STORM);
		    break;
		case 12:
		    movebank = new Node[27];
		    movebank[0] = new Node(Move.POUND);
		    //movebank[2] = new Node(Move.LEAF_SLAP);
		    movebank[4] = new Node(Move.ROOT_KICK);
		    movebank[5] = new Node(Move.LEER);
		    movebank[8] = new Node(Move.HEADBUTT);
		    movebank[12] = new Node(Move.LEAF_TORNADO);
		    movebank[16] = new Node(Move.ROCK_THROW);
		    movebank[21] = new Node(Move.ROCK_TOMB);
		    movebank[26] = new Node(Move.ROCK_BLAST);
		    break;
		case 13:
		    movebank = new Node[55];
		    movebank[0] = new Node(Move.POUND);
		    //movebank[2] = new Node(Move.LEAF_SLAP);
		    movebank[4] = new Node(Move.ROOT_KICK);
		    movebank[5] = new Node(Move.LEER);
		    movebank[8] = new Node(Move.HEADBUTT);
		    movebank[12] = new Node(Move.LEAF_TORNADO);
		    movebank[16] = new Node(Move.ROCK_THROW);
		    movebank[21] = new Node(Move.ROCK_TOMB);
		    movebank[26] = new Node(Move.ROCK_BLAST);
		    //movebank[30] = new Node(Move.LEAF_BALL);
		    //movebank[34] = new Node(Move.STRONG_ARM);
		    //movebank[35] = new Node(Move.SMASH);
		    movebank[38] = new Node(Move.GROWTH);
		    movebank[44] = new Node(Move.EARTHQUAKE);
		    movebank[48] = new Node(Move.ROLLOUT);
		    movebank[52] = new Node(Move.SUPERPOWER);
		    movebank[54] = new Node(Move.LEAF_STORM);
		    break;
		case 14:
		    movebank = new Node[12];
		    movebank[0] = new Node(Move.SCRATCH);
		    movebank[1] = new Node(Move.LEER);
		    movebank[1].next = new Node(Move.REFLECT);
		    movebank[3] = new Node(Move.TAIL_WHIP);
		    movebank[6] = new Node(Move.TACKLE);
		    //movebank[11] = new Node(Move.TAIL_WHACK);
		    break;
		case 15:
		    movebank = new Node[45];
		    movebank[0] = new Node(Move.SCRATCH);
		    movebank[1] = new Node(Move.LEER);
		    movebank[3] = new Node(Move.TAIL_WHIP);
		    movebank[6] = new Node(Move.TACKLE);
		    //movebank[11] = new Node(Move.TAIL_WHACK);
		    movebank[16] = new Node(Move.BITE);
		    movebank[25] = new Node(Move.BOUNCE);
		    movebank[30] = new Node(Move.ZEN_HEADBUTT);
		    //movebank[37] = new Node(Move.CHOMP);
		    movebank[44] = new Node(Move.HYPER_FANG);
		    break;
		case 16:
		    movebank = new Node[32];
		    movebank[0] = new Node(Move.SCRATCH);
		    movebank[2] = new Node(Move.TAIL_WHIP);
		    //movebank[5] = new Node(Move.NIBBLE);
		    //movebank[10] = new Node(Move.SLAP);
		    movebank[14] = new Node(Move.DOUBLE_SLAP);
		    //movebank[18] = new Node(Move.PUNCH);
		    movebank[22] = new Node(Move.BITE);
		    movebank[27] = new Node(Move.HEADBUTT);
		    movebank[31] = new Node(Move.ROLLOUT);
		    break;
		case 17:
		    movebank = new Node[55];
		    movebank[0] = new Node(Move.SCRATCH);
		    movebank[2] = new Node(Move.TAIL_WHIP);
		    //movebank[5] = new Node(Move.NIBBLE);
		    //movebank[10] = new Node(Move.SLAP);
		    movebank[14] = new Node(Move.DOUBLE_SLAP);
		    //movebank[18] = new Node(Move.PUNCH);
		    movebank[22] = new Node(Move.BITE);
		    movebank[27] = new Node(Move.HEADBUTT);
		    movebank[31] = new Node(Move.ROLLOUT);
		    //movebank[34] = new Node(Move.CHOMP);
		    movebank[41] = new Node(Move.ZEN_HEADBUTT);
		    movebank[48] = new Node(Move.SUPER_FANG);
		    movebank[54] = new Node(Move.HYPER_FANG);
		    break;
		case 18:
		    movebank = new Node[21];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[2] = new Node(Move.DEFENSE_CURL);
		    movebank[5] = new Node(Move.MUD_SPORT);
		    movebank[8] = new Node(Move.ROCK_POLISH);
		    movebank[11] = new Node(Move.ROCK_THROW);
		    movebank[15] = new Node(Move.ROCK_TOMB);
		    movebank[20] = new Node(Move.MAGNITUDE);
		    break;
		case 19:
		    movebank = new Node[36];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[2] = new Node(Move.DEFENSE_CURL);
		    movebank[5] = new Node(Move.MUD_SPORT);
		    movebank[8] = new Node(Move.ROCK_POLISH);
		    movebank[11] = new Node(Move.ROCK_THROW);
		    movebank[15] = new Node(Move.ROCK_TOMB);
		    movebank[20] = new Node(Move.MAGNITUDE);
		    movebank[25] = new Node(Move.ROLLOUT);
		    movebank[30] = new Node(Move.SLAM);
		    movebank[35] = new Node(Move.ROCK_BLAST);
		    break;
		case 20:
		    movebank = new Node[70];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[2] = new Node(Move.DEFENSE_CURL);
		    movebank[5] = new Node(Move.MUD_SPORT);
		    movebank[8] = new Node(Move.ROCK_POLISH);
		    movebank[11] = new Node(Move.ROCK_THROW);
		    movebank[15] = new Node(Move.ROCK_TOMB);
		    movebank[20] = new Node(Move.MAGNITUDE);
		    movebank[25] = new Node(Move.ROLLOUT);
		    movebank[30] = new Node(Move.SLAM);
		    movebank[35] = new Node(Move.ROCK_BLAST);
		    movebank[38] = new Node(Move.STONE_EDGE);
		    movebank[42] = new Node(Move.EARTHQUAKE);
		    //movebank[46] = new Node(Move.ROCKET);
		    movebank[50] = new Node(Move.SUPERPOWER);
		    movebank[54] = new Node(Move.EARTH_POWER);
		    movebank[60] = new Node(Move.FIRE_BLAST);
		    movebank[69] = new Node(Move.HYPER_BEAM);
		    break;
		case 21:
		    movebank = new Node[21];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[1] = new Node(Move.DEFENSE_CURL);
		    movebank[4] = new Node(Move.ROCK_POLISH);
		    movebank[10] = new Node(Move.SLAM);
		    movebank[15] = new Node(Move.RAPID_SPIN);
		    movebank[20] = new Node(Move.ROLLOUT);
		    break;
		case 22:
		    movebank = new Node[39];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[1] = new Node(Move.DEFENSE_CURL);
		    movebank[4] = new Node(Move.ROCK_POLISH);
		    movebank[10] = new Node(Move.SLAM);
		    movebank[15] = new Node(Move.RAPID_SPIN);
		    movebank[20] = new Node(Move.ROLLOUT);
		    movebank[27] = new Node(Move.STOMP);
		    movebank[33] = new Node(Move.ROCK_TOMB);
		    //movebank[38] = new Node(Move.ROCK_BLADE);
		    break;
		case 23:
		    movebank = new Node[60];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[1] = new Node(Move.DEFENSE_CURL);
		    movebank[4] = new Node(Move.ROCK_POLISH);
		    movebank[10] = new Node(Move.SLAM);
		    movebank[15] = new Node(Move.RAPID_SPIN);
		    movebank[20] = new Node(Move.ROLLOUT);
		    movebank[27] = new Node(Move.STOMP);
		    movebank[33] = new Node(Move.ROCK_TOMB);
		    //movebank[38] = new Node(Move.ROCK_BLADE);
		    movebank[39] = new Node(Move.WING_ATTACK);
		    //movebank[44] = new Node(Move.BOULDER_SLAM);
		    //movebank[49] = new Node(Move.SPIKE_SHOT);
		    //movebank[54] = new Node(Move.DOUBLE_EDGE);
		    movebank[59] = new Node(Move.SKY_ATTACK);
		    break;
		case 24:
			movebank = new Node[19];
			//movebank[0] = new Node(Move.NIBBLE);
			movebank[1] = new Node(Move.LEER);
			movebank[3] = new Node(Move.BUBBLE);
			movebank[6] = new Node(Move.BITE);
			movebank[9] = new Node(Move.WATER_GUN);
			//movebank[13] = new Node(Move.CHOMP);
			movebank[18] = new Node(Move.HYPER_FANG);
			break;
		case 25:
			movebank = new Node[55];
			movebank[0] = new Node(Move.THUNDER);
			movebank[1] = new Node(Move.LEER);
			movebank[3] = new Node(Move.BUBBLE);
			movebank[6] = new Node(Move.BITE);
			movebank[9] = new Node(Move.WATER_GUN);
			//movebank[13] = new Node(Move.CHOMP);
			movebank[18] = new Node(Move.HYPER_FANG);
			movebank[22] = new Node(Move.SPARK);
			movebank[27] = new Node(Move.DOUBLE_SLAP);
			//movebank[31] = new Node(Move.TIDAL_WAVE);
			//movebank[36] = new Node(Move.DOUBLE_BLAST);
			movebank[45] = new Node(Move.THUNDERBOLT);
			movebank[54] = new Node(Move.ELECTROBALL);
			break;
		case 26:
			movebank = new Node[15];
			movebank[0] = new Node(Move.TACKLE);
			movebank[4] = new Node(Move.SAND_ATTACK);
			movebank[6] = new Node(Move.FORESIGHT);
			movebank[9] = new Node(Move.GUST);
			movebank[12] = new Node(Move.PECK);
			movebank[14] = new Node(Move.WING_ATTACK);
			break;
		case 27:
			movebank = new Node[33];
			movebank[0] = new Node(Move.TACKLE);
			movebank[4] = new Node(Move.SAND_ATTACK);
			movebank[6] = new Node(Move.FORESIGHT);
			movebank[9] = new Node(Move.GUST);
			movebank[12] = new Node(Move.PECK);
			movebank[14] = new Node(Move.WING_ATTACK);
			movebank[17] = new Node(Move.QUICK_ATTACK);
			movebank[19] = new Node(Move.AIR_CUTTER);
			movebank[23] = new Node(Move.TWISTER);
			movebank[27] = new Node(Move.AGILITY);
			movebank[32] = new Node(Move.ROOST);
			break;
		case 28:
			movebank = new Node[55];
			movebank[0] = new Node(Move.TACKLE);
			movebank[4] = new Node(Move.SAND_ATTACK);
			movebank[6] = new Node(Move.FORESIGHT);
			movebank[9] = new Node(Move.GUST);
			movebank[12] = new Node(Move.PECK);
			movebank[14] = new Node(Move.WING_ATTACK);
			movebank[17] = new Node(Move.QUICK_ATTACK);
			movebank[19] = new Node(Move.AIR_CUTTER);
			movebank[23] = new Node(Move.TWISTER);
			movebank[27] = new Node(Move.AGILITY);
			movebank[32] = new Node(Move.ROOST);
			//movebank[39] = new Node(Move.MIRROR_MOVE);
			movebank[44] = new Node(Move.DRILL_PECK);
			movebank[49] = new Node(Move.AIR_SLASH);
			movebank[54] = new Node(Move.BRAVE_BIRD);
			break;
		case 29:
			movebank = new Node[15];
			movebank[0] = new Node(Move.SAND_ATTACK);
			movebank[9] = new Node(Move.PECK);
			movebank[14] = new Node(Move.WING_ATTACK);
			break;
		case 30:
			movebank = new Node[55];
			movebank[0] = new Node(Move.SAND_ATTACK);
			movebank[9] = new Node(Move.PECK);
			movebank[14] = new Node(Move.WING_ATTACK);
			movebank[17] = new Node(Move.GUST);
			movebank[20] = new Node(Move.TWISTER);
			movebank[25] = new Node(Move.FURY_ATTACK);
			movebank[32] = new Node(Move.AGILITY);
			movebank[39] = new Node(Move.ROOST);
			//movebank[49] = new Node(Move.MIRROR_MOVE);
			movebank[54] = new Node(Move.DRILL_PECK);
			break;
			
			
		case -1:
			movebank = new Node[15];
			//movebank[0] = new Node(Move.POKE);
			movebank[1] = new Node(Move.GROWL);
			movebank[5] = new Node(Move.LEECH_LIFE);
			//movebank[8] = new Node(Move.LEAF_SLAP);
			movebank[11] = new Node(Move.DOUBLE_SLAP);
			movebank[14] = new Node(Move.LEAF_TORNADO);
			break;
		case -2:
			movebank = new Node[35];
			//movebank[0] = new Node(Move.POKE);
			movebank[1] = new Node(Move.GROWL);
			movebank[5] = new Node(Move.LEECH_LIFE);
			//movebank[8] = new Node(Move.LEAF_SLAP);
			movebank[11] = new Node(Move.DOUBLE_SLAP);
			movebank[14] = new Node(Move.LEAF_TORNADO);
			movebank[14] = new Node(Move.LEAF_TORNADO);
			movebank[16] = new Node(Move.MAGICAL_LEAF);
			movebank[20] = new Node(Move.MEGA_DRAIN);
			//movebank[22] = new Node(Move.BRANCH_WHACK);
			movebank[26] = new Node(Move.BODY_SLAM);
			movebank[31] = new Node(Move.GYRO_BALL);
			movebank[34] = new Node(Move.LEAF_STORM);
			break;
		case -3:
		    movebank = new Node[75];
		    movebank[0] = new Node(Move.ROCK_TOMB);
		    movebank[1] = new Node(Move.GROWL);
		    movebank[5] = new Node(Move.LEECH_LIFE);
		    //movebank[8] = new Node(Move.LEAF_SLAP);
		    movebank[11] = new Node(Move.DOUBLE_SLAP);
		    movebank[14] = new Node(Move.LEAF_TORNADO);
		    movebank[14] = new Node(Move.LEAF_TORNADO);
		    movebank[16] = new Node(Move.MAGICAL_LEAF);
		    movebank[20] = new Node(Move.MEGA_DRAIN);
		    //movebank[22] = new Node(Move.BRANCH_WHACK);
		    movebank[26] = new Node(Move.BODY_SLAM);
		    movebank[31] = new Node(Move.GYRO_BALL);
		    movebank[34] = new Node(Move.LEAF_STORM);
		    //movebank[37] = new Node(Move.GRASS_PUNCH);
		    movebank[39] = new Node(Move.ROCK_BLAST);
		    //movebank[47] = new Node(Move.ROOT_CRUSH);
		    //movebank[54] = new Node(Move.BOULDER_CRUSH);
		    movebank[64] = new Node(Move.FRENZY_PLANT);
		    movebank[70] = new Node(Move.SUPERPOWER);
		    movebank[74] = new Node(Move.ROCK_SLIDE);
		    break;
		case -4:
		    movebank = new Node[15];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[0].next = new Node(Move.HARDEN);
		    movebank[1] = new Node(Move.SCREECH);
		    //movebank[6] = new Node(Move.IGNITE);
		    movebank[6].next = new Node(Move.FIRE_SPIN);
		    movebank[6].next.next = new Node(Move.AGILITY);
		    movebank[8] = new Node(Move.EMBER);
		    movebank[11] = new Node(Move.SLAM);
		    movebank[14] = new Node(Move.FIRE_FANG);
		    break;
		case -5:
		    movebank = new Node[33];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[1] = new Node(Move.SCREECH);
		    //movebank[6] = new Node(Move.IGNITE);
		    movebank[8] = new Node(Move.EMBER);
		    movebank[11] = new Node(Move.SLAM);
		    movebank[14] = new Node(Move.FIRE_FANG);
		    //movebank[18] = new Node(Move.BLACK_DUST);
		    movebank[23] = new Node(Move.FIRE_SPIN);
		    movebank[27] = new Node(Move.SELF_DESTRUCT);
		    movebank[32] = new Node(Move.LAVA_PLUME);
		    break;
		case -6:
		    movebank = new Node[65];
		    movebank[0] = new Node(Move.OVERHEAT);
		    movebank[1] = new Node(Move.SCREECH);
		    //movebank[6] = new Node(Move.IGNITE);
		    movebank[8] = new Node(Move.EMBER);
		    movebank[11] = new Node(Move.SLAM);
		    movebank[14] = new Node(Move.FIRE_FANG);
		    //movebank[18] = new Node(Move.BLACK_DUST);
		    movebank[23] = new Node(Move.FIRE_SPIN);
		    movebank[27] = new Node(Move.SELF_DESTRUCT);
		    movebank[32] = new Node(Move.LAVA_PLUME);
		    movebank[38] = new Node(Move.EXPLOSION);
		    movebank[44] = new Node(Move.ERUPTION);
		    movebank[54] = new Node(Move.FIRE_BLAST);
		    movebank[64] = new Node(Move.BLAST_BURN);
		    break;
		case -7:
		    movebank = new Node[18];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[1] = new Node(Move.TAIL_WHIP);
		    movebank[5] = new Node(Move.SAND_ATTACK);
		    movebank[8] = new Node(Move.BUBBLE);
		    movebank[10] = new Node(Move.BITE);
		    movebank[13] = new Node(Move.BUBBLEBEAM);
		    movebank[17] = new Node(Move.WATER_GUN);
		    break;
		case -8:
		    movebank = new Node[28];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[1] = new Node(Move.TAIL_WHIP);
		    movebank[5] = new Node(Move.SAND_ATTACK);
		    movebank[8] = new Node(Move.BUBBLE);
		    movebank[10] = new Node(Move.BITE);
		    movebank[13] = new Node(Move.BUBBLEBEAM);
		    movebank[17] = new Node(Move.WATER_GUN);
		    movebank[20] = new Node(Move.QUICK_ATTACK);
		    movebank[27] = new Node(Move.AQUA_JET);
		    break;
		case -9:
		    movebank = new Node[75];
		    movebank[0] = new Node(Move.WATER_PULSE);
		    movebank[1] = new Node(Move.TAIL_WHIP);
		    movebank[5] = new Node(Move.SAND_ATTACK);
		    movebank[8] = new Node(Move.BUBBLE);
		    movebank[10] = new Node(Move.BITE);
		    movebank[13] = new Node(Move.BUBBLEBEAM);
		    movebank[17] = new Node(Move.WATER_GUN);
		    movebank[20] = new Node(Move.QUICK_ATTACK);
		    movebank[27] = new Node(Move.AQUA_JET);
		    movebank[33] = new Node(Move.SWIFT);
		    movebank[38] = new Node(Move.BRINE);
		    //movebank[44] = new Node(Move.DOUBLE_JET);
		    movebank[54] = new Node(Move.HYDRO_PUMP);
		    movebank[64] = new Node(Move.HYDRO_CANNON);
		    movebank[70] = new Node(Move.GYRO_BALL);
		    movebank[74] = new Node(Move.HYPER_BEAM);
		    break;
		case -10:
		    movebank = new Node[15];
		    movebank[0] = new Node(Move.POUND);
		    //movebank[3] = new Node(Move.LEAF_SLAP);
		    //movebank[4] = new Node(Move.STARE);
		    //movebank[7] = new Node(Move.PUNCH);
		    //movebank[10] = new Node(Move.WOOD_FANG);
		    //movebank[14] = new Node(Move.LEAF_PULSE);
		    break;
		case -11:
		    movebank = new Node[55];
		    movebank[0] = new Node(Move.LEAF_BLADE);
		    //movebank[3] = new Node(Move.LEAF_SLAP);
		    //movebank[4] = new Node(Move.STARE);
		    //movebank[7] = new Node(Move.PUNCH);
		    //movebank[10] = new Node(Move.WOOD_FANG);
		    //movebank[14] = new Node(Move.LEAF_PULSE);
		    movebank[18] = new Node(Move.HEADBUTT);
		    //movebank[23] = new Node(Move.BRANCH_WHACK);
		    //movebank[28] = new Node(Move.LEAF_BALL);
		    movebank[35] = new Node(Move.GLARE);
		    //movebank[44] = new Node(Move.FAINT_ATTACK);
		    movebank[54] = new Node(Move.LEAF_STORM);
		    break;
		case -12:
		    movebank = new Node[27];
		    movebank[0] = new Node(Move.POUND);
		    //movebank[2] = new Node(Move.LEAF_SLAP);
		    movebank[4] = new Node(Move.ROOT_KICK);
		    movebank[5] = new Node(Move.LEER);
		    movebank[8] = new Node(Move.HEADBUTT);
		    movebank[12] = new Node(Move.LEAF_TORNADO);
		    movebank[16] = new Node(Move.ROCK_THROW);
		    movebank[21] = new Node(Move.ROCK_TOMB);
		    movebank[26] = new Node(Move.ROCK_BLAST);
		    break;
		case -13:
		    movebank = new Node[55];
		    movebank[0] = new Node(Move.POUND);
		    //movebank[2] = new Node(Move.LEAF_SLAP);
		    movebank[4] = new Node(Move.ROOT_KICK);
		    movebank[5] = new Node(Move.LEER);
		    movebank[8] = new Node(Move.HEADBUTT);
		    movebank[12] = new Node(Move.LEAF_TORNADO);
		    movebank[16] = new Node(Move.ROCK_THROW);
		    movebank[21] = new Node(Move.ROCK_TOMB);
		    movebank[26] = new Node(Move.ROCK_BLAST);
		    //movebank[30] = new Node(Move.LEAF_BALL);
		    //movebank[34] = new Node(Move.STRONG_ARM);
		    //movebank[35] = new Node(Move.SMASH);
		    movebank[38] = new Node(Move.GROWTH);
		    movebank[44] = new Node(Move.EARTHQUAKE);
		    movebank[48] = new Node(Move.ROLLOUT);
		    movebank[52] = new Node(Move.SUPERPOWER);
		    movebank[54] = new Node(Move.LEAF_STORM);
		    break;
		case -14:
		    movebank = new Node[12];
		    movebank[0] = new Node(Move.SCRATCH);
		    movebank[1] = new Node(Move.LEER);
		    movebank[1].next = new Node(Move.REFLECT);
		    movebank[3] = new Node(Move.TAIL_WHIP);
		    movebank[6] = new Node(Move.TACKLE);
		    //movebank[11] = new Node(Move.TAIL_WHACK);
		    break;
		case -15:
		    movebank = new Node[45];
		    movebank[0] = new Node(Move.SCRATCH);
		    movebank[1] = new Node(Move.LEER);
		    movebank[3] = new Node(Move.TAIL_WHIP);
		    movebank[6] = new Node(Move.TACKLE);
		    //movebank[11] = new Node(Move.TAIL_WHACK);
		    movebank[16] = new Node(Move.BITE);
		    movebank[25] = new Node(Move.BOUNCE);
		    movebank[30] = new Node(Move.ZEN_HEADBUTT);
		    //movebank[37] = new Node(Move.CHOMP);
		    movebank[44] = new Node(Move.HYPER_FANG);
		    break;
		case -16:
		    movebank = new Node[32];
		    movebank[0] = new Node(Move.SCRATCH);
		    movebank[2] = new Node(Move.TAIL_WHIP);
		    //movebank[5] = new Node(Move.NIBBLE);
		    //movebank[10] = new Node(Move.SLAP);
		    movebank[14] = new Node(Move.DOUBLE_SLAP);
		    //movebank[18] = new Node(Move.PUNCH);
		    movebank[22] = new Node(Move.BITE);
		    movebank[27] = new Node(Move.HEADBUTT);
		    movebank[31] = new Node(Move.ROLLOUT);
		    break;
		case -17:
		    movebank = new Node[55];
		    movebank[0] = new Node(Move.SCRATCH);
		    movebank[2] = new Node(Move.TAIL_WHIP);
		    //movebank[5] = new Node(Move.NIBBLE);
		    //movebank[10] = new Node(Move.SLAP);
		    movebank[14] = new Node(Move.DOUBLE_SLAP);
		    //movebank[18] = new Node(Move.PUNCH);
		    movebank[22] = new Node(Move.BITE);
		    movebank[27] = new Node(Move.HEADBUTT);
		    movebank[31] = new Node(Move.ROLLOUT);
		    //movebank[34] = new Node(Move.CHOMP);
		    movebank[41] = new Node(Move.ZEN_HEADBUTT);
		    movebank[48] = new Node(Move.SUPER_FANG);
		    movebank[54] = new Node(Move.HYPER_FANG);
		    break;
		case -18:
		    movebank = new Node[21];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[2] = new Node(Move.DEFENSE_CURL);
		    movebank[5] = new Node(Move.MUD_SPORT);
		    movebank[8] = new Node(Move.ROCK_POLISH);
		    movebank[11] = new Node(Move.ROCK_THROW);
		    movebank[15] = new Node(Move.ROCK_TOMB);
		    movebank[20] = new Node(Move.MAGNITUDE);
		    break;
		case -19:
		    movebank = new Node[36];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[2] = new Node(Move.DEFENSE_CURL);
		    movebank[5] = new Node(Move.MUD_SPORT);
		    movebank[8] = new Node(Move.ROCK_POLISH);
		    movebank[11] = new Node(Move.ROCK_THROW);
		    movebank[15] = new Node(Move.ROCK_TOMB);
		    movebank[20] = new Node(Move.MAGNITUDE);
		    movebank[25] = new Node(Move.ROLLOUT);
		    movebank[30] = new Node(Move.SLAM);
		    movebank[35] = new Node(Move.ROCK_BLAST);
		    break;
		case -20:
		    movebank = new Node[70];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[2] = new Node(Move.DEFENSE_CURL);
		    movebank[5] = new Node(Move.MUD_SPORT);
		    movebank[8] = new Node(Move.ROCK_POLISH);
		    movebank[11] = new Node(Move.ROCK_THROW);
		    movebank[15] = new Node(Move.ROCK_TOMB);
		    movebank[20] = new Node(Move.MAGNITUDE);
		    movebank[25] = new Node(Move.ROLLOUT);
		    movebank[30] = new Node(Move.SLAM);
		    movebank[35] = new Node(Move.ROCK_BLAST);
		    movebank[38] = new Node(Move.STONE_EDGE);
		    movebank[42] = new Node(Move.EARTHQUAKE);
		    //movebank[46] = new Node(Move.ROCKET);
		    movebank[50] = new Node(Move.SUPERPOWER);
		    movebank[54] = new Node(Move.EARTH_POWER);
		    movebank[60] = new Node(Move.FIRE_BLAST);
		    movebank[69] = new Node(Move.HYPER_BEAM);
		    break;
		case -21:
		    movebank = new Node[21];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[1] = new Node(Move.DEFENSE_CURL);
		    movebank[4] = new Node(Move.ROCK_POLISH);
		    movebank[10] = new Node(Move.SLAM);
		    movebank[15] = new Node(Move.RAPID_SPIN);
		    movebank[20] = new Node(Move.ROLLOUT);
		    break;
		case -22:
		    movebank = new Node[39];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[1] = new Node(Move.DEFENSE_CURL);
		    movebank[4] = new Node(Move.ROCK_POLISH);
		    movebank[10] = new Node(Move.SLAM);
		    movebank[15] = new Node(Move.RAPID_SPIN);
		    movebank[20] = new Node(Move.ROLLOUT);
		    movebank[27] = new Node(Move.STOMP);
		    movebank[33] = new Node(Move.ROCK_TOMB);
		    //movebank[38] = new Node(Move.ROCK_BLADE);
		    break;
		case -23:
		    movebank = new Node[60];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[1] = new Node(Move.DEFENSE_CURL);
		    movebank[4] = new Node(Move.ROCK_POLISH);
		    movebank[10] = new Node(Move.SLAM);
		    movebank[15] = new Node(Move.RAPID_SPIN);
		    movebank[20] = new Node(Move.ROLLOUT);
		    movebank[27] = new Node(Move.STOMP);
		    movebank[33] = new Node(Move.ROCK_TOMB);
		    //movebank[38] = new Node(Move.ROCK_BLADE);
		    movebank[39] = new Node(Move.WING_ATTACK);
		    //movebank[44] = new Node(Move.BOULDER_SLAM);
		    //movebank[49] = new Node(Move.SPIKE_SHOT);
		    //movebank[54] = new Node(Move.DOUBLE_EDGE);
		    movebank[59] = new Node(Move.SKY_ATTACK);
		    break;
		case -24:
			movebank = new Node[19];
			//movebank[0] = new Node(Move.NIBBLE);
			movebank[1] = new Node(Move.LEER);
			movebank[3] = new Node(Move.BUBBLE);
			movebank[6] = new Node(Move.BITE);
			movebank[9] = new Node(Move.WATER_GUN);
			//movebank[13] = new Node(Move.CHOMP);
			movebank[18] = new Node(Move.HYPER_FANG);
			break;
		case -25:
			movebank = new Node[55];
			movebank[0] = new Node(Move.THUNDER);
			movebank[1] = new Node(Move.LEER);
			movebank[3] = new Node(Move.BUBBLE);
			movebank[6] = new Node(Move.BITE);
			movebank[9] = new Node(Move.WATER_GUN);
			//movebank[13] = new Node(Move.CHOMP);
			movebank[18] = new Node(Move.HYPER_FANG);
			movebank[22] = new Node(Move.SPARK);
			movebank[27] = new Node(Move.DOUBLE_SLAP);
			//movebank[31] = new Node(Move.TIDAL_WAVE);
			//movebank[36] = new Node(Move.DOUBLE_BLAST);
			movebank[45] = new Node(Move.THUNDERBOLT);
			movebank[54] = new Node(Move.ELECTROBALL);
			break;
		case -26:
			movebank = new Node[15];
			movebank[0] = new Node(Move.TACKLE);
			movebank[4] = new Node(Move.SAND_ATTACK);
			movebank[6] = new Node(Move.FORESIGHT);
			movebank[9] = new Node(Move.GUST);
			movebank[12] = new Node(Move.PECK);
			movebank[14] = new Node(Move.WING_ATTACK);
			break;
		case -27:
			movebank = new Node[33];
			movebank[0] = new Node(Move.TACKLE);
			movebank[4] = new Node(Move.SAND_ATTACK);
			movebank[6] = new Node(Move.FORESIGHT);
			movebank[9] = new Node(Move.GUST);
			movebank[12] = new Node(Move.PECK);
			movebank[14] = new Node(Move.WING_ATTACK);
			movebank[17] = new Node(Move.QUICK_ATTACK);
			movebank[19] = new Node(Move.AIR_CUTTER);
			movebank[23] = new Node(Move.TWISTER);
			movebank[27] = new Node(Move.AGILITY);
			movebank[32] = new Node(Move.ROOST);
			break;
		case -28:
			movebank = new Node[55];
			movebank[0] = new Node(Move.TACKLE);
			movebank[4] = new Node(Move.SAND_ATTACK);
			movebank[6] = new Node(Move.FORESIGHT);
			movebank[9] = new Node(Move.GUST);
			movebank[12] = new Node(Move.PECK);
			movebank[14] = new Node(Move.WING_ATTACK);
			movebank[17] = new Node(Move.QUICK_ATTACK);
			movebank[19] = new Node(Move.AIR_CUTTER);
			movebank[23] = new Node(Move.TWISTER);
			movebank[27] = new Node(Move.AGILITY);
			movebank[32] = new Node(Move.ROOST);
			//movebank[39] = new Node(Move.MIRROR_MOVE);
			movebank[44] = new Node(Move.DRILL_PECK);
			movebank[49] = new Node(Move.AIR_SLASH);
			movebank[54] = new Node(Move.BRAVE_BIRD);
			break;
		case -29:
			movebank = new Node[15];
			movebank[0] = new Node(Move.SAND_ATTACK);
			movebank[9] = new Node(Move.PECK);
			movebank[14] = new Node(Move.WING_ATTACK);
			break;
		case -30:
			movebank = new Node[55];
			movebank[0] = new Node(Move.SAND_ATTACK);
			movebank[9] = new Node(Move.PECK);
			movebank[14] = new Node(Move.WING_ATTACK);
			movebank[17] = new Node(Move.GUST);
			movebank[20] = new Node(Move.TWISTER);
			movebank[25] = new Node(Move.FURY_ATTACK);
			movebank[32] = new Node(Move.AGILITY);
			movebank[39] = new Node(Move.ROOST);
			//movebank[49] = new Node(Move.MIRROR_MOVE);
			movebank[54] = new Node(Move.DRILL_PECK);
			break;
		case -31:
			movebank = new Node[40];
			movebank[0] = new Node(Move.TACKLE);
			movebank[2] = new Node(Move.LEER);
			movebank[5] = new Node(Move.SAND_ATTACK);
			movebank[9] = new Node(Move.GUST);
			movebank[12] = new Node(Move.PECK);
			movebank[16] = new Node(Move.WATER_GUN);
			movebank[20] = new Node(Move.HEADBUTT);
			movebank[29] = new Node(Move.WING_ATTACK);
			movebank[39] = new Node(Move.DRILL_PECK);
			break;
		case -32:
			movebank = new Node[45];
			movebank[0] = new Node(Move.SCRATCH);
			//movebank[4] = new Node(Move.LEAF_SLAP);
			movebank[9] = new Node(Move.GUST);
			//movebank[15] = new Node(Move.LEAF_KOBE);
			movebank[16] = new Node(Move.PECK);
			//movebank[21] = new Node(Move.LEAF_GUST);
			//movebank[29] = new Node(Move.LEAF_BALL);
			movebank[34] = new Node(Move.WING_ATTACK);
			movebank[44] = new Node(Move.DRILL_PECK);
			break;
		case -33:
			movebank = new Node[16];
			movebank[0] = new Node(Move.POISON_STING);
			movebank[2] = new Node(Move.STRING_SHOT);
			movebank[5] = new Node(Move.LEECH_LIFE);
			movebank[9] = new Node(Move.SUPERSONIC);
			movebank[11] = new Node(Move.SMOKESCREEN);
			movebank[15] = new Node(Move.SLUDGE);
			break;
		case -34:
			movebank = new Node[55];
			movebank[0] = new Node(Move.POISON_STING);
			movebank[2] = new Node(Move.STRING_SHOT);
			movebank[5] = new Node(Move.LEECH_LIFE);
			movebank[9] = new Node(Move.SUPERSONIC);
			movebank[11] = new Node(Move.SMOKESCREEN);
			movebank[15] = new Node(Move.SLUDGE);
			//movebank[20] = new Node(Move.POISON_POWDER);
			//movebank[25] = new Node(Move.FAINT_ATTACK);
			movebank[33] = new Node(Move.BUG_BITE);
			movebank[38] = new Node(Move.FIRST_IMPRESSION);
			movebank[43] = new Node(Move.DARK_PULSE);
			movebank[48] = new Node(Move.CRUNCH);
			movebank[54] = new Node(Move.POISON_FANG);
			break;
		case -35:
			movebank = new Node[40];
			movebank[0] = new Node(Move.POISON_STING);
			//movebank[2] = new Node(Move.BUZZ);
			//movebank[4] = new Node(Move.SLAP);
			movebank[11] = new Node(Move.DOUBLE_SLAP);
			movebank[24] = new Node(Move.WING_ATTACK);
			//movebank[27] = new Node(Move.STING);
			movebank[39] = new Node(Move.BUG_BUZZ);
			break;
		case -36:
			movebank = new Node[40];
			movebank[0] = new Node(Move.TACKLE);
			//movebank[2] = new Node(Move.BUZZ);
			//movebank[4] = new Node(Move.SLAP);
			movebank[12] = new Node(Move.DOUBLE_SLAP);
			movebank[19] = new Node(Move.BUG_BITE);
			movebank[24] = new Node(Move.DOUBLE_HIT);
			movebank[29] = new Node(Move.WING_ATTACK);
			//movebank[34] = new Node(Move.INJECT);
			movebank[39] = new Node(Move.BUG_BUZZ);
			break;
		case -37:
			movebank = new Node[50];
			movebank[0] = new Node(Move.LICK);
			movebank[2] = new Node(Move.SMOKESCREEN);
			movebank[7] = new Node(Move.SMOG);
			movebank[12] = new Node(Move.POISON_GAS);
			movebank[18] = new Node(Move.SLUDGE);
			//movebank[23] = new Node(Move.POISON_BALL);
			movebank[28] = new Node(Move.SHADOW_BALL);
			movebank[36] = new Node(Move.SLUDGE_BOMB);
			//movebank[49] = new Node(Move.POISONOUS_WATER);
			break;
		case -38:
			movebank = new Node[24];
			movebank[0] = new Node(Move.NIGHT_SHADE);
			movebank[2] = new Node(Move.SCREECH);
			movebank[6] = new Node(Move.SCARY_FACE);
			movebank[8] = new Node(Move.CHARM);
			movebank[10] = new Node(Move.HYPNOSIS);
			movebank[14] = new Node(Move.CURSE);
			movebank[18] = new Node(Move.SUCKER_PUNCH);
			movebank[23] = new Node(Move.SHADOW_BALL);
			break;
		case -39:
			movebank = new Node[55];
			movebank[0] = new Node(Move.NIGHT_SHADE);
			movebank[2] = new Node(Move.SCREECH);
			movebank[6] = new Node(Move.SCARY_FACE);
			movebank[8] = new Node(Move.CHARM);
			movebank[10] = new Node(Move.HYPNOSIS);
			movebank[14] = new Node(Move.CURSE);
			movebank[18] = new Node(Move.SUCKER_PUNCH);
			movebank[23] = new Node(Move.SHADOW_BALL);
			movebank[24] = new Node(Move.IRON_DEFENSE);
			//movebank[28] = new Node(Move.SPIKE_SLAM);
			//movebank[30] = new Node(Move.STRONG_ARM);
			movebank[31] = new Node(Move.DOUBLE_HIT);
			movebank[33] = new Node(Move.DREAM_EATER);
			movebank[38] = new Node(Move.IRON_TAIL);
			movebank[41] = new Node(Move.NIGHTMARE);
			movebank[48] = new Node(Move.GYRO_BALL);
			movebank[54] = new Node(Move.DESTINY_BOND);
			break;
		case -40:
			movebank = new Node[18];
			movebank[0] = new Node(Move.NIGHT_SHADE);
			//movebank[3] = new Node(Move.DISAPPEAR);
			movebank[7] = new Node(Move.LICK);
			//movebank[11] = new Node(Move.BAWL);
			movebank[12] = new Node(Move.SCREECH);
			movebank[14] = new Node(Move.HYPNOSIS);
			movebank[17] = new Node(Move.CURSE);
			break;
		case -41:
			movebank = new Node[55];
			movebank[0] = new Node(Move.NIGHT_SHADE);
			//movebank[3] = new Node(Move.DISAPPEAR);
			movebank[7] = new Node(Move.LICK);
			//movebank[11] = new Node(Move.BAWL);
			movebank[12] = new Node(Move.SCREECH);
			movebank[14] = new Node(Move.HYPNOSIS);
			movebank[17] = new Node(Move.CURSE);
			movebank[21] = new Node(Move.SUCKER_PUNCH);
			movebank[24] = new Node(Move.SHADOW_BALL);
			//movebank[28] = new Node(Move.FAINT_ATTACK);
			movebank[32] = new Node(Move.MINIMIZE);
			movebank[38] = new Node(Move.DARK_PULSE);
			movebank[41] = new Node(Move.DREAM_EATER);
			movebank[48] = new Node(Move.NIGHTMARE);
			movebank[51] = new Node(Move.TAKE_OVER);
			movebank[54] = new Node(Move.DESTINY_BOND);
			break;
		case -42:
			movebank = new Node[45];
			movebank[0] = new Node(Move.HEADBUTT);
			//movebank[9] = new Node(Move.BLIND);
			movebank[14] = new Node(Move.SPARK);
			movebank[19] = new Node(Move.THUNDER_WAVE);
			//movebank[29] = new Node(Move.LIGHTNING_HEADBUTT);
			movebank[34] = new Node(Move.THUNDERBOLT);
			movebank[44] = new Node(Move.DISCHARGE);
			break;
		case -43:
			movebank = new Node[45];
			movebank[0] = new Node(Move.SCRATCH);
			movebank[1] = new Node(Move.TAIL_WHIP);
			movebank[4] = new Node(Move.SPARK);
			movebank[7] = new Node(Move.CHARGE);
			movebank[12] = new Node(Move.THUNDER_WAVE);
			movebank[17] = new Node(Move.WHIP_SMASH);
			//movebank[24] = new Node(Move.LIGHTNING_HEADBUTT);
			movebank[29] = new Node(Move.QUICK_ATTACK);
			movebank[37] = new Node(Move.DOUBLE_TEAM);
			movebank[44] = new Node(Move.THUNDERBOLT);
			break;
		case -44:
			movebank = new Node[23];
			//movebank[0] = new Node(Move.SHOCK);
			movebank[3] = new Node(Move.CHARGE);
			movebank[7] = new Node(Move.SPARK);
			movebank[11] = new Node(Move.WRAP);
			movebank[18] = new Node(Move.THUNDER_WAVE);
			movebank[22] = new Node(Move.THUNDERSHOCK);
			break;
		case -45:
			movebank = new Node[55];
			//movebank[0] = new Node(Move.SHOCK);
			movebank[3] = new Node(Move.CHARGE);
			movebank[7] = new Node(Move.SPARK);
			movebank[11] = new Node(Move.WRAP);
			movebank[18] = new Node(Move.THUNDER_WAVE);
			movebank[22] = new Node(Move.THUNDERSHOCK);
			movebank[30] = new Node(Move.DOUBLE_HIT);
			movebank[39] = new Node(Move.GYRO_BALL);
			movebank[47] = new Node(Move.DISCHARGE);
			movebank[54] = new Node(Move.THUNDER);
			break;
		case -46:
			movebank = new Node[26];
			//movebank[0] = new Node(Move.ZAP);
			movebank[4] = new Node(Move.POUND);
			movebank[7] = new Node(Move.CHARGE);
			movebank[10] = new Node(Move.SPARK);
			movebank[13] = new Node(Move.TAIL_WHIP);
			movebank[18] = new Node(Move.SLAM);
			movebank[23] = new Node(Move.THUNDER_WAVE);
			movebank[25] = new Node(Move.WRAP);
			break;
		case -47:
			movebank = new Node[55];
			//movebank[0] = new Node(Move.ZAP);
			movebank[4] = new Node(Move.POUND);
			movebank[7] = new Node(Move.CHARGE);
			movebank[10] = new Node(Move.SPARK);
			movebank[13] = new Node(Move.TAIL_WHIP);
			movebank[18] = new Node(Move.SLAM);
			movebank[23] = new Node(Move.THUNDER_WAVE);
			movebank[25] = new Node(Move.WRAP);
			movebank[30] = new Node(Move.SHOCK_WAVE);
			//movebank[34] = new Node(Move.GIGA_HIT);
			movebank[35] = new Node(Move.THUNDERBOLT);
			movebank[43] = new Node(Move.DISCHARGE);
			movebank[54] = new Node(Move.HYPER_BEAM);
			break;
		case -48:
			movebank = new Node[12];
			movebank[0] = new Node(Move.TACKLE);
			movebank[2] = new Node(Move.LEER);
			movebank[4] = new Node(Move.LOW_KICK);
			movebank[7] = new Node(Move.KARATE_CHOP);
			movebank[11] = new Node(Move.TORNADO_SPIN);
			break;
		case -49:
			movebank = new Node[24];
			movebank[0] = new Node(Move.TACKLE);
			movebank[2] = new Node(Move.LEER);
			movebank[4] = new Node(Move.LOW_KICK);
			movebank[7] = new Node(Move.KARATE_CHOP);
			movebank[11] = new Node(Move.TORNADO_SPIN);
			movebank[14] = new Node(Move.SWORD_SPIN);
			//movebank[17] = new Node(Move.SWORD_STAB);
			//movebank[20] = new Node(Move.DOUBLE_SLICE);
			//movebank[23] = new Node(Move.SWORD_SLASH);
			break;
		case -50:
			movebank = new Node[55];
			movebank[0] = new Node(Move.TACKLE);
			movebank[2] = new Node(Move.LEER);
			movebank[4] = new Node(Move.LOW_KICK);
			movebank[7] = new Node(Move.KARATE_CHOP);
			movebank[11] = new Node(Move.TORNADO_SPIN);
			movebank[14] = new Node(Move.SWORD_SPIN);
			//movebank[17] = new Node(Move.SWORD_STAB);
			//movebank[20] = new Node(Move.DOUBLE_SLICE);
			//movebank[23] = new Node(Move.SWORD_SLASH);
			//movebank[24] = new Node(Move.SHURIKEN);
			//movebank[25] = new Node(Move.MACHETE_STAB);
			//movebank[26] = new Node(Move.GUNSHOT);
			movebank[29] = new Node(Move.FIRST_IMPRESSION);
			movebank[34] = new Node(Move.AGILITY);
			movebank[39] = new Node(Move.FIRE_PUNCH);
			//movebank[44] = new Node(Move.BLAZING_SWORD);
			movebank[49] = new Node(Move.AURA_SPHERE);
			movebank[54] = new Node(Move.CLOSE_COMBAT);
			break;
		case -51:
			movebank = new Node[25];
			movebank[0] = new Node(Move.LOW_KICK);
			movebank[1] = new Node(Move.LEER);
			movebank[6] = new Node(Move.KARATE_CHOP);
			movebank[14] = new Node(Move.SEISMIC_TOSS);
			movebank[16] = new Node(Move.REVENGE);
			//movebank[21] = new Node(Move.VITAL_THROW);
			movebank[22] = new Node(Move.MACH_PUNCH);
			movebank[23] = new Node(Move.HI_JUMP_KICK);
			//movebank[24] = new Node(Move.SWORD_SLICE);
			break;
		case -52:
			movebank = new Node[55];
			movebank[0] = new Node(Move.LOW_KICK);
			movebank[1] = new Node(Move.LEER);
			movebank[6] = new Node(Move.KARATE_CHOP);
			movebank[14] = new Node(Move.SEISMIC_TOSS);
			movebank[16] = new Node(Move.REVENGE);
			//movebank[21] = new Node(Move.VITAL_THROW);
			movebank[22] = new Node(Move.MACH_PUNCH);
			movebank[26] = new Node(Move.COMET_PUNCH);
			movebank[33] = new Node(Move.THUNDER_PUNCH);
			movebank[34] = new Node(Move.FIRE_PUNCH);
			//movebank[35] = new Node(Move.POISON_PUNCH);
			movebank[37] = new Node(Move.SKY_UPPERCUT);
			//movebank[44] = new Node(Move.MEGA_PUNCH);
			movebank[54] = new Node(Move.CLOSE_COMBAT);
			break;
		case -53:
			movebank = new Node[55];
			movebank[0] = new Node(Move.LOW_KICK);
			movebank[1] = new Node(Move.LEER);
			movebank[6] = new Node(Move.KARATE_CHOP);
			movebank[14] = new Node(Move.SEISMIC_TOSS);
			movebank[16] = new Node(Move.REVENGE);
			//movebank[21] = new Node(Move.VITAL_THROW);
			movebank[23] = new Node(Move.HI_JUMP_KICK);
			movebank[30] = new Node(Move.ROOT_KICK);
			movebank[33] = new Node(Move.DOUBLE_KICK);
			//movebank[38] = new Node(Move.MEGA_KICK);
			//movebank[45] = new Node(Move.SWEEP_KICK);
			//movebank[48] = new Node(Move.THUNDER_KICK);
			movebank[54] = new Node(Move.CLOSE_COMBAT);
			break;
		case -54:
			movebank = new Node[55];
			movebank[0] = new Node(Move.LOW_KICK);
			movebank[1] = new Node(Move.LEER);
			movebank[6] = new Node(Move.KARATE_CHOP);
			movebank[14] = new Node(Move.SEISMIC_TOSS);
			movebank[16] = new Node(Move.REVENGE);
			//movebank[21] = new Node(Move.VITAL_THROW);
			//movebank[24] = new Node(Move.SWORD_SLICE);
			//movebank[26] = new Node(Move.SWORD_STAB);
			//movebank[32] = new Node(Move.DOUBLE_SLICE);
			movebank[36] = new Node(Move.SWORD_SPIN);
			//movebank[39] = new Node(Move.MEGA_SWORD);
			//movebank[43] = new Node(Move.BLAZING_SWORD);
			movebank[54] = new Node(Move.CLOSE_COMBAT);
			break;
		case -55:
			movebank = new Node[24];
			movebank[0] = new Node(Move.POUND);
			movebank[3] = new Node(Move.HARDEN);
			movebank[6] = new Node(Move.MUD_SLAP);
			movebank[11] = new Node(Move.EMBER);
			movebank[14] = new Node(Move.MINIMIZE);
			movebank[15] = new Node(Move.SLUDGE);
			movebank[17] = new Node(Move.POISON_GAS);
			movebank[20] = new Node(Move.MUD_BOMB);
			//movebank[23] = new Node(Move.ASSURANCE);
			break;
		case -56:
			movebank = new Node[55];
			movebank[0] = new Node(Move.POUND);
			movebank[3] = new Node(Move.HARDEN);
			movebank[6] = new Node(Move.MUD_SLAP);
			movebank[11] = new Node(Move.EMBER);
			movebank[14] = new Node(Move.MINIMIZE);
			movebank[15] = new Node(Move.SLUDGE);
			movebank[17] = new Node(Move.POISON_GAS);
			movebank[20] = new Node(Move.MUD_BOMB);
			//movebank[23] = new Node(Move.ASSURANCE);
			movebank[26] = new Node(Move.FLASH_CANNON);
			movebank[30] = new Node(Move.SLUDGE_BOMB);
			movebank[36] = new Node(Move.DOUBLE_HIT);
			//movebank[39] = new Node(Move.BIG_BULLET);
			movebank[44] = new Node(Move.GUNK_SHOT);
			//movebank[54] = new Node(Move.ROCKET);
			break;
		case -57:
		    movebank = new Node[21];
		    movebank[0] = new Node(Move.LEECH_LIFE);
		    movebank[3] = new Node(Move.SUPERSONIC);
		    movebank[4] = new Node(Move.POISON_STING);
		    movebank[7] = new Node(Move.HAZE);
		    movebank[10] = new Node(Move.BITE);
		    movebank[14] = new Node(Move.ASTONISH);
		    movebank[17] = new Node(Move.WING_ATTACK);
		    movebank[20] = new Node(Move.CONFUSE_RAY);
		    break;
		case -58:
		    movebank = new Node[55];
		    movebank[0] = new Node(Move.LEECH_LIFE);
		    movebank[3] = new Node(Move.SUPERSONIC);
		    movebank[4] = new Node(Move.POISON_STING);
		    movebank[7] = new Node(Move.HAZE);
		    movebank[10] = new Node(Move.BITE);
		    movebank[14] = new Node(Move.ASTONISH);
		    movebank[17] = new Node(Move.WING_ATTACK);
		    movebank[20] = new Node(Move.CONFUSE_RAY);
		    movebank[24] = new Node(Move.TOXIC);
		    movebank[28] = new Node(Move.GUNK_SHOT);
		    movebank[32] = new Node(Move.SCREECH);
		    movebank[38] = new Node(Move.AIR_CUTTER);
		    movebank[40] = new Node(Move.POISON_FANG);
		    movebank[44] = new Node(Move.CRUNCH);
		    movebank[49] = new Node(Move.CROSS_POISON);
		    movebank[54] = new Node(Move.BRAVE_BIRD);
		    break;
		case -59:
		    movebank = new Node[25];
		    movebank[0] = new Node(Move.SCRATCH);
		    movebank[2] = new Node(Move.TAIL_WHIP);
		    //movebank[6] = new Node(Move.POISON_POWDER);
		    movebank[8] = new Node(Move.VINE_WHIP);
		    movebank[11] = new Node(Move.SLEEP_POWDER);
		    movebank[12] = new Node(Move.MEGA_DRAIN);
		    movebank[14] = new Node(Move.RAZOR_LEAF);
		    movebank[18] = new Node(Move.SYNTHESIS);
		    movebank[21] = new Node(Move.FIRE_FANG);
		    movebank[22] = new Node(Move.THUNDER_FANG);
		    movebank[23] = new Node(Move.POISON_FANG);
		    movebank[24] = new Node(Move.FURY_SWIPES);
		    break;
		case -60:
		    movebank = new Node[55];
		    movebank[0] = new Node(Move.SCRATCH);
		    movebank[2] = new Node(Move.TAIL_WHIP);
		    //movebank[6] = new Node(Move.POISON_POWDER);
		    movebank[8] = new Node(Move.VINE_WHIP);
		    movebank[11] = new Node(Move.SLEEP_POWDER);
		    movebank[12] = new Node(Move.MEGA_DRAIN);
		    movebank[14] = new Node(Move.RAZOR_LEAF);
		    movebank[18] = new Node(Move.SYNTHESIS);
		    movebank[21] = new Node(Move.FIRE_FANG);
		    movebank[22] = new Node(Move.THUNDER_FANG);
		    movebank[23] = new Node(Move.POISON_FANG);
		    movebank[24] = new Node(Move.FURY_SWIPES);
		    movebank[27] = new Node(Move.ROLLOUT);
		    movebank[28] = new Node(Move.SLASH);
		    movebank[29] = new Node(Move.TAKE_DOWN);
		    movebank[31] = new Node(Move.GIGA_DRAIN);
		    movebank[36] = new Node(Move.ANCIENT_POWER);
		    movebank[40] = new Node(Move.CRUNCH);
		    movebank[45] = new Node(Move.SKULL_BASH);
		    movebank[49] = new Node(Move.GIGA_IMPACT);
		    movebank[54] = new Node(Move.SOLAR_BEAM);
		    break;
		case -61:
		    movebank = new Node[28];
		    movebank[0] = new Node(Move.POISON_STING);
		    movebank[2] = new Node(Move.SUPERSONIC);
		    //movebank[6] = new Node(Move.CONSTRICT);
		    movebank[10] = new Node(Move.WATER_GUN);
		    movebank[13] = new Node(Move.ACID);
		    movebank[17] = new Node(Move.BUBBLEBEAM);
		    movebank[19] = new Node(Move.WRAP);
		    movebank[24] = new Node(Move.WATER_PULSE);
		    movebank[27] = new Node(Move.AQUA_JET);
		    break;
		case -62:
		    movebank = new Node[55];
		    movebank[0] = new Node(Move.POISON_STING);
		    movebank[2] = new Node(Move.SUPERSONIC);
		    //movebank[6] = new Node(Move.CONSTRICT);
		    movebank[10] = new Node(Move.WATER_GUN);
		    movebank[13] = new Node(Move.ACID);
		    movebank[17] = new Node(Move.BUBBLEBEAM);
		    movebank[19] = new Node(Move.WRAP);
		    movebank[24] = new Node(Move.WATER_PULSE);
		    movebank[27] = new Node(Move.AQUA_JET);
		    movebank[29] = new Node(Move.DOUBLE_HIT);
		    movebank[32] = new Node(Move.POISON_GAS);
		    movebank[36] = new Node(Move.POISON_JAB);
		    movebank[40] = new Node(Move.DIVE);
		    movebank[44] = new Node(Move.SLUDGE_BOMB);
		    movebank[54] = new Node(Move.HYDRO_PUMP);
		    break;
		case -63:
		    movebank = new Node[20];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[2] = new Node(Move.LEER);
		    movebank[5] = new Node(Move.BUBBLE);
		    movebank[8] = new Node(Move.WATER_GUN);
		    movebank[12] = new Node(Move.DOUBLE_TEAM);
		    movebank[14] = new Node(Move.CONFUSE_RAY);
		    movebank[19] = new Node(Move.WATER_PULSE);
		    break;
		case -64:
		    movebank = new Node[55];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[2] = new Node(Move.LEER);
		    movebank[5] = new Node(Move.BUBBLE);
		    movebank[8] = new Node(Move.WATER_GUN);
		    movebank[12] = new Node(Move.DOUBLE_TEAM);
		    movebank[14] = new Node(Move.CONFUSE_RAY);
		    movebank[19] = new Node(Move.WATER_PULSE);
		    movebank[24] = new Node(Move.BODY_SLAM);
		    movebank[27] = new Node(Move.HAMMER_ARM);
		    //movebank[29] = new Node(Move.SPIKE_JAB);
		    movebank[35] = new Node(Move.GYRO_BALL);
		    movebank[38] = new Node(Move.BRINE);
		    movebank[40] = new Node(Move.AQUA_JET);
		    movebank[44] = new Node(Move.DRAGON_PULSE);
		    movebank[54] = new Node(Move.SKY_UPPERCUT);
		    break;
		case -65:
		    movebank = new Node[27];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[2] = new Node(Move.GROWL);
		    //movebank[5] = new Node(Move.SLAP);
		    movebank[10] = new Node(Move.DOUBLE_SLAP);
		    movebank[13] = new Node(Move.WATER_GUN);
		    movebank[14] = new Node(Move.SUPERSONIC);
		    movebank[17] = new Node(Move.HEADBUTT);
		    movebank[21] = new Node(Move.WING_ATTACK);
		    movebank[26] = new Node(Move.WATER_PULSE);
		    break;
		case -66:
		    movebank = new Node[55];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[2] = new Node(Move.GROWL);
		    //movebank[5] = new Node(Move.SLAP);
		    movebank[10] = new Node(Move.DOUBLE_SLAP);
		    movebank[13] = new Node(Move.WATER_GUN);
		    movebank[14] = new Node(Move.SUPERSONIC);
		    movebank[17] = new Node(Move.HEADBUTT);
		    movebank[21] = new Node(Move.WING_ATTACK);
		    movebank[26] = new Node(Move.WATER_PULSE);
		    movebank[27] = new Node(Move.TWISTER);
		    movebank[28] = new Node(Move.GUST);
		    movebank[31] = new Node(Move.DRAGON_RAGE);
		    movebank[33] = new Node(Move.FLAIL);
		    movebank[38] = new Node(Move.AQUA_RING);
		    movebank[40] = new Node(Move.SCREECH);
		    movebank[42] = new Node(Move.AGILITY);
		    movebank[47] = new Node(Move.DRAGON_RUSH);
		    movebank[54] = new Node(Move.OUTRAGE);
		    break;
		case -67:
		    movebank = new Node[17];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[2] = new Node(Move.LEER);
		    movebank[4] = new Node(Move.CHARM);
		    movebank[7] = new Node(Move.BUBBLE);
		    movebank[10] = new Node(Move.WATER_GUN);
		    movebank[13] = new Node(Move.RAPID_SPIN);
		    movebank[16] = new Node(Move.HEADBUTT);
		    break;
		case -68:
		    movebank = new Node[55];
		    movebank[0] = new Node(Move.TACKLE);
		    movebank[2] = new Node(Move.LEER);
		    movebank[4] = new Node(Move.CHARM);
		    movebank[7] = new Node(Move.BUBBLE);
		    movebank[10] = new Node(Move.WATER_GUN);
		    movebank[13] = new Node(Move.RAPID_SPIN);
		    movebank[16] = new Node(Move.HEADBUTT);
		    movebank[20] = new Node(Move.WATER_PULSE);
		    movebank[23] = new Node(Move.SHELL_SMASH);
		    movebank[27] = new Node(Move.BRINE);
		    movebank[32] = new Node(Move.CRUNCH);
		    movebank[37] = new Node(Move.SKULL_BASH);
		    movebank[40] = new Node(Move.AQUA_JET);
		    movebank[49] = new Node(Move.DIVE);
		    movebank[54] = new Node(Move.HYDRO_PUMP);
		    break;
		case -69:
		    movebank = new Node[45];
		    movebank[0] = new Node(Move.BITE);
		    movebank[1] = new Node(Move.TAIL_WHIP);
		    movebank[2] = new Node(Move.SCRATCH);
		    movebank[6] = new Node(Move.SCARY_FACE);
		    movebank[10] = new Node(Move.HEADBUTT);
		    movebank[14] = new Node(Move.THUNDER_FANG);
		    movebank[18] = new Node(Move.BEAT_UP);
		    movebank[22] = new Node(Move.CRUNCH);
		    movebank[28] = new Node(Move.AQUA_JET);
		    movebank[44] = new Node(Move.SURF);
		    break;
		case -70:
		    movebank = new Node[55];
		    movebank[0] = new Node(Move.BITE);
		    //movebank[4] = new Node(Move.TAIL_WHACK);
		    movebank[6] = new Node(Move.RAGE);
		    movebank[9] = new Node(Move.SLAM);
		    movebank[14] = new Node(Move.DRAGON_BREATH);
		    movebank[19] = new Node(Move.BUBBLEBEAM);
		    movebank[29] = new Node(Move.CRUNCH);
		    movebank[39] = new Node(Move.AQUA_JET);
		    //movebank[49] = new Node(Move.ROCKET);
		    movebank[54] = new Node(Move.HYPER_BEAM);
		    break;
		case -71:
			movebank = new Node[30];
			movebank[0] = new Node(Move.HORN_ATTACK);
			movebank[14] = new Node(Move.FURY_ATTACK);
			movebank[24] = new Node(Move.ROCK_BLAST);
			movebank[29] = new Node(Move.TAKE_DOWN);
			break;
		case -72:
			movebank = new Node[55];
			movebank[0] = new Node(Move.HORN_ATTACK);
			movebank[14] = new Node(Move.FURY_ATTACK);
			movebank[24] = new Node(Move.ROCK_BLAST);
			movebank[29] = new Node(Move.TAKE_DOWN);
			movebank[29] = new Node(Move.STOMP);
			movebank[32] = new Node(Move.HORN_DRILL);
			movebank[34] = new Node(Move.MAGNITUDE);
			movebank[42] = new Node(Move.STONE_EDGE);
			movebank[46] = new Node(Move.DOUBLE_HIT);
			movebank[50] = new Node(Move.BODY_SLAM);
			movebank[54] = new Node(Move.EARTH_POWER);
			break;
		case -73:
			movebank = new Node[70];
			movebank[0] = new Node(Move.HORN_ATTACK);
			movebank[14] = new Node(Move.FURY_ATTACK);
			movebank[24] = new Node(Move.ROCK_BLAST);
			movebank[29] = new Node(Move.TAKE_DOWN);
			movebank[29] = new Node(Move.STOMP);
			movebank[32] = new Node(Move.HORN_DRILL);
			movebank[34] = new Node(Move.MAGNITUDE);
			movebank[42] = new Node(Move.STONE_EDGE);
			movebank[46] = new Node(Move.DOUBLE_HIT);
			movebank[50] = new Node(Move.BODY_SLAM);
			movebank[54] = new Node(Move.EARTH_POWER);
			movebank[55] = new Node(Move.HAMMER_ARM);
			movebank[60] = new Node(Move.EARTHQUAKE);
			//movebank[68] = new Node(Move.ROCKET);
			movebank[69] = new Node(Move.HYPER_BEAM);
			break;
		case -74:
			movebank = new Node[15];
			//movebank[0] = new Node(Move.IGNITE);
			movebank[14] = new Node(Move.EMBER);
			break;
		case -75:
			movebank = new Node[32];
			//movebank[0] = new Node(Move.IGNITE);
			movebank[14] = new Node(Move.EMBER);
			movebank[15] = new Node(Move.FLAME_WHEEL);
			movebank[20] = new Node(Move.FURY_SWIPES);
			movebank[25] = new Node(Move.MACH_PUNCH);
			movebank[28] = new Node(Move.KARATE_CHOP);
			//movebank[31] = new Node(Move.FIREBALL);
			break;
		case -76:
			movebank = new Node[65];
			//movebank[0] = new Node(Move.IGNITE);
			movebank[14] = new Node(Move.EMBER);
			movebank[15] = new Node(Move.FLAME_WHEEL);
			movebank[20] = new Node(Move.FURY_SWIPES);
			movebank[25] = new Node(Move.MACH_PUNCH);
			movebank[28] = new Node(Move.KARATE_CHOP);
			//movebank[31] = new Node(Move.FIREBALL);
			movebank[35] = new Node(Move.FIRE_FANG);
			movebank[39] = new Node(Move.WAKE_UP_SLAP);
			movebank[47] = new Node(Move.CROSS_POISON);
			movebank[49] = new Node(Move.CLOSE_COMBAT);
			movebank[54] = new Node(Move.ERUPTION);
			movebank[64] = new Node(Move.FIRE_BLAST);
			break;
		case -77:
			movebank = new Node[29];
			movebank[0] = new Node(Move.TACKLE);
			movebank[2] = new Node(Move.TAIL_WHIP);
			//movebank[3] = new Node(Move.SLAP);
			movebank[7] = new Node(Move.DOUBLE_SLAP);
			movebank[9] = new Node(Move.EMBER);
			movebank[13] = new Node(Move.SLAM);
			movebank[18] = new Node(Move.DOUBLE_TEAM);
			movebank[21] = new Node(Move.FLAME_WHEEL);
			//movebank[28] = new Node(Move.FIRE_CHARGE);
			break;
		case -78:
			movebank = new Node[53];
			movebank[0] = new Node(Move.TACKLE);
			movebank[2] = new Node(Move.TAIL_WHIP);
			//movebank[3] = new Node(Move.SLAP);
			movebank[7] = new Node(Move.DOUBLE_SLAP);
			movebank[9] = new Node(Move.EMBER);
			movebank[13] = new Node(Move.SLAM);
			movebank[18] = new Node(Move.DOUBLE_TEAM);
			movebank[21] = new Node(Move.FLAME_WHEEL);
			//movebank[28] = new Node(Move.FIRE_CHARGE);
			movebank[34] = new Node(Move.WING_ATTACK);
			movebank[35] = new Node(Move.BITE);
			movebank[36] = new Node(Move.FIRE_FANG);
			movebank[41] = new Node(Move.AIR_SLASH);
			movebank[44] = new Node(Move.DRAGON_BREATH);
			movebank[49] = new Node(Move.FLAMETHROWER);
			movebank[52] = new Node(Move.CRUNCH);
			break;
		case -79:
			movebank = new Node[75];
			movebank[0] = new Node(Move.TACKLE);
			movebank[2] = new Node(Move.TAIL_WHIP);
			//movebank[3] = new Node(Move.SLAP);
			movebank[7] = new Node(Move.DOUBLE_SLAP);
			movebank[9] = new Node(Move.EMBER);
			movebank[13] = new Node(Move.SLAM);
			movebank[18] = new Node(Move.DOUBLE_TEAM);
			movebank[21] = new Node(Move.FLAME_WHEEL);
			//movebank[28] = new Node(Move.FIRE_CHARGE);
			movebank[34] = new Node(Move.WING_ATTACK);
			movebank[35] = new Node(Move.BITE);
			movebank[36] = new Node(Move.FIRE_FANG);
			movebank[41] = new Node(Move.AIR_SLASH);
			movebank[44] = new Node(Move.DRAGON_BREATH);
			movebank[49] = new Node(Move.FLAMETHROWER);
			movebank[52] = new Node(Move.CRUNCH);
			movebank[55] = new Node(Move.IRON_TAIL);
			movebank[59] = new Node(Move.DRAGON_RUSH);
			movebank[62] = new Node(Move.OUTRAGE);
			movebank[66] = new Node(Move.DRACO_METEOR);
			movebank[70] = new Node(Move.FIRE_BLAST);
			movebank[74] = new Node(Move.HYPER_BEAM);
			break;
		case -80:
			movebank = new Node[25];
			movebank[0] = new Node(Move.TACKLE);
			movebank[4] = new Node(Move.LOW_KICK);
			movebank[7] = new Node(Move.HEADBUTT);
			movebank[8] = new Node(Move.IRON_DEFENSE);
			movebank[12] = new Node(Move.EMBER);
			movebank[14] = new Node(Move.SCREECH);
			movebank[19] = new Node(Move.FLAME_WHEEL);
			movebank[24] = new Node(Move.HEAT_WAVE);
			break;
		case -81:
			movebank = new Node[55];
			movebank[0] = new Node(Move.TACKLE);
			movebank[4] = new Node(Move.LOW_KICK);
			movebank[7] = new Node(Move.HEADBUTT);
			movebank[8] = new Node(Move.IRON_DEFENSE);
			movebank[12] = new Node(Move.EMBER);
			movebank[14] = new Node(Move.SCREECH);
			movebank[19] = new Node(Move.FLAME_WHEEL);
			movebank[24] = new Node(Move.HEAT_WAVE);
			movebank[27] = new Node(Move.TAKE_DOWN);
			movebank[30] = new Node(Move.FIRE_SPIN);
			movebank[36] = new Node(Move.MAGNET_RISE);
			movebank[42] = new Node(Move.GIGA_IMPACT);
			//movebank[44] = new Node(Move.SUPER_CHARGE);
			movebank[48] = new Node(Move.FLAMETHROWER);
			movebank[50] = new Node(Move.GYRO_BALL);
			movebank[54] = new Node(Move.BLAST_BURN);
			break;
		case -82:
			movebank = new Node[25];
			movebank[0] = new Node(Move.BRINE);
			//movebank[4] = new Node(Move.DISAPPEAR);
			movebank[14] = new Node(Move.WATER_PULSE);
			movebank[24] = new Node(Move.SHADOW_SNEAK);
			break;
		case -83:
			movebank = new Node[55];
			movebank[0] = new Node(Move.BRINE);
			//movebank[4] = new Node(Move.DISAPPEAR);
			movebank[14] = new Node(Move.WATER_PULSE);
			movebank[24] = new Node(Move.SHADOW_SNEAK);
			movebank[25] = new Node(Move.CHARGE);
			movebank[26] = new Node(Move.DISCHARGE);
			movebank[29] = new Node(Move.WING_ATTACK);
			movebank[34] = new Node(Move.AEROBLAST);
			movebank[44] = new Node(Move.THUNDERBOLT);
			movebank[49] = new Node(Move.SHADOW_BALL);
			movebank[54] = new Node(Move.THUNDER);
			break;
		case -84:
			movebank = new Node[27];
			movebank[0] = new Node(Move.PECK);
			movebank[7] = new Node(Move.EMBER);
			movebank[12] = new Node(Move.WILL_O_WISP);
			movebank[17] = new Node(Move.DRAGON_RAGE);
			movebank[20] = new Node(Move.FURY_ATTACK);
			movebank[22] = new Node(Move.FLAME_WHEEL);
			movebank[26] = new Node(Move.WING_ATTACK);
			break;
		case -85:
			movebank = new Node[55];
			movebank[0] = new Node(Move.PECK);
			movebank[7] = new Node(Move.EMBER);
			movebank[12] = new Node(Move.WILL_O_WISP);
			movebank[17] = new Node(Move.DRAGON_RAGE);
			movebank[20] = new Node(Move.FURY_ATTACK);
			movebank[22] = new Node(Move.FLAME_WHEEL);
			movebank[26] = new Node(Move.WING_ATTACK);
			movebank[30] = new Node(Move.HI_JUMP_KICK);
			movebank[36] = new Node(Move.ROOST);
			movebank[41] = new Node(Move.FLAMETHROWER);
			movebank[44] = new Node(Move.DRILL_PECK);
			movebank[50] = new Node(Move.BRAVE_BIRD);
			movebank[54] = new Node(Move.FIRE_BLAST);
			break;
		case -86:
			movebank = new Node[30];
			movebank[0] = new Node(Move.POISON_STING);
			//movebank[1] = new Node(Move.CONSTRICT);
			movebank[2] = new Node(Move.STRING_SHOT);
			movebank[3] = new Node(Move.SCARY_FACE);
			movebank[9] = new Node(Move.BUG_BITE);
			movebank[14] = new Node(Move.NIGHT_SHADE);
			movebank[19] = new Node(Move.SHADOW_SNEAK);
			//movebank[24] = new Node(Move.FAINT_ATTACK);
			movebank[29] = new Node(Move.BEAT_UP);
			break;
		case -87:
			movebank = new Node[55];
			movebank[0] = new Node(Move.POISON_STING);
			//movebank[1] = new Node(Move.CONSTRICT);
			movebank[2] = new Node(Move.STRING_SHOT);
			movebank[3] = new Node(Move.SCARY_FACE);
			movebank[9] = new Node(Move.BUG_BITE);
			movebank[14] = new Node(Move.NIGHT_SHADE);
			movebank[19] = new Node(Move.SHADOW_SNEAK);
			//movebank[24] = new Node(Move.FAINT_ATTACK);
			movebank[29] = new Node(Move.BEAT_UP);
			movebank[35] = new Node(Move.FIRE_FANG);
			movebank[36] = new Node(Move.MAGIC_FANG);
			movebank[37] = new Node(Move.THUNDER_FANG);
			movebank[38] = new Node(Move.POISON_FANG);
			movebank[40] = new Node(Move.SHADOW_BALL);
			movebank[48] = new Node(Move.CROSS_POISON);
			movebank[49] = new Node(Move.CRUNCH);
			movebank[54] = new Node(Move.PERISH_SONG);
			break;
		case -88:
			movebank = new Node[40];
			movebank[0] = new Node(Move.CUT);
			movebank[4] = new Node(Move.FURY_CUTTER);
			movebank[9] = new Node(Move.FALSE_SWIPE);
			movebank[14] = new Node(Move.AGILITY);
			movebank[19] = new Node(Move.FIRST_IMPRESSION);
			movebank[24] = new Node(Move.SLASH);
			movebank[29] = new Node(Move.X_SCIZZOR);
			movebank[34] = new Node(Move.SUPERPOWER);
			movebank[39] = new Node(Move.GYRO_BALL);
			break;
		case -89:
			movebank = new Node[30];
			movebank[0] = new Node(Move.NIGHT_SLASH);
			movebank[4] = new Node(Move.BEAT_UP);
			//movebank[9] = new Node(Move.FAINT_ATTACK);
			movebank[14] = new Node(Move.BITE);
			//movebank[19] = new Node(Move.DARK_VOID);
			movebank[24] = new Node(Move.DARK_PULSE);
			movebank[29] = new Node(Move.CRUNCH);
			break;
		case -90:
			movebank = new Node[56];
			movebank[0] = new Node(Move.NIGHT_SLASH);
			movebank[4] = new Node(Move.BEAT_UP);
			//movebank[9] = new Node(Move.FAINT_ATTACK);
			movebank[14] = new Node(Move.BITE);
			//movebank[19] = new Node(Move.DARK_VOID);
			movebank[24] = new Node(Move.DARK_PULSE);
			movebank[29] = new Node(Move.CRUNCH);
			movebank[30] = new Node(Move.STOMP);
			movebank[35] = new Node(Move.WILL_O_WISP);
			movebank[40] = new Node(Move.SUCKER_PUNCH);
			movebank[45] = new Node(Move.ZEN_HEADBUTT);
			movebank[50] = new Node(Move.SHADOW_BALL);
			movebank[55] = new Node(Move.CLOSE_COMBAT);
			break;
		case -91:
			movebank = new Node[55];
			movebank[0] = new Node(Move.SCRATCH);
			movebank[2] = new Node(Move.BEAT_UP);
			movebank[8] = new Node(Move.SHADOW_SNEAK);
			movebank[9] = new Node(Move.EMBER);
			movebank[15] = new Node(Move.DIVE);
			movebank[20] = new Node(Move.ROCK_BLAST);
			//movebank[22] = new Node(Move.LEAF_BALL);
			movebank[34] = new Node(Move.SHADOW_BALL);
			movebank[44] = new Node(Move.VOLT_TACKLE);
			//movebank[54] = new Node(Move.GALAXY_ATTACK);
			break;
		case -92:
			movebank = new Node[1];
			//movebank[0] = new Node(Move.ELECTROEXPLOSION);
			break;
		case -93:
			movebank = new Node[30];
			movebank[0] = new Node(Move.EMBER);
			movebank[1] = new Node(Move.ASTONISH);
			movebank[4] = new Node(Move.SMOKESCREEN);
			//movebank[9] = new Node(Move.FIREBALL);
			movebank[14] = new Node(Move.CURSE);
			movebank[19] = new Node(Move.FLAMETHROWER);
			movebank[29] = new Node(Move.SHADOW_BALL);
			break;
		case -94:
			movebank = new Node[70];
			movebank[0] = new Node(Move.EMBER);
			movebank[1] = new Node(Move.ASTONISH);
			movebank[4] = new Node(Move.SMOKESCREEN);
			//movebank[9] = new Node(Move.FIREBALL);
			movebank[14] = new Node(Move.CURSE);
			movebank[19] = new Node(Move.FLAMETHROWER);
			movebank[29] = new Node(Move.SHADOW_BALL);
			//movebank[31] = new Node(Move.FIRE_CHARGE);
			movebank[34] = new Node(Move.EXPLOSION);
			movebank[39] = new Node(Move.FIRE_BLAST);
			movebank[41] = new Node(Move.DESTINY_BOND);
			//movebank[44] = new Node(Move.DARK_VOID);
			movebank[49] = new Node(Move.NIGHTMARE);
			movebank[54] = new Node(Move.TAKE_OVER);
			movebank[69] = new Node(Move.BLAST_BURN);
			break;
		case -95:
			movebank = new Node[5];
			movebank[0] = new Node(Move.DEFENSE_CURL);
			movebank[1] = new Node(Move.SCRATCH);
			movebank[2] = new Node(Move.GROWL);
			movebank[3] = new Node(Move.IRON_DEFENSE);
			movebank[4] = new Node(Move.GYRO_BALL);
			break;
		case -96:
			movebank = new Node[50];
			movebank[0] = new Node(Move.DEFENSE_CURL);
			movebank[1] = new Node(Move.SCRATCH);
			movebank[2] = new Node(Move.GROWL);
			movebank[3] = new Node(Move.IRON_DEFENSE);
			movebank[4] = new Node(Move.GYRO_BALL);
			movebank[9] = new Node(Move.IRON_HEAD);
			movebank[19] = new Node(Move.FURY_SWIPES);
			movebank[23] = new Node(Move.SLAM);
			movebank[26] = new Node(Move.ROLLOUT);
			movebank[34] = new Node(Move.FLASH_CANNON);
			movebank[41] = new Node(Move.GIGA_IMPACT);
			movebank[49] = new Node(Move.GYRO_BALL);
			break;
		case -97:
			movebank = new Node[24];
			//movebank[0] = new Node(Move.GUNSHOT);
			movebank[1] = new Node(Move.SCREECH);
			movebank[9] = new Node(Move.SHELL_SMASH);
			movebank[14] = new Node(Move.HEADBUTT);
			movebank[19] = new Node(Move.AUTOMOTIZE);
			movebank[23] = new Node(Move.LOCK_ON);
			break;
		case -98:
			movebank = new Node[48];
			//movebank[0] = new Node(Move.GUNSHOT);
			movebank[1] = new Node(Move.SCREECH);
			movebank[9] = new Node(Move.SHELL_SMASH);
			movebank[14] = new Node(Move.HEADBUTT);
			movebank[19] = new Node(Move.AUTOMOTIZE);
			movebank[23] = new Node(Move.LOCK_ON);
			movebank[24] = new Node(Move.IRON_DEFENSE);
			movebank[31] = new Node(Move.TAKE_DOWN);
			movebank[39] = new Node(Move.REBOOT);
			movebank[47] = new Node(Move.METAL_SOUND);
			break;
		case -99:
			movebank = new Node[60];
			//movebank[0] = new Node(Move.GUNSHOT);
			movebank[1] = new Node(Move.SCREECH);
			movebank[9] = new Node(Move.SHELL_SMASH);
			movebank[14] = new Node(Move.HEADBUTT);
			movebank[19] = new Node(Move.AUTOMOTIZE);
			movebank[23] = new Node(Move.LOCK_ON);
			movebank[24] = new Node(Move.IRON_DEFENSE);
			movebank[31] = new Node(Move.TAKE_DOWN);
			movebank[39] = new Node(Move.REBOOT);
			movebank[47] = new Node(Move.METAL_SOUND);
			//movebank[49] = new Node(Move.AUTO_SHOT);
			movebank[50] = new Node(Move.FLAMETHROWER);
			movebank[54] = new Node(Move.GIGA_IMPACT);
			movebank[59] = new Node(Move.GYRO_BALL);
			break;
		case -100:
			movebank = new Node[33];
			movebank[0] = new Node(Move.POUND);
			movebank[1] = new Node(Move.LEER);
			movebank[4] = new Node(Move.THUNDERSHOCK);
			movebank[7] = new Node(Move.WRAP);
			movebank[13] = new Node(Move.DRAGON_RAGE);
			movebank[19] = new Node(Move.WATER_PULSE);
			movebank[22] = new Node(Move.SLAM);
			movebank[26] = new Node(Move.DRAGON_BREATH);
			movebank[32] = new Node(Move.THUNDERBOLT);
			break;
		case -101:
			movebank = new Node[53];
			movebank[0] = new Node(Move.POUND);
			movebank[1] = new Node(Move.LEER);
			movebank[4] = new Node(Move.THUNDERSHOCK);
			movebank[7] = new Node(Move.WRAP);
			movebank[13] = new Node(Move.DRAGON_RAGE);
			movebank[19] = new Node(Move.WATER_PULSE);
			movebank[22] = new Node(Move.SLAM);
			movebank[26] = new Node(Move.DRAGON_BREATH);
			movebank[32] = new Node(Move.THUNDERBOLT);
			movebank[38] = new Node(Move.IRON_HEAD);
			movebank[40] = new Node(Move.DRAGON_PULSE);
			movebank[45] = new Node(Move.DRAGON_RUSH);
			movebank[52] = new Node(Move.HYDRO_PUMP);
			break;
		case -102:
			movebank = new Node[80];
			movebank[0] = new Node(Move.POUND);
			movebank[1] = new Node(Move.LEER);
			movebank[4] = new Node(Move.THUNDERSHOCK);
			movebank[7] = new Node(Move.WRAP);
			movebank[13] = new Node(Move.DRAGON_RAGE);
			movebank[19] = new Node(Move.WATER_PULSE);
			movebank[22] = new Node(Move.SLAM);
			movebank[26] = new Node(Move.DRAGON_BREATH);
			movebank[32] = new Node(Move.THUNDERBOLT);
			movebank[38] = new Node(Move.IRON_HEAD);
			movebank[40] = new Node(Move.DRAGON_PULSE);
			movebank[45] = new Node(Move.DRAGON_RUSH);
			movebank[52] = new Node(Move.HYDRO_PUMP);
			//movebank[57] = new Node(Move.GALAXY_ATTACK);
			movebank[60] = new Node(Move.SKY_ATTACK);
			movebank[64] = new Node(Move.HYPER_BEAM);
			movebank[67] = new Node(Move.THUNDER);
			movebank[72] = new Node(Move.OUTRAGE);
			movebank[79] = new Node(Move.DRACO_METEOR);
			break;
		case -103:
			movebank = new Node[55];
			movebank[0] = new Node(Move.ANCIENT_POWER);
			movebank[4] = new Node(Move.THUNDERBOLT);
			movebank[7] = new Node(Move.DRAGON_RAGE);
			movebank[14] = new Node(Move.WING_ATTACK);
			movebank[23] = new Node(Move.FLY);
			movebank[35] = new Node(Move.FLAMETHROWER);
			movebank[42] = new Node(Move.CLOSE_COMBAT);
			movebank[45] = new Node(Move.HYPER_BEAM);
			movebank[49] = new Node(Move.DRAGON_RUSH);
			movebank[54] = new Node(Move.SUPERPOWER);
			break;
		case -104:
			movebank = new Node[55];
			movebank[0] = new Node(Move.ANCIENT_POWER);
			movebank[4] = new Node(Move.THUNDERBOLT);
			movebank[7] = new Node(Move.DRAGON_RAGE);
			movebank[14] = new Node(Move.WING_ATTACK);
			movebank[23] = new Node(Move.FLY);
			movebank[35] = new Node(Move.FLAMETHROWER);
			movebank[42] = new Node(Move.AEROBLAST);
			movebank[45] = new Node(Move.HYPER_BEAM);
			movebank[49] = new Node(Move.DRAGON_RUSH);
			movebank[54] = new Node(Move.SKY_ATTACK);
			break;
		case -105:
			movebank = new Node[55];
			movebank[0] = new Node(Move.ANCIENT_POWER);
			movebank[4] = new Node(Move.THUNDERBOLT);
			movebank[7] = new Node(Move.DRAGON_RAGE);
			movebank[14] = new Node(Move.WING_ATTACK);
			movebank[23] = new Node(Move.FLY);
			movebank[35] = new Node(Move.FLAMETHROWER);
			movebank[42] = new Node(Move.FIRE_BLAST);
			movebank[45] = new Node(Move.HYPER_BEAM);
			movebank[49] = new Node(Move.DRAGON_RUSH);
			movebank[54] = new Node(Move.OVERHEAT);
			break;
		case -106:
			movebank = new Node[55];
			movebank[0] = new Node(Move.SWIFT);
			movebank[9] = new Node(Move.MAGIC_BLAST);
			movebank[14] = new Node(Move.AQUA_RING);
			//movebank[19] = new Node(Move.DISAPPEAR);
			//movebank[24] = new Node(Move.EXTRAORDINARY);
			movebank[39] = new Node(Move.MAGIC_REFLECT);
			movebank[44] = new Node(Move.MAGIC_TOMB);
			movebank[54] = new Node(Move.STAR_STORM);
			break;
		case -107:
			movebank = new Node[24];
			movebank[0] = new Node(Move.DOUBLE_TEAM);
			movebank[1] = new Node(Move.QUICK_ATTACK);
			movebank[2] = new Node(Move.EMBER);
			//movebank[3] = new Node(Move.IGNITE);
			movebank[4] = new Node(Move.SMOKESCREEN);
			movebank[7] = new Node(Move.CONFUSION);
			movebank[9] = new Node(Move.GLARE);
			movebank[13] = new Node(Move.HYPNOSIS);
			movebank[18] = new Node(Move.SWIFT);
			movebank[23] = new Node(Move.FLAME_WHEEL);
			break;
		case -108:
			movebank = new Node[55];
			movebank[0] = new Node(Move.DOUBLE_TEAM);
			movebank[1] = new Node(Move.QUICK_ATTACK);
			movebank[2] = new Node(Move.EMBER);
			//movebank[3] = new Node(Move.IGNITE);
			movebank[4] = new Node(Move.SMOKESCREEN);
			movebank[7] = new Node(Move.CONFUSION);
			movebank[9] = new Node(Move.GLARE);
			movebank[13] = new Node(Move.HYPNOSIS);
			movebank[18] = new Node(Move.SWIFT);
			movebank[23] = new Node(Move.FLAME_WHEEL);
			//movebank[27] = new Node(Move.FIRE_DASH);
			//movebank[32] = new Node(Move.PSYCHO_BLADE);
			//movebank[37] = new Node(Move.GIGA_HIT);
			movebank[40] = new Node(Move.ERUPTION);
			movebank[48] = new Node(Move.COMET_CRASH);
			//movebank[54] = new Node(Move.BLAST_FLAME);
			break;
		case -109:
			movebank = new Node[30];
			movebank[0] = new Node(Move.GUST);
			movebank[4] = new Node(Move.SPARK);
			//movebank[10] = new Node(Move.DISAPPEAR);
			movebank[12] = new Node(Move.AIR_CUTTER);
			movebank[14] = new Node(Move.SWIFT);
			movebank[17] = new Node(Move.HYPNOSIS);
			movebank[22] = new Node(Move.DREAM_EATER);
			movebank[24] = new Node(Move.FLAME_WHEEL);
			movebank[29] = new Node(Move.ROCK_BLAST);
			break;
		case -110:
			movebank = new Node[50];
			movebank[0] = new Node(Move.GUST);
			movebank[4] = new Node(Move.SPARK);
			//movebank[10] = new Node(Move.DISAPPEAR);
			movebank[12] = new Node(Move.AIR_CUTTER);
			movebank[14] = new Node(Move.SWIFT);
			movebank[17] = new Node(Move.HYPNOSIS);
			movebank[22] = new Node(Move.DREAM_EATER);
			movebank[24] = new Node(Move.FLAME_WHEEL);
			movebank[29] = new Node(Move.ROCK_BLAST);
			movebank[32] = new Node(Move.AQUA_JET);
			movebank[36] = new Node(Move.MOONLIGHT);
			//movebank[41] = new Node(Move.PHASE_SHIFT);
			movebank[45] = new Node(Move.SYNTHESIS);
			//movebank[49] = new Node(Move.BLACK_HOLE);
			break;
		case -111:
			movebank = new Node[75];
			movebank[0] = new Node(Move.GUST);
			movebank[4] = new Node(Move.SPARK);
			//movebank[10] = new Node(Move.DISAPPEAR);
			movebank[12] = new Node(Move.AIR_CUTTER);
			movebank[14] = new Node(Move.SWIFT);
			movebank[17] = new Node(Move.HYPNOSIS);
			movebank[22] = new Node(Move.DREAM_EATER);
			movebank[24] = new Node(Move.FLAME_WHEEL);
			movebank[29] = new Node(Move.ROCK_BLAST);
			movebank[32] = new Node(Move.AQUA_JET);
			movebank[36] = new Node(Move.MOONLIGHT);
			//movebank[41] = new Node(Move.PHASE_SHIFT);
			movebank[45] = new Node(Move.SYNTHESIS);
			//movebank[48] = new Node(Move.BLACK_HOLE);
			movebank[49] = new Node(Move.COMET_CRASH);
			movebank[55] = new Node(Move.STAR_STORM);
			//movebank[64] = new Node(Move.GALAXY_ATTACK);
			movebank[69] = new Node(Move.HYPER_BEAM);
			movebank[74] = new Node(Move.MAGIC_CRASH);
			break;
		case -112:
			movebank = new Node[25];
			movebank[0] = new Node(Move.LEER);
			movebank[1] = new Node(Move.TACKLE);
			movebank[4] = new Node(Move.ODOR_SLEUTH);
			movebank[7] = new Node(Move.BITE);
			movebank[11] = new Node(Move.FURY_SWIPES);
			movebank[14] = new Node(Move.TAKE_DOWN);
			movebank[19] = new Node(Move.SLAM);
			movebank[23] = new Node(Move.CRUNCH);
			//movebank[24] = new Node(Move.EXTRAORDINARY);
			break;
		case -113:
			movebank = new Node[65];
			movebank[0] = new Node(Move.LEER);
			movebank[1] = new Node(Move.TACKLE);
			movebank[4] = new Node(Move.ODOR_SLEUTH);
			movebank[7] = new Node(Move.BITE);
			movebank[11] = new Node(Move.FURY_SWIPES);
			movebank[14] = new Node(Move.TAKE_DOWN);
			movebank[19] = new Node(Move.SLAM);
			movebank[23] = new Node(Move.CRUNCH);
			//movebank[24] = new Node(Move.EXTRAORDINARY);
			movebank[24] = new Node(Move.LEECH_SEED);
			movebank[27] = new Node(Move.MEGA_DRAIN);
			movebank[32] = new Node(Move.LEAF_BLADE);
			movebank[36] = new Node(Move.TAKE_DOWN);
			movebank[42] = new Node(Move.EARTHQUAKE);
			movebank[48] = new Node(Move.GIGA_DRAIN);
			movebank[54] = new Node(Move.GRASS_KNOT);
			movebank[64] = new Node(Move.LEAF_STORM);
			break;
		case -114:
			movebank = new Node[65];
			movebank[0] = new Node(Move.LEER);
			movebank[1] = new Node(Move.TACKLE);
			movebank[4] = new Node(Move.ODOR_SLEUTH);
			movebank[7] = new Node(Move.BITE);
			movebank[11] = new Node(Move.FURY_SWIPES);
			movebank[14] = new Node(Move.TAKE_DOWN);
			movebank[19] = new Node(Move.SLAM);
			movebank[23] = new Node(Move.CRUNCH);
			//movebank[24] = new Node(Move.EXTRAORDINARY);
			movebank[24] = new Node(Move.FLAME_WHEEL);
			//movebank[29] = new Node(Move.FIRE_CHARGE);
			movebank[33] = new Node(Move.FIRE_FANG);
			movebank[37] = new Node(Move.TAKE_DOWN);
			movebank[42] = new Node(Move.FLAMETHROWER);
			movebank[48] = new Node(Move.THUNDERBOLT);
			movebank[54] = new Node(Move.ERUPTION);
			movebank[60] = new Node(Move.STONE_EDGE);
			movebank[64] = new Node(Move.FIRE_BLAST);
			break;
		case -115:
			movebank = new Node[65];
			movebank[0] = new Node(Move.LEER);
			movebank[1] = new Node(Move.TACKLE);
			movebank[4] = new Node(Move.ODOR_SLEUTH);
			movebank[7] = new Node(Move.BITE);
			movebank[11] = new Node(Move.FURY_SWIPES);
			movebank[14] = new Node(Move.TAKE_DOWN);
			movebank[19] = new Node(Move.SLAM);
			movebank[23] = new Node(Move.CRUNCH);
			//movebank[24] = new Node(Move.EXTRAORDINARY);
			movebank[24] = new Node(Move.AQUA_JET);
			movebank[30] = new Node(Move.WATER_PULSE);
			movebank[34] = new Node(Move.TAKE_DOWN);
			movebank[41] = new Node(Move.CROSS_POISON);
			movebank[45] = new Node(Move.BRINE);
			movebank[48] = new Node(Move.EARTHQUAKE);
			//movebank[54] = new Node(Move.TIDAL_WAVE);
			movebank[64] = new Node(Move.HYDRO_PUMP);
			break;
		case -116:
			movebank = new Node[65];
			movebank[0] = new Node(Move.LEER);
			movebank[1] = new Node(Move.TACKLE);
			movebank[4] = new Node(Move.ODOR_SLEUTH);
			movebank[7] = new Node(Move.BITE);
			movebank[11] = new Node(Move.FURY_SWIPES);
			movebank[14] = new Node(Move.TAKE_DOWN);
			movebank[19] = new Node(Move.SLAM);
			movebank[23] = new Node(Move.CRUNCH);
			//movebank[24] = new Node(Move.EXTRAORDINARY);
			movebank[24] = new Node(Move.CROSS_POISON);
			movebank[29] = new Node(Move.POISON_GAS);
			movebank[33] = new Node(Move.AQUA_JET);
			movebank[38] = new Node(Move.POISON_JAB);
			movebank[42] = new Node(Move.TAKE_DOWN);
			movebank[47] = new Node(Move.DARK_PULSE);
			movebank[54] = new Node(Move.TOXIC);
			movebank[60] = new Node(Move.LEAF_BLADE);
			movebank[64] = new Node(Move.GUNK_SHOT);
			break;
		case -117:
			movebank = new Node[65];
			movebank[0] = new Node(Move.LEER);
			movebank[1] = new Node(Move.TACKLE);
			movebank[4] = new Node(Move.ODOR_SLEUTH);
			movebank[7] = new Node(Move.BITE);
			movebank[11] = new Node(Move.FURY_SWIPES);
			movebank[14] = new Node(Move.TAKE_DOWN);
			movebank[19] = new Node(Move.SLAM);
			movebank[23] = new Node(Move.CRUNCH);
			//movebank[24] = new Node(Move.EXTRAORDINARY);
			movebank[24] = new Node(Move.CHARGE);
			movebank[25] = new Node(Move.DISCHARGE);
			movebank[32] = new Node(Move.ELECTROBALL);
			movebank[36] = new Node(Move.TAKE_DOWN);
			movebank[44] = new Node(Move.THUNDERBOLT);
			movebank[49] = new Node(Move.IRON_TAIL);
			movebank[52] = new Node(Move.VOLT_TACKLE);
			movebank[56] = new Node(Move.BRINE);
			movebank[64] = new Node(Move.THUNDER);
			break;
		case -118:
			movebank = new Node[65];
			movebank[0] = new Node(Move.LEER);
			movebank[1] = new Node(Move.TACKLE);
			movebank[4] = new Node(Move.ODOR_SLEUTH);
			movebank[7] = new Node(Move.BITE);
			movebank[11] = new Node(Move.FURY_SWIPES);
			movebank[14] = new Node(Move.TAKE_DOWN);
			movebank[19] = new Node(Move.SLAM);
			movebank[23] = new Node(Move.CRUNCH);
			//movebank[24] = new Node(Move.EXTRAORDINARY);
			movebank[24] = new Node(Move.ROCK_TOMB);
			movebank[30] = new Node(Move.ROCK_BLAST);
			movebank[33] = new Node(Move.ROLLOUT);
			movebank[36] = new Node(Move.ROCK_SLIDE);
			movebank[41] = new Node(Move.TAKE_DOWN);
			movebank[44] = new Node(Move.AEROBLAST);
			movebank[49] = new Node(Move.STONE_EDGE);
			movebank[53] = new Node(Move.HEAD_SMASH);
			movebank[64] = new Node(Move.ROCK_WRECKER);
			break;
		case -119:
			movebank = new Node[65];
			movebank[0] = new Node(Move.LEER);
			movebank[1] = new Node(Move.TACKLE);
			movebank[4] = new Node(Move.ODOR_SLEUTH);
			movebank[7] = new Node(Move.BITE);
			movebank[11] = new Node(Move.FURY_SWIPES);
			movebank[14] = new Node(Move.TAKE_DOWN);
			movebank[19] = new Node(Move.SLAM);
			movebank[23] = new Node(Move.CRUNCH);
			//movebank[24] = new Node(Move.EXTRAORDINARY);
			movebank[24] = new Node(Move.SHADOW_SNEAK);
			//movebank[26] = new Node(Move.ASSURANCE);
			movebank[32] = new Node(Move.BEAT_UP);
			//movebank[38] = new Node(Move.FAINT_ATTACK);
			movebank[40] = new Node(Move.MINIMIZE);
			movebank[41] = new Node(Move.STAR_STORM);
			movebank[46] = new Node(Move.DARK_PULSE);
			movebank[51] = new Node(Move.FLAMETHROWER);
			movebank[64] = new Node(Move.DESTINY_BOND);
			break;
		case -120:
			movebank = new Node[65];
			movebank[0] = new Node(Move.LEER);
			movebank[1] = new Node(Move.TACKLE);
			movebank[4] = new Node(Move.ODOR_SLEUTH);
			movebank[7] = new Node(Move.BITE);
			movebank[11] = new Node(Move.FURY_SWIPES);
			movebank[14] = new Node(Move.TAKE_DOWN);
			movebank[19] = new Node(Move.SLAM);
			movebank[23] = new Node(Move.CRUNCH);
			//movebank[24] = new Node(Move.EXTRAORDINARY);
			movebank[24] = new Node(Move.BULLET_PUNCH);
			movebank[30] = new Node(Move.IRON_DEFENSE);
			movebank[33] = new Node(Move.MAGNET_RISE);
			movebank[35] = new Node(Move.TAKE_DOWN);
			movebank[42] = new Node(Move.IRON_TAIL);
			movebank[47] = new Node(Move.WATER_PULSE);
			movebank[54] = new Node(Move.FLASH_CANNON);
			movebank[64] = new Node(Move.GYRO_BALL);
			break;
		case -121:
			movebank = new Node[65];
			movebank[0] = new Node(Move.LEER);
			movebank[1] = new Node(Move.TACKLE);
			movebank[4] = new Node(Move.ODOR_SLEUTH);
			movebank[7] = new Node(Move.BITE);
			movebank[11] = new Node(Move.FURY_SWIPES);
			movebank[14] = new Node(Move.TAKE_DOWN);
			movebank[19] = new Node(Move.SLAM);
			movebank[23] = new Node(Move.CRUNCH);
			//movebank[24] = new Node(Move.EXTRAORDINARY);
			movebank[24] = new Node(Move.MACH_PUNCH);
			movebank[25] = new Node(Move.LOW_KICK);
			movebank[29] = new Node(Move.SWAGGER);
			movebank[33] = new Node(Move.BRICK_BREAK);
			movebank[38] = new Node(Move.TAKE_DOWN);
			//movebank[42] = new Node(Move.ASSURANCE);
			movebank[44] = new Node(Move.HI_JUMP_KICK);
			movebank[48] = new Node(Move.ROCK_SLIDE);
			movebank[54] = new Node(Move.BULK_UP);
			movebank[64] = new Node(Move.CLOSE_COMBAT);
			break;
		case -122:
			movebank = new Node[65];
			movebank[0] = new Node(Move.LEER);
			movebank[1] = new Node(Move.TACKLE);
			movebank[4] = new Node(Move.ODOR_SLEUTH);
			movebank[7] = new Node(Move.BITE);
			movebank[11] = new Node(Move.FURY_SWIPES);
			movebank[14] = new Node(Move.TAKE_DOWN);
			movebank[19] = new Node(Move.SLAM);
			movebank[23] = new Node(Move.CRUNCH);
			//movebank[24] = new Node(Move.EXTRAORDINARY);
			movebank[24] = new Node(Move.DRAGON_RAGE);
			movebank[28] = new Node(Move.DRAGON_BREATH);
			movebank[33] = new Node(Move.TAKE_DOWN);
			movebank[36] = new Node(Move.DRAGON_DANCE);
			movebank[42] = new Node(Move.IRON_TAIL);
			movebank[47] = new Node(Move.DRAGON_PULSE);
			movebank[53] = new Node(Move.DRAGON_RUSH);
			movebank[59] = new Node(Move.OUTRAGE);
			movebank[64] = new Node(Move.DRACO_METEOR);
			break;
		case -123:
			movebank = new Node[65];
			movebank[0] = new Node(Move.LEER);
			movebank[1] = new Node(Move.TACKLE);
			movebank[4] = new Node(Move.ODOR_SLEUTH);
			movebank[7] = new Node(Move.BITE);
			movebank[11] = new Node(Move.FURY_SWIPES);
			movebank[14] = new Node(Move.TAKE_DOWN);
			movebank[19] = new Node(Move.SLAM);
			movebank[23] = new Node(Move.CRUNCH);
			//movebank[24] = new Node(Move.EXTRAORDINARY);
			movebank[26] = new Node(Move.SWIFT);
			//movebank[32] = new Node(Move.PSYCHO_BLADE);
			movebank[36] = new Node(Move.TAKE_DOWN);
			movebank[41] = new Node(Move.FLAMETHROWER);
			movebank[44] = new Node(Move.AURA_SPHERE);
			movebank[49] = new Node(Move.MAGIC_REFLECT);
			movebank[55] = new Node(Move.MAGIC_TOMB);
			movebank[60] = new Node(Move.STAR_STORM);
			//movebank[64] = new Node(Move.GALAXY_ATTACK);
			break;
		case -124:
			movebank = new Node[12];
			movebank[0] = new Node(Move.TACKLE);
			movebank[1] = new Node(Move.LEER);
			//movebank[4] = new Node(Move.LEAF_SLAP);
			movebank[7] = new Node(Move.DEFENSE_CURL);
			movebank[11] = new Node(Move.ABSORB);
			break;
		case -125:
			movebank = new Node[32];
			movebank[0] = new Node(Move.TACKLE);
			movebank[1] = new Node(Move.LEER);
			//movebank[4] = new Node(Move.LEAF_SLAP);
			movebank[7] = new Node(Move.DEFENSE_CURL);
			movebank[11] = new Node(Move.ABSORB);
			movebank[16] = new Node(Move.HAMMER_ARM);
			movebank[19] = new Node(Move.MEGA_DRAIN);
			//movebank[21] = new Node(Move.DOUBLE_PUNCH);
			movebank[24] = new Node(Move.ROCK_TOMB);
			movebank[27] = new Node(Move.LEAF_BLADE);
			movebank[31] = new Node(Move.SYNTHESIS);
			break;
		case -126:
			movebank = new Node[70];
			movebank[0] = new Node(Move.TACKLE);
			movebank[1] = new Node(Move.LEER);
			//movebank[4] = new Node(Move.LEAF_SLAP);
			movebank[7] = new Node(Move.DEFENSE_CURL);
			movebank[11] = new Node(Move.ABSORB);
			movebank[16] = new Node(Move.HAMMER_ARM);
			movebank[19] = new Node(Move.MEGA_DRAIN);
			//movebank[21] = new Node(Move.DOUBLE_PUNCH);
			movebank[24] = new Node(Move.ROCK_TOMB);
			movebank[27] = new Node(Move.LEAF_BLADE);
			movebank[31] = new Node(Move.SYNTHESIS);
			movebank[36] = new Node(Move.GIGA_DRAIN);
			movebank[39] = new Node(Move.POISON_FANG);
			//movebank[43] = new Node(Move.WRING_OUT);
			//movebank[47] = new Node(Move.NEEDLE_SPRAY);
			//movebank[49] = new Node(Move.DUAL_STAB);
			movebank[52] = new Node(Move.DOUBLE_TEAM);
			movebank[57] = new Node(Move.LEAF_STORM);
			movebank[62] = new Node(Move.CLOSE_COMBAT);
			movebank[69] = new Node(Move.FRENZY_PLANT);
			break;
		case -127:
			movebank = new Node[13];
			movebank[0] = new Node(Move.SCRATCH);
			movebank[1] = new Node(Move.TAIL_WHIP);
			movebank[6] = new Node(Move.SPARK);
			movebank[8] = new Node(Move.SMOKESCREEN);
			movebank[12] = new Node(Move.EMBER);
			break;
		case -128:
			movebank = new Node[28];
			movebank[0] = new Node(Move.SCRATCH);
			movebank[1] = new Node(Move.TAIL_WHIP);
			movebank[6] = new Node(Move.SPARK);
			movebank[8] = new Node(Move.SMOKESCREEN);
			movebank[12] = new Node(Move.EMBER);
			movebank[16] = new Node(Move.QUICK_ATTACK);
			movebank[18] = new Node(Move.FLAME_WHEEL);
			movebank[20] = new Node(Move.AGILITY);
			movebank[23] = new Node(Move.CRUNCH);
			//movebank[27] = new Node(Move.FIRE_CHARGE);
			break;
		case -129:
			movebank = new Node[70];
			movebank[0] = new Node(Move.SCRATCH);
			movebank[1] = new Node(Move.TAIL_WHIP);
			movebank[6] = new Node(Move.SPARK);
			movebank[8] = new Node(Move.SMOKESCREEN);
			movebank[12] = new Node(Move.EMBER);
			movebank[16] = new Node(Move.QUICK_ATTACK);
			movebank[18] = new Node(Move.FLAME_WHEEL);
			movebank[20] = new Node(Move.AGILITY);
			movebank[23] = new Node(Move.CRUNCH);
			//movebank[27] = new Node(Move.FIRE_CHARGE);
			movebank[36] = new Node(Move.FLAMETHROWER);
			movebank[41] = new Node(Move.BOUNCE);
			movebank[44] = new Node(Move.AUTOMOTIZE);
			movebank[49] = new Node(Move.FLAME_BURST);
			movebank[53] = new Node(Move.EXTREME_SPEED);
			movebank[57] = new Node(Move.DRILL_RUN);
			movebank[61] = new Node(Move.FRUSTERATION);
			movebank[65] = new Node(Move.FIRE_BLAST);
			movebank[69] = new Node(Move.FLARE_BLITZ);
			break;
		case -130:
			movebank = new Node[16];
			movebank[0] = new Node(Move.POUND);
			movebank[1] = new Node(Move.LEER);
			movebank[6] = new Node(Move.BUBBLE);
			movebank[8] = new Node(Move.BUBBLEBEAM);
			movebank[12] = new Node(Move.BITE);
			movebank[15] = new Node(Move.SLAM);
			break;
		case -131:
			movebank = new Node[34];
			movebank[0] = new Node(Move.POUND);
			movebank[1] = new Node(Move.LEER);
			movebank[6] = new Node(Move.BUBBLE);
			movebank[8] = new Node(Move.BUBBLEBEAM);
			movebank[12] = new Node(Move.BITE);
			movebank[15] = new Node(Move.SLAM);
			movebank[19] = new Node(Move.WATER_PULSE);
			movebank[24] = new Node(Move.CRUNCH);
			movebank[27] = new Node(Move.AQUA_TAIL);
			movebank[30] = new Node(Move.HYPER_FANG);
			movebank[33] = new Node(Move.TAKE_DOWN);
			break;
		case -132:
			movebank = new Node[70];
			movebank[0] = new Node(Move.POUND);
			movebank[1] = new Node(Move.LEER);
			movebank[6] = new Node(Move.BUBBLE);
			movebank[8] = new Node(Move.BUBBLEBEAM);
			movebank[12] = new Node(Move.BITE);
			movebank[15] = new Node(Move.SLAM);
			movebank[19] = new Node(Move.WATER_PULSE);
			movebank[24] = new Node(Move.CRUNCH);
			movebank[27] = new Node(Move.AQUA_TAIL);
			movebank[30] = new Node(Move.HYPER_FANG);
			movebank[33] = new Node(Move.TAKE_DOWN);
			movebank[37] = new Node(Move.BEAT_UP);
			movebank[41] = new Node(Move.AQUA_TAIL);
			movebank[44] = new Node(Move.EARTH_POWER);
			movebank[48] = new Node(Move.DRAGON_PULSE);
			movebank[52] = new Node(Move.DIVE);
			movebank[54] = new Node(Move.HYDRO_PUMP);
			movebank[62] = new Node(Move.DRAGON_RUSH);
			//movebank[69] = new Node(Move.TIDAL_WAVE);
			break;
		case -133:
			movebank = new Node[85];
			movebank[0] = new Node(Move.THUNDER_FANG);
			movebank[1] = new Node(Move.DRAGON_RAGE);
			movebank[7] = new Node(Move.THUNDER_WAVE);
			movebank[14] = new Node(Move.ANCIENT_POWER);
			movebank[21] = new Node(Move.THUNDERBOLT);
			movebank[28] = new Node(Move.DRAGON_BREATH);
			movebank[35] = new Node(Move.SLASH);
			movebank[42] = new Node(Move.STAR_STORM);
			movebank[49] = new Node(Move.DRAGON_CLAW);
			movebank[53] = new Node(Move.MAGIC_REFLECT);
			movebank[58] = new Node(Move.THUNDER);
			movebank[62] = new Node(Move.OUTRAGE);
			movebank[70] = new Node(Move.HYPER_BEAM);
			movebank[77] = new Node(Move.DRACO_METEOR);
			movebank[84] = new Node(Move.BOLT_STRIKE);
			break;
		case -134:
			movebank = new Node[85];
			movebank[0] = new Node(Move.FIRE_FANG);
			movebank[1] = new Node(Move.DRAGON_RAGE);
			movebank[7] = new Node(Move.WILL_O_WISP);
			movebank[14] = new Node(Move.ANCIENT_POWER);
			movebank[21] = new Node(Move.FLAMETHROWER);
			movebank[28] = new Node(Move.DRAGON_BREATH);
			movebank[35] = new Node(Move.SLASH);
			movebank[42] = new Node(Move.STAR_STORM);
			movebank[49] = new Node(Move.DRAGON_PULSE);
			movebank[53] = new Node(Move.MAGIC_REFLECT);
			movebank[58] = new Node(Move.FIRE_BLAST);
			movebank[62] = new Node(Move.OUTRAGE);
			movebank[70] = new Node(Move.HYPER_BEAM);
			movebank[77] = new Node(Move.DRACO_METEOR);
			movebank[84] = new Node(Move.BLUE_FLARE);
			break;
		case -135:
			movebank = new Node[100];
			//movebank[0] = new Node(Move.DISAPPEAR);
			//movebank[1] = new Node(Move.DOUBLE_BLAST);
			movebank[5] = new Node(Move.VINE_WHIP);
			//movebank[7] = new Node(Move.DARK_VOID);
			movebank[8] = new Node(Move.DREAM_EATER);
			movebank[9] = new Node(Move.ANCIENT_POWER);
			//movebank[16] = new Node(Move.BLACK_HOLE);
			movebank[19] = new Node(Move.CONFUSION);
			//movebank[24] = new Node(Move.EXTRAORDINARY);
			movebank[30] = new Node(Move.DRAGON_PULSE);
			movebank[36] = new Node(Move.GIGA_IMPACT);
			movebank[45] = new Node(Move.MAGIC_BLAST);
			movebank[52] = new Node(Move.X_SCIZZOR);
			movebank[61] = new Node(Move.AEROBLAST);
			movebank[74] = new Node(Move.MAGIC_REFLECT);
			movebank[87] = new Node(Move.STAR_STORM);
			movebank[94] = new Node(Move.ERUPTION);
			//movebank[99] = new Node(Move.ELECTROEXPLOSION);
			break;
		case -136:
			movebank = new Node[100];
			movebank[0] = new Node(Move.FLAMETHROWER);
			movebank[1] = new Node(Move.AURA_SPHERE);
			movebank[5] = new Node(Move.BUG_BUZZ);
			movebank[7] = new Node(Move.CHARGE);
			movebank[8] = new Node(Move.DISCHARGE);
			movebank[9] = new Node(Move.ANCIENT_POWER);
			//movebank[16] = new Node(Move.BLACK_HOLE);
			movebank[19] = new Node(Move.CONFUSION);
			//movebank[24] = new Node(Move.EXTRAORDINARY);
			movebank[30] = new Node(Move.DARK_PULSE);
			movebank[36] = new Node(Move.COMET_CRASH);
			movebank[45] = new Node(Move.MAGIC_BLAST);
			movebank[52] = new Node(Move.X_SCIZZOR);
			movebank[61] = new Node(Move.HYDRO_PUMP);
			movebank[74] = new Node(Move.SOLAR_BEAM);
			movebank[87] = new Node(Move.MAGIC_CRASH);
			movebank[94] = new Node(Move.DRACO_METEOR);
			movebank[99] = new Node(Move.BOLT_STRIKE);
			break;
		case -137:
			movebank = new Node[100];
			movebank[0] = new Node(Move.THUNDERBOLT);
			movebank[1] = new Node(Move.TWISTER);
			movebank[5] = new Node(Move.WING_ATTACK);
			movebank[7] = new Node(Move.SLEEP_POWDER);
			//movebank[8] = new Node(Move.POISON_POWDER);
			movebank[9] = new Node(Move.AIR_CUTTER);
			movebank[16] = new Node(Move.WATER_PULSE);
			movebank[19] = new Node(Move.CONFUSION);
			//movebank[24] = new Node(Move.EXTRAORDINARY);
			movebank[30] = new Node(Move.DRAGON_CLAW);
			movebank[36] = new Node(Move.THUNDER);
			//movebank[45] = new Node(Move.POISON_PUNCH);
			movebank[52] = new Node(Move.FIRE_BLAST);
			movebank[61] = new Node(Move.MAGIC_BLAST);
			movebank[74] = new Node(Move.MAGIC_REFLECT);
			movebank[87] = new Node(Move.MAGIC_CRASH);
			movebank[94] = new Node(Move.BOLT_STRIKE);
			movebank[99] = new Node(Move.BLUE_FLARE);
			break;
		case -138:
			movebank = new Node[100];
			movebank[0] = new Node(Move.HYPER_BEAM);
			//movebank[1] = new Node(Move.GALAXY_ATTACK);
			//movebank[24] = new Node(Move.EXTRAORDINARY);
			movebank[29] = new Node(Move.DRAGON_PULSE);
			movebank[34] = new Node(Move.GIGA_IMPACT);
			movebank[39] = new Node(Move.STAR_STORM);
			movebank[44] = new Node(Move.MAGIC_BLAST);
			movebank[49] = new Node(Move.X_SCIZZOR);
			//movebank[52] = new Node(Move.BOULDER_CRUSH);
			//movebank[55] = new Node(Move.PHASE_SHIFT);
			movebank[58] = new Node(Move.ERUPTION);
			movebank[61] = new Node(Move.AEROBLAST);
			movebank[64] = new Node(Move.HYPER_BEAM);
			//movebank[69] = new Node(Move.BLAST_FLAME);
			movebank[74] = new Node(Move.DESTINY_BOND);
			//movebank[79] = new Node(Move.ELECTROEXPLOSION);
			movebank[84] = new Node(Move.TAKE_OVER);
			movebank[89] = new Node(Move.DRACO_METEOR);
			movebank[94] = new Node(Move.STAR_STORM);
			movebank[99] = new Node(Move.BLUE_FLARE);
			break;
		case -139:
			movebank = new Node[100];
			movebank[0] = new Node(Move.HYPER_BEAM);
			//movebank[1] = new Node(Move.GALAXY_ATTACK);
			//movebank[24] = new Node(Move.EXTRAORDINARY);
			movebank[29] = new Node(Move.DARK_PULSE);
			movebank[34] = new Node(Move.COMET_CRASH);
			movebank[39] = new Node(Move.ERUPTION);
			movebank[44] = new Node(Move.MAGIC_BLAST);
			movebank[49] = new Node(Move.X_SCIZZOR);
			movebank[52] = new Node(Move.SOLAR_BEAM);
			movebank[55] = new Node(Move.FIRE_BLAST);
			movebank[58] = new Node(Move.IRON_TAIL);
			movebank[61] = new Node(Move.HYDRO_PUMP);
			movebank[64] = new Node(Move.STONE_EDGE);
			movebank[69] = new Node(Move.EARTH_POWER);
			movebank[74] = new Node(Move.MAGIC_REFLECT);
			movebank[79] = new Node(Move.DESTINY_BOND);
			movebank[84] = new Node(Move.MAGIC_CRASH);
			movebank[89] = new Node(Move.DRACO_METEOR);
			movebank[94] = new Node(Move.STAR_STORM);
			movebank[99] = new Node(Move.BOLT_STRIKE);
			break;
		case -140:
			movebank = new Node[100];
			movebank[0] = new Node(Move.HYPER_BEAM);
			//movebank[1] = new Node(Move.GALAXY_ATTACK);
			//movebank[24] = new Node(Move.EXTRAORDINARY);
			movebank[29] = new Node(Move.DRAGON_CLAW);
			movebank[34] = new Node(Move.THUNDER);
			movebank[39] = new Node(Move.ERUPTION);
			movebank[44] = new Node(Move.IRON_TAIL);
			movebank[49] = new Node(Move.BRAVE_BIRD);
			movebank[52] = new Node(Move.FIRE_BLAST);
			movebank[55] = new Node(Move.SKY_ATTACK);
			movebank[58] = new Node(Move.MAGIC_BLAST);
			movebank[61] = new Node(Move.MAGIC_REFLECT);
			movebank[64] = new Node(Move.MAGIC_CRASH);
			movebank[69] = new Node(Move.SUPERPOWER);
			movebank[74] = new Node(Move.HYDRO_PUMP);
			movebank[79] = new Node(Move.DESTINY_BOND);
			movebank[84] = new Node(Move.MAGIC_FANG);
			movebank[89] = new Node(Move.DRACO_METEOR);
			movebank[94] = new Node(Move.BOLT_STRIKE);
			movebank[99] = new Node(Move.BLUE_FLARE);
			break;
		case -141:
			movebank = new Node[25];
			movebank[0] = new Node(Move.SCRATCH);
			movebank[2] = new Node(Move.TAIL_WHIP);
			//movebank[6] = new Node(Move.POISON_POWDER);
			movebank[8] = new Node(Move.EMBER);
			movebank[11] = new Node(Move.WILL_O_WISP);
			movebank[12] = new Node(Move.FIRE_SPIN);
			movebank[14] = new Node(Move.FLAME_WHEEL);
			//movebank[18] = new Node(Move.FIRE_TAIL);
			movebank[21] = new Node(Move.FIRE_FANG);
			movebank[22] = new Node(Move.THUNDER_FANG);
			movebank[23] = new Node(Move.POISON_FANG);
			movebank[24] = new Node(Move.FURY_SWIPES);
			break;
		case -142:
			movebank = new Node[25];
			movebank[0] = new Node(Move.SCRATCH);
			movebank[2] = new Node(Move.TAIL_WHIP);
			//movebank[6] = new Node(Move.POISON_POWDER);
			movebank[8] = new Node(Move.BUBBLEBEAM);
			movebank[11] = new Node(Move.AQUA_RING);
			movebank[12] = new Node(Move.WATER_GUN);
			movebank[14] = new Node(Move.AQUA_JET);
			movebank[18] = new Node(Move.AQUA_TAIL);
			movebank[21] = new Node(Move.FIRE_FANG);
			movebank[22] = new Node(Move.THUNDER_FANG);
			movebank[23] = new Node(Move.POISON_FANG);
			movebank[24] = new Node(Move.FURY_SWIPES);
			break;
		case -143:
			movebank = new Node[55];
			movebank[0] = new Node(Move.SCRATCH);
			movebank[2] = new Node(Move.TAIL_WHIP);
			//movebank[6] = new Node(Move.POISON_POWDER);
			movebank[8] = new Node(Move.EMBER);
			movebank[11] = new Node(Move.WILL_O_WISP);
			movebank[12] = new Node(Move.FIRE_SPIN);
			movebank[14] = new Node(Move.FLAME_WHEEL);
			//movebank[18] = new Node(Move.FIRE_TAIL);
			movebank[21] = new Node(Move.FIRE_FANG);
			movebank[22] = new Node(Move.THUNDER_FANG);
			movebank[23] = new Node(Move.POISON_FANG);
			movebank[24] = new Node(Move.FURY_SWIPES);
			movebank[27] = new Node(Move.ROLLOUT);
			movebank[28] = new Node(Move.SLASH);
			movebank[29] = new Node(Move.TAKE_DOWN);
			movebank[31] = new Node(Move.FLAMETHROWER);
			movebank[36] = new Node(Move.ANCIENT_POWER);
			movebank[40] = new Node(Move.CRUNCH);
			movebank[45] = new Node(Move.SKULL_BASH);
			movebank[49] = new Node(Move.GIGA_IMPACT);
			movebank[54] = new Node(Move.FIRE_BLAST);
			break;
		case -144:
			movebank = new Node[55];
			movebank[0] = new Node(Move.SCRATCH);
			movebank[2] = new Node(Move.TAIL_WHIP);
			//movebank[6] = new Node(Move.POISON_POWDER);
			movebank[8] = new Node(Move.BUBBLEBEAM);
			movebank[11] = new Node(Move.AQUA_RING);
			movebank[12] = new Node(Move.WATER_GUN);
			movebank[14] = new Node(Move.AQUA_JET);
			movebank[18] = new Node(Move.AQUA_TAIL);
			movebank[21] = new Node(Move.FIRE_FANG);
			movebank[22] = new Node(Move.THUNDER_FANG);
			movebank[23] = new Node(Move.POISON_FANG);
			movebank[24] = new Node(Move.FURY_SWIPES);
			movebank[27] = new Node(Move.ROLLOUT);
			movebank[28] = new Node(Move.SLASH);
			movebank[29] = new Node(Move.TAKE_DOWN);
			movebank[31] = new Node(Move.BRINE);
			movebank[36] = new Node(Move.ANCIENT_POWER);
			movebank[40] = new Node(Move.CRUNCH);
			movebank[45] = new Node(Move.SKULL_BASH);
			movebank[49] = new Node(Move.GIGA_IMPACT);
			movebank[54] = new Node(Move.HYDRO_PUMP);
			break;
		}
		
	}
	
	public class Node implements Serializable {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public Move data;
	    public Node next;

	    public Node(Move data) { this.data = data; }
	}

	public void faint(boolean announce, Player player) {
		this.currentHP = 0;
		this.fainted = true;
		this.battled = false;
		this.vStatuses.remove(Status.LOCKED);
		this.vStatuses.remove(Status.SPUN);
		this.vStatuses.remove(Status.RECHARGE);
		this.vStatuses.remove(Status.CHARGING);
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
		setAbility(this.abilitySlot);
		
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
		
		if (move.accuracy <= 100 && move.cat != 2) {
			if (getImmune(foe, move.mtype)) return 0; // Check for immunity
		} else if (move.accuracy > 100 && move.cat != 2) {
			if (getImmune(foe, move.mtype)) return 0; // Check for immunity
		}
		if (move.cat != 2 && move.mtype == PType.GROUND && foe.magCount > 0) return 0; // Check for immunity
		
		if (move == Move.DREAM_EATER && foe.status != Status.ASLEEP) return 0;
		
		if (move.basePower < 0) bp = determineBasePower(foe, move);
		
		//if (this.vStatuses.contains(Status.AUTO) && (move == Move.BIG_BULLET || move == Move.GUNSHOT || move == Move.ROCKET)) bp *= 2;
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
			if (this.status == Status.FROSTBITE) attackStat /= 2;
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
		//if (move == Move.FIRE_DASH) damage = this.currentHP;
		if (move == Move.SUPER_FANG) damage = foe.currentHP / 2;
		if (move == Move.DRAGON_RAGE) damage = 40;
		
		damage = Math.max(damage, 1);
		
		return damage;
	}

	public static void endOfTurn(Pokemon p, Pokemon f, Player player) {
		if (p.isFainted()) return;
		if (p.status == Status.FROSTBITE) {
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
		if (this.status == Status.PARALYZED) speed *= 0.5;
		return (int) speed;
	}
	
	public Pokemon getFaster(Pokemon other, Field field, int thisP, int otherP) {
		int speed1 = this.getSpeed();
		if (field.contains(field.playerSide, Effect.TAILWIND)) speed1 *= 2;
		if (checkAbilitySpeedBoost(this.ability, field)) speed1 *= 2;
		int speed2 = other.getSpeed();
		if (field.contains(field.foeSide, Effect.TAILWIND)) speed2 *= 2;
		if (checkAbilitySpeedBoost(other.ability, field)) speed2 *= 2;
		Pokemon faster = speed1 > speed2 ? this : other;
		if (speed1 == speed2) {
			Random random = new Random();
			boolean isHeads = random.nextBoolean();
			faster = isHeads ? this : other;
		}
		if (field.contains(field.fieldEffects, Effect.TRICK_ROOM)) {
			faster = faster == this ? other : this;
		}
		faster = otherP > thisP ? other : faster;
		faster = thisP > otherP ? this : faster;
		return faster;
	}
	
	private boolean checkAbilitySpeedBoost(Ability ability, Field field) {
		if (field.equals(field.weather, Effect.SUN) && ability == Ability.CHLOROPHYL) return true;
		if (field.equals(field.weather, Effect.RAIN) && ability == Ability.SWIFT_SWIM) return true;
		if (field.equals(field.weather, Effect.SANDSTORM) && ability == Ability.SAND_RUSH) return true;
		if (field.equals(field.weather, Effect.SNOW) && ability == Ability.SLUSH_RUSH) return true;
		return false;
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
			if (announce) fail();
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
			if (announce) fail();
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
			if (announce) fail();
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
			if (announce) fail();
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
//		} else if (move == Move.DOUBLE_BLAST) {
//	        System.out.println("Hit " + 2 + " times!");
//	        bp = 80;
		} else if (move == Move.DOUBLE_HIT) {
	        System.out.println("Hit " + 2 + " times!");
	        bp = 70;
//		} else if (move == Move.DOUBLE_JET) {
//			int randomNum = (int) (Math.random() * 4) + 2;
//	        System.out.println("Hit " + randomNum + " times!");
//	        bp = 20 * randomNum;
		} else if (move == Move.DOUBLE_KICK) {
	        System.out.println("Hit " + 2 + " times!");
	        bp = 60;
//		} else if (move == Move.DOUBLE_PUNCH) {
//	        System.out.println("Hit " + 2 + " times!");
//	        bp = 60;
		} else if (move == Move.DOUBLE_SLAP) {
			int randomNum = (int) (Math.random() * 4) + 2;
	        System.out.println("Hit " + randomNum + " times!");
	        bp = 15 * randomNum;
//		} else if (move == Move.DOUBLE_SLICE) {
//	        System.out.println("Hit " + 2 + " times!");
//	        bp = 60;
//		} else if (move == Move.DUAL_STAB) {
//	        System.out.println("Hit " + 2 + " times!");
//	        bp = 60;
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
//		} else if (move == Move.FIREBALL) {
//			if (foe.status == Status.BURNED) {
//				bp = 120;
//			} else {
//				bp = 60;
//			}
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
//		} else if (move == Move.SPIKE_SHOT) {
//			int randomNum = (int) (Math.random() * 4) + 2;
//	        System.out.println("Hit " + randomNum + " times!");
//	        bp = 20 * randomNum;
//		} else if (move == Move.TIDAL_WAVE) {
//			int wave = (int) (Math.random()*3 + 1);
//			if (wave == 1) {
//				bp = 90;
//				System.out.println("Morning Tide!");
//			} else if (wave == 2) {
//				bp = 50;
//				System.out.println("Day Tide!");
//			} else if (wave == 3) {
//				bp = 130;
//				System.out.println("Evening Tide!");
//			}
		} else if (move == Move.WAKE_UP_SLAP) {
			bp = 60;
			if (foe.status == Status.ASLEEP) {
				bp = 120;
				foe.sleepCounter = 0;
			}
//		} else if (move == Move.WRING_OUT) {
//			double hpRatio = foe.currentHP * 1.0 / foe.getStat(0);
//			hpRatio *= 120;
//			bp = Math.max((int) hpRatio, 1);
		}
		return bp;
	}
	
	private void removeBad(ArrayList<Status> stati) {
		stati.remove(Status.CONFUSED);
		stati.remove(Status.CURSED);
		stati.remove(Status.LEECHED);
		stati.remove(Status.NIGHTMARE);
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

	public int[] getIVs() {
		return ivs;
	}
	
	private void setNature() {
		nature = new double[] {1,1,1,1,1,-1};
		Random random = new Random();
        int increaseIndex = random.nextInt(5);
        int decreaseIndex = random.nextInt(5);
        
        if (increaseIndex == decreaseIndex) {
        	nature[5] = increaseIndex;
        } else {
        	nature[decreaseIndex] = 0.9; // Decreased stat multiplied by 0.9
            nature[increaseIndex] = 1.1; // Increased stat multiplied by 1.1
        }
	}
	
	public String getNature() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < nature.length; i++) {
			s.append(nature[i]);
			if (i != nature.length - 1) s.append(",");
		}
		String ns = s.toString();
		String natureString;
		switch (ns) {
		case "1.0,1.0,1.0,1.0,1.0,0.0":
			natureString = "Hardy";
			break;
		case "1.0,1.0,1.0,1.0,1.0,1.0":
			natureString = "Docile";
			break;
		case "1.0,1.0,1.0,1.0,1.0,2.0":
			natureString = "Bashful";
			break;
		case "1.0,1.0,1.0,1.0,1.0,3.0":
			natureString = "Quirky";
			break;
		case "1.0,1.0,1.0,1.0,1.0,4.0":
			natureString = "Serious";
			break;
		case "1.1,0.9,1.0,1.0,1.0,-1.0":
			natureString = "Lonely";
			break;
		case "1.1,1.0,0.9,1.0,1.0,-1.0":
			natureString = "Adamant";
			break;
		case "1.1,1.0,1.0,0.9,1.0,-1.0":
			natureString = "Naughty";
			break;
		case "1.1,1.0,1.0,1.0,0.9,-1.0":
			natureString = "Brave";
			break;
		case "0.9,1.1,1.0,1.0,1.0,-1.0":
			natureString = "Bold";
			break;
		case "1.0,1.1,0.9,1.0,1.0,-1.0":
			natureString = "Impish";
			break;
		case "1.0,1.1,1.0,0.9,1.0,-1.0":
			natureString = "Lax";
			break;
		case "1.0,1.1,1.0,1.0,0.9,-1.0":
			natureString = "Relaxed";
			break;
		case "0.9,1.0,1.1,1.0,1.0,-1.0":
			natureString = "Modest";
			break;
		case "1.0,0.9,1.1,1.0,1.0,-1.0":
			natureString = "Mild";
			break;
		case "1.0,1.0,1.1,0.9,1.0,-1.0":
			natureString = "Rash";
			break;
		case "1.0,1.0,1.1,1.0,0.9,-1.0":
			natureString = "Quiet";
			break;
		case "0.9,1.0,1.0,1.1,1.0,-1.0":
			natureString = "Calm";
			break;
		case "1.0,0.9,1.0,1.1,1.0,-1.0":
			natureString = "Gentle";
			break;
		case "1.0,1.0,0.9,1.1,1.0,-1.0":
			natureString = "Careful";
			break;
		case "1.0,1.0,1.0,1.1,0.9,-1.0":
			natureString = "Sassy";
			break;
		case "0.9,1.0,1.0,1.0,1.1,-1.0":
			natureString = "Timid";
			break;
		case "1.0,0.9,1.0,1.0,1.1,-1.0":
			natureString = "Hasty";
			break;
		case "1.0,1.0,0.9,1.0,1.1,-1.0":
			natureString = "Jolly";
			break;
		case "1.0,1.0,1.0,0.9,1.1,-1.0":
			natureString = "Naive";
			break;
		default:
			natureString = ns;
			break;
		}
			
//		natureString += " (";
//		int increasedStat, decreasedStat;
//		increasedStat = decreasedStat = -1;
//		for (int i = 0; i < nature.length; i++) {
//			if (nature[i] == 1.1) increasedStat = i;
//			if (nature[i] == 0.9) decreasedStat = i;
//		}
//		if (increasedStat != -1 || decreasedStat != -1) {
//			natureString += "+" + getStatName(increasedStat) + ",-" + getStatName(decreasedStat);
//		} else {
//			natureString += "Neutral";
//		}
//		natureString += ")";
		
		return natureString;
	}



	public JPanel showSummary() {
	    JPanel teamMemberPanel = new JPanel();
	    teamMemberPanel.setLayout(new BoxLayout(teamMemberPanel, BoxLayout.Y_AXIS));

	    JLabel nameLabel, hp, at, de, sa, sd, sp, abilityLabel, abilityDescLabel, natureLabel, hpLabel, statusLabel;
	    nameLabel = hp = at = de = sa = sd = sp = abilityLabel = abilityDescLabel = natureLabel = hpLabel = statusLabel = new JLabel("N/A");
	    JGradientButton type1B, type2B;
	    JPanel movesPanel = new JPanel(new GridLayout(2, 2));
	    type1B = new JGradientButton("");
	    type2B = new JGradientButton("");
	    if (this != null) {
	        nameLabel = new JLabel(this.getName() + " Lv. " + this.getLevel());
	        nameLabel.setForeground(this.type1.getColor().darker());
	        nameLabel.setFont(new Font(nameLabel.getFont().getName(), Font.BOLD, 16));
	        hpLabel = new JLabel(this.currentHP + " / " + this.getStat(0) + " HP");
	        hpLabel.setFont(new Font(hpLabel.getFont().getName(), Font.BOLD, 14));
	        type1B.setText(this.type1.toString());
	        type1B.setBackground(this.type1.getColor());
	        if (this.type2 != null) {
	            type2B.setText(this.type2.toString());
	            type2B.setBackground(this.type2.getColor());
	        }
	        hp = new JLabel("HP: " + this.stats[0] + ", IV: " + this.getIVs()[0] + "  (" + this.getBaseStat(0) + ")");
	        at = new JLabel("Atk: " + this.stats[1] + ", IV: " + this.getIVs()[1] + "  (" + this.getBaseStat(1) + ")");
	        de = new JLabel("Def: " + this.stats[2] + ", IV: " + this.getIVs()[2] + "  (" + this.getBaseStat(2) + ")");
	        sa = new JLabel("SpA: " + this.stats[3] + ", IV: " + this.getIVs()[3] + "  (" + this.getBaseStat(3) + ")");
	        sd = new JLabel("SpD: " + this.stats[4] + ", IV: " + this.getIVs()[4] + "  (" + this.getBaseStat(4) + ")");
	        sp = new JLabel("Spe: " + this.stats[5] + ", IV: " + this.getIVs()[5] + "  (" + this.getBaseStat(5) + ")");
	        if (this.nature[0] == 1.1) at.setForeground(Color.red.darker().darker());
	        if (this.nature[0] == 0.9) at.setForeground(Color.blue.darker().darker());
	        if (this.nature[1] == 1.1) de.setForeground(Color.red.darker().darker());
	        if (this.nature[1] == 0.9) de.setForeground(Color.blue.darker().darker());
	        if (this.nature[2] == 1.1) sa.setForeground(Color.red.darker().darker());
	        if (this.nature[2] == 0.9) sa.setForeground(Color.blue.darker().darker());
	        if (this.nature[3] == 1.1) sd.setForeground(Color.red.darker().darker());
	        if (this.nature[3] == 0.9) sd.setForeground(Color.blue.darker().darker());
	        if (this.nature[4] == 1.1) sp.setForeground(Color.red.darker().darker());
	        if (this.nature[4] == 0.9) sp.setForeground(Color.blue.darker().darker());
	        abilityLabel = new JLabel("Ability: " + this.ability.toString());
	        abilityDescLabel = new JLabel(this.ability.desc);
	        abilityLabel.setFont(new Font(hpLabel.getFont().getName(), Font.BOLD, 14));
	        natureLabel = new JLabel(this.getNature() + " Nature");

	        for (int i = 0; i < 4; i++) {
	        	JButton moveButton = new JGradientButton("");
	            if (moveset[i] != null) {
	                moveButton.setText(moveset[i].toString());
	                moveButton.setBackground(moveset[i].mtype.getColor());
	                int index = i;
	                moveButton.addActionListener(e -> {
	                	String message = "Move: " + moveset[index].toString() + "\n";
			            message += "Type: " + moveset[index].mtype + "\n";
			            message += "BP: " + moveset[index].getbp() + "\n";
			            message += "Accuracy: " + moveset[index].accuracy + "\n";
			            message += "Category: " + moveset[index].getCategory() + "\n";
			            message += "Description: " + moveset[index].getDescription();
			            JOptionPane.showMessageDialog(null, message, "Move Description", JOptionPane.INFORMATION_MESSAGE);
	                });
	            }
	            movesPanel.add(moveButton);
	        }
	        statusLabel = (this.isFainted()) ? new JLabel("Status: FAINTED") : new JLabel("Status: " + this.status.toString());
	        if (!this.isFainted() && this.status == Status.HEALTHY) {
	            statusLabel.setForeground(Color.GREEN.darker());
	        } else if (this.isFainted()) {
	            statusLabel.setForeground(Color.RED.darker());
	        } else {
	            statusLabel.setForeground(this.status.getColor());
	        }
	    }

	    JPanel nameLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    nameLabelPanel.add(nameLabel);
	    teamMemberPanel.add(nameLabelPanel);

	    JPanel hpLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    hpLabelPanel.add(hpLabel);
	    teamMemberPanel.add(hpLabelPanel);

	    JPanel typesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    typesPanel.add(type1B);
	    typesPanel.add(type2B);
	    teamMemberPanel.add(typesPanel);
	    
	    JPanel abilityLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    abilityLabelPanel.add(abilityLabel);
	    teamMemberPanel.add(abilityLabelPanel);
	    
	    JPanel abilityDescLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    abilityDescLabelPanel.add(abilityDescLabel);
	    teamMemberPanel.add(abilityDescLabelPanel);

	    JPanel natureLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    natureLabelPanel.add(natureLabel);
	    teamMemberPanel.add(natureLabelPanel);
	    
	    JPanel hpPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    hpPanel.add(hp);
	    teamMemberPanel.add(hpPanel);
	    
	    JPanel atPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    atPanel.add(at);
	    teamMemberPanel.add(atPanel);
	    
	    JPanel dePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    dePanel.add(de);
	    teamMemberPanel.add(dePanel);
	    
	    JPanel saPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    saPanel.add(sa);
	    teamMemberPanel.add(saPanel);
	    
	    JPanel sdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    sdPanel.add(sd);
	    teamMemberPanel.add(sdPanel);
	    
	    JPanel spPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    spPanel.add(sp);
	    teamMemberPanel.add(spPanel);
	    
	    teamMemberPanel.add(movesPanel);
	    
	    JPanel statusLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    statusLabelPanel.add(statusLabel);
	    teamMemberPanel.add(statusLabelPanel);
	    
	    teamMemberPanel.add(Box.createHorizontalGlue());

	    return teamMemberPanel;
	}





//	private String getStatName(int stat) {
//		switch(stat) {
//		case 0:
//			return "Atk";
//		case 1:
//			return "Def";
//		case 2:
//			return "SpA";
//		case 3:
//			return "SpD";
//		case 4:
//			return "Spe";
//		default:
//			return "null";
//		}
//	}
}
