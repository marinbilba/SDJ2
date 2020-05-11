package simple_chat_system.client.model;

import simple_chat_system.transferobjects.User;
import simple_chat_system.transferobjects.UsersPM;
import simple_chat_system.transferobjects.messages.Message;
import simple_chat_system.transferobjects.messages.PrivateMessage;
import simple_chat_system.transferobjects.util.Subject;

import java.beans.PropertyChangeEvent;

public interface MainModel extends Subject
{
  void sendMessage(Message message);
  void addUser(User username);
  void receiveMessageInChat(PropertyChangeEvent propertyChangeEvent);
  void addToUsersList(PropertyChangeEvent propertyChangeEvent);
  void sendInviteToPM(User user);
  void sendListOfPmRoomUsers(UsersPM usersPM);
  void sendMessageInPmToServer(PrivateMessage message);
}
