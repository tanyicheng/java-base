package com.barrett.base.net.tcp;


import com.barrett.common.constants.Constants;

import java.io.*;
import java.net.Socket;

/**
 * 客户端
 * @Author created by barrett in 2020/5/24 14:29
 */
public class Client {
    public static void main(String[] args) {
        clientFile();
    }


    //上传文件
    public static void clientFile(){
        try {
            //建立连接
            Socket client = new Socket("127.0.0.1",9999);
            //输入输出流操作
            InputStream is = new BufferedInputStream(new FileInputStream(Constants.file_path_man));
            BufferedOutputStream bos = new BufferedOutputStream(client.getOutputStream());
            byte[] bytes = new byte[1024];
            int len=-1;
            while ((len=is.read(bytes))!=-1){
                bos.write(bytes,0,len);
            }
            bos.flush();

            //释放资源
            bos.close();
            is.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void client(){
        try {
            //建立连接
            Socket client = new Socket("127.0.0.1",9999);
            //输入输出流操作
             DataOutputStream dos = new DataOutputStream(client.getOutputStream());
             String str = "hello";
             dos.writeUTF(str);
             dos.flush();
             //释放资源
            dos.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
