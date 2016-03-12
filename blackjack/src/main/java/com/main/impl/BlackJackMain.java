package com.main.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.main.entity.Card;
import com.main.entity.Player;
import com.main.utility.CardUtil;
import com.main.utility.blackjack.BlackjackUtil;

public class BlackJackMain {
	static BlackjackUtil blackjackutil = null;
	static Map<Player, List<Card>> playerCards = null;
	
	static int iterationCount = 0;
	static int noOfCardsAssigned = 0;
	static List<Card> cardList = null;
	public static void main(String[] args) {

		

		// step 1 initiate the players
		blackjackutil = new BlackjackUtil();
		blackjackutil.setPlayerCount();
		blackjackutil.setPlayerList(true);
		blackjackutil.setCardSet();
		System.out.println("Created the player list");
		// step2 initiate card deck and shuffle the card deck


		System.out.println("created/shuffled the card set");
		// step3 distribute the cards to the players

		playerCards = new HashMap<Player, List<Card>>();
		cardList = new ArrayList();


		do{//validate the card count incase if the list is empty reform the deck;
			// sub step 1 if it is first time distribution
			if (iterationCount == 0) {
				noOfCardsAssigned = 2;
				playerCards = blackjackutil.assignCardToAllPlayers(playerCards,noOfCardsAssigned);
				//System.out.println("created a Map between player and card");
				iterationCount++;
			}
			// sub step 2 if it is not the 1st time distribution
			if (iterationCount > 0) {

				noOfCardsAssigned = 1;

				System.out.println("in the   2nd sub Map between player and card");
				for (int cnt = 0; cnt < blackjackutil.getPlayerList().size(); cnt++) {

					playerCards = blackjackutil.assignCardToOnePlayer(playerCards,
							blackjackutil.getPlayerList().get(cnt), noOfCardsAssigned);
			

				}

			}

		
			printMap();
			blackjackutil.winnerList(playerCards);
			blackjackutil.ContinuePlay(playerCards);
			iterationCount=0;
			
		}while(playerCards.size()>1);
		if(playerCards.size()==1)
		{		blackjackutil.flush();
				playerCards.clear();
				
				blackjackutil=null;
				iterationCount = 0;
				noOfCardsAssigned = 0;
				cardList = null;
			System.out.println("Good Bye");
			
		}
	}

	

	private static void printMap() {
		for (int cnt = 0; cnt < blackjackutil.getPlayerList().size(); cnt++) {
			System.out.println("Player =" + blackjackutil.getPlayerList().get(cnt).getPlayer());

			for (int counter = 0; counter < playerCards.get(blackjackutil.getPlayerList().get(cnt)).size(); counter++) {
				System.out.println("Cards WithDrawn ="
						+ playerCards.get(blackjackutil.getPlayerList().get(cnt)).get(counter).getCardSetvalue()
						+ "Card Value is ="
						+ playerCards.get(blackjackutil.getPlayerList().get(cnt)).get(counter).getCardfacevalue());
			}
		}
		// TODO Auto-generated method stub

	}
}
