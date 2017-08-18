package com.libertymutual.blackjack.commands;

public class NumberCard implements Card{

	private int value;
	private String suit;

	public NumberCard(int value, String suit) {
		this.value = value;
		this.suit = suit;
	}

	@Override
	public String toString() {
		return this.getVisualRepresentation() + " of " + this.getSuit();
	}

	public int[] getValues() {
		return new int[] { value, value };
	}

	public String getSuit() {
		return suit;
	}
	
	public String getVisualRepresentation() {
		return String.valueOf(value);
	}
	
}