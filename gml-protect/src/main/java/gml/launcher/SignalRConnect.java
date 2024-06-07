package gml.launcher;

import com.google.errorprone.annotations.Var;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import gml.launcher.plugin.MinecraftPlugin;
import org.bukkit.BanList;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;

public class SignalRConnect {

    private static final Logger logger = Logger.getLogger(SignalRConnect.class.getName());

    private final MinecraftPlugin minecraftPlugin;
    private final String address;
    private final String token;
    private HubConnection hubConnection;

    public SignalRConnect(MinecraftPlugin minecraftPlugin, String address, String token) {
        this.minecraftPlugin = minecraftPlugin;
        this.address = address;
        this.token = token;
    }

    private String buildHubUrl() {
        return String.format("%s/ws/gameServer?access_token=%s", this.address, this.token);
    }

    public void buildAndConnect() throws Exception {
        this.hubConnection = HubConnectionBuilder.create(this.buildHubUrl()).build();
        this.connect();
        this.createListeners();
    }

    private void connect() {
        try {
            this.hubConnection.start();
        } catch (Exception ex) {
            logger.severe("Error starting connection: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void createListeners() {
        this.hubConnection.onClosed((error) -> {
            logger.severe("[Gml.Protect] Connection closed: " + error);
        });

        hubConnection.on("BanUser", (userName) -> {
            Server server = minecraftPlugin.getServer();

            server.getScheduler().runTask(minecraftPlugin, () -> {

                Objects.requireNonNull(server.getPlayer(userName)).kickPlayer("You banned from server");
                Objects.requireNonNull(server.getPlayer(userName)).ban("You banned. Reason: Hack", (Date) null, null);

            });

        }, String.class);

        hubConnection.on("KickUser", (userName, reason) -> {
            Server server = minecraftPlugin.getServer();

            server.getScheduler().runTask(minecraftPlugin, () -> {

                Objects.requireNonNull(server.getPlayer(userName)).kickPlayer(reason);

            });

        }, String.class, String.class);
    }

    public void onJoin(String userName) {
        hubConnection.send("OnJoin", userName);
    }

    public void onLeft(String userName) {
        hubConnection.send("OnLeft", userName);
    }
}