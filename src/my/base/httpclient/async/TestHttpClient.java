package my.base.httpclient.async;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Package: my.base.httpclient.async<br>
 * @ClassName: TestHttpClient.java<br>
 * @Description: TODO
 * @author: gaoxinxing
 */
public class TestHttpClient {
    public static void main(String[] args){

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(50000)
                .setSocketTimeout(50000)
                .setConnectionRequestTimeout(10)//设置为10ms
                .build();

        //配置io线程
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom().
                setIoThreadCount(Runtime.getRuntime().availableProcessors())
                .setSoKeepAlive(true)
                .build();
        //设置连接池大小
        ConnectingIOReactor ioReactor=null;
        try {
            ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        } catch (IOReactorException e) {
            e.printStackTrace();
        }
        PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor);
        connManager.setMaxTotal(2);//最大连接数设置1
        connManager.setDefaultMaxPerRoute(2);//per route最大连接数设置1


        final CloseableHttpAsyncClient client = HttpAsyncClients.custom().
                setConnectionManager(connManager)
                .setDefaultRequestConfig(requestConfig)
                .build();


        //构造请求
        String url = "http://127.0.0.1:8089/logger/hello/human";
        List<HttpPost> list = new ArrayList<HttpPost>();
        for(int i=0;i<2;i++){
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = null;
            try {
                String a = //"{ \"id\": { \"_index\": \"test\", \"_type\": \"test\"} }\n" +
                        "{\"name\": \"上海\",\"age\":33}\n";
                entity = new StringEntity(a);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            httpPost.setEntity(entity);
            list.add(httpPost);
        }

        client.start();

        for(int i=0;i<2;i++){
            client.execute(list.get(i), new Back());
        }

        while(true){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Back implements FutureCallback<HttpResponse> {

        private long start = System.currentTimeMillis();
        Back(){
        }

        @Override
        public void completed(HttpResponse httpResponse) {
            try {
                System.out.println("cost is:"+(System.currentTimeMillis()-start)+":"+ EntityUtils.toString(httpResponse.getEntity()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void failed(Exception e) {
            e.printStackTrace();
            System.err.println(" cost is:"+(System.currentTimeMillis()-start)+":"+e);
        }

        @Override
        public void cancelled() {

        }
    }
}
