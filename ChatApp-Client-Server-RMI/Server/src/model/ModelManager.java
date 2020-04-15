package model;

import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

public class ModelManager implements Model
{
  private MessageList messages;
  private ChatterBase chatters;
  private PropertyChangeAction<String, Message> property;

  public ModelManager()
  {
    this.messages = new MessageList();
    this.chatters = new ChatterBase();
    this.property = new PropertyChangeProxy<>(this);
  }

  @Override public void sendMessage(Message message)
  {
    messages.addMessage(message);
    property.firePropertyChange("add", null, message);
    property.firePropertyChange("number", chatters.getNumberOfChatters(), null);
  }

  @Override public String getNumberOfChatters()
  {
    return chatters.getNumberOfChatters();
  }

  @Override public MessageList getMessages()
  {
    return messages;
  }

  @Override public void disconnect(Chatter chatter)
  {
    chatters.disconnectChatter(chatter);
    property.firePropertyChange("number", getNumberOfChatters(), null);
    //System.out.println(getNumberOfChatters());
  }

  @Override public void connect(Chatter chatter)
  {
    chatters.addChatter(chatter);
    property.firePropertyChange("number", getNumberOfChatters(), null);
  }

  @Override public void close()
  {
    property.close();
  }

  @Override public boolean addListener(
      GeneralListener<String, Message> listener, String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, Message> listener, String... propertyNames)
  {
    return property.removeListener(listener, propertyNames);
  }
}
