package mine_Flyweight;

public class WoodenCoin implements Valuable
{
  @Override public Valuables getName()
  {
    return Valuables.WOODEN_COIN;
  }

  @Override public int getValue()
  {
    return 2;
  }
}
