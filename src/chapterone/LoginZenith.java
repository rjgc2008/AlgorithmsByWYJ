package chapterone;

import edu.princeton.cs.algs4.StdOut;

import java.sql.Connection;
import java.sql.DriverManager;

public class LoginZenith {
    public static void main(String[] args){
        String driver = "com.huawei.gauss.jdbc.ZenithDriver";
        String sourceURL = "jdbc:zenith:@172.16.2.23:32082";
        String username = "campusbasedb";
        String passwd = "Changeme_123";

        Connection conn = null;
        try {
            // 加载数据库驱动。
//            Class.forName(driver).getDeclaredConstructor().newInstance();
            Class.forName(driver);
            // 创建数据库连接。
            conn = DriverManager.getConnection(sourceURL, username, passwd);
            StdOut.println("sourceURL:  " + sourceURL);
            StdOut.println("username:  " + username);
            StdOut.println("passwd:  " + passwd);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
