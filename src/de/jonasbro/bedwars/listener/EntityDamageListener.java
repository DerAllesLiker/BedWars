package de.jonasbro.bedwars.listener;

import de.jonasbro.bedwars.utils.GameState;
import de.jonasbro.bedwars.main.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    private final Main main;

    public EntityDamageListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (GameState.getGameState() == GameState.LOBBY) {
            if (e.getEntity() instanceof Player) {
                if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
                    e.getEntity().teleport((Location) main.getConfig().get("location.lobby"));
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }
}
