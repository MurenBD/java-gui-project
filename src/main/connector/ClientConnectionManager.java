package main.connector;

import com.prosysopc.ua.ApplicationIdentity;
import com.prosysopc.ua.UserIdentity;
import com.prosysopc.ua.client.ServerStatusListener;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.builtintypes.StatusCode;
import com.prosysopc.ua.stack.core.ApplicationDescription;
import com.prosysopc.ua.stack.core.ApplicationType;
import com.prosysopc.ua.stack.core.ServerState;
import com.prosysopc.ua.stack.core.ServerStatusDataType;
import com.prosysopc.ua.stack.transport.security.SecurityMode;

import java.util.Locale;

public class ClientConnectionManager{
    UaClient client;
    ApplicationDescription applicationDescription;
    ApplicationIdentity identity;
    ServerStatusListener serverStatusListener;
    ServerStatusObject serverStatusObject;

    protected class ServerStatusObject {
        //Server Status Objects
        //onShutdown
        long shutdownTimeUntil;
        LocalizedText shutdownReason;
        //onStateChange
        ServerState serverStateOld;
        ServerState serverStateNew;
        //onStatusChange
        ServerStatusDataType statusChange;
        StatusCode statusChangeErrorCode;
    }



    public ClientConnectionManager(){
        initialize();
    }

    private void initialize(){
        client = new UaClient();
        applicationDescription = new ApplicationDescription();
        identity = new ApplicationIdentity();
        serverStatusListener = new ServerStatusListener() {
            @Override
            public void onShutdown(UaClient uaClient, long l, LocalizedText localizedText) {
                serverStatusObject.shutdownTimeUntil = l;
                serverStatusObject.shutdownReason = localizedText;
            }

            @Override
            public void onStateChange(UaClient uaClient, ServerState serverState, ServerState serverState1) {
                serverStatusObject.serverStateOld = serverState;
                serverStatusObject.serverStateNew = serverState1;
            }

            @Override
            public void onStatusChange(UaClient uaClient, ServerStatusDataType serverStatusDataType, StatusCode statusCode) {
                serverStatusObject.statusChange = serverStatusDataType;
                serverStatusObject.statusChangeErrorCode = statusCode;
            }
        };
        //identity.setApplicationDescription(applicationDescription);
    }


    public void defaultAppInitialize(){
        applicationDescription.setApplicationName(new LocalizedText("TestJavaClient", Locale.ENGLISH));
        applicationDescription.setApplicationUri("urn:localhost:UA:TestJavaClient");
        applicationDescription.setProductUri("urn:prosysopc.com:UA:TestJavaClient");
        applicationDescription.setApplicationType(ApplicationType.Client);

        //final ApplicationIdentity identity = new ApplicationIdentity();
        identity.setApplicationDescription(applicationDescription);
        client.setApplicationIdentity(identity);
        client.setValidateDiscoveredEndpoints(false);

        //SecurityMode
        client.setSecurityMode(SecurityMode.NONE);

        //ClientIdentity

        //UserIdentity
       // client.setUserIdentity(UserIdentity);

        //ServerStatusListener
        client.addServerStatusListener(serverStatusListener);
    }

    public void defaultCreateCertificate(){

    }

    public UaClient getClient(){
        return client;
    }

    public void setClient(UaClient client){
        this.client = client;
    }

    public ServerStatusListener getServerStatusListener() { return serverStatusListener; }

    public ServerStatusObject getServerStatusObject() { return serverStatusObject; }

}
