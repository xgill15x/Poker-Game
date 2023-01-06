package Player;

import Card.*;
import PokerHand.*;

import java.util.*;

public class Player {
    private final Integer playerId;
    private final List<Card> currentHand;
    private final HandType handType;

    public Player(Integer playerId, String currentHand) {
        this.playerId = playerId;
        this.currentHand = stringToCardList(currentHand);

        this.handType = (new PokerHand(this.currentHand).determineHandType());

        System.out.println(handType.toString());
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public List<Card> getCurrentHand() {
        return currentHand;
    }

    public List<Card> stringToCardList(String pokerHand) {
        List<Card> cardList = new ArrayList<>();
        String[] splitPokerHand = pokerHand.split(" ");

        for (String card : splitPokerHand) {
            String rank = Character.toString(card.charAt(0));
            String suit = Character.toString(card.charAt(1));

            cardList.add(new Card(suit, rank));
        }

        return cardList;
    }
}
