package view.manage;

import javafx.application.Platform;
import javafx.beans.property.*;
import mediator.MainModel;
import model.Temperature;

import java.beans.PropertyChangeEvent;

public class ManageVM
{
  private MainModel model;
  private StringProperty temperatureT1;
  private StringProperty temperatureT2;
  private StringProperty temperatureT0;

  private StringProperty lowOrHighLabel;

//  NEW
  private StringProperty levelLabel;

  public ManageVM(MainModel model)
  {
    this.model = model;
    temperatureT0 = new SimpleStringProperty();
    temperatureT1 = new SimpleStringProperty();
    temperatureT2 = new SimpleStringProperty();
    lowOrHighLabel = new SimpleStringProperty();
    levelLabel=new SimpleStringProperty();

    model.addPropertyChangeListener("TemperatureUpdate",
        this::updateTemperaturesFields);
    model.addPropertyChangeListener("ExternalTemperatureUpdate",
        this::updateExternalTemperaturesFields);
    model.addPropertyChangeListener("RadiatorState", this::updateRadiatorState);



  }

  private void updateRadiatorState(PropertyChangeEvent evt)
  {
    levelLabel.setValue(String.valueOf(evt.getNewValue()));
  }

  public void turnUp()
  {
    model.turnUp();
  }

  public void turnDown()
  {
    model.turnDown();
  }

  public void updateTemperaturesFields(PropertyChangeEvent evt)
  {
    Temperature temp = (Temperature) evt.getNewValue();
    if (temp.getId().equals("T1"))
    {
      Platform.runLater(
          () -> temperatureT1.setValue(String.valueOf(temp.getValue())));
      if (temp.getValue() > 25)
      {
        Platform.runLater(() -> lowOrHighLabel.setValue("HIGH"));
      }
      else if (temp.getValue() < 16)
      {
        Platform.runLater(() -> lowOrHighLabel.setValue("LOW"));
      }
      else
      {
        Platform.runLater(() -> lowOrHighLabel.setValue("NORMAL"));
      }
    }
    else if (temp.getId().equals("T2"))
    {
      Platform.runLater(
          () -> temperatureT2.setValue(String.valueOf(temp.getValue())));
    }
  }

  public void updateExternalTemperaturesFields(PropertyChangeEvent evt)
  {
    Temperature temp = (Temperature) evt.getNewValue();
    Platform.runLater(
        () -> temperatureT0.setValue(String.valueOf(temp.getValue())));
  }

  public StringProperty temperatureT1Property()
  {
    return temperatureT1;
  }

  public StringProperty temperatureT2Property()
  {
    return temperatureT2;
  }

  public StringProperty temperatureT0Property()
  {
    return temperatureT0;
  }

  public StringProperty lowOrHighLabelProperty()
  {
    return lowOrHighLabel;
  }

//  NEW

  public StringProperty levelLabelProperty()
  {
    return levelLabel;
  }
}
