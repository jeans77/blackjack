package com.libertymutual.blackjack.commands;

import java.util.ArrayList;

public class Hand {
		private ArrayList<Card> cards;
		public Hand () {
			cards = new ArrayList<Card>();
		}

		public void removeCard(Card card) {
			cards.remove(card);
		}
		
		public void addCard(Card card) {
			cards.add(card);
		}

		public Card getCard(int cardIndex) {
			return cards.get(cardIndex);
		}

		public ArrayList<String> getAllCards() {
			ArrayList<String> cardsList = new ArrayList<String>();
			for (Card c : cards) {
				cardsList.add(c.getName());
			}
//											System.out.println("--> Cards in Hand:" + "CardList: " + cardsList + "Cards: " + cards);
		return cardsList;
		}
		
		
		public void clearHand() {
			while (!cards.isEmpty()) {
				cards.remove(0);
			}
		}

		public int [] getHandValue() {
			int[] handValue = new int[] {0,0};
			for (Card c: cards) {
				int[] values = c.getValues();
				handValue[0] += values[0];
				handValue[1] += values[1];	
				}
			return handValue;
		}

		public int getHandBestValue() {
			int[] handValue = new int[] {0,0};
			int handBestValue = 0;
			for (Card c: cards) {
				int[] values = c.getValues();
				handValue[0] += values[0];
				handValue[1] += values[1];
				}
				if (handValue[0] <= 21) {
					handBestValue = handValue[0];
				}
				if (handValue[1] <= 21 && handValue[1] > handBestValue) {
					handBestValue = handValue[1];
				}
			return handBestValue;	
			}

	}