package main.java.org.lxx.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBase {
    private String url;
    private String user;
    private String password;
    private Connection connection;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    //构造方法，这这里建立数据库连接
    public DataBase(String url, String user, String password) throws Exception
    {
        this.url = url;
        this.user = user;
        this.password = password;
        connection = DriverManager.getConnection(url,user,password);
    }

    /**
     * 向数据表page中插入数据的方法
     * @param tag
     * @param url
     * @param seq
     * @param html
     * @return
     * @throws Exception
     */
    public Boolean insertPage(String tag, String url, int seq, String html) throws Exception
    {
        //插入语句示例
        //INSERT INTO `page` (`id`, `tag`, `url`, `seq`, `html`) VALUES ('5', '123', '123', '123', '123')
        //INSERT INTO `page` ( `tag`, `url`, `seq`, `html`) VALUES ( '123', 'www.123.com', '1', '123456789')
        // "+variable+"替换变量值组成查询语句
        String sql = "INSERT INTO `page` ( `tag`, `url`, `seq`, `html`) VALUES ( '"+tag+"', '"+url+"', '"+seq+"', '"+html+"')";
        Statement statement  = connection.createStatement();
        Boolean flag  = statement.execute(sql);
        return flag;
    }

    /**
     * 根据标签查询page表
     * @param tag
     * @return
     * @throws Exception
     */
    public ResultSet selectPageByTag(String tag) throws Exception
    {
        String sql = "select * from `page` where tag = " + tag;
        Statement statement  = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    /**
     * 插入section_content表的方法
     * @param tag
     * @param seq
     * @param title
     * @param content
     * @return
     * @throws Exception
     */
    public Boolean insertSectionContent(String tag, int seq, String title, String content) throws Exception
    {
        //插入语句示例
        //INSERT INTO `page` ( `tag`, `url`, `seq`, `html`) VALUES ( '123', 'www.123.com', '1', '123456789')
        // "+variable+"替换变量值组成查询语句
        String sql = "INSERT INTO `section_content` ( `tag`, `seq`, `title`, `content`) VALUES ( '"+tag+"', '"+seq+"', '"+title+"', '"+content+"')";
        Statement statement  = connection.createStatement();
        Boolean flag  = statement.execute(sql);
        return flag;
    }

    /**
     * 根据标签查询section_content的内容
     * @param tag
     * @return
     * @throws Exception
     */
    public ResultSet selectSectionContentByTag(String tag) throws Exception
    {
        String sql = "select * from `section_content` where tag = " + tag;
        Statement statement  = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }


















}
