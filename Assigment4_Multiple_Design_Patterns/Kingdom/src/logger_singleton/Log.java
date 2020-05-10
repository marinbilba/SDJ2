package logger_singleton;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Log
{
  private static Log INSTANCE;
  private LogLine logLine;
  private static final Lock lock = new ReentrantLock();

  private BufferedWriter writer;
  private File file;

  private Log()
  {
    file = new File("Logs.txt");
  }

  public static Log getLogEntry()
  {
    if (INSTANCE == null)
    {
      synchronized (lock)
      {
        if (INSTANCE == null)
          INSTANCE = new Log();
      }
    }
    return INSTANCE;
  }

  public synchronized void add(String log)
  {
    if (log == null || "".equals(log))
      return;
    this.logLine = new LogLine(log);
    try
    {

      writer = new BufferedWriter(new FileWriter(file, true));
      writer.append(logLine.toString());
      writer.newLine();
      writer.flush();
      writer.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

  }
}
