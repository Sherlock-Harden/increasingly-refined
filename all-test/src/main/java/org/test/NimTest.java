package org.test;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

public class NimTest {

  private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
      '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

  public static void main(String[] args) throws Exception {

    for (int i = 0; i < 50; i++) {
      generateToken(i);
    }
  }


  public static void generateToken(int i) throws Exception {

    String url = "https://api.netease.im/nimserver/user/create.action";
    HttpPost httpPost = new HttpPost(url);

    String appKey = "642fd96ce151fdb09328ddf091a73512";
    String appSecret = "9e7e8dd7bf87";
    String nonce = "123456";
    String curTime = String.valueOf((new Date()).getTime() / 1000L);
    String checkSum = getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码

    // 设置请求的header
    httpPost.addHeader("AppKey", appKey);
    httpPost.addHeader("Nonce", nonce);
    httpPost.addHeader("CurTime", curTime);
    httpPost.addHeader("CheckSum", checkSum);
    httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

    // 设置请求的参数
    List<NameValuePair> nvps = new ArrayList<>();
    String accid = UUID.randomUUID().toString();
    nvps.add(new BasicNameValuePair("accid", "hello-im" + i));
    httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

    SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, (x509Certificates, authType) -> true)
        .build();
    //设置协议http和https对应的处理socket链接工厂的对象
    Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
        .register("http", PlainConnectionSocketFactory.INSTANCE)
        .register("https", new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE))
        .build();
    PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
    connManager.setMaxTotal(200);
    connManager.setDefaultMaxPerRoute(100);
    CloseableHttpClient httpClient = HttpClients.custom()
        .setSSLContext(sslContext)
        .setConnectionManager(connManager).build();

    // 执行请求
    HttpResponse response = httpClient.execute(httpPost);

    // 打印执行结果
    System.out.println("token>>>>>>>>>>>>>>>>>" + EntityUtils.toString(response.getEntity(), "utf-8"));


  }

  public static String getCheckSum(String appSecret, String nonce, String curTime) {
    return encode("sha1", appSecret + nonce + curTime);
  }

  private static String encode(String algorithm, String value) {
    if (value == null) {
      return null;
    }
    try {
      MessageDigest messageDigest
          = MessageDigest.getInstance(algorithm);
      messageDigest.update(value.getBytes("UTF-8"));
      return getFormattedText(messageDigest.digest());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private static String getFormattedText(byte[] bytes) {
    int len = bytes.length;
    StringBuilder buf = new StringBuilder(len * 2);
    for (int j = 0; j < len; j++) {
      buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
      buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
    }
    return buf.toString();
  }
}
