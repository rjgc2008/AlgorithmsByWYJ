package httpclient;

import edu.princeton.cs.algs4.StdOut;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class URLTest2 {
    public static void main(String[] args) {
        String BaseURL_String = "https://naas-intl.huaweicloud.com:11125/";

        try {
            URL Base_URL = new URL(BaseURL_String);
            String encoding_String = DatatypeConverter.printBase64Binary("wyj:Huawei@123".getBytes("UTF-8"));
            HttpURLConnection connection_HttpURLConnection = (HttpURLConnection) Base_URL.openConnection();
            connection_HttpURLConnection.setRequestMethod("GET");
            connection_HttpURLConnection.setDoOutput(true);
            connection_HttpURLConnection.setRequestProperty("Authorization", "Basic " + encoding_String);
            InputStream contentInputStream = connection_HttpURLConnection.getInputStream();
            FileUtils.writeStringToFile(new File(".\\src\\main\\java\\httpclient\\tmp.html"), IOUtils.toString(contentInputStream), "UTF-8");
//            BufferedReader in = new BufferedReader(new InputStreamReader(contentInputStream));
//            String line;
//            while ((line = in.readLine()) != null) {
//                StdOut.println(line);
//            }

            File file = new File(".\\src\\main\\java\\httpclient\\tmp.html");
            Document doc = Jsoup.parse(file, "UTF-8", "");

            Elements links = doc.select("a");
            for (int i = 1; i < links.size(); i++) {
                StdOut.printf("%d: href = %s\n", i, links.get(i).attr("href"));
//                StdOut.printf("%d: text = %s\n", i, links.get(i).text());
                String URLStr = null;
                URLStr = BaseURL_String + links.get(i).attr("href");
                StdOut.printf("链接：%s",URLStr);
                Base_URL = new URL(URLStr);
                String FileName = links.get(i).text();
                connection_HttpURLConnection = (HttpURLConnection) Base_URL.openConnection();
                connection_HttpURLConnection.setRequestMethod("GET");
                connection_HttpURLConnection.setDoOutput(true);
                connection_HttpURLConnection.setRequestProperty("Authorization", "Basic " + encoding_String);
                contentInputStream = connection_HttpURLConnection.getInputStream();
//            in = new BufferedReader(new InputStreamReader(content));
//            FileUtils.writeStringToFile(new File(".\\src\\main\\java\\httpclient\\" + FileName), IOUtils.toString(in), "UTF-8");
                FileOutputStream outFileOutputStream = new FileOutputStream(".\\src\\main\\java\\httpclient\\" + FileName);
                byte[] b = new byte[1024];
                int count = -1;
                while ((count = contentInputStream.read(b)) >= 0) {
                    outFileOutputStream.write(b, 0, count);
                }
                outFileOutputStream.flush();
                outFileOutputStream.close();
                contentInputStream.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
