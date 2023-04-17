package me.nolezor.dragoneggrespawnspigot;

import me.nolezor.dragoneggrespawnspigot.commands.CheckRemainingEggsCommand;
import me.nolezor.dragoneggrespawnspigot.commands.CheckTimesRespawnedCommand;
import me.nolezor.dragoneggrespawnspigot.commands.SetEggNumberCommand;
import me.nolezor.dragoneggrespawnspigot.events.DragonDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class DragonEggRespawn_Spigot extends JavaPlugin {
    public static DragonEggRespawn_Spigot plugin;
    private static int eggsRemaining;
    private static int timesRespawned;
    private static boolean customFountain;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        plugin = this;
        eggsRemaining = getConfig().getInt("eggs.remaining");
        timesRespawned = getConfig().getInt("eggs.respawned");
        customFountain = getConfig().getBoolean("end_fountain.custom_coords");

        getCommand("seteggsremaining").setExecutor(new SetEggNumberCommand());
        getCommand("checkeggsremaining").setExecutor(new CheckRemainingEggsCommand());
        getCommand("checktimesrespawned").setExecutor(new CheckTimesRespawnedCommand());

        getServer().getPluginManager().registerEvents(new DragonDeathEvent(), this);
    }

    public static DragonEggRespawn_Spigot getPlugin() { return plugin; }


    public static boolean isCustomFountain() { return customFountain; }
    public static int getEggsRemaining() { return eggsRemaining; }

    public static void setEggsRemaining(int remaining) { eggsRemaining = remaining; }

    public static int getTimesRespawned() { return timesRespawned; }

    public static void setTimesRespawned(int respawned) { timesRespawned = respawned; }

    public static void updateData() {
        plugin.getConfig().set("eggs.remaining", eggsRemaining);
        plugin.getConfig().set("eggs.respawned", timesRespawned);
        plugin.saveConfig();
    }
}
