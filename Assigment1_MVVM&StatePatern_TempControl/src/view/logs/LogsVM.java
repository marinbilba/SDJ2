package view.logs;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import mediator.MainModel;
import model.Temperature;

import java.beans.PropertyChangeEvent;

public class LogsVM
{
  private XYChart.Series<String, Double> series;
  private XYChart.Series<String, Double> series2;
  private XYChart.Series<String, Double> series3;

  private ObservableList<Temperature> internalTemperatures1;
  private ObservableList<Temperature> internalTemperatures2;
  private ObservableList<Temperature> externalTemperatures;
  private MainModel mainModel;

  //  Following Strings are required for my stupid approach solving the chart problem
  private String t1 = "";
  private String t2 = "";
  private String t0 = "";
  private String t = " ";

  public LogsVM(MainModel mainModel)
  {
    series = new XYChart.Series<>();
    series.setName("T1");
    series2 = new XYChart.Series<>();
    series2.setName("T2");
    series3 = new XYChart.Series<>();
    series3.setName("T0");
    this.mainModel = mainModel;
    internalTemperatures1 = FXCollections.observableArrayList();
    internalTemperatures2 = FXCollections.observableArrayList();
    externalTemperatures = FXCollections.observableArrayList();
    mainModel.addPropertyChangeListener("TemperatureUpdate",
        this::updateTemperatureList);
    mainModel.addPropertyChangeListener("ExternalTemperatureUpdate",
        this::updateExternalTemperatureList);

  }

  private synchronized void updateExternalTemperatureList(
      PropertyChangeEvent evt)
  {
    Temperature temp = (Temperature) evt.getNewValue();
    if (externalTemperatures.size() == 19)
    {
      externalTemperatures.remove(0);
      externalTemperatures.add((Temperature) evt.getNewValue());
    }
    else
    {
      externalTemperatures.add((Temperature) evt.getNewValue());
    }
    if (temp.getId().equals("T0"))
    {

      Platform.runLater(() -> series3.getData()
          .add(new XYChart.Data<String, Double>(t0, temp.getValue())));
      t0 = t0.concat(t);
    }

  }

  private void updateTemperatureList(PropertyChangeEvent evt)
  {
    Temperature temp = (Temperature) evt.getNewValue();
    if (temp.getId().equals("T1"))
    {
      if (internalTemperatures1.size() == 19)
      {
        internalTemperatures1.remove(0);
        internalTemperatures1.add((Temperature) evt.getNewValue());
        Platform.runLater(() -> series.getData()
            .add(new XYChart.Data<String, Double>(t1, temp.getValue())));
        t1 = t1.concat(t);
      }
      else
      {
        internalTemperatures1.add((Temperature) evt.getNewValue());
        Platform.runLater(() -> series.getData()
            .add(new XYChart.Data<String, Double>(t1, temp.getValue())));
        t1 = t1.concat(t);
      }
    }
    else if (temp.getId().equals("T2"))
    {
      if (internalTemperatures2.size() == 19)
      {
        internalTemperatures2.remove(0);
        internalTemperatures2.add((Temperature) evt.getNewValue());
        Platform.runLater(() -> series2.getData()
            .add(new XYChart.Data<String, Double>(t2, temp.getValue())));
        t2 = t2.concat(t);
      }
      else
      {
        internalTemperatures2.add((Temperature) evt.getNewValue());
        Platform.runLater(() -> series2.getData()
            .add(new XYChart.Data<String, Double>(t2, temp.getValue())));
        t2 = t2.concat(t);
      }
    }
    //    if (temp.getId().equals("T1")){
    //
    //      Platform.runLater(
    //          () -> series.getData().add(new XYChart.Data<String, Double>(t1,temp.getValue())));
    //      t1 = t1.concat(t);
    //    }else if(temp.getId().equals("T2")){
    //      Platform.runLater(
    //          () -> series2.getData().add(new XYChart.Data<String, Double>(t2,temp.getValue())));
    //      t2=t2.concat(t);
  }

  public XYChart.Series<String, Double> getSeries()
  {
    return series;
  }

  public XYChart.Series<String, Double> getSeries2()
  {
    return series2;
  }

  public XYChart.Series<String, Double> getSeries3()
  {
    return series3;
  }

  public ObservableList<Temperature> getInternalTemperatures1()
  {
    return internalTemperatures1;
  }

  public ObservableList<Temperature> getInternalTemperatures2()
  {
    return internalTemperatures2;
  }

  public ObservableList<Temperature> getExternalTemperatures()
  {
    return externalTemperatures;
  }
}
