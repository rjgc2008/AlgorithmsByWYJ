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
            InputStream content_InputStream = connection_HttpURLConnection.getInputStream();
            BufferedReader in_BufferedReader = new BufferedReader(new InputStreamReader(content_InputStream));
//            FileUtils.writeStringToFile(new File(".\\src\\main\\java\\httpclient\\tmp.html"), IOUtils.toString(in_BufferedReader), "UTF-8");
            FileUtils.writeStringToFile(new File(".\\tmp.html"), IOUtils.toString(in_BufferedReader), "UTF-8");

            File file = new File(".\\tmp.html");
            Document doc = Jsoup.parse(file, "UTF-8", "");

            Elements links = doc.select("a");
            for (int i = 1; i < links.size(); i++) {
//                StdOut.printf("%d: href = %s\n", i, links.get(i).attr("href"));
//                StdOut.printf("%d: text = %s\n", i, links.get(i).text());s
                String URLStr = null;
                URLStr = BaseURL_String + links.get(i).attr("href");
                StdOut.printf("download link：%s\n", URLStr);
                Base_URL = new URL(URLStr);
                String FileName = links.get(i).text();
//                String FileName = new String(URLEncoder.encode(links.get(i).text(),"UTF-8"));
//                StdOut.printf("filename is(default) %s\n",FileName);
//                StdOut.printf("filename is(UTF-8) %s\n",new String(URLEncoder.encode(links.get(i).text(),"UTF-8").getBytes("UTF-8")),"UTF-8");
//                StdOut.printf("filename is(系统编码) %s\n",new String(URLEncoder.encode(links.get(i).text(),"GBK").getBytes()));
//                StdOut.printf("我 默认: %s\n",new String("我".getBytes()));
//                StdOut.printf("我 UTF-8:%s\n",new String("我".getBytes("UTF-8")));
//                StdOut.printf("我 GBK:%s\n",new String("我".getBytes("GBK")));
//                StdOut.printf("系统编码：%s\n",System.getProperty("file.encoding"));

//                StdOut.printf("filename is %s\n",new String(FileName.getBytes("UTF-8"),"ISO8859-1"));

                connection_HttpURLConnection = (HttpURLConnection) Base_URL.openConnection();
                connection_HttpURLConnection.setRequestMethod("GET");
                connection_HttpURLConnection.setDoOutput(true);
                connection_HttpURLConnection.setRequestProperty("Authorization", "Basic " + encoding_String);
                content_InputStream = connection_HttpURLConnection.getInputStream();
//            in = new BufferedReader(new InputStreamReader(content));
//            FileUtils.writeStringToFile(new File(".\\src\\main\\java\\httpclient\\" + FileName), IOUtils.toString(in), "UTF-8");
//                FileName = new String(FileName.getBytes("UTF-8"),"ISO8859-1");
                FileOutputStream out_FileOutputStream = new FileOutputStream(".\\" + FileName);
//                FileOutputStream out_FileOutputStream = new FileOutputStream(".\\" + FileName);
//                FileOutputStream out_FileOutputStream = new FileOutputStream(".\\src\\main\\java\\httpclient\\" + FileName);
                byte[] b = new byte[1024];
                int count = -1;
                while ((count = content_InputStream.read(b)) >= 0) {
                    out_FileOutputStream.write(b, 0, count);
                }
                out_FileOutputStream.flush();
                out_FileOutputStream.close();
                content_InputStream.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
