package com.main.utility.blackjack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.main.utility.*;
import com.main.entity.*;

public class BlackjackUtil {

	private static int lastCardAssignedCount = 0;
	private static int playerCount = 0;
	private static List<Player> playerList = null;
	private static Scanner userInput = new Scanner(System.in);
	static CardUtil cardSet = null;
	// define functions here

	public List<Player> getPlayerList() {
		return playerList;
	}

	// players creation
	public void setPlayerList(boolean dealertrue) {

		playerList = new ArrayList();
		System.out.println("Enter " + playerCount + " Names");
		for (int t = 0; t < playerCount; t++) {
			if (userInput.hasNextLine() == true) {
				Player player = new Player(userInput.nextLine());
				playerList.add(player);
			}
		}
		if (dealertrue == true) {
			Player dealer = new Player("DEALER");
			playerList.add(dealer);
		}

	}

	public static CardUtil getCardSet() {
		return cardSet;
	}

	public static void setCardSet() {
		cardSet = new CardUtil();
		cardSet.formDeck();
		cardSet.shuffleDeck();
	}

	public static int getLastCardAssignedCount() {
		return lastCardAssignedCount;
	}

	public static void setLastCardAssignedCount(int lastCardAssignedCount) {
		BlackjackUtil.lastCardAssignedCount = lastCardAssignedCount;
	}

	public static int getPlayerCount() {
		return playerCount;
	}

	// player count
	public static void setPlayerCount() {
		System.out.println("Enter  playercount ");
		Scanner inputplayercount = new Scanner(System.in);
		playerCount = inputplayercount.nextInt();

	}

	// calculate the score of the player set of cards
	public static int checkScore(Map<Player, List<Card>> playerCards, Player currPlayer) {

		int cardsetValue = 0;
		int currCardValue = 0;
		List<Card> card = null;
		card = playerCards.get(currPlayer);

		for (Card currCard : card) {
			currCardValue = currCard.getCardfacevalue();

			cardsetValue = cardsetValue + CardUtil.cardScoreValue(currCardValue);
			// System.out.println(cardsetValue);
		}
		return cardsetValue;
	}

	// assign cards to all the players.
	public static Map assignCardToAllPlayers(Map<Player, List<Card>> playerCards,
			int noOfCardsAssigned) {
		//Check  the last card has reached 52
		
		int iterationCount = noOfCardsAssigned;
if(lastCardAssignedCount==52){cardSet.formDeck();cardSet.shuffleDeck();lastCardAssignedCount=0;}
		for (Player player : playerList) {
			List<Card> cardList = new ArrayList();
			for (int cnt = 0; cnt < noOfCardsAssigned; cnt++) {
				cardList.add(cardSet.getDeck().get(lastCardAssignedCount));
				iterationCount++;
				lastCardAssignedCount++;
			}
			playerCards.put(player, cardList);

		}
		return playerCards;

	}

	// validate if the score is >=21
	public static boolean validateScore(int score) {
		if (score >= 21) {
			return true;
		} else
			return false;
	}
	// User input on whether to proceed or not

	public static int requestUserToProceed(Player player, int currScore) {
		if (player.getPlayer() != "DEALER") {

			System.out.println(player.getPlayer() + "   Your Score is " + currScore);
			System.out.println("Do you want to pick another card?-->Type Y for 1 and N for 0 ");
			return userInput.nextInt();
		}
		return 0;
	}

	// assign cards to each player.
	public static Map assignCardToOnePlayer(Map<Player, List<Card>> playerCards, Player player,
			int noOfCardsAssigned) {
	

		int checkCurScore = checkScore(playerCards, player);
		List<Card> cardList = playerCards.get(player);
		int gameContinue = requestUserToProceed(player, checkCurScore);
		String playerName = player.getPlayer();
		System.out.println(playerName);
		// used for count
		if(lastCardAssignedCount==52){cardSet.formDeck();cardSet.shuffleDeck();lastCardAssignedCount=0;}
		if (gameContinue == 1 && !(player.getPlayer().equals("DEALER")) && checkCurScore < 21) {
			System.out.println("ENTERD In the 2nd LOOP");

			int iterationCount = noOfCardsAssigned;

			for (int cnt = 0; cnt < noOfCardsAssigned; cnt++) {
				cardList.add(cardSet.getDeck().get(lastCardAssignedCount));
				iterationCount++;
				lastCardAssignedCount++;

			}

			playerCards.put(player, cardList);
			checkCurScore = checkScore(playerCards, player);
			if (checkCurScore == 21) {
				// System.out.println("Your Score is 21:");
			}
			if (checkCurScore > 21) {
				// System.out.println("you have crossed 21 below is your card
				// list");
				displayCardList(cardList);
				// System.out.println("Your Score has crossed 21:You Lose");
			}
			if (checkCurScore < 21) {
				assignCardToOnePlayer(playerCards, player, noOfCardsAssigned);
			}
		}
		if (player.getPlayer().equals("DEALER")) {
			int highestCardScore = 0;
			int checkDealerScore = checkScore(playerCards, player);
			// pick till the score reaches 21 or if the score is
			// greater than equal to the best user score
			// check the number of cards picked up by the users and the highest
			// score
			for (Player curPlayer : playerCards.keySet()) {
				if (checkScore(playerCards, curPlayer) <= 21) {
					if (highestCardScore < checkScore(playerCards, curPlayer)) {

						highestCardScore = checkScore(playerCards, curPlayer);

					}
				}
			}
			// System.out.println(checkDealerScore + "-HIGHESET SCORE IS " +
			// highestCardScore);
			if (checkDealerScore == 11) {
				for (Card curCard : cardList)

				{
					if (curCard.getCardfacevalue() == 1) {
						curCard.setCardfacevalue(14);
						checkCurScore = checkScore(playerCards, player);
						playerCards.put(player, cardList);
					}
				}
			}
			if (checkCurScore < highestCardScore) {

				for (int cnt = 0; cnt < noOfCardsAssigned; cnt++) {
					cardList.add(cardSet.getDeck().get(lastCardAssignedCount));

					lastCardAssignedCount++;
				}
				playerCards.put(player, cardList);
			}
			checkCurScore = checkScore(playerCards, player);
			if (checkCurScore == 21 || checkCurScore >= highestCardScore) {
				System.out.println("Dealer score is :" + checkCurScore);
			}
			if (checkCurScore > 21) {
				// System.out.println("Dealer has crossed 21 below is your card
				// list");
				// displayCardList(cardList);
				// System.out.println("Dealer Score has crossed 21:You Lose");
			}
			if (checkCurScore < 21 && checkCurScore < highestCardScore) {
				assignCardToOnePlayer(playerCards, player, noOfCardsAssigned);
			}
		}

		return playerCards;

	}

	// display the cardlist here
	public static void displayCardList(List<Card> cardList) {
		for (Card curCard : cardList) {
			System.out.println(
					"Card Set Value=" + curCard.getCardSetvalue() + "Card Face Value=" + curCard.getCardfacevalue());
		}
	}

	// determine who has won
	public static void winnerList(Map<Player, List<Card>> playerCards) {
		List<String> playerList = new ArrayList();
		int highestScore = 0;
		for (Player curPlayer : playerCards.keySet()) {
			int curPlayerScore = checkScore(playerCards, curPlayer);

			if (curPlayerScore > 21) {
				System.out.println("Player : " + curPlayer.getPlayer() + " Lost");
			}

			if (checkScore(playerCards, curPlayer) <= 21) {
				if (highestScore == curPlayerScore) {
					highestScore = curPlayerScore;
					playerList.add(curPlayer.getPlayer());
				}
				if (highestScore < curPlayerScore) {
					highestScore = curPlayerScore;
					playerList.clear();
					playerList.add(curPlayer.getPlayer());
				}

			}

		}

		for (String playerName : playerList) {
			System.out.println("player=" + playerName + " Has Won" + "with Score =" + highestScore);
		}

	}

	// continue game .
	public Map ContinuePlay(Map<Player, List<Card>> playerCards) {
		List<Player> removalList = new ArrayList();
		for (Player player : playerCards.keySet()) {
			List<Card> cardList = playerCards.get(player);
			cardList.clear();
			if (!(player.getPlayer().equals("DEALER"))) {

				System.out.println("Player:" + player.getPlayer() + "-Want to Continue?:If Yes type 1 else 0");
				if (userInput.nextInt() == 1) {
					playerCards.put(player, cardList);
				} else {
					removalList.add(player);
				}
			}
			if ((player.getPlayer().equals("DEALER"))) {
				playerCards.put(player, cardList);
			}
		}
		for (Player curPlayer : removalList) {
			playerCards.remove(curPlayer);
			playerList.remove(curPlayer);
		}
		for (Player curPlayer : playerCards.keySet()) {
			System.out.println("REMAINING PLAYERS IN MAP=" + curPlayer.getPlayer());
		}

		return playerCards;
	}

	// flush and reset as the game is over.
	public void flush() {

		lastCardAssignedCount = 0;
		playerCount = 0;
		playerList = null;
		cardSet=null;
		userInput.close();

	}

	// Handling Aus
	public int firstCardValidatorForUser()

	{

		System.out.println("Should AUS  be 1 or 11 type specific value");

		return userInput.nextInt();
	}
	

}
