package main.java.org.lxx.main;

import java.io.*;

import static main.java.org.lxx.util.HtmlUtils.*;


/**
 * 需要解决的问题：
 * 1.单线程下载和处理速度很慢
 * 2.程序的鲁棒性很差，没有考虑失败的情况
 * 3.只考虑了url有序且顺序的情况
 * */


public class NovelSpider {


    //文件读取地址头
    private static String FILE_READ_BASE = "C:\\data\\200650\\read_";
    //txt文件保存地址头
    private static String FILE_SAVE_BASE = "C:\\data\\txt\\";
    private static String FILE_SAVE_BASE2 = "C:\\data\\slices\\";
    //网页url
    private static String url = "https://www.xiashuwu.com/200650/read_";
    private static String BASE_URL = "https://www.xiashuwu.com/216057/read_";

    //文本内容两端字符串
    private static String end1 = "</script></div>";
    private static String end2 = "<div class=\"info bottominfo\">";









    public static void main(String[] args) throws IOException {

        String content = "";
        String result = "";
        String fileSavePath = "";
        String fileReadPath = "";
        String url_1 ="https://www.xiashuwu.com/39401/read_1050.html";
        String url_2 = "https://www.xiashuwu.com/55525/read_2219.html";



        /*String urlBase = "https://www.xiashuwu.com/216057/read_";
        String basePath = "C:\\data\\216057\\";
        int titleOffset1 = 0;
        int titleOffset2 = 0;
        int contentOffset1 = 0;
        int contentOffset2 = 0;
        int num =754;*/


        //https://www.xiashuwu.com/200542/read_821.html
        //https://www.xiashuwu.com/38294/read_1224.html
        //https://www.xiashuwu.com/112156/read_1131.html
        //https://www.xiashuwu.com/203226/read_757.html
        //String urlBase = "https://www.xiashuwu.com/55525/read_";
        //String urlBase = "https://www.xiashuwu.com/227521/read_";
        String urlBase = "https://www.xiashuwu.com/200542/read_";
        String basePath = "C:\\data\\200542\\";
        String mergePath = "C:\\data\\merge.txt";
        String saveFilePath = "C:\\data\\200542\\txt\\";

        int titleOffset1 = 0;
        int titleOffset2 = 0;
        int contentOffset1 = 0;
        int contentOffset2 = 0;
        int num =821;

        //保存url数组
        String[] source = new String[num];
        int index;

        //获取url数组
        for(int i=0;i<num;i++)
        {
            index = i+1;
            source[i] = urlBase + index +".html";
        }
        //下载网页并获取txt文件
        try{
            getSectionsTxt(source,basePath,end1,end2,titleOffset1,titleOffset2,contentOffset1,contentOffset2);
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        //将txt文件合并
        String[] source2 = new String[num];
        index = 0;

        //获取章节正文文件路径数组
        for(int i=0;i<num;i++)
        {
            index = i+1;
            source2[i] = saveFilePath + index;
        }

        try{
            //合并章节内容
            mergeTxt(source2,mergePath);
        }catch (Exception e)
        {
            e.printStackTrace();
        }










        /*String[] source = new String[1516];
        int index = 0;
        String mergePath = "C:\\data\\merge.txt";
        //获取章节正文文件路径数组
        for(int i=0;i<1516;i++)
        {
            index = i+1;
            source[i] = FILE_SAVE_BASE2 + index;
        }

        try{
            //合并章节内容
            mergeTxt(source,mergePath);
        }catch (Exception e)
        {
            e.printStackTrace();
        }*/
















    }







     /* //去除正文中的<br/>，同时给正文加上标题
        String title = "";
        int i,num;
        num =1516 ;
        for(i=1;i<=num;i++)
        {
            try{
                //读取包含<br/>的文本文件
                content = fileRead(FILE_SAVE_BASE+i);
                //从网页中获取标题
                title = getTitle(FILE_READ_BASE+i);
                content = title + "\n" + content;
                content = content.replaceAll("<br/>","\n");
                //将正文保存到文件中去
                File file = new File(FILE_SAVE_BASE2+i);
                if(!file.exists())
                    file.createNewFile();
                FileOutputStream fos = new FileOutputStream(FILE_SAVE_BASE2+i);
                fos.write(content.getBytes());
                fos.flush();
                fos.close();
                //System.out.println(content);
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }*/



        /*int num = 1516;
        for(int i=1;i<=num;i++)
        {
            fileReadPath = FILE_READ_BASE+i;
            fileSavePath = FILE_SAVE_BASE+i;
            //从网页中获取文本内容
            try
            {
                //将网页文件读取到字符串
                content = fileRead(fileReadPath);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            //从网页字符串中抽取正文
            result = getContentTxt(content,end1,end2);
            //将正文保存到文件中去
            File file = new File(fileSavePath);
            if(!file.exists())
                file.createNewFile();
            FileOutputStream fos = new FileOutputStream(fileSavePath);
            fos.write(result.getBytes());
            fos.flush();
            fos.close();
        }*/




    //下载html网页的代码
    /*String final_url = "";
        int num = 1;
        int i;
        for(i=1;i<=num;i++)
        {
            //final_url = BASE_URL+i+".html";
            final_url = url+i+".html";
            downloadPage(final_url,filePath);
        }*/




}
