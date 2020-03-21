package simple_chat_system.client.core;

import simple_chat_system.client.networking.Client;
import simple_chat_system.client.networking.SocketClient;

public class ClientFactory
{
  private Client client;

  public Client getClient()
  {
    if(client==null){
      client=new SocketClient();
    }
    return client;
  }
}
