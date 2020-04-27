package sn.thecells.context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import sn.thecells.entity.Piece;

/**
 * @author snadeau
 *
 * Only Operations can perform changes on the game context.
 * Operations are dynamically attached to the phases upon events.
 * When attached to a phase, an operation can be in one of the Before, During or After section of the phase.
 * All operation that are attached to the same phase section are processed sequentially but in no specific order.
 * If an ordering of the operation is required, then a new phase/sub phase should be created.
 * 
 * Operations are read by the phase manager.
 * Only the operation associated to the current phase and its parents are taken into account.
 * 
 * NEED
 * Simpler, Flexible, Verbose
 * WANT
 * Operations are reusable (same Operation can be used in different context)
 * 
 */
public class Operation {


	
	
	public static int SCOPE_GAME = 100;
	public static int SCOPE_PHASE = 110;
	public static int SCOPE_PARENT = 120;
	
	
	
	// All the available operations
	private static Set<Operation> operations = new HashSet<Operation>();
	
	public static void add(Operation op) {
		operations.add(op);
		System.out.println("Adding operation: " + op.label);
		
	}
	public static void remove(Operation op) {
		operations.remove(op);
		System.out.println("Removing operation: " + op.label);
	}
	public static List<Operation> get(String phase){
		return operations.stream().filter(o -> phase.startsWith(o.phase)).collect(Collectors.toList());
	}
	public static Operation getExact(String phase){
		List<Operation> list = operations.stream().filter(o -> phase.equals(o.phase)).collect(Collectors.toList());
		if (list.size() > 1)
			throw new IllegalStateException("Only one operation is allowed in phase: " + phase);
		return list.size() == 1 ? list.get(1) : null;
	}

	public static void test() {
		add(new Operation("count.monster.strength", "game.turn.n.play.player.n.action.a.move.out.monster.a.attack.count.add", Phase.TYPE_COUNT, SCOPE_GAME, null));
		add(new Operation("count.defender.strength", "game.turn.n.play.player.n.action.a.move.out.monster.a.attack.count.sub", Phase.TYPE_COUNT, SCOPE_GAME, null));
		//add(new Operation("count.arena.skulls", "game.turn.n.play.player.n.action.a.move.out.monster.a.attack.count.add", Phase.TYPE_COUNT, SCOPE_GAME, null));
		add(new Operation("select.monster.attack.outcome", "game.turn.n.play.player.n.action.a.move.out.monster.a.attack.count.select", Phase.TYPE_SELECT, SCOPE_GAME, null));
	}
	
	
	private final String label;
	private final String phase;
	private final int type;
	private final int scope;
	private final Operation parent;

	public Operation(String label, String phase, int type, int scope, Operation parent) {
		super();
		this.label = label;
		this.phase = phase;
		this.type = type;
		this.scope = scope;
		this.parent = parent;
		if (!Phase.exists(phase))
			throw new IllegalArgumentException("Unknown phase label: " + phase);
		if (scope == SCOPE_PARENT && parent == null)
			throw new IllegalArgumentException("Missing parent for scope");
			
	}
	public String getId() {
		return label + "-" + phase;
	}
	public String getNextPhaseName(Context ctx) {
		if (type != Phase.TYPE_SELECT)
			throw new IllegalArgumentException("Invalid operation type for phase: " + phase);
		switch (label) {
		case "select.monster.attack.outcome":
			return ctx.getCount() >= 0 ? "game.turn.n.play.player.n.action.a.move.out.monster.a.attack.count.select.lose" : "game.turn.n.play.player.n.action.a.move.out.monster.a.attack.count.select.win";
		default:
			throw new IllegalStateException("Unknown Operation label: " + label);
		}
	}
	public int getCount(Context ctx) {
		if (type != Phase.TYPE_COUNT)
			return 0;
		switch (label) {
		case "count.attacker.strength":
			return ctx.getAttacker().getPiece().strength;
		case "count.defender.strength":
			return ctx.getDefender().getPiece().strength;
		case "count.arena.skulls":
			return (int)ctx.getArena().pieces.stream().filter(p -> p.type == Piece.TYPE_SKULL).count();
//			return ctx.getArena().pieces.stream().map(Piece::getStrength).reduce(0, Integer::sum).intValue();
		default:
			throw new IllegalStateException("Unknown Operation label: " + label);
		}
	}
	@Override
    public boolean equals(Object o) {
		return o instanceof Operation && this.getId().equals(((Operation)o).getId());
	}
	@Override
	public int hashCode() {
		return label.hashCode();
	}
}
