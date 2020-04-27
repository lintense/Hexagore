package sn.thecells.context;

/**
 * @author snadeau
 *
 * A rule can be applied after an operation has been performed on a context.
 * The rule takes the original context, before the operation is applied and 
 * the context after the operation has been applied.
 * 
 * If the conditions of the rule are not met, then the operation must be discarded.
 * 
 * Rules could/should also be used to remove choices when querying the users for values.
 */
public class Rule {

	
	// Player cannot play event cards in hand
	// 2 players cannot share the same village
	// Can pick only one treasur on mine
	// No fight on mine or village
	// no event can only apply on a single village or a mine
	
	// Only one monster per case
	// No monster on villages or mine
	// No two mountains with the same monster type
	// New monster next to already in place one of the same type
	// If no monster of a type then must be placed on mountain
}
