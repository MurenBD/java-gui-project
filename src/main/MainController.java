package main;

import com.prosysopc.ua.ServiceException;
import com.prosysopc.ua.client.UaClient;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.Text;
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

    public MainController(){
        initialize();
    }

    public MainController(ClientConnectionManager client){
        initialize();
    }

    private void initialize(){
        connectionManager = new ClientConnectionManager();
        dbxAxis = new CategoryAxis();
        dbyAxis = new NumberAxis();
        dataseries = new XYChart.Series<>();
        dbxyChart = new LineChart<String, Number>(dbxAxis, dbyAxis);
        dataseries.getData().add(new XYChart.Data<String, Number>("0", 0));
        dbxyChart.getData().add(dataseries);
        //TextFormatter numberFormat = new TextFormatter();
        //dbxTextField.setTextFormatter(numberFormat);

        //init dbxyChart
        //dbxAxis = new NumberAxis();
         //dbxyChart = new LineChart(dbxAxis, dbyAxis);
       // dataseries = new XYChart.Series();
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
        this.client = connectionManager.getClient();
       /* client.setAddress(cAddressTextField.getText());
        connectionManager.defaultAppInitialize();
        try {
            client.connect();
        } catch (ServiceException error) {
            System.out.println("Connection Failed"); //debug
            System.out.print(error);
        }*/
    }

}
