package communication;

import java.util.Scanner;

import game.Player;
import game.card.entity.Card;
import game.card.entity.Hand;

import java.util.List;

public class ConsoleInOut implements InOutInterface {

    protected static String defaultListQuetion = "Please select an item";

    protected static String selectCard = "Please select an card";
    protected Scanner userInput = new Scanner(System.in);

    public void print(String message) {
        System.out.println(message);
    }

    private String getString() {
        return userInput.nextLine();
    }

    private int getInteger() {
        int result = -1;
        int counter = 0;
        int maxCounter = 5;
        do {
            try {
                result = Integer.parseInt(getString());
            } catch (NumberFormatException exception) {
                // Display.Output expected NumberFormatException.
                print("Please enter a integer");
            }
        } while (result < 0 && counter < maxCounter);

        return result;
    }

    public String getInputString(String message){
        print(message);
        return getString();        
    }

    public int getInputInteger(String message){
        print(message);
        return getInteger();
    }

    public int getListIndex(List<String> selection){
        return getListIndex(defaultListQuetion, selection);
    }

    public int getListIndex(String question, List<String> selection){
        int counter = 0;
        print(question);
        for (String item : selection){
            print(counter + " - " + item);
        }
        return getInteger();
    }

    public <ExtendedENum extends Enum<ExtendedENum>> ExtendedENum getEnumIndex(Class<ExtendedENum> selection){
        return getEnumIndex(defaultListQuetion, selection);
    }

    public <ExtendedENum extends Enum<ExtendedENum>> ExtendedENum getEnumIndex(String question, Class<ExtendedENum> selection){
        print(question);
        int counter = 0;
        for (ExtendedENum item: selection.getEnumConstants()){
            print(counter + " - " + item);
            counter ++;
        }
        int index = getInteger();
        return (ExtendedENum)selection.getEnumConstants()[index];
    }

    public YesOrNo getYesOrNo(String question){
        YesOrNo result = null;
        String answer = getInputString(question);
        if (answer.toLowerCase().contains("y") || answer.toLowerCase().contains("0")){
            result = YesOrNo.YES;
        } else {
            result = YesOrNo.NO;
        }
        return result;
    }

    public void displayPlayer(Player player){
        print(player.toString());
    }

    public Card getPlayersCard(Player player){
        displayPlayerWithVisibility(player);
        return player.playACard(getInputInteger(selectCard));
    }

    public void displayPlayerWithVisibility(Player player){
        print(player.displayHandWithVisibility());
    }

    public void displayCard(String name, Card card){
        print(name + " " + card.displayCamelCase());
    }

    public void displayHand(String name, Hand hand){
        print(name + " " + hand.toString());
    }

    public static void main(String[] args){
        ConsoleInOut consoleInOut = new ConsoleInOut();
        consoleInOut.print(consoleInOut.getEnumIndex("Hello", YesOrNo.class).toString());
    }
}
