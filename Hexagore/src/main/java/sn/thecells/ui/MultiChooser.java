package sn.thecells.ui;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.color.ColorSpace;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sn.thecells.control.GameController;
import sn.thecells.entity.Entity;
import sn.thecells.support.Point2D;

public class MultiChooser extends Panel {

	private final List<GraphButton> buttons = new ArrayList();
	List<? extends Entity> first;
	List<? extends Entity> second;
	
	// https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
	public MultiChooser(String message, List<? extends Entity> first, List<? extends Entity> second, GraphDrawer gd){
		
		this.first = first;
		this.second = second;
		MultiChooser me = this;
		GridBagLayout gridbag = new GridBagLayout();
        setLayout(gridbag);
//        setMaximumSize(new Dimension(500, 570));
        setFont(new Font("SansSerif", Font.PLAIN, 20));
        setBackground(new Color(50, 210, 250, 150));
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 4.0;
        c.gridwidth = GridBagConstraints.REMAINDER; //end row
        Label label = new Label(message);
        gridbag.setConstraints(label, c);
        add(label);
		
        int index = 0;
        Point2D bs = new Point2D(100,100);
        
        for (Iterator<? extends Entity> j = first.iterator(); j.hasNext();) {
        	Entity ej = j.next();
	        c.fill = GridBagConstraints.BOTH;
	        c.gridwidth = 1;
	        c.weightx = 1.0;
	        c.weighty = 1.0;
	        c.gridheight = 2;
	        GraphButton master = new GraphButton(index++, null, gd, bs, ej);
	        gridbag.setConstraints(master, c);
	        add(master);
	        buttons.add(master);
	        master.start();
        
	        for (Iterator<? extends Entity> i = second.iterator(); i.hasNext();) {
	        	Entity ei = i.next();
	        	GraphButton button = new GraphButton(index++, master, gd, bs, ei);
	        	if (!i.hasNext())
	        		c.gridwidth = GridBagConstraints.REMAINDER;
	            gridbag.setConstraints(button, c);
	            add(button);
	            buttons.add(button);
	            button.start();
	            
	            button.addMouseListener(new MouseAdapter(){
	    			public void mouseClicked(MouseEvent e) {
	    				me.onClick(button.id);
	    			}
	    		});	
	        }
        }
//        TextField text = new TextField();
//		setFont(new Font("SansSerif", Font.PLAIN, 14));
//        text.setText("Opponent name!");
//        text.setEditable(true);
//        c.weightx = 0.0;
//        c.gridwidth = GridBagConstraints.REMAINDER; //end row
//        gridbag.setConstraints(text, c);
//        add(text);
        
        
        Button popupCloseButton = new Button("Ok!");
        c.weightx = 0.0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(popupCloseButton, c);
        add(popupCloseButton);
	      
		popupCloseButton.addActionListener(e -> {
			getParent().setVisible(false);
			getParent().getParent().validate();
			GameController.resumeGame();
		});

//		frame.pack();
//		frame.setVisible(true);
//		add(frame);
		
	}
	public Map<Entity,Entity> getSelection() {
		Map<Entity,Entity> result = new HashMap();
		buttons.stream().filter(b -> !b.children.isEmpty()).forEach(b -> {
			GraphButton but = b.children.stream().filter(c -> c.selected).findAny().orElse(null);
			if (but != null)
				result.put(b.entity, but.entity);
		});
		return result;
	}
	void onClick(int buttonId) {
		
		// Retrieve selected button
		GraphButton selected = buttons.stream().filter(b -> b.id == buttonId).findAny().get();
		// Reverse selection of selected button
		selected.selected = !selected.selected;
		selected.repaint();
		
		// If button is deselected, also deselect master
		if (!selected.selected) {
			if (selected.master.selected) {
				selected.master.unselect();
				selected.master.repaint();
			}
		} else { // Otherwise, select master and all other children
			if (!selected.master.selected) {
				selected.master.select();
				selected.master.repaint();
			}
			selected.master.children.stream().filter(b -> b.id != buttonId).forEach(b -> {
				if (b.selected) {
					b.unselect();
					b.repaint();
				}
			});
		}
//		invalidate();
//		validate();
//		repaint();
	}

	public class GraphButton extends Canvas{
	    
		private final int id;
	    private final Entity entity;
	    private final Dimension dim;
	    private final GraphDrawer gd;
	    private final Point2D size;
	    private boolean selected = false;
	    private final GraphButton master;
	    private List<GraphButton> children = new ArrayList();
	    
	    GraphButton(int id, GraphButton master, GraphDrawer gd, Point2D size, Entity entity){
	    	this.id = id;
	    	this.master = master;
	    	this.dim = new Dimension(size.x, size.y);
	    	this.entity = entity;
	    	this.gd = gd;
	    	this.size = size;
	    	if (master != null)
	    		master.children.add(this);
	    }

	    public void paint(Graphics g){
	    	Graphics2D g2d = (Graphics2D) g;
	    	ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
	    	BufferedImageOp op = new ColorConvertOp(cs, null);
	    	g2d.drawImage(gd.resizeImage(entity.getPieceIndex(), size), selected ? null : op, 0, 0);
	    }
	    public void unselect() {
	    	selected = false;
	    }
	    public void select() {
	    	selected = true;
	    }
	    public void start(){
	        setPreferredSize(dim);
	    }
	}
//	private static Panel wrapInPanel(Component component) {
//	      Panel jPanel = new Panel();
//	      jPanel.setBackground(new Color(50, 210, 250, 150));
//	      jPanel.add(component);
////	      jPanel.setVisible(true);
//	      return jPanel;
//	  }
}
