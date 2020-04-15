package model;

import utility.observer.subject.LocalSubject;

public interface Model extends LocalSubject<String, Message>
{
  void sendMessage(Message message);
  String getNumberOfChatters();
  MessageList getMessages();
  void disconnect(Chatter chatter);
  void connect(Chatter chatter);
  void close();
}
