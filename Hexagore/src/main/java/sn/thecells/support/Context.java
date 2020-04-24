package sn.thecells.support;

import java.util.List;

public class Context {

	private List<Player> players;
	private int firstPlayer;
	private int currentPlayer;
	private Deque market;
	private Deque draw;
	private Deque trash;
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public int getFirstPlayer() {
		return firstPlayer;
	}
	public void setFirstPlayer(int firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	public Deque getMarket() {
		return market;
	}
	public void setMarket(Deque market) {
		this.market = market;
	}
	public Deque getDraw() {
		return draw;
	}
	public void setDraw(Deque draw) {
		this.draw = draw;
	}
	public Deque getTrash() {
		return trash;
	}
	public void setTrash(Deque trash) {
		this.trash = trash;
	}
	
	
	
}
