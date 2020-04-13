package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.StudentListViewModel;

public class StudentListViewController
{
  @FXML private TextField numberField;
  @FXML private TextField nameField;
  @FXML private Label resultLabel;
  @FXML private TableView<StudentTableRowData> studentTable;
  @FXML private TableColumn<StudentTableRowData, String> numberColumn;
  @FXML private TableColumn<StudentTableRowData, String> nameColumn;

  private ViewHandler viewHandler;
  private StudentListViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, StudentListViewModel studentViewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = studentViewModel;
    this.root = root;

    nameField.textProperty().bindBidirectional(viewModel.getNameProperty());
    numberField.textProperty().bindBidirectional(viewModel.getNumberProperty());
    resultLabel.textProperty().bind(viewModel.getResultProperty());

    numberColumn.setCellValueFactory(d -> d.getValue().getNumberProperty());
    nameColumn.setCellValueFactory(d -> d.getValue().getNameProperty());

    studentTable.setItems(viewModel.getAllStudents());
  }

  public void reset()
  {
    viewModel.clear();
  }

  @FXML private void createStudent()
  {
    viewModel.createStudent();
    numberField.requestFocus();
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private  void textFieldAction()
  {
    nameField.requestFocus();
  }
}
