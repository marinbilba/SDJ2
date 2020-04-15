package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ChatViewModel;

public class ChatViewController
{
  @FXML private TextField messageField;
  @FXML private Label errorLabel;
  @FXML private Label connectedLabel;
  @FXML TableView<ChatTableRowData> messageTable;
  @FXML TableColumn<ChatTableRowData, String> messageColumn;
  @FXML TableColumn<ChatTableRowData, String> userColumn;
  private ViewHandler viewHandler;
  private ChatViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, ChatViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    messageField.textProperty()
        .bindBidirectional(viewModel.getMessageProperty());
    errorLabel.textProperty().bind(viewModel.getErrorProperty());
    connectedLabel.textProperty().bind(viewModel.getNumberProperty());

    messageColumn.setCellValueFactory(d -> d.getValue().getMessageProperty());
    userColumn.setCellValueFactory(d -> d.getValue().getUserProperty());

    messageTable.setItems(viewModel.getMessages());
  }

  public void reset()
  {
    viewModel.clear();
  }

  @FXML private void sendButtonPressed()
  {
    viewModel.sendMessage();
  }

  public Region getRoot()
  {
    return root;
  }
}
