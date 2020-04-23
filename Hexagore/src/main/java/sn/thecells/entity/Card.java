package sn.thecells.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card extends Entity {

	public static final Card[] cards;
	
	public static final int TYPE_UNKNOWN = 0;
	public static final int TYPE_MONSTER = 1;
	public static final int TYPE_COMPANION = 2;
	public static final int TYPE_MONEY = 4;
	public static final int TYPE_EVENT = 8;
	public static final int TYPE_TRASHER = 16;
	
	static {
		int i = 0;
		cards = new Card[] {
			new Card(i++, 1, TYPE_MONSTER, 4, "Gobblin"),
			new Card(i++, 2, TYPE_MONSTER, 4, "Ogre"),
			new Card(i++, 3, TYPE_MONSTER, 4, "Troll"),
			new Card(i++, 1, TYPE_MONEY, 6, "Bronze"),
			new Card(i++, 2, TYPE_MONEY, 4, "Silver"),
			new Card(i++, 3, TYPE_MONEY, 2, "Gold"),
			new Card(i++, 6, TYPE_COMPANION, 1, "Mercenary"),
			new Card(i++, 1, TYPE_MONSTER | TYPE_TRASHER, 4, "Gobblin Trasher"),
			new Card(i++, 1, TYPE_COMPANION, 1, "Elve")
		};
	}
	
	public final int strength;
	public final int type;
	public final int quantity;
	public final String name;
	
	public Card(int id, int strength, int type, int quantity, String name) {
		super(id);
		this.strength = strength;
		this.type = type;
		this.quantity = quantity;
		this.name = name;
	}


	@Override
	public int getPieceIndex() {
		// TODO Auto-generated method stub
		return 10 + id;
	}
	public static List<Card> getPlayerDeck(){
		List<Card> result = new ArrayList(); // Must have the correct ids (for image indexes...)
		Arrays.stream(cards).filter(c -> c.name.equals("Gobblin")).findAny().ifPresent(c -> result.add(c));
		Arrays.stream(cards).filter(c -> c.name.equals("Bronze")).findAny().ifPresent(c -> { for (int i = 0; i < 5; i++) result.add(c);});
		Arrays.stream(cards).filter(c -> c.name.equals("Elve")).findAny().ifPresent(c -> result.add(c));
		return result;
	}
	public static List<Card> getAllCards(){
		List<Card> result = new ArrayList();
		Arrays.stream(cards).forEach(c -> {
			for (int i = 0; i < c.quantity; i++)
				result.add(c);
		});
		return result;
	}

}
