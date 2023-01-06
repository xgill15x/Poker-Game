package Card;

public enum Rank {
    Two("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("T", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("K", 13),
    ACE("A", 14);

    private String letterRepresentation;
    private Integer numericalRepresentation;

    Rank(String letterRepresentation, Integer numericalRepresentation) {
        this.letterRepresentation = letterRepresentation;
        this.numericalRepresentation = numericalRepresentation;
    }

    public Integer getNumericalRepresentation() {
        return numericalRepresentation;
    }

    @Override
    public String toString() {
        return letterRepresentation;
    }
}