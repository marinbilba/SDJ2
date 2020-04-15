package viewmodel;

import model.LocalModel;

public class ViewModelFactory
{
  private ChatViewModel chatViewModel;
  private SettingsViewModel settingsViewModel;

  public ViewModelFactory(LocalModel model)
  {
    this.chatViewModel = new ChatViewModel(model);
    this.settingsViewModel = new SettingsViewModel(model);
  }

  public ChatViewModel getChatViewModel()
  {
    return chatViewModel;
  }

  public SettingsViewModel getSettingsViewModel()
  {
    return settingsViewModel;
  }
}
