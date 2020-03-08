package view.logs;

import core.ViewHandler;
import core.ViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Temperature;

public class LogsController
{

  @FXML private TableView<Temperature> internalTableView1;
  @FXML private TableView<Temperature> internalTableView2;
  @FXML private TableView<Temperature> externalTableView;

  //  T1
  public TableColumn<String, Temperature> internalT1Time;
  public TableColumn<String, Temperature> internalThermometer1;
  public TableColumn<String, Temperature> internalTemperature1;

  //  T2
  public TableColumn<String, Temperature> internalT2Time;
  public TableColumn<String, Temperature> internalThermometer2;
  public TableColumn<String, Temperature> internalTemperature2;

  //  External
  public TableColumn<String, Temperature> externalThermometer;
  public TableColumn<String, Temperature> externalTemperature;
  public TableColumn<String, Temperature> externalT0Time;

  public LineChart<String, Double> chart;
  private LogsVM vm;
  private ViewHandler vh;

  public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    vm = vmf.getLogsVM();
    //    T1
    internalThermometer1.setCellValueFactory(new PropertyValueFactory<>("id"));
    internalTemperature1
        .setCellValueFactory(new PropertyValueFactory<>("value"));
    internalT1Time
        .setCellValueFactory(new PropertyValueFactory<>("timeCreated"));

    internalTableView1.setItems(vm.getInternalTemperatures1());

    //    T2
    internalThermometer2.setCellValueFactory(new PropertyValueFactory<>("id"));
    internalTemperature2
        .setCellValueFactory(new PropertyValueFactory<>("value"));
    internalT2Time
        .setCellValueFactory(new PropertyValueFactory<>("timeCreated"));

    internalTableView2.setItems(vm.getInternalTemperatures2());

    // External
    externalThermometer.setCellValueFactory(new PropertyValueFactory<>("id"));
    externalTemperature
        .setCellValueFactory(new PropertyValueFactory<>("value"));
    externalT0Time
        .setCellValueFactory(new PropertyValueFactory<>("timeCreated"));
    externalTableView.setItems(vm.getExternalTemperatures());

    externalTableView.setItems(vm.getExternalTemperatures());

    chart.getData().addAll(vm.getSeries(), vm.getSeries2(), vm.getSeries3());

  }

  public void openManageState()
  {

    vh.openManageView();
  }
}
