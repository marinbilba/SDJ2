package viewmodel;

import model.LocalModel;

public class ViewModelFactory
{
  private StudentViewModel studentViewModel;

  public ViewModelFactory(LocalModel model)
  {
    studentViewModel = new StudentViewModel(model);
  }

  public StudentViewModel getStudentViewModel()
  {
    return studentViewModel;
  }
}
