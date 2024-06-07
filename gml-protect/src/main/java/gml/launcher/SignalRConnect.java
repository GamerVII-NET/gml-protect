package gml.launcher;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

import java.util.logging.Logger;

public class SignalRConnect {

    private static final Logger logger = Logger.getLogger(SignalRConnect.class.getName());

    private final String address;
    private final String token;
    private HubConnection hubConnection;

    public SignalRConnect(String address, String token) {
        this.address = address;
        this.token = token;
    }

    private String buildHubUrl() {
        return String.format("%s/ws/launcher?access_token=%s", this.address, this.token);
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
    }
}