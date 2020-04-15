package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.LocalModel;

public class SettingsViewModel
{
  private LocalModel model;
  private StringProperty tag;
  private StringProperty error;
  private StringProperty number;

  public SettingsViewModel(LocalModel model)
  {
    this.model = model;
    tag = new SimpleStringProperty();
    error = new SimpleStringProperty();
    number = new SimpleStringProperty();
    //tag.set(model.getChatter().getTag());
    //number.set(model.getNumberOfChatters());
  }

  public void clear()
  {
    number.set(null);
    error.set(null);
    tag.set(null);
  }

  public void getConnectedNumber()
  {
    try
    {
      number.set(model.getNumberOfChatters());
    }
    catch (Exception e)
    {
      error.set(e.getMessage());
    }
  }

  public void setTag()
  {
    try
    {
      if (!tag.get().equals(""))
      {
        model.disconnect(model.getChatter());
        model.getChatter().setTag(tag.get());
        model.connect(model.getChatter());
        error.set("");
      }
      else
        error.set("Empty field!");
    }
    catch (Exception e)
    {
      error.set(e.getMessage());
    }
  }

  public StringProperty getTagProperty()
  {
    return tag;
  }

  public StringProperty getErrorProperty()
  {
    return error;
  }

  public StringProperty getNumberProperty()
  {
    return number;
  }

}
