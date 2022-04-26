package chapterone;

import edu.princeton.cs.algs4.StdOut;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GaussDbUtil {
    String nameOfArea = null;

    Connection connCampusbaseDB = null;
    Statement stmtCampusbaseDB = null;

    Statement stmtUserDB = null;
    Connection connUserDB = null;

    String sqlDeviceSum = "SELECT count(*) AS sum FROM CAMPUSBASEDB.T_CAMPUS_DEVICEMGR_DEVICE";
    String sqlDeviceWarnSum = "SELECT count(*) AS SUM FROM CAMPUSBASEDB.T_CAMPUS_DEVICEMGR_DEVICE WHERE STATUS = '1'";
    String sqlDeviceOfflineSum = "SELECT count(*) AS SUM FROM CAMPUSBASEDB.T_CAMPUS_DEVICEMGR_DEVICE WHERE STATUS = '3'";
    String sqlDeviceUnRegisterSum = "SELECT count(*) AS SUM FROM CAMPUSBASEDB.T_CAMPUS_DEVICEMGR_DEVICE WHERE STATUS = '4'";
    String sqlDeviceNormalSum = "SELECT count(*) AS SUM FROM CAMPUSBASEDB.T_CAMPUS_DEVICEMGR_DEVICE WHERE STATUS = '0'";

    String sqlTenantSum = "select count(*) AS SUM from T_ORGANIZATION_TAG where TAG_VALUE= 'tenant'";
    String sqlMSPSum = "select count(*) AS Sum from T_ORGANIZATION_TAG where TAG_VALUE= 'msp'";

//    Proxy socketProxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("113.125.74.234",1080));
//    HttpURLConnection socketConnection = (HttpURLConnection)

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
            DBInfo dbinfoCampubaseDB = new DBInfo("172.16.2.23", "32082", "campusbasedb", "Changeme_123");
            DBInfo dbinfoUserDB = new DBInfo("172.16.2.23", "32085", "userdb", "Changeme_123");
            // 建数据库连接。
            try {
                // 加载数据库驱动。
                Class.forName(dbinfoCampubaseDB.driver);
                // 创建数据库连接。
                connCampusbaseDB = DriverManager.getConnection(dbinfoCampubaseDB.sourceURL, dbinfoCampubaseDB.dbName, dbinfoCampubaseDB.password);
                stmtCampusbaseDB = connCampusbaseDB.createStatement();

                connUserDB = DriverManager.getConnection(dbinfoUserDB.sourceURL,dbinfoUserDB.dbName,dbinfoUserDB.password);
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

                connUserDB = DriverManager.getConnection(dbinfoUserDB.sourceURL,dbinfoUserDB.dbName,dbinfoUserDB.password);
                stmtUserDB = connUserDB.createStatement();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (str.equals("Brazil")) {
            DBInfo dbinfoCampubaseDB = new DBInfo("172.16.2.23", "32082", "campusbasedb", "Changeme_123");
            DBInfo dbinfoUserDB = new DBInfo("172.16.2.23", "32085", "userdb", "Changeme_123");
            // 建数据库连接。
            try {
                // 加载数据库驱动。
                Class.forName(dbinfoCampubaseDB.driver);
                // 创建数据库连接。
                connCampusbaseDB = DriverManager.getConnection(dbinfoCampubaseDB.sourceURL, dbinfoCampubaseDB.dbName, dbinfoCampubaseDB.password);
                stmtCampusbaseDB = connCampusbaseDB.createStatement();

                connUserDB = DriverManager.getConnection(dbinfoUserDB.sourceURL,dbinfoUserDB.dbName,dbinfoUserDB.password);
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
            StdOut.println("[ " + nameOfArea + " ] " + "关闭 Statement");
        }
        //关闭 Connection
        if (connCampusbaseDB != null) {
            try {
                connCampusbaseDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            StdOut.println("[ " + nameOfArea + " ] " + "关闭 Connection");
        }
        //关闭 Statement
        if (stmtUserDB != null) {
            try {
                stmtUserDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            StdOut.println("[ " + nameOfArea + " ] " + "关闭 Statement");
        }
        //关闭 Connection
        if (connUserDB != null) {
            try {
                connUserDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            StdOut.println("[ " + nameOfArea + " ] " + "关闭 Connection");
        }
    }

    public void printInfo() {
        String SumOfDevice = getDeviceSumInfo(stmtCampusbaseDB, sqlDeviceSum);
        StdOut.printf("%-50s%6s\n","[" + nameOfArea + "] the sum of all device is : ", SumOfDevice);

        String sumOfDeviceWarn = getDeviceSumInfo(stmtCampusbaseDB, sqlDeviceWarnSum);
        StdOut.printf("%-50s%6s\n","[" + nameOfArea + "] the sum of warn device is : ", sumOfDeviceWarn);

        String sumOfDeviceOffline = getDeviceSumInfo(stmtCampusbaseDB, sqlDeviceOfflineSum);
        StdOut.printf("%-50s%6s\n","[" + nameOfArea + "] the sum of offline device is : ", sumOfDeviceOffline);

        String DeviceUnregisterSum = getDeviceSumInfo(stmtCampusbaseDB, sqlDeviceUnRegisterSum);
        StdOut.printf("%-50s%6s\n","[" + nameOfArea + "] the sum of unregister device is : ", DeviceUnregisterSum);

        String SumOfDeviceNormal = getDeviceSumInfo(stmtCampusbaseDB, sqlDeviceNormalSum);
        StdOut.printf("%-50s%6s\n","[" + nameOfArea + "] the sum of normal device is : ", SumOfDeviceNormal);

        String SumOfTenantNormal = getDeviceSumInfo(stmtUserDB, sqlTenantSum);
        StdOut.printf("%-50s%6s\n","[" + nameOfArea + "] the sum of tenant is : ", SumOfTenantNormal);

        String SumOfMSPNormal = getDeviceSumInfo(stmtUserDB,sqlMSPSum);
        StdOut.printf("%-50s%6s\n","[" + nameOfArea + "] the sum of msp is : ", SumOfMSPNormal);
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

    public static void main(String[] args) throws SQLException {
//        GaussDbUtil HK = new GaussDbUtil("HK");
//        HK.printInfo();
//        HK.closeAll();
        GaussDbUtil Europe = new GaussDbUtil("Europe");
        Europe.printInfo();
        Europe.closeAll();
    }
}