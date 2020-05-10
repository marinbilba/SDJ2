package mine_Flyweight;

public class Gold implements Valuable
{
  @Override public Valuables getName()
  {
    return Valuables.GOLD;
  }

  @Override public int getValue()
  {
    return 10;
  }
}
