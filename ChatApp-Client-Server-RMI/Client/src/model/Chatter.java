package model;

import java.io.Serializable;

public class Chatter implements Serializable
{
  private String tag;
  private boolean isOnline;

  public Chatter(String tag)
  {
    this.tag = tag;
    isOnline = false;
  }

  public void setTag(String tag)
  {
    this.tag = tag;
  }

  public void setOnline(boolean online)
  {
    isOnline = online;
  }

  public String getTag()
  {
    return tag;
  }

  public boolean isOnline()
  {
    return isOnline;
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Chatter))
      return false;
    Chatter other = (Chatter) obj;
    return tag.equals(other.tag) && isOnline == other.isOnline;
  }
}
