package Helper;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {


 public static LocalTime stringtToTime(String time){
  DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
  LocalTime newLocalTime = LocalTime.parse(time, dateTimeFormatter);
  return newLocalTime;
 }

 }
