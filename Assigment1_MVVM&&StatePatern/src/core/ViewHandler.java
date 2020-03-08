package core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.logs.LogsController;
import view.manage.ManageController;

import java.io.IOException;

public class ViewHandler
{
  private ViewModelFactory vmf;
  private Stage stage;
  private Scene manageScene;
  private Scene logsScene;

  public ViewHandler(ViewModelFactory vmf)
  {
    this.vmf = vmf;
    stage=new Stage();
  }
  public void start()
  {
   openManageView();
    stage.show();
  }
  public void openManageView(){
    FXMLLoader loader=new FXMLLoader();
    if(manageScene==null){
      Parent root = getRootByPath("../view/manage/Manage.fxml",loader);
      ManageController controller=loader.getController();
      controller.init(this, vmf);
      manageScene=new Scene(root);
    }
    stage.setTitle("Manage");
    stage.setScene(manageScene);
  }

  public void openLogsView(){
    FXMLLoader loader=new FXMLLoader();
    if(logsScene==null){
      Parent root = getRootByPath("../view/logs/Logs.fxml",loader);
      LogsController controller=loader.getController();
      controller.init(this, vmf);
      logsScene=new Scene(root);
    }
    stage.setTitle("Logs");
    stage.setScene(logsScene);
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
