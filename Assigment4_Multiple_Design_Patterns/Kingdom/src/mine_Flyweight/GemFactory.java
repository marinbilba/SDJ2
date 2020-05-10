package mine_Flyweight;

import java.util.HashMap;

public class GemFactory
{
  private static HashMap<Valuables, Valuable> valuableHashMap = new HashMap<>();

  public static Valuable getValuable(Valuables valuableI)
  {
    Valuable valuable = valuableHashMap.get(valuableI);
    if (valuable == null)
    {

      switch (valuableI)
      {
        case GOLD:
          valuable = new Gold();
          break;
        case DIAMOND:
          valuable = new Diamond();
          break;
        case GOLD_NUGGET:
          valuable = new GoldNugget();
          break;
        case WOODEN_COIN:
          valuable = new WoodenCoin();
          break;
      }

      valuableHashMap.put(valuableI, valuable);
    }
    return valuable;
  }
}
