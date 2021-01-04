package main;

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.stack.core.MessageSecurityMode;
import com.prosysopc.ua.stack.transport.security.SecurityMode;
import com.prosysopc.ua.stack.transport.security.SecurityPolicy;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.connector.ClientConnectionManager;

public class MainController {
    //Variables
    int dbx, dby;
    ClientConnectionManager connectionManager;
    UaClient client;
    final static String defaultAddress = "opc.tcp://DESKTOP-B0OMO7A:48010";

    //Dashboard Components
    @FXML TextField dbxTextField;
    @FXML TextField dbyTextField;
    @FXML Button dbcoordButton;
    @FXML ListView<String> dbxyListView;
    @FXML CategoryAxis dbxAxis;
    @FXML NumberAxis dbyAxis;
    @FXML LineChart<String, Number> dbxyChart;
    XYChart.Series<String, Number> dataseries;
    //Connection Components
    @FXML TextField cConnectionStatusTextField;
    @FXML TextField cAddressTextField;
    @FXML Button cConnectButton;
    @FXML CheckBox cReverseConnectCheckBox;
    @FXML ComboBox cSecurityPolicyComboBox;
    @FXML ComboBox cMessageSecurityModeComboBox;
    @FXML Button cCreateCertificateButton;

    public MainController(){

    }

    public MainController(ClientConnectionManager client){

    }

    public void initialize(){
        //Dashboard
        //dbxAxis = new CategoryAxis();
        //dbyAxis = new NumberAxis();
        dataseries = new XYChart.Series<>();
        //dbxyChart = new LineChart<String, Number>(dbxAxis, dbyAxis);
        dataseries.getData().add(new XYChart.Data<String, Number>("0", 0));
        dbxyChart.getData().add(dataseries);

        //Connection
        connectionManager = new ClientConnectionManager();
        //cReverseConnectCheckBox = new CheckBox();
        //cSecurityPolicyComboBox = new ComboBox();
        //cMessageSecurityModeComboBox = new ComboBox();

        cSecurityPolicyComboBox.getItems().add("None");
        cSecurityPolicyComboBox.getItems().add("AES128");
        cSecurityPolicyComboBox.getItems().add("AES256");
        cSecurityPolicyComboBox.getItems().add("Basic128RSA15");
        cSecurityPolicyComboBox.getItems().add("Basic256");
        cSecurityPolicyComboBox.getItems().add("Basic256SHA256");

        cMessageSecurityModeComboBox.getItems().add("None");
        cMessageSecurityModeComboBox.getItems().add("Sign");
        cMessageSecurityModeComboBox.getItems().add("Sign & Encrypt");



        System.out.println("initialize"); //debug
    }


    @FXML
    public void coordButtonClicked(Event e){
        String category = dbxTextField.getCharacters().toString();
        int numbers = Integer.parseInt(dbyTextField.getCharacters().toString());
        dataseries.getData().add(new XYChart.Data<String, Number>(category, numbers));
        dbxyChart.getData().add(dataseries);
        System.out.println(dataseries); //debug
        System.out.println("button clicked"); //debug
    }

    @FXML
    public void connectButtonClicked(Event e){
        //Initialise 1
        this.client = connectionManager.getClient();

        //Check if has connect, if connected then end connection
        if(client.isConnected())
            client.disconnect();

        //Initialise 2
        connectionManager.defaultAppInitialize();

        //Reverse Connect
        if (cReverseConnectCheckBox.isSelected() == false) {
            client.setAddress(cAddressTextField.getText());
            client.setReverseAddress(null);
        } else { //UNIMPLEMENTED
            client.setAddress("");
            //client.setReverseAddress();
        }

        //Security Policy
        SecurityPolicy securityPolicy = SecurityPolicy.NONE;
        MessageSecurityMode messageSecurityMode = MessageSecurityMode.None;
        switch (cSecurityPolicyComboBox.getSelectionModel().getSelectedIndex()){
            case 0:
                securityPolicy = SecurityPolicy.NONE;
                break;
            case 1:
                securityPolicy = SecurityPolicy.AES128_SHA256_RSAOAEP;
                break;
            case 2:
                securityPolicy = SecurityPolicy.AES256_SHA256_RSAPSS;
                break;
            case 3:
                securityPolicy = SecurityPolicy.BASIC128RSA15;
                break;
            case 4:
                securityPolicy = SecurityPolicy.BASIC256;
                break;
            case 5:
                securityPolicy = SecurityPolicy.BASIC256SHA256;
                break;
        }

        switch (cMessageSecurityModeComboBox.getSelectionModel().getSelectedIndex()){
            case 0:
                messageSecurityMode = MessageSecurityMode.None;
                break;
            case 1:
                messageSecurityMode = MessageSecurityMode.Sign;
                break;
            case 2:
                messageSecurityMode = MessageSecurityMode.SignAndEncrypt;
                break;
        }
        SecurityMode securityMode = new SecurityMode(securityPolicy, messageSecurityMode);
        client.setSecurityMode(securityMode);


        //Connect
        try {
            client.connect();
        } catch (ServiceException error) {
            System.out.println("Connection Failed"); //debug
            System.out.print(error);
        }
        //System.out.println(client.getSecurityMode()); //debug
        System.out.println(client.getServerState()); //debug
    }

    @FXML
    private void cCreateCertificateButtonClicked(Event e) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CertificatePopUp.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

        System.out.println("cCreateCertificateButtonClicked"); //debug
    }

}
