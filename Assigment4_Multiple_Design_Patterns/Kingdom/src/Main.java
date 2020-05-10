import deposit_producer_consumer.GemDeposit;
import deposit_producer_consumer.Miner;
import deposit_producer_consumer.Transporter;
import mine_Flyweight.GemMine;
import treasure_room.*;

public class Main
{
  public static void main(String[] args)
  {
    GemMine gemMine=new GemMine(500,500,500,500);
    GemDeposit gemDeposit=new GemDeposit(500);

    TreasureRoom treasureRoom=new TreasureRoom();
    TreasureRoomDoor treasureRoomDoor=new TreasureGuard(treasureRoom);

    Miner miner =new Miner(gemMine,gemDeposit);
    Transporter transporter =new Transporter(gemDeposit,treasureRoomDoor);
    Accountant accountant=new Accountant(treasureRoomDoor);
    King king =new King(treasureRoomDoor);

    Thread thread1=new Thread(miner);
    Thread thread2=new Thread(miner);
    Thread thread3=new Thread(miner);
    Thread thread4=new Thread(transporter);
    Thread thread5=new Thread(accountant);
    Thread thread6=new Thread(king);

    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();
    thread5.start();
  thread6.start();
  }

}
