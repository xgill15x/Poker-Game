package Player;

import Card.*;
import PokerLogic.*;

import java.util.*;

public class Player {

    private final Integer playerId;
    private final PokerHand pokerHand;

    private static final Set<Integer> takenPlayerIds = new HashSet<>();

    public Player(Integer playerId, String pokerHand) {
        this.playerId = playerId;
        checkPlayerIdAvailability();

        this.pokerHand = new PokerHand(stringToCardList(pokerHand));
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public PokerHand getPokerHand() {
        return pokerHand;
    }

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