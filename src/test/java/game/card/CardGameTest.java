package game.card;
import org.junit.jupiter.api.Test;

import communication.ConsoleInOut;
import game.Game;
import game.Player;
import game.card.entity.CompetitorType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CardGameTest {
    CardGame cardGame = new CardGame();

    private void mockInOut(String name, int noOfPlayers){
        ConsoleInOut mockInOut = mock(ConsoleInOut.class);
        when(mockInOut.getInputString(Game.askName)).thenReturn(name);
        when(mockInOut.getInputInteger(Game.numberOfCompetitors)).thenReturn(noOfPlayers);
        cardGame.setInOut(mockInOut);
    }

    private void mockInitiation(String name, int noOfPlayers){
        mockInOut(name, noOfPlayers);
        cardGame.initiatePlayers();
    }

    @Test
    void testContructor(){
        assertEquals(52, cardGame.deck.size());
    }

    @Test
    void testContructorWithOverride(){
        CardGame cardGame = new CardGame("D3,D2,S5,S6");
        assertEquals(4, cardGame.deck.size());
    }

    @Test
    void testGetDeck() {
        assertEquals(52, cardGame.getDeck().size());
    }

    @Test
    void testCreatePlayerName(){
        String name = "Derek";
        Player player = cardGame.createPlayer(CompetitorType.USER, name);
        assertEquals(name, player.getName());
    }

    @Test
    void testCreatePlayerType(){
        String name = "Derek";
        Player player = cardGame.createPlayer(CompetitorType.USER, name);
        assertEquals(CompetitorType.USER, player.getCompetitorType());
    }

    @Test
    void testAddPlayer(){
        String name = "Derek";
        Player player = cardGame.createPlayer(CompetitorType.USER, name);
        assertEquals(1, cardGame.getPlayers().size());
    }

    @Test
    void testCreateHumanPlayerType(){
        String name = "Derek";
        mockInOut(name, 0);
        cardGame.createHumanPlayer();
        assertEquals(CompetitorType.USER, cardGame.getPlayer(0).getCompetitorType());
    }

    @Test
    void testCreateHumanPlayerName(){
        String name = "Derek";
        mockInOut(name, 0);
        cardGame.createHumanPlayer();
        assertEquals(name, cardGame.getPlayer(0).getName());
    }

    @Test
    void testCreateComputerCompetitorsComputer(){
        cardGame.createComputerCompetitors(3);
        assertEquals(CompetitorType.COMPUTER, cardGame.getPlayer(0).getCompetitorType());
    }

    @Test
    void testCreateComputerCompetitors(){
        cardGame.createComputerCompetitors(3);
        assertEquals(3, cardGame.getPlayersSize());
    }

    @Test
    void testInitiatePlayersUserName(){
        String name = "Derek";
        mockInOut(name, 4);
        cardGame.initiatePlayers();
        assertEquals(name, cardGame.getPlayer(1).getName());
    }

    @Test
    void testInitiatePlayers(){
        String name = "Derek";
        mockInOut(name, 4);
        cardGame.initiatePlayers();
        assertEquals(4, cardGame.getPlayersSize());
    }

    @Test
    void testResetPlayers(){
        String name = "Derek";
        mockInitiation(name, 4);
        cardGame.getPlayer(2).setWinner(true);
        cardGame.resetPlayers();
        assertFalse(cardGame.getPlayer(2).hasWon());
    }

    @Test
    void testDealCards(){
        String name = "Derek";
        mockInitiation(name, 4);
        cardGame.setNoOfCards(0);
        cardGame.dealCards();
        assertEquals(13, cardGame.getPlayer(0).getHand().size());
    }

    @Test
    void testDealHand() {
        CardGame cardGame = new CardGame("D3,D2,S5,S6");
        Player competitor = new Player(CompetitorType.DEALER, "Dealer");
        competitor.addHand(cardGame.dealHand( 3));
        assertEquals("S6, S5, D2", competitor.displayHand());
    }

    @Test
    void testInitiateDeck(){
        String name = "Derek";
        mockInOut(name, 4);
        cardGame.setNoOfCards(2);
        cardGame.initiate();
        //44 since two cards dealt out to players
        assertEquals(44, cardGame.deck.size());
    }

    
    @Test
    void testInitiateCreatePlayers(){
        String name = "Derek";
        mockInOut(name, 4);
        cardGame.initiate();
        assertEquals(4, cardGame.getPlayersSize());
    }

    @Test
    void testInitiateDealCards(){
        String name = "Derek";
        mockInOut(name, 4);
        cardGame.setNoOfCards(2);
        cardGame.initiate();
        assertEquals(2, cardGame.getPlayer(0).getHand().size());
    } 
    
    @Test
    void testInitiateDealCardsFour(){
        String name = "Derek";
        mockInOut(name, 4);
        cardGame.setNoOfCards(4);
        cardGame.initiate();
        assertEquals(4, cardGame.getPlayer(0).getHand().size());
    }   

    @Test
    void testInitiateDealCardsAll(){
        String name = "Derek";
        mockInOut(name, 4);
        cardGame.setNoOfCards(0);
        cardGame.initiate();
        assertEquals(13, cardGame.getPlayer(0).getHand().size());
    }  

}