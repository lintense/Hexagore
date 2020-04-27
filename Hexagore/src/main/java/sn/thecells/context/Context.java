package sn.thecells.context;

import java.util.List;
import java.util.Map;

import sn.thecells.entity.Entity;
import sn.thecells.support.Deque;

/**
 * @author snadeau
 *
 * The context is a data structure without behaviors that is responsible to hold the all the game states.
 * It is passed to Phases and then to Operations that will carry the changes.
 * 
 * Eventually, context could be copied and compare.
 * It should also have a status (unchanged, changed) for all its field.
 * 
 */
public class Context {

	private List<Location> locations;
	
	// Phase buffers
	// It is very important that the order of the operations have no effect on the outcome
	// I.e. all pluses and lesses can be done in any order.
	// Same for collectAdds and collectSubs
	// A map.put CANNOT be override, it must be final!
	
	private int count;
	private Entity entity; // Curerent entity
	private List<Entity> collectAdd = null;
	private List<Entity> collectSub = null;
	private Map<Entity, Entity> map = null;
	
	
	// Combat
	private Player attacker;
	private Player defender;
	private Location arena;
	private Player winner;
	private Player loser;
	
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
	public List<Location> getLocations() {
		return locations;
	}
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	public Player getAttacker() {
		return attacker;
	}
	public void setAttacker(Player attacker) {
		this.attacker = attacker;
	}
	public Player getDefender() {
		return defender;
	}
	public void setDefender(Player defender) {
		this.defender = defender;
	}
	public Location getArena() {
		return arena;
	}
	public void setArena(Location arena) {
		this.arena = arena;
	}
	public Player getWinner() {
		return winner;
	}
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	public Player getLoser() {
		return loser;
	}
	public void setLoser(Player loser) {
		this.loser = loser;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void addCount(int count) {
		this.count += count;
	}
	public void subCount(int count) {
		this.count -= count;
	}
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public List<Entity> getCollectAdd() {
		return collectAdd;
	}
	public void setCollectAdd(List<Entity> collectAdd) {
		this.collectAdd = collectAdd;
	}
	public List<Entity> getCollectSub() {
		return collectSub;
	}
	public void setCollectSub(List<Entity> collectSub) {
		this.collectSub = collectSub;
	}
	public Map<Entity, Entity> getMap() {
		return map;
	}
	public void setMap(Map<Entity, Entity> map) {
		this.map = map;
	}
	
	
	
}
