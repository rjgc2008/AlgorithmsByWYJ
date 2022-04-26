package chapterone;

import edu.princeton.cs.algs4.StdOut;
import learningaccumulation.WeekTime;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

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

    public GaussDbUtil(String str) {
        nameOfArea = str;
        if (str.equals("HK")) {
            DBInfo dbInfoCampubaseDB = new DBInfo("172.16.2.23", "32082", "campusbasedb", "Changeme_123");
            DBInfo dbInfoUserDB = new DBInfo("172.16.2.23", "32085", "userdb", "Changeme_123");
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
        } else if (str.equals("Europe")) {
            DBInfo dbinfoCampubaseDB = new DBInfo("192.168.2.72", "32083", "campusbasedb", "Changeme_123");
            DBInfo dbinfoUserDB = new DBInfo("192.168.2.71", "32083", "userdb", "Changeme_123");
            // 建数据库连接。
            try {
                // 加载数据库驱动。
                Class.forName(dbinfoCampubaseDB.driver);
                // 创建数据库连接。
                connCampusbaseDB = DriverManager.getConnection(dbinfoCampubaseDB.sourceURL, dbinfoCampubaseDB.dbName, dbinfoCampubaseDB.password);
                stmtCampusbaseDB = connCampusbaseDB.createStatement();

                connUserDB = DriverManager.getConnection(dbinfoUserDB.sourceURL, dbinfoUserDB.dbName, dbinfoUserDB.password);
                stmtUserDB = connUserDB.createStatement();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (str.equals("Brazil")) {
            DBInfo dbInfoCampubaseDB = new DBInfo("172.16.2.23", "32082", "campusbasedb", "Changeme_123");
            DBInfo dbInfoUserDB = new DBInfo("172.16.2.23", "32085", "userdb", "Changeme_123");
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
            StdOut.printf("[%s] close stmtCampusbaseDB\n",nameOfArea);
        }
        //关闭 Connection
        if (connCampusbaseDB != null) {
            try {
                connCampusbaseDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            StdOut.printf("[%s] close connCampusbaseDB\n",nameOfArea);
        }
        //关闭 Statement
        if (stmtUserDB != null) {
            try {
                stmtUserDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            StdOut.printf("[%s] close stmtUserDB\n",nameOfArea);
        }
        //关闭 Connection
        if (connUserDB != null) {
            try {
                connUserDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            StdOut.printf("[%s] close connUserDB\n",nameOfArea);
        }
    }

    public void printInfo() {
        String SumOfDevice = getDeviceSumInfo(stmtCampusbaseDB, sqlDeviceSum);
        StdOut.printf("%-50s%6s\n", "[" + nameOfArea + "] the sum of all device is : ", SumOfDevice);

        String sumOfDeviceWarn = getDeviceSumInfo(stmtCampusbaseDB, sqlDeviceWarnSum);
        StdOut.printf("%-50s%6s\n", "[" + nameOfArea + "] the sum of warn device is : ", sumOfDeviceWarn);

        String sumOfDeviceOffline = getDeviceSumInfo(stmtCampusbaseDB, sqlDeviceOfflineSum);
        StdOut.printf("%-50s%6s\n", "[" + nameOfArea + "] the sum of offline device is : ", sumOfDeviceOffline);

        String DeviceUnregisterSum = getDeviceSumInfo(stmtCampusbaseDB, sqlDeviceUnRegisterSum);
        StdOut.printf("%-50s%6s\n", "[" + nameOfArea + "] the sum of unregister device is : ", DeviceUnregisterSum);

        String SumOfDeviceNormal = getDeviceSumInfo(stmtCampusbaseDB, sqlDeviceNormalSum);
        StdOut.printf("%-50s%6s\n", "[" + nameOfArea + "] the sum of normal device is : ", SumOfDeviceNormal);

        String SumOfTenantNormal = getDeviceSumInfo(stmtUserDB, sqlTenantSum);
        StdOut.printf("%-50s%6s\n", "[" + nameOfArea + "] the sum of tenant is : ", SumOfTenantNormal);

        String SumOfMSPNormal = getDeviceSumInfo(stmtUserDB, sqlMSPSum);
        StdOut.printf("%-50s%6s\n", "[" + nameOfArea + "] the sum of msp is : ", SumOfMSPNormal);

        StdOut.println("the info about device&tenant&sum in this week");
        this.printInfoIncrease(stmtCampusbaseDB,stmtUserDB);
    }

    private class DeviceIncreaseInfo {
        String tenantName = null;
        String deviceIncreaseSum = null;


    }

    public void printInfoIncrease(Statement stmtCampusbaseDB, Statement stmtUserDB) {
        WeekTime weektime = new WeekTime();
        Long startTimeOfThisWeek = weektime.getWeekStartTime();
        Long endTimeOfThisWeek = weektime.getWeekEndTime();
        String sqlQueryIncreaseInfo = "SELECT count(*) AS SUM,TENANTID FROM CAMPUSBASEDB.T_CAMPUS_DEVICEMGR_DEVICE WHERE CREATETIME > " + startTimeOfThisWeek + " AND CREATETIME < " + endTimeOfThisWeek + " GROUP BY TENANTID ORDER BY SUM DESC LIMIT 10";

        ResultSet rs = null;
        HashMap<String, DeviceIncreaseInfo> hashMapDeviceIncreaseInfo = new HashMap<String, DeviceIncreaseInfo>();
        try {
            // 执行普通SQL语句。
            rs = stmtCampusbaseDB.executeQuery(sqlQueryIncreaseInfo);
            while (rs.next()) {
                DeviceIncreaseInfo div = new DeviceIncreaseInfo();
                div.deviceIncreaseSum = rs.getString("SUM");
                hashMapDeviceIncreaseInfo.put(rs.getString("TENANTID"), div);
            }

            // 执行普通SQL语句。
            for (String tenantID : hashMapDeviceIncreaseInfo.keySet()) {
                String sqlQueryTenantInfo = "select ORGID ,ORGNAME  from T_ORGANIZATION where ORGID= '" + tenantID + "'";
                String tenantName = null;

                rs = stmtUserDB.executeQuery(sqlQueryTenantInfo);
                while (rs.next()) {
                    tenantName = rs.getString("ORGNAME");
                }
                hashMapDeviceIncreaseInfo.get(tenantID).tenantName = tenantName;
            }
        } catch (SQLException e) {
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

        for(String tenantID : hashMapDeviceIncreaseInfo.keySet()){
//            StdOut.println(tenantID + "|" + hashMapDeviceIncreaseInfo.get(tenantID).tenantName + "|" + hashMapDeviceIncreaseInfo.get(tenantID).deviceIncreaseSum);
            StdOut.printf("%-40s|%-40s|%10s\n",tenantID,hashMapDeviceIncreaseInfo.get(tenantID).tenantName,hashMapDeviceIncreaseInfo.get(tenantID).deviceIncreaseSum);
        }

    }

    /**
     * 查询数据
     */
    public String getDeviceSumInfo(Statement stmt, String sql) {
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

    public static void main(String[] args)  {
        GaussDbUtil HK = new GaussDbUtil("HK");
        try {
            HK.printInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HK.closeAll();
    }
}