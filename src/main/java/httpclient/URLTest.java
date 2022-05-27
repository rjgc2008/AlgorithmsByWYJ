package httpclient;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLTest {
    public static void main(String[] args) {
//		String https2="https://www.apiopen.top/journalismApi";
        String https="https://www.baidu.com";
        InputStream in=null;
        try {
            URL url=new URL(https);
            HttpsURLConnection openConnection = (HttpsURLConnection) url.openConnection();
            String protocol = url.getProtocol();
            System.out.println(protocol);
            openConnection.connect();
            in = openConnection.getInputStream();

            StringBuilder builder=new StringBuilder();
            BufferedReader bufreader =new BufferedReader(new InputStreamReader(in));
            for (String temp=bufreader.readLine();temp!=null;temp= bufreader.readLine()) {
                builder.append(temp);
            }
            System.out.println(builder);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
