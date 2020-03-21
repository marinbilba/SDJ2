package mediator;

import model.Radiator;

import java.beans.PropertyChangeListener;

public interface MainModel
{
  void updateExternalTemperature(String id, double value);
  void updateTemperature(String id, double value);
  void turnUp();
  void turnDown();
  Radiator getRadiator();

  void addListener(PropertyChangeListener lstnr);
  void addPropertyChangeListener(String eventName,
      PropertyChangeListener lstnr);
  void removeListener(PropertyChangeListener lstnr);
  void removePropertyChangeListener(String eventName,
      PropertyChangeListener lstnr);
}
