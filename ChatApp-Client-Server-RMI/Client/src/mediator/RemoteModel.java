package mediator;

import model.Chatter;
import model.Message;
import model.MessageList;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;

public interface RemoteModel extends RemoteSubject<String, Message>
{
  void sendMessage(Message message) throws RemoteException;
  String getNumberOfChatters() throws RemoteException;
  void disconnect(Chatter chatter) throws RemoteException;
  MessageList getMessages() throws RemoteException;
  void connect(Chatter chatter) throws RemoteException;
}
