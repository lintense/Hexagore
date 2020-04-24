package sn.thecells.control;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import sn.thecells.control.ActionController.Mover;
import sn.thecells.entity.Hexagon;
import sn.thecells.support.Point2D;
import sn.thecells.ui.HexagonChooser;

public class EventController extends WindowAdapter implements MouseListener, MouseMotionListener, MouseWheelListener, ActionListener, ItemListener, ComponentListener, WindowListener {

//	IMoving mover = new DefaultMoving();
//	boolean mousePressed = false;
	Mover mover = null;
//	boolean mouseEntered = true;
	int mouseX; // TODO useless?
	int mouseY; // TODO useless?
	int button;
	ActionController ac;
//	Graph p;
	
	public EventController(ActionController ac) {
		super();
		this.ac = ac;
//		this.p = p;
		
	}

	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		ac.executeCommand(s);
	}

	//1.1 event handling
	public void mouseClicked(MouseEvent e) {
		button = e.getButton();
		if (button == MouseEvent.BUTTON1) {
			Point2D drawingPoint = ac.getDrawingPointForMouse(e.getX(), e.getY());
			Hexagon hex = Hexagon.getHex(drawingPoint);
			if (hex != null)
				ac.test(hex.center);
//			.neighbors.forEach(hex -> ac.test(hex.x, hex.y));
			
//			Hexagon.getHexes().forEach(hex -> ac.test(hex.x, hex.y));
			
			
//			ac.test(e.getX(), e.getY());
			ac.repaint();
		}
	}

	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		if (mover != null && button == MouseEvent.BUTTON1)
		{
//			ac.drag(e.getX(), e.getY());
			mover.drag(e.getX(), e.getY());
			ac.repaint();
			e.consume();
		}
	}

	public void mouseEntered(MouseEvent e) {
//		mouseEntered = true;
		e.consume();
	}

	public void mouseExited(MouseEvent e) {
//		mouseEntered = false;
		e.consume();
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if (true) { // TODO Avoid this process whenever possible...
			Point2D drawingPoint = ac.getDrawingPointForMouse(e.getX(), e.getY());
			Hexagon hex = Hexagon.getHex(drawingPoint);
			if (hex != null) {
				ac.p.setCursor(HexagonChooser.cursor);
			} else {
				ac.p.setCursor(null);
			}
		}
	}

	public void mousePressed(MouseEvent e) {
		button = e.getButton();
		if (mover == null && button == MouseEvent.BUTTON1) {
			mover = ac.getMover(e.getX(), e.getY());
//			pick(e.getX(), e.getY());
//			ac.pick(e.getX(), e.getY());
//			ac.repaint();
			e.consume();
		}
	}

	public void mouseReleased(MouseEvent e) {

		mover = null;
//		if (mousePressed) {
//			mousePressed = false;
//			ac.unpick(e.getX(), e.getY());
//			ac.repaint();
			e.consume();
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
//		if (ac.isInsidePanel(e.getX(), e.getY())) {
			ac.updateZoom(e.getX(), e.getY(), e.getWheelRotation());
			ac.repaint();
//		}
//		ac.zoom(e.getX(), e.getY(), e.getWheelRotation());
	}
	
	public void timerEvent() {
//	    ac.relax();
////		if (!mousePressed) ac.zoom(mouseX, mouseY);
//		//if (mouseEntered) ac.zoom(mouseX, mouseY);
////		else if (!mousePressed) ac.unzoom(mouseX, mouseY);
//		//else if (!mouseEntered) ac.unzoom(mouseX, mouseY);
//	    ac.repaint();
//	    
//	    if (ac.isRandom()) {
//	    	ac.random();
//	    	ac.play("audio/drip.au");
//	    }
	}
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		Object src = e.getSource();
	
//		if (src == p.scramble) {
//		    ac.play("audio/computer.au");
//		    ac.scramble();
//		    return;
//		}
//	
//		if (src == p.shake) {
//		    ac.play("audio/gong.au");
//		    ac.shake();
//		}
//	}

	public void itemStateChanged(ItemEvent e) {
		Object src = e.getSource();
		boolean on = e.getStateChange() == ItemEvent.SELECTED;
//		if (src == p.stress) ac.setStress(on);
//		else if (src == p.random) ac.setRandom(on);
	}

	public void componentHidden(ComponentEvent e) {
		ac.repaint();
	}

	public void componentMoved(ComponentEvent e) {
		ac.repaint();
	}

	public void componentResized(ComponentEvent e) {
		ac.checkZoom(0, 0);
		ac.repaint();
	}

	public void componentShown(ComponentEvent e) {
		ac.repaint();
	}

	public void windowClosing(WindowEvent e) {
		Window w = e.getWindow();
		w.setVisible(false);
		w.dispose();
		System.exit(0);
	}

}
