import Player.Player;

import java.util.*;

public class Poker {

    public static Integer numberOfPlayers = 0;
    public static List<Player> listOfPlayers = new ArrayList<>();

    public static void main(String[] args) {
        getPokerGameData();
    }

    public static void getPokerGameData() {
        Scanner scanner = new Scanner(System.in);

        //get # of players
         numberOfPlayers = Integer.parseInt(scanner.nextLine());

        //populate playerIds and player hands
        for (int i = 0; i < numberOfPlayers; i++) {
            String[] playerInfo = scanner.nextLine().split(" ", 2);

            Integer playerId = Integer.parseInt(playerInfo[0]);
            String playerHand = playerInfo[1];

            listOfPlayers.add(new Player(playerId, playerHand));
        }

        scanner.close();
    }
}
