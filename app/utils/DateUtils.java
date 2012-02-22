package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Period;

public class DateUtils {
  /**
   * 计算两个日期相隔的小时数.
   * @param before
   * @param after
   * @return
   */
  public static long getDiffHours(Date before,Date after){
    DateTime beforeTime = new DateTime(before.getTime());
    DateTime afterTime = new DateTime(after.getTime());
    Period p = new Period(beforeTime, afterTime);
    return p.getHours();
  }
  /**
   * 计算两个日期相隔的分钟数.
   * @param before
   * @param after
   * @return
   */
  public static long getDiffMinutes(Date before,Date after){
    DateTime beforeTime = new DateTime(before.getTime());
    DateTime afterTime = new DateTime(after.getTime());
    Period p = new Period(beforeTime, afterTime);
    return p.getMinutes();
  }
  public static void main(String[] args) {
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date a;
    try {
      a = format.parse("2011-02-22 23:10:10");
      Date b = new Date();
      System.out.println(DateUtils.getDiffMinutes(a, b));
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }
}
