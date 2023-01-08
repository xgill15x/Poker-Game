package Card;

import java.util.*;

public class Card {
    private final Ranks rank;
    private final Suits suit;

    private static final Set<String> takenCards = new HashSet<>();

    public Card(String rank, String suit) {
        this.rank = stringToRank(rank);
        this.suit = stringToSuit(suit);
        checkCardAvailability();
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
        //exit if rank is invalid
        System.out.println("Card rank:" + cardRank + " is invalid...");
        System.exit(1);
        return null;
    }

    public Suits stringToSuit(String cardSuit) {
        for (Suits suit : Suits.values()) {
            if (suit.toString().equals(cardSuit)) {
                return suit;
            }
        }
        //exit if suit is invalid
        System.out.println("Card suit:" + cardSuit + " is invalid...");
        System.exit(1);
        return null;
    }

    public void checkCardAvailability() {
        String cardInCharacterRepresentation = rank.toString().concat(suit.toString());
        if (takenCards.contains(cardInCharacterRepresentation)) {
            System.out.println("Card:" + rank.toString() + suit.toString() + " has already been dealt...");
            System.exit(1);
        }
        else {
            takenCards.add(cardInCharacterRepresentation);
        }
    }
}