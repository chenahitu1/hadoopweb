package com.cht.hadoopDemo.controller;

import com.alibaba.fastjson.JSONArray;
import com.cht.hadoopDemo.entity.UserInfo;
import com.cht.hadoopDemo.service.HdpTool;
import org.apache.xerces.impl.dv.util.HexBin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet(urlPatterns = {"/controller/Login"})
public class LoginController extends HttpServlet {

    Connection dbconn = null;
    public void init() {
        String dburl  = "jdbc:mysql://localhost:3306/logindb?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Shanghai&autoReconnect=true";
        String username ="root";
        String password = "root";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbconn = DriverManager.getConnection(dburl,username,password);
            System.out.println("数据库连接成功");
        }catch (ClassNotFoundException e1){
            System.out.println(e1+"驱动程序找不到");
        }catch(SQLException e2){
            System.out.println(e2);
        }
    }

//doPost它用于客户端把数据传送到服务器端，也会有副作用。但好处是可以隐藏传送给服务器的任何数据。Post适合发送大量的数据。
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    //1

    //GET 调用用于获取服务器信息，并将其做为响应返回给客户端。
    // 当经由Web浏览器或通过HTML、JSP直接访问Servlet的URL时，一般用GET调用。
    // GET调用在URL里显示正传送给SERVLET的数据，这在系统的安全方面可能带来一些问题，比如用户登录，
    // 表单里的用户名和密码需要发送到服务器端， 若使用Get调用，就会在浏览器的URL中显示用户名和密码
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doPost(req,resp);
        //如果将以下代码放在dopost请求数据的时候是看不到数据的
        //设置字符集为utf-8
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        Boolean isLogin;
        UserInfo.userName = req.getParameter("username");
        UserInfo.password= req.getParameter("password");
        //密码的不可逆加密操作MD5
        MessageDigest messageDigest=null;
        try {
            messageDigest=MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("加密操作出错");
        }
        byte[] bytes=messageDigest.digest(UserInfo.password.getBytes());
        String encodePassword= HexBin.encode(bytes);//加密后的密码
        System.out.println(encodePassword);

        //创建一个map集合用来存储数据库的用户名和密码
        Map<String,String> map=new HashMap<>();
        try {
            String sql="SELECT * FROM user ";
            //创建一个statement执行者
            PreparedStatement statement = dbconn.prepareStatement(sql);
            //执行语句
            ResultSet result = statement.executeQuery();

            //处理返回结果
            while (result.next()){
                System.out.println(result.getString("userName") + "---" + result.getString("password"));
                map.put(result.getString("userName"),result.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean isExit = map.containsKey(UserInfo.userName);
        if(isExit){
            String value = map.get(UserInfo.userName);
            if(value.equals(encodePassword)){
                isLogin=true;
            }else{
                isLogin=false;
            }
        }else{
            isLogin=false;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("isLogin",isLogin);
        JSONArray ja= new JSONArray();
        ja.add(hashMap);
        PrintWriter out=resp.getWriter();
        out.print(ja);
    }

    public void destroy(){
        try{
            dbconn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
