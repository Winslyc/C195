package Helper;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class TimeUtil {


 public static LocalTime stringtToTime(String time){
  DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
  LocalTime newLocalTime = LocalTime.parse(time, dateTimeFormatter);
  return newLocalTime;
 }

    public static String dateTimetoTimestampUTC(LocalDateTime dateTime) {{
            Timestamp currentTimeStamp = Timestamp.valueOf(String.valueOf(dateTime));
            LocalDateTime LocalDT = currentTimeStamp.toLocalDateTime();
            ZonedDateTime zoneDT = LocalDT.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
            ZonedDateTime utcDT = zoneDT.withZoneSameInstant(ZoneId.of("UTC"));
            LocalDateTime localOUT = utcDT.toLocalDateTime();
            String utcOUT = localOUT.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return utcOUT;
        }   }
}
