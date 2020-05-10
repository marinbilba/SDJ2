package treasure_room;

import logger_singleton.Log;
import mine_Flyweight.Valuable;

import java.util.ArrayList;

public class TreasureGuard implements TreasureRoomDoor
{
  private boolean activeWriter;
  private int activeReaders;
  private int waitingWriters;
  private Log log;

  private TreasureRoom treasureRoom;

  public TreasureGuard(TreasureRoom treasureRoom)
  {
    log = Log.getLogEntry();
    this.treasureRoom = treasureRoom;
  }

  @Override public int getTotalValueOfGems()
  {

    int temp = treasureRoom.getTotalValueOfGems();
    log.add("***Accountant COUNTED the following value of gems: " + temp);
    System.out
        .println("***Accountant COUNTED the following value of gems: " + temp);

    return temp;

  }

  @Override public void putGemsInTreasureRoom(ArrayList<Valuable> valuables,
      String person)
  {

    int count=0;
    treasureRoom.putGemsInTreasureRoom(valuables, person);
    for (int i = 0; i < valuables.size(); i++)
    {
      if(valuables.get(i)!=null)
      {
        count += valuables.get(i).getValue();
      }
      }
    log.add(
        "*** " + person + " PUT into treasure room following number of gems: "
            + valuables.size() + " value: "+count);
    System.out.println(
        "*** " + person + "  PUT into treasure room following number of gems: "
            + valuables.size()+ " value: "+count);

  }

  @Override public Valuable takeGemsFromTreasure()
  {
    Valuable valuable= treasureRoom.takeGemsFromTreasure();
    if (valuable!=null)
    {
      log.add("***KING Has Taken one gem value: " + valuable.getValue());
    }
return valuable;
  }

  @Override public synchronized void acquireRead()
  {
    while (activeWriter || waitingWriters > 0)
    {
      try
      {
        wait();
      }
      catch (InterruptedException ignored)
      {
      }
    }
    activeReaders++;
    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tREAD ACQUIRED");
  }

  @Override public synchronized void releaseRead()
  {
    activeReaders--;
    if (activeReaders == 0)
    {
      notifyAll();
    }
    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tREAD RELEASED");
  }

  @Override public synchronized void acquireWrite()
  {
    waitingWriters++;
    while (activeWriter || activeReaders > 0)
    {
      try
      {
        wait();
      }
      catch (InterruptedException ignored)
      {
      }
    }
    waitingWriters--;
    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tLOCK ACQUIRED");
    activeWriter = true;
  }

  @Override public synchronized void releaseWrite()
  {

    activeWriter = false;
    notifyAll();
    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tLOCK RELEASED");
  }
}
