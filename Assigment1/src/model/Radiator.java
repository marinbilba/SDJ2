package model;

public class Radiator
{
  private StateControl currentState;

  public Radiator()
  {
    currentState = new OffState();
  }

  public void turnUp()
  {
    currentState.turnUp(this);
  }

  public void turnDown()
  {
    currentState.turnDown(this);
  }

  public void setState(StateControl state)
  {
    currentState = state;
  }

  public int getState()
  {
 return  currentState.getPower();

  }
}
