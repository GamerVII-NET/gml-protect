package gml.launcher.plugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.logging.Logger;

public class PlayerJoinListener implements Listener {
    private final Logger logger;

    public PlayerJoinListener(Logger logger) {
        this.logger = logger;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        logger.info(event.getPlayer().getName() + " joined the game");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        logger.info(event.getPlayer().getName() + " left the game");
    }

    
}