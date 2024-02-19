package game.card.game.Snap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import communication.YesOrNo;
import game.card.entity.Hand;

public class SnapTest {

    Snap snap = new Snap();

    @Test
    void testSnapOverride(){
        Snap snap = new Snap("S3,S4,S5");
        assertEquals(3, snap.getDeck().size());
    }

    @Test
    void testSnapNoOverride(){
        assertEquals(52, snap.getDeck().size());
    }

    @Test
    void testDealCards(){
        snap.createComputerCompetitors(4);
        snap.dealCards();
        assertEquals(13, snap.getPlayer(0).getHand().size());
    }

    @Test
    void testHasSnapped(){
        Hand hand = new Hand("S3,S4,C4");
        assertTrue(snap.hasSnapped(YesOrNo.YES, hand));

    }

    @Test
    void testHasSnappedWithDifferntCards(){
        Hand hand = new Hand("S3,S4,C5");
        assertFalse(snap.hasSnapped(YesOrNo.YES, hand));
    }

    
}
