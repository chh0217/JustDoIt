package ch;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * @author 云岩
 * @description
 * @date 2020/10/30 4:48 下午
 */
@Slf4j
public class DownloadTest {
    public static void main(String[] args) {
        String url = "https://img13.360buyimg.com/cms/jfs/t3139/18/3080749147/82748/a62387bc/57eba33bN6cf700dd.jpg";
        for (; ; ) {
            System.out.println(sendGet(url) + "  ==============");
        }
    }

    public static int sendGet(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        //超时时间
        RequestConfig config = RequestConfig.custom().setConnectTimeout(300 * 1000).build();
        HttpResponse hps = null;
        try {
            request.setConfig(config);
            hps = httpclient.execute(request);
        } catch (SocketTimeoutException | ConnectTimeoutException e) {
            return 111;
        } catch (Exception e) {
            return 500;
        } finally {
            try {
                httpclient.close();
            } catch (IOException ioe) {
                log.error("关闭httpClient出错");
            }
        }
        return hps.getStatusLine().getStatusCode();
    }

}
