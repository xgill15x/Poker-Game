package Player;

import Card.*;
import PokerLogic.*;

import java.util.*;

public class Player {
    private final Integer playerId;
    private final PokerHand currentHand;

    public Player(Integer playerId, String currentHand) {
        this.playerId = playerId;
        this.currentHand = new PokerHand(stringToCardList(currentHand));

        System.out.println(this.currentHand.determineHandType().toString());
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public PokerHand getCurrentHand() {
        return currentHand;
    }

    public List<Card> stringToCardList(String pokerHand) {
        String[] splitPokerHand = pokerHand.split(" ");

        List<Card> cards = new ArrayList<>();
        for (String card : splitPokerHand) {
            String rank = Character.toString(card.charAt(0));
            String suit = Character.toString(card.charAt(1));

            cards.add(new Card(suit, rank));
        }

//        if (currentHand.getNumberOfCardsPerHand() < cards.size()) {
//            System.out.println("Too many cards per hand...");
//            System.exit(1);
//        }
//        else if (currentHand.getNumberOfCardsPerHand() > cards.size()) {
//            System.out.println("Too few cards per hand...");
//            System.exit(1);
//        }

        return cards;
    }
}
