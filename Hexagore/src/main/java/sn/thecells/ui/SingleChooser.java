package sn.thecells.ui;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sn.thecells.control.GameController;
import sn.thecells.entity.Entity;
import sn.thecells.support.Point2D;

public class SingleChooser extends Panel {

	private final List<GraphButton> buttons = new ArrayList();
	List<? extends Entity> first;
	
	// https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
	public SingleChooser(String message, List<? extends Entity> first, GraphDrawer gd){
		
		this.first = first;
		SingleChooser me = this;
		GridBagLayout gridbag = new GridBagLayout();
        setLayout(gridbag);
//        setMaximumSize(new Dimension(500, 570));
        setFont(new Font("SansSerif", Font.PLAIN, 20));
        setBackground(new Color(50, 210, 250, 150));
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.CENTER;
        c.weightx = first.size();
        c.gridwidth = GridBagConstraints.REMAINDER; //end row
        Label label = new Label(message);
        gridbag.setConstraints(label, c);
        add(label);
		
        int index = 0;
        Point2D bs = new Point2D(100,100); // FIXME SHould be entity related
        
        for (Iterator<? extends Entity> j = first.iterator(); j.hasNext();) {
        	Entity ej = j.next();
	        c.fill = GridBagConstraints.BOTH;
	        c.gridwidth = j.hasNext() ? 1 : GridBagConstraints.REMAINDER;
	        c.weightx = 1.0;
	        c.weighty = 1.0;
	        c.gridheight = 2;
	        GraphButton master = new GraphButton(index++, gd, bs, ej);
	        gridbag.setConstraints(master, c);
	        add(master);
	        master.start();
        
//	        for (Iterator<? extends Entity> i = second.iterator(); i.hasNext();) {
//	        	Entity ei = i.next();
//	        	GraphButton button = new GraphButton(index++, master, gd, bs, ei);
//	        	if (!i.hasNext())
//	        		c.gridwidth = GridBagConstraints.REMAINDER;
//	            gridbag.setConstraints(button, c);
//	            add(button);
//	            buttons.add(button);
//	            button.start();
//	            
//	            button.addMouseListener(new MouseAdapter(){
//	    			public void mouseClicked(MouseEvent e) {
//	    				me.onClick(button.id);
//	    			}
//	    		});	
//	        }
        }

        Button popupCloseButton = new Button("Ok!");
        c.weightx = 0.0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(popupCloseButton, c);
        add(wrapInPanel(popupCloseButton));
	      
		popupCloseButton.addActionListener(e -> {
			getParent().setVisible(false);
			getParent().getParent().validate();
			GameController.resumeGame();
		});
	}
	public Entity getSelection() {
		GraphButton but = buttons.stream().filter(c -> c.selected).findAny().orElse(null);
		return but == null ? null : but.entity;
	}
	void onClick(int buttonId) {
		buttons.stream().filter(b -> b.id != buttonId).forEach(b -> {
			if (b.id == buttonId) {
				b.selected = !b.selected;
				b.repaint();
			} else {
				if (b.selected) {
					b.unselect();
					b.repaint();
				}
			}
		});
	}

	public class GraphButton extends Canvas{
	    
		private final int id;
	    private final Entity entity;
	    private final Dimension dim;
	    private final GraphDrawer gd;
	    private final Point2D size;
	    private boolean selected = false;
	    
	    GraphButton(int id, GraphDrawer gd, Point2D size, Entity entity){
	    	this.id = id;
	    	this.dim = new Dimension(size.x, size.y);
	    	this.entity = entity;
	    	this.gd = gd;
	    	this.size = size;
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
	private static Panel wrapInPanel(Component component) {
	      Panel jPanel = new Panel();
	      jPanel.setBackground(new Color(50, 210, 250, 150));
	      jPanel.add(component);
//	      jPanel.setVisible(true);
	      return jPanel;
	  }
}