package simple_chat_system.client.core;

import simple_chat_system.client.views.chat.ChatClientViewModel;
import simple_chat_system.client.views.login.LoginViewModel;
import simple_chat_system.client.views.private_chat.PrivateChatViewModel;

public class ViewModelFactory
{
  private ModelFactory mf;
  private ChatClientViewModel chatVM;
  private LoginViewModel loginVM;
  private PrivateChatViewModel privateChatVM;

  public ViewModelFactory(ModelFactory mf)
  {
    this.mf = mf;
    chatVM = new ChatClientViewModel(mf.getMainModel());
    loginVM = new LoginViewModel(mf.getMainModel());
    privateChatVM = new PrivateChatViewModel(mf.getMainModel());

  }

  public ChatClientViewModel getChatVM()
  {
    return chatVM;
  }

  public LoginViewModel getLoginVM()
  {

    return loginVM;
  }
  public PrivateChatViewModel getPrivateChatVM()
  {

    return privateChatVM;
  }
}
