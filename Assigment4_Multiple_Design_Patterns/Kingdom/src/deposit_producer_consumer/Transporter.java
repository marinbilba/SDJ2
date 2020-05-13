package deposit_producer_consumer;

import mine_Flyweight.Valuable;
import treasure_room.TreasureRoomDoor;

import java.util.ArrayList;

public class Transporter implements Runnable
{

  private DepositQueue gemDeposit;
  private ArrayList<Valuable> cart;
  private TreasureRoomDoor treasureRoomDoor;

  public Transporter(DepositQueue gemDeposit,
      TreasureRoomDoor treasureRoomDoor)
  {
    this.treasureRoomDoor = treasureRoomDoor;
    this.gemDeposit = gemDeposit;
  }

  public static int getRandomInteger(int maximum, int minimum)
  {
    return ((int) (Math.random() * (maximum - minimum))) + minimum;
  }

  @Override public void run()
  {
    while (true)
    {
      //      !!!!!!!!!!!!!!!!!!!
      int count = 0;
      int count2 = 0;
      cart = new ArrayList<>();

      int random = getRandomInteger(50, 200);
      do
      {
        Valuable tempValuable = gemDeposit.dequeue();
        cart.add(tempValuable);
        count += tempValuable.getValue();
        count2++;
        System.out.println(
            "\t\t\t\t\t\t Transporter Cart(Value): " + count + " No Gems:"
                + count2);
        try
        {
          Thread.sleep(1);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
      while (count < random);

      try
      {
        treasureRoomDoor.acquireWrite();
        treasureRoomDoor.putGemsInTreasureRoom(cart, "TRANSPORTER");
        treasureRoomDoor.releaseWrite();
        Thread.sleep(50000);
        cart.clear();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }
}
