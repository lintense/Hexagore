package sn.thecells.command;

import sn.thecells.control.ActionController;
import sn.thecells.support.Point2D;

public class CommandContext {

	Point2D coord; // ATTENTION : Possible multi-thread problem here!!!
	private final ActionController ac;
	
	public CommandContext(ActionController ac) {
		this.ac = ac;
	}
	public void setCoordinate(Point2D p) {
		this.coord = p;
	}
	public Point2D getCoordinate() {
		return coord;
	}
	public ActionController getActionController() {
		return ac;
	}
}
