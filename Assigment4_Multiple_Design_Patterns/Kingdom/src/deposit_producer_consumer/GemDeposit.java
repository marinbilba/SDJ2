package deposit_producer_consumer;

import logger_singleton.Log;
import mine_Flyweight.Valuable;
import utility.collection.ArrayList;

public class GemDeposit implements DepositQueue
{
  private ArrayList<Valuable> gemDeposit = new ArrayList<>();
  private int capacity;
  private Log log;

  public GemDeposit(int capacity)
  {
    log = Log.getLogEntry();
    this.capacity = capacity;
  }

  @Override public synchronized void enqueue(Valuable element)
  {
    if (element == null)
    {
      throw new IllegalArgumentException("Can't add null elements in the list");
    }
    else
    {
      while (gemDeposit.size() == capacity)
      {
        try
        {
          wait();
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
      if (gemDeposit.size() == 0)
      {
        notifyAll();
      }
      gemDeposit.add(element);
      log.add("Gem ADDED to the deposit " + gemDeposit.size() + "/" + capacity
          + "\n\t\t\tDeposit Value: " + getDepositGemValue());
      //  Temporary print !!!!!!!!!!!!
      System.out.println(
          "Gem ADDED to the deposit " + gemDeposit.size() + "/" + capacity
              + "\n\t\t\tDeposit Value: " + getDepositGemValue());
    }
  }

  @Override public synchronized Valuable dequeue()
  {
    while (gemDeposit.size() == 0)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    if (gemDeposit.size() == capacity)
    {
      notifyAll();
    }
    Valuable temp = gemDeposit.remove(0);
    log.add("Gem REMOVED from the deposit " + gemDeposit.size() + "/" + capacity
        + "\n\t\t\tDeposit Value: " + getDepositGemValue());
    //  Temporary print !!!!!!!!!!!!
    System.out.println(
        "Gem REMOVED from the deposit " + gemDeposit.size() + "/" + capacity
            + "\n\t\t\tDeposit Value: " + getDepositGemValue());
    return temp;

  }

  @Override public boolean isEmpty()
  {
    return gemDeposit.size() == 0;
  }

  public int getDepositGemValue()
  {
    int count = 0;
    for (int i = 0; i < gemDeposit.size(); i++)
    {
      count += gemDeposit.get(i).getValue();
    }
    return count;
  }

}

