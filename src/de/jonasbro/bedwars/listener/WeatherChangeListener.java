package de.jonasbro.bedwars.listener;

import de.jonasbro.bedwars.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener implements Listener {

    private final Main main;
    public WeatherChangeListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent e) {
        e.setCancelled(true);
        e.getWorld().setStorm(false);
        e.getWorld().setWeatherDuration(0);
        e.getWorld().setThundering(false);
    }
}
