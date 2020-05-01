package sn.thecells.context;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author snadeau
 *
 * A phase is a part of the game.
 * A phase may have sub phases.
 * Each phase always occur in the same order.
 * Some phase may be skipped at times.
 * The phases are all created at the beginning of the game.
 * 
 * The phase structure main idea is to allow all the operation to be assigned to a specific phase
 * with the least impact possible with respect to the other phases.
 * 
 * Special meaning inserted in the phase name
 * 
 * 	.iterate (Requires a OPERATION_TYPE = Collection)
 * 		A phase with .n. is repeated n times.
 * 		A phase ending with .n will determine if a new loop will follow.
 * 		A phase is active (DURING) when its name is contained in the name of the current phase.
 * 		The AFTER phase is done when the next phase does not contain its name.
 * 		An operation should provide a collection to iterate
 * 		
 * 	.select (Requires a OPERATION_TYPE = Collection)
 * 		A phase ending by .a is responsible to select which of its child will follow
 * 		Phases ending with select are deciding which phase will follow.
 * 		A previous phase should provide a collection of choices
 * 		SingleChooser
 * 		HexagonChooser
 * 
 * 	.count (Requires a OPERATION_TYPE = Number)
 * 		A phase ending by count is meant to compute a total of some sort
 * 		Only the operation leading to a number will be taken into account
 * 		.count (init the sum)  Only ONE EXACT operation is possible here!
 * 		.count.add.x1 (sum += x1)
 * 		.count.sub.x2 (sum -= x2)
 * 		.count.select (use current count to select next phase) Only ONE EXACT operation is allowed here!
 * 
 * 	.collect (Requires a OPERATION_TYPE = Collection)
 * 		A phase that will collect entities
 * 		Each operation should be ADD or REMOVE
 * 		At the end of the phase, all ADD are combined and all REMOVED are removed.
 * 		Only the remaining elements are produced.
 * 		.collect (init the collection) Only ONE EXACT operation is allowed here!
 *		.collect.add.x1 (collection_add.addAll(x1)
 *		.collect.sub.x2 (collection_sub.addAll(x2)
 *		.collect.select (use current collection = collection_add - collection_sub) Only ONE EXACT operation is allowed here!
 *
 *	.map (Requires a OPERATION_TYPE = map)
 *		A phase that is building a map
 *		Each operation should be ADD or REMOVE items in a key
 *		.map (init the map) Only ONE EXACT operation is allowed here!
 *		.map.put.x1
 *		.map.get (current entity = value of selected key)
 *		.map.collect (collect = key collection)
 *		MultiChooser
 *
 *	.check (Requires a OPERATION_TYPE = boolean)
 *		Check if this phase has to be played.
 *		If false (i.e. no monster) then skip this phase and all its children entirely.
 *		Can have many operations but all must be true for the phase to proceed. (AND)
 *
 *	Advantages:
 *		Taking into account the available choosers.
 *		Make the logic visible and allow insertion of dynamic operations.
 *
 * NICE: The phase manager which decide the appropriate chooser!
 * 		i.e .collect.select that chooses from a collection
 * 		i.e .map.select
 * 			collect (player pieces), collect (opponent types)
 * 
 *
 */
public class Phase {

	
	public static int TYPE_COUNT = 1;
	public static int TYPE_ITERATE = 2;
	public static int TYPE_COLLECT = 4;
	public static int TYPE_SELECT = 8;
	public static int TYPE_MAP = 16;
	public static int TYPE_CHECK = 32;
	public static int TYPE_UPDATE_OPERATION = 64;
	public static int TYPE_UPDATE_CONTEXT= 128;
	
	
	// All possible phases in order
	// Add more when necessary!
	private static String[] phases = new String[] {
	"game",
	"game.setup",
	"game.setup.board",
	"game.setup.board.init",
	"game.setup.board.init.draw",
	"game.setup.board.init.market",
	"game.setup.board.init.trash", // empty
	"game.setup.player",
	"game.setup.player.ask", // select the number of player
	"game.setup.player.n",
	"game.setup.player.n.get",
	"game.setup.player.n.get.name",
	"game.setup.player.n.get.crest",
	"game.setup.player.n.get.village",
	"game.setup.player.n.init",
	"game.setup.player.n.init.draw",
	"game.setup.player.n.init.hand", // empty
	"game.setup.player.n.init.trash", // empty
	"game.setup.player.first",
	"game.turn",
	"game.turn.n",
	"game.turn.n.init",
	"game.turn.n.init.player",
	"game.turn.n.init.player.reset",
	"game.turn.n.init.player.reset.hand",
	"game.turn.n.init.board",
	"game.turn.n.init.board.reset",
	"game.turn.n.init.board.reset.market",
	"game.turn.n.play",
	"game.turn.n.play.check",  // is game terminated?
	"game.turn.n.play.event",
	"game.turn.n.play.event.n",
	"game.turn.n.play.event.n.process",
	"game.turn.n.play.player",
	"game.turn.n.play.player.first",
	"game.turn.n.play.player.n",
	"game.turn.n.play.player.n.action",
	"game.turn.n.play.player.n.action.a", // Ask player for action
	"game.turn.n.play.player.n.action.a.buy",
	"game.turn.n.play.player.n.action.a.move",
	"game.turn.n.play.player.n.action.a.move.out",
	"game.turn.n.play.player.n.action.a.move.out.monster",
	"game.turn.n.play.player.n.action.a.move.out.monster.check",
	"game.turn.n.play.player.n.action.a.move.out.monster.a",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.attack",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.attack.round",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.attack.round.n",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.attack.round.n.bonus",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.attack.round.n.bonus.card",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.attack.round.n.bonus.card.n",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.attack.round.n.malus",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.attack.round.n.malus.card",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.attack.round.n.malus.card.n",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.attack.count",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.attack.count.add",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.attack.count.sub",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.attack.count.select",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.attack.count.select.lose",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.attack.count.select.lose.drop",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.attack.count.select.win",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.attack.count.select.win.pick",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.bribe",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.bribe.round",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.bribe.round.n",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.bribe.round.n.bonus",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.bribe.round.n.bonus.card",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.bribe.round.n.bonus.card.n",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.bribe.round.n.malus",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.bribe.round.n.malus.card",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.bribe.round.n.malus.card.n",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.bribe.count",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.bribe.outcome",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.bribe.outcome.a",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.bribe.outcome.a.lose",
	"game.turn.n.play.player.n.action.a.move.out.monster.a.bribe.outcome.a.win",
	"game.turn.n.play.player.n.action.a.move.in",
	"game.turn.n.play.player.n.action.a.trash",
	"game.turn.n.play.player.n.action.a.pick",
	"game.turn.n.play.player.n.action.a.drop",
	"game.turn.n.play.player.n.action.a.attack",
	"game.turn.n.play.player.n.action.a.attack.a",
	"game.turn.n.play.player.n.action.a.attack.a.opponent",
	"game.turn.n.play.player.n.action.a.attack.a.opponent.round",
	"game.turn.n.play.player.n.action.a.attack.a.opponent.round.n",
	"game.turn.n.play.player.n.action.a.attack.a.opponent.round.n.bonus",
	"game.turn.n.play.player.n.action.a.attack.a.opponent.round.n.bonus.card",
	"game.turn.n.play.player.n.action.a.attack.a.opponent.round.n.bonus.card.n",
	"game.turn.n.play.player.n.action.a.attack.a.opponent.round.n.malus",
	"game.turn.n.play.player.n.action.a.attack.a.opponent.round.n.malus.card",
	"game.turn.n.play.player.n.action.a.attack.a.opponent.round.n.malus.card.n",
	"game.turn.n.play.player.n.action.a.attack.a.opponent.count",
	"game.turn.n.play.player.n.action.a.attack.a.opponent.outcome",
	"game.turn.n.play.player.n.action.a.attack.a.opponent.outcome.lose", // Always both
	"game.turn.n.play.player.n.action.a.attack.a.opponent.outcome.lose.drop",
	"game.turn.n.play.player.n.action.a.attack.a.opponent.outcome.win",
	"game.turn.n.play.player.n.action.a.attack.a.opponent.outcome.win.pick",
	"game.turn.n.play.player.n.action.a.attack.a.monster",
	"game.turn.n.play.player.n.action.a.attack.a.monster.round",
	"game.turn.n.play.player.n.action.a.attack.a.monster.round.n",
	"game.turn.n.play.player.n.action.a.attack.a.monster.round.n.bonus",
	"game.turn.n.play.player.n.action.a.attack.a.monster.round.n.bonus.card",
	"game.turn.n.play.player.n.action.a.attack.a.monster.round.n.bonus.card.n",
	"game.turn.n.play.player.n.action.a.attack.a.monster.round.n.malus",
	"game.turn.n.play.player.n.action.a.attack.a.monster.round.n.malus.card",
	"game.turn.n.play.player.n.action.a.attack.a.monster.round.n.malus.card.n",
	"game.turn.n.play.player.n.action.a.attack.a.monster.count",
	"game.turn.n.play.player.n.action.a.attack.a.monster.outcome",
	"game.turn.n.play.player.n.action.a.attack.a.monster.outcome.a",
	"game.turn.n.play.player.n.action.a.attack.a.monster.outcome.a.lose",
	"game.turn.n.play.player.n.action.a.attack.a.monster.outcome.a.lose.drop",
	"game.turn.n.play.player.n.action.a.attack.a.monster.outcome.a.win",
	"game.turn.n.play.player.n.action.a.attack.a.monster.outcome.a.win.pick",
	"game.turn.n.play.player.n.action.a.card",
	"game.turn.n.play.player.n.action.a.hero",
	"game.turn.n.play.player.n.action.a.pass",
	"game.turn.n.play.player.done",
	"game.turn.n.play.market",
	"game.turn.n.play.market.monster",
	"game.turn.n.play.market.monster.n",
	"game.turn.n.play.market.monster.n.place",
	"game.turn.n.reset",
	"game.turn.n.reset.first"};

//	private static Phase current;
//	private static Map<String,Integer> counters = new HashMap<String,Integer>();
	private static Context ctx = new Context(); // Eventually, context will be member of the phase and a copy given to the child.
	// So it will be possible to undo to any parent phase...
	
	public static void main(String[] args) {
		
		Operation.test(); // Add testing operations
		
		new Phase("game").start();
		
//		while (current != null) {
//			current = current.getNext();
//		}
		System.out.println("Program done!");
	}
	public static boolean exists(String name) {
		return Arrays.stream(phases).anyMatch(p -> p.equals(name));
	}
	
	private final String name;
	private final Phase parent;
	
	public Phase(String name) {
		super();
//		System.out.println("Creating phase: " + name);
		this.name = name;
		this.parent = null;
	}
	public Phase(Phase parent, String name) {
		super();
//		System.out.println("Creating phase: " + name);
		this.name = name;
		this.parent = parent;
		if (!name.startsWith(parent.name))
			throw new IllegalArgumentException();
	}
	void start() {
		System.out.println("Starting:  " + name);
		List<String> childrenNames = getChildrenNames();
		
		List<Operation> ops = Operation.get(name);
		
		if (name.endsWith(".a")) {
			pickRandom(childrenNames).start();
		} else if (name.endsWith(".n")) {
			int n = (int) (Math.random() * 3 + 2);
			for (int i = 0; i < n; i++) {
				System.out.println("Iterating: " + name + " = " + (i + 1));
				for (String childName : childrenNames) {
					new Phase(this, childName).start();
				}
			}
		} else if (name.endsWith(".count")) {
			Operation op = Operation.getExact(name);
			ctx.setCount(op == null ? 0 : op.getCount(ctx));
			for (String childName : childrenNames)
				new Phase(this, childName).start();
		} else if (name.endsWith(".count.add")) {
			for (Operation op : ops) {
				ctx.addCount(op.getCount(ctx));
				System.out.println("	Operation: " + op + "	Context count: " + ctx.getCount());
			}
		} else if (name.endsWith(".count.sub")) {
			for (Operation op : ops) {
				ctx.subCount(op.getCount(ctx));
				System.out.println("	Operation: " + op + "	Context count: " + ctx.getCount());
			}
		} else if (name.endsWith(".select")) {
			Operation op = Operation.getExact(name);
			if (op != null)
				new Phase(this, op.getNextPhaseName(ctx)).start();
			else
				pickRandom(childrenNames).start();
		} else {
			for (String childName : childrenNames)
				new Phase(this, childName).start();
		}
		System.out.println("Ending:    " + name);
	}
	private Phase pickRandom(List<String> childrenNames) {
		int random = (int)(Math.random() * childrenNames.size());
		Phase child = new Phase(this, childrenNames.get(random));
		return child;
	}
	private List<String> getChildrenNames(){
		return Arrays.stream(phases).filter(c-> isImmediateChild(c)).collect(Collectors.toList());
	}
	private boolean isImmediateChild(String phaseName) {
		return !phaseName.equals(name) && phaseName.startsWith(name) && phaseName.indexOf('.', name.length() + 1) == -1;
	}
}
