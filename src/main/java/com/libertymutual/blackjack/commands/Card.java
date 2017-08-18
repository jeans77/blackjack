package com.libertymutual.blackjack.commands;

public interface Card {

	String getSuit();

	String getVisualRepresentation();

	int[] getValues();

//	public static int position();
//		return position + 1;
	
	}