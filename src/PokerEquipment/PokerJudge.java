package PokerEquipment;

import Card.*;
import Player.*;

import java.util.*;

public class PokerJudge {

    private final List<Player> players;

    private final List<Player> straightFlushPlayers = new ArrayList<>();
    private final List<Player> threeOfAKindPlayers = new ArrayList<>();
    private final List<Player> straightPlayers = new ArrayList<>();
    private final List<Player> flushPlayers = new ArrayList<>();
    private final List<Player> pairPlayers = new ArrayList<>();
    private final List<Player> highCardPlayers = new ArrayList<>();

    /** Creates a Judge that will decide a winner between all the players.
     * @param players A list of all participating players.
    */
    public PokerJudge(List<Player> players) {
        this.players = players;

        for (Player player : this.players) {
            switch (player.getPokerHand().determineHandType()) {
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

    /** Gets the winners of the game based on the all the hands in the game.
     * @return A list of Player objects for all the winners.
    */
    public List<Player> getWinners() {
        if (straightFlushPlayers.isEmpty()) {
            if (threeOfAKindPlayers.isEmpty()) {
                if (straightPlayers.isEmpty()) {
                    if (flushPlayers.isEmpty()) {
                        if (pairPlayers.isEmpty()) {
                            if (highCardPlayers.isEmpty()) {
                                System.out.println("A fatal error has occurred...");
                                System.exit(1);
                            }
                            return breakTiesAndGetWinner(players);
                        }
                        return breakPairTiesAndGetWinner(pairPlayers);
                    }
                    return breakTiesAndGetWinner(flushPlayers);
                }
                return breakTiesAndGetWinner(straightPlayers);
            }
            return breakTiesAndGetWinner(threeOfAKindPlayers);
        }
        return breakTiesAndGetWinner(straightFlushPlayers);
    }

    /** Breaks ties between the same groups of hands. Ties are broken by comparing the largest ranks in the all the poker hands.
     * @param typeOfPlayers A specified groups of hands (E.g. all flushPlayers, all pairPlayers, etc.).
     * @return A list of Person objects for all winners from the specified group.
    */
    private List<Player> breakTiesAndGetWinner(List<Player> typeOfPlayers) {
        List<Player> winners = new ArrayList<>();

        /*
         * For all straight-flush players, compare their highest cards to determine a winner.
         * In case of a tie, move on to the second highest, then third ...
        */
        for (int i=typeOfPlayers.get(0).getPokerHand().getCards().size()-1; i>-1; i--) {
            Integer largestRank = 0;
            for (Player player : typeOfPlayers) {
                Integer rankOfCardBeingCompared = player.getPokerHand().getCardAt(i).getRank().getNumericalRepresentation();

                if (rankOfCardBeingCompared > largestRank) {
                    winners.clear();
                    largestRank = rankOfCardBeingCompared;
                    winners.add(player);
                }
                else if (rankOfCardBeingCompared.equals(largestRank)) {
                    winners.add(player);
                }
            }

            if (winners.size() == 1) {
                break;
            }

            if (i > 0) {
                Set<Player> losers = new HashSet<>();
                for (Player player : players) {
                    if (!winners.contains(player)) {
                        losers.add(player);
                    }
                }
                typeOfPlayers.removeAll(losers);
            }
        }

        winners.sort(Comparator.comparingInt(player -> player.getPlayerId()));
        return winners;
    }

    /** Breaks ties between pair hands. Ties are broken by comparing the pairs. If the pairs are tied the remaining cards in the hand are used to break the tie.
     * @param typeOfPlayers A specified groups of hands (E.g. all flushPlayers, all pairPlayers, etc.).
     * @return A list of Person objects for all winners from the specified group.
    */
    private List<Player> breakPairTiesAndGetWinner(List<Player> typeOfPlayers) {
        List<Player> winners = new ArrayList<>();
        Integer largestRank = 0;

        //retrieve the pair value for all pairPlayers and return the player with the highest pair value
        for (Player player : typeOfPlayers) {
            Map<Ranks, Integer> rankToOccurrenceMap = player.getPokerHand().getRankToOccurrencesMap();
            PokerHand significantCards = new PokerHand();

            for (Card card : player.getPokerHand().getCards()) {
                if (rankToOccurrenceMap.get(card.getRank()) == 2) {
                    significantCards.appendCard(card);
                    break;
                }
            }

            Integer pairRank = significantCards.getCardAt(0).getRank().getNumericalRepresentation();
            if (pairRank > largestRank) {
                winners.clear();
                largestRank = pairRank;
                winners.add(player);
            }
            else if (pairRank.equals(largestRank)) {
                winners.add(player);
            }
        }

        //If the pairs are tied, then compare kicker cards (algo made to also accommodate hands larger than 3 cards)
        if (winners.size() > 1) {
            Set<Player> losers = new HashSet<>();
            for (Player player : players) {
                if (!winners.contains(player)) {
                    losers.add(player);
                }
            }
            typeOfPlayers.removeAll(losers);

            for (Player player : typeOfPlayers) {
                Map<Ranks, Integer> rankToOccurrenceMap = player.getPokerHand().getRankToOccurrencesMap();
                PokerHand sideCards = new PokerHand();

                for (Card card : player.getPokerHand().getCards()) {
                    if (rankToOccurrenceMap.get(card.getRank()) != 2) {
                        sideCards.appendCard(card);
                    }
                }
                sideCards.sortHandByRank();
                player.getPokerHand().setCards(sideCards.getCards());
            }
            return breakTiesAndGetWinner(typeOfPlayers);
        }
        return winners;
    }
}