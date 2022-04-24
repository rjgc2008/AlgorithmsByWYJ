package learningaccumulation;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeDate {
    public  static void main(String[] args) {

        String patternStr = "yyyy-MM-dd HH:mm:ss";
        // 北京时间（new出来就是默认时区的时间）
        Date bjDate = new Date();

        // 得到纽约的时区
        TimeZone newYorkTimeZone = TimeZone.getTimeZone("America/New_York");
        // 根据此时区 将北京时间转换为纽约的Date
        DateFormat newYorkDateFormat = new SimpleDateFormat(patternStr);
        newYorkDateFormat.setTimeZone(newYorkTimeZone);
        System.out.println("这是北京时间：" + new SimpleDateFormat(patternStr).format(bjDate));
        System.out.println("这是纽约时间：" + newYorkDateFormat.format(bjDate));
    }
}
