package com.libertymutual.blackjack.commands;

public class Dealer {

		public Hand dealerHand;
		public Dealer(Hand dealerHand) {
			this.dealerHand = dealerHand;
		}
		
		public void setInitialHand(Hand dealerHand)	{
			this.dealerHand = dealerHand;
		}
		
		public void play()	{	
		}

		public void initHand(Deck deck) {
			// TODO Auto-generated method stub
		}
		
		public Hand getHand() {
			return dealerHand;
		}

	}
