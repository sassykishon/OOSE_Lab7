package game.card.game.TwentyOne;

import game.Player;
import game.card.CardGame;
import game.card.entity.Card;
import game.card.entity.FaceCard;
import game.card.entity.Hand;

public class TwentyOne extends CardGame {
    private int maxScore = 21;

    public TwentyOneActions generateHelp() {
        return inOut.getEnumIndex(TwentyOneActions.class);
    }

    protected TwentyOneActions getPlayerAction(Player player) {

        TwentyOneActions userAction;
        if (player.hasHand()) {
            print(player.getHand().toString());
        }
        userAction = generateHelp();
        print("You choose " + userAction.display());
        return userAction;
    }

    protected void userPlays(Player player) {
        TwentyOneActions userAction = TwentyOneActions.TWIST;
        while (getScore(player.getHand()) <= maxScore && userAction != TwentyOneActions.STICK) {
            userAction = getPlayerAction(player);
            if (userAction == TwentyOneActions.TWIST) {
                player.getHand().add(deck.playACard());
            }
        }
        setFinishGame(true);
    }

    protected void computerPlays(Player player) {
        Hand hand = player.getHand();
        while (getScore(hand) <= player.getLevelOfRisk()) {
            hand.add(deck.playACard());
        }
    }

    public int getScore(Hand hand) {
        int score = 0;
        Boolean hasAnAce = false;
        for (Card card : hand.getHandOfCards()) {
            if (card.getFaceCard() == FaceCard.ACE) {
                hasAnAce = true;
            }
            score += card.getFaceCard().getValue();
        }
        if (score > maxScore && hasAnAce) {
            score -= FaceCard.ACE.getValue() - 1;
        }
        return score;
    }

    public static void main(String[] args) {
        TwentyOne twentyOne = new TwentyOne();
        twentyOne.play();
    }

}
