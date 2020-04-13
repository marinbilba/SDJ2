package simple_chat_system.client.views.chat;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import simple_chat_system.client.core.ViewHandler;
import simple_chat_system.transferobjects.InviteAccept;
import simple_chat_system.transferobjects.User;
import simple_chat_system.transferobjects.UsersPM;
import simple_chat_system.transferobjects.messages.Message;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

public class ChatClientController
{
  public TextArea textArea;
  public TextField textField;
  public ListView<User> usersListFXML;
  public Label invitePmErrorLabel;
  public Label userDisplayedName;

  private ChatClientViewModel chatVM;
  private ViewHandler vh;
  private User user;
  private UsersPM usersPM;

  public void init(ChatClientViewModel chatVM, ViewHandler vh)
  {
    this.chatVM = chatVM;
    this.vh = vh;

    // ONLINE LIST
    usersListFXML.setItems(chatVM.getUsersList());
    usersListFXML.setCellFactory(lv -> new ListCell<User>()
    {
      @Override public void updateItem(User item, boolean empty)
      {
        super.updateItem(item, empty);
        if (empty)
        {
          setText(null);
        }
        else
        {
          String text = item.getUsername(); // get text from item
          setText(text);
        }
      }
    });

    //CHAT MESSAGES
    StringProperty textChat = new SimpleStringProperty();
    textChat.bind(chatVM.messageProperty());
    textChat.addListener(new ChangeListener<String>()
    {
      @Override public void changed(
          ObservableValue<? extends String> observableValue, String s,
          String t1)
      {
        textArea.appendText("\n" + t1);
      }
    });
    chatVM.addListener("SendInvite", this::openPrivateChat);

    user = chatVM.getCurrentUser();
    userDisplayedName.setText("You nickname is: '" + user.getUsername()+"'");
  }

  private void openPrivateChat(PropertyChangeEvent propertyChangeEvent)
  {
    usersPM = ((UsersPM) propertyChangeEvent.getNewValue());
    JPanel panel = new JPanel();

    Object[] options = {"Yes", "No"};

    int selected = JOptionPane.showOptionDialog(panel,
        "Hey, " + user + " user '" + usersPM.getSender()
            + "' invites you to a private channel", "Invite",
        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
        options, options[0]);

    if (selected == JOptionPane.NO_OPTION)
    {
    }
    else if (selected == JOptionPane.YES_OPTION)
    {

        openPrivateChatForSender(usersPM.getSender());
        Platform.runLater(() -> vh.openPrivateChat(usersPM.getReceiver()));
        chatVM.sendListOfPmRoomUsers(usersPM);
    }
  }

  private void openPrivateChatForSender(User user)
  {
    Platform.runLater(() -> vh.openPrivateChat2(user));
  }

  public void sendButton()
  {
    String message = textField.getText();
    chatVM.sendMessageToEveryone(new Message(message));
    textField.clear();
  }

  public void inviteToPmButton()
  {
    if(usersListFXML.getSelectionModel().getSelectedItems().isEmpty()){
     invitePmErrorLabel.setText("Select a user from the list");
    }else
    {
      User user = (User) usersListFXML.getSelectionModel().getSelectedItems().get(0);
      if (!user.getUsername().equals(this.user.getUsername()))
      {
        chatVM.sentInviteToPM(user);
    }else {
        invitePmErrorLabel.setText("Do you really want to talk to yourself?");
      }
    }
    }
}
