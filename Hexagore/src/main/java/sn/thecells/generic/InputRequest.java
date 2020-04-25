package sn.thecells.generic;

import java.awt.Panel;

import sn.thecells.support.Point2D;

public interface InputRequest {

	/**
	 * @return If the request is a listener then return an Id otherwise return null
	 */
	default String getListenerId() {
		return null;
	}
	
	default Panel getPanel() {
		return (Panel) this;
	}
	
	default void setBoardCoordinates(Point2D p) {
		// Do nothing
	}
}
