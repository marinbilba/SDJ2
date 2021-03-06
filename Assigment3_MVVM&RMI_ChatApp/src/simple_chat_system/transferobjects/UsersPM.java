package simple_chat_system.transferobjects;

import simple_chat_system.transferobjects.User;

import java.io.Serializable;

public class UsersPM implements Serializable
{
  private User sender;
  private User receiver;

  public UsersPM(User sender, User receiver)
  {
    this.sender = sender;
    this.receiver = receiver;
  }

  public User getSender()
  {
    return sender;
  }

  public User getReceiver()
  {
    return receiver;
  }
}
