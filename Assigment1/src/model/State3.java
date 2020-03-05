package model;

import java.beans.PropertyChangeSupport;

public class State3 implements StateControl
{
  private static final int POWER=3;
  private Thread thread;
  private Radiator radiator;
private PropertyChangeSupport support;

  public State3(Radiator radiator)
  {
    support=new PropertyChangeSupport(this);
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
    support.firePropertyChange("RadiatorState", null, radiator.getState());
    System.out.println(radiator.getState());
    System.out.println("Radiator switched to state 2");
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
        Thread.sleep(50000);
        turnDown(radiator);
      }
      catch (InterruptedException e)
      {

      }
    }
  }
}
