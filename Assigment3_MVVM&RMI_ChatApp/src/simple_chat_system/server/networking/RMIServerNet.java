package simple_chat_system.server.networking;

import simple_chat_system.transferobjects.User;
import simple_chat_system.transferobjects.UserList;
import simple_chat_system.transferobjects.UsersPM;
import simple_chat_system.transferobjects.messages.PrivateMessage;
import simple_chat_system.transferobjects.messages.PublicMessage;
import simple_chat_system.transferobjects.networking.ClientNet;
import simple_chat_system.transferobjects.networking.ServerNet;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMIServerNet implements ServerNet
{
  private List<ClientNet> clientsForBroadcast;
  private UserList users = new UserList();

  public RMIServerNet() throws RemoteException
  {

    UnicastRemoteObject.exportObject(this, 0);
    clientsForBroadcast = new ArrayList<>();
  }

  public void start() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("Server", this);
    System.out.println("Server started");
  }

  @Override public void registerClient(ClientNet rmiClient)
  {
    clientsForBroadcast.add(rmiClient);
  }

  @Override public void sendInvitePMtoServer(UsersPM usersPM)
      throws RemoteException
  {
    for (ClientNet clients : clientsForBroadcast)
    {
      if (clients.getUser().equals(usersPM.getReceiver()))
      {
        clients.sendInvite(usersPM);
      }
    }
  }

  @Override public void broadcastMessage(PublicMessage message)

  {
    for (ClientNet clients : clientsForBroadcast)
    {
      try
      {
        clients.sendMessageToUser(message);
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }
  }

  @Override public void inviteToPMtoUser(UsersPM usersPM) throws RemoteException

  {
    for (ClientNet clients : clientsForBroadcast)
    {
      if (clients.getUser().equals(usersPM.getReceiver()))
      {
        clients.sendInvite(usersPM);
      }
    }
  }

  @Override public void sendMessageInPM(PrivateMessage message)
      throws RemoteException
  {
    for (ClientNet clients : clientsForBroadcast)
    {

      if (clients.getUser().equals(message.getUserOne()) || clients.getUser()
          .equals(message.getUserTwo()))
        clients.sendMessageInPM(message);
    }
  }

  @Override public void addUser(User username) throws RemoteException
  {
    System.out
        .println("[SERVER] " + username.getUsername() + " joined the chat");
    {

      updateUsersList(username);
    }

  }


  private void updateUsersList(User username) throws RemoteException
  {
    users.add(username);
    for (ClientNet clients : clientsForBroadcast)
    {
      for (int i = 0; i < users.getSize(); i++)
        {
          clients.addToUsersList(users.get(i));
        }

    }
  }
}
