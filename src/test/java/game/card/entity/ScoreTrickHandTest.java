package game.card.entity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

class ScoreTrickHandTest {

    @Test
    public void testGetSameFaceCardTwoPairs() {
        Hand hand = new Hand("C7,C3,D8,D7,D3");
        ScoreTrickHand scoreTrickHand = new ScoreTrickHand(hand);
        HashMap<FaceCard, Hand> sameFaceCards = scoreTrickHand.getSameFaceCards();
        assertEquals(2, sameFaceCards.size());

    }

    @Test
    public void testGetSameFaceCardArePairs() {
        Hand hand = new Hand("C7,C3,D8,D7,D3");
        ScoreTrickHand scoreTrickHand = new ScoreTrickHand(hand);
        HashMap<FaceCard, Hand> sameFaceCards = scoreTrickHand.getSameFaceCards();
        assertEquals(2, sameFaceCards.get(FaceCard.THREE).size());
    }

    @Test
    public void testGetSameFaceCardAreFourOfAKind() {
        Hand hand = new Hand("C7,S7,H7,D7,D3");
        ScoreTrickHand scoreTrickHand = new ScoreTrickHand(hand);
        HashMap<FaceCard, Hand> sameFaceCards = scoreTrickHand.getSameFaceCards();
        assertEquals(4, sameFaceCards.get(FaceCard.SEVEN).size());
    }

    @Test
    public void testGetSameFaceCardAreFourOfAKindString() {
        Hand hand = new Hand("C7,S7,H7,D7,D3");
        ScoreTrickHand scoreTrickHand = new ScoreTrickHand(hand);
        HashMap<FaceCard, Hand> sameFaceCards = scoreTrickHand.getSameFaceCards();
        assertEquals("C7, S7, H7, D7", sameFaceCards.get(FaceCard.SEVEN).toString());
    }

    @Test
    public void testGetStraightSameSuitFive() {
        Hand hand = new Hand("C7,C6,C4,C5,C8");
        ScoreTrickHand scoreTrickHand = new ScoreTrickHand(hand);
        List<Hand> straightHand = scoreTrickHand.getStraightSameSuit();
        assertEquals("[C4, C5, C6, C7, C8]", straightHand.toString());
    }

    @Test
    public void testGetStraightSameSuitTwoHands() {
        Hand hand = new Hand("C7,C6,D4,H5,H6");
        ScoreTrickHand scoreTrickHand = new ScoreTrickHand(hand);
        List<Hand> straightHands = scoreTrickHand.getStraightSameSuit();
        assertEquals(2, straightHands.size());
    }

    @Test
    public void testGetStraightSameSuitToString() {
        Hand hand = new Hand("C7,C6,D4,H5,H6");
        ScoreTrickHand scoreTrickHand = new ScoreTrickHand(hand);
        List<Hand> straightHands = scoreTrickHand.getStraightSameSuit();
        assertEquals("[C6, C7, H5, H6]", straightHands.toString());
    }
}

