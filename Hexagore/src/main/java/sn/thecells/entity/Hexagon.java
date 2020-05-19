package sn.thecells.entity;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import sn.thecells.support.Point2D;

public class Hexagon extends Entity {

	public static final int TYPE_UNKNOWN = 0;
	public static final int TYPE_GOLD_MINE = 1;
	public static final int TYPE_MOUNTAIN = 2;
	public static final int TYPE_FOREST = 3;
	public static final int TYPE_RIVER = 4;
	public static final int TYPE_PRAIRIE = 5;
	public static final int TYPE_VILLAGE = 6;
	public static final Hexagon[] hexes;
	
	private static final String FILE_HEX = "TO_BE_CREATED";
	private static final int NEIGHBORS_SQUARED_DIST = 1000;
	private static final String[] TYPE_NAMES = {"unknown","mine","mountain","forest","river","prairie","village"};
	
	static {
		int i = 0;
		hexes = new Hexagon[] {
			new Hexagon("Hex.G71",FILE_HEX,i++,531,282,TYPE_GOLD_MINE,7),
			
			new Hexagon("Hex.M61",FILE_HEX,i++,462,317,TYPE_MOUNTAIN,6),
			new Hexagon("Hex.M62",FILE_HEX,i,530,354,TYPE_MOUNTAIN,6),
			new Hexagon("Hex.M63",FILE_HEX,i,595,317,TYPE_MOUNTAIN,6),
			
			new Hexagon("Hex.F51",FILE_HEX,i++,394,354,TYPE_FOREST,5),
			new Hexagon("Hex.F52",FILE_HEX,i,461,391,TYPE_FOREST,5),
			new Hexagon("Hex.F53",FILE_HEX,i,529,430,TYPE_FOREST,5),
			new Hexagon("Hex.F54",FILE_HEX,i,594,391,TYPE_FOREST,5),
			new Hexagon("Hex.F55",FILE_HEX,i,662,354,TYPE_FOREST,5),
			
			new Hexagon("Hex.R41",FILE_HEX,i++,326,391,TYPE_RIVER,4),
			new Hexagon("Hex.R42",FILE_HEX,i,394,430,TYPE_RIVER,4),
			new Hexagon("Hex.R43",FILE_HEX,i,460,465,TYPE_RIVER,4),
			new Hexagon("Hex.R44",FILE_HEX,i,528,503,TYPE_RIVER,4),
			new Hexagon("Hex.R45",FILE_HEX,i,593,465,TYPE_RIVER,4),
			new Hexagon("Hex.R46",FILE_HEX,i,662,430,TYPE_RIVER,4),
			new Hexagon("Hex.R47",FILE_HEX,i,728,391,TYPE_RIVER,4),
			
			new Hexagon("Hex.P31",FILE_HEX,i++,259,430,TYPE_PRAIRIE,3),
			new Hexagon("Hex.P32",FILE_HEX,i,326,465,TYPE_PRAIRIE,3),
			new Hexagon("Hex.P33",FILE_HEX,i,394,503,TYPE_PRAIRIE,3),
			new Hexagon("Hex.P34",FILE_HEX,i,459,540,TYPE_PRAIRIE,3),
			new Hexagon("Hex.P35",FILE_HEX,i,527,577,TYPE_PRAIRIE,3),
			new Hexagon("Hex.P36",FILE_HEX,i,592,540,TYPE_PRAIRIE,3),
			new Hexagon("Hex.P37",FILE_HEX,i,662,503,TYPE_PRAIRIE,3),
			new Hexagon("Hex.P38",FILE_HEX,i,728,465,TYPE_PRAIRIE,3),
			new Hexagon("Hex.P39",FILE_HEX,i,794,430,TYPE_PRAIRIE,3),
			
			new Hexagon("Hex.P21",FILE_HEX,i++,193,465,TYPE_PRAIRIE,2),
			new Hexagon("Hex.P22",FILE_HEX,i,259,503,TYPE_PRAIRIE,2),
			new Hexagon("Hex.P23",FILE_HEX,i,326,540,TYPE_PRAIRIE,2),
			new Hexagon("Hex.P24",FILE_HEX,i,394,577,TYPE_PRAIRIE,2),
			new Hexagon("Hex.P25",FILE_HEX,i,458,614,TYPE_PRAIRIE,2),
			new Hexagon("Hex.P26",FILE_HEX,i,526,651,TYPE_PRAIRIE,2),
			new Hexagon("Hex.P27",FILE_HEX,i,591,614,TYPE_PRAIRIE,2),
			new Hexagon("Hex.P28",FILE_HEX,i,662,577,TYPE_PRAIRIE,2),
			new Hexagon("Hex.P29",FILE_HEX,i,728,540,TYPE_PRAIRIE,2),
			new Hexagon("Hex.P2A",FILE_HEX,i,794,503,TYPE_PRAIRIE,2),
			new Hexagon("Hex.P2B",FILE_HEX,i,861,470,TYPE_PRAIRIE,2),
			
			new Hexagon("Hex.P11",FILE_HEX,i++,259,577,TYPE_PRAIRIE,1),
			new Hexagon("Hex.P12",FILE_HEX,i,394,651,TYPE_PRAIRIE,1),
			new Hexagon("Hex.P13",FILE_HEX,i,525,724,TYPE_PRAIRIE,1),
			new Hexagon("Hex.P14",FILE_HEX,i,662,651,TYPE_PRAIRIE,1),
			new Hexagon("Hex.P15",FILE_HEX,i,794,577,TYPE_PRAIRIE,1),
			
			new Hexagon("Hex.V1",FILE_HEX,i++,193,540,TYPE_VILLAGE,1),
			new Hexagon("Hex.V2",FILE_HEX,i,326,614,TYPE_VILLAGE,1),
			new Hexagon("Hex.V3",FILE_HEX,i,457,686,TYPE_VILLAGE,1),
			new Hexagon("Hex.V4",FILE_HEX,i,590,686,TYPE_VILLAGE,1),
			new Hexagon("Hex.V5",FILE_HEX,i,728,614,TYPE_VILLAGE,1),
			new Hexagon("Hex.V6",FILE_HEX,i,861,540,TYPE_VILLAGE,1)
		};
		for (int ii = 0; ii < hexes.length; ii++)
			hexes[ii].setNeighbors(getNeighbors(hexes[ii]));
		
	}
	public final Point2D center;
	public final int type;
	public final int height;
	public List<Hexagon> neighbors;
	
	public Hexagon(String label, String propFile, int propIndex, int x, int y, int type, int height) {
		super(label, propFile, propIndex);
		this.center = new Point2D(x, y);
		this.type = type;
		this.height = height;
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
	public static void writePrologScript(Writer w) throws IOException {
		w.append("/* Generated from method: sn.thecells.entity.Hexagon.writePrologScript() */\n");
		w.append("/* NOTE: _base predicates should be overriden for better control */\n\n");
		w.append(":- dynamic(hex_type_base/2).\n").append(":- dynamic(hex_height_base/2).\n").append(":- dynamic(hex_neb_base/2).\n\n");
		w.append("/* All board hexagons */\n");
		for (Hexagon hex : hexes)
			w.append("hex_type_base(").append(getPrologTerm(hex.label)).append(",").append(TYPE_NAMES[hex.type].toLowerCase()).append(").\n");
		w.append("\n/* All hexagons height (aka distance the from board bottom) */\n");
		for (Hexagon hex : hexes)
			w.append("hex_height_base(").append(getPrologTerm(hex.label)).append(",").append(Integer.toString(hex.height)).append(").\n");
		w.append("\n/* All hexagons neighbors */\n");
		for (Hexagon hex : hexes)
			for (Hexagon neighbors : hex.neighbors)
				w.append("hex_neb_base(").append(getPrologTerm(hex.label)).append(",").append(getPrologTerm(neighbors.label)).append(").\n");
		w.append("\n");
	}
	private static String getPrologTerm(String s) {
		return s.toLowerCase().replace('.', '_');
	}
	
	
	
	
}
