package me.taigr;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private TabManager tabManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        getServer().getPluginManager().registerEvents(new TabManager(this, config), this);
        getLogger().info("TaigrTab has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("TaigrTab has been disabled!");
    }
}
