package httpclient;

import edu.princeton.cs.algs4.StdOut;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.swing.text.Document;
import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class URLTest2 {
    public static void main(String[] args) {

        try {
            URL url = new URL("https://naas-intl.huaweicloud.com:11125");
//            URL url = new URL("http://113.125.74.234:4627//200WYJ");
            String encoding = DatatypeConverter.printBase64Binary("wyj:Huawei@123".getBytes("UTF-8"));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            InputStream content = connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(content));
            FileUtils.writeStringToFile(new File(".\\src\\main\\java\\httpclient\\tmp.html"), IOUtils.toString(in), "UTF-8");
            String line;
            while ((line = in.readLine()) != null) {
                StdOut.println(line);
            }

            File file = new File(".\\src\\main\\java\\httpclient\\tmp.html");
            Document doc = Jsoup

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
