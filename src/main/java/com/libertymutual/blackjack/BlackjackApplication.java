package com.libertymutual.blackjack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.libertymutual.blackjack.commands.AceCard;
import com.libertymutual.blackjack.commands.Deck;
import com.libertymutual.blackjack.commands.Hand;
import com.libertymutual.blackjack.commands.NumberCard;

@SpringBootApplication
public class BlackjackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackjackApplication.class, args);
//		AceCard card = new AceCard("Heart");
//		NumberCard card2 = new NumberCard(2, "Heart");
//		System.out.println(card2);
//		
		
//		Round round = new Round();
//		round.startRound();
//		System.out.println(round.getPlayerHand().getCards());
//		
/*		Hand hand = new Hand();
		hand.addCard(new AceCard("Diamond"));
		hand.addCard(new NumberCard(3, "Heart"));
*/				
		Deck deck = new Deck();
		deck.shuffle();
//		deck.printThis();
		Hand hand = new Hand();
		
		
				}

}
