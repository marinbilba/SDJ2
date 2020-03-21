package model;
import java.beans.PropertyChangeListener;

public interface StateControl
{
  void turnUp(Radiator radiator);
  void turnDown(Radiator radiator);
  int getPower();
  void addPropertyChangeListener(String eventName,
      PropertyChangeListener lstnr);
}
