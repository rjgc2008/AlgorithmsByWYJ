package chapterone;
import edu.princeton.cs.algs4.StdOut;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class GaussDbUtil {
    private static Connection conn = null;

    // 建数据库连接。
    static {
        String ip = "172.16.2.23";
        String driver = "com.huawei.gauss.jdbc.ZenithDriver";
        String sourceURL = "jdbc:zenith:@" + ip + ":32082";
        String username = "campusbasedb";
        String passwd = "Changeme_123";
        try {
            // 加载数据库驱动。
            Class.forName(driver);
            // 创建数据库连接。
            conn = DriverManager.getConnection(sourceURL, username, passwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询数据
     */
    public static String gaussQuery(String sql) {
        ResultSet rs = null;
        Statement stmt = null;
        String resultStr = null;
        try {
            stmt = conn.createStatement();
            // 执行普通SQL语句。
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                resultStr = rs.getString("SUM");
//                StdOut.println("ID = " + resultStr);
            }
            return resultStr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    StdOut.println("关闭 ResultSet");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    StdOut.println("关闭 Statement");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                    StdOut.println("关闭 Connection");
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
        String sql = "SELECT count(*) AS sum FROM CAMPUSBASEDB.T_CAMPUS_DEVICEMGR_DEVICE";
        String result = gaussQuery(sql);
        System.out.println("设备总数是：" + result);
    }
}