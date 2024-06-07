package gml.launcher.plugin;

import gml.launcher.SignalRConnect;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.logging.Logger;

public class PlayerJoinListener implements Listener {
    private final Logger logger;
    private final SignalRConnect signalRManager;

    public PlayerJoinListener(Logger logger, SignalRConnect signalRManager) {
        this.logger = logger;
        this.signalRManager = signalRManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        signalRManager.onJoin(event.getPlayer().getName());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        signalRManager.onLeft(event.getPlayer().getName());
    }

    
}