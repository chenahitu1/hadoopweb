package com.cht.hadoopDemo.controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(urlPatterns = {"/controller/verifyCode"})
public class VerifyCodeController extends HttpServlet {
    public static String verifyCode="";
    //验证码图片的宽度
    private int width=80;
    //验证码图片的宽度
    private int height=20;
    //验证码字符的个数
    private int codeCount=4;
    private int x=width/(codeCount+1);
    //字体的高度
    private int fontHeight=height-2;
    private int codeY=height-4;
    //字符的取值
    char[] codes={'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doGet(req,resp);
    }

    @Override
    protected  void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{


        //定义图像buffer
        BufferedImage bufferedImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        //在图像上面加上一个2D图像
        Graphics2D g=bufferedImage.createGraphics();
        //创建一个随机数生成器类
        Random random=new Random();
        //将图像填充为白色-选择颜色
        g.setColor(Color.WHITE);
        //填充图像
        g.fillRect(0,0,width,height);
        //创建字体，字体的大小根据图片的高度来定
        Font font=new Font(Font.DIALOG,Font.PLAIN,fontHeight);
        //在生成的2D图片上声明用什么字体来书写
        g.setFont(font);
        //画边框
        g.setColor(Color.BLACK);
        g.drawRect(0,0,width-1,height-1);
        //随机产生10条干扰线，使图像中的认证码不易被其它程序识别
        g.setColor(Color.BLACK);
        for(int i=0;i<10;i++){
            int x=random.nextInt(width);
            int y=random.nextInt(height);
            int xl=random.nextInt(10);
            int yl=random.nextInt(10);
            g.drawLine(x,y,x+xl,y+yl);
        }
        //randomCode用于保存随机产生的验证码，以便用户登录后进行验证
        StringBuffer randomCode=new StringBuffer();
        int red=0,green=0,blue=0;
        for(int i=0;i<codeCount;i++){
            //得到随机产生的验证码数字
            String strRand=String.valueOf(codes[random.nextInt(36)]);
            //产生随机的颜色分量来构造颜色值，这样输出的每个数字的颜色值都将是不同的
            red=random.nextInt(255);
            green=random.nextInt(255);
            blue=random.nextInt(255);
            //用随机产生的颜色将验证码绘制到图像中
            g.setColor(new Color(red,green,blue));
            g.drawString(strRand,(i+1)*x,codeY);
            //将产生的四个随机数组合到一起
            randomCode.append(strRand);
        }

        //将四位数字的验证码保存到Session中  验证码用Session来存储
        HttpSession session=req.getSession();
        session.setAttribute("validateCode",verifyCode=randomCode.toString());

        //允许跨域访问
        resp.setHeader("Access-Control-Allow-Origin", "*");
        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/jpeg");
        try {
            //将图像输出到Servlet输出流中
            ServletOutputStream sos=resp.getOutputStream();
            ImageIO.write(bufferedImage,"jpeg",sos);
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
