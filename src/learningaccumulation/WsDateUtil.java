package learningaccumulation;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WsDateUtil {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取指定时间戳所在月份开始的时间戳/秒
     *
     * @param dateTimeMillis 指定时间戳/毫秒
     * @return
     */
    public Long getMonthBegin(Long dateTimeMillis) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(dateTimeMillis));

        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND, 0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return c.getTimeInMillis() / 1000;
    }

    /**
     * 获取指定时间戳所在月份15号的时间戳/秒
     *
     * @param dateTimeMillis 指定时间戳/毫秒
     * @return
     */
    public Long getMonthMiddle(Long dateTimeMillis) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(dateTimeMillis));

        //设置为当月最后一天
        c.set(Calendar.DAY_OF_MONTH, 15);
        //将小时至23
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至59
        c.set(Calendar.MINUTE, 59);
        //将秒至59
        c.set(Calendar.SECOND, 59);
        //将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 获取本月最后一天的时间戳
        return c.getTimeInMillis() / 1000;
    }

    /**
     * 获取指定时间戳所在月份结束的时间戳/秒
     *
     * @param dateTimeMillis 指定时间戳/毫秒
     * @return
     */
    public Long getMonthEnd(Long dateTimeMillis) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(dateTimeMillis));

        //设置为当月最后一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至23
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至59
        c.set(Calendar.MINUTE, 59);
        //将秒至59
        c.set(Calendar.SECOND, 59);
        //将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 获取本月最后一天的时间戳
        return c.getTimeInMillis() / 1000;
    }


    /**
     * 获取当前日期
     *
     * @return
     */
    public  String getCurrentDate() {
        Date date = new Date();
        return sdf.format(date);
    }

}
