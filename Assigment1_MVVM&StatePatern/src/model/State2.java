package model;

public class State2 implements StateControl
{
  private static final int POWER = 2;

  @Override public void turnUp(Radiator radiator)
  {
    System.out.println("Radiator switched to state 3");
    radiator.setState(new State3(radiator));
  }

  @Override public void turnDown(Radiator radiator)
  {
    System.out.println("Radiator switched to state 1");
    radiator.setState(new State1());
  }

  @Override public int getPower()
  {
    return POWER;
  }
}
