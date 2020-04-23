package sn.thecells.command;

import sn.thecells.control.ActionController;

public class SpawnHere implements ICommand {

	private final CommandContext ctx;
	public SpawnHere(CommandContext ctx) {
		this.ctx = ctx;
	}
	
	public boolean isEnabled() {
		return ctx.getCoordinate() != null;
	}

	public boolean isVisible() {
		return true;
	}

	public void execute() {
		// Should never happen - Do nothing
	}

	public String getLabel() {
		return "Spawn Here";
	}

	public String[] getParameters() {
		// Label, Parm, Label, Parm, ...
		return new String[]{"Red", "1", "Blue", "2"};
	}

	public void execute(String parm) {
		ActionController ac = ctx.getActionController();
//		ac.getDrawer().drawPiece(ac.getImage().getImage(), Integer.parseInt(parm), ctx.getCoordinate());
		ac.repaint();
	}
}
