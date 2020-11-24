package com.barrett.test;

import com.barrett.util.PLC.tcp.HexUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MC {

    public static void main(String[] args) {
        try {
//            write("192.168.3.101",4002,1,new String[1],false);
            read("192.168.3.101",4002,1,1,false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String[] read(String ip, int port, int startPos, int num, boolean swapHL) throws Exception {

        Socket socket = new Socket(ip,port);

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        StringBuffer sb = new StringBuffer("5000"); //副标题，命令代码
        sb.append("00");//网络编号
        sb.append("FF"); //PLC编号
        sb.append("FF03");//IO编号
        sb.append("00");//模块站号

        StringBuilder sbReqData = new StringBuilder();
        sbReqData.append("0100");//CPU监视定时器
        sbReqData.append("0104");//命令
        sbReqData.append("0000");//子命令
        sbReqData.append(HexUtil.intToHexString(startPos, 6, true));//起始软元件
        sbReqData.append("AF");//软元件代码，R*=AF
        sbReqData.append(HexUtil.intToHexString(num, 4, true));//软元件数
        sb.append(HexUtil.intToHexString(sbReqData.toString().length() / 2, 4, true));//请求数据长度
        sb.append(sbReqData.toString());
        String readCmd = "50 00 00 FF FF 03 00 0E 00 10 00 01 04 00 00 0A 00 00 A8 01 00 0F 00".replaceAll(" ","");
//        String readCmd = sb.toString();

        System.out.println(readCmd);

        dos.write(HexUtil.hexStringToByte(readCmd));

        //开始读取
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        byte[] buffer = new byte[20]; //从开头到数据长度
        dis.readFully(buffer);
        String response = HexUtil.bytesToHexString(buffer);//从开头到数据长度
        String[] results = null;
        System.out.println("======="+response);
        if ("D000".equals(response.substring(0, 4))) {
            int dataLen = HexUtil.hexStringToInt(response.substring(14), true);
            buffer = new byte[dataLen];
            dis.readFully(buffer);
            String dataResponse = HexUtil.bytesToHexString(buffer);//数据
            String resCode = dataResponse.substring(0, 4); //结束代码
            if ("0000".equals(resCode)) {
                dataResponse = dataResponse.substring(4); //数据
                results = new String[num];
                for (int i = 0; i < num * 2; i += 2) {
                    String resultL = dataResponse.substring(i * 2, (i + 1) * 2); //返回结果
                    String resultH = dataResponse.substring((i + 1) * 2, (i + 2) * 2); //返回结果
                    if (swapHL) {
                        results[i / 2] = resultH + resultL;
                    } else {
                        results[i / 2] = resultL + resultH;
                    }
                }
            }
        }
        return results;
    }

    public static void write(String ip, int port, int startPos, String[] datas, boolean swapHL) throws Exception {
        try {
            Socket socket = new Socket(ip,port);

            synchronized (socket) {
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//                StringBuffer sb = new StringBuffer("5000"); //副标题，命令代码
//                sb.append("00");//网络编号
//                sb.append("FF"); //PLC编号
//                sb.append("FF03");//IO编号
//                sb.append("00");//模块站号
//                StringBuilder sbReqData = new StringBuilder();
//                sbReqData.append("0100");//CPU监视定时器
//                sbReqData.append("0114");//命令
//                sbReqData.append("0000");//子命令
//                sbReqData.append(HexUtil.intToHexString(startPos, 6, true));//起始软元件
//                sbReqData.append("AF");//软元件代码，R*=AF
//                sbReqData.append(HexUtil.intToHexString(21, 4, true));//软元件数
//                for (String item : datas) {
//                    sbReqData.append(HexUtil.str2ToHex4(item, swapHL));
//                }
//                //补全空白
//                for (int i = datas.length; i < 21; i++) {
//                    sbReqData.append("0000");
//                }
//                sb.append(HexUtil.intToHexString(sbReqData.toString().length() / 2, 4, true));//请求数据长度
//                sb.append(sbReqData.toString());
                String writeCmd = "50 00 00 FF FF 03 00 0E 00 10 00 01 14 00 00 0A 00 00 A8 01 00 0F 00".replaceAll(" ","");
//                String writeCmd = sb.toString();
                System.out.println(writeCmd);
//                byte[] bytes = new byte[100];
//                String[] s = writeCmd.split(" ");
//                    for (int i = 0; i < s.length; i++) {
//                    bytes[i]= Byte.parseByte(s[i]);
//                }
                dos.write(HexUtil.hexStringToByte(writeCmd));

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                byte[] buffer = new byte[9]; //从开头到数据长度
                dis.readFully(buffer);
                String response = HexUtil.bytesToHexString(buffer);//从开头到数据长度
                System.out.println(response);
                if ("D000".equals(response.substring(0, 4))) {
                    int dataLen = HexUtil.hexStringToInt(response.substring(14), true);
                    buffer = new byte[dataLen];
                    dis.readFully(buffer);
                    //                String dataResponse = HexUtil.bytesToHexString(buffer); //数据
                }
            }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
    }
}
