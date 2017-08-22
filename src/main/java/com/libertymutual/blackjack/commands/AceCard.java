package com.libertymutual.blackjack.commands;

public class AceCard implements Card {

	private String suit;

	public AceCard(String suit) {
		this.suit = suit;
	}

	public String getSuit() {
		return suit;
	}

	public String getName() {
		return "Ace";
	}

	public int[] getValues() {
		return new int[] { 1, 11 };
	}
	
	/*	public String getVisualRepresentation() {
		return "Ace";
		} */
	/*	public String toString() {
		return this.getVisualRepresentation() + " of " + this.getSuit();
		} */

}
