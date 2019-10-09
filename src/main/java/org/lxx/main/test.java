package main.java.org.lxx.main;

import main.java.org.lxx.download.HtmlDownloader;
import main.java.org.lxx.util.HtmlUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class test {

    public static void  main(String[] args)
    {
        //System.out.println("测试开始");
        String filePath = "C:\\data\\html\\";
        //要下载的网页网址
        String[] urls1 = new String[100];
        //要下载网页的网址公共部分
        String baseURL = "https://www.xiashuwu.com/200542/read_";
        HtmlDownloader htmlDownloader;

        for(int i=0;i<100;i++)
        {
            urls1[i] = baseURL + (i+1)+".html";
        }

        //Thread thread1 = new Thread(htmlDownloader1);
        //thread1.start();

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(8, Integer.MAX_VALUE,
                500, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(20),
                new ThreadPoolExecutor.AbortPolicy());
        for(int i=0;i<100;i++)
        {
            htmlDownloader = new HtmlDownloader(urls1[i],filePath);
            threadPool.submit(htmlDownloader);
        }

        threadPool.shutdown();






    }


}
