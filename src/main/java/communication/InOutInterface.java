package communication;

import java.util.List;

import game.Player;
import game.card.entity.Card;
import game.card.entity.Hand;

public interface InOutInterface {
    
    public void print(String message);

    public String getInputString(String message);

    public int getInputInteger(String message);

    public int getListIndex(String question, List<String> selection);

    public int getListIndex(List<String> selection);

    public <ExtendedENum extends Enum<ExtendedENum>> ExtendedENum getEnumIndex(Class<ExtendedENum> selection);
    
    public <ExtendedENum extends Enum<ExtendedENum>> ExtendedENum getEnumIndex(String question, Class<ExtendedENum> selection);
    
    public YesOrNo getYesOrNo(String question);

    public void displayPlayer(Player player);

    public Card getPlayersCard(Player player);

    public void displayPlayerWithVisibility(Player player);

    public void displayCard(String name, Card card);

    public void displayHand(String name, Hand hand);
}
