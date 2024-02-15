package game.card.game.GinRummy;

import java.util.List;
import java.util.HashMap;

import game.Player;
import game.card.CardGame;
import game.card.entity.Card;
import game.card.entity.FaceCard;
import game.card.entity.Hand;


public class GinRummy extends CardGame {

    protected static int noOfCards = 7;

    ScoreTrickHand scoreTrickHand = new ScoreTrickHand();

    //Task Four contructors

    protected void discardCard(Player player){
        Card card = getPlayersCard(player);
        addToDiscarded(card);
    }

    protected void userPlays(Player competitor){
        competitor.addCard(playerSelectCard(competitor, deck));
        discardCard(competitor);
    }

    protected Card removeWeakestCard(Hand hand){
        int currentScore = scoreHand(hand);
        Card card = null;
        //Task Four code
        return card;
    }

    protected Card scoreWithCard(Hand hand, Card card){
        int currentScore = scoreHand(hand);
        //Task four code
        return card;
    }

    protected void computerPlays(Player player){
        Hand hand = player.getHand();
        
        Card card = discardPile.getLastCard();
        Card removedCard = scoreWithCard(hand, card);
        if (card == removedCard) {
            card = deck.playACard();
            card = scoreWithCard(hand, card);
        }
        discardPile.add(card);
    }

    public int scoreHand(Hand hand){
        //Task Four code
        return 0;
    }

    protected void beforeInitiate(){
        setNoOfCards(noOfCards);
    }

    protected void afterInitiate(){
        Player leftOfDealer = getPlayer(1);
        leftOfDealer.addCard(deck.playACard());
        discardCard(leftOfDealer);
    }

    protected int maxStraightSuitSize(Hand hand){
        int score = 0;
        //Task four code
        return score;
    }

    protected int maxSameFaceCardSize(Hand hand){
        int score = 0;
        //Task four code
        return score;
    }

    protected void afterPlayerPlays(Player player){
        //Task four code
    }

    
    public static void main(String[] args){
        GinRummy ginRummy = new GinRummy();
        ginRummy.play();
    }


    
}
