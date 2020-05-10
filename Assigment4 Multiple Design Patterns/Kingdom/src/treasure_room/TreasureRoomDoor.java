package treasure_room;

import mine_Flyweight.Valuable;

import java.util.ArrayList;

public interface TreasureRoomDoor
{
  int getTotalValueOfGems();
  void putGemsInTreasureRoom(ArrayList<Valuable> valuables, String person);
  Valuable takeGemsFromTreasure();


  void acquireRead();
  void releaseRead();
  void acquireWrite();
  void releaseWrite();
}
