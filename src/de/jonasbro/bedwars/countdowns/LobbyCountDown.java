package de.jonasbro.bedwars.countdowns;

import de.jonasbro.bedwars.utils.GameState;
import de.jonasbro.bedwars.main.Main;
import org.bukkit.Bukkit;

public class LobbyCountDown {

    static boolean isStarted;
    static int count = 60;
    static int shed;

    public static void start(boolean force) {
        if (GameState.getGameState() != GameState.LOBBY) {
            return;
        }
        if (force || Bukkit.getOnlinePlayers().size() == 2) {
            if (!isStarted) {
                if (force) {
                    count = 60;
                }
                shed = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {
                    @Override
                    public void run() {
                        Bukkit.getOnlinePlayers().forEach(p -> p.setLevel(count));
                        if (count == 60 || count == 45 || count == 30 || count == 15 || count <= 5 && count != 0) {
                            Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(Main.getPlugin(Main.class).prefix
                            + "§aThe game starts in §8" + count + " seconds§a!"));
                        } else if (count == 0) {
                            Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(Main.getPlugin(Main.class).prefix
                                    + "§aThe game starts now!"));
                            GameManager.start();
                            Bukkit.getScheduler().cancelTask(shed);
                            return;
                        }
                        count--;
                    }
                }, 20, 20);
            }
        }
    }
}
