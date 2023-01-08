package Card;

import java.util.*;

public class Card {
    private final Suits suit;
    private final Ranks rank;

    private static Set<String> takenCards = new HashSet<>();

    public Card(String suit, String rank) {
        this.suit = stringToSuit(suit);
        this.rank = stringToRank(rank);

        String cardInCharacterRepresentation = this.rank.toString().concat(this.suit.toString());
        if (takenCards.contains(cardInCharacterRepresentation)) {
            System.out.println("Card: " + this.rank.toString() + this.suit.toString() + " has already been dealt...");
        }
        else {
            takenCards.add(cardInCharacterRepresentation);
        }
    }

    public Suits getSuit() {
        return suit;
    }

    public Ranks getRank() {
        return rank;
    }

    public Suits stringToSuit(String cardSuit) {
        for (Suits suit : Suits.values()) {
            if (suit.toString().equals(cardSuit)) {
                return suit;
            }
        }

        System.out.println("One or more suits/ranks do not exist...");
        System.exit(1);
        return null;
    }

    public Ranks stringToRank(String cardRank) {
        for (Ranks rank : Ranks.values()) {
            if (rank.toString().equals(cardRank)) {
                return rank;
            }
        }

        System.out.println("One or more suit/ranks do not exist...");
        System.exit(1);
        return null;
    }
}
