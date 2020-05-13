package treasure_room;

import mine_Flyweight.Valuable;

import java.util.ArrayList;

public class TreasureRoom implements TreasureRoomDoor
{

  private ArrayList<Valuable> treasure;

  public TreasureRoom()
  {
    treasure = new ArrayList<>();
  }

  @Override public void acquireRead()
  {
    // NO USE
  }

  @Override public void releaseRead()
  {
    // NO USE
  }

  @Override public void acquireWrite()
  {
    // NO USE
  }

  @Override public void releaseWrite()
  {
    // NO USE
  }

  public int getTotalValueOfGems()
  {
    int count = 0;
    for (int i = 0; i < treasure.size(); i++)
    {
      count += treasure.get(i).getValue();
    }
    return count;
  }

  @Override  public void putGemsInTreasureRoom(ArrayList<Valuable> valuables, String p)
  {
    for (int i = 0; i < valuables.size(); i++)
    {
      if (valuables.get(i) != null)
      {
        treasure.add(valuables.get(i));
      }
    }
  }

  @Override public Valuable takeGemFromTreasure()
  {
    if (treasure.size() == 0)
    {
      return null;
    }
    else
      return treasure.remove(treasure.size() - 1);
  }
}
