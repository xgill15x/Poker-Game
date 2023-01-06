package Card;

public enum Suit {
    HEARTS("h"),
    DIAMONDS("d"),
    SPADES("s"),
    CLUBS("c");

    private String letterRepresentation;

    Suit(String letterRepresentation) {
        this.letterRepresentation = letterRepresentation;
    }

    @Override
    public String toString() {
        return letterRepresentation;
    }
}