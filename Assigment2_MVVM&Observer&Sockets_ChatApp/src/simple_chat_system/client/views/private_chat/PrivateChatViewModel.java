package simple_chat_system.client.views.private_chat;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import simple_chat_system.client.model.MainModel;
import simple_chat_system.transferobjects.messages.PrivateMessage;
import simple_chat_system.transferobjects.User;
import simple_chat_system.transferobjects.UsersPM;
import simple_chat_system.transferobjects.util.Subject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PrivateChatViewModel
{
  private MainModel mainModel;
  private ObservableList<User> users;
  private PropertyChangeSupport support;
  private StringProperty message;
  public PrivateChatViewModel(MainModel mainModel)
  {
    message=new SimpleStringProperty();
    support=new PropertyChangeSupport(this);
    this.mainModel = mainModel;
    users= FXCollections.observableArrayList();
    support=new PropertyChangeSupport(this);
    mainModel.addListener("UsersOnlineInPM",this::addToUserList);
    mainModel.addListener("PrivateMessages",this::displayMessageInChat);
  }

  private void displayMessageInChat(PropertyChangeEvent propertyChangeEvent)
  {
    PrivateMessage pm=(PrivateMessage) propertyChangeEvent.getNewValue();
    message.setValue(pm.getUsername()+": "+pm.getMsg());
  }

  private void addToUserList(PropertyChangeEvent propertyChangeEvent)
  {
    UsersPM user= (UsersPM) propertyChangeEvent.getNewValue();
    Platform.runLater( () -> {
      users.add(user.getSender());
      users.add(user.getReceiver());
    });
  }

  public ObservableList<User> getUsers()
  {
    return users;
  }

  public void sendMessageInPM(PrivateMessage message)
  {
    mainModel.sendMessageInPmToServer(message);
  }

  public StringProperty messageProperty()
  {
    return message;
  }

}
