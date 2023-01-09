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

    /** Determines the winner of game*/
    public static void determineWinners() {
        PokerJudge pokerJudge = new PokerJudge(players);
        StringBuilder winnersMessage = new StringBuilder();

        for (Player player : pokerJudge.getWinners()) {
            winnersMessage.append(player.getPlayerId().toString()).append(" ");
        }
        System.out.println(winnersMessage.toString().trim());
    }

    /** Gets input, and populates player hands*/
    public static void getPokerGameData() {
        Scanner scanner = new Scanner(System.in);

        //get number of players
        try {
            numberOfPlayers = Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println("'Number of players' contains invalid characters...");
            System.exit(1);
        }

        if (numberOfPlayers < 1 || numberOfPlayers > 8) {
            System.out.println("'Number of players' is invalid. Please confirm it's between 1 and 8...");
            System.exit(1);
        }

        //populate playerIds and player hands
        for (int i=0; i<numberOfPlayers; i++) {
            String[] playerInfo = scanner.nextLine().trim().split(" ", 2);
            try {
                Integer playerId = Integer.parseInt(playerInfo[0].trim());
                String playerHand = playerInfo[1].trim();
                players.add(new Player(playerId, playerHand));
            } catch (Exception e) {
                System.out.println("'Player ID' contains invalid characters...");
                System.exit(1);
            }
        }
        scanner.close();
    }
}