package simple_chat_system.client.networking;

import simple_chat_system.transferobjects.User;
import simple_chat_system.transferobjects.UsersPM;
import simple_chat_system.transferobjects.messages.PrivateMessage;
import simple_chat_system.transferobjects.messages.PublicMessage;
import simple_chat_system.transferobjects.networking.ClientNet;
import simple_chat_system.transferobjects.networking.ServerNet;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIClient implements Client, ClientNet
{
  private User user;
  private ServerNet serverNet;
  private PropertyChangeSupport support;

  public RMIClient() throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
  }

  @Override public void start() throws IOException
  {

    support = new PropertyChangeSupport(this);
    Registry registry = LocateRegistry.getRegistry("localhost", 1099);
    try
    {
      serverNet = (ServerNet) registry.lookup("Server");
      serverNet.registerClient(this);
    }
    catch (NotBoundException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void displayMessage(PublicMessage message)
  {
    support.firePropertyChange("MessageForEveryone", null, message);
  }

  @Override public void sendMessage(PublicMessage message)
  {
    try
    {
      serverNet.broadcastMessage(message);
      System.out.println(
          "[SERVER] " + "user: " + message.getUsername() + " sent: " + message
              .getMessageString());
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void invitePmToServer(UsersPM usersPM)
  {
    try
    {
      serverNet.sendInvitePMtoServer(usersPM);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void sendMessageInPMToServer(PrivateMessage message)
  {
    try
    {
      serverNet.sendMessageInPM(message);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }

  }

  @Override public void addUser(User username)
  {
    user = username;
    try
    {
      serverNet.addUser(username);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);
  }

  public void removeFromList(User user)
  {
    support.firePropertyChange("RemoveUser", null, user);
  }

  @Override public void sendMessageToUser(PublicMessage message)
  {
    displayMessage(message);
  }

  @Override public void sendInvite(UsersPM usersPM)
  {
    support.firePropertyChange("SendInvitePM", null, usersPM);
  }

  @Override public void sendMessageInPM(PrivateMessage message)
  {
    support.firePropertyChange("PrivateMessages", null, message);
  }

  @Override public void joinChat(User username)
  {
    System.out
        .println("[CLIENT] user " + user.getUsername() + " added to list");
    support.firePropertyChange("AddNewUser", null, user);
  }

  @Override public void addToUsersList(User user)
  {
    System.out
        .println("[CLIENT] user " + user.getUsername() + " added to list");
    support.firePropertyChange("AddNewUser", null, user);
  }

  @Override public User getUser()
  {
    return user;
  }
}
