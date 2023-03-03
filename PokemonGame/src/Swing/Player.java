package Swing;

import java.io.Serializable;
import java.util.Random;

public class Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2851052666892205583L;
	public int id;
	public Pokemon[] team;
	public Pokemon[] box;
	public int money;
	public Pokemon current;
	public int numPokemonTeam;
	public int numPokemonBox;
	
	public Player() {
		this(genID());
	}

	public Player(int id) {
		team = new Pokemon[6];
		box = new Pokemon[100];
		money = 0;
		current = null;
		numPokemonTeam = 0;
		numPokemonBox = 0;
	}

	private static int genID() {
		Random random = new Random();
	    return random.nextInt();
	}
	
	public Pokemon getCurrent() {
		return this.current;
	}
	
	public void catchPokemon(Pokemon p) {
		if (numPokemonTeam < 6) {
            team[numPokemonTeam] = p;
            numPokemonTeam++;
            if (numPokemonTeam == 1) current = p;
        } else if (numPokemonBox < 100) {
            box[numPokemonBox] = p;
            numPokemonBox++;
        } else {
            System.out.println("Cannot catch " + p.getName() + ", box is full");
        }
	}

}
