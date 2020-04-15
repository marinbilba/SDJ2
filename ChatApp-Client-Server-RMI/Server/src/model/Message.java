package model;

import java.io.Serializable;

public class Message implements Serializable
{
  private String message;
  private Chatter chatter;

  public Message(String message, Chatter chatter)
  {
    this.message = message;
    this.chatter = chatter;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }

  public void setChatter(Chatter chatter)
  {
    this.chatter = chatter;
  }

  public String getMessage()
  {
    return message;
  }

  public Chatter getChatter()
  {
    return chatter;
  }
}
