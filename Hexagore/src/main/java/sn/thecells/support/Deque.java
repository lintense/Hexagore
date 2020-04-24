package sn.thecells.support;

import java.util.List;
import java.util.stream.Collectors;

import sn.thecells.entity.Card;
import sn.thecells.entity.Entity;

public class Deque {

	
	private String label;
	private List<Card> cards;
	
	public Deque(String label, List<Card> cards) {
		super();
		this.label = label;
		this.cards = cards;
	}
	
	public List<Card> draw(int count){
		return Entity.extractSomeEntities(cards, count).stream().map(c -> (Card)c).collect(Collectors.toList());
	}
}
