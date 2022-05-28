package httpclient;

import edu.princeton.cs.algs4.StdOut;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLTest2 {
    public static void main(String[] args) {
        //请求URL连接
        String BaseURL_String = "https://naas-intl.huaweicloud.com:11125/";

        try {
            //加密用户名&密码
            String encoding_String = DatatypeConverter.printBase64Binary("wyj:Huawei@123".getBytes("UTF-8"));

            URL Base_URL = new URL(BaseURL_String);
            HttpURLConnection connection_HttpURLConnection = (HttpURLConnection) Base_URL.openConnection();

            //设置请求参数
            connection_HttpURLConnection.setRequestMethod("GET");
            connection_HttpURLConnection.setDoOutput(true);
            connection_HttpURLConnection.setRequestProperty("Authorization", "Basic " + encoding_String);
            //请求URL并写入文件
            InputStream content_InputStream = connection_HttpURLConnection.getInputStream();
            BufferedReader in_BufferedReader = new BufferedReader(new InputStreamReader(content_InputStream));
            FileUtils.writeStringToFile(new File(".\\tmp.html"), IOUtils.toString(in_BufferedReader), "UTF-8");

            //读取文件并即将进行解析
            File tmpFile = new File(".\\tmp.html");
            Document doc = Jsoup.parse(tmpFile, "UTF-8", "");

            //读取下载链接并循环下载
            Elements links = doc.select("a");
            for (int i = 1; i < links.size(); i++) {
                String URLStr = null;
                URLStr = BaseURL_String + links.get(i).attr("href");
                Base_URL = new URL(URLStr);
                String FileName = links.get(i).text();
                connection_HttpURLConnection = (HttpURLConnection) Base_URL.openConnection();
                connection_HttpURLConnection.setRequestMethod("GET");
                connection_HttpURLConnection.setDoOutput(true);
                connection_HttpURLConnection.setRequestProperty("Authorization", "Basic " + encoding_String);
                content_InputStream = connection_HttpURLConnection.getInputStream();

                //将读取的文件写到本地
                FileOutputStream out_FileOutputStream = new FileOutputStream(".\\" + FileName);
                byte[] b = new byte[1024];
                int count = -1;
                while ((count = content_InputStream.read(b)) >= 0) {
                    out_FileOutputStream.write(b, 0, count);
                }

                //关闭输入、输出流
                out_FileOutputStream.flush();
                out_FileOutputStream.close();
                content_InputStream.close();

                //删除文件
                tmpFile.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
