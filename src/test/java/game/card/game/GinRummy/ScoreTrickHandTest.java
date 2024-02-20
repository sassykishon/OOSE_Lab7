package game.card.game.GinRummy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.List;

import game.card.entity.FaceCard;
import game.card.entity.Hand;
import game.card.game.GinRummy.ScoreTrickHand;

public class ScoreTrickHandTest {

    @Test
    public void testGetSameFaceCardTwoPairs() {
        Hand hand = new Hand("C7,C3,D8,D7,D3");
        ScoreTrickHand scoreTrickHand = new ScoreTrickHand();
        HashMap<FaceCard, Hand> sameFaceCards = scoreTrickHand.getSameFaceCards(hand);
        assertEquals(2, sameFaceCards.size());

    }

    @Test
    public void testGetSameFaceCardArePairs() {
        Hand hand = new Hand("C7,C3,D8,D7,D3");
        ScoreTrickHand scoreTrickHand = new ScoreTrickHand();
        HashMap<FaceCard, Hand> sameFaceCards = scoreTrickHand.getSameFaceCards(hand);
        assertEquals(2, sameFaceCards.get(FaceCard.THREE).size());
    }

    @Test
    public void testGetSameFaceCardAreFourOfAKind() {
        Hand hand = new Hand("C7,S7,H7,D7,D3");
        ScoreTrickHand scoreTrickHand = new ScoreTrickHand();
        HashMap<FaceCard, Hand> sameFaceCards = scoreTrickHand.getSameFaceCards(hand);
        assertEquals(4, sameFaceCards.get(FaceCard.SEVEN).size());
    }

    @Test
    public void testGetSameFaceCardAreFourOfAKindString() {
        Hand hand = new Hand("C7,S7,H7,D7,D3");
        ScoreTrickHand scoreTrickHand = new ScoreTrickHand();
        HashMap<FaceCard, Hand> sameFaceCards = scoreTrickHand.getSameFaceCards(hand);
        assertEquals("C7, S7, H7, D7", sameFaceCards.get(FaceCard.SEVEN).toString());
    }

    @Test
    public void testGetStraightSameSuitFive() {
        Hand hand = new Hand("C7,C6,C4,C5,C8");
        ScoreTrickHand scoreTrickHand = new ScoreTrickHand();
        List<Hand> straightHand = scoreTrickHand.getStraightSameSuit(hand);
        assertEquals("[C4, C5, C6, C7, C8]", straightHand.toString());
    }

    @Test
    public void testGetStraightSameSuitTwoHands() {
        Hand hand = new Hand("C7,C6,D4,H5,H6");
        ScoreTrickHand scoreTrickHand = new ScoreTrickHand();
        List<Hand> straightHands = scoreTrickHand.getStraightSameSuit(hand);
        assertEquals(2, straightHands.size());
    }

    @Test
    public void testGetStraightSameSuitToString() {
        Hand hand = new Hand("C7,C6,D4,H5,H6");
        ScoreTrickHand scoreTrickHand = new ScoreTrickHand();
        List<Hand> straightHands = scoreTrickHand.getStraightSameSuit(hand);
        assertEquals("[C6, C7, H5, H6]", straightHands.toString());
    }
    
}
