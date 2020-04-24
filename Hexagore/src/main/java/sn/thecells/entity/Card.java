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
	
	public static final String FILE_COMPANIONS = "Companions_140x245";
	public static final String FILE_EVENTS = "Companions_140x245";
	public static final String FILE_OTHERS = "Others_140x245";
	
	static {
		int i = 0;
		cards = new Card[] {
			new Card("Card.1", FILE_COMPANIONS, i++, 1, TYPE_COMPANION, 1, false),
			new Card("Card.2", FILE_COMPANIONS, i++, 1, TYPE_COMPANION, 1, false),
			new Card("Card.3", FILE_COMPANIONS, i++, 2, TYPE_COMPANION, 1, false),
			new Card("Card.4", FILE_COMPANIONS, i++, 2, TYPE_COMPANION, 1, false),
			new Card("Card.5", FILE_COMPANIONS, i++, 2, TYPE_COMPANION, 1, false),
			new Card("Card.6", FILE_COMPANIONS, i++, 2, TYPE_COMPANION, 1, false),
			new Card("Card.7", FILE_COMPANIONS, i++, 2, TYPE_COMPANION, 1, false),
			new Card("Card.8", FILE_COMPANIONS, i++, 3, TYPE_COMPANION, 1, false),
			new Card("Card.9", FILE_COMPANIONS, i++, 2, TYPE_COMPANION, 1, false),
			new Card("Card.10", FILE_COMPANIONS, i++, 3, TYPE_COMPANION, 1, false),
			new Card("Card.11", FILE_COMPANIONS, i++, 3, TYPE_COMPANION, 1, false),
			new Card("Card.12", FILE_COMPANIONS, i++, 3, TYPE_COMPANION, 1, false),
			new Card("Card.13", FILE_COMPANIONS, i++, 3, TYPE_COMPANION, 1, false),
			new Card("Card.14", FILE_COMPANIONS, i++, 1, TYPE_COMPANION, 1, false),
			new Card("Card.15", FILE_COMPANIONS, i++, 3, TYPE_COMPANION, 1, false),
			new Card("Card.16", FILE_COMPANIONS, i++, 1, TYPE_COMPANION, 1, false),
			new Card("Card.17", FILE_COMPANIONS, i++, 2, TYPE_COMPANION, 1, false),
			new Card("Card.18", FILE_COMPANIONS, i++, 3, TYPE_COMPANION, 1, false),
			new Card("Card.19", FILE_COMPANIONS, i++, 2, TYPE_COMPANION, 1, false),
			new Card("Card.20", FILE_COMPANIONS, i++, 3, TYPE_COMPANION, 1, false),
			new Card("Card.21", FILE_COMPANIONS, i++, 1, TYPE_COMPANION, 1, false),
			
			new Card("Card.31", FILE_EVENTS, i++, -2, TYPE_EVENT, 1, false),
			new Card("Card.32", FILE_EVENTS, i++, -2, TYPE_EVENT, 1, false),
			new Card("Card.33", FILE_EVENTS, i++, 1, TYPE_EVENT, 1, false),
			new Card("Card.34", FILE_EVENTS, i++, -1, TYPE_EVENT, 1, false),
			new Card("Card.35", FILE_EVENTS, i++, -1, TYPE_EVENT, 1, false),
			new Card("Card.36", FILE_EVENTS, i++, -2, TYPE_EVENT, 1, false),
			new Card("Card.37", FILE_EVENTS, i++, -1, TYPE_EVENT, 1, false),
			new Card("Card.38", FILE_EVENTS, i++, -1, TYPE_EVENT, 1, false),
			new Card("Card.39", FILE_EVENTS, i++, -2, TYPE_EVENT, 1, false),
			new Card("Card.40", FILE_EVENTS, i++, -2, TYPE_EVENT, 1, false),
			new Card("Card.41", FILE_EVENTS, i++, 2, TYPE_EVENT, 1, false),
			new Card("Card.42", FILE_EVENTS, i++, -1, TYPE_EVENT, 1, false),
			new Card("Card.43", FILE_EVENTS, i++, -1, TYPE_EVENT, 1, false),

			new Card("Card.51", FILE_OTHERS, i++, 2, TYPE_MONEY, 1, false),
			new Card("Card.52", FILE_OTHERS, i++, 2, TYPE_MONEY, 1, false),
			new Card("Card.53", FILE_OTHERS, i++, 2, TYPE_MONEY, 1, false),
			new Card("Card.54", FILE_OTHERS, i++, 2, TYPE_MONSTER | TYPE_TRASHER, 1, false),
			new Card("Card.55", FILE_OTHERS, i++, 2, TYPE_MONSTER, 1, false),
			new Card("Card.56", FILE_OTHERS, i++, 2, TYPE_MONSTER, 1, false),
			new Card("Card.57", FILE_OTHERS, i++, 2, TYPE_MONSTER, 1, false),
			new Card("Card.58", FILE_OTHERS, i++, 2, TYPE_MONSTER | TYPE_TRASHER, 1, false),
			new Card("Card.59", FILE_OTHERS, i++, 2, TYPE_MONEY, 1, true),
			new Card("Card.60", FILE_OTHERS, i++, 2, TYPE_MONSTER, 1, true),
			new Card("Card.61", FILE_OTHERS, i++, 2, TYPE_COMPANION, 1, true)
		};
	}
	
	public final int strength;
	public final int type;
	public final int quantity;
	public final boolean startingCards;
	
	public Card(String label, String propFile, int propIndex, int strength, int type, int quantity, boolean startingCards) {
		super(label, propFile, propIndex);
		this.strength = strength;
		this.type = type;
		this.quantity = quantity;
		this.startingCards = startingCards;
	}

	public static Card get(String label){
		return Arrays.stream(cards).filter(c -> label.equalsIgnoreCase(c.label)).findAny().get();
	}
	public static List<Card> getPlayerDeck(){
		List<Card> result = new ArrayList<Card>(); // Must have the correct ids (for image indexes...)
		for (int i = 0; i < 5; i++)
			result.add(get("Card.59"));
		result.add(get("Card.60"));
		result.add(get("Card.61"));
		return result;
	}
	public static List<Card> getAllCards(){
		List<Card> result = new ArrayList<Card>();
		Arrays.stream(cards).forEach(c -> {
			for (int i = 0; i < c.quantity; i++)
				result.add(c);
		});
		return result;
	}


	@Override
	public String getPropFile() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getPropIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

}
