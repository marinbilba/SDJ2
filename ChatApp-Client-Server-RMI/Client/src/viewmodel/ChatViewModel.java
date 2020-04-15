package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.LocalModel;
import model.Message;
import model.MessageList;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;
import view.ChatTableRowData;

public class ChatViewModel implements LocalListener<String, Message>
{
  private LocalModel model;
  private StringProperty message;
  private StringProperty error;
  private ObservableList<ChatTableRowData> messages;

  public ChatViewModel(LocalModel model)
  {
    this.model = model;
    this.model.addListener(this, "add");
    this.message = new SimpleStringProperty();
    this.error = new SimpleStringProperty();
    updateMessages();
  }

  @Override public void propertyChange(ObserverEvent<String, Message> event)
  {
    if (event.getPropertyName().equals("add"))
    {
      Platform.runLater(
          () -> messages.add(new ChatTableRowData(event.getValue2())));
    }
  }

  public void clear()
  {
    message.set(null);
    error.set(null);
  }

  public void sendMessage()
  {
    try
    {
      Message m = new Message(message.get(), model.getChatter());
      clear();
      model.sendMessage(m);
    }
    catch (Exception e)
    {
      error.set(e.getMessage());
    }
  }

  public void disconnect()
  {
    try
    {
      model.disconnect(model.getChatter());
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
