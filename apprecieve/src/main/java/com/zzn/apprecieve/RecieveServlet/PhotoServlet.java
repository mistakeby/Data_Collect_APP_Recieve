package com.zzn.apprecieve.RecieveServlet;

import com.zzn.apprecieve.pojo.Data;
import com.zzn.apprecieve.service.ServerImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/photo")
public class PhotoServlet extends HttpServlet {
    private String userid=null;
    @Autowired
    ServerImpl serverimpl;//=new ServerImpl();
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        System.out.println("photo-Get");
    }
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        System.out.println("photo-Post");
        if (ServletFileUpload.isMultipartContent(request)==true)
        {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            String path = "F:/TestData/"+userid+"/photo";
            File file=new File(path);//以后考虑是否要建立子目录，即是否要建立多级目录，
            // 按照用户名，照片，视频依次建子目录
            if(!file.exists()){
                file.mkdirs();
            }
            else
            {
            }
            factory.setRepository(new File(path));
            factory.setSizeThreshold(1024*1024*10) ;//可以考虑扩大缓冲区大小
            //文件上传处理
            ServletFileUpload upload = new ServletFileUpload(factory);
            try{
                //调用 parseRequest（request）方法  获得上传文件 FileItem 的集合list 可实现多文件上传。
                List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
                for(FileItem item:list){
                    //获取表单属性名字。
                    String name = item.getFieldName();
                    //如果获取的表单信息是普通的文本信息。即通过页面表单形式传递来的字符串。
                    if(item.isFormField()){
                    }
                    //如果传入的是非简单字符串，而是图片，音频，视频等二进制文件。
                    else{
                        //获取路径名
                        String value = item.getName();
                        //取到最后一个反斜杠。
                        int start = value.lastIndexOf("\\");
                        //截取上传文件的 字符串名字。+1是去掉反斜杠。
                        String filename = value.substring(start+1);
                        //收到写到接收的文件中
                        OutputStream out = new FileOutputStream(new File(path,filename));
                        InputStream in = item.getInputStream();
                        int length = 0;
                        byte[] buf = new byte[1024];
                        while((length = in.read(buf))!=-1){
                            out.write(buf,0,length);
                        }
                        in.close();
                        out.close();
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else
        {
            String[]  str1 = new String[20];
            String[] str2= new String[20];
            int i=0;
            Enumeration e = request.getParameterNames();
            while (e.hasMoreElements()) {
                str1[i] = (String) e.nextElement();
                str2[i] = request.getParameter(str1[i]);
                System.out.println(str1[i]+":"+str2[i]);
                i++;
            }
            userid=str2[0];
            Data data=new Data();
            data.setUserid(str2[0]);
            data.setType(str2[1]);
            data.setLight(str2[2]);
            data.setTopic(str2[3]);
            data.setLatitude(str2[4]);
            data.setCity(str2[5]);
            data.setDistrict(str2[6]);
            data.setSavepath(str2[7]);
            data.setOriginaltime(str2[8]);
            data.setDescprition(str2[9]);

            serverimpl.saveData(data);
        }
    }
}
