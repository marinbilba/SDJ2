package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Student;
import model.StudentList;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;
import view.StudentTableRowData;

public class StudentListViewModel implements LocalListener<Student, Student>
{
  private Model model;
  private StringProperty result;
  private StringProperty name;
  private StringProperty number;
  private ObservableList<StudentTableRowData> students;

  public StudentListViewModel(Model model)
  {
    this.model = model;
    this.model.addListener(this, "add");

    result = new SimpleStringProperty();
    name = new SimpleStringProperty();
    number = new SimpleStringProperty();
    updateStudents();
  }

  @Override public void propertyChange(ObserverEvent<Student, Student> event)
  {
    Platform.runLater(() ->
         students.add(new StudentTableRowData(event.getValue2()))
    );
  }

  public void clear()
  {
    result.set(null);
    name.set(null);
    number.set(null);
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

  public StringProperty getNameProperty()
  {
    return name;
  }

  public StringProperty getNumberProperty()
  {
    return number;
  }

  public ObservableList<StudentTableRowData> getAllStudents()
  {
    return students;
  }

  private void updateStudents()
  {
    students = FXCollections.observableArrayList();
    StudentList list = model.getAllStudents();
    for (int i = 0; i < list.getSize(); i++)
    {
      students.add(new StudentTableRowData(list.getStudent(i)));
    }
  }



}
