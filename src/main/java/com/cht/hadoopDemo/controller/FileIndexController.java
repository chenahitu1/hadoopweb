package com.cht.hadoopDemo.controller;


import com.alibaba.fastjson.JSONArray;
import com.cht.hadoopDemo.entity.HdpFile;
import com.cht.hadoopDemo.service.HdpTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 文件目录接口
 */
@WebServlet(urlPatterns = {"/controller/fileIndex"})
public class FileIndexController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        try {
            HdpTool hdpTool = new HdpTool();
            List<HdpFile> list= hdpTool.getFile(path);
            JSONArray ja= (JSONArray) JSONArray.toJSON(list);
            PrintWriter out=resp.getWriter();
            out.print(ja);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}