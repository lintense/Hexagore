package sn.thecells.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import sn.thecells.support.Point2D;

public class Hexagon extends Entity {

	public static final int TYPE_UNKNOWN = 0;
	public static final int TYPE_GOLD_MINE = 1;
	public static final int TYPE_MOUNTAIN = 2;
	public static final int TYPE_FOREST = 4;
	public static final int TYPE_RIVER = 8;
	public static final int TYPE_FIELD = 16;
	public static final int TYPE_VILLAGE = 32;
	public static final Hexagon[] hexes;
	
	private static final String FILE_HEX = "TO_BE_CREATED";
	private static final int NEIGHBORS_SQUARED_DIST = 1000;
	
	static {
		int i = 0;
		hexes = new Hexagon[] {
			new Hexagon("Hex.0",FILE_HEX,i++,531,282,TYPE_GOLD_MINE),
			
			new Hexagon("Hex.1",FILE_HEX,i++,462,317,TYPE_MOUNTAIN),
			new Hexagon("Hex.2",FILE_HEX,i,530,354,TYPE_MOUNTAIN),
			new Hexagon("Hex.3",FILE_HEX,i,595,317,TYPE_MOUNTAIN),
			
			new Hexagon("Hex.4",FILE_HEX,i++,394,354,TYPE_FOREST),
			new Hexagon("Hex.5",FILE_HEX,i,461,391,TYPE_FOREST),
			new Hexagon("Hex.6",FILE_HEX,i,529,430,TYPE_FOREST),
			new Hexagon("Hex.7",FILE_HEX,i,594,391,TYPE_FOREST),
			new Hexagon("Hex.8",FILE_HEX,i,662,354,TYPE_FOREST),
			
			new Hexagon("Hex.9",FILE_HEX,i++,326,391,TYPE_RIVER),
			new Hexagon("Hex.10",FILE_HEX,i,394,430,TYPE_RIVER),
			new Hexagon("Hex.11",FILE_HEX,i,460,465,TYPE_RIVER),
			new Hexagon("Hex.12",FILE_HEX,i,528,503,TYPE_RIVER),
			new Hexagon("Hex.13",FILE_HEX,i,593,465,TYPE_RIVER),
			new Hexagon("Hex.14",FILE_HEX,i,662,430,TYPE_RIVER),
			new Hexagon("Hex.15",FILE_HEX,i,728,391,TYPE_RIVER),
			
			new Hexagon("Hex.16",FILE_HEX,i++,259,430,TYPE_FIELD),
			new Hexagon("Hex.17",FILE_HEX,i,326,465,TYPE_FIELD),
			new Hexagon("Hex.18",FILE_HEX,i,394,503,TYPE_FIELD),
			new Hexagon("Hex.19",FILE_HEX,i,459,540,TYPE_FIELD),
			new Hexagon("Hex.20",FILE_HEX,i,527,577,TYPE_FIELD),
			new Hexagon("Hex.21",FILE_HEX,i,592,540,TYPE_FIELD),
			new Hexagon("Hex.22",FILE_HEX,i,662,503,TYPE_FIELD),
			new Hexagon("Hex.23",FILE_HEX,i,728,465,TYPE_FIELD),
			new Hexagon("Hex.24",FILE_HEX,i,794,430,TYPE_FIELD),
			
			new Hexagon("Hex.25",FILE_HEX,i++,193,465,TYPE_FIELD),
			new Hexagon("Hex.26",FILE_HEX,i,259,503,TYPE_FIELD),
			new Hexagon("Hex.27",FILE_HEX,i,326,540,TYPE_FIELD),
			new Hexagon("Hex.28",FILE_HEX,i,394,577,TYPE_FIELD),
			new Hexagon("Hex.29",FILE_HEX,i,458,614,TYPE_FIELD),
			new Hexagon("Hex.30",FILE_HEX,i,526,651,TYPE_FIELD),
			new Hexagon("Hex.31",FILE_HEX,i,591,614,TYPE_FIELD),
			new Hexagon("Hex.32",FILE_HEX,i,662,577,TYPE_FIELD),
			new Hexagon("Hex.33",FILE_HEX,i,728,540,TYPE_FIELD),
			new Hexagon("Hex.34",FILE_HEX,i,794,503,TYPE_FIELD),
			new Hexagon("Hex.35",FILE_HEX,i,861,470,TYPE_FIELD),
			
			new Hexagon("Hex.36",FILE_HEX,i++,259,577,TYPE_FIELD),
			new Hexagon("Hex.37",FILE_HEX,i,394,651,TYPE_FIELD),
			new Hexagon("Hex.38",FILE_HEX,i,525,724,TYPE_FIELD),
			new Hexagon("Hex.39",FILE_HEX,i,662,651,TYPE_FIELD),
			new Hexagon("Hex.40",FILE_HEX,i,794,577,TYPE_FIELD),
			
			new Hexagon("Hex.41",FILE_HEX,i++,193,540,TYPE_VILLAGE),
			new Hexagon("Hex.42",FILE_HEX,i,326,614,TYPE_VILLAGE),
			new Hexagon("Hex.43",FILE_HEX,i,457,686,TYPE_VILLAGE),
			new Hexagon("Hex.44",FILE_HEX,i,590,686,TYPE_VILLAGE),
			new Hexagon("Hex.45",FILE_HEX,i,728,614,TYPE_VILLAGE),
			new Hexagon("Hex.46",FILE_HEX,i,861,540,TYPE_VILLAGE)
		};
		for (int ii = 0; ii < hexes.length; ii++)
			hexes[ii].setNeighbors(getNeighbors(hexes[ii]));
	}
	public final Point2D center;
	public final int type;
	public List<Hexagon> neighbors;
	
	public Hexagon(String label, String propFile, int propIndex, int x, int y, int type) {
		super(label, propFile, propIndex);
		this.center = new Point2D(x, y);
		this.type = type;
	}
	private float getDistSquared(Point2D pp) {
		Point2D r = center.minus(pp).squared();
		return r.x + r.y;
	}
	private void setNeighbors(List<Hexagon> neighbors) {
		this.neighbors = neighbors;
	}
	public boolean containsPoint(Point2D p) {
		return getDistSquared(p) < NEIGHBORS_SQUARED_DIST;
	}
	
	private static List<Hexagon> getNeighbors(Hexagon prop) {
		int dist = 75*75+40*40;
		List<Hexagon> result = new ArrayList<Hexagon>();
		for (int i = 0; i < hexes.length; i++)
			if (!hexes[i].label.equals(prop.label) && hexes[i].getDistSquared(prop.center) <= dist)
				result.add(hexes[i]);
		return result;
	}
	public static List<Hexagon> getAllHexes(){
		return new ArrayList<Hexagon>(Arrays.asList(hexes));
	}
	public static Hexagon getHex(Point2D p) {
		for (int i = 0; i < hexes.length; i++)
			if (hexes[i].containsPoint(p))
				return hexes[i];
		return null;
	}
	public static List<Hexagon> getHexesForType(int hexType){
		return Arrays.stream(hexes).filter(h -> h.type == hexType).collect(Collectors.toList());
	}
}
