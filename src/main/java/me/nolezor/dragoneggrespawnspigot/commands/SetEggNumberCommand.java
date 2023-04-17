package me.nolezor.dragoneggrespawnspigot.commands;

import me.nolezor.dragoneggrespawnspigot.DragonEggRespawn_Spigot;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SetEggNumberCommand implements CommandExecutor {
    Plugin plugin = DragonEggRespawn_Spigot.getPlugin();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("seteggsremaining")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("dragoneggrespawn.seteggs") || p.hasPermission("dragoneggrespawn.*") || p.isOp()) {
                    if (args.length < 2) {
                        p.sendMessage(ChatColor.RED + "Wrong numbers of arguments");
                        p.sendMessage(ChatColor.RED + "Usage 1: /seteggsremaining add number");
                        p.sendMessage(ChatColor.RED + "Usage 2: /seteggsremaining set number");
                    } else {
                        String firstArg = args[0];
                        if (firstArg.equals("add") || firstArg.equals("set")) {
                            int newEggsRemaining;
                            try {
                                newEggsRemaining = Integer.parseInt(args[1]);
                                if (newEggsRemaining >= 0) {
                                    if (firstArg.equals("add")) {
                                        DragonEggRespawn_Spigot.setEggsRemaining(DragonEggRespawn_Spigot.getEggsRemaining()+newEggsRemaining);
                                    } else {
                                        DragonEggRespawn_Spigot.setEggsRemaining(newEggsRemaining);
                                    }
                                    DragonEggRespawn_Spigot.updateData();
                                    p.sendMessage(ChatColor.YELLOW + "Number of remaining eggs: " + DragonEggRespawn_Spigot.getEggsRemaining());
                                } else {
                                    p.sendMessage(ChatColor.RED + "The first argument must be greater than 0");
                                }
                            } catch (NumberFormatException e) {
                                p.sendMessage(ChatColor.RED + "The first argument must be an integer");
                            }
                        } else {
                            p.sendMessage(ChatColor.RED + "The first argument must be \"add\" or \"set\"");
                        }
                    }
                }
            } else if (sender instanceof ConsoleCommandSender) {
                if (args.length < 2) {
                    plugin.getLogger().info("Wrong numbers of arguments");
                    plugin.getLogger().info("Usage 1: /eggsremaining add number");
                    plugin.getLogger().info("Usage 2: /eggsremaining set number");
                } else {
                    String firstArg = args[0];
                    if (firstArg.equals("add") || firstArg.equals("set")) {
                        int newEggsRemaining;
                        try {
                            newEggsRemaining = Integer.parseInt(args[0]);
                            if (newEggsRemaining >= 0) {
                                if (firstArg.equals("add")) {
                                    DragonEggRespawn_Spigot.setEggsRemaining(DragonEggRespawn_Spigot.getEggsRemaining()+newEggsRemaining);
                                } else {
                                    DragonEggRespawn_Spigot.setEggsRemaining(newEggsRemaining);
                                }
                                DragonEggRespawn_Spigot.updateData();
                            } else {
                                plugin.getLogger().info("The first argument must be greater than 0");
                            }
                        } catch (NumberFormatException e) {
                            plugin.getLogger().info("The first argument must be an integer");
                        }
                    } else {
                        plugin.getLogger().info("The first argument must be \"add\" or \"set\"");
                    }
                }
            } else {
                plugin.getLogger().warning(sender.getName() + " is trying to run the command \"/"+ command.getName() +"\"");
            }
        }
        return true;
    }
}
