package Card;

public enum Suits {
    HEARTS("h"),
    DIAMONDS("d"),
    SPADES("s"),
    CLUBS("c");

    private final String letterRepresentation;

    /** Converts the letter representation of a card's suit to a suit object
     * @param letterRepresentation A string containing a character representing the suit.
     * @return A Suit object.
    */
    Suits(String letterRepresentation) {
        this.letterRepresentation = letterRepresentation;
    }

    @Override
    public String toString() {
        return letterRepresentation;
    }
}