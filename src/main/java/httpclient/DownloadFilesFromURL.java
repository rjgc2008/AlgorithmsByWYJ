package httpclient;

import edu.princeton.cs.algs4.StdOut;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.net.ssl.*;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class DownloadFilesFromURL {
    String BaseURL_String = null;
    String encoding_String = null;
    String tmpFilenameOfDirectory = null;

    //构造函数，默认是香港公有云的HFS
    public DownloadFilesFromURL() {
        //临时文件名称赋值
        tmpFilenameOfDirectory = "tmp.html";

        //请求URL连接
        BaseURL_String = "https://naas-intl.huaweicloud.com:11125/";
        //加密用户名&密码
        try {
            encoding_String = DatatypeConverter.printBase64Binary("wyj:Huawei@123".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    //构造函数，其它下载网址
    public DownloadFilesFromURL(String url, String usernamePassword) {
        //请求URL连接
        BaseURL_String = url;
        //加密用户名&密码
        try {
            encoding_String = DatatypeConverter.printBase64Binary("wyj:Huawei@123".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载某网页并生成html文件
     *
     * @param filename
     * @param url
     * @return
     */
    public String downloadFile(String url, String filename) {
        try {
            /*
             *  fix for
             *    Exception in thread "main" javax.net.ssl.SSLHandshakeException:
             *       sun.security.validator.ValidatorException:
             *           PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException:
             *               unable to find valid certification path to requested target
             */
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }

                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

            URL Base_URL = new URL(url);
//            Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("proxyhk.huawei.com",8080));
            HttpURLConnection connection_HttpURLConnection = (HttpURLConnection) Base_URL.openConnection();

            //设置请求参数
            connection_HttpURLConnection.setRequestMethod("GET");
            connection_HttpURLConnection.setDoOutput(true);
            connection_HttpURLConnection.setRequestProperty("Authorization", "Basic " + encoding_String);
            //请求URL并写入文件
            InputStream content_InputStream = connection_HttpURLConnection.getInputStream();
            BufferedReader in_BufferedReader = new BufferedReader(new InputStreamReader(content_InputStream));
//            FileUtils.writeStringToFile(new File(".\\" + filename), IOUtils.toString(in_BufferedReader), "UTF-8");

            //将读取的文件写到本地
            FileOutputStream out_FileOutputStream = new FileOutputStream(".\\" + filename);
            byte[] b = new byte[102400];
            int count = -1;
            while ((count = content_InputStream.read(b)) >= 0) {
                out_FileOutputStream.write(b, 0, count);
            }

            //关闭输入、输出流
            out_FileOutputStream.flush();
            out_FileOutputStream.close();
            content_InputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } finally {
        }
        return filename;
    }

    /**
     * 批量下载文件
     *
     * @param filenameOfDirectory
     */
    public void downloadFiles(String filenameOfDirectory) {
        try {
            //读取文件并即将进行解析
            File tmpFile = new File(".\\" + filenameOfDirectory);
            Document doc = Jsoup.parse(tmpFile, "UTF-8", "");

            //读取下载链接并循环下载
            Elements links = doc.select("a");
            for (int i = 1; i < links.size(); i++) {
                String URLStr = null;
                URLStr = BaseURL_String + links.get(i).attr("href");
                String FileName = links.get(i).text();

                //下载文件
                downloadFile(URLStr, FileName);
                //删除文件
                tmpFile.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DownloadFilesFromURL downloadFilesFromURL = new DownloadFilesFromURL();
        //下载目录页
        downloadFilesFromURL.downloadFile(downloadFilesFromURL.BaseURL_String, downloadFilesFromURL.tmpFilenameOfDirectory);

        //下载目录页的链接
        downloadFilesFromURL.downloadFiles(downloadFilesFromURL.tmpFilenameOfDirectory);
    }
}