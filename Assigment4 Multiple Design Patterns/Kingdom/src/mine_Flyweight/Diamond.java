package mine_Flyweight;

public class Diamond implements Valuable
{
  @Override public Valuables getName()
  {
    return Valuables.DIAMOND;
  }

  @Override public int getValue()
  {
    return 20;
  }
}
