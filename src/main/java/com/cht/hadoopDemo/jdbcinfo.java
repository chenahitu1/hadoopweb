package com.cht.hadoopDemo;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class jdbcinfo {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //提供JDBC连接的URL
            String url="jdbc:mysql://localhost:3306/logindb?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Shanghai&autoReconnect=true";
            String username="root";
            String password="root";
            //创建数据库的连接
            Connection con = DriverManager.getConnection(url,username,password);
            //创建一个statement执行者
            String sql="SELECT * FROM user ";
            PreparedStatement statement = con.prepareStatement(sql);
//            statement.setLong(1,1);
            //执行SQL语句
            ResultSet result = statement.executeQuery();


            //
            Map<String,String> map=new HashMap<>();


            //处理返回结果
            while (result.next()){
                System.out.println(result.getString("userName") + "---" + result.getString("password"));
                map.put(result.getString("userName"),result.getString("password"));
            }

            for(String o: map.keySet()){
                System.out.println(o);
            }


            //关闭JDBC对象
            con.close();
            result.close();
            statement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
//    Connection dbconn = null;
//    public void init() {
//        String dburl  = "jdbc:mysql://127.0.0.1:3306/new_schema?useSSL=false&serverTimezone=UTC";
//        String username ="用户名";
//        String password = "密码";
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            dbconn = DriverManager.getConnection(dburl,username,password);
//            System.out.println("数据库连接成功");
//        }catch (ClassNotFoundException e1){
//            System.out.println(e1+"驱动程序找不到");
//        }catch(SQLException e2){
//            System.out.println(e2);
//        }
//
//    }