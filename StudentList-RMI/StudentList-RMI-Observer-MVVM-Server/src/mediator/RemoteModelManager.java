package mediator;

import model.Model;
import model.Student;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RemoteModelManager
    implements RemoteModel, LocalListener<Student, Student>
{
  private Model model;
  private PropertyChangeAction<Student, Student> property;

  public RemoteModelManager(Model model)
      throws RemoteException, MalformedURLException
  {
    this.property = new PropertyChangeProxy<>(this, true);
    this.model = model;
    this.model.addListener(this, "add");
    startRegistry();
    startServer();
  }

  private void startServer() throws RemoteException, MalformedURLException
  {
    UnicastRemoteObject.exportObject(this, 0);
    Naming.rebind("StudentList", this);
    System.out.println("Server started...");
  }

  public void close()
  {
    property.close();
    try { UnicastRemoteObject.unexportObject(this, true); }
    catch (Exception e) {/*nothing*/ }
  }

  @Override public Student getStudentByStudyNumber(String studyNumber)
  {
    return model.getStudentByStudyNumber(studyNumber);
  }

  @Override public Student getStudentByName(String name)
  {
    return model.getStudentByName(name);
  }

  @Override public void addStudent(Student student)
  {
    model.addStudent(student);
    property.firePropertyChange("add", null, student);
  }

  @Override public boolean addListener(
      GeneralListener<Student, Student> listener, String... propertyNames)
  {
    boolean ok = property.addListener(listener, propertyNames);
    return ok;
  }

  @Override public boolean removeListener(
      GeneralListener<Student, Student> listener, String... propertyNames)
  {
    return property.removeListener(listener, propertyNames);
  }

  @Override public void propertyChange(ObserverEvent<Student, Student> event)
  {
    property.firePropertyChange(event.getPropertyName(),
                                null, event.getValue2());
  }

  private void startRegistry() throws RemoteException
  {
    try
    {
      Registry reg = LocateRegistry.createRegistry(1099);
      System.out.println("Registry started...");
    }
    catch (java.rmi.server.ExportException e)
    {
      System.out.println("Registry already started?" + " " + e.getMessage());
    }
  }

}





