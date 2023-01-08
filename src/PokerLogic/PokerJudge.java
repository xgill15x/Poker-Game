package PokerLogic;

import Card.*;
import Player.*;

import java.util.*;

public class PokerJudge {

    private List<Player> players;

    private List<Player> straightFlushPlayers = new ArrayList<>();
    private List<Player> threeOfAKindPlayers = new ArrayList<>();;
    private List<Player> straightPlayers = new ArrayList<>();;
    private List<Player> flushPlayers = new ArrayList<>();;
    private List<Player> pairPlayers = new ArrayList<>();;
    private List<Player> highCardPlayers = new ArrayList<>();;

//    private static Integer numberOfPlayers = 0;

    public PokerJudge(List<Player> players) {
        this.players = players;

        for (Player player : this.players) {
            switch (player.getCurrentHand().determineHandType()) {
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

//        numberOfPlayers = straightFlushPlayers.size() + threeOfAKindPlayers.size() +
//                          straightPlayers.size() + flushPlayers.size() +
//                          pairPlayers.size() + highCardPlayers.size();
    }

    public List<Player> getWinners() {
        if (straightFlushPlayers.isEmpty()) {
            if (threeOfAKindPlayers.isEmpty()) {
                if (straightPlayers.isEmpty()) {
                    if (flushPlayers.isEmpty()) {
                        if (pairPlayers.isEmpty()) {
                            if (highCardPlayers.isEmpty()) {

                            }
                            return players;
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

    private List<Player> breakTiesAndGetWinner(List<Player> typeOfPlayers) {
        /*
        * For all straight-flush players, compare their highest cards to determine a winner.
        * In case of a tie, move on to the second highest, then third ...
        * */
        List<Player> winners = new ArrayList<>();

        for (int i=players.get(0).getCurrentHand().getCards().size()-1; i>-1; i--) {
            Integer largestRank = 0;
            for (Player player : typeOfPlayers) {
                Integer rankOfCardBeingCompared = player.getCurrentHand().getCardAt(i).getRank().getNumericalRepresentation();

                if (rankOfCardBeingCompared > largestRank) {
                    winners.clear();
                    largestRank = rankOfCardBeingCompared;
                    winners.add(player);
                }
                else if (rankOfCardBeingCompared == largestRank) {
                    winners.add(player);
                }
            }

            if (winners.size() == 1) {
                break;
            }

            if (i > 0) {
                winners.clear();
            }
        }

        return winners;
    }

    private List<Player> breakPairTiesAndGetWinner(List<Player> typeOfPlayers) {
        Integer nOfAKind = 0;

        List<Player> winners = new ArrayList<>();
        Integer largestRank = 0;

        if (typeOfPlayers == pairPlayers) {
            nOfAKind = 2;
        }
        else if (typeOfPlayers == threeOfAKindPlayers) {
            nOfAKind = 3;
        }

        for (Player player : typeOfPlayers) {
            Map<Ranks, Integer> rankToOccurrenceMap = player.getCurrentHand().getRankToOccurrencesMap();
            PokerHand significantCards = new PokerHand();

            for (Card card : player.getCurrentHand().getCards()) {
                if (rankToOccurrenceMap.get(card.getRank()) == nOfAKind) {
                    significantCards.addCard(card);
                    break;
                }
            }

            Integer pairRank = significantCards.getCardAt(0).getRank().getNumericalRepresentation();
            if (pairRank > largestRank) {
                winners.clear();
                largestRank = pairRank;
                winners.add(player);
            }
            else if (pairRank == largestRank) {
                winners.add(player);
            }
        }

        //If the pairs are tied, then compare kicker cards
        if (winners.size() > 1) {
            winners.clear();

            for (Player player : typeOfPlayers) {
                Map<Ranks, Integer> rankToOccurrenceMap = player.getCurrentHand().getRankToOccurrencesMap();
                PokerHand sideCards = new PokerHand();

                for (Card card : player.getCurrentHand().getCards()) {
                    if (rankToOccurrenceMap.get(card.getRank()) != nOfAKind) {
                        sideCards.addCard(card);
                    }
                }
                sideCards.sortHandByRank();
                player.getCurrentHand().setCards(sideCards.getCards());
            }

            return breakTiesAndGetWinner(typeOfPlayers);
        }
        return winners;
    }


}
