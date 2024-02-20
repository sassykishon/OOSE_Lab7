package game.card.game.GinRummy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import game.card.entity.Hand;
import game.card.game.Snap.Snap;
import game.card.entity.Card;
import game.card.entity.CompetitorType;
import game.Player;

public class GinRummyTest {

    GinRummy ginRummy = new GinRummy();

    @Test
    void testGinRummyOverride(){
        GinRummy ginRummy = new GinRummy("S3,S4,S5");
        assertEquals(3, ginRummy.getDeck().size());
    }

    @Test
    void testGinRummyNoOverride(){
        assertEquals(52, ginRummy.getDeck().size());
    }

    @Test
    void testMaxSameFaceCardSize(){
        Hand hand = new Hand("D2,D3,S3,D4,D5,D9,SA");
        assertEquals(2, ginRummy.maxSameFaceCardSize(hand));
    }

    @Test
    void testMaxStraightSuitSize(){
        Hand hand = new Hand("D2,D3,S3,D4,D5,D9,SA");
        assertEquals(4, ginRummy.maxStraightSuitSize(hand));
    }

    @Test 
    void testScoreHand(){
        Hand hand = new Hand( "D2,D3,S3,D4,D5,D9,SA");
        assertEquals(6, ginRummy.scoreHand(hand));
    }

    @Test
    void testScoreHandWin(){
        Hand hand = new Hand( "D7,D2,S2,D4,D5,D6,S2");
        assertEquals(7, ginRummy.scoreHand(hand));
    }

    @Test
    void testRemoveWeakestCard(){
        Hand hand = new Hand("D2,D3,S3,D4,D5,D6,S9,SA");
        int initialCardScore = ginRummy.scoreHand(hand);
        ginRummy.removeWeakestCard(hand);
        assertEquals(initialCardScore, ginRummy.scoreHand(hand));
    }

    @Test
    void testScoreWithCard(){
        Hand hand = new Hand( "D2,D3,S3,D4,D5,D6,SA");
        Card card = new Card("S9");
        Card cardToCheck = ginRummy.scoreWithCard(hand, card);
        assertEquals("S9", cardToCheck.toString());
    }

    @Test
    void testScoreWithCardBetterCard(){
        Hand hand = new Hand("D2,D3,S9,D4,D5,D6,SA");
        Card card = new Card("S3");
        Card cardToCheck = ginRummy.scoreWithCard(hand, card);
        assertEquals("S9", cardToCheck.toString());
    }

    @Test
    void testAfterPlayerPlaysNotWon(){
        Hand hand = new Hand("D2,D3,S3,D4,D10,D6,SA");
        Player player = new Player(CompetitorType.USER, "Player");
        player.addHand(hand);
        ginRummy.afterPlayerPlays(player);
        assertEquals(false, player.hasWon());
    }

    @Test
    void testAfterPlayerPlaysWon(){
        Hand hand = new Hand("D7,D2,S2,D4,D5,D6,S2");
        Player player = new Player(CompetitorType.USER, "Player");
        player.addHand(hand);
        ginRummy.afterPlayerPlays(player);
        assertEquals(true, player.hasWon());
    }


}
