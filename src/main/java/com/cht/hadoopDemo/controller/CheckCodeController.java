package com.cht.hadoopDemo.controller;

import com.alibaba.fastjson.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/controller/checkCode"})
public class CheckCodeController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String code1=req.getParameter("code");
        String code2=VerifyCodeController.verifyCode;
        Boolean isEqual;
        if (code1.equals(code2)){
            isEqual=true;
        }else{
            isEqual=false;
        }
        HashMap<String,Object> map=new HashMap<>();
        map.put("isEqual",isEqual);
        JSONArray ja= new JSONArray();
        ja.add(map);
        PrintWriter out=resp.getWriter();
        out.print(ja);
    }
    @Override
    protected  void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {
        doPost(req,resp);
    }
    }
