package sn.thecells.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.BoxLayout;

import sn.thecells.control.GameController;
import sn.thecells.support.Point2D;

public class TextInput extends Panel {

//	private final List<GraphButton> buttons = new ArrayList();
//	List<? extends Entity> first;
//	List<? extends Entity> second;
	
	TextField text;
	
	public TextInput(String message, int index, GraphDrawer gd, String suggestion){
		
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
//		setMaximumSize(new Dimension(150, 170));
		setBackground(new Color(50, 210, 250, 150));
//		setVisible(true);
		
		Point2D imageSize = new Point2D(150,150);
		GraphButton image = new GraphButton(0, gd, imageSize, index);
		image.setBackground(Color.WHITE);
        add(wrapInPanel(image));
        image.start();
		
		Label label = new Label(message);
		label.setFont(new Font("SansSerif", Font.PLAIN, 20));
		add(label);
		
		text = new TextField();
		text.setFont(new Font("SansSerif", Font.PLAIN, 16));
		text.setText(suggestion == null ? "" : suggestion);
		text.setEditable(true);
		add(text);
		
		Button popupCloseButton = new Button("Ok!");
		add(wrapInPanel(popupCloseButton));
		
		popupCloseButton.addActionListener(e -> {
			getParent().setVisible(false);
			getParent().getParent().validate();
			GameController.resumeGame();
		});
	}
	public String getText() {
		return text.getText();
	}

	public class GraphButton extends Canvas{
	    
		private final int id;
	    private final int index;
	    private final Dimension dim;
	    private final GraphDrawer gd;
	    private final Point2D size;
	    private boolean selected = false;
	    
	    GraphButton(int id,GraphDrawer gd, Point2D size, int index){
	    	this.id = id;
	    	this.dim = new Dimension(size.x, size.y);
	    	this.index = index;
	    	this.gd = gd;
	    	this.size = size;
	    }

	    public void paint(Graphics g){
	    	Graphics2D g2d = (Graphics2D) g;
	    	g2d.drawImage(gd.resizeImage(index, size), null, 0, 0);
	    }
	    public void start(){
	        setPreferredSize(dim);
	    }
	}
	private static Panel wrapInPanel(Component component) {
	      Panel jPanel = new Panel();
	      jPanel.setBackground(new Color(50, 210, 250, 150));
	      jPanel.add(component);
	      return jPanel;
	  }
}
