package model;

public class State1 implements StateControl
{
  private static final int POWER=1;
  @Override public void turnUp(Radiator radiator)
  {
    System.out.println("Radiator switched to state 2");
    radiator.setState(new State2());
  }

  @Override public void turnDown(Radiator radiator)
  {
    System.out.println("Radiator switched to state 0");
    radiator.setState(new OffState());
  }
  @Override public int getPower()
  {
    return POWER;
  }

}
