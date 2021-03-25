package de.jonasbro.bedwars.main;

import de.jonasbro.bedwars.main.Main;
import org.bukkit.Location;

public class setSpawn {
    public static int i = 0;
    public static int set(Location location, Main main) {
        i++;
        main.getConfig().set("location.spawn." + i, location);
        main.saveConfig();
        return i;
    }

}

