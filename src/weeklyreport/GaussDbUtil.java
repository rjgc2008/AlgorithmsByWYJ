package weeklyreport;

import edu.princeton.cs.algs4.StdOut;
import learningaccumulation.MonthTime;
import learningaccumulation.WeekTime;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

public class GaussDbUtil {
    //记录公有云：HK/Ctyun/Europe/Brazil/A/B/D/F
    String nameOfArea = null;

    //CampusbaseDB的Connection和Statement
    Connection connCampusbaseDB = null;
    Statement stmtCampusbaseDB = null;

    //userDB的Connection和Statement
    Connection connUserDB = null;
    Statement stmtUserDB = null;

    //SQL语句：查询设备总数
    String sqlDeviceSum = "SELECT count(*) AS sum FROM CAMPUSBASEDB.T_CAMPUS_DEVICEMGR_DEVICE";
    //SQL语句：查询设备状态为告警的设备总数
    String sqlDeviceWarnSum = "SELECT count(*) AS SUM FROM CAMPUSBASEDB.T_CAMPUS_DEVICEMGR_DEVICE WHERE STATUS = '1'";
    //SQL语句：查询设备状态为离线的设备总数
    String sqlDeviceOfflineSum = "SELECT count(*) AS SUM FROM CAMPUSBASEDB.T_CAMPUS_DEVICEMGR_DEVICE WHERE STATUS = '3'";
    //SQL语句：查询设备状态为未注册的设备总数
    String sqlDeviceUnRegisterSum = "SELECT count(*) AS SUM FROM CAMPUSBASEDB.T_CAMPUS_DEVICEMGR_DEVICE WHERE STATUS = '4'";
    //SQL语句：查询设备状态为正常的设备总数
    String sqlDeviceNormalSum = "SELECT count(*) AS SUM FROM CAMPUSBASEDB.T_CAMPUS_DEVICEMGR_DEVICE WHERE STATUS = '0'";

    //SQL语句：查询平台租户总数
    String sqlTenantSum = "select count(*) AS SUM from T_ORGANIZATION_TAG where TAG_VALUE= 'tenant'";
    //SQL语句：查询平台MSP总数
    String sqlMSPSum = "select count(*) AS Sum from T_ORGANIZATION_TAG where TAG_VALUE= 'msp'";
    //SQL语句：查询平台设备总数排名前10的租户
    String sqlTotalDeviceAndTenantInfo = "SELECT count(*) AS sum,tenantid FROM CAMPUSBASEDB.T_CAMPUS_DEVICEMGR_DEVICE GROUP BY TENANTID ORDER BY sum DESC LIMIT 10";

    //SQL语句：查询有设备的租户总数
    String sqlOfgetSumOfTenant = "SELECT count(DISTINCT tenantid) as SUM FROM T_CAMPUS_DEVICEMGR_DEVICE";

    /**
     * 记录数据库登录信息
     */
    private class DBInfo {
        String driver;
        String ip;
        String port;
        String sourceURL;
        String dbName;
        String password;

        DBInfo(String ip, String port, String dbName, String password) {
            driver = "com.huawei.gauss.jdbc.ZenithDriver";
            this.ip = ip;
            this.port = port;
            sourceURL = "jdbc:zenith:@" + ip + ":" + port;
            this.dbName = dbName;
            this.password = password;
        }
    }

    /**
     * 构造函数：基于传入参数判断平台归属
     *
     * @param str
     */
    public GaussDbUtil(String str) {
        nameOfArea = str;
        DBInfo dbInfoCampubaseDB = null;
        DBInfo dbInfoUserDB = null;
        if (str.equals("HK")) {
            dbInfoCampubaseDB = new DBInfo("172.16.2.23", "32082", "campusbasedb", "Changeme_123");
            dbInfoUserDB = new DBInfo("172.16.2.23", "32085", "userdb", "Changeme_123");
        } else if (str.equals("Ctyun")) {
            dbInfoCampubaseDB = new DBInfo("192.168.3.15", "32087", "campusbasedb", "Changeme_123");
            dbInfoUserDB = new DBInfo("192.168.3.14", "32082", "userdb", "Changeme_123");
        } else if (str.equals("Europe")) {
            dbInfoCampubaseDB = new DBInfo("192.168.2.72", "32083", "campusbasedb", "Changeme_123");
            dbInfoUserDB = new DBInfo("192.168.2.71", "32083", "userdb", "Changeme_123");
        } else if (str.equals("Brazil")) {
            dbInfoCampubaseDB = new DBInfo("10.1.2.67", "32083", "campusbasedb", "Changeme_123");
            dbInfoUserDB = new DBInfo("10.1.2.65", "32083", "userdb", "Changeme_123");
        } else if (str.equals("A")) {
            dbInfoCampubaseDB = new DBInfo("10.16.2.106", "32081", "campusbasedb", "Changeme_123");
            dbInfoUserDB = new DBInfo("10.16.2.101", "32083", "userdb", "Changeme_123");
        } else if (str.equals("B")) {
            dbInfoCampubaseDB = new DBInfo("10.100.2.56", "32083", "campusbasedb", "Changeme_123");
            dbInfoUserDB = new DBInfo("10.100.2.51", "32080", "userdb", "Changeme_123");
        } else if (str.equals("D")) {
            dbInfoCampubaseDB = new DBInfo("10.22.11.16", "32080", "campusbasedb", "Changeme_123");
            dbInfoUserDB = new DBInfo("10.22.11.13", "32083", "userdb", "Changeme_123");
        } else if (str.equals("F")) {
            dbInfoCampubaseDB = new DBInfo("10.21.11.46", "32083", "campusbasedb", "Changeme_123");
            dbInfoUserDB = new DBInfo("10.21.11.41", "32080", "userdb", "Changeme_123");
        }
        // 建数据库连接。
        try {
            // 加载数据库驱动。
            Class.forName(dbInfoCampubaseDB.driver);
            // 创建数据库连接。
            connCampusbaseDB = DriverManager.getConnection(dbInfoCampubaseDB.sourceURL, dbInfoCampubaseDB.dbName, dbInfoCampubaseDB.password);
            stmtCampusbaseDB = connCampusbaseDB.createStatement();

            connUserDB = DriverManager.getConnection(dbInfoUserDB.sourceURL, dbInfoUserDB.dbName, dbInfoUserDB.password);
            stmtUserDB = connUserDB.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 关闭全部连接
     */
    public void closeAll() {
        //关闭 Statement
        if (stmtCampusbaseDB != null) {
            try {
                stmtCampusbaseDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            StdOut.printf("|[%s] close stmtCampusbaseDB|", nameOfArea);
        }
        //关闭 Connection
        if (connCampusbaseDB != null) {
            try {
                connCampusbaseDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            StdOut.printf("[%s] close connCampusbaseDB|", nameOfArea);
        }
        //关闭 Statement
        if (stmtUserDB != null) {
            try {
                stmtUserDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            StdOut.printf("[%s] close stmtUserDB|", nameOfArea);
        }
        //关闭 Connection
        if (connUserDB != null) {
            try {
                connUserDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            StdOut.printf("[%s] close connUserDB|", nameOfArea);
        }
    }

    /**
     * 打印统计信息
     */
    public void printStatisticsInfo() {
        StdOut.printf("Title: up to %s,the Statistics info about this platform\n",new WeekTime().getCurrentTimeStr());
        StdOut.println("---------------------------------------------------------");
        String SumOfDevice = getSum(stmtCampusbaseDB, sqlDeviceSum);
        StdOut.printf("%-50s%6s\n", "[" + nameOfArea + "] the sum of all device is : ", SumOfDevice);

        String sumOfDeviceWarn = getSum(stmtCampusbaseDB, sqlDeviceWarnSum);
        StdOut.printf("%-50s%6s\n", "[" + nameOfArea + "] the sum of warn device is : ", sumOfDeviceWarn);

        String sumOfDeviceOffline = getSum(stmtCampusbaseDB, sqlDeviceOfflineSum);
        StdOut.printf("%-50s%6s\n", "[" + nameOfArea + "] the sum of offline device is : ", sumOfDeviceOffline);

        String DeviceUnregisterSum = getSum(stmtCampusbaseDB, sqlDeviceUnRegisterSum);
        StdOut.printf("%-50s%6s\n", "[" + nameOfArea + "] the sum of unregister device is : ", DeviceUnregisterSum);

        String SumOfDeviceNormal = getSum(stmtCampusbaseDB, sqlDeviceNormalSum);
        StdOut.printf("%-50s%6s\n", "[" + nameOfArea + "] the sum of normal device is : ", SumOfDeviceNormal);

        String SumOfTenantNormal = getSum(stmtUserDB, sqlTenantSum);
        StdOut.printf("%-50s%6s\n", "[" + nameOfArea + "] the sum of tenant is : ", SumOfTenantNormal);

        String SumOfMSPNormal = getSum(stmtUserDB, sqlMSPSum);
        StdOut.printf("%-50s%6s\n", "[" + nameOfArea + "] the sum of msp is : ", SumOfMSPNormal);

        String SumOfTenantHaveDevice = getSum(stmtCampusbaseDB, sqlOfgetSumOfTenant);
        StdOut.printf("%-50s%6s\n", "[" + nameOfArea + "] the sum of tenant which have device is : ", SumOfTenantHaveDevice);

        StdOut.printf("\n\nTitle: %s-%s the tenant info about which increased most this week\n",new WeekTime().getWeekStartTimeStr(),new WeekTime().getWeekEndTimeStr());
        StdOut.println("---------------------------------------------------------");
        StdOut.printf("|%-12s|%-40s\n", "SumOfDevices","TenantName" );
        StdOut.println("---------------------------------------------------------");
        this.printHashMap(this.getTenantNameAndCount(stmtCampusbaseDB, stmtUserDB, this.getSqlOfIncreasdMostThisWeek()));
        StdOut.printf("\n\nTitle: %s-%s the tenant info about which increased most this month\n",new MonthTime().getCurrentMonthStartTimeStr(),new MonthTime().getCurrentMonthEndTimeStr());
        StdOut.println("---------------------------------------------------------");
        StdOut.printf("|%-12s|%-40s\n", "SumOfDevices","TenantName" );
        StdOut.println("---------------------------------------------------------");
        this.printHashMap(this.getTenantNameAndCount(stmtCampusbaseDB, stmtUserDB, this.getSqlofIncreasedMostThisMonth()));
        StdOut.printf("\n\nTitle: up to %s,the tenant info about which have most device  in this platform\n",new WeekTime().getCurrentTimeStr());
        StdOut.println("---------------------------------------------------------");
        StdOut.printf("|%-12s|%-40s\n", "SumOfDevices","TenantName" );
        StdOut.println("---------------------------------------------------------");
        this.printHashMap(this.getTenantNameAndCount(stmtCampusbaseDB, stmtUserDB, sqlTotalDeviceAndTenantInfo));
    }

    private class DeviceIncreaseInfo {
        String tenantName = null;
        String deviceIncreaseSum = null;
    }

    /**
     * 生成本周内新增设备的SQL语句
     *
     * @return
     */
    public String getSqlOfIncreasdMostThisWeek() {
        WeekTime weektime = new WeekTime();
        Long startTimeOfThisWeek = weektime.getWeekStartTime();
        Long endTimeOfThisWeek = weektime.getWeekEndTime();
        String sqlQueryIncreaseInfo = "SELECT count(*) AS SUM,TENANTID FROM CAMPUSBASEDB.T_CAMPUS_DEVICEMGR_DEVICE WHERE CREATETIME > " + startTimeOfThisWeek + " AND CREATETIME < " + endTimeOfThisWeek + " GROUP BY TENANTID ORDER BY SUM DESC LIMIT 10";
        return sqlQueryIncreaseInfo;
    }

    /**
     * 生成本月内新增设备的SQL语句
     * @return
     */
    public String getSqlofIncreasedMostThisMonth(){
        MonthTime MonthTime = new MonthTime();
        Long startTimeOfThisMonth = MonthTime.getCurrentMonthStartTime();
        Long endTimeOfThisMonth = MonthTime.getCurrentMonthEndTime();
        String sqlQueryIncreaseInfo = "SELECT count(*) AS SUM,TENANTID FROM CAMPUSBASEDB.T_CAMPUS_DEVICEMGR_DEVICE WHERE CREATETIME > " + startTimeOfThisMonth + " AND CREATETIME < " + endTimeOfThisMonth + " GROUP BY TENANTID ORDER BY SUM DESC LIMIT 10";
        return sqlQueryIncreaseInfo;
    }

    /**
     * 根据查询语句统计租户信息（租户ID、租户名称、设备总数）
     * 主要有2种，一种查询一周内新增，另外一种查询统计总数
     *
     * @param stmtCampusbaseDB
     * @param stmtUserDB
     * @param sqlQueryIncreaseInfo
     */
    public LinkedHashMap<String, DeviceIncreaseInfo> getTenantNameAndCount(Statement stmtCampusbaseDB, Statement stmtUserDB, String sqlQueryIncreaseInfo) {
        ResultSet rs = null;
        LinkedHashMap<String, DeviceIncreaseInfo> hashMapDeviceIncreaseInfo = new LinkedHashMap<String, DeviceIncreaseInfo>();
        try {
            // 根据SQL语句，在CampusbaseDB统计信息，执行普通SQL语句。
            rs = stmtCampusbaseDB.executeQuery(sqlQueryIncreaseInfo);
            while (rs.next()) {
                DeviceIncreaseInfo div = new DeviceIncreaseInfo();
                div.deviceIncreaseSum = rs.getString("SUM");
                hashMapDeviceIncreaseInfo.put(rs.getString("TENANTID"), div);
            }

            for (String tenantID : hashMapDeviceIncreaseInfo.keySet()) {
                String sqlQueryTenantInfo = "select ORGID ,ORGNAME  from T_ORGANIZATION where ORGID= '" + tenantID + "'";
                String tenantName = null;

                // 根据SQL语句，在UserDB统计信息，执行普通SQL语句
                rs = stmtUserDB.executeQuery(sqlQueryTenantInfo);
                while (rs.next()) {
                    tenantName = rs.getString("ORGNAME");
                }
                hashMapDeviceIncreaseInfo.get(tenantID).tenantName = tenantName;
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return hashMapDeviceIncreaseInfo;
    }

    public void printHashMap(LinkedHashMap<String, DeviceIncreaseInfo> hashMapDeviceIncreaseInfo){

        //打印信息：租户ID|租户名称|设备总数
        for (
                String tenantID : hashMapDeviceIncreaseInfo.keySet()) {
//            StdOut.printf("|%-40s|%-40s|%10s|\n", tenantID, hashMapDeviceIncreaseInfo.get(tenantID).tenantName, hashMapDeviceIncreaseInfo.get(tenantID).deviceIncreaseSum);
            StdOut.printf("|%-12s|%-40s\n", hashMapDeviceIncreaseInfo.get(tenantID).deviceIncreaseSum,hashMapDeviceIncreaseInfo.get(tenantID).tenantName );
        }
    }

    /**
     * 根据SQL语句查询各种设备状态的设备总数
     * 设备状态：总数|告警|离线|未注册|离线|正常
     */
    public String getSum(Statement stmt, String sql) {
        ResultSet rs = null;
        String resultSum = null;
        try {
            // 执行普通SQL语句。
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                resultSum = rs.getString("SUM");
            }
            return resultSum;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
// /**
// * 更新数据
// *
// * @param tableName
// * @param updateFieldValueMap
// * @param whereFieldValueMap
// */
// public int gaussUpdate(String sql) {
// int rowcount = 0;
// PreparedStatement pstmt = null;
// try {
// pstmt = conn.prepareStatement(sql);
// rowcount = pstmt.executeUpdate();
// } catch (SQLException e) {
// e.printStackTrace();
// } finally {
// if (pstmt != null) {
// try {
// pstmt.close();
// } catch (SQLException e1) {
// e1.printStackTrace();
// }
// }
// }
//
// return rowcount;
// }

//    /**
//     * ResultSet转Json
//     *
//     * @param rs
//     * @return
//     * @throws SQLException
//     * @throws JSONException
//     */
//    public static String resultSetToJson(ResultSet rs) throws SQLException, JSONException {
//        JSONArray array = new JSONArray();
//// 获取列数
//        ResultSetMetaData metaData = rs.getMetaData();
//        int columnCount = metaData.getColumnCount();
//// 遍历ResultSet中的每条数据
//        while (rs.next()) {
//            JSONObject jsonObj = new JSONObject();
//// 遍历每一列
//            for (int i = 1; i <= columnCount; i++) {
//                String columnName = metaData.getColumnLabel(i);
//                String value = rs.getString(columnName);
//                jsonObj.put(columnName, value);
//            }
//            array.add(jsonObj);
//        }
//        return array.toString();
//    }

    public static void main(String[] args) {
        String platName = args[0];
        GaussDbUtil HK = new GaussDbUtil(platName);
        try {
            HK.printStatisticsInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HK.closeAll();
    }
}