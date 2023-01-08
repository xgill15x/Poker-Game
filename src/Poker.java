import Player.*;
import PokerLogic.PokerJudge;

import java.util.*;

public class Poker {

    public static Integer numberOfPlayers = 0;
    public static List<Player> players = new ArrayList<>();

    public static void main(String[] args) {
        getPokerGameData();
        determineWinners();
    }

    public static void getPokerGameData() {
        Scanner scanner = new Scanner(System.in);

        //get number of players
         numberOfPlayers = Integer.parseInt(scanner.nextLine());
         if (numberOfPlayers < 1 || numberOfPlayers > 8) {
             System.out.println("Number of players is invalid. Please confirm it's between 1 and 8...");
             System.exit(1);
         }

        //populate playerIds and player hands
        for (int i = 0; i < numberOfPlayers; i++) {
            String[] playerInfo = scanner.nextLine().split(" ", 2);

            Integer playerId = Integer.parseInt(playerInfo[0]);
            String playerHand = playerInfo[1];
            players.add(new Player(playerId, playerHand));
        }
        scanner.close();
    }

    public static void determineWinners() {
        PokerJudge pokerJudge = new PokerJudge(players);

        StringBuilder winnersMessage = new StringBuilder();
        for (Player player : pokerJudge.getWinners()) {
            winnersMessage.append(player.getPlayerId().toString()).append(" ");
        }

        System.out.println(winnersMessage);
    }
}
