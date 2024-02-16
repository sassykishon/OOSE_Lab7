package game.card.game.GinRummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import game.card.entity.Hand;
import game.card.entity.Card;
import game.card.entity.FaceCard;

public class ScoreTrickHand {  


    protected boolean isSameSuitAndNext(Card previousCard, Card nextCard){
        return previousCard != null && previousCard.getSuit() == nextCard.getSuit() &&
        previousCard.getFaceCard().ordinal() == nextCard.getFaceCard().ordinal() -1;
    }
    
    protected List<Hand> getStraightSameSuit(Hand hand) {
        List<Hand> straightHands = new ArrayList<Hand>();
        Hand straightHand = new Hand();
        hand.sortHand();;
        Card previousCard = null;
        for (Card nextCard : hand.getHandOfCards()) {
            if (isSameSuitAndNext(previousCard, nextCard)) {
                if (!straightHand.getHandOfCards().contains(previousCard)){
                    straightHand.add(previousCard);
                }
                straightHand.add(nextCard);
            } else {
                if (straightHand.size() > 0){
                    straightHands.add(straightHand);
                    straightHand = new Hand();
                }
            }
            previousCard = nextCard;
        }
        if (straightHand.size() > 0){
            straightHands.add(straightHand);
        }
        return straightHands;
    }

    protected HashMap<FaceCard, Hand> getSameFaceCards(Hand hand) {
        HashMap<FaceCard, Hand> sameFaceCards = new HashMap<FaceCard, Hand>();
        //Task Three code
        return sameFaceCards;
    }

}
