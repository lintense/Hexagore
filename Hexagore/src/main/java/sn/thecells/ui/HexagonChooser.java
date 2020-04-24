package sn.thecells.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.List;

import sn.thecells.control.GameController;
import sn.thecells.entity.Hexagon;

public class HexagonChooser extends Panel {
	
	private List<Hexagon> allowed;
	public HexagonChooser(String message, List<Hexagon> allowed){ // return filter.apply(Hexagon);
		super(new BorderLayout());
		this.allowed = allowed;
		setVisible(true);
		setFont(Application.MESSAGE_FONT);
		
		Label label = new Label(message);
		add(wrapInPanel(label), BorderLayout.CENTER);
		Button popupCloseButton = new Button("Ok!");
		add(wrapInPanel(popupCloseButton), BorderLayout.SOUTH);
		
		popupCloseButton.addActionListener(e -> {
			getParent().setVisible(false);
			getParent().getParent().validate();
			GameController.resumeGame();
		});
	}

	private static Panel wrapInPanel(Component component) {
		Panel jPanel = new Panel();
		jPanel.setBackground(Application.MESSAGE_BACKGROUND_COLOR);
		jPanel.add(component);
		return jPanel;
	}
	
	public static final Cursor cursor;
	static {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension dim = kit.getBestCursorSize(48, 48);
		
		//Get current GraphicsConfiguration
        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment
            .getLocalGraphicsEnvironment()
            .getDefaultScreenDevice()
            .getDefaultConfiguration();
		
        //Create a Compatible BufferedImage
        BufferedImage buffered = graphicsConfiguration.createCompatibleImage(dim.width, dim.height);
        
//		BufferedImage buffered = GraphicsUtilities.createCompatibleTranslucentImage(dim.width, dim.height);
		
		Shape circle = new Ellipse2D.Float(0, 0, dim.width - 1, dim.height - 1);
		Graphics2D g = buffered.createGraphics();
		g.setColor(Color.BLUE);
		g.draw(circle);
		g.setColor(Color.RED);
		int centerX = (dim.width - 1) /2;
		int centerY = (dim.height - 1) / 2;
		g.drawLine(centerX, 0, centerX, dim.height - 1);
		g.drawLine(0, centerY, dim.height - 1, centerY);
		g.dispose();
		cursor = kit.createCustomCursor(buffered, new Point(centerX, centerY), "myCursor");
	}

	    
//	    MouseAdapter
//	    setCursor(handCursor
	    
}
