package game.card.game.Golf;

import communication.YesOrNo;
import game.Player;
import game.card.CardGame;
import game.card.entity.Card;
import game.card.entity.FaceCard;
import game.card.entity.CompetitorType;
import game.card.entity.Hand;
import java.util.Random;
import java.util.HashMap;

// https://en.wikipedia.org/wiki/Golf_(card_game)

public class Golf extends CardGame {

    protected static int noOfHands = 3;
    protected static int noOfCardsPerHand = 2;
    protected static int noOfVisibleCards = 2;
    protected static int computerMinScore = 5;
    protected static String cardToMakeVisible = "Select card to be visible";
    protected static HashMap<FaceCard, Integer> specialCards = new HashMap<FaceCard, Integer>();
    protected Random random = new Random();

    protected int computerSkill = 0;

    public Golf(String override) {
        super(override);
        specialCards.put(FaceCard.KING, 0);
        specialCards.put(FaceCard.TWO, -2);
        specialCards.put(FaceCard.ACE, 1);
    }

    public Golf() {
        this("");
    }

    protected void userPlaysCard(Player competitor, Card cardSelected, int userChoice) {
        Hand hand = null;
        int indexOfCard = 0;
        Card cardDiscarded = null;
        if (userChoice < 2) {
            hand = competitor.getHand();
            indexOfCard = userChoice;
        } else if (userChoice < 4) {
            hand = competitor.getHand(1);
            indexOfCard = userChoice - 2;
        } else if (userChoice < 6) {
            hand = competitor.getHand(1);
            indexOfCard = userChoice - 4;
        } else {
            cardDiscarded = cardSelected;
        }
        if (userChoice < 4) {
            cardDiscarded = hand.getCard(indexOfCard);
            hand.set(indexOfCard, cardSelected);
        }
        discardPile.add(cardDiscarded);
    }

    protected void checkIfKnock(Player player) {
        YesOrNo knockYes = inOut.getYesOrNo("Do you want to knock?");
        if (knockYes == YesOrNo.YES) {
            player.setWinner(true);
        }
    }

    protected void userPlays(Player competitor) {
        Card cardSelected = playerSelectCard(competitor, deck);
        displayCard("Selected card", cardSelected);
        int userChoice = getInputInteger("Which card to replace?");
        userPlaysCard(competitor, cardSelected, userChoice);
        checkIfKnock(competitor);
    }

    protected boolean hasBestScore(int bestScore, int currentScore) {
        boolean hasWon = false;
        if (bestScore < 0 || currentScore < bestScore) {
            hasWon = true;
        }
        return hasWon;
    }

    public int scoreHand(Hand hand) {
        int score = 0;
        if (hand.getCard(0).getFaceCard() != hand.getCard(1).getFaceCard()) {
            for (Card card : hand.getHandOfCards()) {
                if (specialCards.containsKey(card.getFaceCard())) {
                    score += specialCards.get(card.getFaceCard());
                } else {
                    score += card.getFaceCard().getValue();
                }
            }
        }
        return score;
    }

    

    protected void replaceCard(Hand hand, Card card, int index) {
        Card replaceCard = hand.getCard(index);
        hand.set(index, card);
        discardPile.add(replaceCard);
    }

    protected boolean compareCardWithHand(Hand hand, Card cardPlayed) {
        boolean matchedCard = false;
        Card computerCard = null;
        int counter = 0;
        while (!matchedCard && counter < hand.size()) {
            computerCard = hand.getCard(counter);
            if (cardPlayed.getFaceCard() == computerCard.getFaceCard()) {
                matchedCard = true;
                if (counter == 0) {
                    counter = 1;
                } else {
                    counter = 0;
                }
                replaceCard(hand, cardPlayed, counter);
                discardPile.add(computerCard);
            }
            counter++;
        }
        return matchedCard;
    }

    protected boolean compareCardForPlayer(Player player, Card cardPlayed) {
        boolean matchedCard = false;
        int counter = 0;
        while (!matchedCard && counter < player.getNumberOfHands()) {
            matchedCard = compareCardWithHand(player.getHand(counter), cardPlayed);
            counter++;
        }
        return matchedCard;
    }

    protected void computerPlayBasic(Player competitor) {
        Card card = discardPile.getLastCard();
        boolean matchedCard = compareCardForPlayer(competitor, card);
        if (!matchedCard) {
            card = deck.playACard();
            matchedCard = compareCardForPlayer(competitor, card);
        }
        if (!matchedCard) {
            discardPile.add(card);
        }
    }

    protected void computerPlays(Player player) {
        displayPlayerWithVisibility(player);
        computerPlayBasic(player);
        int score = getScore(player);
        player.setScore(score);
        if (score < computerSkill * computerMinScore) {
            player.setWinner(true);
        }
    }

    protected void makeCardsVisible(int[] cardIndexes, Player player) {
        for (int index : cardIndexes) {
            player.getHand((int) Math.floor(index / noOfCardsPerHand)).makeCardVisible(index % noOfCardsPerHand);
        }
    }

    protected void makeUserCardsVisible(Player player) {
        print(player.displayHandWithVisibility());
        int[] cardIndexes = new int[2];
        for (int counter = 0; counter < noOfVisibleCards; counter++) {
            cardIndexes[counter] = getInputInteger(cardToMakeVisible);
        }
        makeCardsVisible(cardIndexes, player);
    }

    protected void makeComputerCardsVisible(Player player) {
        int[] cardIndexes = new int[noOfVisibleCards];
        for (int counter = 0; counter < noOfVisibleCards; counter++) {
            cardIndexes[counter] = random.nextInt((noOfCardsPerHand * noOfHands) - 1);
        }
        makeCardsVisible(cardIndexes, player);
    }

    protected void makePlayersHandsVisible() {
        Player player = null;
        for (int counter = 0; counter < getPlayersSize(); counter++) {
            player = getPlayer(counter);
            if (player.getCompetitorType() == CompetitorType.USER) {
                makeUserCardsVisible(player);
            } else {
                makeComputerCardsVisible(player);
            }
        }
    }

    protected void afterInitiate() {
        discardPile.add(deck.playACard());
        computerSkill = getInputInteger("What skill of computer from 1 - 3 (1 is hard?");
        makePlayersHandsVisible();
        super.showPlayers();
    }

    protected void dealCardsToPlayer(Player player) {
        for (int counter = 0; counter < noOfHands; counter++) {
            player.addHand(dealHand(noOfCardsPerHand, false));
        }

    }

    protected void dealCards() {
        for (int counter = 0; counter < getPlayersSize(); counter++) {
            dealCardsToPlayer(getPlayer(counter));
        }
    }

    protected void showPlayers() {
        print("Game Over, below are the scores.");
        Player player = null;
        for (int counter = 0; counter < getPlayersSize(); counter++) {
            player = getPlayer(counter);
            displayPlayer(player);
        }
    }

    protected void playTillFinish() {
        int counterOfPlayer = 0;
        while (!getPlayer(counterOfPlayer).hasWon()) {
            playerPlaysHand(getPlayer(counterOfPlayer));
            counterOfPlayer = (counterOfPlayer + 1) % getPlayersSize();
        }
    }

    public static void main(String[] args) {
        Golf golf = new Golf();
        golf.play();
    }
}
