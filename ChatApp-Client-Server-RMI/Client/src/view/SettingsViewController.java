package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.SettingsViewModel;

public class SettingsViewController
{
  @FXML private TextField tagField;
  @FXML private Label numberLabel;
  @FXML private Label errorLabel;
  private ViewHandler viewHandler;
  private SettingsViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, SettingsViewModel settingsViewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = settingsViewModel;
    this.root = root;

    tagField.textProperty().bindBidirectional(viewModel.getTagProperty());
    numberLabel.textProperty().bind(viewModel.getNumberProperty());
    errorLabel.textProperty().bind(viewModel.getErrorProperty());
  }

  public void reset()
  {
    viewModel.clear();
  }

  @FXML private void applyButtonPressed()
  {
    viewModel.setTag();
  }

  @FXML private void refreshButtonPressed()
  {
    viewModel.getConnectedNumber();
  }

  @FXML private void backButtonPressed()
  {
    viewHandler.openView("chat");
  }

  public Region getRoot()
  {
    return root;
  }
}
