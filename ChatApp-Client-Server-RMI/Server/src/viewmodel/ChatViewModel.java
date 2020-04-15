package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Chatter;
import model.Message;
import model.MessageList;
import model.Model;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;
import view.ChatTableRowData;

public class ChatViewModel implements LocalListener<String, Message>
{
  private Model model;
  private StringProperty message;
  private StringProperty error;
  private StringProperty number;
  private ObservableList<ChatTableRowData> messages;

  public ChatViewModel(Model model)
  {
    this.model = model;
    this.model.addListener(this, "add", "number");
    message = new SimpleStringProperty();
    error = new SimpleStringProperty();
    number = new SimpleStringProperty();
    updateMessages();
    number.set("0");
  }

  @Override public void propertyChange(ObserverEvent<String, Message> event)
  {
    if (event.getPropertyName().equals("add"))
    {
      Platform.runLater(
          () -> messages.add(new ChatTableRowData(event.getValue2())));
    }
    if (event.getPropertyName().equals("number"))
    {
      Platform.runLater(() -> number.set(event.getValue1()));
    }
  }

  public void clear()
  {
    number.set(null);
    error.set(null);
    message.set(null);
  }

  public void sendMessage()
  {
    try
    {
      Message m = new Message(message.get(), new Chatter("SERVER"));
      clear();
      model.sendMessage(m);
    }
    catch (Exception e)
    {
      error.set(e.getMessage());
    }
  }

  public StringProperty getMessageProperty()
  {
    return message;
  }

  public StringProperty getErrorProperty()
  {
    return error;
  }

  public StringProperty getNumberProperty()
  {
    return number;
  }

  public ObservableList<ChatTableRowData> getMessages()
  {
    return messages;
  }

  public void updateMessages()
  {
    messages = FXCollections.observableArrayList();
    MessageList list = model.getMessages();
    for (int i = 0; i < list.getSize(); i++)
    {
      messages.add(new ChatTableRowData(list.getMessage(i)));
    }
  }
}
