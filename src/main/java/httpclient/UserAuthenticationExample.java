package httpclient;

import edu.princeton.cs.algs4.StdOut;
import org.apache.commons.io.FileUtils;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.Credentials;
import org.apache.hc.client5.http.auth.CredentialsProvider;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.DefaultHttpClientConnectionOperator;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.File;

public class UserAuthenticationExample {
    public static void main(String args[]) throws Exception {

        //Create an object of credentialsProvider
        BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();

        //Set the credentials
        AuthScope scope = new AuthScope("http://113.125.74.234/", 4627);
//        AuthScope scope = new AuthScope("https://naas-intl.huaweicloud.com", 11125);

        Credentials credentials = new UsernamePasswordCredentials("wyj", "Huawei@123".toCharArray());

        basicCredentialsProvider.setCredentials(scope, credentials);

        //Creating the HttpClientBuilder
        HttpClientBuilder clientbuilder = HttpClients.custom();

        //Setting the credentials
        clientbuilder = clientbuilder.setDefaultCredentialsProvider(basicCredentialsProvider);

        //Building the CloseableHttpClient object
        CloseableHttpClient httpclient = clientbuilder.build();

        //Creating a HttpGet object
        HttpGet httpget = new HttpGet("http://113.125.74.234:4627/200WYJ/");
//        HttpGet httpget = new HttpGet("https://naas-intl.huaweicloud.com:11125/");

//        String encoding = DatatypeConverter.printBase64Binary("wyj:Huawei@123".getBytes("UTF-8"));
//        httpget.setHeader("Authorization","Basic " + encoding);


        //Printing the method used
        System.out.println("Request Method:" + httpget.getMethod());

        //Executing the Get request
        CloseableHttpResponse httpresponse = null;
        try {
            // 执行请求
            httpresponse = httpclient.execute(httpget);
            StdOut.println(EntityUtils.toString(httpresponse.getEntity(), "UTF-8"));
            // 判断返回状态是否为200
            if (httpresponse.getCode() == 200) {
                //请求体内容
                String content = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
                //内容写入文件
//                FileUtils.writeStringToFile(new File(".\\baidu.html"), content, "UTF-8");
                System.out.println("内容长度：" + content.length());
                StdOut.println("Content: " + content);
            }
        } finally {
            if (httpresponse != null) {
                httpresponse.close();
            }
            //相当于关闭浏览器
            httpclient.close();
        }


        //Printing the status line
        StdOut.println("Status Code:" + httpresponse.getReasonPhrase());
        int statusCode = httpresponse.getCode();
        System.out.println("Status Code:" + statusCode);
        StdOut.println(httpresponse.getEntity());

//        Header[] headers= httpresponse.getHeaders();
//        for (int i = 0; i<headers.length;i++){
//            System.out.println(headers[i].getName());
//        }

    }


}
