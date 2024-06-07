package gml.launcher.plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class MinecraftPlugin extends JavaPlugin {
    
    private Logger logger;
    
    public MinecraftPlugin() {
        logger = getLogger();        
    }

    @Override
    public void onEnable() {
        logger.info("Plugin started initialized");

        PluginManager pm = getServer().getPluginManager();
        
        pm.registerEvents(new PlayerJoinListener(logger), this);
    }
}