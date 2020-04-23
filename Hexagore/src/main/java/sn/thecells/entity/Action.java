package sn.thecells.entity;

public class Action extends Entity {

	public static final Action[] actions;
	
	public static final int TYPE_PLAY_NO_ACTION = 0;
	public static final int TYPE_BUY_A_CARD_FROM_THE_MARKET = 1;
	public static final int TYPE_USE_A_CARD_TO_MOVE = 2;
	public static final int TYPE_USE_A_CARD_TO_TRASH_A_CARD = 4;
	public static final int TYPE_USE_A_CARD_TO_PICK_A_TREASURE = 8;	
	public static final int TYPE_USE_A_CARD_TO_DROP_A_TREASURE = 16;
	public static final int TYPE_USE_A_CARD_TO_ATTACK_ANOTHER_PLAYER = 32;
	public static final int TYPE_PLAY_A_COMPANION_ACTION = 64;
	public static final int TYPE_PLAY_THE_TRASH_COMPANION_ACTION = 128;
	public static final int TYPE_PLAY_A_SPECIAL_IN_HAND_EVENT_CARD = 256;
	public static final int TYPE_PAY_A_MONSTER_TO_BUY_PASSAGE = 512;
	public static final int TYPE_USE_A_CARD_TO_ATTACK_A_MONSTER = 1024; // not moving!

	
	static {
		int i = 0;
		actions = new Action[] {
			new Action(i++, TYPE_PLAY_NO_ACTION, "I pass!", Card.TYPE_UNKNOWN),
			new Action(i++, TYPE_BUY_A_CARD_FROM_THE_MARKET, "I buy a card from the market!", Card.TYPE_MONEY),
			new Action(i++, TYPE_USE_A_CARD_TO_MOVE, "I move!", Card.TYPE_COMPANION | Card.TYPE_MONEY | Card.TYPE_MONSTER),
			new Action(i++, TYPE_USE_A_CARD_TO_TRASH_A_CARD, "I trash a card!", Card.TYPE_TRASHER),
			new Action(i++, TYPE_USE_A_CARD_TO_PICK_A_TREASURE, "I pick a treasure!", Card.TYPE_COMPANION | Card.TYPE_MONEY | Card.TYPE_MONSTER),
			new Action(i++, TYPE_USE_A_CARD_TO_DROP_A_TREASURE, "I drop a treasure!", Card.TYPE_COMPANION | Card.TYPE_MONEY | Card.TYPE_MONSTER),
			new Action(i++, TYPE_USE_A_CARD_TO_ATTACK_ANOTHER_PLAYER, "I attack a player!", Card.TYPE_COMPANION | Card.TYPE_MONEY | Card.TYPE_MONSTER),
			new Action(i++, TYPE_PLAY_A_COMPANION_ACTION, "I play a companion!", Card.TYPE_COMPANION),
			new Action(i++, TYPE_PLAY_THE_TRASH_COMPANION_ACTION, "I play a companion heroic action!", Card.TYPE_COMPANION),
			new Action(i++, TYPE_PLAY_A_SPECIAL_IN_HAND_EVENT_CARD, "I play a special event card!", Card.TYPE_EVENT),
			new Action(i++, TYPE_PAY_A_MONSTER_TO_BUY_PASSAGE, "I buy peace to the monster!", Card.TYPE_MONEY),
			new Action(i++, TYPE_USE_A_CARD_TO_ATTACK_A_MONSTER, "I attack a monster!", Card.TYPE_COMPANION | Card.TYPE_MONEY | Card.TYPE_MONSTER)
		};
	}
	
	public final int type;
	public final String name;
	public final int allowedCardTypes;
	
	public Action(int id, int type, String name, int allowedCardTypes) {
		super(id);
		this.type = type;
		this.name = name;
		this.allowedCardTypes = allowedCardTypes;
	}

	@Override
	public int getPieceIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

}
