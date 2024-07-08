package me.taigr;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TabManager implements Listener {

    private final FileConfiguration config;
    private final JavaPlugin plugin;

    public TabManager(JavaPlugin plugin, FileConfiguration config) {
        this.plugin = plugin;
        this.config = config;
        startTabUpdateTask();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        updateTab(player);
    }

    private void startTabUpdateTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    updateTab(player);
                }
            }
        }.runTaskTimer(plugin, 0, 20 * 5); // Update every 5 seconds
    }

    public void updateTab(Player player) {
        String header = config.getString("tab.header", "Welcome to the server!");
        String footer = config.getString("tab.footer", "Enjoy your stay!");

        // Replace & with § for color codes
        header = ChatColor.translateAlternateColorCodes('&', header);
        footer = ChatColor.translateAlternateColorCodes('&', footer);

        // Check if RAM usage should be shown
        boolean showRamUsage = config.getBoolean("tab.show_ram_usage", true);

        if (showRamUsage) {
            // Get server RAM usage
            long maxMemory = Runtime.getRuntime().maxMemory() / (1024 * 1024);
            long allocatedMemory = Runtime.getRuntime().totalMemory() / (1024 * 1024);
            long freeMemory = Runtime.getRuntime().freeMemory() / (1024 * 1024);
            long usedMemory = allocatedMemory - freeMemory;

            // Append RAM usage to the footer
            footer += String.format("\nRAM Usage: %d MB / %d MB", usedMemory, maxMemory);
        }

        player.setPlayerListHeaderFooter(header, footer);
    }
}
