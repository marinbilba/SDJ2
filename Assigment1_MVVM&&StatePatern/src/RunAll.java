import core.ModelFactory;
import core.ViewHandler;
import core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Thermometer;

public class RunAll extends Application
{
  @Override public void start(Stage stage) throws Exception
  {
    ModelFactory mf = new ModelFactory();
    ViewModelFactory vmf = new ViewModelFactory(mf);
    ViewHandler vh = new ViewHandler(vmf);
    vh.start();
//    Thermometer thermometer0=new Thermometer(mf.getMainModel(),"T0",15,-10,25);
    Thermometer thermometer=new Thermometer(mf.getMainModel(),"T1",15,1);
    Thermometer thermometer2=new Thermometer(mf.getMainModel(),"T2",15,7);


    Thread thread=new Thread(thermometer);
 Thread thread2=new Thread(thermometer2);

    thread.start();
    thread2.start();


    vh.start();
  }
}
