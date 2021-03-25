package de.jonasbro.bedwars.listener;

import de.jonasbro.bedwars.countdowns.GameManager;
import de.jonasbro.bedwars.main.Main;
import de.jonasbro.bedwars.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Set;

public class InteractListemer  implements Listener {


    private Main main;

    public InteractListemer(Main main) {
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getItem() != null) {
            if (e.getMaterial() == Material.BED_BLOCK) {
                Set<String> teams = main.getConfig().getConfigurationSection("location.spawn").getKeys(false);
                int size = 9;
                if (teams.size() <= 9) {
                    size = 9;
                } else if (teams.size() <= 18) {
                    size = 18;
                } else if (teams.size() <= 27) {
                    size = 27;
                }

                Inventory inv = Bukkit.createInventory(null, size, "§6Choose your team");

                for (String s : teams) {
                    try {
                        byte subid = 5;
                        if (GameManager.getTeamPlayers(s).size() == main.getConfig().getInt("location.spawn")) {
                            subid = 14;
                        }
                        ItemBuilder itemBuilder = new ItemBuilder(new ItemStack(Material.WOOL, 1, subid));
                        itemBuilder.lore("");
                        ArrayList lore = new ArrayList();
                        for (Player p : GameManager.getTeamPlayers(s)) {
                            itemBuilder.lore(p.getName());
                        }

                        inv.setItem(1, itemBuilder.build());
                        inv.setItem(2, itemBuilder.build());
                        inv.setItem(3, itemBuilder.build());
                        inv.setItem(4, itemBuilder.build());
                        e.getPlayer().openInventory(inv);
                    } catch (Exception e1){
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}