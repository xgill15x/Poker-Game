
# Mark43 three-card poker game 🎲🃏

This is a three-card poker game made using Java.

## Compilation steps:
* Install and use Java SE Development Kit 13.0.2 as the project SDK(Latest version of Java 13) [GET IT HERE](https://www.oracle.com/java/technologies/javase/jdk13-archive-downloads.html)

* Navigate to `Poker.java` found in src/
* To compile the program, run the following command `javac Poker.java`
* To run tests, run the following command `./run_tests "java Poker"`

## Design decisions:
* Logic was developed such that hand size is not limited to 3.
* Classes were made for each logical component found in poker. (Player, Card, PokerHand, PokerJudge, etc.).
* Enumerators were used to cleanly represent ranks, suits & hand types.

##  Known bugs or limitations
* None that I am aware of.