package main.java.org.lxx.jdbc;

import java.sql.*;

public class JDBCTest {
    public static void main(String[] args) throws Exception {




        try {


            String url_data = "jdbc:mysql://127.0.0.1:3306/test?serverTimezone=GMT%2B8";
            String user = "root";
            String password = "root";
            DataBase dataBase = new DataBase(url_data,user,password);
            Connection connection = dataBase.getConnection();
            String sql = "select * from  page";
            Statement statement  = connection.createStatement();
            //ResultSet resultSet  = statement.executeQuery(sql);
            //Boolean flag = dataBase.insertPage("12345","www.12345.com",2,"qweajfija");
            ResultSet resultSet = dataBase.selectPageByTag("38294");








            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                String tag = resultSet.getString("tag");
                String url = resultSet.getString("url");
                String seq = resultSet.getString("seq");
                //String html = resultSet.getString("html");
                System.out.println("id="+id+";tag="+tag+";url="+url+";seq="+seq);
            }
            resultSet.close();
            statement.close();
            connection.close();

        } finally {


        }
    }









}