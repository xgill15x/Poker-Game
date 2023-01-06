package PokerHand;

import java.util.*;
import Card.*;

public class PokerHand {

    private List<Card> pokerHand;

    public PokerHand(List<Card> pokerHand) {
        this.pokerHand = pokerHand;
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
        Integer numberOfMatchingCards = 0;

        for (int i=0; i<pokerHand.size(); i++) {
            for (int j=i+1; j<pokerHand.size(); j++) {
                if (pokerHand.get(i).getRank() == pokerHand.get(j).getRank() && numberOfMatchingCards == 0) {
                    numberOfMatchingCards = 2;
                }
                else if (pokerHand.get(i).getRank() == pokerHand.get(j).getRank() && numberOfMatchingCards != 0) {
                    numberOfMatchingCards += 1;
                }
            }
        }

        if (numberOfMatchingCards == 2) {
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
}
