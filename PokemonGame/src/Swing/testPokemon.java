package Swing;


public class testPokemon {

	public static void main(String[] args) {
		
		//Test evolution
		Pokemon evo = new Pokemon(1, 5);
		System.out.println(evo + "\n");
		
		Pokemon foe = new Pokemon(2, 11);
		System.out.println(foe + "\n");
		
		
		evo.heal();
		System.out.println(evo + "\n");
		
		Pokemon foe1 = new Pokemon(1, 9);
		System.out.println(foe1 + "\n");
		
	}
	

}
