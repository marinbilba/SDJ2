package core;

import mediator.MainModel;
import mediator.MainModelImplementation;

public class ModelFactory
{
  private MainModel mainModel;

  public MainModel getMainModel()
  {
    if (mainModel == null)
      mainModel = new MainModelImplementation();
    return mainModel;
  }
}

