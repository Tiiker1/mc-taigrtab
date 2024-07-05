package me.taigr;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TabManager implements Listener {

    private final Main plugin;

    public TabManager(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        updateTab(player);
    }

    public void updateTab(Player player) {
        String header = "Welcome to the server!";
        String footer = "Enjoy your stay!";
        player.setPlayerListHeaderFooter(header, footer);
    }
}