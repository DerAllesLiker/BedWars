package de.jonasbro.bedwars.listener;

import de.jonasbro.bedwars.utils.GameState;
import de.jonasbro.bedwars.main.Main;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildListener implements Listener {

    private final Main main;

    public BuildListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (GameState.getGameState() == GameState.LOBBY) {
            if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                e.setCancelled(true);
            }
        }
    }
}