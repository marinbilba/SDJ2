package mediator;

import model.Chatter;
import model.Message;
import model.MessageList;
import utility.observer.subject.LocalSubject;

public interface ClientModel extends LocalSubject<String, Message>
{
  void sendMessage(Message message);
  String getNumberOfChatters();
  void disconnect(Chatter chatter);
  void connect(Chatter chatter);
  MessageList getMessages();
  void close();
}

