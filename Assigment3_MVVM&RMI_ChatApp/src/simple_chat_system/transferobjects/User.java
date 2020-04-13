package simple_chat_system.transferobjects;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable
{
  private String username;

  public User(String username)
  {
    this.username = username;
  }

  public String getUsername()
  {
    return username;
  }

  public boolean equals(Object obj){
    if(!(obj instanceof User))
      return false;
    User other=(User) obj;
    return username.equals(other.username);
  }

  @Override public String toString()
  {
    return  username;
  }
}
