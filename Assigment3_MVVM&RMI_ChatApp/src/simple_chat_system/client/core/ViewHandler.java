package simple_chat_system.client.core;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import simple_chat_system.client.views.chat.ChatClientController;
import simple_chat_system.client.views.login.LoginController;
import simple_chat_system.client.views.private_chat.PrivateChatController;
import simple_chat_system.transferobjects.User;
import simple_chat_system.transferobjects.UsersPM;

import java.io.IOException;

public class ViewHandler
{
  private ViewModelFactory vmf;
  private Stage stage;
  private Scene chat;
  private Scene login;
  private Scene chatPM;
  private Scene chatPM2;

  public ViewHandler(ViewModelFactory vmf)
  {
    stage = new Stage();
    this.vmf = vmf;
  }

  public void start()
  {
    openLogin();
  }

  public void openChatClientView()
  {
    FXMLLoader loader = new FXMLLoader();
    if (chat == null)
    {
      Parent root = getRootByPath("../views/chat/ChatClient.fxml", loader);
      ChatClientController controller = loader.getController();
      controller.init(vmf.getChatVM(), this);
      chat = new Scene(root);
    }
    stage.setTitle("Chat");
    stage.setScene(chat);
    stage.show();
  }

  private void openLogin()
  {
    FXMLLoader loader = new FXMLLoader();
    if (login == null)
    {
      Parent root = getRootByPath("../views/login/login.fxml", loader);
      LoginController controller = loader.getController();
      controller.init(vmf.getLoginVM(), this);
      login = new Scene(root);
    }
    stage.setTitle("Login");
    stage.setScene(login);
    stage.show();
  }

  public void openPrivateChat(User user)
  {
    Stage stage2 = new Stage();
    FXMLLoader loader = new FXMLLoader();
    if (chatPM == null)
    {
      Parent root = getRootByPath("../views/private_chat/PrivateChat.fxml",
          loader);
      PrivateChatController controller = loader.getController();
      controller.init(vmf.getPrivateChatVM(), user);
      chatPM = new Scene(root);
    }
    stage2.setTitle("PM");
    stage2.setScene(chatPM);
    stage2.show();
  }
  public void openPrivateChat2(User user)
  {
    Stage stage3 = new Stage();
    FXMLLoader loader = new FXMLLoader();
    if (chatPM2 == null)
    {
      Parent root = getRootByPath("../views/private_chat/PrivateChat.fxml",
          loader);
      PrivateChatController controller = loader.getController();
      controller.init(vmf.getPrivateChatVM(), user);
      chatPM2 = new Scene(root);
    }
    stage3.setTitle("PM");
    stage3.setScene(chatPM2);
    stage3.show();
  }
  private Parent getRootByPath(String path, FXMLLoader loader)
  {
    loader.setLocation(getClass().getResource(path));
    Parent root = null;
    try
    {
      root = loader.load();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return root;
  }
}
