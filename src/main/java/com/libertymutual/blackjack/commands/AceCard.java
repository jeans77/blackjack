package com.libertymutual.blackjack.commands;

public class AceCard implements Card {

	private String suit;

	public AceCard(String suit) {
		this.suit = suit;
	}
	
	@Override
	public String toString() {
		return this.getVisualRepresentation() + " of " + this.getSuit();
	}


	@Override
	public String getSuit() {
		return suit;
	}
	

	@Override
	public String getVisualRepresentation() {
		return "Ace";
	}
	

	@Override
	public int[] getValues() {
		return new int[] { 1, 11 };
	}
	
}
