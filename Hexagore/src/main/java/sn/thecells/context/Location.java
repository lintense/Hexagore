package sn.thecells.context;

import java.util.List;

import sn.thecells.entity.Piece;

/**
 * @author snadeau
 *
 * A location is part of a context and is referring to one hexagon.
 * The locations are use to contain all the pieces (players, monsters and objects)
 * that are placed on the board.
 * 
 * Empty location can be ignored? May be not since they can be queried...
 * 
 * 
 * It would be great to have a visual description of the location.
 * 00-BACK-The background would be the type of terrain. (Limit one per hex)
 * 10-BIG-Then the buildings (tower, bridge, stables, etc). (Limit one per hex)
 * 20-Then the nature stuff (fire, flood, plants, etc). (Limit one per hex)
 * 30-Then the animals and non agents
 * 40-Then the monsters
 * 50-Then the accessories
 * 60-Then the Players (which override all the rest)
 * 
 * 
 */
public class Location {

	List<Player> players;
	List<Piece> pieces;
	
	
	
}
