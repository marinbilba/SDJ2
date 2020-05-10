package logger_singleton;

public class LogLine
{
  private String text;
    private DateTime dateTime;

  public LogLine(String text)
    {
      this.text = text;
      this.dateTime = new DateTime();
    }

    public String toString()
    {
      return dateTime.getTimestamp() + " " + text;
    }

}

