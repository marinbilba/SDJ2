package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.StudentViewModel;

public class StudentViewController
{
  @FXML private TextField numberField;
  @FXML private TextField nameField;
  @FXML private Label resultLabel;
  @FXML private Label messageLabel;

  private ViewHandler viewHandler;
  private StudentViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, StudentViewModel studentViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = studentViewModel;
    this.root = root;

    nameField.textProperty().bindBidirectional(viewModel.getNameProperty());
    numberField.textProperty().bindBidirectional(viewModel.getNumberProperty());
    resultLabel.textProperty().bind(viewModel.getResultProperty());
    messageLabel.textProperty().bind(viewModel.getMessageProperty());
  }

  public void reset()
  {
    viewModel.clear();
  }

  @FXML private void searchByNumber()
  {
    viewModel.searchByNumber();
  }

  @FXML private void searchByName()
  {
    viewModel.searchByName();
  }

  @FXML private void createStudent()
  {
    viewModel.createStudent();
  }

  public Region getRoot()
  {
    return root;
  }
}
