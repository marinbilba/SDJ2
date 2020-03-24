package simple_chat_system.transferobjects.messages;

import simple_chat_system.transferobjects.User;

import java.io.Serializable;

public class PublicMessage implements Serializable
{
  private User username;
  private Message message;

  public PublicMessage(User username, Message message)
  {
    this.username = username;
    this.message = message;
  }

  public User message()
  {
    return username;
  }

  public Message getMessage()
  {
    return message;
  }

  public String getUsername()
  {
    return username.getUsername();
  }

  public String getMessageString()
  {
    return message.getMsg();
  }
}
