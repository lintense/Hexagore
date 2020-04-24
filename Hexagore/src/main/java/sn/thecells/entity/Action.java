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
	
	private static final String FILE_ACTION = "TO_BE_CREATED!";

	
	static {
		int i = 0;
		actions = new Action[] {
			new Action("Action.1", FILE_ACTION, i++, TYPE_PLAY_NO_ACTION, Card.TYPE_UNKNOWN),
			new Action("Action.2", FILE_ACTION, i++, TYPE_BUY_A_CARD_FROM_THE_MARKET, Card.TYPE_MONEY),
			new Action("Action.3", FILE_ACTION, i++, TYPE_USE_A_CARD_TO_MOVE,  Card.TYPE_COMPANION | Card.TYPE_MONEY | Card.TYPE_MONSTER),
			new Action("Action.4", FILE_ACTION, i++, TYPE_USE_A_CARD_TO_TRASH_A_CARD, Card.TYPE_TRASHER),
			new Action("Action.5", FILE_ACTION, i++, TYPE_USE_A_CARD_TO_PICK_A_TREASURE, Card.TYPE_COMPANION | Card.TYPE_MONEY | Card.TYPE_MONSTER),
			new Action("Action.6", FILE_ACTION, i++, TYPE_USE_A_CARD_TO_DROP_A_TREASURE, Card.TYPE_COMPANION | Card.TYPE_MONEY | Card.TYPE_MONSTER),
			new Action("Action.7", FILE_ACTION, i++, TYPE_USE_A_CARD_TO_ATTACK_ANOTHER_PLAYER, Card.TYPE_COMPANION | Card.TYPE_MONEY | Card.TYPE_MONSTER),
			new Action("Action.8", FILE_ACTION, i++, TYPE_PLAY_A_COMPANION_ACTION, Card.TYPE_COMPANION),
			new Action("Action.9", FILE_ACTION, i++, TYPE_PLAY_THE_TRASH_COMPANION_ACTION, Card.TYPE_COMPANION),
			new Action("Action.10", FILE_ACTION, i++, TYPE_PLAY_A_SPECIAL_IN_HAND_EVENT_CARD, Card.TYPE_EVENT),
			new Action("Action.11", FILE_ACTION, i++, TYPE_PAY_A_MONSTER_TO_BUY_PASSAGE, Card.TYPE_MONEY),
			new Action("Action.12", FILE_ACTION, i++, TYPE_USE_A_CARD_TO_ATTACK_A_MONSTER, Card.TYPE_COMPANION | Card.TYPE_MONEY | Card.TYPE_MONSTER)
		};
	}
	
	public final int type;
	public final int allowedCardTypes;
	
	public Action(String label, String propFile, int propIndex, int type, int allowedCardTypes) {
		super(label, propFile, propIndex);
		this.type = type;
		this.allowedCardTypes = allowedCardTypes;
	}

}
