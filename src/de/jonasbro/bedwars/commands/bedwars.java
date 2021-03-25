package de.jonasbro.bedwars.commands;

import de.jonasbro.bedwars.main.Main;
import de.jonasbro.bedwars.main.setSpawn;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class bedwars implements CommandExecutor {
    private final Main main;

    public bedwars(Main main) {
        this.main = main;
        Bukkit.getPluginCommand("bedwars").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        if (args.length == 1 && args[0].equalsIgnoreCase("setlobby")) {
            main.getConfig().set("location.lobby", p.getLocation());
            main.saveConfig();
            p.sendMessage(main.prefix + "§aYou set the lobby!");
        } else if (args.length == 2 && args[0].equalsIgnoreCase("setspawn")) {
            int i = setSpawn.set(p.getLocation(), main);
            p.sendMessage(main.prefix + "§cYou set the spawn with §7[ID=" + args[1] + "]");
        } else if (args.length == 1 && args[0].equalsIgnoreCase("setspec")) {
            main.getConfig().set("location.spectator", p.getLocation());
            main.saveConfig();
            p.sendMessage(main.prefix + "§aYou set the spectatorspawn!");
        } else if (args.length == 2 && args[0].equalsIgnoreCase("setspawn")) {
            try {
                main.getConfig().set("location.spectator" + Integer.parseInt(args[1]), p.getLocation());
                main.saveConfig();
                p.sendMessage(main.prefix + "§aYou set the spectatorspawn!");
            } catch (NumberFormatException e) {
                p.sendMessage(main.prefix + "§cYou need to specifie a number");
            }
        }else if (args.length == 2 && args[0].equalsIgnoreCase("setteam")) {
            try {
                main.getConfig().set("location.spawn", Integer.parseInt(args[1]));
                main.saveConfig();
                p.sendMessage(main.prefix + "§aYou set the teamsize to §8" + args[1] + " §a!");
            } catch (NumberFormatException e) {
                p.sendMessage(main.prefix + "§cYou need to specifie a number");
            }
        } else {
            showHelp(p);
        }
        return false;
    }

    public void showHelp(Player p) {
        p.sendMessage(main.prefix + "§b§lHelp:");
        p.sendMessage(main.prefix + "§7/bw setlobby §8- §aSet the lobby");
        p.sendMessage(main.prefix + "§7/bw setspawn §8- §aSet the next spawn");
        p.sendMessage(main.prefix + "§7/bw setspawn [ID] §8- §aSet the spawn with id");
        p.sendMessage(main.prefix + "§7/bw setspec §8- §aSet the spectatorspawn");
        p.sendMessage(main.prefix + "§7/bw setteam §8- §aSet the teamsize");
        p.sendMessage("");
    }
}
