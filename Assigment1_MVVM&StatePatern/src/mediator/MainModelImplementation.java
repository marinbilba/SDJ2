package mediator;

import model.Radiator;
import model.Temperature;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainModelImplementation implements MainModel
{
  private PropertyChangeSupport support;
  private Radiator radiator;


  public MainModelImplementation()
  {
    support = new PropertyChangeSupport(this);
    radiator = new Radiator();
  }

  private String calcTimeStamp()
  {
    SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
    Date now = new Date();
    return sdfDate.format(now);
  }

  ////  Use for VM
  @Override public void turnUp()
  {
    radiator.turnUp();
    support.firePropertyChange("RadiatorState", null, radiator.getState());
  }

  @Override public void turnDown()
  {
    radiator.turnDown();
    support.firePropertyChange("RadiatorState", null, radiator.getState());
  }



  @Override public void updateExternalTemperature(String id, double value)
  {
    Temperature temp = new Temperature(id, value,calcTimeStamp());
    support.firePropertyChange("ExternalTemperatureUpdate", null, temp);
//    System.out
//        .println("External Temperature update: " + id + " " + value);
  }

  //  Use for VM
  @Override public void updateTemperature(String id, double value)
  {
    Temperature temperature = new Temperature(id, value,calcTimeStamp());
    support.firePropertyChange("TemperatureUpdate", null, temperature);
//    System.out.println("Temperature update: " + id + " " + value);

  }
  @Override public void addListener(PropertyChangeListener lstnr)
  {
    support.addPropertyChangeListener(lstnr);
  }

  @Override public void addPropertyChangeListener(String eventName,
      PropertyChangeListener lstnr)
  {
    if (eventName == null || eventName.equals(""))
    {
      support.addPropertyChangeListener(lstnr);
    }
    else
      support.addPropertyChangeListener(eventName, lstnr);
  }

  @Override public void removeListener(PropertyChangeListener lstnr)
  {
    support.removePropertyChangeListener(lstnr);
  }

  @Override public void removePropertyChangeListener(String eventName,
      PropertyChangeListener lstnr)
  {
    if (eventName == null || eventName.equals(""))
    {
      support.removePropertyChangeListener(lstnr);
    }
    else
      support.removePropertyChangeListener(eventName, lstnr);
  }
}
