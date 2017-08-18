package com.libertymutual.blackjack.commands;

public class Round {

	public Hand playerHand;
	public Hand getPlayerHand() {
		return playerHand;
	}

	public Hand getDealerHand() {
		return dealerHand;
	}

	public Hand dealerHand;
	
	private int currrentCardIndex;


	public void startRound() {
		playerHand = new Hand();
		dealerHand = new Hand();
//		playerHand.addCard(card);
	}
	
	public static void addCardToPlayerHand() {
//		playerHand.addCard(new AceCard("Heart"));
//		playerHand.addCard(new NumberCard(2, "Heart"));
	}
	
	public static void addCardToDealerHand() {
//		dealerHand.addCard(new AceCard("Heart"));
//		dealerHand.addCard(new NumberCard(2, "Heart"));
	}

	//End a Round
/*	private static void endRound(CardHand playerHand, CardHand dealerHand, int balance, int bet) {
		if(playerHand.handValue() == dealerHand.handValue())
			// push
		else if(playerHand.handValue() > dealerHand.handValue())
			// player wins
		else
			// player loses
			
		// print current balance
	}
	}
*/
}