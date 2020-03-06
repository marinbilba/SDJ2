package model;

public class Temperature
{
  private double value;
  private String id;
  private String timeCreated;

  public Temperature(String id, double value, String timeCreated)
  {
    this.value = value;
    this.id = id;
    this.timeCreated = timeCreated;
  }

  public double getValue()
  {
    return value;
  }

  public String getId()
  {
    return id;
  }

  //  IMPORTANT The method bellow is used for the table column
  public String getTimeCreated()
  {
    return timeCreated;
  }

  public String toString()
  {
    return "Id: " + id + "\n Value: " + value;
  }
}
