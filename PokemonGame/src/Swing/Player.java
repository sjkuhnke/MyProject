package Swing;

import java.io.Serializable;
import java.util.Random;

public class Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2851052666892205583L;
	public Pokemon[] team;
	public Pokemon[] box;
	public int money;
	public Pokemon current;
	public int pokeballCount;
	public int greatballCount;
	public int ultraballCount;
	
	public Player() {
		team = new Pokemon[6];
		box = new Pokemon[30];
		money = 0;
		current = null;
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
	                System.out.println("Caught " + p.name + ", added to party!");
	                current = team[0];
	                break;
	            }
	        }
	    } else {
	        int index = -1;
	        for (int i = 0; i < box.length; i++) {
	            if (box[i] == null) {
	                index = i;
	                break;
	            }
	        }
	        if (index >= 0) {
	            box[index] = p;
	            System.out.println("Caught " + p.name + ", sent to box!");
	        } else {
	            System.out.println("Cannot catch " + p.name + ", box is full");
	        }
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
		lead.clearVolatile();
		this.current = pokemon;
		this.team[0] = pokemon;
		this.team[index] = lead;
		System.out.println("Go " + current.name + "!\n");
		
	}

}
