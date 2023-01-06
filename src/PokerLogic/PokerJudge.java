package PokerLogic;

import Player.*;

import java.util.*;

public class PokerJudge {

    private List<Player> straightFlushPlayers;
    private List<Player> threeOfAKindPlayers;
    private List<Player> straightPlayers;
    private List<Player> flushPlayers;
    private List<Player> pairPlayers;
    private List<Player> highCardPlayers;

    public PokerJudge(List<Player> players) {
        for (Player player : players) {
            switch (player.getHandType()) {
                case STRAIGHT_FLUSH:
                    straightFlushPlayers.add(player);
                    break;
                case THREE_OF_A_KIND:
                    threeOfAKindPlayers.add(player);
                    break;
                case STRAIGHT:
                    straightPlayers.add(player);
                    break;
                case FLUSH:
                    flushPlayers.add(player);
                    break;
                case PAIR:
                    pairPlayers.add(player);
                    break;
                case HIGH_CARD:
                    highCardPlayers.add(player);
                    break;
            }
        }
    }
}
