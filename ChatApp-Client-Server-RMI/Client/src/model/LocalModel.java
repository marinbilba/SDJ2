package model;

import utility.observer.subject.LocalSubject;

public interface LocalModel extends LocalSubject<String, Message>
{
  void sendMessage(Message message);
  String getNumberOfChatters();
  void disconnect(Chatter chatter);
  void connect(Chatter chatter);
  MessageList getMessages();
  Chatter getChatter();
  void close();
}
