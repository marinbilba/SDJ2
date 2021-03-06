package simple_chat_system.client;

import javafx.application.Application;
import javafx.stage.Stage;
import simple_chat_system.client.core.ClientFactory;
import simple_chat_system.client.core.ModelFactory;
import simple_chat_system.client.core.ViewModelFactory;
import simple_chat_system.client.core.ViewHandler;

public class RunApp extends Application
{
  @Override public void start(Stage stage) throws Exception
  {
    ClientFactory cf=new ClientFactory();
    ModelFactory mf=new ModelFactory(cf);
    ViewModelFactory vmf=new ViewModelFactory(mf);
    ViewHandler vh = new ViewHandler(vmf);
    vh.start();
  }
}
