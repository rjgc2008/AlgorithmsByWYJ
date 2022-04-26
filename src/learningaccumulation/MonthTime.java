package learningaccumulation;

import edu.princeton.cs.algs4.StdOut;

import java.util.Calendar;

public class MonthTime {
    public long getMonthStartTime(int month){
        // 获取当月第一天
        Calendar FirstDayOfMonthJan = Calendar.getInstance();
        FirstDayOfMonthJan.set(Calendar.MONTH,month);
        FirstDayOfMonthJan.set(Calendar.DAY_OF_MONTH, 1);
        FirstDayOfMonthJan.set(Calendar.HOUR_OF_DAY, 0);
        FirstDayOfMonthJan.set(Calendar.MINUTE, 0);
        FirstDayOfMonthJan.set(Calendar.SECOND, 0);
        long firstdayTime = FirstDayOfMonthJan.getTime().getTime();
        return firstdayTime;
    }

    public long getMonthEndTime(int month) {
        Calendar LastDayOfThisMonth = Calendar.getInstance();
        LastDayOfThisMonth.set(Calendar.MONTH,month);
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

    public long getCurrentMonthEndTime() {
        Calendar LastDayOfThisMonth = Calendar.getInstance();
        LastDayOfThisMonth.set(Calendar.DAY_OF_MONTH, LastDayOfThisMonth.getActualMaximum(Calendar.DAY_OF_MONTH));
        LastDayOfThisMonth.set(Calendar.HOUR_OF_DAY, 23);
        LastDayOfThisMonth.set(Calendar.MINUTE, 59);
        LastDayOfThisMonth.set(Calendar.SECOND, 59);

        long lastdayTime = LastDayOfThisMonth.getTime().getTime();
        return lastdayTime;
    }

    public static void main(String[] args){
        MonthTime mt = new MonthTime();
        StdOut.printf("%s\n",mt.getCurrentMonthStartTime());
        StdOut.printf("%s\n",mt.getCurrentMonthEndTime());
        StdOut.printf("%s\n",mt.getMonthStartTime(0));
        StdOut.printf("%s\n",mt.getMonthEndTime(0));
    }
}
