package PokerLogic;

import Card.*;

import java.util.*;

public class PokerHand {

    private List<Card> cards = new ArrayList<>();
    private static final Integer numberOfCardsAllowedPerHand = 3;

    public PokerHand() {}

    /** Creates a PokerHand with the list of Card objects provided.
     * @param  pokerHand a list of Card objects.
     * @return A PokerHand Object containing the list of Card objects.
    */
    public PokerHand(List<Card> pokerHand) {
        this.cards = pokerHand;
        checkNumberOfCardsValidity();
        sortHandByRank();
    }

    /** Gets the cards in the poker hand.
     * @return A list of Card objects.
    */
    public List<Card> getCards() {
        return cards;
    }

    /** Sets the cards in the poker hand.
     * @return Nothing, the cards have been set.
    */
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    /** Gets the card at index i in the poker hand.
     * @param i The index of the specified card.
     * @return The Card object at index i.
    */
    public Card getCardAt(int i) {
        return cards.get(i);
    }

    /** Appends the card to the poker hand.
     * @param card The Card object to be appended.
     * @return Nothing, the card has been appended.
    */
    public void appendCard(Card card) {
        this.cards.add(card);
    }

    /** Determines the best hand that can be formed with 3 ca.
     * @return A HandType object representing the type of hand.
    */
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

    /** Checks to see if the poker hand is a pair.
     * @return A boolean representing if the poker hand is a pair.
    */
    public boolean isPair() {
        Map<Ranks, Integer> rankToOccurrencesMap = getRankToOccurrencesMap();
        int numberOfPairs = 0;

        //check to see which ranks have an occurrence of 2
        for (Ranks rank : rankToOccurrencesMap.keySet()) {
            if (rankToOccurrencesMap.get(rank) == 2) {
                numberOfPairs += 1;
            }
        }
        return numberOfPairs == 1;
    }

    /** Checks to see if the poker hand is a flush.
     * @return A boolean representing if the poker hand is a flush.
    */
    public boolean isFlush() {
        Map<Suits, Integer> suitToOccurrencesMap = getSuitToOccurrencesMap();
        int numberOfFlushes = 0;

        for (Suits suit : suitToOccurrencesMap.keySet()) {
            if (suitToOccurrencesMap.get(suit) == cards.size()) {
                numberOfFlushes += 1;
            }
        }
        return numberOfFlushes == 1;
    }

    /** Checks to see if the poker hand is a straight.
     * @return A boolean representing if the poker hand is a straight.
    */
    public boolean isStraight() {
        boolean isStraight = true;

        for (int i=0; i<cards.size(); i++) {
            if (cards.get(0).getRank().getNumericalRepresentation() != cards.get(i).getRank().getNumericalRepresentation() - i) {
                isStraight = false;
                break;
            }
        }

        //If there is an A-rank card, change its numerical value to 1 to see if it forms a straight
        if (cards.get(cards.size()-1).getRank() == Ranks.ACE && !isStraight) {
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

    /** Checks to see if the poker hand is a three-of-a-kind.
     * @return A boolean representing if the poker hand is a three-of-a-kind.
    */
    public boolean isThreeOfAKind() {
        Map<Ranks, Integer> rankToOccurrencesMap = getRankToOccurrencesMap();
        int numberOfThreeOfAKinds = 0;

        //check to see which ranks have an occurrence of 3
        for (Ranks rank : rankToOccurrencesMap.keySet()) {
            if (rankToOccurrencesMap.get(rank) == 3) {
                numberOfThreeOfAKinds += 1;
            }
        }
        return numberOfThreeOfAKinds == 1;
    }

    /** Checks to see if the poker hand is a straight-flush.
     * @return A boolean representing if the poker hand is a straight-flush.
    */
    public boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

    /** Creates a Map that pairs up every unique rank found in the poker hand to the number of times it occurs.
     * @return A Map object representing rank to occurrences.
    */
    public Map<Ranks, Integer> getRankToOccurrencesMap() {
        Map<Ranks, Integer> rankToOccurrences = new HashMap<>();

        //populate map with number of occurrences for each rank
        for (Card card : cards) {
            if (rankToOccurrences.containsKey(card.getRank())) {
                Integer oldOccurrenceValue = rankToOccurrences.get(card.getRank());
                rankToOccurrences.put(card.getRank(), oldOccurrenceValue + 1);
            }
            else {
                rankToOccurrences.put(card.getRank(), 1);
            }
        }
        return rankToOccurrences;
    }

    /** Creates a Map that pairs up every unique suit found in the poker hand to the number of times it occurs.
     * @return A Map object representing suit to occurrences.
    */
    public Map<Suits, Integer> getSuitToOccurrencesMap() {
        Map<Suits, Integer> suitToOccurrences = new HashMap<>();

        //populate map with number of occurrences for each suit
        for (Card card : cards) {
            if (suitToOccurrences.containsKey(card.getSuit())) {
                Integer oldOccurrenceValue = suitToOccurrences.get(card.getSuit());
                suitToOccurrences.put(card.getSuit(), oldOccurrenceValue + 1);
            }
            else {
                suitToOccurrences.put(card.getSuit(), 1);
            }
        }
        return suitToOccurrences;
    }

    /** Sorts the poker hand.
     * @return Nothing, the poker hand has been sorted..
    */
    public void sortHandByRank() {
        //selection sort used for small array sizes
        for (int i=0; i<cards.size()-1; i++) {

            int min_idx = i;
            for (int j=i+1; j<cards.size(); j++) {
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

    /** Checks to see if the number of cards in the poker hand match with the specified amount set for the game.
     * @return Nothing if the number of cards is valid, but exits with status 1 if the number of cards do not match the specified amount.
    */
    public void checkNumberOfCardsValidity() {
        if (cards.size() > numberOfCardsAllowedPerHand) {
            System.out.println("Too many cards per hand...");
            System.exit(1);
        }
        else if (cards.size() < numberOfCardsAllowedPerHand) {
            System.out.println("Too few cards per hand...");
            System.exit(1);
        }
    }
}