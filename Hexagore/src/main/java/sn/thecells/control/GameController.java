package sn.thecells.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import sn.thecells.entity.Card;
import sn.thecells.entity.Entity;
import sn.thecells.entity.Opponent;
import sn.thecells.entity.Piece;
import sn.thecells.ui.MessagePanel;
import sn.thecells.ui.MultiChooser;
import sn.thecells.ui.TextInput;

public class GameController extends Thread {
//implements Runnable {

	private static GameController controller;
	public static void resumeGame() {
        synchronized(controller)
        {
        	controller.notify();
        }
	}
	public static void startGame(ActionController ac) {
		controller = new GameController(ac);
		controller.start();
	}
	private final ActionController ac;
	private GameController(ActionController ac){
		this.ac = ac;
	}
	
	@Override
    public void run() {
		// Prepare game deck!
		List<Card> allCards = Card.getAllCards();
		List<Card> gameDeck = extractSomeEntities(allCards, 40).stream().map(c -> (Card)c).collect(Collectors.toList());
		
		setup(); // Prepare the game!

	}
	private void setup() {
		// Welcome message
		MessagePanel mp;
		ac.showMessage(mp = new MessagePanel("Welcome to Hexagore!"));
		pauseGame();
		
		// Ask number of players
		MultiChooser mc;
		ac.showMessage(mc = new MultiChooser("Select players:", Piece.getAllForType(Piece.TYPE_PLAYER), Opponent.getAll(), ac.getDrawer()));
		pauseGame();
		Map<Entity, Entity> sel = mc.getSelection();
		sel.entrySet().stream().forEach(e -> ((Piece)e.getKey()).setOpponent((Opponent)e.getValue()));
		
		// Ask each human player for its name
		// Give random names to computer players
		List<String> randomNames = Opponent.getSomeNames();
		sel.keySet().stream().forEach(e -> {
			TextInput ti;
			Piece op = (Piece)e;
			ac.showMessage(ti = new TextInput("Player " + (op.id + 1) + " please enter your name:", op.getPieceIndex(), ac.getDrawer(), extractAny(randomNames)));
			pauseGame();
			op.setName(ti.getText());
		});
		
		sel = sel;
		
		
		// Prepare 
		
		// Draw market cards
		
		
		// Initial placement

		
	}
//	private void wait(Pannel pane, ActionController ac) {
//		Panel mp;
//		ac.showMessage(mp = new MessagePanel("Welcome to Hexagore!"));
//		while(mp.state == 0) {
//			Thread.sleep(100); 
//		};
//	}
//
//	@Override
//	public void run() {
//		Thread me = Thread.currentThread();
//		while (timer == me) {
//			ec.timerEvent();
//		    try {
//		    	Thread.sleep(1000);
//		    } catch (InterruptedException e) {
//		    	break;
//		    }
//		}
//		
//	}
    private void pauseGame()
    {
        try {
			synchronized(this)
			{
			    this.wait();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private String extractAny(List<String> ss) {
    	int index = (int)Math.floor(Math.random() * ss.size());
    	return ss.remove(index);
    }
    private Entity extractAnyEntity(List<? extends Entity> ss) {
    	int index = (int)Math.floor(Math.random() * ss.size());
    	return ss.remove(index);
    }
    private List<Entity> extractSomeEntities(List<? extends Entity> entities, int count){
    	List<Entity> result = new ArrayList<Entity>();
    	for (int i = 0; i < count && !entities.isEmpty(); i++)
    		result.add(extractAnyEntity(entities));
    	return result;
    }
}
