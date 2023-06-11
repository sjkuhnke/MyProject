package Swing;

import java.util.ArrayList;
import java.util.Iterator;

public class Field {
	
	FieldEffect weather;
	int weatherTurns;
	FieldEffect terrain;
	int terrainTurns;
	public ArrayList<FieldEffect> playerSide;
	public ArrayList<FieldEffect> foeSide;
	
	public Field() {
		weather = null;
		playerSide = new ArrayList<>();
		foeSide = new ArrayList<>();
	}
	
	public enum Effect {
		SUN(5, true, false),
		RAIN(5, true, false),
		SANDSTORM(5, true, false),
		SNOW(5, true, false),
		GRASSY(5, false, true),
		ELECTRIC(5, false, true),
		PSYCHIC(5, false, true),
		SPARKLY(5, false, true), 
		REFLECT(5, false, false),
		LIGHT_SCREEN(5, false, false);
		
		private Effect(int turns, boolean isWeather, boolean isTerrain) {
			this.turns = turns;
			this.isWeather = isWeather;
			this.isTerrain = isTerrain;
		}
		
		public int turns;
		public boolean isWeather;
		public boolean isTerrain;
		
		@Override
		public String toString() {
			String name = super.toString();
		    name = name.toLowerCase().replace('_', ' ');
		    String[] words = name.split(" ");
		    StringBuilder sb = new StringBuilder();
		    for (String word : words) {
		        sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
		    }
		    return sb.toString().trim();
		}
	}
	
	public class FieldEffect {
		int turns;
		Effect effect;
		
		public FieldEffect(Effect effect) {
			this.effect = effect;
			turns = effect.turns;
		}
	}
	
	public void setWeather(FieldEffect weather) {
		if (weather.effect.isWeather) {
			this.weather = weather;
			this.weatherTurns = weather.turns;
		}
	}
	
	public void setTerrain(FieldEffect terrain) {
		if (terrain.effect.isTerrain) {
			this.terrain = terrain;
			this.terrainTurns = terrain.turns;
		}
	}

	public boolean contains(ArrayList<FieldEffect> side, Effect reflect) {
		for (FieldEffect effect : side) {
			if (effect.effect == reflect) return true;
		}
		return false;
	}
	
	public void endOfTurn() {
	    if (weather != null) {
	        weatherTurns--;
	        if (weatherTurns == 0) {
	            System.out.println("The weather returned to normal!");
	            weather = null;
	        }
	    }
	    if (terrain != null) {
	        terrainTurns--;
	        if (terrainTurns == 0) {
	            System.out.println("The terrain returned to normal!");
	            terrain = null;
	        }
	    }
	    
	    Iterator<FieldEffect> iterator = playerSide.iterator();
	    while (iterator.hasNext()) {
	        FieldEffect effect = iterator.next();
	        if (effect.turns > 0) effect.turns--;
	        if (effect.turns == 0) {
	            System.out.println("Your " + effect.effect.toString() + " wore off!");
	            iterator.remove();
	        }
	    }
	    
	    iterator = foeSide.iterator();
	    while (iterator.hasNext()) {
	        FieldEffect effect = iterator.next();
	        if (effect.turns > 0) effect.turns--;
	        if (effect.turns == 0) {
	            System.out.println("Foe's " + effect.effect.toString() + " wore off!");
	            iterator.remove();
	        }
	    }
	}


}
