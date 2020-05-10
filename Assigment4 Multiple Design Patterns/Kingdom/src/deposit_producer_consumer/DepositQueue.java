package deposit_producer_consumer;

import mine_Flyweight.Valuable;

public interface DepositQueue
{

	 void enqueue(Valuable element);
	 Valuable dequeue();
	 boolean isEmpty();

}
