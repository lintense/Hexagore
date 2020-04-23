package sn.thecells.entity;

public class Creature {

	/**
	
	// Players
	1 = Red(Att), 2 = Blue(Spw), 3 = Green(Mov), 4 = Yellow(mng), 5 = Pink(Dig), 6 = Black(Def)
	
	// Board
	byte[HEXES_PER_ROW * NUMBER_OF_ROWS][13]
	Have a map indexed by position (y * #rows + x);
	Value is a byte[]: 1,300000,001000,
	Controler|# of type 1|# of type 2|...|# of type 6|active pill type|# pill type 1|...|# pill type 6|
	if a cell is not owned it is considered empty so it is useless to index it, it will only be referred by a goal (get the pill, get the bonus)
	
	
	// Creature
	All not empty spaces that are connected and controlled by the same player
	 
	Have a Set indexed by position (real position)
	- This means the hole map must be redone each time the entity moves...
	
	//Touching entities
	- Do not merge entities if not asked for (but they must still be considered as a hole if they touched...)
	- Sort the positions in x and y (2 sorts = 2 maps...)
	- For each axe, get the set of elements that are close enough
	- Merge the 2 sets of each creatures.
	- Fully compare each elements of both sets (Costly part)

	// Pills
	- Can attach Messages (left, right, top, bottom,...) to position contents
	
	
	
	// Move entity
	Points to center of mass, draw a line to the mouse pos
	- Logic for moving: try to move a piece, if the piece hold a pill, try to move the pill
	- If there is already something, then try to move it (in the right direction of course...)
	- If missing room then create a stack (never stack your only MGT!!!)
	
	To compute the new position:
	- get pt-dest - pt-orig en float / dist(nombre de mvt pour se rendre), floor et transformer en pos (delta).
	- add the previously computed delta to each cell members
	- increase each board byte (do not move prisonners), also some case may switch control
	- redraw the dest case (put the orig in a set)
	- At the end remove the cell cases from the orig-set and redraw the orig-set
	
	distances between 2 pts: 453ms / 100000000
	dy = y1-y2;
	z = (dy == 0
		? ((dx = (x1-x2))*dx <= 1)
		: ((dy*dy) == 1 
			? (y1%2 == 1 
				? (x2==x1 || x2-x1==1)
				: (x2==x1 || x1-x2==1)
			)
			: false
		)
	);

	
	
	
	
	
	
	*/
	
}
