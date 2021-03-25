package de.jonasbro.bedwars.main;

import de.jonasbro.bedwars.commands.bedwars;
import de.jonasbro.bedwars.listener.*;
import de.jonasbro.bedwars.utils.GameState;
import de.jonasbro.bedwars.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin {

    public String prefix = "§8[§6BedWars§8]";

    @Override
    public void onEnable() {
        GameState.setGameState(GameState.LOBBY);
        initListener();
        initCommands();
        System.out.println("[BedWars] Enabled");
        Logger logger = this.getLogger();
        new UpdateChecker(this, 88664).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                logger.info("Congrats your on the latest version.");
            } else {
                logger.info("There is a new update available.");
                logger.info("Your Version: 1.4"); //TODO Alte Version durch das die letzte ersetzen
                logger.info("New Version: 1.5"); //TODO Neue Version hier rein packen
                logger.info("Download new version here: https://www.spigotmc.org/resources/command-simplifier.88664/update?update=393401");
            }
        });
    }

        @Override
        public void onDisable () {
            System.out.println("[BedWars] Disabled");
            System.out.println("[BedWars] Thanks for using!!");
        }

        public void initCommands () {
            new bedwars(this);
        }

        public void initListener () {
            new InteractListemer(this);
            new BreakListener(this);
            new BuildListener(this);
            new EntityDamageListener(this);
            new FoodLevelChangeListener(this);
            new JoinListener(this);
            new WeatherChangeListener(this);

            Bukkit.getPluginManager().registerEvents(new BreakListener(this), this);
            Bukkit.getPluginManager().registerEvents(new BuildListener(this), this);
            Bukkit.getPluginManager().registerEvents(new EntityDamageListener(this), this);
            Bukkit.getPluginManager().registerEvents(new FoodLevelChangeListener(this), this);
            Bukkit.getPluginManager().registerEvents(new JoinListener(this), this);
            Bukkit.getPluginManager().registerEvents(new WeatherChangeListener(this), this);
            Bukkit.getPluginManager().registerEvents(new InteractListemer(this), this);
        }

    public void getScoreBoardManager() {
        return;
    }

    public void getInstance() {
        return;
    }
}






