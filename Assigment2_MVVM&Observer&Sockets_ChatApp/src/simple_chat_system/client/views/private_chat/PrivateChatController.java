package simple_chat_system.client.views.private_chat;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import simple_chat_system.client.core.ViewHandler;
import simple_chat_system.transferobjects.messages.PrivateMessage;
import simple_chat_system.transferobjects.User;
import simple_chat_system.transferobjects.UsersPM;

public class PrivateChatController
{
  public ListView<User> usersListFXML;
  public Label invitePmErrorLabel;
  public TextArea textArea;
  public TextField textField;
  public Label userDisplayedName;

  private User user;
private PrivateChatViewModel pmVM;
 private SimpleStringProperty textChat;

public void init(PrivateChatViewModel privateChatVM, User user)
  {
    this.user = user;
    this.pmVM =privateChatVM;
    usersListFXML.setItems(pmVM.getUsers());
    textChat = new SimpleStringProperty();
    textChat.bind(pmVM.messageProperty());
    textChat.addListener(new ChangeListener<String>()
    {
      @Override public void changed(
          ObservableValue<? extends String> observableValue, String s,
          String t1)
      {
        textArea.appendText("\n" + t1);
      }
    });
    userDisplayedName.setText("You nickname is: '" + user.getUsername()+"'");
  }


  public void sendButton()
  {
    String message = textField.getText();
    pmVM.sendMessageInPM(new PrivateMessage(user,message));
    textField.clear();
  }
}
