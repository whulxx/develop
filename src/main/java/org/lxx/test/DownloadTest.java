package main.java.org.lxx.test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class DownloadTest {
    String json = "{n\r\n"
            + "	\"http://www.xxx.com/111/123.mp4\":\"myFile/111/123.mp4\",\r\n"
            + "	\"http://www.xxx.com/111/124.mp4\":\"myFile/111/124.mp4\",\r\n"
            + "	\"http://www.xxx.com/111/125.mp4\":\"myFile/111/125.mp4\"\r\n"
            + "}";

    @SuppressWarnings("unchecked")

    public void test() {
        Map<String, String> map = new HashMap<>();
        Map<String, String> resMap = JSON.parseObject(json, map.getClass());

        int times = 1;
        for (int index = 0; index < times; index++) {
            DownloadUtil.batch(resMap);
        }
    }
}
