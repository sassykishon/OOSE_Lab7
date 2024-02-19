package game.card.game.Snap;

import communication.YesOrNo;
import game.Player;
import game.card.CardGame;
import game.card.entity.Deck;
import game.card.entity.Hand;
import game.card.entity.Card;


public class Snap extends CardGame{

    public final static String wantToSnap = "Do you want to Snap?";
    public final static String discardPileMessage = "Discard Pile";

    protected static int noOfCards = 0;

    public Snap() {
        this("");
    }

    public Snap(String override) {
        this.deck = new Deck(override);
    }

    protected void dealCards() {
        boolean allCards = false;
        int noOfCards;
        Player player = null;
        if (Snap.noOfCards == 0) {
            noOfCards = (int) Math.floor(deck.size() / getPlayersSize());
            allCards = true;
        } else {
            noOfCards = Snap.noOfCards;
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
    
    protected boolean hasSnapped(YesOrNo isSnap, Hand discardPile){
        Card topCard = discardPile.getLastCard();
        Card secondTopCard = discardPile.getSecondLastCard();

        if (discardPile.size() >= 2) {
            if (isSnap == YesOrNo.YES && topCard.getFaceCard().equals(secondTopCard.getFaceCard())) {
                return true;
            }
            if (isSnap == YesOrNo.NO && !topCard.getFaceCard().equals(secondTopCard.getFaceCard())) {
                return true;
            }
        }
        return false;
        
    }

    protected void playerPlaysHand(Player player) {
        Player user = getUser();
        discardPile.add(player.getHand().playACard());
        displayCard(discardPileMessage, discardPile.getLastCard());
        YesOrNo isSnap = inOut.getYesOrNo(wantToSnap);
        user.setWinner(hasSnapped(isSnap, discardPile));
        setFinishGame(user.hasWon());
    }

    public static void main(String[] args) {
        Snap snap = new Snap();
        snap.play();
    }
}
