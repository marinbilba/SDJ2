package viewmodel;

import model.Model;

public class ViewModelFactory
{
  private ChatViewModel chatViewModel;

  public ViewModelFactory(Model model)
  {
    chatViewModel = new ChatViewModel(model);
  }

  public ChatViewModel getChatViewModel()
  {
    return chatViewModel;
  }
}
