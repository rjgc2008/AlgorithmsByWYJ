package learningaccumulation;

import edu.princeton.cs.algs4.StdOut;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class WeekTime {
    /**
     * start
     * 本周开始时间戳 - 以星期一为本周的第一天
     */
    public long getWeekStartTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0,
                0, 0);
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        cal.add(Calendar.DATE, -day_of_week + 1);
        return cal.getTime().getTime();
    }

    /**
     * end
     * 本周结束时间戳 - 以星期一为本周的第一天
     */
    public long getWeekEndTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 23,
                59, 59);
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        cal.add(Calendar.DATE, -day_of_week + 7);
        return cal.getTime().getTime();
    }

    public static void main(String[] args) {
        WeekTime lz = new WeekTime();
        StdOut.println(lz.getWeekStartTime());
        StdOut.println(lz.getWeekEndTime());
    }
}
