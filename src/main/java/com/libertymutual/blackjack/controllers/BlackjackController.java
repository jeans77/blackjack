package com.libertymutual.blackjack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.libertymutual.blackjack.commands.Deck;
import com.libertymutual.blackjack.commands.Hand;
import com.libertymutual.blackjack.commands.Round;

@Controller
@RequestMapping("/")

public class BlackjackController<PlayerHand> {
	Hand playerHand = new Hand();
	Hand dealerHand = new Hand();
	int[] values;
	
	/* initialize Round */
	public BlackjackController() {
			
		Deck deck = new Deck();
		deck.shuffle();
//		deck.printThis();

	}
	
	@GetMapping("")
	public ModelAndView startRound () {
		ModelAndView modelAndView = new ModelAndView("blackjack/initial");
	
		modelAndView.addObject("playerHand", playerHand );
		modelAndView.addObject("dealerHand", dealerHand );
		
//		Round round = new Round (PlayerHand[] playerHand);
		
		return modelAndView;
	}
	
	@PostMapping("")
	public String addTheCardToHand() {
		return "redirect:/";
	}

	@PostMapping("initial")
	public String startGame() {
		Round round = new Round();
		round.startRound();
		return "blackjack/initial";
	}
	
	@PostMapping("play")
	public String playGame() {
		//control bet not null amount less than his balance//
		return "blackjack/run";
	}
	
	@PostMapping("run")
	public String run() {
		return "blackjack/run";
	}
	
	
	@PostMapping("hit")
	public String pushHitButton() {
	//place bet
		
		return "redirect:/";
	}
		
	@PostMapping("stand")
	public String pushStandButton() {
		
		return "redirect:/";
	}
	
	@PostMapping("surrender")
	public String pushSurrenderButton() {
		
		
		return "redirect:/";
	}
	
/*	public String actionPerformed (@RequestParam(name="left") double first, @RequestParam(name="right") double second, Model model, String action) {
	}
		public void actionPerformed() {
        String command = getActionCommand();
        if (command.equals("Hit!"))
           doHit();
        else if (command.equals("Stand!"))
           doStand();
        else if (command.equals("New Game"))
           doNewGame();
		return action1;
	}
*/


/*		private String getActionCommand() {
			// TODO Auto-generated method stub
			return null;
		}
*/
	
/*	@PostMapping("blackjack/hand")

	Hand hand = new Hand();
	hand.addCart(new AceCard("Heart"));
	hand.addCart(new NumberCard(2, "Diamond"));
	hand.printThis();
	
	public ModelAndView showHand() {
		ModelAndView.addObject("hand", );
	
		return "redirect:/";
	}
	
}
*/

}