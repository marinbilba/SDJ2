package simple_chat_system.server;

import simple_chat_system.server.network.Server;

public class RunServer
{
  public static void main(String[] args)
  {
    Server es=new Server();
    es.start();
  }
}
