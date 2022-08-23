package derby;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DerbyTestDatabase {

    public static final String CSdriver = new String("org.apache.derby.jdbc.EmbeddedDriver");
    public static final String dbURLCS = new String("jdbc:derby:102Derby\\toursdb;create=true");

    public static void main(String[] args) throws Exception {

        System.out.println("Loading the Derby jdbc driver...");
        Class<?> clazz = Class.forName(CSdriver);
        clazz.getConstructor().newInstance();

        System.out.println("Getting Derby database connection...");
        Connection connCS = DriverManager.getConnection(dbURLCS);
        System.out.println("Successfully got the Derby database connection...");

        System.out.println("Inserted " + insertRows(null, connCS) +
                " rows into the ToursDB");

        connCS.close();

        // Shut down the database cleanly before exiting.
        try {
            DriverManager.getConnection(dbURLCS + ";shutdown=true");
        } catch (SQLException sqle) {
            // Database shutdown is expected to raise SQLState 08006.
            // Report any other exception.
            if (!"08006".equals(sqle.getSQLState())) {
                throw sqle;
            }
        }
    }

    public static int insertRows(String path, Connection conn)
            throws SQLException, FileNotFoundException, IOException {
        PreparedStatement ps = null;

        ps = conn.prepareStatement
                ("insert into maps (map_name, region, area, photo_format, picture) values (?,?,?,?,?)");

        ps.setString(1,"North Ocean");
        ps.setString(2,"Cup Island");
        ps.setBigDecimal(3, new BigDecimal("1776.11"));
        ps.setString(4,"gif");

        String fileName;
        if (path == null)
            fileName=".\\src\\derby\\cupisle.gif";
        else
            fileName=path + File.separator + "cupisle.gif";
        File file = new File (fileName);
        InputStream fileIn = new FileInputStream(file);
        ps.setBinaryStream(5, fileIn, (int)file.length());
        int numrows = ps.executeUpdate();
        fileIn.close();

        ps.setString(1,"Middle Ocean");
        ps.setString(2,"Small Island");
        ps.setBigDecimal(3, new BigDecimal("1166.77"));
        ps.setString(4,"gif");
        if (path == null)
            fileName=".\\src\\derby\\smallisle.gif";
        else
            fileName=path + File.separator + "smallisle.gif";
        file = new File (fileName);
        fileIn = new FileInputStream(file);
        ps.setBinaryStream(5, fileIn, (int)file.length());
        numrows = numrows + ps.executeUpdate();
        fileIn.close();

        ps.setString(1,"South Ocean");
        ps.setString(2,"Witch Island");
        ps.setBigDecimal(3, new BigDecimal("9117.90"));
        ps.setString(4,"gif");
        if (path == null)
            fileName=".\\src\\derby\\witchisle.gif";
        else
            fileName=path + File.separator + "witchisle.gif";
        file = new File (fileName);
        fileIn = new FileInputStream(file);
        ps.setBinaryStream(5, fileIn, (int)file.length());
        numrows = numrows + ps.executeUpdate();

        fileIn.close();
        ps.close();

        return numrows;
    }

}