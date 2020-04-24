package sn.thecells.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Crest extends Entity {

	
	public static final Crest[] crests;
	
	private static final String FILE_PIECES = "Crest_200x245";
	
	static {
		int i = 0;
		crests = new Crest[] {
			new Crest("Crest.1",FILE_PIECES,i++),
			new Crest("Crest.2",FILE_PIECES,i++),
			new Crest("Crest.3",FILE_PIECES,i++),
			new Crest("Crest.4",FILE_PIECES,i++),
			new Crest("Crest.5",FILE_PIECES,i++),
			new Crest("Crest.6",FILE_PIECES,i++),
			new Crest("Crest.7",FILE_PIECES,i++),
			new Crest("Crest.8",FILE_PIECES,i++),
		};
	}
	
	public Crest(String label, String propFile, int propIndex) {
		super(label, propFile, propIndex);
	}
	public static List<Crest> getAll(){
		return new ArrayList<Crest>(Arrays.asList(crests));
	}
}
