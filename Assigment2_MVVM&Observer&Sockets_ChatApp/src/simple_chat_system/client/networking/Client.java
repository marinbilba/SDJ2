package simple_chat_system.client.networking;

import simple_chat_system.transferobjects.User;
import simple_chat_system.transferobjects.UsersPM;
import simple_chat_system.transferobjects.messages.PrivateMessage;
import simple_chat_system.transferobjects.messages.PublicMessage;
import simple_chat_system.transferobjects.util.Subject;

import java.io.IOException;

public interface Client extends Subject
{
  void start() throws IOException;
  void displayMessage(PublicMessage message);
  void addUser(User username);
  void sendMessage(PublicMessage um);
  void invitePmToServer(UsersPM usersPM);
  void sendMessageInPMToServer(PrivateMessage message);

}
