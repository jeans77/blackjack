package com.libertymutual.blackjack.commands;

public interface Card {
	
	String getSuit();

//	String getVisualRepresentation();
	
	String getName();

	int[] getValues();
	
	}