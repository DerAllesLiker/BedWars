package de.jonasbro.bedwars.listener;

import de.jonasbro.bedwars.countdowns.LobbyCountDown;
import de.jonasbro.bedwars.utils.GameState;
import de.jonasbro.bedwars.utils.ItemBuilder;
import de.jonasbro.bedwars.main.Main;
import de.jonasbro.bedwars.utils.scoreboard.ScoreboardUtil;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;


public class JoinListener implements Listener {

    private final Main main;
    private Object Scoreboard;
    private Object PlayerJoinEvent;
    private Object ScoreboardUtil;

    void createScoreboard(Scoreboard scoreboard) {

    }

    public JoinListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (GameState.getGameState() == GameState.LOBBY)
        e.setJoinMessage(main.prefix + "§a" + e.getPlayer().getDisplayName() + " §7joined the game.");
        try {
            e.getPlayer().getScoreboard();
            e.getPlayer().setGameMode(GameMode.SURVIVAL);
            e.getPlayer().getInventory().clear();
            e.getPlayer().getInventory().setArmorContents(null);
            e.getPlayer().updateInventory();
            e.getPlayer().setExp(0);
            e.getPlayer().setFoodLevel(20);
            e.getPlayer().setHealth(20);
            e.getPlayer().setMaxHealth(20);
            e.getPlayer().setAllowFlight(false);
            e.getPlayer().setLevel(0);
            e.getPlayer().teleport ((Location) main.getConfig().get("location.lobby"));

            LobbyCountDown.start(false);

            e.getPlayer().getInventory().setItem(0, new ItemBuilder(Material.COMPASS).name("§6Choose Team!").build());
            e.getPlayer().getInventory().setItem(1, new ItemBuilder(Material.NETHER_STAR).name("§6Achievements!").build());
        } catch (NullPointerException e1) {
            e.getPlayer().sendMessage(main.prefix + "§cThe Plugin is not configured completely yet!");
        }

    }
}
