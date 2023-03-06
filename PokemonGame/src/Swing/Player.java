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
		box = new Pokemon[30];
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
		if (p.isFainted()) return;
		if (numPokemonTeam < 6) {
            team[numPokemonTeam] = p;
            numPokemonTeam++;
            System.out.println("Caught " + p.name + ", added to party!");
            if (numPokemonTeam == 1) current = p;
        } else if (numPokemonBox < 30) {
            box[numPokemonBox] = p;
            numPokemonBox++;
            System.out.println("Caught " + p.name + ", sent to box!");
        } else {
            System.out.println("Cannot catch " + p.name + ", box is full");
        }
	}
	
	@Override // implementation
	public String toString() {
		return "Party: " + this.PokemonArrayToString(team) + ", Box: " + this.PokemonArrayToString(box);
	}
	
	private String PokemonArrayToString(Pokemon[] arr) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");
	    for (int i = 0; i < arr.length ; i++) {
	        if (arr[i] != null) {
	        	sb.append(arr[i].getName());
	        } else { break; }
	        if (i != arr.length - 1) {
	            sb.append(", ");
	        }
	    }
	    sb.append("]");
	    return sb.toString();
	}

	public void swap(Pokemon pokemon, int index) {
		System.out.println(current.name + ", come back!");
		Pokemon lead = current;
		this.current = pokemon;
		this.team[0] = pokemon;
		this.team[index] = lead;
		System.out.println("Go " + current.name + "!\n");
		
	}

}
