package me.nolezor.dragoneggrespawnspigot.commands;

import me.nolezor.dragoneggrespawnspigot.DragonEggRespawn_Spigot;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CheckTimesRespawnedCommand implements CommandExecutor {
    Plugin plugin = DragonEggRespawn_Spigot.getPlugin();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("checktimesrespawned")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("dragoneggrespawn.respawnedeggs") || p.hasPermission("dragoneggrespawn.*") || p.isOp()) {
                    p.sendMessage(ChatColor.YELLOW + "Respawned eggs: " + DragonEggRespawn_Spigot.getTimesRespawned());
                }
            } else if (sender instanceof ConsoleCommandSender) {
                plugin.getLogger().info("Respawned eggs: " + DragonEggRespawn_Spigot.getTimesRespawned());
            } else {
                plugin.getLogger().warning(sender.getName() + " is trying to run the command \"/"+ command.getName() +"\"");
            }
        }
        return true;
    }
}
