package Player;

import PokerEquipment.*;

import java.util.*;

public class Player {

    private final Integer playerId;
    private final PokerHand pokerHand;

    private static final Set<Integer> takenPlayerIds = new HashSet<>();

    /** Creates a Player with the specified player identification number and poker hand.
     * @param playerId The player's identification number in integer form.
     * @param pokerHand The player's cards in a string form.
    */
    public Player(Integer playerId, String pokerHand) {
        this.playerId = playerId;
        checkPlayerIdAvailability();

        this.pokerHand = new PokerHand(pokerHand);
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

    /** Checks the takenPlayersIds set to see if the player identification number is already taken.*/
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