package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Message;

public class ChatTableRowData
{
  private StringProperty message;
  private StringProperty user;

  public ChatTableRowData(Message message)
  {
    String qMessage = null;
    String qUser = null;
    if (message != null)
    {
      qMessage = message.getMessage();
      qUser = message.getChatter().getTag();
    }
    this.message = new SimpleStringProperty(qMessage);
    this.user = new SimpleStringProperty(qUser);
  }

  public StringProperty getMessageProperty()
  {
    return message;
  }

  public StringProperty getUserProperty()
  {
    return user;
  }
}
