package treasure_room;

public class Accountant implements Runnable
{
  private TreasureRoomDoor treasureRoomDoor;

  public Accountant(TreasureRoomDoor treasureRoomDoor)
  {
    this.treasureRoomDoor = treasureRoomDoor;
  }

  @Override public void run()
  {
    while (true){
      treasureRoomDoor.acquireRead();
      treasureRoomDoor.getTotalValueOfGems();
      treasureRoomDoor.releaseRead();
      try
      {
        Thread.sleep(10000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }
}
