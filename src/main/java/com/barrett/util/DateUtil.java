package com.barrett.util;

import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间的工具类
 *
 * @author tanyicheng   E-mail: 65797706@qq.com
 * @version 1.0 常州繁成软件开发有限公司版权所有，请勿盗用！
 * @date 创建时间：2017年3月26日 下午2:44:47
 */
public class DateUtil {

    /**
     * 获取当前时间
     * 大写HH：24小时制，小写hh：12小时制
     * 毫秒：SSS
     *
     * @return yyyy-MM-dd HH:mm:ss
     * @author tanyicheng 创建时间：2017年3月26日-下午2:50:26
     */
    public static String getDateTime() {
        String dateStr = "";
        Date date = new Date();
        //format的格式可以任意 yyyy-MM-dd HH/mm/ss
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dateStr = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 根据时间生成订单编码,
     * 根据num 添加随机数(num=100,表示加3位)
     *
     * @return
     * @author tanyicheng 创建时间：2017年11月22日-下午5:57:46
     */
    public static String getDateTimeToOrder(int num) {
        String dateStr = "";
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsss");
        try {
            //外加随机数 (6位是*100000)
            int rnd = 0;
            if (num > 0) {
                rnd = (int) ((Math.random() * 9 + 1) * num);
            }
            dateStr = sdf.format(date) + rnd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 获取当前日期
     *
     * @return yyyy-MM-dd
     * @author tanyicheng 创建时间：2017年4月25日-下午8:41:29
     */
    public static String getDate() {
        String dateStr = "";
        Date date = new Date();
        //format的格式可以任意
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	            DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH/mm/ss");   
        try {
            dateStr = sdf.format(date);
//	                System.out.println(dateStr);   
//	                dateStr = sdf2.format(date);   
//	                System.out.println(dateStr);   
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * String类型转Date <br>
     * yyyy-MM-dd HH:mm:ss --> Sun Mar 26 14:53:45 CST 2017
     *
     * @return Fri Mar 26 13:31:40 CST 2017
     * @author tanyicheng 创建时间：2017年3月26日-下午2:49:34
     */
    public static Date stringToDate(String dateStr) {
        Date date = new Date();
        //注意format的格式要与日期String的格式相匹配   
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(dateStr);
            System.out.println(date.toString());
            System.out.println(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * date转String <br>
     * Sun Mar 26 14:53:45 CST 2017 --> yyyy-MM-dd HH:mm:ss
     *
     * @return
     * @author tanyicheng 创建时间：2017年3月26日-下午2:57:51
     */
    public static String dateToString(Date date) {
        String dateStr = "";
        //format的格式可以任意
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dateStr = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 获取当前时间
     * @author tanyicheng 创建时间：2017年3月26日-下午3:03:45
     * @return yyyy/MM/dd HH:mm:ss
     */
//	public static String getDate2(){
//		String dateStr = "";   
//		Date date = new Date();   
//		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");   
//		try {   
//			dateStr = sdf.format(date);   
//		} catch (Exception e) {   
//			e.printStackTrace();   
//		}  
//		return dateStr;
//	}

    /**
     * string转Timestamp <br>
     * 2017-03-26 15:09:33 --> 2017-03-26 15:09:23.0
     *
     * @return
     * @author tanyicheng 创建时间：2017年3月26日-下午3:06:20
     */
    public static Timestamp StringToTimestamp(String tsStr) {

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        try {
            ts = Timestamp.valueOf(tsStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ts;
    }

    /**
     * 时间转换为数字
     *
     * @param date
     * @return
     * @author tanyicheng 创建时间：2017年3月26日-下午3:13:07
     */
    public static long dateNum(Date date) {
        long dl = date.getTime();
        return dl;
    }

    /**
     * 比较时间差<br> 格式：2018-01-02 11:30:24
     *
     * @param dateFirst
     * @param dateLast
     * @return dateFirst > dateEnd 大于1，  小于-1，  等于为0
     * @author tanyicheng 创建时间：2017年3月26日-下午3:17:12
     */
    public static int dateDifference(String dateFirst, String dateLast) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(dateFirst));
            c2.setTime(df.parse(dateLast));
        } catch (ParseException e) {
            System.err.println("格式不正确");
        }
        int result = c1.compareTo(c2);
//	    if(result==0)
//	    	System.out.println("dateFirst相等dateEnd");
//	    else if(result<0)
//	    	System.out.println("dateFirst小于dateEnd");
//	    else
//	    	System.out.println("dateFirst大于dateEnd");
        return result;
    }

    /**
     * 计算时间差<br> 格式：2018-01-02 11:30:24
     * first - last
     *
     * @param dateFirst
     * @param dateLast
     * @param flag      ss 计算秒数差，dd 计算天数
     * @return 返回具体秒数
     * @author tanyicheng 创建时间：2018年1月25日-下午8:52:41
     */
    public static long dateDifferenceNum(String dateFirst, String dateLast, String flag) {
        DateFormat df = null;
        long interval = 0L;
        try {
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();

            if (flag.equals("ss")) {
                df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                interval = (df.parse(dateLast).getTime() - df.parse(dateFirst).getTime()) / 1000;
            } else if (flag.equals("dd")) {
                df = new SimpleDateFormat("yyyy-MM-dd");
                interval = (df.parse(dateLast).getTime() - df.parse(dateFirst).getTime()) / (1000 * 3600 * 24);
            }

        } catch (Exception e) {
            System.err.println("格式不正确");
        }
        return interval;
    }

    /**
     * 倒计时，例：84天2小时1分0秒
     *
     * @return
     * @author tanyicheng 创建时间：2017年3月26日-下午5:42:29
     */
    public static String daojishi() {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin = null;
        Date end = null;
        try {
            begin = dfs.parse("2004-01-02 11:30:24");
            end = dfs.parse("2004-03-26 13:31:40");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long between = (end.getTime() - begin.getTime()) / 1000;//除以1000是为了转换成秒
        long day1 = between / (24 * 3600);
        long hour1 = between % (24 * 3600) / 3600;
        long minute1 = between % 3600 / 60;
        long second1 = between % 60 / 60;
        String str = day1 + "天" + hour1 + "小时" + minute1 + "分" + second1 + "秒";
//	       System.out.println(str);  
        return str;
    }

    /**
     * 当指定日期增加天数
     *
     * @Param num 天数
     * @Param newDate为空则在当前日期叠加 格式：yyy-MM-dd
     * @Author created by barrett in 2018/12/11 22:50
     */
    public static String plusDay(int num, String newDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String enddate = "";
        try {
            Date currdate = new Date();
            if (!StringUtils.isEmpty(newDate)) {
                currdate = format.parse(newDate);
            }
            Calendar ca = Calendar.getInstance();
            ca.setTime(currdate);
            ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
            currdate = ca.getTime();
            enddate = format.format(currdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return enddate;
    }

    /**
     * 根据时间戳计算差值
     *
     * @author created by barrett in 2021/5/16 20:42
     **/
    public static long timestamp(long first, long end) {
        long result = end - first;
        return result;
    }

    /**
     * 获取偏移的日期 秒
     *
     * @return
     * @throws ParseException
     */
    public static Date getDateOffsetSeconds(Date date, int seconds) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);//把秒往后增加    整数往后推,负数往前移动
        return calendar.getTime();
    }

    /**
     * 时间字符转时间戳
     * @Author created by barrett in 2022/12/2 09:18
     */
    public static long getTimestamp(String str) throws ParseException {
        Date date = stringToDate(str);
        long shootTime = date.getTime();
        return shootTime;
    }


    public static void main(String[] args) throws ParseException {
        DateUtil d = new DateUtil();
        //d.stringToDate("2004-03-26 13:31:40");
//		long da = dateNum(new Date());
//		System.out.println(da);
//		System.out.println(getDateTimeToOrder());
//		System.out.println(dateDifferenceNum("2004-03-26 13:00:40", "2004-03-26 13:60:40"));
//		System.out.println(getDateTime());;
//        plusDay(1, null);
//		plusDay(1,"2018-12-31");
        long timestamp = timestamp(1621169244719L, 1621169267719L);
        System.out.println(timestamp);

        String dateTimeToOrder = getDateTimeToOrder(1000);
        System.out.println(dateTimeToOrder);
        //202206261510406098
        //2022070521371631140
        //20220705213604549
        //2022070521340574

        System.out.println(dateToString(getDateOffsetSeconds(new Date(), 60)));

        System.out.println(getTimestamp("2022-12-02 09:46:00"));

        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar beforeDay = Calendar.getInstance();
        Timestamp ts = Timestamp.valueOf("2022-11-26 10:13:49");
        beforeDay.setTime(ts);
        beforeDay.add(Calendar.DAY_OF_MONTH,-1);
        String beforeDate = sdf.format(beforeDay.getTime());
        System.out.println(beforeDate);

        int i = dateDifference("2022-10-26 10:13:49", "2022-11-26 10:13:49");
        System.out.println(i);

    }
}
