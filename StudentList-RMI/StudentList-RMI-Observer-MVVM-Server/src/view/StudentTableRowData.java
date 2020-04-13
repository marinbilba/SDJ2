package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Student;

public class StudentTableRowData
{
  private StringProperty number;
  private StringProperty name;

  public StudentTableRowData(Student student)
  {
    String studentName = null;
    String studentNumber = null;
    if (student != null)
    {
      studentName = student.getName();
      studentNumber = student.getStudyNumber();
    }
    number = new SimpleStringProperty(studentNumber);
    name = new SimpleStringProperty(studentName);
  }

 public StringProperty getNumberProperty()
  {
    return number;
  }

  public StringProperty getNameProperty()
  {
    return name;
  }
}
