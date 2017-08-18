package com.libertymutual.blackjack.commands;

import java.util.List;
import java.util.ArrayList;

public class Hand {

	List<Card> cards = new ArrayList<>();
	
//	private int value = 0;
	
	/* Initiating a new hand */	
	public Hand() {
//		cards = new ArrayList<Card>();
//		this.value   = 0;
	}

	/* Add a card to the list of cards in hand and find its position*/
	public void addCard(Card card) {
		cards.add(card);
//		this.value += card.position().getValue();
	}
	
	/* Return the size of the hand */	
	public static int size() {
		return Hand.size();
	}

	/* Return the total value of the hand */	
	public int[] getValues() {
		int[] sums = new int[] { 0, 0 };
		
		for (Card c : cards) {
			int[] values = c.getValues();
			sums[0] += values[0];
			sums[1] += values[1];
		}
		
		return sums;
	}
	
	public List<Card> getCards() {
		return cards;
	}
	
	public void printThis() {
		for (Card card : cards) {
			System.out.println(card);
		}
		
	}
	
}
