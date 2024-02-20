package game.card.game.GinRummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import game.card.entity.Hand;
import game.card.entity.Suit;
import game.card.entity.Card;
import game.card.entity.FaceCard;

public class ScoreTrickHand {  

    public ScoreTrickHand() {
    }

    protected boolean isSameSuitAndNext(Card previousCard, Card nextCard){
        return previousCard != null && previousCard.getSuit() == nextCard.getSuit() &&
        previousCard.getFaceCard().ordinal() == nextCard.getFaceCard().ordinal() -1;
    }
    
    public List<Hand> getStraightSameSuit(Hand hand){
        if (hand == null) {
            return new ArrayList<>();
        }
        List<Hand> returnList = new ArrayList<>();
        List<Card> listOfCards = hand.getHandOfCards();
        List<Suit> listOfSuits = new ArrayList<>();
        for (Card card : listOfCards) {
            Suit currentSuit = card.getSuit();
            if (!listOfSuits.contains(currentSuit)) {
                listOfSuits.add(currentSuit);
                Hand currentHand = new Hand();
                for (Card cardToMatch : listOfCards) {
                    if (cardToMatch.getSuit().equals(currentSuit)) {
                        currentHand.add(cardToMatch);
                    }
                }
                if (currentHand.size() > 1) {
                    currentHand.sortHand();
                    returnList.add(currentHand);
                }
            }
        }
        return returnList;
    }

    protected HashMap<FaceCard, Hand> getSameFaceCards(Hand hand) {
        HashMap<FaceCard, Hand> sameFaceCards = new HashMap<FaceCard, Hand>();
        List<Card> listOfCards = hand.getHandOfCards();
        for (Card card : listOfCards) {
            FaceCard currentFaceCard = card.getFaceCard();
            Hand currentHand = new Hand();
            for (Card cardToMatch : listOfCards) {
                if (cardToMatch.getFaceCard().equals(currentFaceCard)) {
                    currentHand.add(cardToMatch);
                }
            }
            if (currentHand.size() > 1) {
                sameFaceCards.put(currentFaceCard, currentHand);
            }
        }
        return sameFaceCards;
    }

    public static void main(String[] args) {
        ScoreTrickHand scoreTrickHand = new ScoreTrickHand();
        Hand hand = new Hand("D2,D3,S3,D4,D5,D6,SA");
        HashMap<FaceCard, Hand> hashmap = scoreTrickHand.getSameFaceCards(hand);
        System.out.println(hashmap);
    }

}
