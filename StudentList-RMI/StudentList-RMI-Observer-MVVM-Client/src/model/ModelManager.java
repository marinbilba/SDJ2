package model;

import mediator.ClientModel;
import mediator.StudentListClient;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

public class ModelManager implements LocalModel, LocalListener<Student, Student>
{
  private ClientModel serverModel;
  private PropertyChangeAction<String, String> property;

  public ModelManager()
  {
    try
    {
      this.serverModel = new StudentListClient(this);
      this.serverModel.addListener(this);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    property = new PropertyChangeProxy<>(this, true);
  }

  @Override public Student getStudentByStudyNumber(String studyNumber)
  {
    return serverModel.getStudentByStudyNumber(studyNumber);
  }

  @Override public Student getStudentByName(String name)
  {
    return serverModel.getStudentByName(name);
  }

  @Override public void addStudent(Student student)
  {
    serverModel.addStudent(student);
  }

  @Override public void close()
  {
    try
    {
      property.close();
      serverModel.close();
    }
    catch (Exception e) {e.printStackTrace();}
  }

  @Override public boolean addListener(
      GeneralListener<String, String> listener, String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
  {
    return property.removeListener(listener, propertyNames);
  }

  @Override public void propertyChange(ObserverEvent<Student, Student> event)
  {
    String message = "Message: Added " + event.getValue2();
    property.firePropertyChange("broadcast", null, message);
  }
}