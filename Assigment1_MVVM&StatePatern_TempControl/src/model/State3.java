package model;

import javafx.application.Platform;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class State3 implements StateControl
{
  private static final int POWER = 3;
  private Thread thread;
  private Radiator radiator;
  private PropertyChangeSupport support;

  public State3(Radiator radiator)
  {
    support = new PropertyChangeSupport(this);
    this.radiator = radiator;
    thread = new Thread(new innerClass());
    thread.start();
  }

  @Override public void turnUp(Radiator radiator)
  {
    System.out.println("Max state reached");
  }

  @Override public void turnDown(Radiator radiator)
  {
    thread.interrupt();
    radiator.setState(new State2());
    Platform.runLater(new Runnable()
    {
      @Override public void run()
      {
        support.firePropertyChange("RadiatorState", this, radiator.getState());
        System.out.println(radiator.getState());
        System.out.println("Radiator switched to state 2");
      }
    });
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

  @Override public int getPower()
  {
    return POWER;
  }

  class innerClass implements Runnable
  {

    @Override public void run()
    {
      try
      {
        Thread.sleep(6000);
        turnDown(radiator);
      }
      catch (InterruptedException e)
      {

      }
    }
  }
}
