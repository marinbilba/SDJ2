package mediator;

import model.LocalModel;
import model.Student;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.RemoteListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StudentListClient
    implements ClientModel, RemoteListener<Student, Student>
{
  public static final String HOST = "localhost";
  private String host;
  private RemoteModel remoteModel;
  private LocalModel model;
  private PropertyChangeAction<Student, Student> property;

  public StudentListClient(LocalModel model, String host)
      throws RemoteException, NotBoundException, MalformedURLException
  {
    this.model = model;
    this.host = host;
    this.remoteModel = (RemoteModel) Naming
        .lookup("rmi://" + host + ":1099/StudentList");
    UnicastRemoteObject.exportObject(this, 0);
    this.remoteModel.addListener(this);
    this.property = new PropertyChangeProxy<>(this, true);
  }

  public StudentListClient(LocalModel model)
      throws RemoteException, NotBoundException, MalformedURLException
  {
    this(model, HOST);
  }



  @Override public Student getStudentByStudyNumber(String studyNumber)
  {
    try
    {
      return remoteModel.getStudentByStudyNumber(studyNumber);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public Student getStudentByName(String name)
  {
    try
    {
      return remoteModel.getStudentByName(name);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public void addStudent(Student student)
  {
    try
    {
      remoteModel.addStudent(student);
    }
    catch (RemoteException e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public void close()
  {
    try {
      UnicastRemoteObject.unexportObject(this, true);
    }
    catch (Exception e) {
      throw new IllegalStateException("Cannot unexport RMI object", e);
    }
  }

  @Override public synchronized void propertyChange(ObserverEvent<Student, Student> event)
  {
    property.firePropertyChange(event);
  }

  @Override public boolean addListener(
      GeneralListener<Student, Student> listener, String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<Student, Student> listener, String... propertyNames)
  {
    return property.removeListener(listener, propertyNames);
  }

  private String getExceptionMessage(Exception e)
  {
    String message = e.getMessage();
    if (message != null)
      message = message.split(";")[0];
    return message;
  }
}
