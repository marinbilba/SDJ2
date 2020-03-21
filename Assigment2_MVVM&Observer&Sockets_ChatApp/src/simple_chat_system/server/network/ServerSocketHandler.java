package simple_chat_system.server.network;

import simple_chat_system.transferobjects.*;
import simple_chat_system.transferobjects.messages.PrivateMessage;
import simple_chat_system.transferobjects.messages.PublicMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketHandler implements Runnable
{
  private ConnectionPool pool;
  private Socket socket;

  private ObjectOutputStream outToClient;
  private ObjectInputStream inFromClient;

  private User user;

  public ServerSocketHandler(Socket socket, ConnectionPool pool)
      throws IOException
  {
    this.socket = socket;
    this.pool = pool;
    inFromClient = new ObjectInputStream(socket.getInputStream());
    outToClient = new ObjectOutputStream(socket.getOutputStream());

  }

  @Override public void run()
  {
    try
    {
      while (true)
      {
        Object obj = inFromClient.readObject();
        if (obj instanceof User)
        {
          pool.addHandler(this);
          user = (User) obj;
          System.out
              .println("[SERVER] " + user.getUsername() + " joined the chat");
          pool.userJoin(user);
          UserList users = new UserList();
          users.addList(pool.getUsers());
          outToClient.writeObject(users);
        }
        else if (obj instanceof PublicMessage)
        {
          PublicMessage message = (PublicMessage) obj;
          pool.broadcastMessage(message);
        }
        else if (obj instanceof UsersPM)
        {
          UsersPM usersPM = (UsersPM) obj;
          pool.inviteToPM(usersPM);
        }
        else if (obj instanceof PrivateMessage)
        {
          PrivateMessage pm = (PrivateMessage) obj;
          pool.sendMessageInPM(pm);
        }
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  public void sendMessage(PublicMessage publicMessage)
  {
    try
    {
      System.out.println(
          "[SERVER] " + "user: " + publicMessage.getUsername() + " sent: "
              + publicMessage.getMessageString());
      outToClient.writeObject(publicMessage);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public User getUser()
  {
    return user;
  }

  public void joinChat(User user)
  {
    try
    {
      outToClient.writeObject(user);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void sendInvite(UsersPM usersPM)
  {
    try
    {
      outToClient.writeObject(usersPM);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void sendMessageInPM(PrivateMessage pm)
  {
    try
    {
      outToClient.writeObject(pm);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}

