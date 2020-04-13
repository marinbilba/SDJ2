package mediator;

import model.Student;

import utility.observer.subject.RemoteSubject;
import java.rmi.RemoteException;

public interface RemoteModel extends RemoteSubject<Student, Student>
{
  public Student getStudentByStudyNumber(String studyNumber) throws
      RemoteException;
  public Student getStudentByName(String name) throws RemoteException;
  public void addStudent(Student student) throws RemoteException;
}
