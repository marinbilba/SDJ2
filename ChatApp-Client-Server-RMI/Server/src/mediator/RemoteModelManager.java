package mediator;

import model.Chatter;
import model.Message;
import model.MessageList;
import model.Model;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RemoteModelManager
    implements RemoteModel, LocalListener<String, Message>
{
  private Model model;
  private PropertyChangeAction<String, Message> property;

  public RemoteModelManager(Model model)
      throws MalformedURLException, RemoteException
  {
    this.property = new PropertyChangeProxy<>(this, true);
    this.model = model;
    this.model.addListener(this, "add");
    startRegistry();
    startServer();
  }

  private void startServer() throws RemoteException, MalformedURLException
  {
    UnicastRemoteObject.exportObject(this, 0);
    Naming.rebind("chatSystem", this);
    System.out.println("Server started...");
  }

  private void startRegistry() throws RemoteException
  {
    try
    {
      Registry reg = LocateRegistry.createRegistry(1099);
      System.out.println("Registry started...");
    }
    catch (java.rmi.server.ExportException e)
    {
      System.out.println("Registry already started?" + " " + e.getMessage());
    }
  }

  public void close()
  {
    property.close();
    try
    {
      UnicastRemoteObject.unexportObject(this, true);
    }
    catch (Exception e)
    {
    }
  }

  @Override public void sendMessage(Message message) throws RemoteException
  {
    model.sendMessage(message);
    //property.firePropertyChange("add", null, message);
  }

  @Override public String getNumberOfChatters() throws RemoteException
  {
    return model.getNumberOfChatters();
  }

  @Override public void disconnect(Chatter chatter) throws RemoteException
  {
    model.disconnect(chatter);
  }

  @Override public MessageList getMessages() throws RemoteException
  {
    return model.getMessages();
  }

  @Override public void propertyChange(ObserverEvent<String, Message> event)
  {
      property.firePropertyChange(event.getPropertyName(), null, event.getValue2());
  }

  @Override public boolean addListener(
      GeneralListener<String, Message> listener, String... propertyNames)
      throws RemoteException
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, Message> listener, String... propertyNames)
      throws RemoteException
  {
    return property.removeListener(listener, propertyNames);
  }

  @Override public void connect(Chatter chatter) throws RemoteException
  {
    model.connect(chatter);
  }
}
