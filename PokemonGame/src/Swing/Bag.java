package Swing;

import java.io.Serializable;
import java.util.ArrayList;

public class Bag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Item[] bag;
	private int[] count;
	
	public Bag() {
		bag = new Item[200];
		count = new int[200];
		add(new Item(1));
	}
	
	public void add(Item item) {
		int index = item.getID();
		if (bag[index] == null) {
			bag[index] = item;
			count[index] = 1;
		} else {
			count[index]++;
		}
	}
	
	public void remove(Item item) {
		int index = item.getID();
		if (count[--index] == 0) bag[index] = null;
	}
	
	public ArrayList<Entry> getItems() {
		ArrayList<Entry> items = new ArrayList<>();
		for (int i = 0; i < bag.length; i++) {
			if (bag[i] == null) continue;
			items.add(new Entry(bag[i], count[i]));
		}
		return items;
	}
	
	public class Entry {
		Item item;
		int count;
		
		public Entry(Item item, int count) {
			this.item = item;
			this.count = count;
		}

		public Item getItem() {
			return item;
		}
		
		public int getCount() {
			return count;
		}
	}

}
