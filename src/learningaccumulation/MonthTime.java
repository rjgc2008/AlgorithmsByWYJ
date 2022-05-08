package learningaccumulation;

import edu.princeton.cs.algs4.StdOut;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MonthTime {
    //    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public long getMonthStartTime(int month) {
        // 获取当月第一天
        Calendar FirstDayOfMonth = Calendar.getInstance();
        FirstDayOfMonth.set(Calendar.MONTH, month);
        FirstDayOfMonth.set(Calendar.DAY_OF_MONTH, 1);
        FirstDayOfMonth.set(Calendar.HOUR_OF_DAY, 0);
        FirstDayOfMonth.set(Calendar.MINUTE, 0);
        FirstDayOfMonth.set(Calendar.SECOND, 0);
        long firstdayTime = FirstDayOfMonth.getTime().getTime();
        return firstdayTime;
    }

    public long getMonthEndTime(int month) {
        Calendar LastDayOfThisMonth = Calendar.getInstance();
        LastDayOfThisMonth.set(Calendar.MONTH, month);
        LastDayOfThisMonth.set(Calendar.DAY_OF_MONTH, LastDayOfThisMonth.getActualMaximum(Calendar.DAY_OF_MONTH));
        LastDayOfThisMonth.set(Calendar.HOUR_OF_DAY, 23);
        LastDayOfThisMonth.set(Calendar.MINUTE, 59);
        LastDayOfThisMonth.set(Calendar.SECOND, 59);

        long lastdayTime = LastDayOfThisMonth.getTime().getTime();
        return lastdayTime;
    }


    public long getCurrentMonthStartTime() {
        // 获取当月第一天
        Calendar FirstDayOfThisMonth = Calendar.getInstance();
        FirstDayOfThisMonth.set(Calendar.DAY_OF_MONTH, 1);
        FirstDayOfThisMonth.set(Calendar.HOUR_OF_DAY, 0);
        FirstDayOfThisMonth.set(Calendar.MINUTE, 0);
        FirstDayOfThisMonth.set(Calendar.SECOND, 0);
        long firstdayTime = FirstDayOfThisMonth.getTime().getTime();
        return firstdayTime;
    }

    public String getCurrentMonthStartTimeStr() {
        Calendar FirstDayOfThisMonth = Calendar.getInstance();
        FirstDayOfThisMonth.set(Calendar.DAY_OF_MONTH, 1);
        FirstDayOfThisMonth.set(Calendar.HOUR_OF_DAY, 0);
        FirstDayOfThisMonth.set(Calendar.MINUTE, 0);
        FirstDayOfThisMonth.set(Calendar.SECOND, 0);
        return sdf.format(FirstDayOfThisMonth.getTime());
    }

    public long getCurrentMonthEndTime() {
        Calendar LastDayOfThisMonth = Calendar.getInstance();
        LastDayOfThisMonth.set(Calendar.DAY_OF_MONTH, LastDayOfThisMonth.getActualMaximum(Calendar.DAY_OF_MONTH));
        LastDayOfThisMonth.set(Calendar.HOUR_OF_DAY, 23);
        LastDayOfThisMonth.set(Calendar.MINUTE, 59);
        LastDayOfThisMonth.set(Calendar.SECOND, 59);

        long lastdayTime = LastDayOfThisMonth.getTime().getTime();
        return lastdayTime;
    }

    public String getCurrentMonthEndTimeStr() {
        Calendar EndDayOfThisMonth = Calendar.getInstance();
        EndDayOfThisMonth.set(Calendar.DAY_OF_MONTH, EndDayOfThisMonth.getActualMaximum(Calendar.DAY_OF_MONTH));
        EndDayOfThisMonth.set(Calendar.HOUR_OF_DAY, 23);
        EndDayOfThisMonth.set(Calendar.MINUTE, 59);
        EndDayOfThisMonth.set(Calendar.SECOND, 59);
        return sdf.format(EndDayOfThisMonth.getTime());
    }

    public static void main(String[] args) {
        MonthTime mt = new MonthTime();
        StdOut.printf("%s\n", mt.getCurrentMonthStartTime());
        StdOut.printf("%s\n", mt.getCurrentMonthEndTime());
        StdOut.printf("%s\n", mt.getMonthStartTime(0));
        StdOut.printf("%s\n", mt.getMonthEndTime(0));
    }
}
