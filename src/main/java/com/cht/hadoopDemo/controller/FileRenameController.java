package com.cht.hadoopDemo.controller;

import com.cht.hadoopDemo.service.HdpTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重命名接口
 */

@WebServlet(urlPatterns = {"/controller/rename"})
public class FileRenameController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String newName =req.getParameter("newName");
        String oldName=req.getParameter("oldName");

        HdpTool hdpTool = null;
        try {
            hdpTool = new HdpTool();
            hdpTool.rename(oldName,newName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/HadoopWeb_war/fileIndex.html");
    }
}
