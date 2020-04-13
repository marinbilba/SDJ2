package simple_chat_system.transferobjects.networking;

import simple_chat_system.transferobjects.User;
import simple_chat_system.transferobjects.UsersPM;
import simple_chat_system.transferobjects.messages.PrivateMessage;
import simple_chat_system.transferobjects.messages.PublicMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientNet extends Remote
{
  void sendMessageToUser(PublicMessage message) throws RemoteException;

  User getUser() throws RemoteException;
  void sendInvite(UsersPM usersPM) throws RemoteException;
  void sendMessageInPM(PrivateMessage message) throws RemoteException;
  void joinChat(User username) throws RemoteException;

  void addToUsersList(User user) throws RemoteException;

}
