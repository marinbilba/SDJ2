package model;

import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

public class ModelManager implements Model
{
  private StudentList studentList;
  private PropertyChangeAction<Student, Student> property;

  public ModelManager()
  {
    this.studentList = new StudentList();
    this.property = new PropertyChangeProxy<>(this);
  }

  @Override public void addStudent(Student student)
      throws IllegalArgumentException
  {
    studentList.addStudent(student);
    property.firePropertyChange("add", null, student);
  }

  @Override public Student getStudentByStudyNumber(String studyNumber)
      throws IllegalArgumentException
  {
    return studentList.getStudentByNumber(studyNumber);
  }


  @Override public Student getStudentByName(String name)
      throws IllegalArgumentException
  {
    return studentList.getStudentByName(name);
  }

  @Override public StudentList getAllStudents()
  {
    return studentList;
  }

  @Override public void close()
  {
    property.close();
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
}




