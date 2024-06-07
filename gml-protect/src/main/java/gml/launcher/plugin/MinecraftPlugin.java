package gml.launcher.plugin;
import gml.launcher.SignalRConnect;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.logging.Logger;

public class MinecraftPlugin extends JavaPlugin {

    private final SignalRConnect signalRManager;
    private Logger logger;
    
    public MinecraftPlugin() {
        this.logger = getLogger();
        this.signalRManager = new SignalRConnect(this, "ws://192.168.31.199:5000", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEiLCJuYmYiOjE3MTc2NTE1NzMsImV4cCI6MTcxNzkxMDc3MywiaWF0IjoxNzE3NjUxNTczfQ.Vz2CNUOrxzSSgSOWzATfGXtLpHkF4iFA3J_CZe0w48s");        
    }

    @Override
    public void onEnable() { 
        try {
            this.signalRManager.buildAndConnect();
            logger.info("Plugin started initialized");
            PluginManager pm = getServer().getPluginManager();
            pm.registerEvents(new PlayerJoinListener(logger, signalRManager), this);
        } catch (Exception e) {
            logger.info(e.getMessage() + Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }

    }
}