package main.connector;

import com.prosysopc.ua.ApplicationIdentity;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.core.ApplicationDescription;
import com.prosysopc.ua.stack.core.ApplicationType;

import java.util.Locale;

public class ClientConnectionManager{
    UaClient client;
    ApplicationDescription applicationDescription;
    ApplicationIdentity identity;



    public ClientConnectionManager(){
        initialize();
    }

    private void initialize(){
        client = new UaClient();
        applicationDescription = new ApplicationDescription();
       // identity = new ApplicationIdentity();
       // identity.setApplicationDescription(applicationDescription);
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
    }

    public void defaultCreateCertificate(){

    }

    public UaClient getClient(){
        return client;
    }

    public void setClient(UaClient client){
        this.client = client;
    }


}
