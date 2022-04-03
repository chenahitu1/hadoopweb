package com.cht.hadoopDemo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.net.URI;

public class HadoopTest {

    //定义一个链接 连接到linux下的hadoop
    public String url = "hdfs://192.168.72.132:9000";
    public Configuration configuration = null;
    public FileSystem fileSystem=null;

    public HadoopTest() throws Exception{
        //hadoop配置
        configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
//        configuration.set("dfs. ", "1");
        fileSystem= FileSystem.get(new URI(url), configuration, "root");
    }

    /**
     * 创建文件夹
     * @param path
     */
    public void mkDir(String path) throws IOException {
        //操作hadoop文件的核心类
        Boolean result = fileSystem.mkdirs(new Path(path));
    }

    /**
     * 上传文件
     * @throws Exception
     */
    public void upload()throws Exception{
        //windows的文件地址
        Path winPath=new Path("C:\\Users\\86130\\Desktop\\p2.jpg");
        //hadoop的保存目录
        Path linuxPath=new Path("/picture");
        fileSystem.copyFromLocalFile(winPath,linuxPath);
    }

    /**
     * 读取文件
     * @throws Exception
     */
    public void read(String path) throws Exception {
        //操作hadoop文件的核心类
        FSDataInputStream in = fileSystem.open(new Path(path));
        IOUtils.copyBytes(in, System.out, 1024);
    }

    /**
     * 写入文件
     * @throws Exception
     */
    public void write()throws Exception{
        FSDataOutputStream out= fileSystem.create(new Path("/test11.txt"));
        out.writeUTF("我喜欢写代码");
        out.flush();
        out.close();
    }

    /**
     * 下载
     * @throws Exception
     */
    public void down()throws Exception{
        Path linuxPath = new Path("/test11.txt");
        Path winPath= new Path("E:/");
        fileSystem.copyToLocalFile(false,linuxPath,winPath,true);
    }


    /**
     * 获取文件目录
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
     * 获取所有文件（不包括文件夹）
     * @throws Exception
     */
    public void listFiles() throws Exception{
        RemoteIterator<LocatedFileStatus> re= fileSystem.listFiles(new Path("/"),true);
        while(re.hasNext()){
            LocatedFileStatus locatedFileStatus=re.next();
            System.out.println(locatedFileStatus.getPath());
        }
    }

    /**
     * 重命名
     * @throws Exception
     */
    public void rename() throws Exception{
        Path oldPath = new Path("/test.txt");
        Path newPath = new Path("/test222.txt");
        fileSystem.rename(oldPath,newPath);
    }

    /**
     *  删除
     * @throws Exception
     */
    public void del() throws  Exception{
        Path path= new Path("/atubo");
        fileSystem.delete(path);
    }

    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();
        HadoopTest hadoopTest = new HadoopTest();

//        hadoopTest.mkDir("/document");
//        hadoopTest.upload();
//        hadoopTest.del();

//        hadoopTest.read("/test.txt");

//        hadoopTest.write();
//        hadoopTest.read("/test11.txt");

//        hadoopTest.down();

//        hadoopTest.fileAll("/picture");
//        hadoopTest.listFiles();
    }


}