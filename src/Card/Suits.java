package Card;

public enum Suits {
    HEARTS("h"),
    DIAMONDS("d"),
    SPADES("s"),
    CLUBS("c");

    private final String letterRepresentation;

    Suits(String letterRepresentation) {
        this.letterRepresentation = letterRepresentation;
    }

    @Override
    public String toString() {
        return letterRepresentation;
    }
}