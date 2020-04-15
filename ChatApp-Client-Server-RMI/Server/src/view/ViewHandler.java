package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;
  private ViewModelFactory viewModelFactory;
  private ChatViewController chatViewController;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    this.currentScene = new Scene(new Region());
    openView("chat");
  }

  public void openView(String id)
  {
    Region root = null;
    switch (id)
    {
      case "chat":
        root = loadChatView("ChatView.fxml");
        break;
    }
    currentScene.setRoot(root);

    String title = "";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }

  private Region loadChatView(String fxmlFile)
  {
    if (chatViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        chatViewController = loader.getController();
        chatViewController
            .init(this, viewModelFactory.getChatViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      chatViewController.reset();
    }
    return chatViewController.getRoot();
  }
}
