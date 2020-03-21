package simple_chat_system.server.network;

import simple_chat_system.transferobjects.*;
import simple_chat_system.transferobjects.messages.PrivateMessage;
import simple_chat_system.transferobjects.messages.PublicMessage;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool
{
  private List<ServerSocketHandler> connections = new ArrayList<>();
  private List<User> users = new ArrayList<>();

  public void broadcastMessage(PublicMessage publicMessage)
  {
    for (ServerSocketHandler handler : connections)
    {
      handler.sendMessage(publicMessage);
    }
  }

  public void userJoin(User user)
  {
    for (ServerSocketHandler handler : connections)
    {
      if (handler.getUser() != null &&!handler.getUser().equals(user))
        {
          handler.joinChat(user);
        }
    }
    users.add(user);
  }
  public void inviteToPM(UsersPM usersPM)
  {
    for (ServerSocketHandler handler : connections)
    {
      if (handler.getUser().equals(usersPM.getReceiver()))
      {
        handler.sendInvite(usersPM);
      }
    }
  }
  public synchronized void addHandler(ServerSocketHandler handler)
  {
    connections.add(handler);
  }

  public void removeHandler(ServerSocketHandler handler)
  {
    connections.remove(handler);
    users.remove(handler.getUser());
  }

  public List<User> getUsers()
  {
    return users;
  }

  public void sendMessageInPM(PrivateMessage pm)
  {
    for (ServerSocketHandler handler : connections)
    {

      if(handler.getUser().equals(pm.getUserOne())||handler.getUser().equals(pm.getUserTwo()))
        handler.sendMessageInPM(pm);
    }
  }
}
