package model;

public class OffState implements StateControl
{
  private static final int POWER=0;
  @Override public void turnUp(Radiator radiator)
  {
    System.out.println("Radiator switched to state 1");
    radiator.setState(new State1());
  }

  @Override public void turnDown(Radiator radiator)
  {
    System.out.println("Min state reached");
  }
  @Override public int getPower()
  {
    return POWER;
  }

}
