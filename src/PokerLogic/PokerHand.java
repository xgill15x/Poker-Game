package PokerLogic;

import Card.*;

import java.util.*;

public class PokerHand {

    private static final Integer numberOfCardsAllowedPerHand = 3;
    private final List<Card> pokerHand;

    public PokerHand(List<Card> pokerHand) {
        this.pokerHand = pokerHand;
        checkValidInput();
        sortHandByRank();
    }

    public Integer getNumberOfCardsPerHand() {
        return numberOfCardsAllowedPerHand;
    }

    public HandType determineHandType() {
        HandType handType = HandType.HIGH_CARD;

        if (isPair()) {
            handType = HandType.PAIR;
        }
        if (isFlush()) {
            handType = HandType.FLUSH;
        }
        if (isStraight()) {
            handType = HandType.STRAIGHT;
        }
        if (isThreeOfAKind()) {
            handType = HandType.THREE_OF_A_KIND;
        }
        if (isStraightFlush()) {
            handType = HandType.STRAIGHT_FLUSH;
        }

        return handType;
    }

    public boolean isPair() {
        Map<Rank, Integer> rankToOccurrencesMap = getRankToOccurrencesMap();
        Integer numberOfPairs = 0;

        //check to see which ranks have an occurrence of 2
        for (Rank rank : rankToOccurrencesMap.keySet()) {
            if (rankToOccurrencesMap.get(rank) == 2) {
                numberOfPairs += 1;
            }
        }

        if (numberOfPairs == 1) {
            return true;
        }
        return false;
    }

    public boolean isFlush() {
        Map<Suit, Integer> suitToOccurrencesMap = getSuitToOccurrencesMap();
        Integer numberOfFlushes = 0;

        for (Suit suit : suitToOccurrencesMap.keySet()) {
            if (suitToOccurrencesMap.get(suit)-1 == pokerHand.size()) {
                numberOfFlushes += 1;
            }
        }

        if (numberOfFlushes == 1) { //there should only ever be a single flush
            return true;
        }
        return false;
    }

    public boolean isStraight() {
        boolean isStraight = true;

        for (int i=0; i<pokerHand.size(); i++) {
            if (pokerHand.get(0).getRank().getNumericalRepresentation() != pokerHand.get(i).getRank().getNumericalRepresentation() - i) {
                isStraight = false;
            }
        }

        //If there is an A-rank card, change its numerical value to 1 to see if it forms a straight
        if (pokerHand.get(pokerHand.size()-1).getRank() == Rank.ACE && isStraight == false) {
            Rank.ACE.setNumericalRepresentation(1);
            sortHandByRank();

            isStraight = true;
            for (int i=0; i<pokerHand.size(); i++) {
                if (pokerHand.get(0).getRank().getNumericalRepresentation() != pokerHand.get(i).getRank().getNumericalRepresentation() - i) {
                    Rank.ACE.setNumericalRepresentation(14);
                    sortHandByRank();
                    isStraight = false;
                }
            }
            Rank.ACE.setNumericalRepresentation(14);
            sortHandByRank();
        }

        return isStraight;
    }

    public boolean isThreeOfAKind() {
        Map<Rank, Integer> rankToOccurrencesMap = getRankToOccurrencesMap();
        Integer numberOfThreeOfAKinds = 0;

        //check to see which ranks have an occurrence of 3
        for (Rank rank : rankToOccurrencesMap.keySet()) {
            if (rankToOccurrencesMap.get(rank)-1 == 3) {
                numberOfThreeOfAKinds += 1;
            }
        }

        if (numberOfThreeOfAKinds == 1) {
            return true;
        }
        return false;
    }

    public boolean isStraightFlush() {
        if (isStraight() && isFlush()) {
            return true;
        }
        return false;
    }

    public Map<Rank, Integer> getRankToOccurrencesMap() {
        Map<Rank, Integer> rankToOccurrences = new HashMap<>();

        //populate map with number of occurrences for each rank
        for (int i=0; i<pokerHand.size(); i++) {
            for (int j=i+1; j<pokerHand.size(); j++) {
                if (pokerHand.get(i).getRank() == pokerHand.get(j).getRank()) {
                    if (rankToOccurrences.containsKey(pokerHand.get(i).getRank())) {
                        Integer oldOccurrenceValue = rankToOccurrences.get(pokerHand.get(i).getRank());
                        rankToOccurrences.put(pokerHand.get(i).getRank(), oldOccurrenceValue + 1);
                    }
                    else {
                        rankToOccurrences.put(pokerHand.get(i).getRank(), 2);
                    }
                }
            }
        }

        return rankToOccurrences;
    }

    public Map<Suit, Integer> getSuitToOccurrencesMap() {
        Map<Suit, Integer> suitToOccurrences = new HashMap<>();

        //populate map with number of occurrences for each rank
        for (int i=0; i<pokerHand.size(); i++) {
            for (int j=i+1; j<pokerHand.size(); j++) {
                if (pokerHand.get(i).getSuit() == pokerHand.get(j).getSuit()) {
                    if (suitToOccurrences.containsKey(pokerHand.get(i).getSuit())) {
                        Integer oldOccurrenceValue = suitToOccurrences.get(pokerHand.get(i).getSuit());
                        suitToOccurrences.put(pokerHand.get(i).getSuit(), oldOccurrenceValue + 1);
                    }
                    else {
                        suitToOccurrences.put(pokerHand.get(i).getSuit(), 2);
                    }
                }
            }
        }

        return suitToOccurrences;
    }

    public void sortHandByRank() {
        for (int i = 0; i < pokerHand.size()-1; i++) {

            int min_idx = i;
            for (int j = i+1; j < pokerHand.size(); j++) {
                if (pokerHand.get(j).getRank().getNumericalRepresentation() <
                        pokerHand.get(min_idx).getRank().getNumericalRepresentation()) {
                    min_idx = j;
                }
            }

            Card temp = pokerHand.get(min_idx);
            pokerHand.set(min_idx, pokerHand.get(i));
            pokerHand.set(i, temp);
        }
    }

    public void checkValidInput() {
        if (pokerHand.size() > numberOfCardsAllowedPerHand) {
            System.out.println("Too many cards per hand...");
            System.exit(1);
        }
        else if (pokerHand.size() < numberOfCardsAllowedPerHand) {
            System.out.println("Too few cards per hand...");
            System.exit(1);
        }
    }
}
