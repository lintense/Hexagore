package sn.thecells.ui;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.BoxLayout;

import sn.thecells.control.GameController;
import sn.thecells.entity.Entity;
import sn.thecells.generic.InputRequest;
import sn.thecells.support.Point2D;

public class TextInput extends Panel implements InputRequest {

	private TextField text;
	public TextInput(String message, String suggestion, Entity entity){
		
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setFont(Application.MESSAGE_FONT);
        setBackground(Application.MESSAGE_BACKGROUND_COLOR);

		Point2D imageSize = new Point2D(150,150);
		GraphButton image = new GraphButton(0, imageSize, entity);
		image.setBackground(Application.MESSAGE_IMAGE_BACKGROUND_COLOR);
        add(wrapInPanel(image));
        image.start();
		
		Label label = new Label(message);
		label.setFont(Application.MESSAGE_FONT);
		add(label);
		
		text = new TextField();
		text.setFont(Application.MESSAGE_INPUT_FONT);
		text.setText(suggestion == null ? "" : suggestion);
		text.setEditable(true);
		add(text);
		
//		Button popupCloseButton = new Button("Ok!");
//		add(wrapInPanel(popupCloseButton));
//		
//		popupCloseButton.addActionListener(e -> {
//			getParent().setVisible(false);
//			getParent().getParent().validate();
//			GameController.resumeGame();
//		});
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
	    
	    GraphButton(int id, Point2D size, Entity entity){
	    	this.id = id;
	    	this.dim = new Dimension(size.x, size.y);
	    	this.index = entity.getPropIndex();
	    	this.gd = GraphDrawer.getDrawer(entity);
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
	      jPanel.setBackground(Application.MESSAGE_BACKGROUND_COLOR);
	      jPanel.add(component);
	      return jPanel;
	  }
}
