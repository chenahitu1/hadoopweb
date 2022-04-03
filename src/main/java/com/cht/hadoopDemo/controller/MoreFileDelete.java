package com.cht.hadoopDemo.controller;

import com.cht.hadoopDemo.service.HdpTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 批量删除接口
 */
@WebServlet(urlPatterns = {"/controller/moreDelete"})
public class MoreFileDelete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);

        try {
            String[] paths = req.getParameterValues("path[]");
            for (String path : paths) {
//                if(path.startsWith("/yxj")){
//                    continue;
//                }
                HdpTool.del(path);
            }
        }catch (Exception e) {
            String path = req.getParameter("path");
            try {
//                if(path.startsWith("/yxj")){
//                    return;
//                }
                HdpTool.del(path);
            } catch (Exception er) {
                er.printStackTrace();
            }
        }

        PrintWriter out=resp.getWriter();
        out.print("success");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
