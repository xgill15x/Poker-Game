package PokerLogic;

import Card.*;

import java.util.*;

public class PokerHand {

    private static final Integer numberOfCardsAllowedPerHand = 3;
    private List<Card> cards = new ArrayList<>();

    public PokerHand() {};

    public PokerHand(List<Card> pokerHand) {
        this.cards = pokerHand;
        checkValidInput();
        sortHandByRank();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Card getCardAt(int i) {
        return cards.get(i);
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public Integer getNumberOfCardsAllowedPerHand() {
        return numberOfCardsAllowedPerHand;
    }

    public boolean isPair() {
        Map<Ranks, Integer> rankToOccurrencesMap = getRankToOccurrencesMap();
        Integer numberOfPairs = 0;

        //check to see which ranks have an occurrence of 2
        for (Ranks rank : rankToOccurrencesMap.keySet()) {
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
        Map<Suits, Integer> suitToOccurrencesMap = getSuitToOccurrencesMap();
        Integer numberOfFlushes = 0;

        for (Suits suit : suitToOccurrencesMap.keySet()) {
            if (suitToOccurrencesMap.get(suit)-1 == cards.size()) {
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

        for (int i=0; i<cards.size(); i++) {
            if (cards.get(0).getRank().getNumericalRepresentation() != cards.get(i).getRank().getNumericalRepresentation() - i) {
                isStraight = false;
            }
        }

        //If there is an A-rank card, change its numerical value to 1 to see if it forms a straight
        if (cards.get(cards.size()-1).getRank() == Ranks.ACE && isStraight == false) {
            Ranks.ACE.setNumericalRepresentation(1);
            sortHandByRank();

            isStraight = true;
            for (int i=0; i<cards.size(); i++) {
                if (cards.get(0).getRank().getNumericalRepresentation() != cards.get(i).getRank().getNumericalRepresentation() - i) {
                    Ranks.ACE.setNumericalRepresentation(14);
                    sortHandByRank();
                    isStraight = false;
                }
            }
            Ranks.ACE.setNumericalRepresentation(14);
            sortHandByRank();
        }

        return isStraight;
    }

    public boolean isThreeOfAKind() {
        Map<Ranks, Integer> rankToOccurrencesMap = getRankToOccurrencesMap();
        Integer numberOfThreeOfAKinds = 0;

        //check to see which ranks have an occurrence of 3
        for (Ranks rank : rankToOccurrencesMap.keySet()) {
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

    public HandTypes determineHandType() {
        HandTypes handType = HandTypes.HIGH_CARD;

        if (isPair()) {
            handType = HandTypes.PAIR;
        }
        if (isFlush()) {
            handType = HandTypes.FLUSH;
        }
        if (isStraight()) {
            handType = HandTypes.STRAIGHT;
        }
        if (isThreeOfAKind()) {
            handType = HandTypes.THREE_OF_A_KIND;
        }
        if (isStraightFlush()) {
            handType = HandTypes.STRAIGHT_FLUSH;
        }

        return handType;
    }

    public Map<Ranks, Integer> getRankToOccurrencesMap() {
        Map<Ranks, Integer> rankToOccurrences = new HashMap<>();

        //populate map with number of occurrences for each rank
        for (int i=0; i<cards.size(); i++) {
            for (int j=i+1; j<cards.size(); j++) {
                if (cards.get(i).getRank() == cards.get(j).getRank()) {
                    if (rankToOccurrences.containsKey(cards.get(i).getRank())) {
                        Integer oldOccurrenceValue = rankToOccurrences.get(cards.get(i).getRank());
                        rankToOccurrences.put(cards.get(i).getRank(), oldOccurrenceValue + 1);
                    }
                    else {
                        rankToOccurrences.put(cards.get(i).getRank(), 2);
                    }
                }
            }
        }

        return rankToOccurrences;
    }

    public Map<Suits, Integer> getSuitToOccurrencesMap() {
        Map<Suits, Integer> suitToOccurrences = new HashMap<>();

        //populate map with number of occurrences for each rank
        for (int i=0; i<cards.size(); i++) {
            for (int j=i+1; j<cards.size(); j++) {
                if (cards.get(i).getSuit() == cards.get(j).getSuit()) {
                    if (suitToOccurrences.containsKey(cards.get(i).getSuit())) {
                        Integer oldOccurrenceValue = suitToOccurrences.get(cards.get(i).getSuit());
                        suitToOccurrences.put(cards.get(i).getSuit(), oldOccurrenceValue + 1);
                    }
                    else {
                        suitToOccurrences.put(cards.get(i).getSuit(), 2);
                    }
                }
            }
        }

        return suitToOccurrences;
    }

    public void sortHandByRank() {
        for (int i = 0; i < cards.size()-1; i++) {

            int min_idx = i;
            for (int j = i+1; j < cards.size(); j++) {
                if (cards.get(j).getRank().getNumericalRepresentation() <
                        cards.get(min_idx).getRank().getNumericalRepresentation()) {
                    min_idx = j;
                }
            }

            Card temp = cards.get(min_idx);
            cards.set(min_idx, cards.get(i));
            cards.set(i, temp);
        }
    }

    public void checkValidInput() {
        if (cards.size() > numberOfCardsAllowedPerHand) {
            System.out.println("Too many cards per hand...");
            System.exit(1);
        }
        else if (cards.size() < numberOfCardsAllowedPerHand) {
            System.out.println("Too few cards per hand...");
            System.exit(1);
        }
    }

//    public void clear() {
//        cards.clear();
//    }
}
