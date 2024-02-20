package game.card.game.GinRummy;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import game.Player;
import game.card.CardGame;
import game.card.entity.Card;
import game.card.entity.Deck;
import game.card.entity.FaceCard;
import game.card.entity.Hand;


public class GinRummy extends CardGame {

    protected static int noOfCards = 7;

    ScoreTrickHand scoreTrickHand = new ScoreTrickHand();

    public GinRummy() {
        this("");
    }

    public GinRummy(String override) {
        this.deck = new Deck(override);
    }

    protected void discardCard(Player player){
        Card card = getPlayersCard(player);
        addToDiscarded(card);
    }

    protected void userPlays(Player competitor){
        competitor.addCard(playerSelectCard(competitor, deck));
        discardCard(competitor);
    }

    protected Card removeWeakestCard(Hand hand){
        int currentScore = scoreHand(hand);
        Card card = null;
        
        List<Card> listOfCards = hand.getHandOfCards();
        for (Card cardToCheck : listOfCards) {
            Hand clonedHand = hand.copy();
            clonedHand.remove(cardToCheck);
            int newScore = scoreHand(clonedHand);
            if (currentScore == newScore && card == null) {
                card = cardToCheck;
            }
        }
        hand.remove(card);
        return card;
    }

    protected Card scoreWithCard(Hand hand, Card card){
        int currentScore = scoreHand(hand);
        hand.add(card);
        int scoreWithCard = scoreHand(hand);
        if (currentScore == scoreWithCard) {
            hand.remove(card);
            return card;
        } else {
            card = removeWeakestCard(hand);
        }
        return card;
    }

    protected void computerPlays(Player player){
        Hand hand = player.getHand();
        
        Card card = discardPile.getLastCard();
        Card removedCard = scoreWithCard(hand, card);
        if (card == removedCard) {
            card = deck.playACard();
            card = scoreWithCard(hand, card);
        }
        discardPile.add(card);
    }

    public int scoreHand(Hand hand){
        return maxSameFaceCardSize(hand) + maxStraightSuitSize(hand);
    }

    protected void beforeInitiate(){
        setNoOfCards(noOfCards);
    }

    protected void afterInitiate(){
        Player leftOfDealer = getPlayer(1);
        leftOfDealer.addCard(deck.playACard());
        discardCard(leftOfDealer);
    }

    protected int maxStraightSuitSize(Hand hand){
        int score = 0;
        List<Hand> sameSuitCards = scoreTrickHand.getStraightSameSuit(hand);
        for (Hand cards : sameSuitCards) {
            int currentStraight = 1;
            cards.sortHand();
            for (int i = 1; i < cards.size(); i++) {
                if (cards.getCard(i-1).getFaceCard().getValue() + 1 == cards.getCard(i).getFaceCard().getValue()) {
                    currentStraight++;
                } else {
                    if (currentStraight > score) {
                        score = currentStraight;
                    }
                    currentStraight = 1;
                }
            }
            if (currentStraight > score) {
                score = currentStraight;
            }
        }
        return score;
    }

    protected int maxSameFaceCardSize(Hand hand){
        int score = 0;
        
        List<Card> listOfCards = hand.getHandOfCards();
        for (Card card : listOfCards) {
            FaceCard currentFaceCard = card.getFaceCard();
            int currentFaceCardScore = 0;
            for (Card otherCard : listOfCards) {
                if (otherCard.getFaceCard() == currentFaceCard) {
                    currentFaceCardScore++;
                }
            }
            if (currentFaceCardScore > score) {
                score = currentFaceCardScore;
            }
        }
        return score;
    }

    protected void afterPlayerPlays(Player player){
        Hand hand = player.getHand();
        int score = scoreHand(hand);
        int noOfCards = hand.size();
        if (score >= noOfCards) {
            player.setWinner(true);
        } else {
            player.setWinner(false);
        }
    }

    
    public static void main(String[] args){
        GinRummy ginRummy = new GinRummy();
        ginRummy.play();
    }


    
}
