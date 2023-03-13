package Swing;

public class Trainer {
	private String name;
	private Pokemon[] team;
	private int money;
	private int currentIndex;
	
	public Trainer(String name, Pokemon[] team, int money) {
		this.name = name;
		this.team = team;
		this.money = money;
		currentIndex = 0;
	}
	
	public Pokemon[] getTeam() {
		return team;
	}
	
	@Override // implementation
	public String toString() {
		return name + " trainer";
	}

	public boolean hasNext() {
		return currentIndex < team.length - 1;
	}
	
	public Pokemon next() {
		return team[++currentIndex];
	}
	
	public Pokemon getCurrent() {
		return team[currentIndex];
	}
	
	public int getMoney() {
		return money;
	}
}
