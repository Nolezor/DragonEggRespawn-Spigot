package me.nolezor.dragoneggrespawnspigot.events;

import me.nolezor.dragoneggrespawnspigot.DragonEggRespawn_Spigot;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

public class DragonDeathEvent implements Listener {
    private Plugin plugin = DragonEggRespawn_Spigot.getPlugin();

    @EventHandler
    public void onDragonDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof EnderDragon && DragonEggRespawn_Spigot.getEggsRemaining() > 0) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                Location loc;
                if (DragonEggRespawn_Spigot.isCustomFountain()) {
                    loc = new Location(e.getEntity().getWorld(), plugin.getConfig().getInt("end_fountain.x"), 0.0D, plugin.getConfig().getInt("end_fountain.z"));
                } else {
                    loc = new Location(e.getEntity().getWorld(), 0.0D, 0.0D, 0.0D);
                }
                loc.setY(loc.getWorld().getHighestBlockYAt(0, 0)+1);
                Block block = e.getEntity().getWorld().getBlockAt(loc);
                block.setType(Material.DRAGON_EGG);
                DragonEggRespawn_Spigot.setTimesRespawned(DragonEggRespawn_Spigot.getTimesRespawned() + 1);
                DragonEggRespawn_Spigot.setEggsRemaining(DragonEggRespawn_Spigot.getEggsRemaining() - 1);
                DragonEggRespawn_Spigot.updateData();
            }, 500L);
        }
    }
}
