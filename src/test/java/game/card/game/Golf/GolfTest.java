package game.card.game.Golf;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import game.card.entity.Hand;
import game.card.entity.Card;
import game.card.entity.CompetitorType;
import game.Player;


public class GolfTest {

    Golf golf = new Golf();
    
    @Test
    void testHasBestScoreWithNegative(){
        assertTrue(golf.hasBestScore(-1, 5));
    }

    @Test
    void testHasBestScorePositive(){
        assertTrue(golf.hasBestScore(4, 3));
    }

    @Test
    void testHasBestScoreNegative(){
        assertFalse(golf.hasBestScore(2, 3));
    }

    @Test
    void testScoreHandQueenKing(){
        Hand hand = new Hand("SQ,SK");
        assertEquals(10, golf.scoreHand(hand));
    }

    @Test
    void testScoreHandTenFive(){
        Hand hand = new Hand("S10,S5");
        assertEquals(15, golf.scoreHand(hand));
    }

    @Test
    void testScoreHandQueenAce(){
        Hand hand = new Hand("SQ,SA");
        assertEquals(11, golf.scoreHand(hand));
    }

    @Test
    void testScoreHandQueenTwo(){
        Hand hand = new Hand("SQ,S2");
        assertEquals(8, golf.scoreHand(hand));
    }

    @Test
    void testScoreHandQueenQueen(){
        Hand hand = new Hand("SQ,CQ");
        assertEquals(0, golf.scoreHand(hand));
    }

    @Test
    void testReplaceCardFirst(){
        Hand hand = new Hand("S2,SQ");
        Card card = new Card("CQ");
        golf.replaceCard(hand, card, 0);
        assertEquals("CQ, SQ", hand.toString());
    }    
    
    @Test
    void testReplaceCardSecond(){
        Hand hand = new Hand("SQ,S2");
        Card card = new Card("CQ");
        golf.replaceCard(hand, card, 1);
        assertEquals("SQ, CQ", hand.toString());
    }

    @Test
    void testCompareCardWithHand(){
        Hand hand = new Hand("SQ,S2");
        Card card = new Card("CQ");
        assertTrue(golf.compareCardWithHand(hand, card));    
    }

    @Test
    void testCompareCardWithHandToString(){
        Hand hand = new Hand("SQ,S2");
        Card card = new Card("CQ");
        golf.compareCardWithHand(hand, card);
        assertEquals("SQ, CQ", hand.toString());
    }

    @Test
    void testCompareCardWithHandNegative(){
        Hand hand = new Hand("S5,S2");
        Card card = new Card("CQ");
        assertFalse(golf.compareCardWithHand(hand, card));    
    }

    @Test
    void testCompareCardWithHandNegativeToString(){
        Hand hand = new Hand("S5,S2");
        Card card = new Card("CQ");
        golf.compareCardWithHand(hand, card);
        assertEquals("S5, S2", hand.toString());
    }

    @Test
    void testCompareCardForPlayerNegative(){
        Player player = new Player(CompetitorType.USER, "Derek");
        Hand hand = new Hand("S5,S2");
        Hand hand2 = new Hand("S6,S3");
        player.addHand(hand);
        player.addHand(hand2);
        Card card = new Card("CQ");
        assertFalse(golf.compareCardForPlayer(player, card));    
    }

    @Test
    void testCompareCardForPlayerNegativeToString(){
        Player player = new Player(CompetitorType.USER, "Derek");
        Hand hand = new Hand("S5,S2");
        Hand hand2 = new Hand("S6,S3");
        player.addHand(hand);
        player.addHand(hand2);
        Card card = new Card("CQ");
        golf.compareCardForPlayer(player, card);
        assertEquals("Derek\nS5, S2\nS6, S3", player.toString());
    }

    @Test
    void testCompareCardForPlayer(){
        Player player = new Player(CompetitorType.USER, "Derek");
        Hand hand = new Hand("S5,S2");
        Hand hand2 = new Hand("SQ,S3");
        player.addHand(hand);
        player.addHand(hand2);
        Card card = new Card("CQ");
        assertTrue(golf.compareCardForPlayer(player, card));    
    }

    @Test
    void testCompareCardForPlayerToString(){
        Player player = new Player(CompetitorType.USER, "Derek");
        Hand hand = new Hand("S5,S2");
        Hand hand2 = new Hand("SQ,S3");
        player.addHand(hand);
        player.addHand(hand2);
        Card card = new Card("CQ");
        golf.compareCardForPlayer(player, card);
        assertEquals("Derek\nS5, S2\nSQ, CQ", player.toString());
    }

    @Test
    void testComputerPlayBasicDiscard(){
        Golf golf = new Golf("S5,S9");
        Card card = new Card("CQ");
        golf.addToDiscarded(card);
        Player player = new Player(CompetitorType.USER, "Derek");
        Hand hand = new Hand("S5,S2");
        Hand hand2 = new Hand("SQ,S3");
        player.addHand(hand);
        player.addHand(hand2);
        golf.computerPlayBasic(player);
        assertEquals("Derek\nS5, S2\nSQ, CQ", player.toString());
    }

    @Test
    void testComputerPlayBasicDeck(){
        Golf golf = new Golf("S5,CQ");
        Card card = new Card("C9");
        golf.addToDiscarded(card);
        Player player = new Player(CompetitorType.USER, "Derek");
        Hand hand = new Hand("S5,S2");
        Hand hand2 = new Hand("SQ,S3");
        player.addHand(hand);
        player.addHand(hand2);
        golf.computerPlayBasic(player);
        assertEquals("Derek\nS5, S2\nSQ, CQ", player.toString());
    }


}
