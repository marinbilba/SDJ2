package mediator;

import model.Chatter;
import model.LocalModel;
import model.Message;
import model.MessageList;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.RemoteListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteModelManager
    implements ClientModel, RemoteListener<String, Message>
{
  private static final String HOST = "localhost";
  private String host;
  private RemoteModel remoteModel;
  private LocalModel model;
  private PropertyChangeAction<String, Message> property;

  public RemoteModelManager(LocalModel model, String host)
      throws RemoteException, NotBoundException, MalformedURLException
  {
    this.model = model;
    this.host = host;
    this.remoteModel = (RemoteModel) Naming
        .lookup("rmi://" + host + ":1099/chatSystem");
    UnicastRemoteObject.exportObject(this, 0);
    this.remoteModel.addListener(this);
    this.property = new PropertyChangeProxy<>(this, true);
  }

  public RemoteModelManager(LocalModel model)
      throws RemoteException, NotBoundException, MalformedURLException
  {
    this(model, HOST);
  }

  @Override public void sendMessage(Message message)
  {
    try
    {
      remoteModel.sendMessage(message);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public String getNumberOfChatters()
  {
    try
    {
      return remoteModel.getNumberOfChatters();
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public void disconnect(Chatter chatter)
  {
    try
    {
      remoteModel.disconnect(chatter);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public void connect(Chatter chatter)
  {
    try
    {
      remoteModel.connect(chatter);
    }
    catch (RemoteException e)
    {
      throw  new IllegalStateException(getExceptionMessage(e),e);
    }
  }

  @Override public MessageList getMessages()
  {
    try
    {
      return remoteModel.getMessages();
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public void close()
  {
    try
    {
      UnicastRemoteObject.unexportObject(this, true);
    }
    catch (Exception e)
    {
      throw new IllegalStateException("Cannot un export RMI object", e);
    }
  }

  @Override public void propertyChange(ObserverEvent<String, Message> event)
      throws RemoteException
  {
    property.firePropertyChange(event);
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

  private String getExceptionMessage(Exception e)
  {
    String message = e.getMessage();
    if (message != null)
      message = message.split(";")[0];
    return message;
  }


}
