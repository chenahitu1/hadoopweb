package com.cht.hadoopDemo.service;

import java.sql.*;

public class DbTool {
    public Connection con=null;
    //数据库连接
    public void connection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //提供JDBC连接的URL
            String url="jdbc:mysql://localhost:3306/logindb?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Shanghai&autoReconnect=true";
            String username="root";
            String password="root";
            //创建数据库的连接
            Connection con = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void closeConnection(){
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
//    public void
//        try{
//            //首先加载驱动
//
//            //创建一个statement执行者
//            String sql="SELECT * FROM user WHERE id = ?";
//            PreparedStatement statement = con.prepareStatement(sql);
//            statement.setLong(1,1);
//            //执行SQL语句
//            ResultSet result = statement.executeQuery();
//            //处理返回结果
//            while (result.next()){
//                System.out.println(result.getString("name") + "---" + result.getString("remark"));
//            }
//            //关闭JDBC对象
//
//            result.close();
//            statement.close();
//        }catch(ClassNotFoundException e){
//            e.printStackTrace();
//        } catch(Exception e){
//            e.printStackTrace();
//        }

}
