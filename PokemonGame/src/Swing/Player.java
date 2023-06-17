package Swing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2851052666892205583L;
	public Pokemon[] team;
	public Pokemon[] box1, box2, box3;
	public int money;
	public Pokemon current;
	private int numBattled;
	public ArrayList<String> trainersBeat;
	private int posX;
	private int posY;
	public Bag bag;
	public int badges;
	public int starter;
	
	public Player() {
		team = new Pokemon[6];
		box1 = new Pokemon[30];
		box2 = new Pokemon[30];
		box3 = new Pokemon[30];
		money = 0;
		current = null;
		trainersBeat = new ArrayList<String>();
		bag = new Bag();
		posX = posY = 23;
	}
	
	public Pokemon getCurrent() {
		return this.current;
	}
	
	public void catchPokemon(Pokemon p) {
	    if (p.isFainted()) return;
	    boolean hasNull = false;
	    for (int i = 0; i < team.length; i++) {
	        if (team[i] == null) {
	            hasNull = true;
	            break;
	        }
	    }
	    if (hasNull) {
	        for (int i = 0; i < team.length; i++) {
	            if (team[i] == null) {
	                team[i] = p;
	                p.slot = i;
	                System.out.println("Caught " + p.name + ", added to party!");
	                current = team[0];
	                break;
	            }
	        }
	    } else {
	        int index = -1;
	        Pokemon[][] boxes = {box1, box2, box3};  // Array of box references
	        for (int i = 0; i < boxes.length; i++) {
	            for (int j = 0; j < boxes[i].length; j++) {
	                if (boxes[i][j] == null) {
	                    index = j;
	                    break;
	                }
	            }
	            if (index >= 0) {
	                boxes[i][index] = p;
	                System.out.println("Caught " + p.name + ", sent to box " + (i+1) + "!");
	                return;  // Exit the method after catching the Pok�mon
	            }
	        }
	        System.out.println("Cannot catch " + p.name + ", all boxes are full");
	    }
	}


	public void swap(Pokemon pokemon, int index) {
		System.out.println("\n" + current.name + ", come back!");
		Pokemon lead = current;
		lead.clearVolatile();
		this.current = pokemon;
		this.team[0] = pokemon;
		this.team[index] = lead;
		if (!this.current.battled) {
			numBattled++;
			this.current.battled = true;
		}
		System.out.println("Go " + current.name + "!");
		
	}
	
	public int getBattled() {
		return numBattled;
	}
	
	public void setBattled(int battled) {
		numBattled = battled;
	}
	
	public void clearBattled() {
		for (Pokemon p : team) {
			if (p != null) p.battled = false;
		}
		numBattled = 1;
	}

	public Pokemon[] getTeam() {
		return team;
	}

	public int getPosX() {
		return posX;
	}
	
	public void setPosX(int x) {
		posX = x;
	}

	public int getPosY() {
		return posY;
	}
	
	public void setPosY(int y) {
		posY = y;
	}
	
	public Bag getBag() {
		return bag;
	}
	
	public void elevate(Pokemon pokemon) {
		int expAmt = pokemon.expMax - pokemon.exp;
    	pokemon.exp += expAmt;
    	while (pokemon.exp >= pokemon.expMax) {
            // Pokemon has leveled up, check for evolution
            Pokemon evolved = pokemon.levelUp();
            if (evolved != null) {
                // Update the player's team with the evolved Pokemon
            	int index = Arrays.asList(this.getTeam()).indexOf(pokemon);
                this.team[index] = evolved;
                if (index == 0) this.current = evolved;
                evolved.checkMove();
                pokemon = evolved;
            }
        }
    	pokemon.fainted = false;
    	JOptionPane.showMessageDialog(null, pokemon.name + " was elevated to " + pokemon.getLevel());
		
	}
	
	public boolean buy(Item item) {
		if (item.getCost() > money) return false;
		money -= item.getCost();
		bag.add(item);
		return true;
	}

}
