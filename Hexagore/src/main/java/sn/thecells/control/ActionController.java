package sn.thecells.control;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JPanel;

import sn.thecells.command.CommandContext;
import sn.thecells.command.ICommand;
import sn.thecells.command.SpawnHere;
import sn.thecells.entity.Entity;
import sn.thecells.entity.Piece;
import sn.thecells.support.Point2D;
import sn.thecells.ui.GraphDrawer;
import sn.thecells.ui.GraphImage;
import sn.thecells.ui.GraphPanel;
import sn.thecells.ui.MessagePanel;

//import test.graphnet.moving.DefaultMoving;
//import test.graphnet.moving.IMoving;
//import test.graphnet.ui.Edge;
//import test.graphnet.ui.Node;

public class ActionController {
	
	public static final String COMMAND_SEPARATOR = "|";
	private static final float ZOOM_IN_FACTOR = 1.1f;
	private static final float ZOOM_OUT_FACTOR = 1/ZOOM_IN_FACTOR;
	
	
	private final Map<String, ICommand> commands = new LinkedHashMap();
	private float zoom = 1f;
	private int x = 0; //-GraphDrawer.BOARD_START_X;
	private int y = 0; //-GraphDrawer.BOARD_START_Y;
	private GraphImage image;
//	private GraphDrawer drawer;
	private GraphPanel panel;
	private CommandContext context;
//	private Random random = new Random();

	final Container p;
//	final URL codeBase;
	
	final Color fixedColor = Color.red;
//	final Color selectColor = Color.pink;
//	final Color edgeColor = Color.black;
//	final Color nodeColor = new Color(250, 220, 100);
//	final Color stressColor = Color.darkGray;
//	final Color arcColor1 = Color.black;
//	final Color arcColor2 = Color.pink;
//	final Color arcColor3 = Color.red;

	/**
	 * GraphPanel constructor comment.
	 */
	public ActionController(Container p) {
		this.p = p;
//		this.codeBase = codeBase;
		getImage();
//		getDrawer();
	}
	public void init(GraphPanel mainPanel) {
		ICommand com;
		panel = mainPanel;
		
		// Initialize commands here
		com = new SpawnHere(getContext());
		commands.put(com.getLabel(), com);
	}
	public void executeCommand(String s) {
		int sep = s.indexOf(COMMAND_SEPARATOR);
		if (sep > 0)
			commands.get(s.substring(0, sep)).execute(s.substring(sep + COMMAND_SEPARATOR.length()));
		else
			commands.get(s).execute();
	}
	public Collection<ICommand> getCommands(int mousex, int mousey) {
		context.setCoordinate(getDrawingPointForMouse(mousex, mousey));
		return commands.values();
	}
	public CommandContext getContext() {
		if (context == null)
			context = new CommandContext(this);
		return context;
	}
	public Mover getMover(int mousex, int mousey) {
		return new Mover(x, y, mousex, mousey);
	}
	public GraphImage getImage() {
		
		if (image == null) {
			URL url = this.getClass().getClassLoader().getResource("\\img\\board.png");
			image = new GraphImage(url);
		}
		return image;
	}
//	public GraphDrawer getDrawer() {
//		if (drawer == null) {
//			URL imageURL = this.getClass().getClassLoader().getResource("\\img\\pieces_210x226.png");
//			drawer = new GraphDrawer(imageURL);
//		}
//		return drawer;
//	}
	private Point2D getOffset() {
		return new Point2D(x, y);
	}
	// Returns a point on the board given a point on the screen
	public Point2D getDrawingPointForMouse(int mousex, int mousey) {
		
		return (new Point2D(mousex, mousey)).minus(getOffset()).dividedBy(zoom);
	}


//	public void test(int dx, int dy) { // TODO - To be removed
//		
////		GraphMenu mainFrame	= new GraphMenu();
////		mainFrame.setLocation(dx, dy);
////		mainFrame.setVisible( true );
//		
//		Point2D drawingPoint = getDrawingPointForMouse(dx, dy);
////		int index = (int)Math.floor(Math.random() * 7);
//		//getDrawer().drawPiece(image.getImage(), index, getDrawer().getCoordinate(drawingPoint));
//		GraphDrawer
//			.getDrawer(Entity.extractAnyEntity(Piece.getAllForType(Piece.TYPE_PLAYER)))
//			.drawPiece(image.getImage(), index, drawingPoint, new Point2D(60,60));
//	}
	public void test(Point2D drawingPoint) { // TODO - To be removed
		
//		GraphMenu mainFrame	= new GraphMenu();
//		mainFrame.setLocation(dx, dy);
//		mainFrame.setVisible( true );
		
//		Point2D drawingPoint = getDrawingPointForMouse(dx, dy);
//		int index = (int)Math.floor(Math.random() * 7);
		//getDrawer().drawPiece(image.getImage(), index, getDrawer().getCoordinate(drawingPoint));
		
//		getDrawer().drawPiece(image.getImage(), index, drawingPoint, new Point2D(60,60));
		
		Entity p = Entity.extractAnyEntity(Piece.getAllForType(Piece.TYPE_PLAYER));
		GraphDrawer.getDrawer(p).drawPiece(image.getImage(), p.getPropIndex(), drawingPoint, new Point2D(60,60));
	}

	public boolean isInsidePanel(int x, int y) {
		Dimension dim = p.getSize();
		return x > 0 && x < dim.width && y > 0 && y < dim.height;
	}
	/**
	 * Curiously this method should not be called internally
	 */
	public void repaint() {
		p.repaint();
	}
	public void play(String sound) {
//		p.play(codeBase, sound);
	}
	public void redraw(JPanel p, Graphics g) {
		BufferedImage im = image.getImage();
		g.drawImage(im, x, y, Math.round(im.getWidth() * zoom), Math.round(im.getHeight() * zoom), p);
	}
	
	// --- ZOOM ---
	
	public void checkZoom(int zoomx, int zoomy) {
		Dimension dim = panel.getSize();
		int imgw = Math.round((image.getImage().getWidth() - 1.8f) * zoom);
		int imgh = Math.round((image.getImage().getHeight() - 1.8f) * zoom);
		if (imgw < dim.getWidth() && imgh < dim.getHeight())
			zoom(ZOOM_IN_FACTOR, zoomx, zoomy);
	}
	private void zoom(float factor, int zoomx, int zoomy) {
		zoom *= factor;
		x = Math.round(x * factor + zoomx * (1 - factor));
		y = Math.round(y * factor + zoomy * (1 - factor));
	}
	public void updateZoom(int zoomx, int zoomy, int rotation) {
		if (rotation < 0)
			zoom(ZOOM_IN_FACTOR, zoomx, zoomy);
		else if (rotation > 0)
			zoom(ZOOM_OUT_FACTOR, zoomx, zoomy);
		checkZoom(zoomx, zoomy);
	}
	public void showMessage(Panel panel) {
		p.add("North", wrapInPanel(panel));
		panel.setVisible(true);
		p.invalidate();
		p.validate();
		p.repaint();
	}
	private static Panel wrapInPanel(Component component) {
	      Panel jPanel = new Panel();
	      jPanel.setBackground(new Color(50, 210, 250, 150));
	      jPanel.add(component);
	      return jPanel;
	  }
	
	
	public class Mover {
		
		int startx;
		int starty;
		int mousex;
		int mousey;
		public Mover(int startx, int starty, int mousex, int mousey) {
			this.startx = startx;
			this.starty = starty;
			this.mousex = mousex;
			this.mousey = mousey;
		}
		public void drag(int currentx, int currenty) {
			
			x = startx + currentx - mousex;
			y = starty + currenty - mousey;
			p.repaint();
		}
	}
}
