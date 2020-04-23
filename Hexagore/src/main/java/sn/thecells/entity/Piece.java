package sn.thecells.entity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Piece extends Entity {

	public static final Piece[] pieces;
	
	public static final int TYPE_UNKNWON = 0;
	public static final int TYPE_PLAYER = 1;
	public static final int TYPE_MONSTER = 2;
	public static final int TYPE_GOLD_PIECES = 4;
	public static final int TYPE_SKULL = 8;
	
	
	static {
		int i = 0;
		pieces = new Piece[] {
			new Piece(i++,1,TYPE_PLAYER,1,0),
			new Piece(i++,1,TYPE_PLAYER,1,0),
			new Piece(i++,1,TYPE_PLAYER,1,0),
			new Piece(i++,1,TYPE_PLAYER,1,0),
			new Piece(i++,1,TYPE_MONSTER,10,1),
			new Piece(i++,2,TYPE_MONSTER,10,2),
			new Piece(i++,3,TYPE_MONSTER,10,3)
			//,new Piece(i++,0,TYPE_GOLD_PIECES,20,10) // TODO - Fix the deck first
			//,new Piece(i++,1,TYPE_SKULL,30,1) // TODO - Fix the deck first
		};
	}
	
	public final int strengh;
	public final int type;
	public String name;
	public Opponent opponent;
	public final int quantity;
	public final int points;
	
	public Piece(int id, int strengh, int type, int quantity, int points) {
		super(id);
		this.strengh = strengh;
		this.type = type;
		this.quantity = quantity;
		this.points = points;
	}
	public static List getAllForType(int type) {
		return Arrays.stream(pieces).filter(p -> p.type == type).collect(Collectors.toList());
	}
	@Override
	public int getPieceIndex() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOpponent(Opponent opponent) {
		this.opponent = opponent;
	}
}
