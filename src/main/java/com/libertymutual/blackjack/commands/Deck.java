package com.libertymutual.blackjack.commands;

public class Deck {

	private Card[] cards;
	private int currrentCardIndex;
	private boolean deckHasCards = true;
	
	public Deck() {
		String[] suits = new String[] { "Club", "Heart", "Diamonds", "Spade" };
		int i = 0;
		cards = new Card[52];
		currrentCardIndex = 51;
		
		for (String suit : suits) {
			cards[i] = new AceCard(suit);
			i += 1;
			
			cards[i] = new FaceCard("Jack", suit);
			i += 1;
			
			cards[i] = new FaceCard("Queen", suit);
			i += 1;

			cards[i] = new FaceCard("King", suit);
			i += 1;
			
			for (int j = 2; j < 11; j += 1) {
				cards[i] = new NumberCard(j, suit);
				i += 1;
			}
		}
	}
	
	public void printThis() {
		for (Card card : cards) {
																			System.out.println("Deck: " + card.getName() + " " + card.getSuit());
		}
	}
		
	public void shuffle() {
		for (int doThisSevenTimes = 0; doThisSevenTimes < 7; doThisSevenTimes += 1) {			
			Card[] tempCardHolder1 = new Card[26];
			Card[] tempCardHolder2 = new Card[26];
			
			for (int i = 0; i < tempCardHolder1.length; i += 1) {
				tempCardHolder1[i] = cards[i];
				tempCardHolder2[i] = cards[26 + i];
			}
			
			int holder1Index = 0;
			int holder2Index = 0;
			int overallIndex = 0;
			
			while (holder1Index < 26 || holder2Index < 26) {
				Card cardToMove;
				if (Math.random() < 0.5) {
					if (holder1Index < 26) {
						cardToMove = tempCardHolder1[holder1Index];
						holder1Index += 1;
					} else {
						cardToMove = tempCardHolder2[holder2Index];
						holder2Index += 1;
					}
				} else {
					if (holder2Index < 26) {
						cardToMove = tempCardHolder2[holder2Index];
						holder2Index += 1;
					} else {
						cardToMove = tempCardHolder1[holder1Index];
						holder1Index += 1;
					}
				}
				cards[overallIndex] = cardToMove;
				overallIndex += 1;
			}
		}
	}
	

	public Card dealCard() {
		currrentCardIndex -= 1;
		return cards[currrentCardIndex + 1];
	}

	public boolean deckHasCards () {
			if 	(currrentCardIndex < 1) {
				return false;
			} else
			return true;
	}
}	
