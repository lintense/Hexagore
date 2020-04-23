package sn.thecells.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Opponent extends Entity {

	public static final Opponent[] opponents;
	
	public static final int TYPE_NONE = 0;
	public static final int TYPE_PLAYER = 1;
	public static final int TYPE_COMPUTER = 2;
	
	static {
		opponents = new Opponent[] {
			new Opponent(0,TYPE_NONE),
			new Opponent(1,TYPE_PLAYER),
			new Opponent(2,TYPE_COMPUTER)
		};
	}
	
	public final int type;
	public Opponent(int id, int type) {
		super(id);
		this.type = type;
	}
	public static List<String> getSomeNames() {
		return new ArrayList(Arrays.asList(new String[]{"Cédric", "Megalon", "Arthus", "Shazam", "Rickon", "Gavroch", "Orvizch", "Tarzan", "Adlae"}));
	}
	public static List<Opponent> getAll(){
		return Arrays.asList(opponents);
	}
	@Override
	public int getPieceIndex() {
		return id + 7;
	}

}
