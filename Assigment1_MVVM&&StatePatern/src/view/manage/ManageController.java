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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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

  private int currentSliderValue;

  public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    vm = vmf.getManageVM();
    vm.controller = this;
    stateSlider.valueProperty().addListener(new ChangeListener<>()
    {
      @Override public void changed(
          ObservableValue<? extends Number> observableValue, Number number,
          Number t1)
      {
        int value = t1.intValue();
        if (currentSliderValue != value)
        {
          while (currentSliderValue < value)
          {
            vm.turnUp();
            currentSliderValue++;
          }
          while (currentSliderValue > value)
          {
            vm.turnDown();
            currentSliderValue--;
          }
          currentSliderValue = value;
        }
      }
    });
    t1Temp.textProperty().bind(vm.temperatureT1Property());
    t2Temp.textProperty().bind(vm.temperatureT2Property());
    t0Temp.textProperty().bind(vm.temperatureT0Property());
    lowOrHighLabel.textProperty()
        .bind(vm.lowOrHighLabelProperty());
    levelLabel.textProperty().bind(vm.levelLabelProperty());
  }
  public void openTemperatureLogs()
  {
    vh.openLogsView();
  }
}
