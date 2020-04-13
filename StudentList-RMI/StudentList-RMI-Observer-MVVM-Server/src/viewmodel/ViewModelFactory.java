package viewmodel;

import model.Model;

public class ViewModelFactory
{
  private StudentListViewModel studentViewModel;

  public ViewModelFactory(Model model)
  {
    studentViewModel = new StudentListViewModel(model);
  }

  public StudentListViewModel getStudentViewModel()
  {
    return studentViewModel;
  }
}
