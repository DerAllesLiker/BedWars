package de.jonasbro.bedwars.listener;

import de.jonasbro.bedwars.utils.GameState;
import de.jonasbro.bedwars.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeListener implements Listener {

    private final Main main;
    public FoodLevelChangeListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent e) {
        GameState.setGameState(GameState.LOBBY);
        if (GameState.getGameState() == GameState.LOBBY) {
            e.setCancelled(true);
        }
    }
}
