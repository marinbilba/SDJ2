package simple_chat_system.client.views.chat;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import simple_chat_system.client.model.MainModel;
import simple_chat_system.transferobjects.InviteAccept;
import simple_chat_system.transferobjects.User;
import simple_chat_system.transferobjects.UsersPM;
import simple_chat_system.transferobjects.messages.Message;
import simple_chat_system.transferobjects.messages.PublicMessage;
import simple_chat_system.transferobjects.util.Subject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatClientViewModel implements Subject
{
  private User currentUser;
  private PropertyChangeSupport support;
  private ObservableList<User> usersList;
  private MainModel mainModel;
  private StringProperty message;
  public ChatClientViewModel(MainModel mainModel)
  {
    support=new PropertyChangeSupport(this);
    message=new SimpleStringProperty();
    usersList = FXCollections.observableArrayList();
this.mainModel=mainModel;
mainModel.addListener("AddNewUser",this::getUsersList);
    mainModel.addListener("MessageForEveryone",this::displayMessageToEveryone);
    mainModel.addListener("SendInvitePM",this::receiveInvitePM);
    mainModel.addListener("SendInviteAcceptPM",this::sendInviteAcceptPM);
    mainModel.addListener("SetUsernameInChat",this::setUsernameInChat);
    mainModel.addListener("RemoveUser",this::removeFromUsersList);
  }

  private void removeFromUsersList(PropertyChangeEvent propertyChangeEvent)
  {
    User user= (User) propertyChangeEvent.getNewValue();
    Platform.runLater( () -> {
      usersList.remove(user);
      System.out.println(usersList);
    });
  }

  private void setUsernameInChat(PropertyChangeEvent propertyChangeEvent)
  {
     currentUser=(User) propertyChangeEvent.getNewValue();
  }

  private void sendInviteAcceptPM(PropertyChangeEvent propertyChangeEvent)
  {
    InviteAccept inviteAccept=(InviteAccept)propertyChangeEvent.getNewValue();
    support.firePropertyChange("SendInviteAcceptPM",null,inviteAccept);
  }

  private void receiveInvitePM(PropertyChangeEvent propertyChangeEvent)
  {
    UsersPM usersPM = ((UsersPM) propertyChangeEvent.getNewValue());
    support.firePropertyChange("SendInvite",null, usersPM);
  }

  private void displayMessageToEveryone(PropertyChangeEvent propertyChangeEvent)
  {
    PublicMessage publicMessage =(PublicMessage) propertyChangeEvent.getNewValue();
    message.setValue(publicMessage.getUsername()+": "+ publicMessage.getMessageString());
  }

  private void getUsersList(PropertyChangeEvent propertyChangeEvent)
  {
   User user= (User) propertyChangeEvent.getNewValue();
   Platform.runLater( () -> {
      usersList.add(user);
      System.out.println(usersList);
    });
  }

  public void sendMessageToEveryone(Message message)
  {
    mainModel.sendMessage(message);
  }

  public ObservableList<User> getUsersList()
{
  return usersList;
}

  public StringProperty messageProperty()
  {
    return message;
  }

  public void sentInviteToPM(User user)
  {
    mainModel.sendInviteToPM(user);
  }

  public User getCurrentUser()
  {
    return currentUser;
  }

  public void sendListOfPmRoomUsers(UsersPM usersPM)
  {
    mainModel.sendListOfPmRoomUsers(usersPM);
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
