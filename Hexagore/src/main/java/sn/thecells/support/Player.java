package sn.thecells.support;

import sn.thecells.entity.Crest;
import sn.thecells.entity.Hexagon;
import sn.thecells.entity.Opponent;
import sn.thecells.entity.Piece;

public class Player {

	private Opponent opponent;
	private Piece piece;
	private String name;
	private Deque draw;
	private Deque hand;
	private Deque discard;
	private Hexagon village;
	private Crest crest;
	
	public Player(Piece piece, Opponent opponent) {
		super();
		this.opponent = opponent;
		this.piece = piece;
	}

	public Opponent getOpponent() {
		return opponent;
	}
	public void setOpponent(Opponent opponent) {
		this.opponent = opponent;
	}
	public Piece getPiece() {
		return piece;
	}
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Deque getDraw() {
		return draw;
	}
	public void setDraw(Deque draw) {
		this.draw = draw;
	}
	public Deque getHand() {
		return hand;
	}
	public void setHand(Deque hand) {
		this.hand = hand;
	}
	public Deque getDiscard() {
		return discard;
	}
	public void setDiscard(Deque discard) {
		this.discard = discard;
	}
	public Hexagon getVillage() {
		return village;
	}
	public void setVillage(Hexagon village) {
		this.village = village;
	}
	public Crest getCrest() {
		return crest;
	}
	public void setCrest(Crest crest) {
		this.crest = crest;
	}
	
	
}
