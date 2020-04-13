package simple_chat_system.client.core;

import simple_chat_system.client.networking.Client;
import simple_chat_system.client.networking.RMIClient;

import java.rmi.RemoteException;

public class ClientFactory
{
  private Client client;

  public Client getClient()
  {
    if(client==null){
      try
      {
        client=new RMIClient();
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }
    return client;
  }
}
