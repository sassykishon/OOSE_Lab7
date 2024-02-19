package game.card.entity;

import java.util.HashMap;

public class Card {

    private static HashMap<String, Card> cards = new HashMap<String, Card>();

    private Suit suit;
    private FaceCard cardFace;

    public Card(Suit suit, FaceCard cardFace) {
        this.suit = suit;
        this.cardFace = cardFace;
    }

    public Card(String card) {
        try {
            this.suit = Suit.getSuit(card.substring(0, 1));
            this.cardFace = FaceCard.getCardRank(card.substring(1, card.length()));
        } catch (Exception exp) {
            System.out.println(card);
            System.out.println(exp.getMessage());
        }
    }


    public static Card getInstance(String shortCodeForCard) {
        Card card = cards.get(shortCodeForCard);
        if (card == null) {
            card = new Card(shortCodeForCard);
            cards.put(shortCodeForCard, card);
        }
        return card;
    }


    public static Card getInstance(Suit suit, FaceCard cardFace) {
        String shortCodeForCard = suit.toString() + cardFace.toString();
        Card card = cards.get(shortCodeForCard);
        if (card == null) {
            card = new Card(suit, cardFace);
            cards.put(shortCodeForCard, card);
        }
        return card;
    }

    public Suit getSuit() {
        return suit;
    }

    public FaceCard getFaceCard() {
        return cardFace;
    }

    public String displayCamelCase() {
        return suit.displayCamelCase() + " " + cardFace.displayCamelCase();
    }

    public String displayOf() {
        return cardFace.displayCamelCase() + " of " + suit.displayCamelCase();
    }

    public String toString() {
        return suit + cardFace.toString();
    }

    public int getSuitThenFaceCardAssociatedValue() {
        int cardFaceValue = getFaceCard().getValue();
        if (this.getSuit() == Suit.CLUBS) {
            return 100 + cardFaceValue;
        } else if (this.getSuit() == Suit.DIAMONDS) {
            return 200 + cardFaceValue;
        } else if (this.getSuit() == Suit.HEARTS) {
            return 300 + cardFaceValue;
        } else {
            return 400 + cardFaceValue;
        }
    }

}
