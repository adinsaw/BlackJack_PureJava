package com.main.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.main.entity.Card;

public class CardUtil {

	// create a set of 52 cards

	private List<Card> deck;

	public CardUtil() {
	}

	public List<Card> getDeck() {
		return deck;
	}

	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}

	public void formDeck() {
		deck = new ArrayList();
		for (int cntSet = 0; cntSet < 4; cntSet++) {
			for (int cnt = 0; cnt < 13; cnt++)

			{
				Card card = new Card(cntSet + 1, cnt + 1);
				deck.add(card);
			}
		}

	}

	public String cardSetValue(int cardSetValue) {
		switch (cardSetValue) {

		case 1:
			return "Spade";
		case 2:
			return "Heart";
		case 3:
			return "Diamond";
		default:
			return "Flower";
		}

	}

	public String cardFaceValue(int cardFaceValue) {
		switch (cardFaceValue) {
		case 1:
			return "one";
		case 2:
			return "two";
		case 3:
			return "three";
		case 4:
			return "four";
		case 5:
			return "five";
		case 6:
			return "six";
		case 7:
			return "seven";
		case 8:
			return "eight";
		case 9:
			return "nine";
		case 10:
			return "ten";
		case 11:
			return "jack";
		case 12:
			return "queen";
		case 13:
			return "king";
		case 14:
			return "aus";

		default:
			return "0";
		}

	}

	public static int cardScoreValue(int cardValue) {
		switch (cardValue) {

		case 1:
			return 1;
		case 2:
			return 2;
		case 3:
			return 3;
		case 4:
			return 4;
		case 5:
			return 5;
		case 6:
			return 6;
		case 7:
			return 7;
		case 8:
			return 8;
		case 9:
			return 9;
		case 10:
			return 10;
		case 11:
			return 10;
		case 12:
			return 10;
		case 13:
			return 10;
		case 14:
			return 11;
		default:
			return 0;
		}

	}

	public void shuffleDeck() {
		//System.out.println(deck.size());
		 for ( int i = deck.size()-1; i > 0; i-- ) {
	            int rand = (int)(Math.random()*(i+1));
	            Card temp = deck.get(i);
	            deck.remove(i);
	            deck.add(i, deck.get(rand));
	            deck.remove(rand);
	            deck.add(rand,temp);
			}//printcard();
		 
		 }

	public void printcard() {
		for (Card card : deck) {
			System.out.println(card.getCardSetvalue() + "CARD SET " + "CARD VALUE" + card.getCardfacevalue());
			;
		}
	}

	/**	public boolean validatecard(List<Card> card, Card currentCard) {
		for (Card currCard : card) {
			if (currCard.getCardSetvalue() == currentCard.getCardSetvalue()
					&& currCard.getCardfacevalue() == currentCard.getCardfacevalue()) {
				return true;
			}
		}
		return false;

	} */
}
