package simple_chat_system.client.model;

import simple_chat_system.client.networking.Client;
import simple_chat_system.transferobjects.User;
import simple_chat_system.transferobjects.UsersPM;
import simple_chat_system.transferobjects.messages.Message;
import simple_chat_system.transferobjects.messages.PrivateMessage;
import simple_chat_system.transferobjects.messages.PublicMessage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

public class MainModelManager implements MainModel
{
  private Client client;
  private PropertyChangeSupport support;
  private User user;
  private UsersPM usersPM;

  public MainModelManager(Client client)
  {
    support = new PropertyChangeSupport(this);
    this.client = client;
    try
    {
      client.start();
      client.addListener("AddNewUser", this::addToUsersList);
      client.addListener("MessageForEveryone", this::receiveMessageInChat);
      client.addListener("SendInvitePM", this::receiveInvitePM);
      client.addListener("PrivateMessages", this::receiveMessagesPM);
      client.addListener("RemoveUser", this::removeFromUsersList);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void removeFromUsersList(PropertyChangeEvent propertyChangeEvent)
  {
    support.firePropertyChange(propertyChangeEvent);
  }

  // PRIVATE CHAT
  @Override public void sendInviteToPM(User user)
  {
    UsersPM usersPM = new UsersPM(this.user, user);
    client.invitePmToServer(usersPM);
  }

  private void receiveInvitePM(PropertyChangeEvent propertyChangeEvent)
  {
    UsersPM usersPM = ((UsersPM) propertyChangeEvent.getNewValue());
    support.firePropertyChange("SendInvitePM", null, usersPM);
  }

  @Override public void sendMessageInPmToServer(PrivateMessage message)
  {
    PrivateMessage pm = new PrivateMessage(message.getUser(), usersPM,
        message.getMsg());
    client.sendMessageInPMToServer(pm);
  }

  private void receiveMessagesPM(PropertyChangeEvent propertyChangeEvent)
  {
    PrivateMessage pm = (PrivateMessage) propertyChangeEvent.getNewValue();
    support.firePropertyChange("PrivateMessages", null, pm);
  }

  //  GLOBAL CHAT
  @Override public void sendListOfPmRoomUsers(UsersPM usersPM)
  {
    this.usersPM = usersPM;
    support.firePropertyChange("UsersOnlineInPM", null, usersPM);
  }

  @Override public void receiveMessageInChat(
      PropertyChangeEvent propertyChangeEvent)
  {
    PublicMessage publicMessage = (PublicMessage) propertyChangeEvent
        .getNewValue();
    support.firePropertyChange("MessageForEveryone", null, publicMessage);
  }

  @Override public void addToUsersList(PropertyChangeEvent propertyChangeEvent)
  {
    User user = (User) propertyChangeEvent.getNewValue();
    support.firePropertyChange("AddNewUser", null, user);
  }

  @Override public void sendMessage(Message message)
  {
    PublicMessage um = new PublicMessage(user, message);
    client.sendMessage(um);
  }

  @Override public void addUser(User username)
  {
    client.addUser(username);
    this.user = username;
    support.firePropertyChange("SetUsernameInChat", null, username);
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

}
