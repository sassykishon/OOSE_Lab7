package game.card.entity;
import org.junit.jupiter.api.Test;

import game.Player;

import static org.junit.jupiter.api.Assertions.*;

class CompetitorTest {
    Player competitor = new Player(CompetitorType.USER, "Derek");
    @Test
    void hasWon() {
        competitor.setWinner(true);
        assertTrue(competitor.hasWon());
    }

    @Test
    void hasWonNot() {
        competitor.setWinner(false);
        assertFalse(competitor.hasWon());
    }

    @Test
    void hasHand() {
        Hand hand = new Hand("S3,S4,D5");
        competitor.addHand(hand);
        assertTrue(competitor.hasHand());
    }


    @Test
    void getPlayerType() {
        assertEquals(CompetitorType.USER, competitor.getCompetitorType());
    }

    @Test
    void getName() {
        assertEquals("Derek", competitor.getName());
    }

    @Test
    void getHand() {
        Hand hand = new Hand("S3,S4,D5");
        competitor.addHand(hand);
        assertEquals(hand, competitor.getHand());
    }
}