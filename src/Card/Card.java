package Card;

import java.util.*;

public class Card {
    private final Ranks rank;
    private final Suits suit;

    private static final Set<String> takenCards = new HashSet<>();

    public Card(String suit, String rank) {
        this.rank = stringToRank(rank);
        this.suit = stringToSuit(suit);

        String cardInCharacterRepresentation = this.rank.toString().concat(this.suit.toString());
        checkCardValidity(cardInCharacterRepresentation);
    }

    public Ranks getRank() {
        return rank;
    }

    public Suits getSuit() {
        return suit;
    }

    public Ranks stringToRank(String cardRank) {
        for (Ranks rank : Ranks.values()) {
            if (rank.toString().equals(cardRank)) {
                return rank;
            }
        }

        System.out.println("Card rank:" + cardRank + " does not exist...");
        System.exit(1);
        return null;
    }

    public Suits stringToSuit(String cardSuit) {
        for (Suits suit : Suits.values()) {
            if (suit.toString().equals(cardSuit)) {
                return suit;
            }
        }

        System.out.println("Card suit:" + cardSuit + " does not exist...");
        System.exit(1);
        return null;
    }

    public void checkCardValidity(String cardInCharacterRepresentation) {
        if (takenCards.contains(cardInCharacterRepresentation)) {
            System.out.println("Card:" + this.rank.toString() + this.suit.toString() + " has already been dealt...");
            System.exit(1);
        }
        else {
            takenCards.add(cardInCharacterRepresentation);
        }
    }
}
