package com.libertymutual.blackjack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libertymutual.blackjack.commands.Dealer;
import com.libertymutual.blackjack.commands.Deck;
import com.libertymutual.blackjack.commands.Hand;
import com.libertymutual.blackjack.commands.Player;


@Controller
@RequestMapping({"/","blackjack"})

public class BlackjackController {
	
	private Deck deck = new Deck();		/* Deck object includes ............*/
	private Dealer dealer;				/* Dealer Object includes ......*/
	private Player player;				/*Player Object */
	private int betAmount;				/* Bet entered by Player */
	boolean showPlayerButtons;			/* Whether Player Buttons should be active */
	boolean dealerCardHole;
	boolean dealerCardShow;				/* Whether player card is Show or Hole */
	int dealerBestScore = 0;			/* stores the best of dealer two hand values*/
	int playerBestScore = 0;			/* stores the best of player two hand values*/
	private String roundOutcome;
	int roundBucket = 0;
	int playerBalance = 100;
	
	//Gets
	@GetMapping("")
		public String home() {
		return "blackjack/start";
	}
	
	@GetMapping("initial")
		public String showPlayPage() {
		return "blackjack/initial";
	}
	
	@GetMapping("game")
		public String showGamePage(Model model) {
		model.addAttribute("playerBalance", playerBalance);
		model.addAttribute("betAmount", betAmount);
		model.addAttribute("dealerCard1", dealer.dealerHand.getCard(0).getName());
		model.addAttribute("dealerCard2", dealer.dealerHand.getCard(1).getName());
		model.addAttribute("dealerCardHole", dealerCardHole);
		model.addAttribute("dealerCardShow" , dealerCardShow);
		model.addAttribute("playerCards", player.playerHand.getAllCards());
		model.addAttribute("playerHandValue", player.playerHand.getHandValue());
//		model.addAttribute("playerCardValues", player.playerHand.getCardValue());
		model.addAttribute("showPlayerButtons" , showPlayerButtons);
		return "blackjack/game";
	}
	
	@PostMapping("")
		public String backToHome() {
		return "blackjack/start";
	}
	
	@PostMapping("game/start")
		public String startGame() {
		deck.shuffle();
		Hand dealerHand = new Hand();
		player = null;
		dealer = new Dealer (dealerHand);
		showPlayerButtons = true;
		return "blackjack/new-game";
	}
	
	@PostMapping("game/bet")
		public String pushButtonBet(int betAmount, Model model) {
		// Setting the whole Game Table
		//Create Hand (empty) for both player and dealer 
		Hand playerHand = new Hand();
		Hand dealerHand = new Hand();
		player = new Player(playerHand, 100);
		player.playerHand.clearHand();
		dealer.dealerHand.clearHand();
		player.getHand();
		dealer.dealerHand.addCard(deck.dealCard());
		dealer.dealerHand.addCard(deck.dealCard());
		player.playerHand.addCard(deck.dealCard());
		player.playerHand.addCard(deck.dealCard());
		this.betAmount = betAmount;
		
		//Not enough Money to Bet this amount
		if (betAmount > playerBalance) {
			model.addAttribute("roundOutcome", "Not Enough Money for This Bet");
			showPlayerButtons = false;
			model.addAttribute("showPlayerButtons", showPlayerButtons);
			model.addAttribute("playerBalance", playerBalance);
			model.addAttribute("betAmount", betAmount);
			return "blackjack/round-over";
		}
		
		if (betAmount == 0) {
			model.addAttribute("roundOutcome", "Need real Money");
			showPlayerButtons = false;
			model.addAttribute("showPlayerButtons", showPlayerButtons);
			model.addAttribute("playerBalance", playerBalance);
			model.addAttribute("betAmount", betAmount);
			return "blackjack//round-over";
		}
		
		if (betAmount < 0) {
			model.addAttribute("roundOutcome", "Bitcoins not accepted");
			showPlayerButtons = false;
			model.addAttribute("showPlayerButtons", showPlayerButtons);
			model.addAttribute("playerBalance", playerBalance);
			model.addAttribute("betAmount", betAmount);
			return "blackjack//round-over";
		}
		dealerCardHole = true;
		dealerCardShow = false;
		return "redirect:/game";
		

	}

	@PostMapping("game/hit")
		public String pushHitButton(Model model) {
		roundBucket = 0;
		int [] handValue = player.playerHand.getHandValue();									
		if (!deck.deckHasCards()) {
			return "redirect:/game/zero-cards";
			}
		player.playerHand.addCard(deck.dealCard());
		handValue = player.playerHand.getHandValue();
		playerBestScore = player.playerHand.getHandBestValue();
		//Player Busted								
		if (handValue[0] > 21 && handValue[1] > 21) {
			roundBucket = -1 * betAmount;
			playerBalance = playerBalance + roundBucket;
							System.out.println("--> POST /game/hit Player -Bal: " + playerBalance + " -Bucket:" + roundBucket + " -bet:" + betAmount);
			model.addAttribute("playerBalance", playerBalance);
			model.addAttribute("betAmount", betAmount);
			model.addAttribute("playerCards", player.playerHand.getAllCards());
			model.addAttribute("dealerCards", dealer.dealerHand.getAllCards());
			model.addAttribute("roundOutcome", "Player Busted");
			model.addAttribute("showPlayeButton", showPlayerButtons);
							System.out.println("--> POST /game/hit Player -Bal: " + playerBalance + " -Bucket:" + roundBucket + " -bet:" + betAmount);
			return "blackjack/round-over";
			}
		
		//Next Action
		return "redirect:/game";
	}
	
	@PostMapping("game/stand")
		public String pushStandButton(Model model) {
		roundBucket = 0;
		int[] handValue = dealer.dealerHand.getHandValue();
		dealerBestScore = dealer.dealerHand.getHandBestValue();
			//Dealer to Draw cards until 17 or More
			while (handValue[0] < 17 || handValue[1] < 17) {
				if (!deck.deckHasCards()) {
					return "redirect:/game/zero-cards";
				}
			dealer.dealerHand.addCard(deck.dealCard());
			handValue = dealer.dealerHand.getHandValue();
			dealerBestScore = dealer.dealerHand.getHandBestValue();
			}
			model.addAttribute("playerCards", player.playerHand.getAllCards());
			model.addAttribute("dealerCards", dealer.dealerHand.getAllCards());
			
			//Dealer alackJack
			if (dealerBestScore == 21) {
				model.addAttribute("roundOutcome", "Blackjack Dealer");
				roundBucket = -1 * betAmount;
			}
			
			//Player BalckJack
			int playerHandSize = player.playerHand.getHandSize();
			if (dealerBestScore == 21 && playerHandSize == 2 ) {
				model.addAttribute("roundOutcome", "Blackjack Dealer");
				roundBucket = betAmount * 3 / 2;
			}
			
			//Dealer Busted
			if (dealerBestScore == 0) {
				model.addAttribute("roundOutcome", "Dealer Busted");
				roundBucket = betAmount;
			}
			
			//Dealer Wins
			if (dealerBestScore > playerBestScore) {
				model.addAttribute("roundOutcome", "Dealer Wins");
				roundBucket = -1 * betAmount;
			}

			//Player Wins
			if (dealerBestScore < playerBestScore) {
				model.addAttribute("roundOutcome", "Player Wins");
				roundBucket = betAmount;
			}
			
			//Draw
			if (dealerBestScore == playerBestScore) {
				model.addAttribute("roundOutcome", "Player Wins");
				roundBucket = 0;
			}
			
			playerBalance = playerBalance + roundBucket;
			model.addAttribute("playerBalance", playerBalance);
			model.addAttribute("betAmount", betAmount);
			return "blackjack/round-over";	
		}
		
		@GetMapping("/game/zero-cards")
		public String zeroCardsLeft(Model model) {
			showPlayerButtons = false;
			model.addAttribute("playerBalance", playerBalance);
			model.addAttribute("betAmount", betAmount);
			model.addAttribute("playerCards", player.playerHand.getAllCards());
			model.addAttribute("dealerCards", dealer.dealerHand.getAllCards());
			model.addAttribute("roundOutcome", "No Cards Left");
			model.addAttribute("showPlayerButtons", showPlayerButtons);
			return "blackjack/game-over";
		}
		
		@GetMapping("/game/notEnough-balance")
		public String notEnoughBalance(Model model) {
		System.out.println("--> GET /game/notEnough-balance");
		showPlayerButtons = false;
		model.addAttribute("playerBalance", playerBalance);
		model.addAttribute("betAmount", betAmount);
		model.addAttribute("playerCards", player.playerHand.getAllCards());
		model.addAttribute("dealerCards", dealer.dealerHand.getAllCards());
		model.addAttribute("roundOutcome", "Not Enough Money for This Bet");
		model.addAttribute("showPlayerButtons", showPlayerButtons);
		return "blackjack/game-over";
		}
	
		@GetMapping("/game/round-over")
		public String endGame(Model model) {
				model.addAttribute("playerBalance", playerBalance);
				model.addAttribute("betAmount", betAmount);
				model.addAttribute("playerCards", player.playerHand.getAllCards());
				model.addAttribute("dealerCards", dealer.dealerHand.getAllCards());
				model.addAttribute("showPlayerButtons", showPlayerButtons);
				return "blackjack/end-hand";

		}
}