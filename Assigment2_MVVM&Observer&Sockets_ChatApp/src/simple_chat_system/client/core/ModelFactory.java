package simple_chat_system.client.core;

import simple_chat_system.client.model.MainModel;
import simple_chat_system.client.model.MainModelManager;

public class ModelFactory
{
  private ClientFactory cf;
  private MainModel mainModel;

  public ModelFactory(ClientFactory cf)
  {
    this.cf = cf;
  }

  public MainModel getMainModel()
  {
    if(mainModel == null)
      mainModel = new MainModelManager(cf.getClient());
    return mainModel;
  }
}
