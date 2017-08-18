package com.libertymutual.blackjack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.libertymutual.blackjack.commands.AceCard;
import com.libertymutual.blackjack.commands.Round;

@SpringBootApplication
public class BlackjackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackjackApplication.class, args);
		AceCard card = new AceCard("Heart");
	
		Round round = new Round();
		round.startRound();
		System.out.println(round.getPlayerHand().getCards());
		
/*		Hand hand = new Hand();
		hand.addCard(new AceCard("Diamond"));
		hand.addCard(new NumberCard(3, "Heart"));
		
		Deck deck = new Deck();
		deck.shuffle();
//		deck.printThis();
*/		
	}

}
