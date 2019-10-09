package main.java.org.lxx.download;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.InputStream;

import static main.java.org.lxx.util.HtmlUtils.getFileName;
import static main.java.org.lxx.util.HtmlUtils.saveToLocal;


public class HtmlDownloader implements Runnable{

    private HttpClient httpClient = null;
    private String url = null;
    private String filePath = null;

    public HtmlDownloader(String url, String filePath)
    {
        this.url = url;
        this.filePath = filePath;
    }

    @Override
    public void run()
    {
        httpClient = HttpClientBuilder.create().build();
        try{
            //System.out.println(url);
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream in = entity.getContent();
            String fileName = getFileName(url);
            //保存到本地
            saveToLocal(in, filePath, fileName);
            System.out.println(url+"已下载");
            Thread.sleep(100);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }






    }

}
