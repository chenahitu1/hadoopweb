package com.cht.hadoopDemo.controller;


import com.cht.hadoopDemo.service.HdpTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除接口
 */
@WebServlet(urlPatterns = {"/controller/fileDelete"})
public class FileDeleteController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        String path = req.getParameter("path");
        HdpTool hdpTool = null;
        try {
            hdpTool = new HdpTool();
            hdpTool.del(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        resp.sendRedirect("/HadoopWeb_war/fileIndex.html");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
