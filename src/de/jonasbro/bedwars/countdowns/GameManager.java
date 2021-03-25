package de.jonasbro.bedwars.countdowns;

import de.jonasbro.bedwars.utils.GameState;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameManager {

    public static HashMap<String, List<Player>> playersInTeam = new HashMap<>();

    public static void start() {
        GameState.setGameState(GameState.INGAME);
    }

    public static void setPlayerTeam(Player p, String team) {
        List<Player> list = new ArrayList<>();
        if (playersInTeam.containsKey(team)) {
            list = playersInTeam.get(team);
        }
        list.add(p);
        playersInTeam.put(team, list);
    }

    public static List<Player> getTeamPlayers(String team) {
        return playersInTeam.get(team);
    }
}
