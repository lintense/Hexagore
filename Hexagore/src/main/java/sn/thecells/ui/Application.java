package sn.thecells.ui;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import sn.thecells.control.ActionController;
import sn.thecells.control.EventController;
import sn.thecells.control.GameController;

public class Application extends Frame implements Runnable {

	ActionController ac;
	EventController ec;
	
	GraphPanel panel;
	Panel controlPanel;
	Thread timer;
	
	Panel message;
	
	public static void main(String[] args) {

//		try {
			Logger logger = java.util.logging.Logger.getAnonymousLogger();
			logger.setLevel(Level.WARNING);
		
			System.out.println(System.getProperty("java.class.path"));
			System.out.println(System.getProperty("java.library.path"));
			
//			URL url = Application.class.getClassLoader().getResource("..\\img\\board.png");
//			File file = new File(url.getFile());
			
//			String path = new File(".").getCanonicalPath() + "/bin/";
//			System.out.println(path.toString());
//			URL codeBase = new URL("file://" + path.toString());
			
			Application app = new Application();
			app.init();
			app.setVisible(true);
			
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	/**
	 * Graph constructor comment.
	 */
	public Application() {
		super("Hexagore");
	}
	public void destroy() {
		remove(panel);
		remove(controlPanel);
	}
	public String getAppletInfo() {
		return "Hexagore: Adventure game \nAuthor: Lintense 2020";
	}

	public void init() {
		
		
		ac = new ActionController(this);
		ec = new EventController(ac);
		this.addComponentListener(ec);
		addWindowListener(ec);

		setLayout(new BorderLayout());
		add("Center", panel = new GraphPanel(ec, ac));
		
//		setLayout(new GridBagLayout());
//		GridBagConstraints gbc = new GridBagConstraints();
//		gbc.anchor = GridBagConstraints.CENTER;
//		gbc.fill = GridBagConstraints.BOTH;
//		add(panel = new GraphPanel(ec, ac), gbc);
		
		
//		add("South", controlPanel = new Panel());
//		add("North", new GraphMenu());
		setSize(450, 450);
		ac.init(panel);
		
		
		GameController.startGame(ac);

//		gbc.anchor = GridBagConstraints.NORTH;
//		gbc.fill = GridBagConstraints.HORIZONTAL;
		
//		message.setVisible(true);

//		Hexagon.getHexes().forEach(hex -> ac.test(hex.x, hex.y));
	}
	public void run() {
		Thread me = Thread.currentThread();
		while (timer == me) {
			ec.timerEvent();
		    try {
		    	Thread.sleep(1000);
		    } catch (InterruptedException e) {
		    	break;
		    }
		}
	}
	public void start() {
		timer = new Thread(this);
		timer.start();
	}
	public void stop() {
		timer = null;
	}
	public void repaint() {
		panel.repaint();
	}
	
	


}
