package sn.thecells.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sn.thecells.support.Label;

public class Opponent extends Entity {

	public static final Opponent[] opponents;
	
	public static final int TYPE_NONE = 0;
	public static final int TYPE_PLAYER = 1;
	public static final int TYPE_COMPUTER = 2;
	
	private static final String FILE_PIECES = "Pieces_210x226";
	
	static {
		int i = 7;
		opponents = new Opponent[] {
			new Opponent("Opponent.0",FILE_PIECES,i++,TYPE_NONE),
			new Opponent("Opponent.1",FILE_PIECES,i++,TYPE_PLAYER),
			new Opponent("Opponent.2",FILE_PIECES,i++,TYPE_COMPUTER)
		};
	}
	
	public final int type;
	public Opponent(String label, String propFile, int propIndex, int type) {
		super(label, propFile, propIndex);
		this.type = type;
	}
	public static List<String> getSomeNames() {
		return new ArrayList<String>(Arrays.asList(Label.get("Opponent.names").split(",")));
	}
	public static List<Opponent> getAll(){
		return Arrays.asList(opponents);
	}
}
