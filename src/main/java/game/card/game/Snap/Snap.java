package game.card.game.Snap;

import communication.YesOrNo;
import game.Player;
import game.card.CardGame;
import game.card.entity.Hand;

public class Snap extends CardGame{

    public final static String wantToSnap = "Do you want to Snap?";
    public final static String discardPileMessage = "Discard Pile";

    protected static int noOfCards = 0;

    //Task one - create constructors

    protected void dealCards() {
        //Task one create code here
    }
    
    protected boolean hasSnapped(YesOrNo isSnap, Hand discardPile){
        return false;//Task one create code here
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
