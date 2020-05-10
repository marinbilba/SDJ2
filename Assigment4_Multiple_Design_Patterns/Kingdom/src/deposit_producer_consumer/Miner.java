package deposit_producer_consumer;

import mine_Flyweight.GemMine;
import mine_Flyweight.Valuable;

public class Miner implements Runnable
{
  private GemMine gemMine;
  private GemDeposit gemDeposit;

  public Miner(GemMine gemMine, GemDeposit gemDeposit)
  {
    this.gemMine = gemMine;
    this.gemDeposit = gemDeposit;
  }

  @Override public void run()
  {
    while (true)
    {
      Valuable valuable = gemMine.getRandomGem();
      if (valuable != null)
      {
        gemDeposit.enqueue(valuable);
        try
        {
          Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
    }
  }
}
