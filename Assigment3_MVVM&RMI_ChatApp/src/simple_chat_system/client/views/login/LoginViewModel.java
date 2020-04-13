package simple_chat_system.client.views.login;

import simple_chat_system.client.model.MainModel;
import simple_chat_system.transferobjects.User;

public class LoginViewModel
{
  private MainModel mainModel;

  public LoginViewModel(MainModel mainModel)
  {
    this.mainModel = mainModel;
  }

  public void addUser(User username)
  {
    mainModel.addUser(username);
  }
}
