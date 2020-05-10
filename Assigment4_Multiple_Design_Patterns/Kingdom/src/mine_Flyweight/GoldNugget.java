package mine_Flyweight;

public class GoldNugget implements Valuable
{
  @Override public Valuables getName()
  {
    return Valuables.GOLD_NUGGET;
  }

  @Override public int getValue()
  {
    return 5;
  }
}
