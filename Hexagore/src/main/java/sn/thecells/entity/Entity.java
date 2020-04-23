package sn.thecells.entity;

public abstract class Entity {

	public final int id;

	public Entity(int id) {
		super();
		this.id = id;
	}
	
	abstract public int getPieceIndex();
}
