package treasure_room;

import mine_Flyweight.Valuable;

import java.util.ArrayList;

import static deposit_producer_consumer.Transporter.getRandomInteger;

public class King implements Runnable
{
  private TreasureRoomDoor treasureRoomDoor;

  public King(TreasureRoomDoor treasureRoomDoor)
  {
    this.treasureRoomDoor = treasureRoomDoor;
  }

  @Override public void run()
  {

    while (true)
    {
      boolean end=true;
      int count = 0;
      ArrayList<Valuable>  kingPocket= new ArrayList<>();
      int random = getRandomInteger(50, 150);
      treasureRoomDoor.acquireWrite();
      System.out.println("***\t\t\t\t\tKING\t\t\t\tPARTY TIME");
      do
      {
        Valuable valuable = treasureRoomDoor.takeGemsFromTreasure();
        if (valuable != null)
        {
          kingPocket.add(valuable);
          count += valuable.getValue();
          System.out.println("***KING Has Taken a gem. Total gems in pocket: " + count
              + "\n\t\t\t\t\t Target " + random);
          if (count>random){
            System.out.println("***KING PARTY PARTY PARTY*** spent(value): "+count);
            treasureRoomDoor.getTotalValueOfGems();
          }
        }
        else
        {
          System.out.println("***KING NO PARTY TODAY");
          treasureRoomDoor.putGemsInTreasureRoom(kingPocket, "KING");
          end = false;
        }
      }
      while (count < random && end);


      treasureRoomDoor.releaseWrite();
      try
      {
        System.out.println("KING SLEEPING");
        Thread.sleep(20000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }
}
