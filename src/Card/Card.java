package Card;

import java.util.*;

public class Card {
    private final Ranks rank;
    private final Suits suit;

    private static final Set<String> takenCards = new HashSet<>();

    /** Creates a Card with the specified rank & suit.
     * @param rank The employee’s last name.
     * @param suit The employee’s first name.
    */
    public Card(String rank, String suit) {
        this.rank = stringToRank(rank);
        this.suit = stringToSuit(suit);
        checkCardAvailability();
    }

    /** Gets the card's rank
     * @return A rank object representing the card's rank.
    */
    public Ranks getRank() {
        return rank;
    }

    /** Gets the Card's suit
     * @return A suit object representing the card's suit.
    */
    public Suits getSuit() {
        return suit;
    }

    /** Converts the letter representation of a card's rank to a rank object
     * @param cardRank A string containing a character that represents the card's rank.
     * @return A rank object representing the card's rank.
    */
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

    /** Converts the letter representation of a card's suit to a suit object
     * @param cardSuit A string containing a character that represents the card's suit.
     * @return A suit object representing the card's suit.
    */
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

    /** Checks the takenCards set to see if the card has already been dealt.
     * @return Nothing if the card is available, but exits with status 1 if the card has been dealt
    */
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