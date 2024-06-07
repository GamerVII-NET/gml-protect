package gml.launcher;
import gml.launcher.utils.LogHelper;
import java.lang.instrument.Instrumentation;

public class Premain {
    private static final String ARGUMENTS_EXCEPTION = "Invalid arguments. Expected format: URL;Token";
    private static SignalRConnect signalRConnect;

    public static void premain(String arg, Instrumentation instrumentation) throws Exception {
        LogHelper.logInitMessage();
        startSignalRConnect(parseArguments(arg));
        signalRConnect.buildAndConnect();
    }
    
    private static String[] parseArguments(String arg) throws Exception {
        String[] parts = arg.split(";");
        if (parts.length != 2) {
            throw new Exception(ARGUMENTS_EXCEPTION);
        }
        LogHelper.logServer(parts[0]);
        LogHelper.logToken(parts[1]);
        return parts;
    }
    
    private static void startSignalRConnect(String[] parts) throws Exception {
        signalRConnect = new SignalRConnect(parts[0], parts[1]);
    }
}