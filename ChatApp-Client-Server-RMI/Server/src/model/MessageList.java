package model;

import java.io.Serializable;
import java.util.ArrayList;

public class MessageList implements Serializable
{
  private ArrayList<Message> messages;

  public MessageList()
  {
    messages = new ArrayList<>();
  }

  public void addMessage(Message message)
  {
    messages.add(message);
  }

  public int getSize()
  {
    return messages.size();
  }

  public Message getMessage(int index)
  {
    return messages.get(index);
  }
}
