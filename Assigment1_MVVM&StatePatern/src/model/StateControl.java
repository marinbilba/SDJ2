package model;

public interface StateControl
{
  void turnUp(Radiator radiator);
  void turnDown(Radiator radiator);
  int getPower();
}
