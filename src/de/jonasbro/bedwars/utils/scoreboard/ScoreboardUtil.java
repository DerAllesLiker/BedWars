package de.jonasbro.bedwars.utils.scoreboard;

import com.sun.applet2.AppletParameters;
import de.jonasbro.bedwars.countdowns.GameManager;
import de.jonasbro.bedwars.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;

public class ScoreboardUtil {
    private Scoreboard scoreboard;
    private static HashMap<String, Team> teams = new HashMap<>();

    private static Main main;

    public ScoreboardUtil(Scoreboard scoreboard) {
        this.main = main;
        generate();
    }

    public static void generate() {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("abcd", "abcd");
        for (String teamnumber : main.getConfig().getConfigurationSection("location.spawn").getKeys(false)) {
            Team team = scoreboard.registerNewTeam("00000" + teamnumber);
            team.setPrefix("§bTeam #" + teamnumber + " | §7");
            team.setAllowFriendlyFire(false);
            teams.put(teamnumber, team);
            GameManager.playersInTeam.put(teamnumber, new ArrayList<Player>());
        }
    }

    public void show(Player p) {
        p.setScoreboard(scoreboard);
    }
    public void setTeam(String playername, String teamname) {
        teams.get(teamname).addEntry(playername);
        Bukkit.getOnlinePlayers().forEach(p -> p.setScoreboard(scoreboard));
    }
}




