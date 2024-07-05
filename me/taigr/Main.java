package me.taigr;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private TabManager tabManager;

    @Override
    public void onEnable() {
        this.tabManager = new TabManager(this);
        getServer().getPluginManager().registerEvents(this.tabManager, this);
        getLogger().info("TaigrTab has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("TaigrTab has been disabled!");
    }
}