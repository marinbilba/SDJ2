package simple_chat_system.transferobjects.networking;

import simple_chat_system.transferobjects.User;
import simple_chat_system.transferobjects.UsersPM;
import simple_chat_system.transferobjects.messages.PrivateMessage;
import simple_chat_system.transferobjects.messages.PublicMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerNet extends Remote
{
  void broadcastMessage(PublicMessage message) throws RemoteException;
  void inviteToPMtoUser(UsersPM usersPM)
      throws RemoteException;
  void registerClient(ClientNet rmiClient) throws RemoteException;
  void sendInvitePMtoServer(UsersPM usersPM) throws RemoteException;
  void sendMessageInPM(PrivateMessage message) throws RemoteException;
  void addUser(User username) throws RemoteException;

}
