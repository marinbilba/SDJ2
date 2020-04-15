package model;

import java.io.Serializable;
import java.util.ArrayList;

public class ChatterBase implements Serializable
{
  private ArrayList<Chatter> chatters;

  public ChatterBase()
  {
    this.chatters = new ArrayList<>();
  }

  public void addChatter(Chatter chatter)
  {
    //chatter.setOnline(true);
    if (!chatters.contains(chatter))
    {
      chatters.add(chatter);
    }
  }

  public String getNumberOfChatters()
  {
    int counter = 0;
    for (int i = 0; i < chatters.size(); i++)
    {
      //chatters.get(i).isOnline() &&
      if (!chatters.get(i).getTag()
          .equals("SERVER"))
        counter++;
    }
    return Integer.toString(counter);
  }

  public void disconnectChatter(Chatter chatter)
  {
    //for (int i = 0; i < chatters.size(); i++)
    //{
    // if (chatters.get(i).equals(chatter))
    //  chatters.get(i).setOnline(false);
    //}
    chatters.remove(chatter);
  }
}
