package com.cht.hadoopDemo.controller;

import com.cht.hadoopDemo.service.HdpTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/controller/Download"})
public class FileDownloadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String downloadPath =req.getParameter("path");

        HdpTool hdpTool = null;
        try {
            hdpTool = new HdpTool();
            hdpTool.download(downloadPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/HadoopWeb_war/fileIndex.html");
    }
}
