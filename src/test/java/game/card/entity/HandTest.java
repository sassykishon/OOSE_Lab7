package game.card.entity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    Hand hand = new Hand("C5,D3,D2,D5,D6,D7,S7");

    @Test
    void testHandConstructorNoCards(){
        Hand hand = new Hand();
        assertEquals(0, hand.size());
    }

    @Test
    void testHandContructorWithCards(){
        assertEquals(7, hand.size());
    }

    @Test
    void getFirstCard() {
        assertEquals("C5", this.hand.getFirstCard().toString());
    }

    @Test
    void getLastCard() {
        assertEquals("S7", this.hand.getLastCard().toString());
    }

    @Test
    void testGetSecondLastCard() {
        fail("Not yet implemented"); //Remove
    }

    @Test
    void getHandOfCards(){
        assertEquals(7, hand.size());
    }

    @Test
    void playACardNoParam(){
        assertEquals("S7", hand.playACard().toString());
    }

    @Test
    void playACardInteger(){
        assertEquals("D3", hand.playACard(1).toString());
    }

    @Test
    void testAddAtStart(){
        Card card = new Card("SA");
        hand.addAtStart(card);
        assertEquals("SA, C5, D3, D2, D5, D6, D7, S7", hand.toString());
    }

    @Test
    void testAddWithIndex(){
        Card card = new Card("SA");
        hand.add(3, card);
        assertEquals("C5, D3, D2, SA, D5, D6, D7, S7", hand.toString());
    }

    @Test
    void testSet(){
        Card card = new Card("SA");
        hand.set(3, card);
        assertEquals("C5, D3, D2, SA, D6, D7, S7", hand.toString());
    }

    @Test
    void copy(){
        Hand newHand = hand.copy();
        assertNotEquals(newHand, hand);
    }

    @Test
    void copySize(){
        Hand newHand = hand.copy();
        assertEquals(newHand.size(), hand.size());
    }

    @Test
    void isEmpty(){
        assertFalse(hand.isEmpty());
    }

    @Test
    void size(){
        assertEquals(7, hand.size());
    }

    @Test
    void sizeZero(){
        Hand zeroHand = new Hand();
        assertEquals(0, zeroHand.size());
    }

    @Test
    void findACard(){
        assertEquals("D3", hand.findACard("D3").toString());
    }

    @Test
    void hasCardShortCode(){
        assertTrue(hand.hasCard("D3"));
    }

    @Test
    void hasCard(){
        Card card = Card.getInstance("D3");
        assertTrue(hand.hasCard(card));
    }

    @Test
    void hasCards(){
        assertTrue(hand.hasCards("C5,D3"));
    }

    @Test
    void highestCardOfASuit(){
        assertEquals("D7", hand.highestCardOfASuit(Suit.DIAMONDS).toString());
    }

    @Test
    void lowestCardOfASuit(){
        assertEquals("D2", hand.lowestCardOfASuit(Suit.DIAMONDS).toString());
    }

    @Test
    void highestCardExceptSuit(){
        assertEquals("D7", hand.highestCardExceptSuit(Suit.HEARTS).toString());
    }

    @Test
    void lowestCardExceptSuit(){
        assertEquals("D2", hand.lowestCardExceptSuit(Suit.HEARTS).toString());
    }

    @Test
    void playACardWithShortCode(){
        assertTrue(hand.playACard("D3"));
    }

    @Test
    void playCards(){
        assertTrue(hand.playCards("D3,D7"));
    }

    @Test
    void playCardsCard(){
        List<Card> cards = new ArrayList<Card>();
        cards.add(Card.getInstance("D3"));
        cards.add(Card.getInstance("D7"));
        assertTrue(hand.playCards(cards));
    }

    @Test
    void playCardsSize(){
        hand.playCards("D3,D7");
        assertEquals(5, hand.size());
    }

    @Test
    void addCardsSize(){
        hand.addCards("DK,DJ");
        assertEquals(9, hand.size());
    }

    @Test
    void addCardsCardSize(){
        List<Card> cards = new ArrayList<Card>();
        cards.add(Card.getInstance("DK"));
        cards.add(Card.getInstance("DJ"));
        hand.addCards(cards);
        assertEquals(9, hand.size());
    }

    @Test
    void testSortHand(){
        fail("Not yet implemented"); //Remove
    }

    @Test
    void testSortHandFace(){
        fail("Not yet implemented"); //Remove
    }
}