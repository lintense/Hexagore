package sn.thecells.entity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Piece extends Entity {

	public static final Piece[] pieces;
	
	public static final int TYPE_UNKNWON = 0;
	public static final int TYPE_PLAYER = 1;
	public static final int TYPE_MONSTER = 2;
	public static final int TYPE_TREASURE = 4;
	public static final int TYPE_SKULL = 8;
	
	private static final String FILE_PIECES = "Pieces_210x226";
	
	static {
		int i = 0;
		pieces = new Piece[] {
			new Piece("Player.1",FILE_PIECES,i++,1,TYPE_PLAYER,1,0),
			new Piece("Player.2",FILE_PIECES,i++,1,TYPE_PLAYER,1,0),
			new Piece("Player.3",FILE_PIECES,i++,1,TYPE_PLAYER,1,0),
			new Piece("Player.4",FILE_PIECES,i++,1,TYPE_PLAYER,1,0),
			new Piece("Player.5",FILE_PIECES,i++,1,TYPE_PLAYER,1,0),
			new Piece("Player.6",FILE_PIECES,i++,1,TYPE_PLAYER,1,0),
			new Piece("Monster.1",FILE_PIECES,i++,1,TYPE_MONSTER,10,1),
			new Piece("Monster.2",FILE_PIECES,i++,2,TYPE_MONSTER,10,2),
			new Piece("Monster.3",FILE_PIECES,i++,3,TYPE_MONSTER,10,3),
			new Piece("Treasure",FILE_PIECES,i++,0,TYPE_TREASURE,20,10),
			new Piece("Skull",FILE_PIECES,i++,1,TYPE_SKULL,30,1)
		};
	}
	
	public final int strength;
	public final int type;
	public final int quantity;
	public final int points;
	
	public Piece(String label, String propFile, int propIndex, int strength, int type, int quantity, int points) {
		super(label, propFile, propIndex);
		this.strength = strength;
		this.type = type;
		this.quantity = quantity;
		this.points = points;
	}
	public static List<Piece> getAllForType(int type) {
		return Arrays.stream(pieces).filter(p -> p.type == type).collect(Collectors.toList());
	}
	public static Piece get(String label) {
		return Arrays.stream(pieces).filter(c -> label.equalsIgnoreCase(c.label)).findAny().get();
	}
	public int getStrength() {
		return strength;
	}
	
	/// Dynamic properties ///
	
//	public String name;
//	public Opponent opponent;
//	
//	public void setName(String name) {
//		this.name = name;
//	}
//	public void setOpponent(Opponent opponent) {
//		this.opponent = opponent;
//	}

}
