package com.libertymutual.blackjack.commands;

public class Player {
	private double roundBucket;
	private int numCardsInPlayersHand;
	private int playerBalance;
	public Hand playerHand;
	
	public Player(Hand playerHand, int playerBalance){
		this.playerBalance = 100;
		this.playerHand = playerHand;
	}

	public Hand getHand() {
		return playerHand;
	}
	
	public int getBalance() {
		return playerBalance;
	}
	
}