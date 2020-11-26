package main;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.Text;

public class MainController {
    //Variables
    int dbx, dby;

    //Dashboard Components
    @FXML
    TextField dbxTextField;

    @FXML
    TextField dbyTextField;

    @FXML
    Button dbcoordButton;

    @FXML
    ListView<String> dbxyListView;

    @FXML
    LineChart dbxyChart;
    XYChart.Series dataseries;

    @FXML
    NumberAxis dbxAxis;

    @FXML
    NumberAxis dbyAxis;

    //Connection Components

    @FXML
    TextField cConnectionStatusTextField;

    @FXML
    TextField cAddressTextField;

    @FXML
    Button cConnectButton;

    public MainController(){
        initialize();
    }

    private void initialize(){
        //TextFormatter numberFormat = new TextFormatter();
        //dbxTextField.setTextFormatter(numberFormat);

        //init dbxyChart
        //dbxAxis = new NumberAxis();
        //dbyAxis = new NumberAxis();
        //dbxyChart = new LineChart(dbxAxis, dbyAxis);
       // dataseries = new XYChart.Series();
    }


    @FXML
    public void coordButtonClicked(Event e){
        dataseries.getData().add(new XYChart.Data());
    }

}
