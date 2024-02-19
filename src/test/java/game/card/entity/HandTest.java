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
        assertEquals("D7", this.hand.getSecondLastCard().toString());
    }

    @Test
    void testSortHand(){
        hand.sortHand();
        assertEquals("C5, D2, D3, D5, D6, D7, S7", hand.toString());
    }

    @Test
    void testSortHandFace(){
        hand.sortHandByFace();
        assertEquals("D2, D3, C5, D5, D6, D7, S7", hand.toString());
    }
}