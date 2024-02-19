package game.card.entity;
import java.util.HashMap;
import java.util.List;
import game.card.entity.Hand;
import java.util.ArrayList;

public class ScoreTrickHand {
    private Hand hand;

    public ScoreTrickHand(Hand hand) {
        this.hand = hand;
    }

    public HashMap<FaceCard, Hand> getSameFaceCards() {
        HashMap<FaceCard, Hand> returnMap = new HashMap<>();
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
                returnMap.put(currentFaceCard, currentHand);
            }
        }
        return returnMap;
    }

    public List<Hand> getStraightSameSuit(){
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
}
