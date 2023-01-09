package Player;

import Card.*;
import PokerLogic.*;

import java.util.*;

public class Player {

    private final Integer playerId;
    private final PokerHand pokerHand;

    private static final Set<Integer> takenPlayerIds = new HashSet<>();

    /** Sets the numerical representation of a card's rank. Currently only used for the ACE case.
     * @param playerId The player's identification number in integer form.
     * @param pokerHand The player's cards in a string form.
    */
    public Player(Integer playerId, String pokerHand) {
        this.playerId = playerId;
        checkPlayerIdAvailability();

        this.pokerHand = new PokerHand(stringToCardList(pokerHand));
    }

    /** Gets the player's identification number.
     * @return An Integer containing the player's identification number.
    */
    public Integer getPlayerId() {
        return playerId;
    }

    /** Gets the player's cards wrapped in a PokerHand object.
     * @return An PokerHand object containing the player's cards.
    */
    public PokerHand getPokerHand() {
        return pokerHand;
    }

    /** Convert's cards in string form to a list of Card objects.
     * @param pokerHand Cards in string form
     * @return An Integer containing the player's identification number.
    */
    public List<Card> stringToCardList(String pokerHand) {
        String[] splitPokerHand = pokerHand.split("\\s+");

        List<Card> cards = new ArrayList<>();
        for (String card : splitPokerHand) {
            if (card.trim().length() != 2) {
                System.out.println("Character representations of cards should ONLY be 2 characters long...");
                System.exit(1);
            }
            String rank = Character.toString(card.charAt(0));
            String suit = Character.toString(card.charAt(1));
            cards.add(new Card(rank, suit));
        }
        return cards;
    }

    /** Checks the takenPlayersIds set to see if the player identification number is already taken.
     * @return Nothing if the player's identification number is valid, but exits with status 1 if the player identification number taken.
    */
    public void checkPlayerIdAvailability() {
        if (takenPlayerIds.contains(playerId)) {
            System.out.println("PlayerID:" + this.getPlayerId().toString() + " has already been taken...");
            System.exit(1);
        }
        else {
            takenPlayerIds.add(playerId);
        }
    }
}