package sn.thecells.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import sn.thecells.entity.Hexagon;
import sn.thecells.generic.InputRequest;
import sn.thecells.support.Point2D;

public class HexagonChooser extends Panel implements MouseMotionListener, InputRequest, MouseListener {
	
	private final List<Hexagon> allowed;
	private final Cursor cursor;
	private Hexagon selection = null;
	private Point2D mousePoint = null;
	String noSelectionMessage;
	Label label;
	
	public HexagonChooser(String message, List<Hexagon> allowed, String noSelectionMessage){ // return filter.apply(Hexagon);
		super(new BorderLayout());
		this.allowed = allowed;
		this.noSelectionMessage = noSelectionMessage;
//		setVisible(true);
		setFont(Application.MESSAGE_FONT);
		
		add(wrapInPanel(new Label(message)), BorderLayout.NORTH);
		add(wrapInPanel(label = new Label("            " + noSelectionMessage + "              ", Label.CENTER)), BorderLayout.CENTER);
//		Button popupCloseButton = new Button("Ok!");
//		add(wrapInPanel(popupCloseButton), BorderLayout.SOUTH);
//		
//		popupCloseButton.addActionListener(e -> {
//			getParent().setVisible(false);
//			getParent().getParent().validate();
//			GameController.resumeGame();
//		});
		
		this.cursor = initCursor(); // TODO - Need Action to initialize the correct cursor
	}
	private void updateMessage() {
		label.setText(selection != null 
				? sn.thecells.support.Label.get(selection.label) 
				: noSelectionMessage); //
//		invalidate();
//		validate();
//		setVisible(true);
		
		// FIXME Need a way to disable the Ok button when no selection done
//		Component[] allComponents = this.getComponents();
//		Component button = Arrays.stream(allComponents).filter(c -> c instanceof Button).findAny().orElse(null);
//		button.setEnabled(selection != null);
		
	}
	private void updateButton() {
		// Must disable only the button
		// BAD This code is bad because it is knowing too much information...
		((Container)getParent().getParent().getComponent(1)).getComponent(0).setEnabled(getSelection() != null);
	}
	private static Panel wrapInPanel(Component component) {
		Panel jPanel = new Panel();
		jPanel.setBackground(Application.MESSAGE_BACKGROUND_COLOR);
		jPanel.add(component);
		return jPanel;
	}
	public Hexagon getSelection() {
		return selection;
	}
	private Cursor initCursor() {
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
		return kit.createCustomCursor(buffered, new Point(centerX, centerY), "myCursor");
	}
	@Override
	public void setVisible(boolean b) {
		updateButton();
		super.setVisible(b);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		e.getComponent().setCursor(allowed.stream().anyMatch(h -> h.containsPoint(mousePoint)) ? cursor : null);
	}
	@Override
	public String getListenerId() {
		return "HexagonChooser";
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		selection = allowed.stream().filter(h -> h.containsPoint(mousePoint)).findAny().orElse(null);
		updateMessage();
		updateButton();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	public void setBoardCoordinates(Point2D p) {
		this.mousePoint = p;
	}
}
