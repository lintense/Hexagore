package sn.thecells.ui;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class GraphMenu extends Panel
{
//	private Panel		topPanel;
//	private	PopupMenu	popupMenu;
	
	public GraphMenu()
	{
		super(new BorderLayout());
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();
				if ("Quit".equals(s)) System.exit(0);
				MenuItem mi = (MenuItem)e.getSource();
				Menu m = (Menu)mi.getParent();
				Label l = (Label)m.getParent();
				if ("black".equals(s)) l.setBackground(Color.black);
				else if ("blue".equals(s)) l.setBackground(Color.blue);
				else if ("cyan".equals(s)) l.setBackground(Color.cyan);
				else if ("darkGray".equals(s)) l.setBackground(Color.darkGray);
				else if ("gray".equals(s)) l.setBackground(Color.gray);
				else if ("green".equals(s)) l.setBackground(Color.green);
				else if ("lightGray".equals(s)) l.setBackground(Color.lightGray);
				else if ("magenta".equals(s)) l.setBackground(Color.magenta);
				else if ("orange".equals(s)) l.setBackground(Color.orange);
				else if ("pink".equals(s)) l.setBackground(Color.pink);
				else if ("red".equals(s)) l.setBackground(Color.red);
				else if ("white".equals(s)) l.setBackground(Color.white);
				else if ("yellow".equals(s)) l.setBackground(Color.yellow);
			}
		};
		add("North", new PopupLabel("North", Label.CENTER, listener));
		add("West", new PopupLabel("West", Label.RIGHT, listener));
		add("East", new PopupLabel("East", Label.LEFT, listener));
		add("Center", new PopupLabel("Center", Label.CENTER, listener));
		add("South", new PopupLabel("South", Label.CENTER, listener));
	}
	protected static class Popup extends PopupMenu {
		public Popup (ActionListener listener) {
			final String entries[] = { "black", "blue", "cyan", "darkGray", "gray",
				"green", "lightGray", "magenta", "orange", "pink",
				"red", "white", "yellow", "Quit" };
			for (int i = 0; i < entries.length; ++ i) {
				MenuItem mi = new MenuItem(entries[i]);
				mi.addActionListener(listener);
				add(mi);
				if (i == 2) addSeparator();
			}
			this.getItem(2).setEnabled(false);
			this.remove(4);
		}
	}
	protected static class PopupLabel extends Label {
		protected Popup popup;
		public PopupLabel (String text, int orientation, ActionListener listener) {
			super(text, orientation);
			add(popup = new Popup(listener));
			enableEvents( AWTEvent.MOUSE_EVENT_MASK );
		}
		public void processMouseEvent (MouseEvent e) {
			if (e.isPopupTrigger())
				popup.show(this, e.getX(), e.getY());
			else
				super.processMouseEvent(e);
		}
	}

}
