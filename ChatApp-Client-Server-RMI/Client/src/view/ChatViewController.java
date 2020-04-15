package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewmodel.ChatViewModel;

public class ChatViewController
{
  @FXML private TextArea sendText;
  @FXML private TextArea textAreaChat;

  @FXML private Label errorLabel;

  private ViewHandler viewHandler;
  private ChatViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, ChatViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    sendText.textProperty()
        .bindBidirectional(viewModel.getMessageProperty());
    errorLabel.textProperty().bind(viewModel.getErrorProperty());


    //CHAT MESSAGES
//    StringProperty textChat = new SimpleStringProperty();
//    textChat.bind(viewModel.getMessageProperty());
//    textChat.addListener(new ChangeListener<String>()
//    {
//      @Override public void changed(
//          ObservableValue<? extends String> observableValue, String s,
//          String t1)
//      {
//        textAreaChat.appendText("\n" + t1);
//      }
//    });


//    messageColumn.setCellValueFactory(d -> d.getValue().getMessageProperty());
//    userColumn.setCellValueFactory(d -> d.getValue().getUserProperty());
//
//    messageTable.setItems(viewModel.getMessages());
  }

  public void reset()
  {
    viewModel.clear();
  }

  @FXML private void sendButtonPressed()
  {
    viewModel.sendMessage();
  }

  @FXML private void exitButtonPressed()
  {
    viewModel.disconnect();
    viewHandler.closeView();
  }

  @FXML private void settingsButtonPressed()
  {
    viewHandler.openView("settings");
  }

  public Region getRoot()
  {
    return root;
  }
}
