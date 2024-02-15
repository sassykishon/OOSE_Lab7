package game.card.game.GinRummy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import game.card.entity.Hand;
import game.card.entity.Card;
import game.card.entity.CompetitorType;
import game.Player;

public class GinRummyTest {

    GinRummy ginRummy = new GinRummy("D3,D4,D5,S3");

    @Test
    void testGinRummyOverride(){
        fail("Not yet implemented"); //Remove
    }

    @Test
    void testGinRummyNoOverride(){
        fail("Not yet implemented"); //Remove
    }

    @Test
    void testMaxSameFaceCardSize(){
        fail("Not yet implemented"); //Remove
    }

    @Test
    void testMaxStraightSuitSize(){
        fail("Not yet implemented"); //Remove
    }

    @Test 
    void testScoreHand(){
        fail("Not yet implemented"); //Remove
    }

    @Test
    void testScoreHandWin(){
        fail("Not yet implemented"); //Remove
    }

    @Test
    void testRemoveWeakestCard(){
        fail("Not yet implemented"); //Remove
    }

    @Test
    void testScoreWithCard(){
        fail("Not yet implemented"); //Remove
    }

    @Test
    void testScoreWithCardBetterCard(){
        fail("Not yet implemented"); //Remove
    }

    @Test
    void testAfterPlayerPlaysNotWon(){
        Hand hand = new Hand("D2,D3,S3,D4,D10,D6,SA");
        Player player = new Player(CompetitorType.USER, "Derek");
        player.addHand(hand);
        ginRummy.afterPlayerPlays(player);
        assertFalse(player.hasWon());
    }

    @Test
    void testAfterPlayerPlaysWon(){
        Hand hand = new Hand("D7,D2,S2,D4,D5,D6,S2");
        Player player = new Player(CompetitorType.USER, "Derek");
        player.addHand(hand);
        ginRummy.afterPlayerPlays(player);
        assertTrue(player.hasWon());
    }


}
