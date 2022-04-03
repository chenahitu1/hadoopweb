package com.cht.hadoopDemo.controller;

import com.cht.hadoopDemo.service.HdpTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 创建文件夹接口
 */
@WebServlet(urlPatterns = {"/controller/Mkdir"})
public class MkdirController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        String newFilePath =req.getParameter("newFileName");
        try {
            HdpTool hdpTool = new HdpTool();
            hdpTool.mkDir(newFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/HadoopWeb_war/fileIndex.html");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
