package view.manage;

import core.ViewHandler;
import core.ViewModelFactory;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.Slider;

public class ManageController
{
  public Label t1Temp;
  public Label t2Temp;
  public Label t0Temp;

  public Slider stateSlider;

  public Menu powerScene;
  public Menu temperatureScene;
  public Label lowOrHighLabel;
  public Label levelLabel;

  private ManageVM vm;
  private ViewHandler vh;

  private DoubleProperty statePower;
  private int currentSliderValue;

  public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    vm = vmf.getManageVM();
    statePower = new SimpleDoubleProperty();
    statePower.bindBidirectional(vm.radiatorStateProperty());

    stateSlider.valueProperty().addListener(new ChangeListener<Number>()
    {
      @Override public void changed(
          ObservableValue<? extends Number> observableValue, Number number,
          Number t1)
      {
        int value = (int) t1.intValue();
        if (currentSliderValue != value)
        {
          if (currentSliderValue < value)
          {
            vm.turnUp();
            stateSlider.focusedProperty();
            stateSlider.setValue(statePower.getValue());
            levelLabel
                .setText(String.valueOf(statePower.getValue().intValue()));
          }
          else
          {
            vm.turnDown();
            levelLabel
                .setText(String.valueOf(statePower.getValue().intValue()));
          }
          currentSliderValue = value;
        }
      }
    });
    t1Temp.textProperty().bindBidirectional(vm.temperatureT1Property());
    t2Temp.textProperty().bindBidirectional(vm.temperatureT2Property());
    t0Temp.textProperty().bindBidirectional(vm.temperatureT0Property());
    lowOrHighLabel.textProperty()
        .bindBidirectional(vm.lowOrHighLabelProperty());
  }

  public void openTemperatureLogs()
  {
    vh.openLogsView();
  }
}
