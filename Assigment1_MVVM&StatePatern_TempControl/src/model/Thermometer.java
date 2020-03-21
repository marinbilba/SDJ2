package model;

import mediator.MainModel;

import java.beans.PropertyChangeEvent;
import java.util.concurrent.locks.ReentrantLock;

//!!!!!!!!!! IMPORTANT
// Both thermometers(indoor and outdoor) should be created before starting the threads
public class Thermometer implements Runnable
{
  private ReentrantLock lock = new ReentrantLock();
  private double distance;
  private double temperature;
  //  Should be added in UML
  private String id;
  private String externalId;

  private double externalTemperature;
  private int radiatorState;
  private double minTemperature;
  private double maxTemperature;
  private MainModel mainModel;

  //  Constructor for indoor thermometer
  public Thermometer(MainModel mainModel, String id, double temperature,
      int distance)
  {
    mainModel
        .addPropertyChangeListener("RadiatorState", this::updateRadiatorState);
    this.mainModel = mainModel;
    this.id = id;
    this.temperature = temperature;
    this.distance = distance;
    externalId = "T0";
    externalTemperature = 15;
    minTemperature = -10;
    maxTemperature = 25;
  }

  //  Constructor for outdoor thermometer
  //  public Thermometer(MainModel mainModel, String externalID, double externalTemperature,
  //      double minTemperature, double maxTemperature)
  //  {
  //    this.mainModel = mainModel;
  //    this.externalId = externalID;
  //    this.externalTemperature = externalTemperature;
  //    this.minTemperature = minTemperature;
  //    this.maxTemperature = maxTemperature;
  //  }
  //

  /**
   * Calculating the internal temperature in one of two locations.
   * This includes a term from a heater (depending on location and
   * heaters power), and a term from an outdoor heat loss.
   * Values are only valid in the outdoor temperature range [-20; 20]
   * and when s, the number of seconds between each measurements are
   * between 4 and 8 seconds.
   *
   * @param t  the last measured temperature
   * @param p  the heaters power {0, 1, 2 or 3} where 0 is turned off,
   *           1 is low, 2 is medium and 3 is high
   * @param d  the distance between heater and measurements {1 or 7}
   *           where 1 is close to the heater and 7 is in the opposite corner
   * @param t0 the outdoor temperature (valid in the range [-20; 20])
   * @param s  the number of seconds since last measurement [4; 8]
   * @return the temperature
   */

  public double temperature(double t, int p, double d, double t0, int s)
  {
    double tMax = Math.min(11 * p + 10, 11 * p + 10 + t0);
    tMax = Math.max(Math.max(t, tMax), t0);
    double heaterTerm = 0;
    if (p > 0)
    {
      double den = Math.max((tMax * (20 - 5 * p) * (d + 5)), 0.1);
      heaterTerm = 30 * s * Math.abs(tMax - t) / den;
    }
    double outdoorTerm = (t - t0) * s / 250.0;
    t = Math.min(Math.max(t - outdoorTerm + heaterTerm, t0), tMax);
    //    Setting fields values
    this.distance = d;
    this.temperature = t;
    return t;
  }

  /**
   * Calculating the external temperature.
   * Values are only valid if the temperature is being measured
   * approximately every 10th second.
   *
   * @param t0  the last measured external temperature
   * @param min a lower limit (may temporally be deceeded)
   * @param max an upper limit (may temporally be exceeded)
   * @return an updated external temperature
   */
  public double externalTemperature(double t0, double min, double max)
  {
    double left = t0 - min;
    double right = max - t0;
    int sign = Math.random() * (left + right) > left ? 1 : -1;
    t0 += sign * Math.random();
    //    Setting fields values
    this.externalTemperature = t0;
    return t0;
  }

  public void updateRadiatorState(PropertyChangeEvent evt)
  {
    radiatorState = (int) evt.getNewValue();
  }

  @Override public void run()
  {
    while (true)
    {
      lock.lock();
      try
      {
        externalTemperature(externalTemperature, minTemperature,
            maxTemperature);
        temperature(temperature, radiatorState, distance, externalTemperature,
            6);
        mainModel.updateTemperature(id, temperature);
        mainModel.updateExternalTemperature(externalId, externalTemperature);
      }
      finally
      {
        lock.unlock();
      }

      try
      {
        Thread.sleep(6000);
      }
      catch (InterruptedException e)
      {
        //none
      }
    }
  }

}

