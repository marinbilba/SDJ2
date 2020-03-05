package core;

import view.logs.LogsVM;
import view.manage.ManageVM;

public class ViewModelFactory
{
  private ModelFactory mf;

  private ManageVM manageVM;
  private LogsVM logsVM;

  public ViewModelFactory(ModelFactory mf)
  {
    logsVM=new LogsVM(mf.getMainModel());
    this.mf = mf;
  }

  public ManageVM getManageVM()
  {
    if(manageVM==null)
      manageVM=new ManageVM(mf.getMainModel());
    return manageVM;
  }

  public LogsVM getLogsVM()
  {
//    if(logsVM==null)

    return logsVM;
  }
}
