package main.java.org.lxx.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtils {

    /**
     * 根据url下载网页并保存到对应路径下的方法
     * @param url 网页url
     * @param filePath 下载网页保存的路径
     * @throws IOException
     */
    public static void downloadPage(String url, String filePath) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        InputStream in = entity.getContent();
        String fileName = getFileName(url);
        //保存到本地
        saveToLocal(in, filePath, fileName);
        client.close();
    }

    public static void downloadHtml(String url) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);
        System.out.println(response.toString());
        //JSON.toJSONString(response);
        HttpEntity entity = response.getEntity();
        //System.out.println(entity.getContent().toString());
        //JSON.toJSONString(entity);

        client.close();
    }




    /**
     * 根据网页文件的url给网页文件命名的方法，截取网页url中的序列号作为文件名
     * @param url 网页的url
     * @return
     */
    public static String getFileName(String url)
    {
        //System.out.println(url);
        String fileName = "";
        String[] slices = url.split("/");
        String segment1 = slices[slices.length-1];
        ;
        //这里的符号"."在匹配时要用"\\."
        String[] segments = segment1.split("\\.");
        if(segments.length>0)
        {
            fileName = segments[0];
            //System.out.println(fileName);
        }
        else
        {
            System.out.println("生成文件名出错");
        }
        //System.out.println(fileName);
        return fileName;
    }

    /**
     * 将数据流保存到本地地址的方法
     * @param in 输入流
     * @param filePath 文件保存路径
     * @param filename 文件名
     * @throws IOException
     */
    public static void saveToLocal(InputStream in, String filePath, String filename) throws IOException {
        //System.out.println("文件："+filename+"已保存到"+filePath);


        File file = new File(filePath);
        if(!file.exists())
            file.mkdirs();
        DataOutputStream out = new DataOutputStream(new FileOutputStream(
                new File(filePath + filename)));
        int result;
        while((result=in.read())!=-1){
            out.write(result);
        }
        out.flush();
        out.close();
    }

    /**
     * 读取文件，返回文件内容字符串
     * @param filePath 文件路径
     * @return
     * @throws Exception
     */
    public static String fileRead(String filePath) throws Exception {
        File file = new File(filePath);//定义一个file对象，用来初始化FileReader
        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
        String s = "";
        while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
            sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
            //System.out.println(s);
        }
        bReader.close();
        String str = sb.toString();

        //System.out.println(str );
        return str;
    }

    /**
     * 从字符串中匹配适配的部分
     * @param content 文本字符串
     * @param regex 匹配模式串
     * @return 匹配合适内容开始和结束的位置
     */
    public static int[] match(String content, String regex)
    {

        //System.out.println(regex);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        int[] index = {0,0} ;
        //检查所有的出现
        while(matcher.find())
        {
            //System.out.println("开始位置："+matcher.start());
            index[0] = matcher.start();
            //System.out.println("结束位置："+matcher.end() + "");
            index[1] = matcher.end();
            //System.out.println(matcher.group());
        }
        return  index;
    }

    /**
     * 从文本内容中抽取出正文
     * @param content
     * @param end1 正文开始前的字符串
     * @param end2 正文结束后的字符串
     * @param contentOffset1 用来截取正文，去掉正文开头多余的部分
     * @param contentOffset2 用来截取正文，去掉正文末尾多余的部分
     * @return 正文内容
     */
    public static String getContentTxt(String content, String end1, String end2,int contentOffset1, int contentOffset2)
    {
        /*String regex1 = end1;
        String regex2 = end2;*/
        int beginIndex,endIndex;
        int[] index1,index2;
        index1 = match(content,end1);
        beginIndex = index1[1];
        index2 = match(content,end2);
        endIndex = index2[0];

        //String result = content.substring(beginIndex+29,endIndex-90);
        //两个偏移值为29 90
        String result = content.substring(beginIndex+contentOffset1,endIndex-contentOffset2);
        //System.out.println(result);

        return  result;

    }

    /**
     * 从html中获取title值，并去除title中多余的字
     * @param filePath 文件路径
     * @param titleOffset1 用来截取标题，去掉title开头多余的字
     * @param titleOffset2  用来截取标题，去掉title末尾多余的字
     * @return html文件的标题
     * @throws Exception
     */
    public static String getTitle(String filePath,int titleOffset1,int titleOffset2) throws Exception
    {
        String title = "";
        String content = "";
        String end1,end2;
        end1 = "<title>";
        end2 = "</title>";
        //读取网页内容
        content = fileRead(filePath);
        int beginIndex,endIndex;
        int[] index1,index2;
        //匹配标题，获取标题位置
        index1 = match(content,end1);
        beginIndex = index1[1];
        index2 = match(content,end2);
        endIndex = index2[0];
        //根据标题位置获取标题内容，-offset2表示去除 “,娱乐之奶爸的彪悍人生 大海中文_下书网”这一后缀
        title = content.substring(beginIndex+titleOffset1,endIndex-titleOffset2);
        return title;
    }


    /**
     * 将source[]数组中保存的路径的对应文件合并并保存到mergeFilePath中
     * @param sourcePath 保存文件路径的数字
     * @param mergeFilePath 合并文件保存路径
     */
    public static void mergeTxt(String sourcePath[], String mergeFilePath) throws Exception
    {
        File file = new File(mergeFilePath);
        String content = "";
        if(!file.exists())
            file.createNewFile();
        //将source中对应文件的内容写入目标文件
        FileOutputStream fos = new FileOutputStream(mergeFilePath);
        for(int i=0;i<sourcePath.length;i++)
        {
            content = fileRead(sourcePath[i]);
            fos.write(content.getBytes());
            fos.flush();
        }
        fos.flush();
        fos.close();

    }


    /**
     * 根据url数组获取对应网页的章节，网页url中的序号必须从1开始且连续，否则会报错
     * @param urls
     * @param basePath
     * @param end1  定位正文前的字符串
     * @param end2  定位正文后的字符串
     * @param titleOffset1 去除标题开头多余字符数
     * @param titleOffset2 去除标题末尾多余字符数
     * @param contentOffset1 去除正文开头多余字符数
     * @param contentOffset2 去除正文末尾多余字符数
     * @throws Exception
     */
    public static void getSectionsTxt(String[] urls, String basePath,String end1,String end2,int titleOffset1, int titleOffset2, int contentOffset1, int contentOffset2) throws Exception
    {
        //保存网页的路径,例如basePath="C:\\data\\200650\\"
        String htmlPath = basePath + "html\\";
        //保存txt文件的路径
        String txtPath = basePath + "txt";


        int i,num;
        num = urls.length;

        //下载网页
        System.out.println("num:"+num);
        for(i=0;i<num;i++)
        {
            System.out.println(urls[i]+"  "+htmlPath);
            downloadPage(urls[i],htmlPath);
        }


        //抽取txt文件
        String content,htmlFilePath,title,txtFilePath,result;
        for(i=1;i<=num;i++)
        {
            //html文件保存路径
            htmlFilePath = htmlPath+"read_"+i;
            //txt文件保存路径
            txtFilePath = txtPath+"\\"+i ;
            content = fileRead(htmlFilePath);
            title = getTitle(htmlFilePath,titleOffset1,titleOffset2);
            //end1和end2分别是正文前面和后面的字符串，用来定位正文
            content = getContentTxt(content,end1,end2,contentOffset1,contentOffset2);
            //去掉正文中的<br/>、</div>
            content = title + "\n" + content;
            content = content.replaceAll("<br/>","\n").replaceAll("</div>","\n");
            //将正文保存到txt文件中
            File txtFile = new File(txtFilePath);
            if(!txtFile.exists())
                txtFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(txtFilePath);
            fos.write(content.getBytes());
            fos.flush();
            fos.close();
            //System.out.println(content);
        }
    }





}
