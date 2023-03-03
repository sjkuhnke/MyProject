package Swing;

public enum PType {
	NORMAL,
	ROCK,
	FIRE,
	WATER,
	GRASS,
	ELECTRIC,
	FIGHTING,
	POISON,
	GROUND,
	FLYING,
	MAGIC,
	BUG,
	GHOST,
	DRAGON,
	STEEL,
	DARK;
	
	@Override // implementation
	public String toString() {
		String name = name().toLowerCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
	}
}