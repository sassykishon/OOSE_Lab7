package game.card;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import game.Game;
import game.LoadCSV;
import game.Player;
import game.card.entity.Card;
import game.card.entity.CompetitorType;
import game.card.entity.Deck;
import game.card.entity.Hand;
import game.card.game.Golf.GolfHelp;

public class CardGame extends Game {

    protected static int noOfCards = 2;
    protected static String askCardToPlay = "Select card by number starting with zero";
    protected static String dealerName = "Dealer";
    protected static String computerPlayerPrefix = "Comp - ";
    
    protected Hand discardPile = new Hand();
    protected Deck deck;

    public CardGame() {
        this("");
    }

    public CardGame(String override) {
        this.deck = new Deck(override);
    }

    protected void setNoOfCards(int noOfCards) {
        CardGame.noOfCards = noOfCards;
    }

    public Deck getDeck() {
        return deck;
    }

    public void displayCard(String name, Card card){
        inOut.displayCard(name, card);
    }

    public void displayHand(String name, Hand hand){
        inOut.displayHand(name, hand);
    }

    public void createDealer(){
        HashMap<CompetitorType, String[]> computerNames = getCompupterPlayersNames();
        createPlayer(CompetitorType.DEALER, computerNames.get(CompetitorType.DEALER)[0]);
    }

    public Card getPlayersCard(Player player){
        return inOut.getPlayersCard(player);
    }

    public void initiatePlayers() {
        clearPlayers();
        createDealer();
        createHumanPlayer();
        createComputerCompetitors(getNumberOfPlayers() - 2);
    }

    protected int getCardToPlay(){
        return getInputInteger(askCardToPlay);
    }

    public int scoreHand(Hand hand){
        int score = 0;
        return score;
    }

    public int getScore(Player player) {
        int score = 0;
        for (int counter = 0; counter < player.getNumberOfHands(); counter++) {
            score += scoreHand(player.getHand(counter));
        }
        return score;
    }

    protected void userPlays(Player competitor){
    }
    
    protected void computerPlays(Player competitor){

    }

    @Override
    protected void afterPlayOfRound() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void beforePlayOfRound() {
        // TODO Auto-generated method stub
        
    }

    protected void dealCards() {
        boolean allCards = false;
        int noOfCards;
        Player player = null;
        if (CardGame.noOfCards == 0) {
            noOfCards = (int) Math.floor(deck.size() / getPlayersSize());
            allCards = true;
        } else {
            noOfCards = CardGame.noOfCards;
        }
        for (int counter = 0; counter< getPlayersSize(); counter++ ) {
            player = getPlayer(counter);
            player.addHand(dealHand(noOfCards));
        }
        if (allCards) {
            for (int counter = 0; counter< getPlayersSize(); counter++ ) {
                player = getPlayer(counter);
                if (deck.size() > 0) {
                    player.getHand().add(deck.playACard());
                }
            }
        }
    }

    protected Hand dealHand(int noOfCardsToDeal, boolean visibility){
        Hand hand = new Hand(visibility);
        for (int cardCounter = 0; cardCounter < noOfCardsToDeal; cardCounter++) {
            if (deck.size() > 0) {
                hand.add(deck.playACard());
            }
        }
        return hand;
    }

    protected Hand dealHand(int noOfCardsToDeal) {
        return dealHand(noOfCardsToDeal, true);
    }

    public Card playerSelectCard(Player player, Deck deck){
        displayCard("Discard Pile", getLastDiscardedCard());
        displayPlayerWithVisibility(player);
        GolfHelp userChoice = inOut.getEnumIndex(GolfHelp.class);
        Card cardSelected = null;
        if (userChoice == GolfHelp.DISCARD){
            cardSelected = discardPile.playACard();
        } else {
            cardSelected = deck.playACard();
        } 
        return cardSelected;
    }

    public void addToDiscarded(Card card){
        discardPile.add(card);
    }

    public Card getLastDiscardedCard(){
        return discardPile.getLastCard();
    }

    protected void afterInitiate() {

    }

    protected void beforeInitiate(){

    }

    protected void initiate() {
        beforeInitiate();
        initiatePlayers();
        dealCards();
        afterInitiate();
    }

    public static void main(String[] args) {
        CardGame cardGame = new CardGame();
        cardGame.play();
    }
}
