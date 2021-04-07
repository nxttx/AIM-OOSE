package src.main.java.oose.ooad.quebble.DAO.MockData;

import src.main.java.oose.ooad.quebble.service.Player;

public class MockUserData {

    public static Player player1 = buildPlayer("username", "password", 100);

    private static Player buildPlayer(String username, String password, int credits) {
        Player player = new Player();
        player.setUsername(username);
        player.setPassword(password);
        player.setCredits(credits);

        return player;
    }
}
