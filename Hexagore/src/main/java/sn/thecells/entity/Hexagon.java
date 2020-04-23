package sn.thecells.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	static {
		hexes = new Hexagon[] {
			new Hexagon(0,531,282,TYPE_GOLD_MINE),
			
			new Hexagon(1,462,317,TYPE_MOUNTAIN),
			new Hexagon(2,530,354,TYPE_MOUNTAIN),
			new Hexagon(3,595,317,TYPE_MOUNTAIN),
			
			new Hexagon(4,394,354,TYPE_FOREST),
			new Hexagon(5,461,391,TYPE_FOREST),
			new Hexagon(6,529,430,TYPE_FOREST),
			new Hexagon(7,594,391,TYPE_FOREST),
			new Hexagon(8,662,354,TYPE_FOREST),
			
			new Hexagon(9,326,391,TYPE_RIVER),
			new Hexagon(10,394,430,TYPE_RIVER),
			new Hexagon(11,460,465,TYPE_RIVER),
			new Hexagon(12,528,503,TYPE_RIVER),
			new Hexagon(13,593,465,TYPE_RIVER),
			new Hexagon(14,662,430,TYPE_RIVER),
			new Hexagon(15,728,391,TYPE_RIVER),
			
			new Hexagon(16,259,430,TYPE_FIELD),
			new Hexagon(17,326,465,TYPE_FIELD),
			new Hexagon(18,394,503,TYPE_FIELD),
			new Hexagon(19,459,540,TYPE_FIELD),
			new Hexagon(20,527,577,TYPE_FIELD),
			new Hexagon(21,592,540,TYPE_FIELD),
			new Hexagon(22,662,503,TYPE_FIELD),
			new Hexagon(23,728,465,TYPE_FIELD),
			new Hexagon(24,794,430,TYPE_FIELD),
			
			new Hexagon(25,193,465,TYPE_FIELD),
			new Hexagon(26,259,503,TYPE_FIELD),
			new Hexagon(27,326,540,TYPE_FIELD),
			new Hexagon(28,394,577,TYPE_FIELD),
			new Hexagon(29,458,614,TYPE_FIELD),
			new Hexagon(30,526,651,TYPE_FIELD),
			new Hexagon(31,591,614,TYPE_FIELD),
			new Hexagon(32,662,577,TYPE_FIELD),
			new Hexagon(33,728,540,TYPE_FIELD),
			new Hexagon(34,794,503,TYPE_FIELD),
			new Hexagon(35,861,470,TYPE_FIELD),
			
			new Hexagon(36,259,577,TYPE_FIELD),
			new Hexagon(37,394,651,TYPE_FIELD),
			new Hexagon(38,525,724,TYPE_FIELD),
			new Hexagon(39,662,651,TYPE_FIELD),
			new Hexagon(40,794,577,TYPE_FIELD),
			
			new Hexagon(41,193,540,TYPE_VILLAGE),
			new Hexagon(42,326,614,TYPE_VILLAGE),
			new Hexagon(43,457,686,TYPE_VILLAGE),
			new Hexagon(44,590,686,TYPE_VILLAGE),
			new Hexagon(45,728,614,TYPE_VILLAGE),
			new Hexagon(46,861,540,TYPE_VILLAGE)
		};
		for (int i = 0; i < hexes.length; i++)
			hexes[i].setNeighbors(getNeighbors(hexes[i]));
	}
	public final Point2D center;
	public final int type;
	public List<Hexagon> neighbors;
	
	public Hexagon(int id, int x, int y, int type) {
		super(id);
		this.center = new Point2D(x, y);
		this.type = type;
	}
	public float getDistSquared(Point2D pp) {
		Point2D r = center.minus(pp).squared();
		return r.x + r.y;
	}
	void setNeighbors(List<Hexagon> neighbors) {
		this.neighbors = neighbors;
	}
	
	private static List<Hexagon> getNeighbors(Hexagon prop) {
		int dist = 75*75+40*40;
		List<Hexagon> result = new ArrayList<Hexagon>();
		for (int i = 0; i < hexes.length; i++)
			if (hexes[i].id != prop.id && hexes[i].getDistSquared(prop.center) <= dist)
				result.add(hexes[i]);
		return result;
	}
	public static List<Hexagon> getHexes(){
		return Arrays.asList(hexes);
	}
	public static Hexagon getHex(Point2D p) {
		for (int i = 0; i < hexes.length; i++)
			if (hexes[i].getDistSquared(p) < 1000)
				return hexes[i];
		return null;
	}
	@Override
	public int getPieceIndex() {
		return -1;
	}
	
}
