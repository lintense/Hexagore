package sn.thecells.control;

import java.awt.Panel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import sn.thecells.entity.Card;
import sn.thecells.entity.Crest;
import sn.thecells.entity.Entity;
import sn.thecells.entity.Opponent;
import sn.thecells.entity.Piece;
import sn.thecells.support.Common;
import sn.thecells.support.Context;
import sn.thecells.support.Deque;
import sn.thecells.support.Label;
import sn.thecells.support.Player;
import sn.thecells.ui.MessagePanel;
import sn.thecells.ui.MultiChooser;
import sn.thecells.ui.SingleChooser;
import sn.thecells.ui.TextInput;

public class GameController extends Thread {
//implements Runnable {

	private static int TOTAL_CARDS = 40;
	private static int MARKET_CARDS = 5;
	private static int PLAYER_HAND = 5;
	
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
		
		Context ctx = new Context();
		setupGame(ctx);
		setupPlayers(ctx);

	}
	private void setupGame(Context ctx) {
		// Prepare game deck!
		// Get all cards but the starting player cards
		List<Card> allCards = Card.getAllCards().stream().filter(c -> !c.startingCards).collect(Collectors.toList());
		// Keep only the required amount while shuffling
		List<Card> gameDraw = Entity.extractSomeEntities(allCards, TOTAL_CARDS).stream().map(c -> (Card)c).collect(Collectors.toList());
		ctx.setDraw(new Deque("game.draw", gameDraw));
		// Extract the market from the draw
		ctx.setMarket(new Deque("game.market", ctx.getDraw().draw(MARKET_CARDS)));
		ctx.setTrash(new Deque("game.trash", new ArrayList<Card>()));
	}
	private void setupPlayers(Context ctx) {
		// Welcome message
		showMessage(new MessagePanel(Label.get("welcome.message")));
		
		// Ask number of players
		MultiChooser mc;
		showMessage(mc = new MultiChooser(Label.get("player.selection"), Piece.getAllForType(Piece.TYPE_PLAYER), Opponent.getAll()));
		Map<Entity, Entity> sel = mc.getSelection();
		ctx.setPlayers(new ArrayList<Player>());
		sel.entrySet().stream().forEach(e -> ctx.getPlayers().add(new Player((Piece)e.getKey(), (Opponent)e.getValue())));
		
		// Ask each human player for its name
		// Give random names to computer players
		List<String> randomNames = getSomeNames();
		List<Crest> availableCrests = Crest.getAll();
		int i = 1;
		for (Player player : ctx.getPlayers()) {
			// Select name
			TextInput ti;
			showMessage(ti = new TextInput(Label.get("ask.enter.player$0.name", Integer.toString(i)), Common.extractAny(randomNames), player.getPiece()));
			player.setName(ti.getText());
			
			// Setup player deques
			player.setDraw(new Deque("player.draw", Card.getPlayerDeck()));
			player.setHand(new Deque("player.hand", player.getDraw().draw(PLAYER_HAND)));
			player.setDiscard(new Deque("player.trash", new ArrayList<Card>()));
			
			// Select crest
			SingleChooser sc;
			showMessage(sc = new SingleChooser(Label.get("player.name$0.crest.selection", player.getName()), availableCrests));
			availableCrests.remove(sc.getSelection());
			player.setCrest((Crest)sc.getSelection());
			
			// HexagoneChooser Label.get("player.name$0.village.selection", player.getName())
		}
		
		
		
		sel = sel; // TODO Use a context
		
		
		// Prepare 
		
		// Draw market cards
		
		
		// Initial placement

		
	}
	private List<String> getSomeNames() {
		return new ArrayList<String>(Arrays.asList(Label.get("Opponent.names").split(",")));
	}
	private void showMessage(Panel panel) { // Combine showMessage and Panel creation to directly return the selection
		ac.showMessage(panel);
		pauseGame();
	}
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

}
