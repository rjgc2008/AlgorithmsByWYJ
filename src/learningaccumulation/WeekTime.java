package learningaccumulation;

import edu.princeton.cs.algs4.StdOut;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class WeekTime {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    private Calendar cal = Calendar.getInstance();

    /**
     * start
     * 本周开始时间戳 - 以星期一为本周的第一天
     */
    public long getWeekStartTime() {
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0,
                0, 0);
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        cal.add(Calendar.DATE, -day_of_week + 1);
        return cal.getTime().getTime();
    }

    public String getWeekStartTimeStr() {
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0,
                0, 0);
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        cal.add(Calendar.DATE, -day_of_week + 1);
        return simpleDateFormat.format(cal.getTime());
    }

    /**
     * end
     * 本周结束时间戳 - 以星期一为本周的第一天
     */
    public long getWeekEndTime() {
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 23,
                59, 59);
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        cal.add(Calendar.DATE, -day_of_week + 7);
        return cal.getTime().getTime();
    }
    public String getWeekEndTimeStr(){
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 23,
                59, 59);
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        cal.add(Calendar.DATE, -day_of_week + 7);
        return simpleDateFormat.format(cal.getTime());
    }

    public String getCurrentTimeStr(){
        return simpleDateFormat.format(cal.getTime());
    }

    public static void main(String[] args) {
        WeekTime lz = new WeekTime();
        StdOut.println(lz.getWeekStartTime());
        StdOut.println(lz.getWeekEndTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        StdOut.printf("%s", simpleDateFormat.format(cal.getTime()));
    }
}
