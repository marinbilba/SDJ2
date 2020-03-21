package simple_chat_system.client.views.login;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import simple_chat_system.client.core.ViewHandler;
import simple_chat_system.transferobjects.User;

public class LoginController
{
  public TextField textField;
  public Label usernameErrorLabel;

  private LoginViewModel loginVM;
  private ViewHandler vh;

  public void init(LoginViewModel loginVM, ViewHandler vh)
  {
    this.loginVM = loginVM;
    this.vh = vh;
  }

  public void enterChatBtn()
  {
    if (textField.getText().length()>=4)
    {
      String username = textField.getText();
      loginVM.addUser(new User(username));
      vh.openChatClientView();
    }
    else
    {
      usernameErrorLabel.setText("Username too short(min 4 char)");
    }
  }
}
