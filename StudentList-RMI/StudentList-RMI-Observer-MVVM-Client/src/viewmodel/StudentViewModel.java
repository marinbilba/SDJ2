package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.LocalModel;
import model.Student;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

import java.rmi.ConnectException;

public class StudentViewModel implements LocalListener<String, String>
{
  private LocalModel model;
  private StringProperty result;
  private StringProperty message;
  private StringProperty name;
  private StringProperty number;

  public StudentViewModel(LocalModel model)
  {
    this.model = model;
    model.addListener(this);
    result = new SimpleStringProperty();
    message = new SimpleStringProperty();
    name = new SimpleStringProperty();
    number = new SimpleStringProperty();
  }

  @Override public void propertyChange(ObserverEvent<String, String> event)
  {
    Platform.runLater(() -> message.set(event.getValue2()));
  }

  public void clear()
  {
    result.set(null);
    message.set(null);
    name.set(null);
    number.set(null);
  }

  public void searchByName()
  {
    String value = name.get();
    try
    {
      Student student = model.getStudentByName(value);
      clear();
      if (student != null)
      {
        result.set("Found: " + student);
      }
      else
      {
        result.set("No student with name " + value);
      }
    }
    catch (Exception e)
    {
      result.set(e.getMessage());
    }
  }

  public void searchByNumber()
  {
    String value = number.get();
    try
    {
      Student student = model.getStudentByStudyNumber(value);
      clear();
      if (student != null)
      {
        result.set("Found: " + student);
      }
      else
      {
        result.set("No student with number " + value);
      }
    }
    catch (Exception e)
    {
      result.set(e.getMessage());
    }
  }

  public void createStudent()
  {
    try
    {
      Student student = new Student(name.get(), number.get());
      clear();
      model.addStudent(student);
      result.set("Added: " + student);
    }
    catch (Exception e)
    {
      result.set(e.getMessage());
    }
  }

  public StringProperty getResultProperty()
  {
    return result;
  }

  public StringProperty getMessageProperty()
  {
    return message;
  }

  public StringProperty getNameProperty()
  {
    return name;
  }

  public StringProperty getNumberProperty()
  {
    return number;
  }

}
