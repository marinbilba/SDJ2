package model;

import utility.observer.subject.LocalSubject;

public interface  Model extends LocalSubject<Student, Student>
{
  public Student getStudentByStudyNumber(String studyNumber);
  public Student getStudentByName(String name);
  public void addStudent(Student student);
  public StudentList getAllStudents();
  public void close();
}
