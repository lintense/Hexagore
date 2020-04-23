package sn.thecells.ui;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.OverlayLayout;
import javax.swing.border.LineBorder;

import sn.thecells.command.ICommand;
import sn.thecells.control.*;
import sn.thecells.ui.GraphMenu.Popup;

public class GraphPanel extends JPanel
{
	protected Popup popup;

	ActionController ac;
//	Graphics offgraphics;
//	Dimension offscreensize;
	
	public GraphPanel(EventController ec, ActionController ac) {
		this.ac = ac;
		addMouseListener(ec);
		addMouseMotionListener(ec);
		addMouseWheelListener(ec);
		add(popup = new Popup(ac, ec));
		setBackground(Color.white);
		
		
		setVisible(true);
		
	}
	public synchronized void update(Graphics g) {
		ac.redraw(this, g);
	}
    public void paint(Graphics g) {
//    	super.paint(g);
        update(g);
    }
	public void processMouseEvent (MouseEvent e) {
		if (e.isPopupTrigger())
			popup.show(this, e.getX(), e.getY());
		else
			super.processMouseEvent(e);
	}
	protected static class Popup extends PopupMenu {
		ActionListener listener;
		ActionController ac;
		public Popup (ActionController ac, ActionListener listener) {
			this.listener = listener;
			this.ac = ac;
		}
		@Override
		public void show(Component c, int x, int y) {
			removeAll();
			for (ICommand com : ac.getCommands(x, y)) {
				if (com.isVisible()) {
					if (com.getParameters() == null) {
						MenuItem mi = new MenuItem(com.getLabel());
						mi.addActionListener(listener);
						mi.setEnabled(com.isEnabled());
						add(mi);
					} else {
						Menu me = new Menu(com.getLabel());
						add(me);
						String[] Parms = com.getParameters();
						for (int i = 0; i < Parms.length; i += 2) {
							MenuItem mi = new MenuItem(Parms[i]);
							mi.addActionListener(listener);
							mi.setActionCommand(com.getLabel() + ActionController.COMMAND_SEPARATOR + Parms[i + 1]);
							me.add(mi);
						}
					}
				}
			}
			super.show(c, x, y);
		}
	}

}
