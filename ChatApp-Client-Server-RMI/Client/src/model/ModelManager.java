package model;

import mediator.ClientModel;
import mediator.RemoteModelManager;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

public class ModelManager implements LocalModel, LocalListener<String, Message>
{
  private ClientModel serverModel;
  private PropertyChangeAction<String, Message> property;
  private Chatter chatter;

  public ModelManager()
  {
    this.chatter = new Chatter(getRandomIntegerBetweenRange(1000, 9999));
    try
    {
      this.serverModel = new RemoteModelManager(this);
      this.serverModel.addListener(this);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    property = new PropertyChangeProxy<>(this, true);
    serverModel.connect(this.chatter);
  }

  @Override public void sendMessage(Message message)
  {
    serverModel.sendMessage(message);
  }

  @Override public String getNumberOfChatters()
  {
    return serverModel.getNumberOfChatters();
  }

  @Override public void disconnect(Chatter chatter)
  {
    serverModel.disconnect(chatter);
  }

  @Override public void connect(Chatter chatter)
  {
    serverModel.connect(chatter);
  }

  @Override public MessageList getMessages()
  {
    return serverModel.getMessages();
  }

  @Override public Chatter getChatter()
  {
    return chatter;
  }

  @Override public void close()
  {
    try
    {
      property.close();
      serverModel.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override public void propertyChange(ObserverEvent<String, Message> event)
  {
    property.firePropertyChange("add", null, event.getValue2());
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

  public static String getRandomIntegerBetweenRange(double min, double max)
  {
    double x = (int) (Math.random() * ((max - min) + 1)) + min;
    return Integer.toString((int) x);
  }
}
