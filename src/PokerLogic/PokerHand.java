package PokerLogic;

import Card.*;

import java.util.*;

public class PokerHand {

    private List<Card> pokerHand;

    public PokerHand(List<Card> pokerHand) {
        this.pokerHand = pokerHand;
        sortHandByRank();
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

        Map<Rank, Integer> rankToOccurrences = new HashMap<>();
        Integer numberOfPairs = 0;

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

        //check to see which ranks have an occurrence of 2
        for (Rank rank : rankToOccurrences.keySet()) {
            if (rankToOccurrences.get(rank) == 2) {
                numberOfPairs += 1;
            }
        }

        if (numberOfPairs == 1) {
            return true;
        }
        return false;
    }

    public boolean isFlush() {
        if (pokerHand.get(0).getSuit() == pokerHand.get(1).getSuit() &&
                pokerHand.get(1).getSuit() == pokerHand.get(2).getSuit()) {
            return true;
        }
        return false;
    }

    public boolean isStraight() {

        if (pokerHand.get(0).getRank().getNumericalRepresentation() == pokerHand.get(1).getRank().getNumericalRepresentation()-1 &&
                pokerHand.get(0).getRank().getNumericalRepresentation() == pokerHand.get(2).getRank().getNumericalRepresentation()-2) {
            return true;
        }

        //If there is an A-rank card, change its numerical value to 1 to see if it forms a straight
        if (pokerHand.get(2).getRank() == Rank.ACE) {
            Rank.ACE.setNumericalRepresentation(1);
            sortHandByRank();
            if (pokerHand.get(0).getRank().getNumericalRepresentation() == pokerHand.get(1).getRank().getNumericalRepresentation()-1 &&
                    pokerHand.get(0).getRank().getNumericalRepresentation() == pokerHand.get(2).getRank().getNumericalRepresentation()-2) {
                Rank.ACE.setNumericalRepresentation(14);
                sortHandByRank();
                return true;
            }
            Rank.ACE.setNumericalRepresentation(14);
            sortHandByRank();
        }
        return false;
    }

    public boolean isThreeOfAKind() {
        if (pokerHand.get(0).getRank() == pokerHand.get(1).getRank() &&
                pokerHand.get(1).getRank() == pokerHand.get(2).getRank()) {
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
}
