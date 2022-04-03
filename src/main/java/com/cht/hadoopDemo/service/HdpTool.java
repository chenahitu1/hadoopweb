package com.cht.hadoopDemo.service;

import com.cht.hadoopDemo.entity.HdpFile;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HdpTool {
    //定义一个链接 连接到linux下的hadoop
    public String url = "hdfs://192.168.72.132:9000";
    public Configuration configuration = null;
    public static FileSystem fileSystem = null;

    public HdpTool() throws Exception {
        //hadoop配置
        configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
//        configuration.set("dfs. ", "1");
        fileSystem = FileSystem.get(new URI(url), configuration, "root");
    }

    /**
     * 获取文件目录
     *
     * @param url
     * @throws Exception
     */
    public List<HdpFile> getFile(String url) throws Exception {
        //新建一个list，用来保存目录
        List<HdpFile> list = new ArrayList<>();
        //获取到Hadoop根目录的文件列表
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path(url));
        for (FileStatus file : fileStatuses) {
//            list.add(file.getPath().toString());
            HdpFile hdpFile = new HdpFile();
            hdpFile.path=file.getPath().toUri().getPath();
            hdpFile.isDir=file.isDirectory();
            hdpFile.name=file.getPath().getName();
            hdpFile.size= new BigDecimal((double)file.getLen()/1024).setScale(2, BigDecimal.ROUND_HALF_UP)+"kb";
            hdpFile.timeStamp=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.getModificationTime());
            list.add(hdpFile);
        }
        return list;
    }


    /**
     * 创建文件夹
     *
     * @param path
     */
    public void mkDir(String path) throws IOException {
        //操作hadoop文件的核心类
        fileSystem.mkdirs(new Path(path));
    }

    /**
     * 获取子文件夹目录
     * @param url
     * @throws Exception
     */
    public void fileAll(String url) throws  Exception{
        //获取到Hadoop根目录的文件列表
        FileStatus[] fileStatuses =fileSystem.listStatus(new Path(url));
        for (FileStatus file:fileStatuses){
            System.out.println(file.getPath());
            if(file.isDirectory()){
                fileAll(file.getPath().toString());
            }
        }
    }
    /**
     * 下载
     */
    public static void download(String path)throws Exception{
        Path filePath = new Path(path);
        Path winPath= new Path("D:\\hadoopDownload");
        fileSystem.copyToLocalFile(false,filePath,winPath,true);
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    }

    /**
     *  删除
     * @throws Exception
     */
    public static void del(String url) throws  Exception{
        Path path= new Path(url);
        fileSystem.delete(path);
    }
    /**
     * 重命名
     * @throws Exception
     */
    public void rename(String oldName,String newName) throws Exception{
        Path oldPath = new Path(oldName);
        Path newPath = new Path(newName);
        fileSystem.rename(oldPath,newPath);
    }

    public void upload(String wp,String hp)throws Exception{
        //windows的文件地址
        Path winPath=new Path(wp);
        //hadoop的保存目录
        Path linuxPath=new Path(hp);
        fileSystem.copyFromLocalFile(winPath,linuxPath);
    }

}

