package model.domain;

import java.io.Serializable;

public class Burger implements Serializable
{
  private String name;

  public Burger(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }

  @Override public String toString()
  {
    return "Burger{" + "name='" + name + '\'' + '}';
  }

}
