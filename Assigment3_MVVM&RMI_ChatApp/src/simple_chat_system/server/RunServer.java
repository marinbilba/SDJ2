package simple_chat_system.server;

import simple_chat_system.server.networking.RMIServerNet;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class RunServer
{
  public static void main(String[] args)
      throws RemoteException, AlreadyBoundException
  {
    RMIServerNet rmiServer=new RMIServerNet();
    rmiServer.start();
 //   Server es=new Server();
   // es.start();
  }
}
