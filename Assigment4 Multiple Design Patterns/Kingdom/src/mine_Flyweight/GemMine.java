package mine_Flyweight;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Random;

public class GemMine
{
  private ArrayList<Valuable> valuablesInMine;

  public GemMine(int maxGold, int maxDiamond, int maxGoldNugget,
      int maxWoodenCoin)
  {
    valuablesInMine = new ArrayList<>();
    for (int i = 0; i < maxGold; i++)
    {
      Valuable gold = GemFactory.getValuable(Valuables.GOLD);
      valuablesInMine.add(gold);
    }
    for (int i = 0; i < maxDiamond; i++)
    {
      Valuable diamond = GemFactory.getValuable(Valuables.DIAMOND);
      valuablesInMine.add(diamond);
    }
    for (int i = 0; i < maxGoldNugget; i++)
    {
      Valuable goldNugget = GemFactory.getValuable(Valuables.GOLD_NUGGET);
      valuablesInMine.add(goldNugget);
    }
    for (int i = 0; i < maxWoodenCoin; i++)
    {
      Valuable woodenCoin = GemFactory.getValuable(Valuables.WOODEN_COIN);
      valuablesInMine.add(woodenCoin);
    }
  }

  public synchronized Valuable getRandomGem()
  {
    Valuable temp=null;
    Valuables[] valuables = {Valuables.GOLD, Valuables.WOODEN_COIN,
        Valuables.GOLD_NUGGET, Valuables.DIAMOND};
    int rnd = new Random().nextInt(valuables.length);

    for (int i = 0; i < valuablesInMine.size(); i++)
    {
      if (valuablesInMine.get(i).getName().equals(valuables[rnd]))
      {
        temp = valuablesInMine.get(i);
        valuablesInMine.remove(temp);
      }
    }
    if (temp==null){
      ArrayUtils.removeElement(valuables, rnd);
    }
    return temp;
  }
}
